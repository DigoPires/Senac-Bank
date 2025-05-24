package com.senacbank.pi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.senacbank.pi.model.Caixinha;
import com.senacbank.pi.model.Extrato;
import com.senacbank.pi.model.Usuario;

@Service
public class UsuarioService {

    private final List<Usuario> usuarios = new ArrayList<>();

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    private final AtomicLong contador = new AtomicLong(1);

    public Usuario cadastrar(Usuario usuario) {
        boolean emailExistente = usuarios.stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(usuario.getEmail()));

        if (emailExistente) {
            return null;
        }

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
        return Arrays.stream(text.split("[\\s\\-_]+"))
                .map(word -> {
                    if (word.isEmpty()) {
                        return word;
                    }
                    return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
                })
                .collect(Collectors.joining(" "));
    }

    public List<Usuario> buscarTodos() {
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
            usuario.adicionarExtrato(new Extrato("Depósito", valor, LocalDateTime.now(), usuario.getNome()));
            return true;
        }
        return false;
    }

    public Boolean sacar(Usuario usuario, double valor, String senha) {
        if (usuario.getSaldo() < 1 || !usuario.getSenha().equals(senha)) {
            return false;
        }

        if (valor <= usuario.getSaldo() && (valor > 0 && valor < 1000000000000000L)) {
            usuario.setSaldo(usuario.getSaldo() - valor);
            usuario.adicionarExtrato(new Extrato("Saque", valor, LocalDateTime.now(), usuario.getNome()));
            return true;
        }
        return false;
    }

    public Boolean transferir(Usuario usuario, Long idEnvio, double valor, String senha) {
        if (!usuario.getSenha().equals(senha)) {
            return false;
        }

        if (valor <= 0 || valor > usuario.getSaldo()) {
            return false;
        }

        Usuario usuarioDestino = buscarPorId(idEnvio);

        if (usuarioDestino == null || usuarioDestino == usuario) {
            return false;
        }

        usuario.setSaldo(usuario.getSaldo() - valor);
        usuarioDestino.setSaldo(usuarioDestino.getSaldo() + valor);

        usuario.adicionarExtrato(new Extrato("Transferência Enviada", valor, LocalDateTime.now(), usuarioDestino.getNome()));
        usuarioDestino.adicionarExtrato(new Extrato("Transferência Recebida", valor, LocalDateTime.now(), usuario.getNome()));

        return true;
    }

    
    public boolean criarCaixinha(Usuario usuario) {
        if (usuario.getCaixinha() != null) {
            return false;
        }
        Caixinha novaCaixinha = new Caixinha();
        usuario.setCaixinha(novaCaixinha);
        return true;
    }

    public Boolean depositarNaCaixinha(Usuario usuario, double valor) {
        Caixinha caixinha = usuario.getCaixinha();
        if (caixinha == null) {
            return false;
        }
        if (valor > 0 && valor <= usuario.getSaldo()) {
            caixinha.depositar(valor);
            usuario.setSaldo(usuario.getSaldo() - valor);
            usuario.adicionarExtrato(new Extrato("Adicionado na Caixinha", valor, LocalDateTime.now(), usuario.getNome()));
            return true;
        }
        return false;
    }

    public Boolean sacarDaCaixinha(Usuario usuario, double valor) {
        Caixinha caixinha = usuario.getCaixinha();
        if (caixinha == null || caixinha.getSaldo() < valor) {
            return false;
        }
        if (caixinha.sacar(valor)) {
            usuario.setSaldo(usuario.getSaldo() + valor);
            usuario.adicionarExtrato(new Extrato("Retirado da Caixinha", valor, LocalDateTime.now(), usuario.getNome()));
            return true;
        }
        return false;
    }

    public boolean aplicarRendimentoCaixinha(Usuario usuario) {
        Caixinha caixinha = usuario.getCaixinha();
        if (caixinha == null || usuario.getSaldo() < 1) {
            return false;
        }
        caixinha.aplicarRendimento();
        usuario.adicionarExtrato(new Extrato("Rendimento aplicado na Caixinha",
                caixinha.getTaxaRendimento(), LocalDateTime.now(), usuario.getNome()));
        return true;
    }

    public Boolean contribuirCaixinha(Usuario usuario, double valor, String tipoContribuicao, String senha) {
        if (!usuario.getSenha().equals(senha) || valor < 1 || tipoContribuicao == null) {
            return false;
        }

        if (tipoContribuicao.equalsIgnoreCase("Adicionar")) {
            return depositarNaCaixinha(usuario, valor);
        } 
        else {
            return sacarDaCaixinha(usuario, valor);
        }
    
    }    


    public Usuario buscarPorId(Long id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }

}
