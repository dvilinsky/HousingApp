package edu.brandeis.housing.repository;

import edu.brandeis.housing.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
}
