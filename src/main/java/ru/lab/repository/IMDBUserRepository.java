package ru.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.model.IMDBUser;

import java.util.List;
import java.util.Optional;

public interface IMDBUserRepository extends JpaRepository<IMDBUser, String> {
    Optional<IMDBUser> findIMDBUserByLogin(String login);

    List<IMDBUser> getAllByEmailIsNotNull();
}
