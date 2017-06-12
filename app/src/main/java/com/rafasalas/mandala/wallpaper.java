package com.rafasalas.mandala;

/**
 * Created by salas on 01/03/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.SurfaceHolder;
import android.os.Handler;

import java.util.Random;

import processing.core.PVector;
import rafalib.composites.Mandala;
import rafalib.atractors.Atractor;
import rafalib.particles.Mat_point;

public class wallpaper extends WallpaperService {
    private Context context;
    private String opcion;
    private boolean muelle, resistencia;
    private int rojo, verde, azul;
    private String tipocolor;
    private Visualizer mVisualizer;
    //private Intent intent;





    @Override
    public Engine onCreateEngine() {

        return new wallpaperEngine();

    }



    private class wallpaperEngine extends Engine implements SensorEventListener {

        private final int frameDuration = 16;
        private SurfaceHolder holder;

        private boolean visible;
        private Handler handler;

        //Variables acelerometro
        private SensorManager mSensorManager;
        private Sensor mAccelerometer;
        private float mSensorX;
        private float mSensorY;
        private float mSensorZ;
        private boolean cambio_muelle, cambio_resistencia;
        private float contador, limite_contador;
        //private int opcion;
        //Variables acelerometro

        private lienzo lienzotrabajo;
        private PVector gravedad;
        //finger
       // context= getApplicationContext();
        final global dataglobal = (global) getApplicationContext();
        //finger
        private wallpaperEngine() {
             context= getApplicationContext();
            //final global dataglobal = (global) getApplicationContext();
            opcion=dataglobal.gettipo();
            muelle=dataglobal.getmuelle();
            resistencia=dataglobal.getresistencia();
            rojo=dataglobal.getred();
            verde=dataglobal.getgreen();
            azul=dataglobal.getblue();
            tipocolor=dataglobal.gettipocolor();
            //Log.i("   en wallpaper","rojo "+rojo);
            //Log.i("butt", "hallegado " + opcion);
            contador=0;
            limite_contador=600;
            mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
            mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            //acelerometro
            gravedad=new PVector(0,0);

            int width = getApplicationContext().getResources().getDisplayMetrics().widthPixels;
            int height = getApplicationContext().getResources().getDisplayMetrics().heightPixels;

            lienzotrabajo=new lienzo(context,width,height);

            handler = new Handler();
            init_music();
        }

        //funciones acelerometro
        public void registerSensors() {
            Log.d("sensor", "registerSensors()");
            mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
        }

        public void unregisterSensors() {
            Log.d("sensor", "unregisterSensors()");
            mSensorManager.unregisterListener(this);
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER)
                return;
            mSensorX = event.values[0];
            mSensorY = event.values[1];
            mSensorZ = event.values[2];
            if (mSensorZ>10){mSensorZ=10;}
            gravedad.set(-mSensorX,mSensorY);
            gravedad.normalize();
            gravedad.mult((10-mSensorZ)*70);


            //Log.d("sensor", "X: " + mSensorX + ", Y: " + mSensorY + ", Z: " + mSensorZ);
           // Log.d("Vector", "X: " + gravedad.x + ", Y: " + gravedad.y);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        //funciones acelerometro


        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {


            super.onCreate(surfaceHolder);

            this.holder = surfaceHolder;
            //cuidadin
            registerSensors();
            //cuidadin




        }
        //finger
        @Override
        public Bundle onCommand(String action, int x, int y, int z,
                                Bundle extras, boolean resultRequested) {

            if (action.equals("android.wallpaper.tap")) {
                dataglobal.sethaydeditos(true);
                dataglobal.setdeditosx(x);
                dataglobal.setdeditosy(y);
            }
            return super.onCommand(action, x, y, z, extras, resultRequested);
        }
        //finger
        private Runnable drawNucleus = new Runnable() {
            @Override
            public void run() {
                draw();

            }
        }
                ;

        private void draw() {


            if (visible) {
                Canvas canvas = holder.lockCanvas();
                canvas.save();
                int width=canvas.getWidth();
                int height=canvas.getHeight();
                Random rnd=new Random();

                //TEMPORIZADOR DE DISPARO
                contador++;
                if (contador>limite_contador){
                    contador=0;
                    if (muelle==true){limite_contador=300;} else {limite_contador=600;}
                  // resistencia=!resistencia;
                    muelle=!muelle;
                    if(rnd.nextInt(100)<50){resistencia=!resistencia;}
                }
                //Log.i("  contador","vlaor "+contador);
                //Log.i("  contador", "resistencia " + resistencia);
                //rebota.draw(canvas, width, height);
                lienzotrabajo.mandy.actualiza(muelle, resistencia);
                Log.i("  tipocolor en wall","vlaor "+tipocolor);
                if (tipocolor.equals("Monocolor")){lienzotrabajo.mandy.monocolor(rojo,verde,azul,(float).60, false);}
                    else{lienzotrabajo.mandy.spiralcolor2(rojo,verde,azul);}
                //lienzotrabajo.mandy.monocolor(rojo,verde,azul,(float).60, false);
                //lienzotrabajo.mandy.spiralcolor2(rojo,verde,azul);
                lienzotrabajo.actualiza_atractor(gravedad);
                lienzotrabajo.draw(canvas, width, height,opcion);
                canvas.restore();
                holder.unlockCanvasAndPost(canvas);

                handler.removeCallbacks(drawNucleus);
                handler.postDelayed(drawNucleus, frameDuration);
            }
        }

        public void onVisibilityChanged(boolean visible) {
            this.visible = visible;

            if (visible) {
                handler.post(drawNucleus);
            } else {
                handler.removeCallbacks(drawNucleus);
            }

        }
        @Override
        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            super.onSurfaceChanged(holder, format, width, height);
            // store the center of the surface, so we can draw the cube in the right spot
            // mCenterX = width/2.0f;
            // mCenterY = height/2.0f;
            //drawFrame();
        }


        @Override
        public void onDestroy() {
            //desregistrando acelerometros...
            unregisterSensors();
            super.onDestroy();
            handler.removeCallbacks(drawNucleus);
        }





    }
///////
private void init_music(){
    final global dataglobal = (global) getApplicationContext();
    mVisualizer = new Visualizer(0);
    mVisualizer.setEnabled(false);

    mVisualizer.setCaptureSize(128);

    Visualizer.OnDataCaptureListener captureListener = new Visualizer.OnDataCaptureListener()
    {
        @Override
        public void onWaveFormDataCapture(Visualizer visualizer, byte[] bytes,
                                          int samplingRate)
        { float sum=0;

            for (int i = 0; i < bytes.length; i++) {
                sum=sum+(float)bytes[i];
            }
            //if (sum<-16383){sum=1;}else{sum=sum/1000;}
            if (sum<-12000 || sum>12000){sum=1;}else{sum=sum/1000;}

            dataglobal.setIntensity(sum);
            Log.v("sumatorio"," "+sum);
        }

        @Override
        public void onFftDataCapture(Visualizer visualizer, byte[] bytes,
                                     int samplingRate)
        {
            Log.v("fft", " " + bytes.length+bytes[0] + " "+bytes[60] + " " + bytes[125]);
        }
    };

    mVisualizer.setDataCaptureListener(captureListener,
            Visualizer.getMaxCaptureRate() / 2, true, true);

    // Enabled Visualizer and disable when we're done with the stream

    mVisualizer.setEnabled(true);

}


    public void release()
    {
        mVisualizer.release();
    }

}

//////////////

