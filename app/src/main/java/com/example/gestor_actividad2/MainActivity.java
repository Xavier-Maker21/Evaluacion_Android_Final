package com.example.gestor_actividad2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gestor_actividad2.model.Video;

public class MainActivity extends AppCompatActivity {

    private Button btn_Album, btn_Buscar,btn_ingreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_Album=findViewById(R.id.btn_Album);
        btn_Buscar=findViewById(R.id.btn_Busqueda);
        btn_ingreso=findViewById(R.id.btn_Ingresi);


        btn_Album.setOnClickListener(this::ingresoP);
        btn_Buscar.setOnClickListener(this::ingresoP_Select);
        btn_ingreso.setOnClickListener(this::ingresoVideos);

    }

    private void ingresoVideos(View view){
        Intent oIntent=new Intent(getApplicationContext(),Insert_Activity.class);
        startActivity(oIntent);
        finish();
    }

    private void ingresoP(View view) {
        Intent oIntent=new Intent(getApplicationContext(),List_Activity.class);
        startActivity(oIntent);
        finish();
    }

    private void ingresoP_Select(View view){
        Intent oIntent2=new Intent(getApplicationContext(),MapsActivity.class);
        startActivity(oIntent2);
        finish();
    }
}