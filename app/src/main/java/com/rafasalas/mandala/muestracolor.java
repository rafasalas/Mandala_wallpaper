package com.rafasalas.mandala;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by salas on 07/06/2016.
 */
public class muestracolor extends View {
    private Context context;
    public int rojo, verde, azul;

    public muestracolor(Context context, AttributeSet atributos) {


        super(context, atributos);

        //dataglobal = (global) getContext();
        //dataglobal=(global)context;
        //rojo=dataglobal.getred();
        //verde=dataglobal.getgreen();
        //azul=dataglobal.getblue();
        rojo=verde=azul=0;

    }
    @Override

    protected void onDraw(Canvas canvas){

        //canvas.drawARGB(255, rojo, verde, azul);
        canvas.drawARGB(255, rojo, verde, azul);
        invalidate();


    }



}
