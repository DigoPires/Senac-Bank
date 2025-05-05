package com.senacbank.pi.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.senacbank.pi.model.Usuario;

@Service

public class UsuarioService {
    private final List <Usuario> usuarios = new ArrayList<>();

    private final AtomicLong contador = new AtomicLong(1);

    public Usuario cadastrar(Usuario usuario) {
        // Verifica se já existe um usuário com o mesmo e-mail
        boolean emailExistente = usuarios.stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(usuario.getEmail()));

        if (emailExistente) {
            return null;
        }

        // Formata o nome capitalizando a primeira letra de cada palavra
        String nomeFormatado = capitalizeFirstLetterOfEachWord(usuario.getNome());
        usuario.setNome(nomeFormatado);

        usuario.setId(contador.getAndIncrement());
        usuarios.add(usuario);
        return usuario;
    }

    private String capitalizeFirstLetterOfEachWord(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return Arrays.stream(text.split("[\\s\\-_]+")) // Divide por espaços, hífens OU underscores
                .map(word -> {
                    if (word.isEmpty()) {
                        return word; // Mantém palavras vazias
                    }
                    return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
                })
                .collect(Collectors.joining(" ")); // Junta novamente com espaços
    }

    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios);
    }

    public Usuario validarLogin(String email, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }

    public Boolean depositar(Usuario usuario, double valor) {
        if (valor > 0 && valor < 1000000000000000L) {
            usuario.setSaldo(valor + usuario.getSaldo());
            return true;
        }

        return false;
    }

}

