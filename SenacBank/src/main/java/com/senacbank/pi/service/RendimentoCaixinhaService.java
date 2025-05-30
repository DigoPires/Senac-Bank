package com.senacbank.pi.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.senacbank.pi.model.Usuario;
import com.senacbank.pi.model.Caixinha;
import java.util.List;

@Service
public class RendimentoCaixinhaService {

    @Autowired
    private UsuarioService usuarioService;

    @Scheduled(fixedRate = 5000)
    public void aplicarRendimentoEmTodasCaixinhas() {

        List<Usuario> usuarios = usuarioService.buscarTodos();
        
        for (Usuario usuario : usuarios) {
            Caixinha caixinha = usuario.getCaixinha();
            if (caixinha != null && caixinha.getSaldo() > 0.99) {
                caixinha.aplicarRendimento();
            }
        }
    }
}

