package com.example.dardos.codeUtils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class RecursosCodigo {

//    public Context context;
//    public int pantallaSizeX;
//    public int pantallaSizeY;
//
//    public CodeResources(Context context){
//        this.context = context;
//        pantallaSizeX = context.getResources().getDisplayMetrics().widthPixels;
//        pantallaSizeY = context.getResources().getDisplayMetrics().heightPixels;
//
//    }

    public static Bitmap[][] construyeArray(Bitmap bm,
                                     int spritesHorizontal, int spritesVertical,
                                     int tamanyoHorizontal, int tamanyoVertical
    ){
        Bitmap[][] aux = new Bitmap[spritesVertical][spritesHorizontal];

        int posCorteXIni = 0;
        int posCorteYIni = 0;

        for(int i = 0; i < aux.length; i++){
            posCorteXIni = 0;
            for(int j = 0; j < aux[i].length; j++){
                aux[i][j] = Bitmap.createBitmap(
                        bm,
                        posCorteXIni, posCorteYIni,
                        tamanyoHorizontal, tamanyoVertical);
                posCorteXIni += tamanyoHorizontal;
            }
            posCorteYIni += tamanyoVertical;
        }
        return aux;
    }

    public static Bitmap getBitmapFromAssets(Context context, String relativePath){
        AssetManager assetManager = context.getAssets();
        InputStream is = null;
        Bitmap btmap = null;

        try{
            is = assetManager.open(relativePath);
            btmap = BitmapFactory.decodeStream(is);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return btmap;
    }

    public static void espera(int millis){
        try{
            Thread.sleep(millis);
        }catch (InterruptedException ie){
            Log.d("GAME_OVER","Interrupción inesperada");
        }
    }
}