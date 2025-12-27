package com.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String redirectToHome() {
        // Redirige al endpoint que maneja la lógica por rol
        return "redirect:/api/usuarios/home";
    }
}
