package com.senacbank.pi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.senacbank.pi.service.UsuarioService;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String telaLogin() {
        return "View/login";
    }    

    @PostMapping("/login")
    public String fazerLogin(
            @RequestParam String email,
            @RequestParam String senha,
            RedirectAttributes redirectAttributes) {

        boolean loginValido = usuarioService.validarLogin(email, senha);

        if (loginValido) {
            return "redirect:/index";
        }

        redirectAttributes.addFlashAttribute("mensagem", "Email ou senha inválidos!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-erro");

        return "redirect:/login";
    }
}
