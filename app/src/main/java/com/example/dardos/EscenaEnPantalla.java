package com.example.dardos;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.dardos.codeUtils.Constantes;
import com.example.dardos.escenas.EscenaAyuda;
import com.example.dardos.escenas.EscenaCreditos;
import com.example.dardos.escenas.EscenaDificultad;
import com.example.dardos.escenas.EscenaJuego;
import com.example.dardos.escenas.EscenaGameOver;
import com.example.dardos.escenas.EscenaMenu;
import com.example.dardos.escenas.EscenaPopSalida;
import com.example.dardos.escenas.EsquemaEscena;

import static com.example.dardos.codeUtils.Constantes.DIFF_EASY;
import static com.example.dardos.codeUtils.Constantes.DIFF_HARD;
import static com.example.dardos.codeUtils.Constantes.DIFF_MED;

public class EscenaEnPantalla extends SurfaceView implements SurfaceHolder.Callback {
    public Context context;
    private Hilo hilo;
    private SurfaceHolder surfaceHolder;

    public EsquemaEscena escena;
    public boolean funcionando = false;

    private int anchoPantalla = 1;
    private int altoPantalla = 1;

    public EscenaEnPantalla(Context context) {
        super(context);
        this.setFocusable(true);
        this.context = context;

        this.surfaceHolder = this.getHolder();
        this.surfaceHolder.addCallback(this);

        hilo = new Hilo();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        hilo.setFuncionando(true); // Se le indica al hilo que puede arrancar
        if (hilo.getState() == Thread.State.NEW) hilo.start(); // si el hilo no ha sido creado se crea;
        if (hilo.getState() == Thread.State.TERMINATED) {      // si el hilo ha sido finalizado se crea de nuevo;
            hilo=new Hilo();
            hilo.start(); // se arranca el hilo
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        anchoPantalla = width;
        altoPantalla = height;

        escena = new EscenaMenu(context, Constantes.ESCENA_MENU_VALUE, width, height);

        hilo.setSurfaceSize(width,height);   // se establece el nuevo ancho y alto de pantalla en el hilo
        hilo. setFuncionando(true);
        if(hilo.getState() == Thread.State.NEW){
            hilo.start();
        }
        if(hilo.getState() == Thread.State.TERMINATED){
            Hilo h = new Hilo();
            h.start();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hilo.setFuncionando(false);  // Se para el hilo
        try {
            hilo.join();   // Se espera a que finalize
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        synchronized (surfaceHolder) {
            int nuevaEscena=escena.onTouchEvent(event);
            if(nuevaEscena!=escena.idEscena){
                switch (nuevaEscena){
                    case Constantes.ESCENA_DIFICULTAD_VALUE:
                        escena = new EscenaDificultad(context, nuevaEscena, anchoPantalla, altoPantalla);
                        break;
                    case Constantes.ESCENA_JUGAR_VALUE_EASY:
                        escena = new EscenaJuego(context, nuevaEscena, anchoPantalla, altoPantalla, DIFF_EASY);
                        break;
                    case Constantes.ESCENA_JUGAR_VALUE_MED:
                        escena = new EscenaJuego(context, nuevaEscena, anchoPantalla, altoPantalla, DIFF_MED);
                        break;
                    case Constantes.ESCENA_JUGAR_VALUE_HARD:
                        escena = new EscenaJuego(context, nuevaEscena, anchoPantalla, altoPantalla, DIFF_HARD);
                        break;
                    case Constantes.ESCENA_AYUDA_VALUE:
                        escena = new EscenaAyuda(context, nuevaEscena, anchoPantalla, altoPantalla);
                        break;
                    case Constantes.ESCENA_SALIR_VALUE:
                        escena = new EscenaPopSalida(context, nuevaEscena, anchoPantalla, altoPantalla);
                        break;
                    case Constantes.ESCENA_CREDITOS_VALUE:
                        escena = new EscenaCreditos(context, nuevaEscena, anchoPantalla, altoPantalla);
                        break;
                    case Constantes.ESCENA_GAME_OVER:
                        escena = new EscenaGameOver(context, nuevaEscena, anchoPantalla, altoPantalla);
                        break;
                    default:
                    case Constantes.ESCENA_MENU_VALUE:
                        escena=new EscenaMenu(context,nuevaEscena,anchoPantalla,altoPantalla);
                        break;
                }
            }
//            else{
//                escena.onTouchEvent(event);
//            }
        }
        return true; //true si ha sido gestionado el evento
    }

    class Hilo extends Thread {
        public Hilo(){}
        @Override
        public void run() {
            while (funcionando) {
                Canvas c = null; //Necesario repintar la totalidad del lienzo
                try {
                    if (!surfaceHolder.getSurface().isValid()) continue; // si la superficie no está preparada repetimos
                    c = surfaceHolder.lockCanvas(); // Obtenemos el lienzo.  La sincronización es necesaria por ser recurso común
                    synchronized (surfaceHolder) {
                        escena.escenaActFisicas();          // Movimiento de los elementos
                        escena.escenaDibuja(c);             // Dibujamos los elementos
                    }
                } finally {  // Haya o no excepción, hay que liberar el lienzo
                    if (c != null) {
                        surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }

        // Activa o desactiva el funcionamiento del hilo
        void setFuncionando(boolean flag) {
            funcionando = flag;
        }

        // Función es llamada si cambia el tamaño de la pantall o la orientación
        public void setSurfaceSize(int width, int height) {
            synchronized (surfaceHolder) {  // Se recomienda realizarlo de forma atómica
            }
        }
    }
}