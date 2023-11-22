package br.com.bookon.server.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.bookon.server.enumerations.RoleEnum;
import br.com.bookon.server.models.postgres.Role;
import br.com.bookon.server.models.postgres.User;
import br.com.bookon.server.payload.request.postgres.FilterRequest;
import br.com.bookon.server.payload.request.postgres.RegisterRequest;
import br.com.bookon.server.payload.response.postgres.MessageResponse;
import br.com.bookon.server.payload.response.postgres.RegionWithUsersRosponse;
import br.com.bookon.server.payload.response.postgres.NominatimAdressResponse.AddressParts;
import br.com.bookon.server.payload.response.postgres.NominatimGeolocationResponse.Place;
import br.com.bookon.server.repositories.postgres.RoleRepository;
import br.com.bookon.server.repositories.postgres.UserRepository;
import br.com.bookon.server.specifications.UserSpecification;


@Service
public class UserService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private GeolocationService geolocationService;
    
    @Autowired
    private PasswordEncoder encoder;
    
    private int RADIUS_EARTH = 6371;
    
    private Integer MAX_DISTANCE = 15;
    
    public Page<User> list(FilterRequest filterRequest) {
        UserSpecification spec = new UserSpecification();
        return userRepository.findAll(spec.search(filterRequest, User.class), filterRequest.build());
    }
    
    public MessageResponse register(RegisterRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new MessageResponse("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new MessageResponse("Error: Email is already in use!");
        }

        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setRoles(strRolesForEnum(signUpRequest.getRole()));
        
        populateAddressValues(user, signUpRequest);
        userRepository.save(user);

        return new MessageResponse("User registered successfully!");
    }
    
    public Set<Role> strRolesForEnum(Set<String> strRoles) {
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(RoleEnum.USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(RoleEnum.ADMININSTRATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(RoleEnum.MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(RoleEnum.USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        return roles;
    }
    
    public List<RegionWithUsersRosponse> findRegionsWithNearbyUsersByUserGeolocation(Integer userFinderId) {
    	User userFinder = userRepository.findById(userFinderId).orElse(null);
    	
    	List<User> nearbyUsers = userRepository.findNearbyUsersOrderByDistance(
    			userFinder.getId(), userFinder.getLatitude(), userFinder.getLongitude(), MAX_DISTANCE);
    	
    	List<RegionWithUsersRosponse> listRegionWithUsers = new ArrayList<>();
    	
    	 while (!nearbyUsers.isEmpty()) {
    	        User closestUser = nearbyUsers.get(0);

    	        List<User> usersInRegion = nearbyUsers.stream()
    	            .filter(otherUser -> calculateDistance(closestUser, otherUser) <= 2)
    	            .collect(Collectors.toList());
    	        usersInRegion.add(closestUser);

    	        nearbyUsers.removeAll(usersInRegion);

    	        RegionWithUsersRosponse regionWithUsers = new RegionWithUsersRosponse();
    	        regionWithUsers.setUsers(usersInRegion);
    	        regionWithUsers.populateCoordinates();
    	        regionWithUsers.setDistance(calculateDistance(userFinder, new User(regionWithUsers.getLatitude(), regionWithUsers.getLongitude())));
    	        
    	        listRegionWithUsers.add(regionWithUsers);
    	    }
    	return listRegionWithUsers;

    }
    
    private double calculateDistance(User closestUser, User otherUser ) {

        double latitudeClosestUser = Math.toRadians(closestUser.getLatitude());
        double longitudeClosestUser = Math.toRadians(closestUser.getLongitude());
        double latitudeOtherUser = Math.toRadians(otherUser.getLatitude());
        double longitudeOtherUser = Math.toRadians(otherUser.getLongitude());

        double latituteDifference = latitudeOtherUser - latitudeClosestUser;
        double longitudeDifference = longitudeOtherUser - longitudeClosestUser;

        double a = Math.sin(latituteDifference / 2) * Math.sin(latituteDifference / 2)
                + Math.cos(latitudeClosestUser) * Math.cos(latitudeOtherUser) * Math.sin(longitudeDifference / 2) * Math.sin(longitudeDifference / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return RADIUS_EARTH * c;
    }
    private User populateAddressValues(User user, RegisterRequest signUpRequest) {
    	
    	if (signUpRequest.isGeolocationFromNatigator()) {
    		
        	AddressParts address = geolocationService.getCityStateCountry(
        			signUpRequest.getLatitude(), signUpRequest.getLongitude())
        			.getAddressparts();
        	
        	user.setLatitude(signUpRequest.getLatitude());
            user.setLongitude(signUpRequest.getLongitude());
            user.setCity(address.getCity() != null ? address.getCity() : address.getTown());
            user.setState(address.getState());
            return user;
        }
		
        Place geolocation = geolocationService.geocodeAddress(
            		signUpRequest.getAddress()).getPlace();
        
        AddressParts address = geolocationService.getCityStateCountry(
        		geolocation.getLatitude(), geolocation.getLongitude())
        		.getAddressparts();
    	
    	user.setLatitude(geolocation.getLatitude());
        user.setLongitude(geolocation.getLongitude());
        user.setCity(address.getCity() != null ? address.getCity() : address.getTown());
        user.setState(address.getState());
        return user;
    }
    
    public User populateGeolocation(User user, String address) {
    	Place geolocation = geolocationService.geocodeAddress(address).getPlace();
		user.setLatitude(geolocation.getLatitude());
	    user.setLongitude(geolocation.getLongitude());
    
    return user;
    }
    
    public List<RegionWithUsersRosponse> findRegionsWithNearbyUsersByAdress(Integer userFinderId, String address) {
    	User userFinder = userRepository.findById(userFinderId).orElse(null);
    	populateGeolocation(userFinder, address);
    	
    	List<User> nearbyUsers = userRepository.findNearbyUsersOrderByDistance(
    			userFinder.getId(), userFinder.getLatitude(), userFinder.getLongitude(), MAX_DISTANCE);
    	
    	List<RegionWithUsersRosponse> listRegionWithUsers = new ArrayList<>();
    	
    	 while (!nearbyUsers.isEmpty()) {
    	        User closestUser = nearbyUsers.get(0);

    	        List<User> usersInRegion = nearbyUsers.stream()
    	            .filter(otherUser -> calculateDistance(closestUser, otherUser) <= 2)
    	            .collect(Collectors.toList());
    	        usersInRegion.add(closestUser);

    	        nearbyUsers.removeAll(usersInRegion);

    	        RegionWithUsersRosponse regionWithUsers = new RegionWithUsersRosponse();
    	        regionWithUsers.setUsers(usersInRegion);
    	        regionWithUsers.populateCoordinates();
    	        regionWithUsers.setDistance(calculateDistance(userFinder, new User(regionWithUsers.getLatitude(), regionWithUsers.getLongitude())));
    	        
    	        listRegionWithUsers.add(regionWithUsers);
    	    }
    	return listRegionWithUsers;

    }

}
