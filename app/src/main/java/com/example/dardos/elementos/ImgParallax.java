package com.example.dardos.elementos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class ImgParallax {

    int auxHSize;
    int auxVSize;

    Bitmap imgParallax;
    float imgParallaxVelocidad;
    float imgParallaxPosX;
    float imgParallaxPosY;
    Paint imgParallaxPaint;
    boolean movimientoVerticalUHorizontal;      //True vertical, false Horizontal

    public ImgParallax(Bitmap imgParallax, Context context,
                       float imgParallaxVelocidad,
                       float imgParallaxPosX, float imgParallaxPosY,
                       boolean movimientoVerticalUHorizontal) {

        auxHSize = context.getResources().getDisplayMetrics().widthPixels;
        auxHSize = context.getResources().getDisplayMetrics().heightPixels;

        this.imgParallax = Bitmap.createScaledBitmap(
                imgParallax,
                auxHSize, auxVSize,
                false);
        this.imgParallaxVelocidad = imgParallaxVelocidad;
        this.imgParallaxPosX = imgParallaxPosX;
        this.imgParallaxPosY = imgParallaxPosY;

        this.imgParallaxPaint = new Paint();

        this.movimientoVerticalUHorizontal = movimientoVerticalUHorizontal;
    }

    public void dibujaParallax(Canvas c){
        c.drawBitmap(this.imgParallax,
                (int)this.imgParallaxPosX,
                (int)this.imgParallaxPosY,
                imgParallaxPaint);
        if((!movimientoVerticalUHorizontal) && this.imgParallaxPosX<0){         //Movimiento del bitmap a la izquierda
            c.drawBitmap(
                    this.imgParallax,
                    auxHSize + this.imgParallaxPosX,
                    this.imgParallaxPosY,
                    imgParallaxPaint);
        }else if((!movimientoVerticalUHorizontal) && this.imgParallaxPosX>0){   //Movimiento del bitmap a la derecha
            c.drawBitmap(
                    this.imgParallax,
                    -1* auxHSize + this.imgParallaxPosX,
                    this.imgParallaxPosY,
                    imgParallaxPaint);
        }
    }

    public void actualizaPosicionParallax(){
        if(movimientoVerticalUHorizontal){
            //La vertical sólo sucede una vez
            if(this.imgParallaxPosY< auxVSize){
                this.imgParallaxPosY += this.imgParallaxVelocidad;
            }
        }else{
            if(this.imgParallaxVelocidad<0){         //Movimiento del bitmap a la izquierda
                if(this.imgParallaxPosX<=-1* auxHSize){
                    this.imgParallaxPosX = 0;
                }
                else{
                    this.imgParallaxPosX +=this.imgParallaxVelocidad;
                }
            }else{                                  //Movimiento del bitmap a la derecha
                if(this.imgParallaxPosX> auxHSize){
                    this.imgParallaxPosX = this.imgParallaxVelocidad;
                }
                else{this.imgParallaxPosX +=this.imgParallaxVelocidad;}
            }
        }
    }
}