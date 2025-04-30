package com.senacbank.pi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/depositar")
    public String telaDepositar() {
        return "View/telaDepositar";
    }    

    @GetMapping("/sacar")
    public String telaSacar() {
        return "View/telaSacar";
    }    

    @GetMapping("/transferir")
    public String telaTransferir() {
        return "View/telaTransferir";
    }    

}
