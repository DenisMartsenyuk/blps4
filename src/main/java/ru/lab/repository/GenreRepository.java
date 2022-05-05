package ru.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.model.Genre;

import java.util.List;
import java.util.Set;

public interface GenreRepository extends JpaRepository<Genre, String> {
    Set<Genre> findGenresByNameIn(List<String> names);
}
