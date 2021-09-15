package com.example.healthyeconomy;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public final class ConfiguracaoFirebase {

    private  static DatabaseReference referenciaFirebase;
    private  static FirebaseAuth autenticacao;



    public static DatabaseReference getFirebase(){

        if( referenciaFirebase == null){
            referenciaFirebase = FirebaseDatabase.getInstance().getReference();
        }

    return referenciaFirebase;
    }



    public static FirebaseAuth getFirebaseAutenticacao(){
        if(autenticacao == null){
            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;

    }

//    public static String getCurrentUser(){
//        String emailUtilizador = autenticacao.getCurrentUser().getUid();
//        String idUtilizador = Base64Custom.codificarBase64(emailUtilizador);
//        return idUtilizador;
//    }


}
