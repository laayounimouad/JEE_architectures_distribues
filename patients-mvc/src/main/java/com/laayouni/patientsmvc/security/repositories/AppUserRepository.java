package com.laayouni.patientsmvc.security.repositories;

import com.laayouni.patientsmvc.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {

    AppUser findByUsername(String username);
}
