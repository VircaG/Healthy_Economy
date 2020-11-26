package com.example.healthyeconomy;

import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class Utilizador {
    private String id;
    private String Nome;
    private String sobrenome;
    private String senha;
    private int idade;
    private String profissao;
    private String estadoCivil;
    private String contato;
    private String email;
    private String morada;
    private boolean filho;
    private Button btnRegistar;

    public void salvar(){
        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        referenciaFirebase.child("utilizadores").child(getId()).setValue(this);
    }


    public Utilizador() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public boolean isFilho() {
        return filho;
    }

    public void setFilho(boolean filho) {
        this.filho = filho;
    }

    public Button getBtnRegistar() {
        return btnRegistar;
    }

    public void setBtnRegistar(Button btnRegistar) {
        this.btnRegistar = btnRegistar;
    }
}