package com.laayouni.jpaactivity21.repositories;

import com.laayouni.jpaactivity21.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    List<Patient> findByMalade(boolean a);
    Page<Patient> findByMalade(boolean a, Pageable pageable);
    List<Patient> findByMaladeAndScoreIsLessThan(boolean a,int score);

    @Query("Select p from Patient p where p.name like :x and p.score<:y")
    List<Patient> chercherPatients(@Param("x") String nom,@Param("y") int scoreMin);
}
