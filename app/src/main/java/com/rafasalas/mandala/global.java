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