package com.example.healthyeconomy;

public class Chat {
    private String idUtilizadorChat;
    private String nome;
    private String mensagem;

    public Chat() {
    }

    public String getIdUtilizadorChat() {
        return idUtilizadorChat;
    }

    public void setIdUtilizadorChat(String idUtilizadorChat) {
        this.idUtilizadorChat = idUtilizadorChat;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
