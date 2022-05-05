package ru.lab.service.impl;

import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.TransactionStatus;
//import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import ru.lab.dto.RateMovieReqDTO;
import ru.lab.exception.DatabaseException;
import ru.lab.model.*;
import ru.lab.repository.*;
import ru.lab.service.UserService;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final CountryRepository countryRepository;
    private final GenreRepository genreRepository;
    private final HumanRepository humanRepository;
    private final MovieRepository movieRepository;
    private final IMDBUserRepository userRepository;
    private final RatingRepository ratingRepository;
    private final TransactionTemplate transactionTemplate;

    @Override
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    @Override
    public List<Human> getHumans() {
        return humanRepository.findAll();
    }

    @Override
    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }

    @Override
    public List<Country> getCountries() {
        return countryRepository.findAll();
    }

    @Override
    public void rateMovie(RateMovieReqDTO rateMovieReqDTO) throws DatabaseException {
        Movie movie = movieRepository.findMovieById(rateMovieReqDTO.getMovieId()).orElseThrow(() -> new DatabaseException("Фильм не найден"));
        IMDBUser user = userRepository.findIMDBUserByLogin(rateMovieReqDTO.getLogin()).orElseThrow(() -> new DatabaseException("Пользователь IMDb не найден"));
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    setRating(movie, user, rateMovieReqDTO.getValue());
                    updateAverageRatingMovie(movie);
                } catch (Exception e) {
                    status.setRollbackOnly();
                }
            }
        });
    }

    private void setRating(Movie movie, IMDBUser user, Double value) {
        Rating rating = ratingRepository.findRatingByMovieAndUser(movie, user).orElseGet(Rating::new);
        rating.setValue(value);
        rating.setMovie(movie);
        rating.setUser(user);
        rating.setDate(new Date(new java.util.Date().getTime()));
        ratingRepository.save(rating);
    }

    private void updateAverageRatingMovie(Movie movie) {
        double averageRating = movie.getRatings().stream().mapToDouble(Rating::getValue).average().orElse(Double.NaN);
        movie.setAverageRating(Double.isNaN(averageRating) ? null : averageRating);
        movieRepository.save(movie);
    }

}
