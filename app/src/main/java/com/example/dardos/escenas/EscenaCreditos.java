package com.example.dardos.escenas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class EscenaCreditos extends EsquemaEscena {
    public EscenaCreditos(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);
    }

    @Override
    public void escenaDibuja(Canvas c) {
        c.drawRect(new Rect(0,0,anchoPantalla, altoPantalla), new Paint());
        super.escenaDibuja(c);
    }
}
