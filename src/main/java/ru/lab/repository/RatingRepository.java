package ru.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.model.IMDBUser;
import ru.lab.model.Movie;
import ru.lab.model.Rating;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findRatingByMovieAndUser(Movie movie, IMDBUser user);
    List<Rating> findRatingsByDateAfter(Date date);
}
