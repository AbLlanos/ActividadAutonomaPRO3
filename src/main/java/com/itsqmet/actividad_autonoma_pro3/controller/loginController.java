package com.itsqmet.actividad_autonoma_pro3.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador MVC que gestiona las rutas generales de navegación de la aplicación,
 * incluyendo el login, el menú principal y el manejo de errores.
 */
@Controller
public class loginController {

    /**
     * Muestra la página de inicio de sesión.
     * Spring Security intercepta esta ruta para procesar las credenciales del usuario.
     * @return Vista del formulario de login (pages/login).
     */
    @GetMapping("/login")
    public String login() {
        return "/pages/login";
    }

    /**
     * Muestra el menú principal del cliente tras autenticarse correctamente.
     * @return Vista del menú principal (pages/MenuCliente).
     */
    @GetMapping("/menu")
    public String menu() {
        return "/pages/MenuCliente";
    }

    /**
     * Maneja los errores de la aplicación y redirige a la página 404.
     * Recibe la petición HTTP para poder acceder a detalles del error si es necesario.
     * @param request Petición HTTP que contiene información del error ocurrido.
     * @return Vista de error 404 (error/404).
     */
    @GetMapping("/error")
    public String manejarError(HttpServletRequest request) {
        return "error/404";
    }
}