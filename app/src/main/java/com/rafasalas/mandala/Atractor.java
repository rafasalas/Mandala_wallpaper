package com.rafasalas.mandala;

/**
 * Created by salas on 02/03/2016.
 */


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Path;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PVector;
import processing.data.IntDict;

public class Atractor {

    PVector posicion, origen_icono;
    float sentido;
    int tipo_atractor;
    int interaccion;
    Random rnd=new Random();
    Atractor (int clase){
        posicion=new PVector(0,0);
        interaccion=0;
        sentido=-1;
        tipo_atractor=clase;
        origen_icono=new PVector (0,0);
    }
    PVector fuerza (PVector posicionobjeto){

        PVector f=posicionobjeto.get();
        f.sub(posicion);

        float modulo=f.mag();
        if (modulo <0) {f.mult(-1);}
        f.normalize();
        switch(tipo_atractor) {
            case 1:
                f.mult(modulo/50);
                break;
            case 2:
                f.mult(150/modulo);
                break;
            case 3:
                f.mult(4);
                break;
            case 4:
                f.mult(150/modulo*modulo);
                break;
        }
        f.mult(sentido);
        return f;
    }
    void visible(Canvas canvas){
        Paint paint;

        //int contador=0;
        paint = new Paint();
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(20);
        paint.setARGB(30,255,255,255);
        canvas.drawCircle(posicion.x, posicion.y, 100, paint);
        paint.setARGB(30,255,255,255);
        canvas.drawCircle(posicion.x, posicion.y, 250, paint);
    //stroke (255,255,255);
        //strokeWeight(1);
        //if (sentido>0) {fill(0,0,0);} else {fill(255,255,255);}
        //noFill();
       // ellipse (posicion.x, posicion.y, 10, 10);

    }

}
