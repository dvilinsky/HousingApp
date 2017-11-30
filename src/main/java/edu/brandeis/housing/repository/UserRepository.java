package edu.brandeis.housing.repository;

import edu.brandeis.housing.models.Rating;
import edu.brandeis.housing.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT u FROM User u WHERE lower(u.name) LIKE CONCAT('%', LOWER(:name), '%' ) ")
    List<User> findUserByName(@Param("name") String name);

    User findByUserName(String userName);

    @Query(value = "SELECT r FROM Rating r WHERE r.writer.userID = :userId")
    List<Rating> findRatingsUserHasWritten(@Param("userId") Integer userId);

    @Query(value = "SELECT r FROM Rating r WHERE r.aboutWhom.userID = :userId")
    List<Rating> findRatingsAboutUser(@Param("userId") Integer userId);
}
