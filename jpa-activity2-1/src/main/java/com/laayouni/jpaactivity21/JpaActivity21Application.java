package com.laayouni.jpaactivity21;

import com.laayouni.jpaactivity21.entities.Patient;
import com.laayouni.jpaactivity21.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class JpaActivity21Application implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaActivity21Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        patientRepository.save(
                new Patient(null,"hassan","mlsdkf@mail",false,34));

        patientRepository.save(
                new Patient(null,"mohammed","mlsdkf@mail",false,34));

        patientRepository.save(
                new Patient(null,"Imane","mlsdkf@mail",true,34));
        List<Patient> patients = patientRepository.findAll();
        patients.forEach(patient -> {
            System.out.println(patient.getName());
        });
        Patient patient = patientRepository.findById(1L).orElse(null);
        if (patient != null) System.out.println(patient);
    }

}
