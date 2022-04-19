package com.laayouni.studentmanagement.security.service;

import com.laayouni.studentmanagement.security.entites.AppRole;
import com.laayouni.studentmanagement.security.entites.AppUser;

public interface SecurityService {

    AppUser saveNewUser(String userName,String password,String rePassword);
    AppRole saveNewRole(String role,String description);
    void addRoleToUser(String username,String roleName);
    void removeRoleFromUser(String username,String roleName);
    AppUser loadUserByUserName(String username);
}
