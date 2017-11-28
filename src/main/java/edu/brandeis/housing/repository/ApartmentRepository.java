package edu.brandeis.housing.repository;

import edu.brandeis.housing.models.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {

    @Query(value = "SELECT a FROM Apartment a WHERE lower(a.address) LIKE CONCAT('%', LOWER(:address), '%') ")
    List<Apartment> findApartmentByAddress(@Param("address") String address);
}
