package com.laayouni.studentmanagement;

import com.laayouni.studentmanagement.entities.Student;
import com.laayouni.studentmanagement.repositories.StudentRepository;
import com.laayouni.studentmanagement.util.Genre;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class StudentManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentManagementApplication.class, args);
    }


    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            studentRepository.save(new Student(null,"lastNam1","firstName1","mail@mail.com",new Date(), Genre.MALE,true));
            studentRepository.save(new Student(null,"name2","firstName2","test@gmail.com",new Date(), Genre.FEMALE,false));
        };
    }
}
