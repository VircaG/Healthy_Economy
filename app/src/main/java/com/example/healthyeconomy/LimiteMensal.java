package com.example.healthyeconomy;

import android.widget.Button;
import com.example.healthyeconomy.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;



public class LimiteMensal {

    private String idLimite;
    private String mes;
    private Integer valor;
    private Button inserirLimite;
    private Button alterarLimite;
    private Button visualizarLimite;


    public  LimiteMensal(){

    }
//    public LimiteMensal(){
//        DatabaseReference reference = ConfiguracaoFirebase.getFirebase().child("limite mensal");
//        setIdLimite(reference.push().getKey());
//    }
    public void guardar(){
        FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        String idUtilizador = Base64Custom.condificarBase64(autenticacao.getCurrentUser().getEmail());

        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        referenciaFirebase.child("limite mensal")
                .child(idUtilizador)
                .child(idLimite)
                .setValue(this);

    }


    public String getIdLimite() {
        return idLimite;
    }

    public void setIdLimite(String idLimite) {
        this.idLimite = idLimite;
    }

    public String getMes() {
        return mes;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public void setMes(String mes) {
        this.mes = mes;
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
