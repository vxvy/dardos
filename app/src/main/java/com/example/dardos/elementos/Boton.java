package com.example.dardos.elementos;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.MotionEvent;

import com.example.dardos.MainActivity;
import com.example.dardos.codeUtils.AssetsPaths;


public class Boton {

    public RectF btnRect;
    public RectF btnRectBorde;
    public String btnTexto;
    public Bitmap btnBitmap;

    public Paint btnPaint;
    public Paint btnPaintBorde;
    public Paint btnPaintTexto;

    public boolean btnBorde;
    public int btnValor;
    public int btnColor;

    public Boton(float left, float top, float right, float bottom,
                 int colorFondoBoton, boolean btnTieneBorde, int btnValor){
        this.btnRect = new RectF(left, top, right, bottom);
        this.btnPaint = new Paint();
        this.btnPaint.setColor(colorFondoBoton);

        this.btnBorde = btnTieneBorde;
        if (btnBorde) {
            this.btnRectBorde = new RectF(left, top, right, bottom);
            this.btnPaintBorde = new Paint();
            this.btnPaintBorde.setColor(Color.BLACK);
            this.btnPaintBorde.setStyle(Paint.Style.STROKE);
            this.btnPaintBorde.setStrokeWidth(2);
        }

        this.btnValor = btnValor;
    }

    public Boton(float left, float top, float right, float bottom,
                 int colorFondoBoton, boolean btnTieneBorde, String btnTexto, int btnFontColor, int btnValor) {
        this(left,top,right,bottom,colorFondoBoton,btnTieneBorde,btnValor);

        this.btnTexto = btnTexto;
        this.btnPaintTexto = new Paint();
        this.btnPaintTexto.setTextSize(this.btnRect.height()*2/3);
        this.btnPaintTexto.setTypeface(Typeface.createFromAsset(MainActivity.context.getAssets(), AssetsPaths.FONT_TEXT_PATH));
        this.btnPaintTexto.setColor(btnFontColor);
    }

    public Boton(float left, float top, float right, float bottom,
                 int colorFondoBoton, boolean btnTieneBorde, Bitmap btnDibujo, int btnValor) {
        this(left,top,right,bottom,colorFondoBoton,btnTieneBorde,btnValor);

        this.btnBitmap = Bitmap.createScaledBitmap(btnDibujo,(int)(right-left),(int)(bottom-top),false);
    }

    public void dibujaBoton(Canvas c){
        c.drawRect(btnRect,btnPaint);
        if(btnBorde){
            c.drawRect(btnRectBorde,btnPaintBorde);
        }
        if(btnTexto != null){
            c.drawText(
                this.btnTexto,
                this.btnRect.centerX() - this.btnRect.centerX()*1/7,
                this.btnRect.centerY() + this.btnRect.height()*1/10,
                this.btnPaintTexto
            );
        }
        if(btnBitmap != null){
            c.drawBitmap(btnBitmap,btnRect.left,btnRect.top,null);
        }
    }

    public boolean pulsaBoton(MotionEvent event){
        if(this.btnRect.contains(
                event.getX(),
                event.getY()))
        {
            return true;
        }else{
            return false;
        }
    }

}