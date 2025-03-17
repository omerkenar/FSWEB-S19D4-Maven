package com.workintech.s19d1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "movie", schema = "fsweb")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotNull(message = "film adi null olamaz(entity message)")
    private String name;

    @Column(name = "director_name")
    @NotNull(message = "yonetmen adi null olamaz(entity message)")
    private String directorName;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @JsonBackReference
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "movie_actor", schema = "fsweb",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> actors;

    public void addActor(Actor actor){
        if(actors == null){
            actors = new ArrayList<>();
        }
        actors.add(actor);
    }
}
