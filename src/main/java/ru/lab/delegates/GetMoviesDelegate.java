package ru.lab.delegates;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import ru.lab.dto.CountryRespDTO;
import ru.lab.dto.GenreRespDTO;
import ru.lab.dto.HumanRespDTO;
import ru.lab.dto.MovieRespDTO;
import ru.lab.model.Movie;
import ru.lab.service.UserService;

import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequiredArgsConstructor
public class GetMoviesDelegate implements JavaDelegate {

    private final UserService userService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<MovieRespDTO> movieRespDTOList = userService.getMovies().stream()
                .map(this::getMovieRespDTO)
                .collect(Collectors.toList());


        ObjectValue jsonHumans = Variables.objectValue(movieRespDTOList).serializationDataFormat("application/json").create();
        delegateExecution.setVariable("moviesList", jsonHumans);
    }

    private MovieRespDTO getMovieRespDTO(Movie movie) {
        List<GenreRespDTO> genres =  movie.getGenres().stream()
                .map(x -> GenreRespDTO.builder().name(x.getName()).build())
                .collect(Collectors.toList());

        List<CountryRespDTO> countries = movie.getCountries().stream()
                .map(x -> CountryRespDTO.builder().name(x.getName()).build())
                .collect(Collectors.toList());

        List<HumanRespDTO> directors = movie.getDirectors().stream()
                .map(x -> HumanRespDTO.builder().id(x.getId()).name(x.getName()).surname(x.getSurname()).build())
                .collect(Collectors.toList());

        List<HumanRespDTO> writers = movie.getWriters().stream()
                .map(x -> HumanRespDTO.builder().id(x.getId()).name(x.getName()).surname(x.getSurname()).build())
                .collect(Collectors.toList());

        List<HumanRespDTO> actors = movie.getActors().stream()
                .map(x -> HumanRespDTO.builder().id(x.getId()).name(x.getName()).surname(x.getSurname()).build())
                .collect(Collectors.toList());


        return MovieRespDTO.builder()
                .id(movie.getId())
                .name(movie.getName())
                .description(movie.getDescription())
                .year(movie.getYear())
                .runtime(movie.getRuntime())
                .averageRating(movie.getAverageRating())
                .genres(genres)
                .countries(countries)
                .directors(directors)
                .writers(writers)
                .actors(actors)
                .build();
    }
}
