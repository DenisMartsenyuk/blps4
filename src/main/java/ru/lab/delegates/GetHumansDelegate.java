package ru.lab.delegates;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import ru.lab.dto.HumanRespDTO;
import ru.lab.service.UserService;

import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequiredArgsConstructor
public class GetHumansDelegate implements JavaDelegate {

    private final UserService userService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<HumanRespDTO> humanRespDTOList = userService.getHumans().stream()
                .map(x -> HumanRespDTO.builder().id(x.getId()).name(x.getName()).surname(x.getSurname()).build())
                .collect(Collectors.toList());

        ObjectValue jsonHumans = Variables.objectValue(humanRespDTOList).serializationDataFormat("application/json").create();
        delegateExecution.setVariable("humansList", jsonHumans);
    }
}
