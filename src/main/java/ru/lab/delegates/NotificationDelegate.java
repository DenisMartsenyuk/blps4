package ru.lab.delegates;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.jms.core.JmsTemplate;
import ru.lab.component.TopMoviesGenerator;

import javax.inject.Named;

@Named
@RequiredArgsConstructor
public class NotificationDelegate implements JavaDelegate {

    private final JmsTemplate jmsTemplate;
    private final TopMoviesGenerator generator;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        jmsTemplate.send("top_films", session -> session.createObjectMessage(generator.generate()));
    }
}
