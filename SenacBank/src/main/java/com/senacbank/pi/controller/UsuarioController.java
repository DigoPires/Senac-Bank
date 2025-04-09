package com.senacbank.pi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.senacbank.pi.model.Usuario;
import com.senacbank.pi.service.UsuarioService;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Exibe a tela de cadastro
    @GetMapping("/cadastro")
    public String telaCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "View/cadastro"; // Caminho da view HTML (templates/View/cadastro.html)
    }

    // Processa o cadastro do usuário
    @PostMapping("/cadastro")
    public String cadastrar(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttributes) {
        usuarioService.cadastrar(usuario);
        redirectAttributes.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/login"; // Redireciona para a própria tela de cadastro
    }

    // Lista os usuários cadastrados
    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "View/listar-usuarios"; // Corrigido para incluir a pasta "View" na view
    }
}
