package com.rafasalas.mandala;

/**
 * Created by salas on 28/03/2016.
 */
import android.app.Application;

public class global extends Application{

    private String tipo;
    private boolean muelle;
    private boolean resistencia;

    private int r,g,b=0;
    //finger

    private int deditosx, deditosy;
    private boolean haydeditos;
    public boolean gethaydeditos(){return haydeditos;}
    public int getdeditosx(){return deditosx;}
    public int getdeditosy(){return deditosy;}
    public void sethaydeditos(boolean hay){haydeditos=hay;}
    public void setdeditosx(int x){deditosx=x;}
    public void setdeditosy(int y){deditosy=y;}



    //fin finger








    public String gettipo() {

        return tipo;
    }

    public void settipo(String atipo) {

        tipo = atipo;

    }

    public boolean getmuelle() {

        return muelle;
    }

    public void setmuelle(boolean amuelle) {

        muelle=amuelle;

    }
    public boolean getresistencia() {

        return resistencia;
    }

    public void setresistencia(boolean aresistencia) {

        resistencia=aresistencia;

    }

    public void setcolor(int red, int green, int blue){
        r=red;
        g=green;
        b=blue;
        }
    public int getred(){return r;}
    public int getgreen(){return g;}
    public int getblue(){return b;}




}