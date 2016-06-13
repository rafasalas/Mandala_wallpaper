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
        float radius=width*0.4f;
        canvas.drawARGB(255, 0,0,0);
        paint.setARGB(255, rojo, verde, azul);
        paint.setStrokeWidth(2);

        for (int i=0;i<8;i++){
            canvas.save();

            canvas.translate(width/2, height/2);
            canvas.rotate(angulo);
            canvas.drawCircle(radius,0,width*0.07f, paint);

            canvas.drawLine(0,0, radius,0,paint);
            canvas.restore();
            angulo=angulo+angulobase;

        }
        //canvas.drawARGB(255, rojo, verde, azul);
        invalidate();


    }



}
