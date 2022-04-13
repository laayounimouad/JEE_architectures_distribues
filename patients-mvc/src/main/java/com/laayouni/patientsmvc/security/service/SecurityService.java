package com.laayouni.patientsmvc.security.service;

import com.laayouni.patientsmvc.security.entities.AppRole;
import com.laayouni.patientsmvc.security.entities.AppUser;

public interface SecurityService {
    AppUser saveNewUser(String username, String password,String rePassword);
    AppRole saveNewRole(String role, String description);
    void addRoleToUser(String username, String roleName);
    void removeRoleFromUser(String username, String roleName);
    AppUser loadUserByUserName(String username);
}
