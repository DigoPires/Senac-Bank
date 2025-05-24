package com.senacbank.pi.model;

import java.time.LocalDateTime;

public class Caixinha {
    private double saldo;             
    private double rendimentoAcumulado;  
    private double taxaRendimento = 0.5; 
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
        double rendimento = saldo * taxaRendimento;
        this.saldo += rendimento;
        this.rendimentoAcumulado += rendimento;
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

    public double getTaxaRendimento() {
        return taxaRendimento;
    }

    public void setTaxaRendimento(double taxaRendimento) {
        this.taxaRendimento = taxaRendimento;
    }

    public LocalDateTime getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }
}
