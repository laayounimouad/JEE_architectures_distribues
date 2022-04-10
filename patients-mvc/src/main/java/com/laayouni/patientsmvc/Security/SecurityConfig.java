package com.laayouni.patientsmvc.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //le mot de passe est par default hasher et encoder
        //{noop} no password encoder
        //utilisation de Bcrypte comme le password encoder :
        PasswordEncoder passwordEncoder=passwordEncoder();
        auth.inMemoryAuthentication()//les utilisateurs qui ont le droit vont etre stocker en memoire
                .withUser("user1").password(passwordEncoder.encode("1234")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder.encode("1234")).roles("USER","ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin();
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/delete/**","/edit/**","/save/**","/formPatients/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/index/**").hasRole("USER");
        //http.authorizeRequests().anyRequest().authenticated();//ça veut dire que tout les requetes doivent être authentifier
        //Configurer les exceptions :
        http.exceptionHandling().accessDeniedPage("/403");
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
