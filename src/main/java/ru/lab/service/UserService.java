package ru.lab.service;

import ru.lab.dto.RateMovieReqDTO;
import ru.lab.exception.DatabaseException;
import ru.lab.model.Country;
import ru.lab.model.Genre;
import ru.lab.model.Human;
import ru.lab.model.Movie;

import java.util.List;

public interface UserService {
    List<Movie> getMovies();
    List<Human> getHumans();
    List<Genre> getGenres();
    List<Country> getCountries();
    void rateMovie(RateMovieReqDTO rateMovieReqDTO) throws DatabaseException;
}
