package br.com.bookon.server.payload.response.postgres;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bookon.server.models.postgres.User;

public class RegionWithUsersRosponse {
	
	private List<User> users;
	
	private double latitude;
	
	private double longitude;
	
	private double distance;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public RegionWithUsersRosponse() {
	}

	public RegionWithUsersRosponse(List<User> users, double latitude, double longitude) {
		super();
		this.users = users;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public void populateCoordinates() {
        double somaLatitude = 0.0;
        double somaLongitude = 0.0;
        
        for (User user : this.users) {
            somaLatitude += user.getLatitude();
            somaLongitude += user.getLongitude();
        }

        double mediaLatitude = somaLatitude / this.users.size();
        double mediaLongitude = somaLongitude / this.users.size();
        
        this.setLatitude(mediaLatitude);
        this.setLongitude(mediaLongitude);        
	}
	
	private List<BookResponse> getBooksFromUsers() {
	    return users.stream()
	            .flatMap(user -> user.getBooks().stream())
	            .map(BookResponse::new)
	            .collect(Collectors.toList());
	}
	
	public RegionWithBookRosponse toRegionWithBookRosponse() {
		RegionWithBookRosponse regionWithBookRosponse = new RegionWithBookRosponse();
		regionWithBookRosponse.setBooks(this.getBooksFromUsers());
		regionWithBookRosponse.setLatitude(this.latitude);
		regionWithBookRosponse.setLongitude(this.longitude);
		regionWithBookRosponse.setDistance(this.distance);
		
		return regionWithBookRosponse;
	}
        
}
