package com.example.dardos.elementos;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.dardos.MainActivity;
import com.example.dardos.codeUtils.Constantes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GestionDatos {

    public SharedPreferences spPartida;
    public SharedPreferences spOpciones;
    public SharedPreferences spRecords;

    public GestionDatos(){
        this.spPartida = MainActivity.context.getSharedPreferences(
                Constantes.FICHERO_SP_PARTIDA, Context.MODE_PRIVATE);
        this.spOpciones = MainActivity.context.getSharedPreferences(
                Constantes.FICHERO_SP_OPCIONES, Context.MODE_PRIVATE);
        this.spRecords = MainActivity.context.getSharedPreferences(
                Constantes.FICHERO_SP_RECORDS, Context.MODE_PRIVATE);
    }

    /**
     * =========================================
     * PARTIDA
     * =========================================
     */

    public void nuevaPartida(){
        SharedPreferences.Editor editor = spOpciones.edit();
        editor.clear();
        editor.commit();
    }

    public void guardarPartida(HashMap<String,?> gameData){
        SharedPreferences.Editor editor = spPartida.edit();
//        editor.putInt(gameData.get(Constantes.PARTIDA_NUMVIDAS));
//        Map.Entry<String,?> a = (int)gameData.get(MainActivity.context.getResources().getString(R.string.opt_music));

        editor.commit();
    }

//    datos que quiero guardar en el shared preferences
//
//    número de vidas
//    posición de pnj en la pantalla
//    número de enemigos derrotados //lo necesario para pasar a la siguiente sala
//    tiempo de juego transcurrido
//    estado del ataque especial
//    id de la pantalla en que está
//    id del power up que lleve si es que lleva (estano normal powerup = none)
                            //Key: string, value: Object

    public Map<String, ?> cargarPartida(){
        return spPartida.getAll();
    }

    /**
     * =========================================
     * OPCIONES
     * =========================================
     */

    /**
     * @param arlOpciones
     * Se espera que en este arraylist el primer dato sea un número del 1 al 100
     * El segundo el booleano para los efectos de sonido
     * El tercero el booleano para la vibración
     */
    public void guardarOpciones(ArrayList<Boolean> arlOpciones){
        SharedPreferences.Editor editor = spOpciones.edit();
        editor.putBoolean(Constantes.OPCIONES_MUSICA,arlOpciones.get(0));
        editor.putBoolean(Constantes.OPCIONES_SONIDOS,arlOpciones.get(1));
        editor.putBoolean(Constantes.OPCIONES_VIBRACION,arlOpciones.get(2));
        editor.commit();
    }

    public void borrarOpciones(){
        SharedPreferences.Editor editor = spOpciones.edit();
        editor.clear();
        editor.commit();
    }

    public Map<String, ?> cargarOpciones(){
        return spOpciones.getAll();
    }

    /**
     * =========================================
     * RECORDS
     * =========================================
     * Hash: string(Nombre, 5 letras) - string (tiempo en milisegundo)
     * Ordenar de menor a mayor
     * - tiempo -> + puntuación
     */

    public void guardarNuevoRecord(String nombre, String puntuación){
        SharedPreferences.Editor editor = spOpciones.edit();
        editor.putString(nombre, puntuación);
        editor.commit();
    }

    //https://stackoverflow.com/questions/22089411/how-to-get-all-keys-of-sharedpreferences-programmatically-in-android/22089446
    public Map<String, ?> cargarRecords(){
        return spRecords.getAll();
    }

    public void borrarRecords(){
        SharedPreferences.Editor editor = spRecords.edit();
        editor.clear();
        editor.commit();
    }
}
