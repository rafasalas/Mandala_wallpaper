package com.rafasalas.mandala;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by salas on 07/06/2016.
 */
public class muestracolor extends View {
    private Context context;
    public int rojo, verde, azul;
    public int ramp_rojo, ramp_verde, ramp_azul;
    public boolean ramp;
    public int[] alfa=new int[24];
    public muestracolor(Context context, AttributeSet atributos) {


        super(context, atributos);

        //dataglobal = (global) getContext();
        //dataglobal=(global)context;
        //rojo=dataglobal.getred();
        //verde=dataglobal.getgreen();
        //azul=dataglobal.getblue();
        rojo=verde=azul=0;
        ramp_rojo=ramp_verde=ramp_azul=0;

        ramp=true;
        Random rnd=new Random();
        for(int i=0;i<24;i++){
           alfa[i] =rnd.nextInt(200-100)+100;
        }
    }
    @Override

    protected void onDraw(Canvas canvas){
        Paint paint;
        paint = new Paint();
        int centerX,centerY;
        centerX=centerY=0;
       // float angulobase=((float) Math.PI)/4;
        float angulobase=45;
        float angulo=0;
        //canvas.drawARGB(255, rojo, verde, azul);
        float width=canvas.getWidth();
        float height=canvas.getHeight();
        float radius=width*0.1f;
        float extreme;
        float ladusX, ladusY, ladus, angulus;
        float size=width*0.05f;
        float hcomp, inc;
        float[] hsv = new float[3];
        inc=0;
        //angulus=(float)(Math.PI)/8;
        canvas.drawARGB(255, 0,0,0);

        paint.setStrokeWidth(3);
        paint.setARGB(255, rojo, verde, azul);
        //canvas.drawCircle(width/2, height/2,radius, paint);
        //canvas.drawCircle(width/2, height/2,width*0.4f, paint);
       //

        if (ramp)
                { Color rgbcolor=new Color();

                rgbcolor.rgb(rojo,verde,azul);

                rgbcolor.RGBToHSV(rojo,verde,azul,hsv);
                if (hsv[0]>180) {hcomp=hsv[0]-180;} else {hcomp=180-hsv[0];}
                inc=(float)(hsv[0]-hcomp)/(float)24;}



        for (int i=0;i<24;i++){
            paint.setARGB(alfa[i], rojo, verde, azul);
            if (i%8==0&i>0){
                angulo=0;
                radius=radius+width*0.15f;

                size=size-width*0.01f;

            }
            //ladus=(float)2.91/(float)(2*radius);
            //ladusX=(float)(ladus*Math.sin(angulus));
            //ladusY=(float)(ladus*Math.cos(angulus));
           //ladus=3;

            canvas.save();

            canvas.translate(width/2, height/2);

            canvas.rotate(angulo);
            if (ramp){ramp_rojo=Color.red(Color.HSVToColor(hsv));
                ramp_verde=Color.green(Color.HSVToColor(hsv));
                ramp_azul=Color.blue(Color.HSVToColor(hsv));

                hsv[0]=hsv[0]-inc;
                paint.setARGB(alfa[i],ramp_rojo, ramp_verde, ramp_azul);}



            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(0, radius,size, paint);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawArc(-radius, -radius, radius,radius, 0,45,false,paint);
            //canvas.rotate(-30);
           //canvas.drawLine( 0, radius,ladusX,radius-ladusY,paint);
            canvas.restore();
            angulo=angulo+angulobase;

        }
        //canvas.drawARGB(255, rojo, verde, azul);
        invalidate();


    }



}
