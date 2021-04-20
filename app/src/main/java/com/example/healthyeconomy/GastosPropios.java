package com.example.healthyeconomy;

import android.widget.Button;

//import com.google.firebase.database.DatabaseReference;

public class GastosPropios {
    private String id;
    private String spinner;
    private String decricao;
    private String categoria;
    private String valor;
    private String data;
    private Button inserirGastos;
    private Button visualizarGastos;

    public void inserir(){
        //DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        //referenciaFirebase.child("Gastos Proprios").child(getId()).setValue(this);
    }

    public GastosPropios(){

    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSpinner() {
        return spinner;
    }

    public void setSpinner(String spinner) {
        this.spinner = spinner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Button getInserirGastos() {
        return inserirGastos;
    }

    public void setInserirGastos(Button inserirGastos) {
        this.inserirGastos = inserirGastos;
    }

    public Button getVisualizarGastos() {
        return visualizarGastos;
    }

    public void setVisualizarGastos(Button visualizarGastos) {
        this.visualizarGastos = visualizarGastos;
    }


}

