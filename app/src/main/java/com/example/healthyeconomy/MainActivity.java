package com.example.healthyeconomy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity  {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private FirebaseAuth utilizadorAutenticacao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        utilizadorAutenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Healthy Economy");
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.desehar_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        //navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_fragment,
                            new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }


    }
/*
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_fragment,
                                new HomeFragment()).commit();
                break;

            case R.id.nav_contacto:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_fragment,
                                new ContactoFragment()).commit();
                break;

            case R.id.nav_chat:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_fragment,
                                new ChatFragment()).commit();
                break;
            case R.id.nav_perfil:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_fragment,
                                new ProfileFragment()).commit();
                break;

            case R.id.nav_enviar:
                Toast.makeText(this,
                        "Enviado",Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_partilhar:
                Toast.makeText(this,
                        "Partilhado",Toast.LENGTH_SHORT)
                        .show();
                break;

        }
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }



    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }

    }

 */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()){
        case R.id.item_sair:
            logoutUtilizador();
            return true;
        case R.id.item_pesquisa:
            return true;
        case R.id.item_contacto:
            return true;
        case R.id.item_configuracoes:
            return true;
        default:
            return super.onOptionsItemSelected(item);

         }
    }
    public void logoutUtilizador(){
        utilizadorAutenticacao.signOut();
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();

    }




}