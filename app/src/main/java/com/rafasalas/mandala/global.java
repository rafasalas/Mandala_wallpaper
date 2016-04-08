package com.rafasalas.mandala;

/**
 * Created by salas on 28/03/2016.
 */
import android.app.Application;

public class global extends Application{

    private String tipo;
    private boolean muelle;
    private boolean resistencia;



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

}