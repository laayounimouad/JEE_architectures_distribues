package com.laayouni.jpaactivity21.repositories;

import com.laayouni.jpaactivity21.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {

}
