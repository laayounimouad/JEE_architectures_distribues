package com.laayouni.patientsmvc.security.service;

import com.laayouni.patientsmvc.security.entities.AppRole;
import com.laayouni.patientsmvc.security.entities.AppUser;
import com.laayouni.patientsmvc.security.repositories.AppRoleRepository;
import com.laayouni.patientsmvc.security.repositories.AppUserRepository;
import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class SecurityServiceImpl implements SecurityService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public AppUser saveNewUser(String username, String password, String rePassword) {
        if(!password.equals(rePassword)) throw new RuntimeException("Passwords not match");
        String hashedPWD = passwordEncoder.encode(password);
        AppUser appUser = new AppUser();
        appUser.setUserId(UUID.randomUUID().toString());
        appUser.setUsername(username);
        appUser.setPassword(hashedPWD);
        appUser.setActive(true);
        AppUser savedAppUser = appUserRepository.save(appUser);
        return savedAppUser;
    }

    @Override
    public AppRole saveNewRole(String role, String description) {
        AppRole appRole = appRoleRepository.findByRoleName(role);
        if(appRole!=null) throw new RuntimeException("Role"+role+"already exist");
        appRole=new AppRole();
        appRole.setRoleName(role);
        appRole.setDescription(description);
        AppRole saved = appRoleRepository.save(appRole);
        return saved;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser= appUserRepository.findByUsername(username);
        if(appUser!=null) throw new RuntimeException("User not found");
        AppRole appRole= appRoleRepository.findByRoleName(roleName);
        if(appRole!=null) throw new RuntimeException("Role not found");
        appUser.getAppRoles().add(appRole);
    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {
        AppUser appUser= appUserRepository.findByUsername(username);
        if(appUser!=null) throw new RuntimeException("User not found");
        AppRole appRole= appRoleRepository.findByRoleName(roleName);
        if(appRole!=null) throw new RuntimeException("Role not found");
        appUser.getAppRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUserName(String username) {
        return appUserRepository.findByUsername(username);
    }
}
