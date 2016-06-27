package com.rafasalas.mandala;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;

public class MainActivity extends Activity {

  private RadioGroup grupocolor;
    private RadioButton tipocolor;
    private Button vamos;
    //private Switch muelle, resistencia;
    private SeekBar red, green, blue=null;
    private boolean esmuelle, tieneresistencia;
    private int valR=255, valG=255, valB=255;

    private muestracolor muestra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);


        setContentView(R.layout.activity_main);

        // muelle = (Switch) findViewById(R.id.muelle);
        //resistencia = (Switch) findViewById(R.id.resistencia);
        red=(SeekBar) findViewById(R.id.r);
        green=(SeekBar) findViewById(R.id.g);
        blue=(SeekBar) findViewById(R.id.b);

        grupocolor = (RadioGroup) findViewById(R.id.tipocolor);


        muestra=(muestracolor)findViewById(R.id.muestra);
        esmuelle=tieneresistencia=true;
        muestra.rojo= muestra.verde= muestra.azul=255;
        //muelle.setChecked(true);
       // resistencia.setChecked(true);
        addlistenerOnButton();



        red.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
            //valR = ((progress/100)*255);
                valR = (progress*255)/100;
                muestra.rojo =valR;





                //Log.i("en cambio","prog "+progress);
               // Log.i("en cambio","val "+valR);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        green.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                valG = (progress*255)/100;
                muestra.verde =valG;}

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        blue.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                valB = (progress*255)/100;;
            muestra.azul =valB;}
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //muelle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

           // @Override
           // public void onCheckedChanged(CompoundButton buttonView,
                                       //  boolean isChecked) {

              //  if (isChecked) {
        //  esmuelle = true;
        // } else {
        //  esmuelle = false;
        //  }

        //  }
        // });
        // resistencia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

        //@Override
        //public void onCheckedChanged(CompoundButton buttonView,
        //                           boolean isChecked) {

        //   if (isChecked) {
        //       tieneresistencia=true;
        //   } else {
        //     tieneresistencia=false;
        //  }

        //   }
        // });

        grupocolor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup arg0, int id) {
                switch (id) {
                    case -1:
                        //Log.v(TAG, "Choices cleared!");
                        break;
                    case R.id.ramp:
                        muestra.ramp=true;
                    break;
                    case R.id.mono:
                        muestra.ramp=true;
                        break;

                }
            }
        });








    }









    public void addlistenerOnButton(){


                                vamos = (Button) findViewById(R.id.Vamos);

       vamos.setOnClickListener(new OnClickListener() {

                                                        @Override
                                                        public void onClick(View v) {

                                                                // get selected radio button from radioGroup
                                                               int selectedId = grupocolor.getCheckedRadioButtonId();

                                                                // find the radiobutton by returned id
                                                                tipocolor = (RadioButton) findViewById(selectedId);
                                                           String clase = tipocolor.getText().toString();
                                                           // Log.i("butt", "Selecionado " + selectedId);
                                                            //Log.i("butt", "Selecionado " + radiotipo.getText());
                                                            final global dataglobal = (global) getApplicationContext();
                                                            dataglobal.settipo("Circulos");
                                                            Log.i("tipocolor","vlaor "+clase);
                                                            dataglobal.settipocolor(clase);
                                                            dataglobal.setmuelle(esmuelle);
                                                            dataglobal.setresistencia(tieneresistencia);
                                                            dataglobal.setdeditosx(0);
                                                            dataglobal.setdeditosy(0);
                                                            dataglobal.sethaydeditos(false);
                                                            //Log.i(" en inicio","rojo "+valR);
                                                            dataglobal.setcolor(valR,valG, valB);

                                                            WallpaperManager wallcachas=WallpaperManager.getInstance(getApplicationContext());
                                                            try{



                                                                Intent intent = new Intent(wallcachas.ACTION_CHANGE_LIVE_WALLPAPER);

                                                                intent.putExtra(wallcachas.EXTRA_LIVE_WALLPAPER_COMPONENT,
                                                                        new ComponentName(MainActivity.this, wallpaper.class));
                                                                //stopService(intent);
                                                                startActivity(intent);
                                                                finish();
                                                            }
                                                            catch(Exception e){

                                                                Intent intent = new Intent();
                                                                intent.setAction(wallcachas.ACTION_LIVE_WALLPAPER_CHOOSER);
                                                                startActivity(intent);
                                                                finish();
                                                            }



                                                        }

                                                    });
                                        }



}
