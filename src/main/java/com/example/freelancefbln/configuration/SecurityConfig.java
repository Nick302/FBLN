package com.example.freelancefbln.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.example.freelancefbln.entity.Role.ADMIN;


@Configuration
@EnableMethodSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity security) throws Exception {
        security
                .csrf().disable()
                .authorizeHttpRequests(urlConfig -> urlConfig
                        .antMatchers("/login", "/v3/api-docs/**", "/swagger-ui/**", "/users/**").permitAll()
                        .antMatchers("/api/v1/user/**").hasAuthority(ADMIN.getAuthority())
                        .antMatchers("/users/{\\d+}/delete").hasAuthority(ADMIN.getAuthority())
                        .antMatchers("/admin/**").hasAuthority(ADMIN.getAuthority())
                        .anyRequest().authenticated())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .deleteCookies("JSESSIONID"))
                .formLogin(login -> login.loginPage("/login")
                                        .defaultSuccessUrl("/index")
                                        .permitAll());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
