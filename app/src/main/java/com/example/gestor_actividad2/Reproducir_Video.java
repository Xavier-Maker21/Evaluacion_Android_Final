package com.example.gestor_actividad2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.gestor_actividad2.db.Data;
import com.example.gestor_actividad2.model.Video;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Reproducir_Video extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener  {

    private YouTubePlayerView oPlayerView;
    private String claveYoutube="AIzaSyA5hLzX5IQgP_oADqtR7o-F_IkI5RyZ6PE";
    private String code_P;
    private int id;
    private Data oData;
    private Video oVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproducir__video);

        oPlayerView=findViewById(R.id.youPlayView);

        oPlayerView.initialize(claveYoutube,this);
        oData=new Data(getApplicationContext());


        Intent oIntent=getIntent();

        code_P=oIntent.getStringExtra("Video");
        id=oIntent.getIntExtra("Id_Video",id);
        if (id == 0) {
            oVideo=null;
        }else{
            oVideo=oData.getVideo(id);
        }

        //System.out.println("\n\n\n\n\n"+id+"\n\n\n\n\n\n"+oVideo.getCodigo()+"\n\n\n\n\n\n");

        //System.out.println("\n\n\n\n\n"+code_P+"\n\n\n\n\n\n");
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b){
            if ((code_P!="" || code_P!=null) && id==0){
                youTubePlayer.cueVideo(code_P);
            }
            if (id!=0 && (code_P=="" || code_P==null)){
                youTubePlayer.cueVideo(oVideo.getCodigo());
            }else{
                System.out.println("Error Fatal");
            }

        }else{
            System.out.println("SE RESTAURO VIDEO");
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this,1).show();
        }else{
            System.out.println("Error");
        }
    }
}