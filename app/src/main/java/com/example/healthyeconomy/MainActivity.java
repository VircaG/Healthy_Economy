package com.example.healthyeconomy;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private FirebaseAuth utilizadorAutenticacao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        utilizadorAutenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();





 /*
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
    */



    }

    public void logoutUtilizador(){
            utilizadorAutenticacao.signOut();
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
    }

    public void abrirActivityPrincipal(){
        startActivity( new Intent( this, TelaPrincipalActivity.class));
    }


}