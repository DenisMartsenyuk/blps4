package ru.lab.service;

import ru.lab.dto.SaveCountryReqDTO;
import ru.lab.dto.SaveGenreReqDTO;
import ru.lab.dto.SaveHumanReqDTO;
import ru.lab.dto.SaveMovieReqDTO;
import ru.lab.exception.DatabaseException;

public interface AdminService {
    void saveHuman(SaveHumanReqDTO saveHumanReqDTO);
    void saveGenre(SaveGenreReqDTO saveGenreReqDTO);
    void saveCountry(SaveCountryReqDTO saveCountryReqDTO);
    void saveMovie(SaveMovieReqDTO saveMovieReqDTO) throws DatabaseException;
}
