package com.example.healthyeconomy;

import android.util.Log;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class LimiteMensal {

    private String idLimiteMensal;
    private String valorLimite;
    private Button inserirDespesas;
    private Button salvarLimite;



    public LimiteMensal(){
        DatabaseReference reference = ConfiguracaoFirebase.getFirebase().child("limite mensal");
        setIdLimiteMensal(reference.push().getKey());
    }
    public void guardarLimite(){
        FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        String idUtilizador = Base64Custom.condificarBase64(autenticacao.getCurrentUser().getEmail());

        DatabaseReference firebase = ConfiguracaoFirebase.getFirebase();
            firebase.child("Limite Mensal")
                    .child(idUtilizador)
                    .child(idLimiteMensal)
                    .setValue(this);
    }

    public String getIdLimiteMensal() {
        return idLimiteMensal;
    }

    public void setIdLimiteMensal(String idLimiteMensal) {
        this.idLimiteMensal = idLimiteMensal;
    }

    public String getValorLimite() {
        return valorLimite;
    }

    public void setValorLimite(String valorLimite) {
        this.valorLimite = valorLimite;
    }

    public Button getInserirDespesas() {
        return inserirDespesas;
    }

    public void setInserirDespesas(Button inserirDespesas) {
        this.inserirDespesas = inserirDespesas;
    }

    public Button getSalvarLimite() {
        return salvarLimite;
    }

    public void setSalvarLimite(Button salvarLimite) {
        this.salvarLimite = salvarLimite;
    }
}
