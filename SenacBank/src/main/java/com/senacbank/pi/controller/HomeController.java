package com.senacbank.pi.controller;

import java.util.List;
import java.util.Locale;
import java.text.NumberFormat;
import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.senacbank.pi.model.Caixinha;
import com.senacbank.pi.model.Usuario;
import com.senacbank.pi.service.UsuarioService;

@Controller
public class HomeController {

    Locale localePtBr = new Locale.Builder().setLanguage("pt").setRegion("BR").build();


    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String homeRoot() {
        return "redirect:/login";
    }

    @GetMapping("/index")
    public String home(Model model, @SessionAttribute("usuario") Usuario usuarioLogado) {
        List<Usuario> usuarios = usuarioService.buscarTodos();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("usuario", usuarioLogado);
        return "index";
    }


    @GetMapping("/depositar")
    public String telaDepositar(Model model, @SessionAttribute("usuario") Usuario usuarioLogado) {
        List<Usuario> usuarios = usuarioService.buscarTodos();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("usuario", usuarioLogado);
        return "View/telaDepositar";
    }

    @PostMapping("/depositar")
    public String depositar(@SessionAttribute("usuario") Usuario usuarioLogado, double valor, RedirectAttributes redirectAttributes) {
        Boolean deposito = usuarioService.depositar(usuarioLogado, valor);

        if (deposito) {
            NumberFormat nf = NumberFormat.getCurrencyInstance(localePtBr);
            redirectAttributes.addFlashAttribute("mensagem", "Depósito realizado com sucesso! Valor Depositado: " + nf.format(valor));
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            return "redirect:/index";
        }
        redirectAttributes.addFlashAttribute("mensagem", "Erro ao realizar o depósito.");
        redirectAttributes.addFlashAttribute("alertClass", "alert-erro");

        return "redirect:/depositar";
    }

    @GetMapping("/sacar")
    public String telaSacar(Model model, @SessionAttribute("usuario") Usuario usuarioLogado) {
        List<Usuario> usuarios = usuarioService.buscarTodos();
        model.addAttribute("usuarios", usuarios);        
        model.addAttribute("usuario", usuarioLogado);
        return "View/telaSacar";
    }

    @PostMapping("/sacar")
    public String sacar(@SessionAttribute("usuario") Usuario usuarioLogado, double valor, String senha, RedirectAttributes redirectAttributes) {
        Boolean saque = usuarioService.sacar(usuarioLogado, valor, senha);

        if (saque) {
            NumberFormat nf = NumberFormat.getCurrencyInstance(localePtBr);
            redirectAttributes.addFlashAttribute("mensagem", "Saque realizado com sucesso! Valor Retirado: " + nf.format(valor));
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            return "redirect:/index";
        }
        redirectAttributes.addFlashAttribute("mensagem", "Erro ao realizar o saque.");
        redirectAttributes.addFlashAttribute("alertClass", "alert-erro");

        return "redirect:/sacar";
    }

    @GetMapping("/transferir")
    public String telaTransferir(Model model, @SessionAttribute("usuario") Usuario usuarioLogado) {
        List<Usuario> usuarios = usuarioService.buscarTodos();
        model.addAttribute("usuarios", usuarios);        
        model.addAttribute("usuario", usuarioLogado);
        return "View/telaTransferir";
    }

    @PostMapping("/transferir")
    public String transferir(@SessionAttribute("usuario") Usuario usuarioLogado, Long idEnvio, double valor, String senha, RedirectAttributes redirectAttributes) {
        Boolean transferencia = usuarioService.transferir(usuarioLogado, idEnvio, valor, senha);
        Usuario usuarioDestino = usuarioService.buscarPorId(idEnvio);

        if (transferencia) {
            NumberFormat nf = NumberFormat.getCurrencyInstance(localePtBr);
            redirectAttributes.addFlashAttribute("mensagem", "Transferência realizada com sucesso! Valor Enviado: " + nf.format(valor) + " - Para: " + usuarioDestino.getNome());
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            return "redirect:/index";
        }
        redirectAttributes.addFlashAttribute("mensagem", "Erro ao realizar a transferência.");
        redirectAttributes.addFlashAttribute("alertClass", "alert-erro");

        return "redirect:/transferir";
    }

    @GetMapping("/caixinha")
    public String telaCaixinha(Model model, @SessionAttribute("usuario") Usuario usuarioLogado) {
        Caixinha caixinha = usuarioLogado.getCaixinha();
        model.addAttribute("caixinha", caixinha);

        List<Usuario> usuarios = usuarioService.buscarTodos();
        model.addAttribute("usuarios", usuarios);        
        model.addAttribute("usuario", usuarioLogado);
        return "View/telaCaixinha";
    }

    @PostMapping("/caixinha")
    public String caixinha(@SessionAttribute("usuario") Usuario usuarioLogado, double valor, String tipoContribuicao, String senha, RedirectAttributes redirectAttributes) {
        String caixinha = usuarioService.contribuirCaixinha(usuarioLogado, valor, tipoContribuicao, senha);

        if (caixinha == "Adicionado") {
            NumberFormat nf = NumberFormat.getCurrencyInstance(localePtBr);
            redirectAttributes.addFlashAttribute("mensagem", "Depósito na caixinha realizado com sucesso! Valor Contribuído: " + nf.format(valor));
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            return "redirect:/index";
        }

        else if (caixinha == "Retirado"){
            NumberFormat nf = NumberFormat.getCurrencyInstance(localePtBr);
            redirectAttributes.addFlashAttribute("mensagem", "Saque da caixinha realizada com sucesso! Valor Retirado: " + nf.format(valor));
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            return "redirect:/index";
        }

        redirectAttributes.addFlashAttribute("mensagem", "Erro ao realizar a contribuição para a caixinha.");
        redirectAttributes.addFlashAttribute("alertClass", "alert-erro");

        return "redirect:/caixinha";
    }

    @GetMapping("/duvidas")
public String telaDuvidas(Model model, @SessionAttribute("usuario") Usuario usuarioLogado) {
    List<Usuario> usuarios = usuarioService.buscarTodos();
    model.addAttribute("usuarios", usuarios);        
    model.addAttribute("usuario", usuarioLogado);

    return "View/duvidas";
}

    @GetMapping("/logout")
    public String logout(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("mensagem", "Usuário deslogado!");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/login";
    }
}