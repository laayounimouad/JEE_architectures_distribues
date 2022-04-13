package com.laayouni.patientsmvc;

import com.laayouni.patientsmvc.entities.Patient;
import com.laayouni.patientsmvc.repositories.PatientRepository;
import com.laayouni.patientsmvc.security.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientsMvcApplication.class, args);
    }



    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(new Patient(null,"hassan",new Date(),false,130));
            patientRepository.save(new Patient(null,"patient2",new Date(),true,150));
            patientRepository.save(new Patient(null,"patient3",new Date(),false,200));
            patientRepository.findAll().forEach(patient -> {
                System.out.println(patient.getNom());
            });
        };
    }

    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("test1","1234","1234");
            securityService.saveNewUser("mouad","1234","1234");
            securityService.saveNewUser("hassan","1234","1234");

            securityService.saveNewRole("ADMIN","");
            securityService.saveNewRole("USER","");

            securityService.addRoleToUser("test1","ADMIN");
            securityService.addRoleToUser("hassan","USER");
            securityService.addRoleToUser("mouad","ADMIN");
        };
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
