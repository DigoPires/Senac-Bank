package com.senacbank.pi.model;

import java.time.LocalDateTime;

public class Caixinha {
    private double saldo;             
    private double rendimentoAcumulado;  
    private LocalDateTime ultimaAtualizacao;

    public Caixinha() {
        this.saldo = 0;
        this.rendimentoAcumulado = 0;
        this.ultimaAtualizacao = LocalDateTime.now();
    }

    public Caixinha(double saldoInicial) {
        this.saldo = saldoInicial;
        this.rendimentoAcumulado = 0;
        this.ultimaAtualizacao = LocalDateTime.now();
    }

    public void aplicarRendimento() {
        double taxaRendimento = saldo * 0.05;
        this.saldo += taxaRendimento;
        this.rendimentoAcumulado += taxaRendimento;
        this.ultimaAtualizacao = LocalDateTime.now();
    }

    public boolean depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            return true;
        }
        return false;
    }

    public boolean sacar(double valor) {
        if (valor > 0 && valor <= this.saldo) {
            this.saldo -= valor;
            return true;
        }
        return false;
    }
    

    // Getters e setters

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getRendimentoAcumulado() {
        return rendimentoAcumulado;
    }

    public LocalDateTime getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }
}
