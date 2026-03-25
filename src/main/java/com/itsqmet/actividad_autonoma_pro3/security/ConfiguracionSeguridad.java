package com.itsqmet.actividad_autonoma_pro3.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Clase de configuración de Spring Security para la aplicación.
 * Define las reglas de acceso a las rutas, el formulario de login,
 * el logout y el codificador de contraseñas.
 */
@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad {

    /**
     * Define la cadena de filtros de seguridad que controla el acceso a las rutas.
     *
     * <p>Rutas públicas (sin autenticación): página principal, login, registro,
     * recursos estáticos (CSS, JS, imágenes) y formulario de cliente.</p>
     *
     * <p>Rutas protegidas (requieren autenticación): facturas, proveedores,
     * productos, clientes y menú principal.</p>
     *
     * @param httpSecurity Objeto de configuración de seguridad HTTP de Spring.
     * @return La cadena de filtros de seguridad construida.
     * @throws Exception Si ocurre un error durante la configuración de seguridad.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(auth -> auth

                        // ── Rutas públicas accesibles sin autenticación ──
                        .requestMatchers(
                                "/",
                                "/login",
                                "/registro",
                                "/imagenes/**",   // Recursos de imágenes
                                "/css/**",        // Hojas de estilo
                                "/error",         // Página de error personalizada
                                "/js/**",         // Scripts JavaScript
                                "/static/**",     // Recursos estáticos generales
                                "/irFormCliente", // Formulario de registro de cliente
                                "/cliente/**"     // Rutas del módulo de clientes
                        ).permitAll()

                        // ── Rutas protegidas que requieren sesión activa ──
                        .requestMatchers(
                                "/factura/**",
                                "/proveedor/**",
                                "/producto/**",
                                "/cliente/**",
                                "/menu"
                        ).authenticated()

                        // Cualquier otra ruta también requiere autenticación
                        .anyRequest().authenticated()

                // ── Configuración del formulario de login ──
        ).formLogin(login -> login
                        .loginPage("/login")                    // Página personalizada de login
                        .defaultSuccessUrl("/menu", true)       // Redirige al menú tras login exitoso
                        .loginProcessingUrl("/login")           // URL que procesa las credenciales
                        .permitAll()

                // ── Configuración del logout ──
        ).logout(logout -> logout
                .logoutUrl("/logout")                   // URL para cerrar sesión
                .logoutSuccessUrl("/login?logout")      // Redirige al login con mensaje de logout
                .permitAll()
        );

        return httpSecurity.build();
    }

    /**
     * Define el codificador de contraseñas usando el algoritmo BCrypt.
     * BCrypt aplica un hash seguro con sal aleatoria, lo que protege
     * las contraseñas almacenadas en la base de datos ante ataques de fuerza bruta.
     * @return Instancia de BCryptPasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}