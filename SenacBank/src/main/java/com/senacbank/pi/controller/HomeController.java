package com.senacbank.pi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/index")
    public String home() {
        return "index"; // ou "index" se você tiver um arquivo index.html/jsp
    }
}
