package rafalib.particles;

/**
 * Created by salas on 02/03/2016.
 */



import java.util.Random;


import processing.core.PVector;
public class Mat_point {

    public PVector posicion, velocidad, aceleracion;
    public PVector ancla;
    public float masa, coto,  factor_rozamiento, kmuelle;
    public boolean resistencia, muelle, cuerda, boxed,eterna;
    public float limite, limitx, limity;
    public int lifespan;
    public Mat_point (PVector pos, float peso){
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
        boxed=false;
        limitx=limity=0;
        eterna = false;
        lifespan=0;

    }
    public void boxed( boolean isboxed, float x, float y){
                        boxed=isboxed;
                        limitx=x;
                        limity=y;
        }
    public void acelerar(PVector acelerador) {
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

    public void actualizar() {
        if (eterna==true){lifespan--;}
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
        if(boxed){   if (posicion.x > limitx ) {
            velocidad.x = velocidad.x*-1;
            posicion.x=limitx;
        }
            if ( posicion.x < 0) {
                velocidad.x = velocidad.x*-1;
                posicion.x=0;
            }
            if (posicion.y > limity ) {
                velocidad.y = velocidad.y*-1;
                posicion.y=limity;
            }
            if (posicion.y < 0) {
                velocidad.y = velocidad.y*-1;
                posicion.y=0;
            }
        }}


    public boolean muerta(){if (lifespan<0){return true;}else {return false;}}


}



