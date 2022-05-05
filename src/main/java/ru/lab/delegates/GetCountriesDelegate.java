package ru.lab.delegates;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import ru.lab.dto.CountryRespDTO;
import ru.lab.service.UserService;

import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequiredArgsConstructor
public class GetCountriesDelegate implements JavaDelegate {

    private final UserService userService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<CountryRespDTO> countryRespDTOList = userService.getCountries().stream()
                .map(x -> CountryRespDTO.builder().name(x.getName()).build())
                .collect(Collectors.toList());

        ObjectValue jsonCountries = Variables.objectValue(countryRespDTOList).serializationDataFormat("application/json").create();
        delegateExecution.setVariable("countriesList", jsonCountries);
    }
}
