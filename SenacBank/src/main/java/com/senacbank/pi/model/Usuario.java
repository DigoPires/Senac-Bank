package com.senacbank.pi.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private double saldo;
    private LocalDateTime dataCadastro;
    private List<Extrato> extrato = new ArrayList<>();
    private Caixinha caixinha;  // só uma caixinha

    public Usuario() {
        this.dataCadastro = LocalDateTime.now();
        this.caixinha = new Caixinha(); // inicializa a caixinha padrão
    }

    public Usuario(Long id, String nome, String email, String senha, LocalDateTime dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.saldo = 0;
        this.dataCadastro = dataCadastro;
        this.caixinha = new Caixinha(); // inicializa aqui também
    }

    // Extrato
    public List<Extrato> getExtrato() {
        return extrato;
    }

    public void adicionarExtrato(Extrato extrato) {
        this.extrato.add(extrato);
    }

    // Caixinha - única
    public Caixinha getCaixinha() {
        return caixinha;
    }

    public void setCaixinha(Caixinha caixinha) {
        this.caixinha = caixinha;
    }

    // Getters e Setters gerais
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
