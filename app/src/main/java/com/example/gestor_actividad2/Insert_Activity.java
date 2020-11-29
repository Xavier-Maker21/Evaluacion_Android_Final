package com.example.gestor_actividad2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestor_actividad2.db.Data;
import com.example.gestor_actividad2.model.Video;

public class Insert_Activity extends AppCompatActivity {

    private Button btn_Insert, btn_Volver, btn_Code;
    private EditText txt_nombre, txt_tipo, txt_codigo,txt_Descripcion;
    private Data oData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_);

        btn_Code=findViewById(R.id.btn_Sacar);
        btn_Insert=findViewById(R.id.btn_Registrar);
        btn_Volver=findViewById(R.id.btn_Volver);

        txt_nombre=findViewById(R.id.txt_Nombre);
        txt_tipo=findViewById(R.id.txt_Tipo);
        txt_codigo=findViewById(R.id.txt_Codigo);
        txt_Descripcion=findViewById(R.id.txt_Descripcion);

        oData=new Data(getApplicationContext());

        btn_Code.setOnClickListener(this::ejemploCodigo);
        btn_Insert.setOnClickListener(this::insert);
        btn_Volver.setOnClickListener(this::volverMain);
    }

    private void ejemploCodigo(View view){
        Toast oToast=Toast.makeText(getApplicationContext(),"El codigo lo encuentras despues del 1er '=' y antes de '&' Ej='aY_ElJkxVtI'",Toast.LENGTH_LONG);
        oToast.show();
    }

    private void insert(View view){
        Video oVideo=new Video();
        oVideo.setNombre(txt_nombre.getText().toString());
        oVideo.setTipo(txt_tipo.getText().toString());
        oVideo.setCodigo(txt_codigo.getText().toString());
        oVideo.setDescripcion(txt_Descripcion.getText().toString());

        oData.insertVideo(oVideo);
        txt_codigo.setText("");
        txt_nombre.setText("");
        txt_tipo.setText("");
        txt_Descripcion.setText("");
        Toast oToast1=Toast.makeText(getApplicationContext(),"Ingresado",Toast.LENGTH_SHORT);
        oToast1.show();
    }

    private void volverMain(View view){
        Intent oIntent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(oIntent);
        finish();
    }
}