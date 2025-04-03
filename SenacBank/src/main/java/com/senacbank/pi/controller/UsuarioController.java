package com.senacbank.pi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.senacbank.pi.model.Usuario;
import com.senacbank.pi.service.UsuarioService;
import org.springframework.ui.Model;

public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/cadastro")
    public String telaCadastro(Model model) {
        // Adiciona o objeto Usuario ao modelo para ser utilizado na view
        model.addAttribute("usuario", new Usuario());
        return "cadastro"; // Retorna a view de cadastro
    }

    @PostMapping("/cadastro")
    public String cadastro(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttributes) {
        // Chama o serviço para salvar o usuário no banco de dados
        usuarioService.cadastrar(usuario);
        // Adiciona uma mensagem de sucesso ao redirecionar para a página inicial
        redirectAttributes.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/cadastro"; // Redireciona para a página inicial após o cadastro
    }

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        // Chama o serviço para obter a lista de usuários
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "listar-usuarios"; // Retorna a view de listagem de usuários
    }
}
