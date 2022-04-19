package com.laayouni.studentmanagement.security.repositories;

import com.laayouni.studentmanagement.security.entites.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByRoleName(String roleName);
}
