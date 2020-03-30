package com.example.dardos.codeUtils;

import com.example.dardos.MainActivity;
import com.example.dardos.R;

public class Constantes {

    //ficheros en que guardaremos los datos
    public static final String FICHERO_SP_PARTIDA = "partida_tdl";
    public static final String FICHERO_SP_OPCIONES = "opciones_tdl";
    public static final String FICHERO_SP_RECORDS = "records_tdl";

    //nombre de los datos guardados en FICHERO_SP_PARTIDA
    public static final String PARTIDA_NUMVIDAS =
            MainActivity.context.getResources().getString(R.string.ptd_lifenumb);
    public static final String PARTIDA_POSX =
            MainActivity.context.getResources().getString(R.string.ptd_posx);
    public static final String PARTIDA_POSY =
            MainActivity.context.getResources().getString(R.string.ptd_posy);
    public static final String PARTIDA_TIEMPO =
            MainActivity.context.getResources().getString(R.string.ptd_time);
    public static final String PARTIDA_ATQESP =
            MainActivity.context.getResources().getString(R.string.ptd_spatk);
    public static final String PARTIDA_IDPANT =
            MainActivity.context.getResources().getString(R.string.ptd_sceenid);
    public static final String PARTIDA_POWERUP =
            MainActivity.context.getResources().getString(R.string.ptd_powerup);


    public static final String BTN_TEXT_Y =
            MainActivity.context.getResources().getString(R.string.btn_salir_si);
    public static final String BTN_TEXT_N =
            MainActivity.context.getResources().getString(R.string.btn_salir_no);


    //nombre de los datos guardados en FICHERO_SP_OPCIONES
    public static String OPCIONES_MUSICA =
            MainActivity.context.getResources().getString(R.string.opt_music);
    public static final String OPCIONES_SONIDOS =
            MainActivity.context.getResources().getString(R.string.opt_soundeffects);
    public static String OPCIONES_VIBRACION =
            MainActivity.context.getResources().getString(R.string.opt_vibration);

    //ids de las escenas
    public static final int ESCENA_MENU_VALUE = 0;
    public static final int ESCENA_DIFICULTAD_VALUE = 1;
    public static final int ESCENA_SALIR_VALUE = 2;
    public static final int ESCENA_AYUDA_VALUE = 4;
    public static final int ESCENA_CREDITOS_VALUE = 5;
    public static final int ESCENA_GAME_OVER = 9;
    public static final int ESCENA_JUGAR_VALUE_EASY = 10;
    public static final int ESCENA_JUGAR_VALUE_MED = 11;
    public static final int ESCENA_JUGAR_VALUE_HARD = 12;

    //velocidades
    public static final int DIFF_EASY = 1;
    public static final int DIFF_EASY_VEL = 1;
    public static final int DIFF_MED = 2;
    public static final int DIFF_MED_VEL = 2;
    public static final int DIFF_HARD = 3;
    public static final int DIFF_HARD_VEL = 3;

    public static final int VERTICAL_VEL = 5;

    //ids de las opciones
    public static final int OPCIONES_MUSICA_ID = 111;
    public static final int BTN_SALIDA_YES_ID = 222;
    public static final int BTN_SALIDA_NO_ID = 333;

    //relaciones de etamaño adaptativas
    public static final int SPRITES_ESCALA = 3; //TODO editar esto cuando sea

    private Constantes(){}
}