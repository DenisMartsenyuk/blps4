package ru.lab.delegates;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import ru.lab.dto.SaveGenreReqDTO;
import ru.lab.service.AdminService;

import javax.inject.Named;

@Named
@RequiredArgsConstructor
public class SaveGenreDelegate implements JavaDelegate {

    private final AdminService adminService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        SaveGenreReqDTO saveGenreReqDTO = new SaveGenreReqDTO();
        saveGenreReqDTO.setName((String) delegateExecution.getVariable("genreName"));
        adminService.saveGenre(saveGenreReqDTO);
    }
}
