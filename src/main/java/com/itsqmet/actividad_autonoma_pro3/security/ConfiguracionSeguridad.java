package com.itsqmet.actividad_autonoma_pro3.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad {

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception{

        httpSecurity.authorizeHttpRequests(auth -> auth

                .requestMatchers(
                        "/",
                        "/login",
                        "/registro",
                        "/imagenes/**",
                        "/css/**",
                        "/error",
                        "/js/**",
                        "/static/**",
                        "/irFormCliente",
                        "/cliente/**"
                ).permitAll()

                .requestMatchers("/factura/**","/proveedor/**","/producto/**","/cliente/**","/menu").authenticated()
                .anyRequest().authenticated()

        ). formLogin( login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/menu", true)
                .loginProcessingUrl("/login")
                .permitAll()
        ). logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
        );
                return httpSecurity.build();

    };

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    };


}
