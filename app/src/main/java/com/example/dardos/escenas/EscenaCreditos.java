package com.example.dardos.escenas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.dardos.codeUtils.AssetsPaths;
import com.example.dardos.codeUtils.Constantes;
import com.example.dardos.codeUtils.RecursosCodigo;
import com.example.dardos.elementos.Boton;

import java.util.ArrayList;


public class EscenaCreditos extends EsquemaEscena {

    public Bitmap bmFondo;
    public ArrayList<Boton> arlBotonnes;

    public int auxH;
    public int auxV;

    public EscenaCreditos(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);

        this.auxH = anchoPantalla/5;
        this.auxV = altoPantalla/11;

        arlBotonnes = new ArrayList<Boton>();
//        arlBotonnes.add(
//                new Boton(
//                        this.auxH,
//                        auxV * 5,
//                        this.auxH *2,
//                        auxV * 6,
//                        Color.WHITE,
//                        false,
//                        Constantes.OPCIONES_MUSICA,
//                        Color.BLACK,
//                        Constantes.OPCIONES_MUSICA_ID)
//        );
//        arlBotonnes.add(
//                new Boton(
//                        this.auxH,
//                        auxV * 7,
//                        this.auxH *2,
//                        auxV * 8,
//                        Color.WHITE,
//                        false,
//                        Constantes.OPCIONES_SONIDOS,
//                        Color.BLACK,
//                        Constantes.OPCIONES_SONIDOS_ID)
//        );
        this.bmFondo = RecursosCodigo.getBitmapFromAssets(context,AssetsPaths.BACKGROUND02_GREEN_PATH);
        bmFondo = Bitmap.createScaledBitmap(
                bmFondo,
                anchoPantalla,altoPantalla,
                false);
    }

    @Override
    public void escenaDibuja(Canvas c) {
        c.drawRect(new Rect(0,0,anchoPantalla, altoPantalla), new Paint());
        super.escenaDibuja(c);

        c.drawRect(0,0,anchoPantalla, altoPantalla, new Paint());
        c.drawBitmap(
                bmFondo,
                new Rect(0,0,bmFondo.getWidth(),bmFondo.getHeight()),
                new Rect(0,0, anchoPantalla,altoPantalla),
                null
        );

        bmFondo = RecursosCodigo.getBitmapFromAssets(this.context, AssetsPaths.BACKGROUND02_GREEN_PATH);
        bmFondo = Bitmap.createScaledBitmap(bmFondo,anchoPantalla,altoPantalla,false);
    }


}
