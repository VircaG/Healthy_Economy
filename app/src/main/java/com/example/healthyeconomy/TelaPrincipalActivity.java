package com.example.healthyeconomy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class TelaPrincipalActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private FirebaseAuth utilizadorFirebase;
    private DatabaseReference firebase;
    private String identificadorContato;

    //private String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegacacao);

        utilizadorFirebase = ConfiguracaoFirebase.getFirebaseAutenticacao();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(

                R.id.nav_principal, R.id.nav_perfil, R.id.nav_questionario,
                R.id.nav_gastosC, R.id.nav_gastosP, R.id.nav_chat,R.id.nav_informacao,
                R.id.nav_contatos)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case  R.id.item_sair:
                logoutUtilizador();
                return true;
            case  R.id.item_configuracoes:
                return  true;
            case R.id.item_contacto:
                abrirRegistoContato();
                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }
    }


    private void abrirRegistoContato(){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(TelaPrincipalActivity.this);

        //Configurações do Dialog
        alertDialog.setTitle("Novo contato");
        alertDialog.setMessage("E-mail do usuário");
        alertDialog.setCancelable(false);

        final EditText editText = new EditText(TelaPrincipalActivity.this);
        alertDialog.setView( editText );

        //Configura botões
        alertDialog.setPositiveButton("Cadastrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String emailContato = editText.getText().toString();

                //Valida se o e-mail foi digitado
                if( emailContato.isEmpty() ){
                    Toast.makeText(TelaPrincipalActivity.this, "Preencha o e-mail", Toast.LENGTH_LONG).show();
                }else{

                    //Verificar se o usuário já está cadastrado no nosso App
                   identificadorContato = Base64Custom.codificarBase64(emailContato);
                 // identificadorContato  = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    //Recuperar instância Firebase
                    firebase = ConfiguracaoFirebase.getFirebase().child("Utilizadores").child(identificadorContato);

                    firebase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            if( dataSnapshot.getValue() != null ){

                                //Recuperar dados do contato a ser adicionado
                               Utilizador utilizadorContato = dataSnapshot.getValue( Utilizador.class );

                                //Recuperar identificador usuario logado (base64)
                                Preferencias preferencias = new Preferencias(TelaPrincipalActivity.this);
                                String identificadorUsuarioLogado = preferencias.getIdentificador();

                                firebase = ConfiguracaoFirebase.getFirebase();
                                firebase = firebase.child("Contatos")
                                        .child( identificadorUsuarioLogado )
                                        .child( identificadorContato );

                                Contato contato = new Contato();
                                contato.setIdentificadorUtilizador( identificadorContato );
                                contato.setEmail(utilizadorContato.getEmail() );
                                contato.setNome( utilizadorContato.getNome() );
                                contato.setProfissao(utilizadorContato.getProfissao());

                                firebase.setValue( contato );

                            }else {
                                Toast.makeText(TelaPrincipalActivity.this, "Usuário não possui cadastro.", Toast.LENGTH_LONG)
                                        .show();
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }

            }
        });

        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertDialog.create();
        alertDialog.show();

    }

    private void logoutUtilizador(){
        utilizadorFirebase.signOut();
        Intent intent = new Intent(TelaPrincipalActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}