package ru.lab.service;

import ru.lab.dto.SendingEmailDTO;

public interface EmailService {
    void send(SendingEmailDTO sendingEmailDTO);
}
