package com.example.healthyeconomy;

import android.widget.Button;

//import com.google.firebase.database.DatabaseReference;

public class GastosPorCategoria {
    private String idUtilizador;
    private String spinnerCategorias;
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

    public String getSpinnerCategorias() {
        return spinnerCategorias;
    }

    public void setSpinnerCategorias(String spinnerCategorias) {
        this.spinnerCategorias = spinnerCategorias;
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
