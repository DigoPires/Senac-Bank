package com.senacbank.pi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.senacbank.pi.model.Usuario;
import com.senacbank.pi.service.UsuarioService;

@Controller
public class HomeController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String homeRoot() {
        return "redirect:/login";
    }

    @GetMapping("/index")
    public String home(Model model, @SessionAttribute("usuario") Usuario usuarioLogado) {
        model.addAttribute("usuario", usuarioLogado);
        return "index";
    }

    @GetMapping("/depositar")
    public String telaDepositar(Model model, @SessionAttribute("usuario") Usuario usuarioLogado) {
        model.addAttribute("usuario", usuarioLogado);
        return "View/telaDepositar";
    }

    @PostMapping("/depositar")
    public String depositar(@SessionAttribute("usuario") Usuario usuarioLogado, double valor, RedirectAttributes redirectAttributes) {
        Boolean deposito = usuarioService.depositar(usuarioLogado, valor);

        if (deposito) {
            redirectAttributes.addFlashAttribute("mensagem", "Depósito realizado com sucesso! Valor Depositado: R$" + String.format("%.2f", valor).replace(".", ","));
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            return "redirect:/index";
        }
        redirectAttributes.addFlashAttribute("mensagem", "Erro ao realizar o depósito.");
        redirectAttributes.addFlashAttribute("alertClass", "alert-erro");

        return "redirect:/depositar";
    }

    @GetMapping("/sacar")
    public String telaSacar() {
        return "View/telaSacar";
    }

    @GetMapping("/transferir")
    public String telaTransferir() {
        return "View/telaTransferir";
    }

    @GetMapping("/logout")
    public String logout(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("mensagem", "Usuário deslogado!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/login";
    }
}