package com.senacbank.pi.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.senacbank.pi.model.Usuario;
import com.senacbank.pi.service.UsuarioService;

@Configuration
public class UsuarioConfig {

    @Bean
    public CommandLineRunner demo(UsuarioService usuarioService) {
        return (args) -> {

            Usuario usuario1 = new Usuario();
            usuario1.setEmail("rodrigo@exemplo");
            usuario1.setSenha("1234");
            usuario1.setNome("Rodrigo");
            usuario1.setSaldo(125020.92);

            usuarioService.cadastrar(usuario1);

            Usuario usuario2 = new Usuario();
            usuario2.setEmail("pedro@exemplo");
            usuario2.setSenha("1234");
            usuario2.setNome("pedro");
            usuario2.setSaldo(16782.04);

            usuarioService.cadastrar(usuario2);
        };
    }
}
