package com.senacbank.pi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.senacbank.pi.service.UsuarioService;
import org.springframework.stereotype.Controller;

public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String telaLogin(){
         return "login"; // Retorna a view de login
    }

    @PostMapping("/login")
    public String fazerLogin(@RequestParam String email, @RequestParam String senha, RedirectAttributes redirectAttributes) {
        // Chama o serviço para verificar as credenciais do usuário
        boolean loginValido = usuarioService.validarLogin(email, senha);
        if (loginValido) {
            return "redirect:/index"; // Redireciona para a página inicial após o login
        } 
        redirectAttributes.addFlashAttribute("mensagem", "Email ou senha inválidos!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-erro");
        return "redirect:/login"; // Redireciona para a página de login em caso de falha
    }   

}

