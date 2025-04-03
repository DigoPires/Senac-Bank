package com.senacbank.pi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import com.senacbank.pi.model.Usuario;

@Service

public class UsuarioService {
    private final List <Usuario> usuarios = new ArrayList<>();

    private final AtomicLong contador = new AtomicLong(1);

    public Usuario cadastrar(Usuario usuario) {
        usuario.setId(contador.getAndIncrement());
        usuarios.add(usuario);
        return usuario;
    }

    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios);
    }

    public boolean validarLogin(String email, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

}

