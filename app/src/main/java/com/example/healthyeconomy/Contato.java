package com.example.healthyeconomy;

public class Contato {
    private String identificadorUtilizador;
    private String nome;
    private String profissao;
    private String email;

    public Contato() {
    }

    public String getIdentificadorUtilizador() {
        return identificadorUtilizador;
    }

    public void setIdentificadorUtilizador(String identificadorUtilizador) {
        this.identificadorUtilizador = identificadorUtilizador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
