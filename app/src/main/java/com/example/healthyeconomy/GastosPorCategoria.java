package com.example.healthyeconomy;

import android.widget.Button;



public class GastosPorCategoria {
    private String idUtilizador;
    private String categoria;
    private String descricaoCategoria;
    private String limiteCategoria;
    private String dataCategoria;


    public GastosPorCategoria(){

    }

    public String getIdUtilizador() {
        return idUtilizador;
    }

    public void setIdUtilizador(String idUtilizador) {
        this.idUtilizador = idUtilizador;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricaoCategoria() {
        return descricaoCategoria;
    }

    public void setDescricaoCategoria(String descricaoCategoria) {
        this.descricaoCategoria = descricaoCategoria;
    }

    public String getLimiteCategoria() {
        return limiteCategoria;
    }

    public void setLimiteCategoria(String limiteCategoria) {
        this.limiteCategoria = limiteCategoria;
    }

    public String getDataCategoria() {
        return dataCategoria;
    }

    public void setDataCategoria(String dataCategoria) {
        this.dataCategoria = dataCategoria;
    }
}
