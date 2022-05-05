package ru.lab.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import ru.lab.component.TopInformationGenerator;
import ru.lab.dto.SendingEmailDTO;
import ru.lab.service.EmailService;

@Component
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    private final TopInformationGenerator topInformationGenerator;

    private void sendEmail(String toAddress, String subject, String message) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toAddress);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        emailSender.send(simpleMailMessage);
    }


    @Override
    public void send(SendingEmailDTO sendingEmailDTO) {
        if (sendingEmailDTO.getEmails().isEmpty() || sendingEmailDTO.getMovies().isEmpty()) {
            return;
        }
        String information = topInformationGenerator.generate(sendingEmailDTO.getMovies());
        System.out.println(information);
        sendingEmailDTO.getEmails().forEach(x -> sendEmail(x, "Самые популярные фильмы недели!", information));
    }
}
