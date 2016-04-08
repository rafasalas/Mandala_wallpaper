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
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.SurfaceHolder;
import android.os.Handler;

import processing.core.PVector;

public class wallpaper extends WallpaperService {
    private Context context;
    private String opcion;
    private boolean muelle, resistencia;
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
        //private int opcion;
        //Variables acelerometro

        private lienzo lienzotrabajo;
        private PVector gravedad;

        private wallpaperEngine() {
            context= getApplicationContext();
            final global dataglobal = (global) getApplicationContext();
            opcion=dataglobal.gettipo();
            muelle=dataglobal.getmuelle();
            resistencia=dataglobal.getresistencia();
            //Log.i("butt", "hallegado " + opcion);

            mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
            mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            //acelerometro
            gravedad=new PVector(0,0);

            int width = getApplicationContext().getResources().getDisplayMetrics().widthPixels;
            int height = getApplicationContext().getResources().getDisplayMetrics().heightPixels;

            lienzotrabajo=new lienzo(context,width,height);

            handler = new Handler();
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
            gravedad.mult((10-mSensorZ)*50);

            //This is your Accelerometer X,Y,Z values
            Log.d("sensor", "X: " + mSensorX + ", Y: " + mSensorY + ", Z: " + mSensorZ);
            Log.d("Vector", "X: " + gravedad.x + ", Y: " + gravedad.y);
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

                //rebota.draw(canvas, width, height);
                lienzotrabajo.mandy.actualiza(muelle, resistencia);
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
        public void onDestroy() {
            //desregistrando acelerometros...
            unregisterSensors();
            super.onDestroy();
            handler.removeCallbacks(drawNucleus);
        }





    }

}
