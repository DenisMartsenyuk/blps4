package ru.lab.delegates;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import ru.lab.dto.SaveHumanReqDTO;
import ru.lab.service.AdminService;

import javax.inject.Named;

@Named
@RequiredArgsConstructor
public class SaveHumanDelegate implements JavaDelegate {

    private final AdminService adminService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        SaveHumanReqDTO saveHumanReqDTO = new SaveHumanReqDTO();
        saveHumanReqDTO.setName((String) delegateExecution.getVariable("humanName"));
        saveHumanReqDTO.setSurname((String) delegateExecution.getVariable("humanSurname"));
        adminService.saveHuman(saveHumanReqDTO);
    }
}
