package com.example.dardos.escenas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import com.example.dardos.R;
import com.example.dardos.codeUtils.AssetsPaths;
import com.example.dardos.codeUtils.RecursosCodigo;


public class EscenaCreditos extends EsquemaEscena {

    public Bitmap bmFondo;

    public int auxH;
    public int auxV;

    public String creditosJuego;
    public String creditosMusica;
    public String creditosImagenes1;
    public String creditosImagenes2;
    public String creditosProductor;

    public Paint fontPaint;

    public EscenaCreditos(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);

        this.auxH = anchoPantalla/7;
        this.auxV = altoPantalla/24;

        this.fontPaint = new Paint();
        this.fontPaint.setTypeface(Typeface.createFromAsset(context.getAssets(),AssetsPaths.FONT_EPIC_PATH));
        this.fontPaint.setTextSize(auxV);
        this.fontPaint.setColor(context.getColor(R.color.letrasCreditos));

        this.creditosJuego = "Vanessa Cuartiella";
        this.creditosMusica = "www.wingless-seraph.net";
        this.creditosImagenes1 = "Vanessa Cuartiella";
        this.creditosImagenes2 = "www.pexels.com/";
        this.creditosProductor = "Javier Conde";

        this.bmFondo = RecursosCodigo.getBitmapFromAssets(context,AssetsPaths.BACKGROUND02_GREEN_PATH);
        bmFondo = Bitmap.createScaledBitmap(
                bmFondo,
                anchoPantalla,altoPantalla,
                false);
    }

    @Override
    public void escenaDibuja(Canvas c) {
        c.drawRect(new Rect(0,0,anchoPantalla, altoPantalla), new Paint());

        c.drawBitmap(
                bmFondo,
                new Rect(0,0,bmFondo.getWidth(),bmFondo.getHeight()),
                new Rect(0,0, anchoPantalla,altoPantalla),
                null
        );

        c.drawText( context.getString(R.string.credits_author),auxH, auxV*2, fontPaint);
        c.drawText(creditosJuego, auxH, auxV*4, fontPaint);
        c.drawText( context.getString(R.string.credits_music),auxH, auxV*6, fontPaint);
        c.drawText(creditosMusica, auxH, auxV*8, fontPaint);
        c.drawText( context.getString(R.string.credits_images),auxH, auxV*10, fontPaint);
        c.drawText(creditosImagenes1, auxH, auxV*12, fontPaint);
        c.drawText(creditosImagenes2, auxH, auxV*14, fontPaint);
        c.drawText( context.getString(R.string.credits_productor),auxH, auxV*16, fontPaint);
        c.drawText(creditosProductor, auxH, auxV*18, fontPaint);

        super.escenaDibuja(c);
    }
}