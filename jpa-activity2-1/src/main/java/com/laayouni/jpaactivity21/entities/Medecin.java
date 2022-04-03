package com.laayouni.jpaactivity21.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Medecin {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String speciality;
    @OneToMany(mappedBy = "medecin" ,fetch = FetchType.LAZY)
    private Collection<Appointement> appointements;
}
