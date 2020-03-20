package com.example.dardos.escenas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;

import com.example.dardos.R;
import com.example.dardos.codeUtils.AssetsPaths;
import com.example.dardos.codeUtils.RecursosCodigo;


public class EscenaGameOver extends EsquemaEscena {

    public Paint fontPaint;

    public int auxH;
    public int auxV;

    public EscenaGameOver(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);

        this.auxH = anchoPantalla/15;
        this.auxV = altoPantalla/2;

        fontPaint = new Paint();
        fontPaint.setStyle(Paint.Style.STROKE);
        fontPaint.setTextSize(altoPantalla/3);
        fontPaint.setColor(context.getColor(R.color.greenyhard));
        fontPaint.setTypeface(Typeface.createFromAsset(context.getAssets(), AssetsPaths.FONT_MAIN_TITLE_PATH));
    }

    @Override
    public void escenaDibuja(Canvas c) {
        c.drawRect(0,0,anchoPantalla,altoPantalla,new Paint());
        c.drawText(
                context.getString(R.string.msg_gameover),
                auxH*3,
                auxV,
                fontPaint
        );
    }

    @Override
    public int onTouchEvent(MotionEvent event) {
        RecursosCodigo.espera(3000);
        return 0;
    }
}
