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

            Usuario usuario = new Usuario();
            usuario.setEmail("rodrigo@exemplo");
            usuario.setSenha("1234");
            usuario.setNome("Rodrigo");
            usuario.setSaldo(125020.92);

            usuarioService.cadastrar(usuario);
        };
    }
}
