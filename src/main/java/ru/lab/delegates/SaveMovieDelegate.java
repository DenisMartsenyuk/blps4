package ru.lab.delegates;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import ru.lab.dto.SaveMovieReqDTO;
import ru.lab.service.AdminService;

import javax.inject.Named;
import java.lang.reflect.Type;
import java.util.List;

@Named
@RequiredArgsConstructor
public class SaveMovieDelegate implements JavaDelegate {

    private final AdminService adminService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<String> genres = getListStringFromJson((String) delegateExecution.getVariable("movieGenres"));
        List<String> countries = getListStringFromJson((String) delegateExecution.getVariable("movieCountries"));
        List<Long> directors = getListLongFromJson((String) delegateExecution.getVariable("movieDirectors"));
        List<Long> writers = getListLongFromJson((String) delegateExecution.getVariable("movieWriters"));
        List<Long> actors = getListLongFromJson((String) delegateExecution.getVariable("movieActors"));

        SaveMovieReqDTO saveMovieReqDTO = new SaveMovieReqDTO();
        saveMovieReqDTO.setName((String) delegateExecution.getVariable("movieName"));
        saveMovieReqDTO.setDescription((String) delegateExecution.getVariable("movieDescription"));
        saveMovieReqDTO.setYear(Integer.valueOf((String) delegateExecution.getVariable("movieYear")));
        saveMovieReqDTO.setRuntime(Integer.valueOf((String) delegateExecution.getVariable("movieRuntime")));
        saveMovieReqDTO.setGenres(genres);
        saveMovieReqDTO.setCountries(countries);
        saveMovieReqDTO.setDirectors(directors);
        saveMovieReqDTO.setWriters(writers);
        saveMovieReqDTO.setActors(actors);

        adminService.saveMovie(saveMovieReqDTO);

    }

    private List<String> getListStringFromJson(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>(){}.getType();
        return gson.fromJson(json, type);
    }

    private List<Long> getListLongFromJson(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Long>>(){}.getType();
        return gson.fromJson(json, type);
    }
}
