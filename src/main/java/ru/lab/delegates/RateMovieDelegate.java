package ru.lab.delegates;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import ru.lab.dto.RateMovieReqDTO;
import ru.lab.service.UserService;

import javax.inject.Named;

@Named
@RequiredArgsConstructor
public class RateMovieDelegate implements JavaDelegate {

    private final UserService userService;
    private final IdentityService identityService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long movieId = Long.valueOf((String) delegateExecution.getVariable("movieId"));
        Double movieRate = Double.valueOf((String) delegateExecution.getVariable("movieRate"));
        String login = identityService.getCurrentAuthentication().getUserId();

        RateMovieReqDTO rateMovieReqDTO = new RateMovieReqDTO();
        rateMovieReqDTO.setMovieId(movieId);
        rateMovieReqDTO.setValue(movieRate);
        rateMovieReqDTO.setLogin(login);

        userService.rateMovie(rateMovieReqDTO);

    }
}
