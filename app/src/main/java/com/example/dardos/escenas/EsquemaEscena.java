package com.example.dardos.escenas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;

import com.example.dardos.R;
import com.example.dardos.codeUtils.Constantes;
import com.example.dardos.codeUtils.RecursosCodigo;
import com.example.dardos.elementos.Boton;

import static com.example.dardos.codeUtils.AssetsPaths.BTNBACK_PATH;
import static com.example.dardos.codeUtils.AssetsPaths.LOGO_PATH;

public class EsquemaEscena {
    public Context context;
    public int idEscena;
    public int anchoPantalla, altoPantalla;

    public Boton btnAtras;
    public Bitmap bmLogoBtnAtras;

    public AudioManager audioManager;
    public MediaPlayer mediaPlayer;

    public EsquemaEscena(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        this.context = context;
        this.idEscena = idEscena;
        this.anchoPantalla = anchoPantalla;
        this.altoPantalla = altoPantalla;

        this.bmLogoBtnAtras = RecursosCodigo.getBitmapFromAssets(context, BTNBACK_PATH);
        this.btnAtras = new Boton(
                anchoPantalla - anchoPantalla/9,
                0,
                anchoPantalla,
                anchoPantalla/9,
                Color.TRANSPARENT, false, bmLogoBtnAtras, Constantes.ESCENA_MENU_VALUE
        );

        audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
    }

    public void escenaDibuja(Canvas c){
        if(idEscena!= Constantes.ESCENA_MENU_VALUE){
            btnAtras.dibujaBoton(c);
        }
    }

    public void escenaActFisicas(){

    }

    public int onTouchEvent(MotionEvent event) {
        int pointerIndex = event.getActionIndex();        //Obtenemos el índice de la acción
        int pointerID = event.getPointerId(pointerIndex); //Obtenemos el Id del pointer asociado a la acción
        int accion = event.getActionMasked();             //Obtenemos el tipo de pulsación
        switch (accion) {
            case MotionEvent.ACTION_DOWN:           // Primer dedo toca
            case MotionEvent.ACTION_POINTER_DOWN:  // Segundo y siguientes tocan
                break;
            case MotionEvent.ACTION_UP:                     // Al levantar el último dedo
            case MotionEvent.ACTION_POINTER_UP:  // Al levantar un dedo que no es el último
                if(btnAtras.pulsaBoton(event) && this.idEscena!=Constantes.ESCENA_MENU_VALUE){
                    return Constantes.ESCENA_MENU_VALUE;
                }
                break;
            case MotionEvent.ACTION_MOVE: // Se mueve alguno de los dedos
                break;
            default:
                Log.i("Otra acción", "Acción no definida: "+accion);
        }
        return idEscena;
    }
}