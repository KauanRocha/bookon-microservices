package br.com.bookon.server.repositories.postgres;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.bookon.server.models.postgres.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer>, JpaSpecificationExecutor<User>, ListCrudRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    
    @Query(value = "SELECT u.*, " +
            "(6371 * acos(cos(radians(:userLatitude)) * cos(radians(u.latitude)) * cos(radians(:userLongitude - u.longitude)) + sin(radians(:userLatitude)) * sin(radians(u.latitude)))) AS distance " +
            "FROM users u " +
            "WHERE u.id <> :userId AND (6371 * acos(cos(radians(:userLatitude)) * cos(radians(u.latitude)) * cos(radians(:userLongitude - u.longitude)) + sin(radians(:userLatitude)) * sin(radians(u.latitude)))) <= :maxDistance " +
            "ORDER BY (6371 * acos(cos(radians(:userLatitude)) * cos(radians(u.latitude)) * cos(radians(:userLongitude - u.longitude)) + sin(radians(:userLatitude)) * sin(radians(u.latitude)))) ASC", nativeQuery = true)
    List<User> findNearbyUsersOrderByDistance(@Param("userId") Integer userId, @Param("userLatitude") double userLatitude, @Param("userLongitude") double userLongitude, @Param("maxDistance") Integer maxDistance);
    
}
