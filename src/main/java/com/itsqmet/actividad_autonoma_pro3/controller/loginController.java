package com.itsqmet.actividad_autonoma_pro3.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {

    @GetMapping("/login")
    public String login(){
        return "/pages/login";
    }

    @GetMapping("/menu")
    public String menu(){
        return "/pages/MenuCliente";
    }

    @GetMapping("/error")
    public String manejarError(HttpServletRequest request) {
        return "error/404";
    }

}
