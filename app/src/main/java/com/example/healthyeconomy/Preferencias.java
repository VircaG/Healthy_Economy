package com.example.healthyeconomy;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class Preferencias {

    private Context contexto;
    private SharedPreferences preferences;
    private final String NOME_ARQUIVO = "healthyEconomy.preferences";
    private final int MODE = 0;
    private SharedPreferences.Editor editor;

    private final String CHAVE_IDENTIFICADOR = "identificadorUtilizadorLogado";

    public  Preferencias(Context contextoParametro){

        contexto = contextoParametro;
        preferences = contexto.getSharedPreferences(NOME_ARQUIVO, MODE);
        editor = preferences.edit();

    }


    public void salvarDados(String identificadorUtilizador){

        editor.putString(CHAVE_IDENTIFICADOR,identificadorUtilizador);
        editor.commit();
    }

    public  String getIdentificador(){
        return  preferences.getString(CHAVE_IDENTIFICADOR, null);
    }




}
