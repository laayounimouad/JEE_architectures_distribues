package com.laayouni.studentmanagement.security;


import com.laayouni.studentmanagement.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //le mot de passe est par default hasher et encoder
        //{noop} no password encoder
        //utilisation de Bcrypte comme le password encoder :

//        PasswordEncoder passwordEncoder=passwordEncoder();
        /*auth.inMemoryAuthentication()//les utilisateurs qui ont le droit vont etre stocker en memoire
                .withUser("user1").password(passwordEncoder.encode("1234")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder.encode("1234")).roles("USER","ADMIN");

         */
        /*
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username as principal, password as credentials, active from users where username=?")
                .authoritiesByUsernameQuery("select username as principal, role as role from users_role where username=?")
                .rolePrefix("ROLE_")
                .passwordEncoder(passwordEncoder);
         */
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin();
        http.authorizeRequests().antMatchers("/").permitAll();
        //http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
        //quand on utilise
        http.authorizeRequests().antMatchers("/admin/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/user/**").hasAuthority("USER");
        //pour permettre au fichier static sans etre authentifier
        http.authorizeRequests().antMatchers("/webjars/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();//ça veut dire que tout les requetes doivent être authentifier

        //Configurer les exceptions :
        http.exceptionHandling().accessDeniedPage("/403");
    }


}
