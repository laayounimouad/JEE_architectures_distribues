package com.laayouni.jpaactivity21.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private boolean malade;
    private int score;
    //@OneToMany(mappedBy = "patient")
    //Collection<Appointement> appointements;
}
