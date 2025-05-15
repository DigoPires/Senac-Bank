package com.senacbank.pi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.senacbank.pi.model.Usuario;
import com.senacbank.pi.service.UsuarioService;
import org.springframework.ui.Model;


@Controller
public class CadastroController {

    @Autowired
    private UsuarioService usuarioService;

    // Exibe a tela de cadastro
    @GetMapping("/cadastro")
    public String telaCadastro(Model model) {
        List<Usuario> usuarios = usuarioService.buscarTodos();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("usuario", new Usuario());
        return "View/cadastro";
    }

    // Processa o cadastro do usuário
    @PostMapping("/cadastro")
    public String cadastrar(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttributes) {
        Usuario user = usuarioService.cadastrar(usuario);
        
        if(user == null){
            redirectAttributes.addFlashAttribute("mensagem", "ERRO: E-mail já cadastrado!");
            redirectAttributes.addFlashAttribute("alertClass", "alert-erro");
            return "redirect:/cadastro";
        }

        redirectAttributes.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/login";
    }
}
