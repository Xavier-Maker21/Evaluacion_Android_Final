package com.example.gestor_actividad2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gestor_actividad2.db.Data;
import com.example.gestor_actividad2.model.Video;

import java.util.ArrayList;
import java.util.List;

public class List_Activity extends AppCompatActivity {

    private static final String TAG="List_Activity";

    private ListView list_Video;
    private ArrayAdapter<Video> adpVideo;
    private Button btn_Volver;
    private Data oData;
    private List<Video> lista_Video=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_);

        oData=new Data(getApplicationContext());

        lista_Video=oData.getAllVideo();

        btn_Volver=findViewById(R.id.btn_Volver);
        list_Video=findViewById(R.id.list_Videos);
        adpVideo=new ArrayAdapter<Video>(this,android.R.layout.simple_expandable_list_item_1,lista_Video);
        list_Video.setAdapter(adpVideo);

        list_Video.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "OnItemClick"+lista_Video.get(position));
                Toast oToast=Toast.makeText(getApplicationContext(),"Hola, tocaste a "+lista_Video.get(position),Toast.LENGTH_LONG);
                oToast.show();
                int idVideo = lista_Video.get(position).getId();

                Intent oIntent=new Intent(getApplicationContext(),Reproducir_Video.class);
                oIntent.putExtra("Id_Video",idVideo);
                startActivity(oIntent);
            }
        });

        btn_Volver.setOnClickListener(this::volverMain);

    }

    private void volverMain(View view){
        Intent oIntent2=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(oIntent2);
        finish();
    }
}