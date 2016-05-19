package com.rafasalas.mandala;

import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private RadioGroup grupotipo;
    private RadioButton radiotipo;
    private Button vamos;
    private Switch muelle, resistencia;
    private SeekBar red, green, blue=null;
    private boolean esmuelle, tieneresistencia;
    private int valR, valG, valB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        muelle = (Switch) findViewById(R.id.muelle);
        resistencia = (Switch) findViewById(R.id.resistencia);
        red=(SeekBar) findViewById(R.id.r);
        green=(SeekBar) findViewById(R.id.g);
        blue=(SeekBar) findViewById(R.id.b);
        esmuelle=tieneresistencia=true;
        muelle.setChecked(true);
        resistencia.setChecked(true);
        addlistenerOnButton();



        red.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
            //valR = ((progress/100)*255);
                valR = (progress*255)/100;
                Log.i("en cambio","prog "+progress);
                Log.i("en cambio","val "+valR);


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
                valG = (progress*255)/100;}

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        blue.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                valB = (progress*255)/100;;}

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        muelle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    esmuelle = true;
                } else {
                    esmuelle = false;
                }

            }
        });
        resistencia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    tieneresistencia=true;
                } else {
                    tieneresistencia=false;
                }

            }
        });
    }




    public void addlistenerOnButton(){

                                //grupotipo = (RadioGroup) findViewById(R.id.Grupotipo);
                                vamos = (Button) findViewById(R.id.Vamos);

       vamos.setOnClickListener(new OnClickListener() {

                                                        @Override
                                                        public void onClick(View v) {

                                                                // get selected radio button from radioGroup
                                                                //int selectedId = grupotipo.getCheckedRadioButtonId();

                                                                // find the radiobutton by returned id
                                                                //radiotipo = (RadioButton) findViewById(selectedId);
                                                           //String clase = radiotipo.getText().toString();
                                                           // Log.i("butt", "Selecionado " + selectedId);
                                                            //Log.i("butt", "Selecionado " + radiotipo.getText());
                                                            final global dataglobal = (global) getApplicationContext();
                                                            dataglobal.settipo("Circulos");
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
                                                            }
                                                            catch(Exception e){

                                                                Intent intent = new Intent();
                                                                intent.setAction(wallcachas.ACTION_LIVE_WALLPAPER_CHOOSER);
                                                                startActivity(intent);
                                                            }



                                                        }

                                                    });
                                        }



}
