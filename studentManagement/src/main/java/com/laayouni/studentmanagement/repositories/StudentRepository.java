package com.laayouni.studentmanagement.repositories;

import com.laayouni.studentmanagement.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Page<Student> findByFirstNameContains(String firstname, Pageable pageable);
}
