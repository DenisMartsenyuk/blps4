package ru.lab.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bpls_human")
@Data
public class Human {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @ManyToMany(mappedBy = "directors")
    private List<Movie> moviesDirector;

    @ManyToMany(mappedBy = "writers")
    private List<Movie> moviesWriter;

    @ManyToMany(mappedBy = "actors")
    private List<Movie> moviesActor;

}
