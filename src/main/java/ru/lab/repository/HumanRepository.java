package ru.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.model.Human;

import java.util.List;
import java.util.Set;

public interface HumanRepository extends JpaRepository<Human, Long> {
    Set<Human> findHumanByIdIn(List<Long> ids);
}
