package com.example.healthyeconomy;

import android.widget.Button;

//import com.google.firebase.database.DatabaseReference;

public class LimiteMensal {

    private String id;
    private String getId;
    private String mes;
    private String valor;
    private Button inserirLimite;
    private Button alterarLimite;
    private Button visualizarLimite;

    public void inserir(){
        //DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        //referenciaFirebase.child("Limite Mensal").child(getId()).setValue(this);
    }

    public LimiteMensal(){

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGetId() {
        return getId;
    }

    public void setGetId(String getId) {
        this.getId = getId;
    }

    public String getValor() {
        return valor;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Button getInserirLimite() {
        return inserirLimite;
    }

    public void setInserirLimite(Button inserirLimite) {
        this.inserirLimite = inserirLimite;
    }

    public Button getAlterarLimite() {
        return alterarLimite;
    }

    public void setAlterarLimite(Button alterarLimite) {
        this.alterarLimite = alterarLimite;
    }

    public Button getVisualizarLimite() {
        return visualizarLimite;
    }

    public void setVisualizarLimite(Button visualizarLimite) {
        this.visualizarLimite = visualizarLimite;
    }
}
