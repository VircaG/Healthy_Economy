package com.example.healthyeconomy;

import android.widget.Button;

//import com.google.firebase.database.DatabaseReference;

public class Questionario {
    private String id;
    private String spinner;
    private String pergunta1;
    private String pergunta2;
    private String pergunta3;
    private String resposta1;
    private String resposta2;
    private String resposta3;
    private Button enviarQuest;
    private Button editarQuest;
    private Button visualizarQuest;

    public void enviar(){
//        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
//        referenciaFirebase.child("Questionário").child(getId()).setValue(this);
    }

    public Questionario(){

    }


    public String getResposta1() {
        return resposta1;
    }

    public void setResposta1(String resposta1) {
        this.resposta1 = resposta1;
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

    public String getPergunta1() {
        return pergunta1;
    }

    public void setPergunta1(String pergunta1) {
        this.pergunta1 = pergunta1;
    }

    public String getPergunta2() {
        return pergunta2;
    }

    public void setPergunta2(String pergunta2) {
        this.pergunta2 = pergunta2;
    }

    public String getPergunta3() {
        return pergunta3;
    }

    public void setPergunta3(String pergunta3) {
        this.pergunta3 = pergunta3;
    }

    public String getResposta2() {
        return resposta2;
    }

    public void setResposta2(String resposta2) {
        this.resposta2 = resposta2;
    }

    public String getResposta3() {
        return resposta3;
    }

    public void setResposta3(String resposta3) {
        this.resposta3 = resposta3;
    }

    public Button getEnviarQuest() {
        return enviarQuest;
    }

    public void setEnviarQuest(Button enviarQuest) {
        this.enviarQuest = enviarQuest;
    }

    public Button getEditarQuest() {
        return editarQuest;
    }

    public void setEditarQuest(Button editarQuest) {
        this.editarQuest = editarQuest;
    }

    public Button getVisualizarQuest() {
        return visualizarQuest;
    }

    public void setVisualizarQuest(Button visualizarQuest) {
        this.visualizarQuest = visualizarQuest;
    }
}
