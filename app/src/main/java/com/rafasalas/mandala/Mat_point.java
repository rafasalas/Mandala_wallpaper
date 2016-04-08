package com.rafasalas.mandala;

/**
 * Created by salas on 02/03/2016.
 */



import java.util.Random;


import processing.core.PVector;
public class Mat_point {

    PVector posicion, velocidad, aceleracion;
    PVector ancla;
    float masa, coto,  factor_rozamiento, kmuelle;
    boolean resistencia, muelle, cuerda;
    float limite;
    Mat_point (PVector pos, float peso){
        posicion=pos;
        ancla=new PVector(posicion.x,posicion.y);
        masa=peso;
        velocidad=new PVector (0,0);
        aceleracion=new PVector (0,0);
        coto=275;
        factor_rozamiento=(float).0015;
        muelle=false;
        kmuelle=(float).0005;
        resistencia=false;
        cuerda=false;
        limite=50;
    }

    void acelerar(PVector acelerador) {
        PVector a=PVector.mult(acelerador, 1/masa);
        aceleracion.add(a);
        if (muelle){
            //PVector recuperacion=new PVector(aceleracion.x, aceleracion.y);
            PVector recuperacion=PVector.sub(posicion, ancla);
            recuperacion.normalize();
            float d=ancla.dist(posicion);
            //println(d);
            recuperacion.mult(-1*kmuelle*d);
            aceleracion.add(recuperacion);

        }
        if (cuerda){
            float d=ancla.dist(posicion);
            if(d>coto){velocidad.x=0;velocidad.y=0;}
        }
    }

    void actualizar() {

       // posicion.add(velocidad);
        if (resistencia) {
            PVector friccion=new PVector(velocidad.x,velocidad.y);

            //friccion.normalize();
            friccion.mult(-1*factor_rozamiento);
            velocidad.add(friccion);
        }
        velocidad.add(aceleracion);
       // PVector oldpos=new PVector(this.posicion.x, this.posicion.y);

        velocidad.limit(limite);


        posicion.add(velocidad);
        aceleracion.mult(0);


    }



}



class puntocolor extends Mat_point{
    int r,g,b,a;
    puntocolor(PVector pos, float masa){
        super(pos, masa);
        Random rnd=new Random();
        r=rnd.nextInt(255);
        g=rnd.nextInt(255);
        b=rnd.nextInt(255);
        a=rnd.nextInt(125);
    }





}
