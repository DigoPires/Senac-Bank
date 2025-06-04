package com.senacbank.pi.model;

import java.time.LocalDateTime;

public class Extrato {
    private String tipo;
    private double valor;
    private LocalDateTime dataHora;
    private String participante;
    private static Long contador = 0L;
    private Long idTransacao;

    public Extrato(String tipo, double valor, LocalDateTime dataHora, String participante) {
        this.tipo = tipo;
        this.valor = valor;
        this.dataHora = dataHora;
        this.participante = participante;
        this.idTransacao = ++contador;
    }

    // Getters
    public String getTipo() { return tipo; }
    public double getValor() { return valor; }
    public LocalDateTime getDataHora() { return dataHora; }
    public String getParticipante() { return participante; }
    public Long getIdTransacao() { return idTransacao; }
}
