// MiErrorController.java — clase separada
package com.itsqmet.actividad_autonoma_pro3.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador personalizado para el manejo global de errores de la aplicación.
 * Implementa {@link ErrorController} para sobreescribir el comportamiento
 * por defecto de Spring Boot en el manejo de errores HTTP (como 404, 500, etc.).
 */
@Controller
public class MiErrorController implements ErrorController {

    /**
     * Intercepta todas las peticiones que Spring Boot redirige a /error
     * cuando ocurre un fallo en la aplicación (página no encontrada, error interno, etc.)
     * y redirige al usuario a una página de error personalizada.
     * @param request Petición HTTP que contiene el código de error y detalles del fallo.
     * @return Vista de error personalizada (error/404).
     */
    @RequestMapping("/error")
    public String manejarError(HttpServletRequest request) {
        return "error/404";
    }
}