package ru.lab.delegates;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import ru.lab.dto.SaveCountryReqDTO;
import ru.lab.service.AdminService;

import javax.inject.Named;

@Named
@RequiredArgsConstructor
public class SaveCountryDelegate implements JavaDelegate {

    private final AdminService adminService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        SaveCountryReqDTO saveCountryReqDTO = new SaveCountryReqDTO();
        saveCountryReqDTO.setName((String) delegateExecution.getVariable("countryName"));
        adminService.saveCountry(saveCountryReqDTO);
    }
}
