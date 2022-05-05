package ru.lab.delegates;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import ru.lab.dto.GenreRespDTO;
import ru.lab.service.UserService;

import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;


@Named
@RequiredArgsConstructor
public class GetGenresDelegate implements JavaDelegate {

    private final UserService userService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<GenreRespDTO> genreRespDTOList = userService.getGenres().stream()
                .map(x -> GenreRespDTO.builder().name(x.getName()).build())
                .collect(Collectors.toList());

        ObjectValue jsonGenres = Variables.objectValue(genreRespDTOList).serializationDataFormat("application/json").create();
        delegateExecution.setVariable("genresList", jsonGenres);
    }
}
