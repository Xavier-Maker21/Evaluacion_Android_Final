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

public class Select_Activity extends AppCompatActivity {

    private Button btn_SelectCode, btn_Edit, btn_Delete, btn_Play, btn_Listar, btn_Volver;
    private EditText txt_Codigo, txt_Nombre, txt_Tipo;
    private Data oData;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_);

        btn_SelectCode=findViewById(R.id.btn_SelectCode);
        btn_Edit=findViewById(R.id.btn_Edit);
        btn_Delete=findViewById(R.id.btn_Delete);
        btn_Listar=findViewById(R.id.btn_Listar);
        btn_Play=findViewById(R.id.btn_Play);
        btn_Volver=findViewById(R.id.btn_Volver);
        txt_Nombre=findViewById(R.id.txt_Nombre);
        txt_Codigo=findViewById(R.id.txt_Codigo);
        txt_Tipo=findViewById(R.id.txt_Tipo);

        oData=new Data(getApplicationContext());

        btn_SelectCode.setOnClickListener(this::select_Code);
        btn_Edit.setOnClickListener(this::edit_Video);
        btn_Delete.setOnClickListener(this::delete_Video);
        btn_Play.setOnClickListener(this::play_Video);

        btn_Listar.setOnClickListener(this::listarP);
        btn_Volver.setOnClickListener(this::volverMain);
    }

    private void select_Code(View view){
        String code=txt_Codigo.getText().toString();
        if (!code.isEmpty()){
            Video oVideo=oData.getVideo(code);

            if(oVideo!=null){
                id=oVideo.getId();
                txt_Nombre.setText(oVideo.getNombre());
                txt_Tipo.setText(oVideo.getTipo());
                Toast oToast = Toast.makeText(getApplicationContext(), "Video encontrado", Toast.LENGTH_SHORT);
                oToast.show();
            }else{
                Toast oToast1 = Toast.makeText(getApplicationContext(), "Video no encontrado", Toast.LENGTH_SHORT);
                oToast1.show();
            }
        }else{
            Toast oToast1 = Toast.makeText(getApplicationContext(), "Ingrese Codigo", Toast.LENGTH_SHORT);
            oToast1.show();
        }
    }

    private void edit_Video(View view){
        Video oVideo=new Video();


        if (!txt_Codigo.getText().toString().isEmpty()){
            oVideo.setId(id);
            oVideo.setCodigo(txt_Codigo.getText().toString());
            oVideo.setNombre(txt_Nombre.getText().toString());
            oVideo.setTipo(txt_Tipo.getText().toString());

            System.out.println(oVideo.getId()+"\n\n "+oVideo.getCodigo()+" \n\n "+oVideo.getTipo()+" \n\n "+oVideo.getNombre());
            oData.editVideo(oVideo);
            txt_Codigo.setText("");
            txt_Nombre.setText("");
            txt_Tipo.setText("");
            Toast oToast2 = Toast.makeText(getApplicationContext(), "Video Editado", Toast.LENGTH_SHORT);
            oToast2.show();
        }else{
            Toast oToast3 = Toast.makeText(getApplicationContext(), "Ingrese Datos", Toast.LENGTH_SHORT);
            oToast3.show();
        }
    }

    private void delete_Video(View view){
        String code=txt_Codigo.getText().toString();

        if (!code.isEmpty()){
            oData.deleteVideo(code);
            txt_Codigo.setText("");
            txt_Nombre.setText("");
            txt_Tipo.setText("");
            Toast oToast2 = Toast.makeText(getApplicationContext(), "Video Eliminado", Toast.LENGTH_SHORT);
            oToast2.show();
        }else{
            Toast oToast2 = Toast.makeText(getApplicationContext(), "Ingrese Codigo", Toast.LENGTH_SHORT);
            oToast2.show();
        }

    }

    private void play_Video(View view){
        String code=txt_Codigo.getText().toString();

        if (!code.isEmpty()){
            txt_Codigo.setText("");
            txt_Nombre.setText("");
            txt_Tipo.setText("");
            Intent oIntent3=new Intent(getApplicationContext(),Reproducir_Video.class);
            oIntent3.putExtra("Video",code);
            startActivity(oIntent3);
        }else{
            Toast oToast2 = Toast.makeText(getApplicationContext(), "Error al Reproducir, Ingrese Codigo", Toast.LENGTH_SHORT);
            oToast2.show();
        }

    }

    private void volverMain(View view){
        Intent oIntent2=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(oIntent2);
        finish();
    }

    private void listarP(View view){
        txt_Codigo.setText("");
        txt_Nombre.setText("");
        txt_Tipo.setText("");
        Intent oIntent=new Intent(getApplicationContext(),List_Activity.class);
        startActivity(oIntent);
        finish();
    }
}