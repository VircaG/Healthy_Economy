package com.example.healthyeconomy;

import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;



public class GastosPropios {

    private String idUtilizador;
    private String spinnerCategoria;
    private String decricao;
    private String valor;
    private String data;

    public GastosPropios() {
    }

    public String getIdUtilizador() {
        return idUtilizador;
    }

    public void setIdUtilizador(String idUtilizador) {
        this.idUtilizador = idUtilizador;
    }

    public String getSpinnerCategoria() {
        return spinnerCategoria;
    }

    public void setSpinnerCategoria(String spinnerCategoria) {
        this.spinnerCategoria = spinnerCategoria;
    }

    public String getDecricao() {
        return decricao;
    }

    public void setDecricao(String decricao) {
        this.decricao = decricao;
    }



    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

