package com.example.healthyeconomy;

import android.widget.Button;

//import com.google.firebase.database.DatabaseReference;

public class GastosPorCategoria {
    private String id;
    private String spinner;
    private String descricao;
    private String valor;
    private String data;
    private Button inserirGastos;
    private Button editarGastos;
    private Button visualizarGastos;


    public void inserir(){
//        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
//        referenciaFirebase.child("Gastos Proprios").child(getId()).setValue(this);
    }

    public GastosPorCategoria(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpinner() {
        return spinner;
    }

    public void setSpinner(String spinner) {
        this.spinner = spinner;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public Button getEditarGastos() {
        return editarGastos;
    }

    public void setEditarGastos(Button editarGastos) {
        this.editarGastos = editarGastos;
    }

    public Button getVisualizarGastos() {
        return visualizarGastos;
    }

    public void setVisualizarGastos(Button visualizarGastos) {
        this.visualizarGastos = visualizarGastos;
    }
}
