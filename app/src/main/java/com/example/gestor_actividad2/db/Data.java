package com.example.gestor_actividad2.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.gestor_actividad2.model.Video;

import java.util.ArrayList;
import java.util.List;

public class Data extends SQLiteOpenHelper {
    private static final String NOMBRE_BD="Videos_Actividad_Final";
    private static final int VERSION=1;
    private static final String TABLA_USUARIO="CREATE TABLE video(_id INTEGER PRIMARY KEY AUTOINCREMENT, _nombre TEXT, _tipo TEXT, _codigo TEXT, _descripcion TEXT)";

    public Data(@Nullable Context context) {
        super(context, NOMBRE_BD, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("========================== UPGRADE ===========================");
    }

    public void insertVideo(Video oVideo){
        SQLiteDatabase bd=getWritableDatabase();

        if (bd!=null){
            bd.execSQL("INSERT INTO video VALUES(NULL,'"+oVideo.getNombre()+"','"+oVideo.getTipo()+"','"+oVideo.getCodigo()+"','"+oVideo.getDescripcion()+"');");
        }
        bd.close();
    }

    public void deleteVideo(String code){
        SQLiteDatabase bd=getWritableDatabase();

        if (bd!=null){
            bd.execSQL("DELETE FROM video WHERE _codigo='"+code+"';");
        }
        bd.close();
    }

    public void editVideo(Video oVideo){
        SQLiteDatabase bd=getWritableDatabase();

        if (bd!=null){
            bd.execSQL("UPDATE video SET _nombre='"+oVideo.getNombre()+"', _tipo='"+oVideo.getTipo()+"', _codigo='"+oVideo.getCodigo()+"',_descripcion='"+oVideo.getDescripcion()+"' WHERE _id="+oVideo.getId()+";");
        }
        bd.close();
    }


    public Video getVideo(String codigo){
        Video oVideo=null;

        SQLiteDatabase bd=getReadableDatabase();

        Cursor oCursor=bd.rawQuery("SELECT * FROM video WHERE _codigo='"+codigo+"';",null);

        if (oCursor.moveToFirst()){
            oVideo=new Video();
            oVideo.setId(oCursor.getInt(0));
            oVideo.setNombre(oCursor.getString(1));
            oVideo.setTipo(oCursor.getString(2));
            oVideo.setCodigo(oCursor.getString(3));
            oVideo.setDescripcion(oCursor.getString(4));
        }

        return oVideo;
    }

    public Video getVideo(int id){
        Video oVideo=null;

        SQLiteDatabase bd=getReadableDatabase();

        Cursor oCursor=bd.rawQuery("SELECT * FROM video WHERE _id="+id+";",null);

        if (oCursor.moveToFirst()){
            oVideo=new Video();
            oVideo.setId(oCursor.getInt(0));
            oVideo.setNombre(oCursor.getString(1));
            oVideo.setTipo(oCursor.getString(2));
            oVideo.setCodigo(oCursor.getString(3));
            oVideo.setDescripcion(oCursor.getString(4));
        }

        return oVideo;
    }

    public List<Video> getAllVideo(){
        List<Video> listVideo=new ArrayList<>();
        SQLiteDatabase bd=getReadableDatabase();
        Cursor oCursor=bd.rawQuery("SELECT * FROM video",null);

        if (oCursor.moveToFirst()){
            do {
                Video oVideo=new Video();
                oVideo.setId(oCursor.getInt(0));
                oVideo.setNombre(oCursor.getString(1));
                oVideo.setTipo(oCursor.getString(2));
                oVideo.setCodigo(oCursor.getString(3));
                oVideo.setDescripcion(oCursor.getString(4));
                listVideo.add(oVideo);
            }while (oCursor.moveToNext());
        }
        return listVideo;
    }
}
