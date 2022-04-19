package com.laayouni.studentmanagement.security.repositories;

import com.laayouni.studentmanagement.security.entites.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppUserRepository extends JpaRepository<AppUser, String> {

    AppUser findByUsername(String username);
}
