package com.example.healthyeconomy;

import android.util.Log;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthRegistrar;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class Utilizador {
    private String Nome;
    private String sobrenome;
    private String senha;
    private String idade;
    private String profissao;
    private String estadoCivil;
    private String contato;
    private String email;
    private String morada;
    //private Boolean filho;
    private String idUtilizador;
    private Button btnRegistar;

    public Utilizador() {
    }

    public void salvarUtilizador() {

        FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
       // String identificadorUtilizador = "teste";
        String identificadorUtilizador = Base64Custom.codificarBase64(autenticacao.getCurrentUser().getEmail());
        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();

        referenciaFirebase.child("Utilizadores")
                .child(identificadorUtilizador)
                .setValue(this, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError != null) {
                            //System.out.println("Data could not be saved " + databaseError.getMessage());
                            Log.d("registo","Data could not be saved " + databaseError.getMessage());

                        } else {
                            //System.out.println("Data saved successfully.");
                            Log.d("registo","Data saved successfully.");
                        }
                    }
                });

    }


    //@Exclude
    public String getIdUtilizador() {
        return idUtilizador;
    }

    public void setIdUtilizador(String idUtilizador) {
        this.idUtilizador = idUtilizador;
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

    //@Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
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

    public Button getBtnRegistar() {
        return btnRegistar;
    }

    public void setBtnRegistar(Button btnRegistar) {
        this.btnRegistar = btnRegistar;
    }
}