package com.rafasalas.mandala;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
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
        float radius=width*0.2f;
        float extreme;
        float ladusX, ladusY, ladus, angulus;
        float size=width*0.05f;
        //angulus=(float)(Math.PI)/8;
        canvas.drawARGB(255, 0,0,0);
        paint.setARGB(255, rojo, verde, azul);
        paint.setStrokeWidth(3);

        //canvas.drawCircle(width/2, height/2,radius, paint);
        //canvas.drawCircle(width/2, height/2,width*0.4f, paint);
       //
        for (int i=0;i<24;i++){
            if (i==8){angulo=0; radius=width*0.4f;size=width*0.03f;}
            //ladus=(float)2.91/(float)(2*radius);
            //ladusX=(float)(ladus*Math.sin(angulus));
            //ladusY=(float)(ladus*Math.cos(angulus));
           //ladus=3;

            canvas.save();

            canvas.translate(width/2, height/2);

            canvas.rotate(angulo);
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
