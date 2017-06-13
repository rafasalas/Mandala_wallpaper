package com.rafasalas.mandala;

/**
 * Created by salas on 01/03/2016.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Path;
import android.graphics.Paint.Style;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.view.Display;


import java.lang.Math;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import processing.core.PVector;
import rafalib.composites.Mandala;
import rafalib.atractors.Atractor;
import rafalib.particles.Mat_point;
import rafalib.composites.Mandala;
import static android.opengl.ETC1.getHeight;
import static android.opengl.ETC1.getWidth;

/**
 * Created by salas on 20/11/2015.
 */
public class lienzo{
    //variables
    private int centroX, centroy, contador=0;
    private PVector centro, centro2;
    Mandala mandy, mandy2;
    private Atractor At;
    private Atractor finger;
    private  Mat_point ancla_atractor;
    private Paint paint, fondopaint;
    private int lienzo_width, lienzo_height;
    private int xfinger, yfinger;
    private boolean hayfinger;
    //private Path linea;

    //final global dataglobal = (global) getApplicationContext();
    final global dataglobal;








    public lienzo(Context context,int width, int height){

        //Typeface tipoduro= Typeface.createFromAsset(context.getAssets(), "fonts/Oswald.ttf");
        //super(context);
        //finger
        // context= getApplicationContext();
        dataglobal = (global) context;
        //finger

        paint = new Paint();
        fondopaint=new Paint();

        At=new Atractor(1);
        finger=new Atractor(4);
        At.sentido=-1;
        finger.sentido=1;
        centro=new PVector(width/2, height/2);
        ancla_atractor=new Mat_point(centro,50);
        ancla_atractor.muelle=true;
        ancla_atractor.resistencia=true;
        ancla_atractor.cuerda=false;
        ancla_atractor.factor_rozamiento=(float).01;

        ancla_atractor.kmuelle=(float).05;
        At.posicion.x=ancla_atractor.posicion.x;
        At.posicion.y=ancla_atractor.posicion.y;

        //centro2=new PVector(width/4, height/4);
        //llamamos al mandala
        //Mandala(PVector center,int nu_vertex, int layer, int radio, int anchura, int masa_inicial, int paso_masa, boolean rozamiento, float coef_roz, boolean muelle, float kelast)
        //mandy2=new Mandala(centro, 30,12,0,25,2, 20, true, (float).005, true,(float).002);
       mandy=new Mandala(centro, 40,6,0,50,70,-8, true, (float).009, true,(float).001, width, height,context);
       // mandy=new Mandala(centro, 20,3,0,50,70,-8, true, (float).009, true,(float).001, width, height,context);
        //mandy=new Mandala(centro, 50,10,0,40,5,+8, true, (float).01, true,(float).001, width, height);
        //mandy.monocolor(93,76,119,(float).35, false);
       // mandy.monocolor(203,102,67,(float).35, true);

        //mandy2.monocolor("89E256",.55);


        // width = w;
        //height = (int) (0.9 * h);

    }


    public void draw(Canvas canvas, int width, int height, String opcion) {


        //canvas.drawColor(0xFF1DDDDF);
        paint.setShader(new LinearGradient(0, 0, 0, height, Color.BLACK, Color.WHITE, Shader.TileMode.MIRROR));
        //fondopaint.setShader(new RadialGradient(width / 2, height / 2, width - (width / 4), 0xff718fc6, 0xff303a64, Shader.TileMode.MIRROR));
        fondopaint.setShader(new RadialGradient(width / 2, height / 2, width - (width / 4), 0xff555555, 0xff000000, Shader.TileMode.MIRROR));
        canvas.drawPaint(fondopaint);
       // At.visible(canvas);
        //mandy.centro.x=width/ 2;
        //mandy.centro.y=height/2;
       if (dataglobal.gethaydeditos()){
           dataglobal.sethaydeditos(false);
           finger.posicion.x=dataglobal.getdeditosx();
           finger.posicion.y=dataglobal.getdeditosy();
           mandy.atraccion(finger);
            }
            At.sentido=-1-dataglobal.getIntensity();
        mandy.atraccion(At);
       // mandy2.atraccion(At);
        //dibujamos mandala
       //mandy2.pintar(canvas);
        mandy.pintar(canvas,opcion);
        //mandy2.pintar(canvas);

       // mandy2.pintar();
    }
    public void actualiza_atractor (PVector gravedad){
        ancla_atractor.acelerar(gravedad);
        ancla_atractor.actualizar();
        At.posicion.x=ancla_atractor.posicion.x;
        At.posicion.y=ancla_atractor.posicion.y;

    }
    public void update(int w, int h){
       //Random rnd=new Random();


    }

    public void set_size(int width, int height) {
        lienzo_width=width;
        lienzo_height=height;

    }


}
