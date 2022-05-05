package ru.lab.activemq;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import ru.lab.dto.SendingEmailDTO;
import ru.lab.service.EmailService;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

@Component
@RequiredArgsConstructor
public class SubscriberMail {

    private final EmailService emailService;

    @JmsListener(destination = "top_films")
    public void receive(ObjectMessage message) throws JMSException {
        SendingEmailDTO sendingEmailDTO = (SendingEmailDTO) message.getObject();
        emailService.send(sendingEmailDTO);
    }

}
