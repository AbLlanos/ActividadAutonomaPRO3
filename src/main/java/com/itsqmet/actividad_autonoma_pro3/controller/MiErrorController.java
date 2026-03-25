// MiErrorController.java — clase separada
package com.itsqmet.actividad_autonoma_pro3.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MiErrorController implements ErrorController {

    @RequestMapping("/error")
    public String manejarError(HttpServletRequest request) {
        return "error/404";
    }
}
