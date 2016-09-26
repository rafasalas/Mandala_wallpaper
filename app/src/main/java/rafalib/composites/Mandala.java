package rafalib.composites;

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
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PVector;
import processing.data.IntDict;


import rafalib.atractors.Atractor;
import rafalib.particles.Mat_point;
import rafalib.particles.puntocolor;

public class Mandala {
   public PVector centro;
     public ArrayList <puntocolor> vertice;
    public int numerovertices, capas;


   public  int radius;
    public int incremento;
    public int masa, inc_masa;
    
   public Mandala(PVector center,int nu_vertex, int layer, int radio, int anchura, int masa_inicial, int paso_masa, boolean rozamiento, float coef_roz, boolean muelle, float kelast, float limitx, float limity,Context context){
        centro=center;
        numerovertices=nu_vertex;
        capas=layer;
        radius=radio;
        incremento=anchura;
        masa=masa_inicial;
        inc_masa=paso_masa;
        int contador=0;

//cuidadin
        float angulo=0, paso=(float)(2*Math.PI)/numerovertices;
        vertice = new ArrayList<puntocolor>();
        for (int i=0; i<numerovertices*capas; i++){
            //calculus=new PVector(cos(angulo)*radius+random(-15,15),sin(angulo)*radius+random(-15,15));
            PVector calculus=new PVector((float)Math.cos(angulo)*radius,(float)Math.sin(angulo)*radius);
            vertice.add(new puntocolor(calculus,1));
            puntocolor Vtemp=vertice.get(i);
            Vtemp.posicion.add(centro);
            Vtemp.ancla.add(centro);
            Vtemp.resistencia=rozamiento;
            Vtemp.factor_rozamiento=coef_roz;
            Vtemp.muelle=muelle;
            Vtemp.kmuelle=kelast;
            Vtemp.masa=masa;
            if (limitx>0 && limity>0){
                Vtemp.boxed(true, limitx,limity);
            }
            //cuidadin
            //Vtemp.cuerda=true;
            //cuidadin
            //Vtemp.coto=2;
            angulo=angulo+paso;
            contador++;
            if (contador%numerovertices==0){
                radius=radius+incremento;
                angulo=angulo+(paso/2);
                masa=masa+inc_masa;
            }
        }


        contador =0;
        //cuidadin

        //temporal pa meter vector handemorico de la muerte


        //temporal pa meter vector handemorico de la muerte

    }

   public  void atraccion(Atractor atractor){
        for (int i = 0; i < vertice.size(); i++) {
            puntocolor vert=vertice.get(i);

            vert.acelerar(atractor.fuerza(vert.posicion));

            vert.actualizar();

        }


    }

  public  void pintar(Canvas canvas, String opcion){
        Paint paint;

        int contador=0;
        paint = new Paint();

        paint.setStyle(Style.FILL);
        for (int i=0; i<(numerovertices*capas-1); i++){
            if (i==0){puntocolor Vtemp0=vertice.get(0);} else {vertice.get(i-1);}
            puntocolor Vtemp1=vertice.get(i);
            puntocolor Vtemp2=vertice.get(i+1);
            contador++;
            if (i<numerovertices){
                if (i==numerovertices-1){Vtemp2=vertice.get(0);}
                //triangle(Vtemp1.posicion.x,Vtemp1.posicion.y,centro.x,centro.y,Vtemp2.posicion.x,Vtemp2.posicion.y);
            }
            else{


                puntocolor Vtemp_half=vertice.get(i-(numerovertices-1));

                if(contador%numerovertices==0 && contador<numerovertices*capas) {Vtemp2=vertice.get(i-(numerovertices-1));Vtemp_half=vertice.get(i-((numerovertices*2)-1));}

                //println(contador);
                //noStroke();
                //fill(Vtemp1.r, Vtemp1.g, Vtemp1.b, Vtemp1.a);
                paint.setARGB( Vtemp1.a,Vtemp1.r, Vtemp1.g, Vtemp1.b);
                if(opcion==null){opcion="Triangulos";}
                switch (opcion) {
                    //triangulo vacio
                   default:
                  // canvas.drawLine(Vtemp1.posicion.x,Vtemp1.posicion.y,Vtemp_half.posicion.x,Vtemp_half.posicion.y, paint);
                   //canvas.drawLine(Vtemp_half.posicion.x,Vtemp_half.posicion.y, Vtemp2.posicion.x,Vtemp2.posicion.y,paint);
                   //canvas.drawLine(Vtemp2.posicion.x,Vtemp2.posicion.y,Vtemp1.posicion.x,Vtemp1.posicion.y,paint);
                       canvas.drawCircle(Vtemp1.posicion.x,Vtemp1.posicion.y, Vtemp1.masa*(float)0.25, paint);
                       paint.setStrokeWidth(masa/10);
                       canvas.drawLine(Vtemp_half.posicion.x,Vtemp_half.posicion.y, Vtemp1.posicion.x,Vtemp1.posicion.y,paint);
                    break;
                    // triangulo vacio
                    //triangulo lleno

                    case "Triangulos":
                    Path path = new Path();
                    path.setFillType(Path.FillType.EVEN_ODD);
                    path.moveTo(Vtemp_half.posicion.x, Vtemp_half.posicion.y);
                    path.lineTo(Vtemp2.posicion.x,Vtemp2.posicion.y);

                    path.lineTo(Vtemp1.posicion.x,Vtemp1.posicion.y);
                    path.close();

                    canvas.drawPath(path, paint);
                    break;
                    //triangulo lleno
                    case "Circulos":
                    //circle
                    canvas.drawCircle(Vtemp1.posicion.x,Vtemp1.posicion.y, Vtemp1.masa*(float)0.25, paint);
                        paint.setStrokeWidth(masa/10);
                    //canvas.drawLine(Vtemp_half.posicion.x,Vtemp_half.posicion.y, Vtemp1.posicion.x,Vtemp1.posicion.y,paint);
                        canvas.drawLine(Vtemp2.posicion.x,Vtemp2.posicion.y, Vtemp1.posicion.x,Vtemp1.posicion.y,paint);
                    //circle
                    break;
                    //aquare
                    //default:
                    case "Cuadrados":
                    canvas.drawRect(Vtemp1.posicion.x, Vtemp1.posicion.y, Vtemp1.posicion.x + (masa * (float) 0.9), Vtemp1.posicion.y + (masa * (float) 0.9), paint);
                    break;
                    //square
                    case "Raro":
                        //Path path2 = new Path();
                        //path2.setFillType(Path.FillType.EVEN_ODD);
                        //path2.moveTo(Vtemp1.posicion.x, Vtemp1.posicion.y);
                        //path2.cubicTo(Vtemp1.posicion.x, Vtemp1.posicion.y, Vtemp_half.posicion.x,Vtemp_half.posicion.y,Vtemp2.posicion.x,Vtemp2.posicion.y);


                        //path.close();

                       // canvas.drawPath(path2, paint);
                        //paint.setStrokeWidth(5);
                        paint.setStrokeWidth(masa/10);
                        canvas.drawLine(Vtemp1.posicion.x,Vtemp1.posicion.y,Vtemp_half.posicion.x,Vtemp_half.posicion.y, paint);
                        canvas.drawLine(Vtemp_half.posicion.x,Vtemp_half.posicion.y, Vtemp2.posicion.x,Vtemp2.posicion.y,paint);
                        canvas.drawLine(Vtemp2.posicion.x,Vtemp2.posicion.y,Vtemp1.posicion.x,Vtemp1.posicion.y,paint);



                        break;

                }
            }

        }
        contador=0;


    }
   public  void monocolor( int r, int g, int b, float fact, boolean alea){
        //int r, g, b, factorr, factorg, factorb, el_mayor;
        int factorr, factorg, factorb, el_mayor;
        // Color rgbcolor=new Color();
        Random rnd=new Random();
        IntDict colorbase;
        //Color.RED

        Log.i("color","red "+r);
        Log.i("color","gr "+g);
        Log.i("color","blu "+b);
        colorbase = new IntDict();
        colorbase.set("r", r);
        colorbase.set("g", g);
        colorbase.set("b", b);
        colorbase.sortValues();
        int[] colores = colorbase.valueArray();
        el_mayor=colores[2];
        factorr=(int)(((el_mayor-r)/100)*fact);
        factorg=(int)(((el_mayor-g)/100)*fact);
        factorb=(int)(((el_mayor-b)/100)*fact);
        //Log.i("color","Factor Rojo "+factorr);
        //Log.i("color","Factor Verde "+factorg);
        //Log.i("color","Factor Azul "+factorb);
        for (int i = 0; i < vertice.size(); i++) {
            puntocolor p = vertice.get(i);
                if ((alea && rnd.nextInt(100)<50) || !alea){
                    p.r=r+rnd.nextInt(factorr+1);
                    p.g=g+rnd.nextInt(factorg+1);
                    p.b=b+rnd.nextInt(factorb+1);}

        }



    }

  public   void spiralcolor( int r, int g, int b){
        //int r, g, b, factorr, factorg, factorb, el_mayor;
        float factorr, factorg, factorb, intr,intg,intb, suprared;
        // Color rgbcolor=new Color();
        //Random rnd=new Random();
        //IntDict colorbase;
        //Color.RED

        //Log.i("color","red "+r);


       // if (255-r>r) {factorr=(((float)(255-r)/(float)vertice.size()));} else {factorr=(-((float)(r-255)/(float)vertice.size()));}
        //if (255-g>g) {factorg=(((float)(255-g)/(float)vertice.size()));} else {factorg=(-((float)(g-255)/(float)vertice.size()));}
       // if (255-b>b) {factorb=(((float)(255-b)/(float)vertice.size()));} else {factorb=(-((float)(b-255)/(float)vertice.size()));}
        factorr=(float)((2*r)-255)/(float)vertice.size();
        factorg=(float)((2*g)-255)/(float)vertice.size();
        factorb=(float)((2*b)-255)/(float)vertice.size();
       // Log.i("color","Factor Rojo "+factorr);
        //Log.i("color","Factor Verde "+factorg);
      // Log.i("color","Factor Azul "+factorb);
        //Log.i("color","vertices "+vertice.size());
        //Log.i("color","op rojo " );
        intr=factorr;
        intg=factorg;
        intb=factorb;
        for (int i = 0; i < vertice.size(); i++) {
            puntocolor p = vertice.get(i);

            p.r=(int)(r-intr);
            p.g=(int)(g-intg);
            p.b=(int)(b-intb);
            intr=intr+factorr;
            intg=intg+factorg;
            intb=intb+factorb;

        }



    }

  public   void spiralcolor2( int r, int g, int b){
        //int r, g, b, factorr, factorg, factorb, el_mayor;
       //float factorr, factorg, factorb, intr,intg,intb, suprared;
        float hcomp, inc;
         Color rgbcolor=new Color();
        rgbcolor.rgb(r,g,b);
        float[] hsv = new float[3];
        rgbcolor.RGBToHSV(r,g,b,hsv);
        if (hsv[0]>180) {hcomp=hsv[0]-180;} else {hcomp=180-hsv[0];}
       inc=(float)(hsv[0]-hcomp)/(float)vertice.size();



        //Random rnd=new Random();
        //IntDict colorbase;
        //Color.RED

        //Log.i("color","red "+r);


        // if (255-r>r) {factorr=(((float)(255-r)/(float)vertice.size()));} else {factorr=(-((float)(r-255)/(float)vertice.size()));}
        //if (255-g>g) {factorg=(((float)(255-g)/(float)vertice.size()));} else {factorg=(-((float)(g-255)/(float)vertice.size()));}
        // if (255-b>b) {factorb=(((float)(255-b)/(float)vertice.size()));} else {factorb=(-((float)(b-255)/(float)vertice.size()));}
        //factorr=(float)((2*r)-255)/(float)vertice.size();
        //factorg=(float)((2*g)-255)/(float)vertice.size();
       // factorb=(float)((2*b)-255)/(float)vertice.size();
        // Log.i("color","Factor Rojo "+factorr);
        //Log.i("color","Factor Verde "+factorg);
        // Log.i("color","Factor Azul "+factorb);
        //Log.i("color","vertices "+vertice.size());
        //Log.i("color","op rojo " );

        for (int i = 0; i < vertice.size(); i++) {
            puntocolor p = vertice.get(i);
            int outputColor = Color.HSVToColor(hsv);
            p.r=Color.red(outputColor);
            p.g=Color.green(outputColor);
            p.b=Color.blue(outputColor);
            hsv[0]=hsv[0]-inc;

        }



    }












public  void vectorcolor(PVector direccion){}
public  void actualiza (boolean esmuelle, boolean tieneresistencia){
                    for (int i = 0; i < vertice.size(); i++) {
                                         puntocolor vert=vertice.get(i);

                                        vert.muelle=esmuelle;
                                        vert.resistencia=tieneresistencia;

    }



                                    }
}
