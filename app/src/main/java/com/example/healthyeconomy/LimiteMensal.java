package com.example.healthyeconomy;

import android.util.Log;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class LimiteMensal {

    private String idUtilizador;
    private String valorLimite;

    public LimiteMensal() {
    }

    public String getIdUtilizador() {
        return idUtilizador;
    }

    public void setIdUtilizador(String idUtilizador) {
        this.idUtilizador = idUtilizador;
    }

    public String getValorLimite() {
        return valorLimite;
    }

    public void setValorLimite(String valorLimite) {
        this.valorLimite = valorLimite;
    }
}
