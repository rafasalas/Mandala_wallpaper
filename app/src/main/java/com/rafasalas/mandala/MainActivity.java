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
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private RadioGroup grupotipo;
    private RadioButton radiotipo;
    private Button vamos;
    private Switch muelle, resistencia;
    private boolean esmuelle, tieneresistencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        muelle = (Switch) findViewById(R.id.muelle);
        resistencia = (Switch) findViewById(R.id.resistencia);
        esmuelle=tieneresistencia=true;
       muelle.setChecked(true);
        resistencia.setChecked(true);
        addlistenerOnButton();
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

                                grupotipo = (RadioGroup) findViewById(R.id.Grupotipo);
                                vamos = (Button) findViewById(R.id.Vamos);

       vamos.setOnClickListener(new OnClickListener() {

                                                        @Override
                                                        public void onClick(View v) {

                                                                // get selected radio button from radioGroup
                                                                int selectedId = grupotipo.getCheckedRadioButtonId();

                                                                // find the radiobutton by returned id
                                                                radiotipo = (RadioButton) findViewById(selectedId);
                                                           String clase = radiotipo.getText().toString();
                                                           // Log.i("butt", "Selecionado " + selectedId);
                                                            //Log.i("butt", "Selecionado " + radiotipo.getText());
                                                            final global dataglobal = (global) getApplicationContext();
                                                            dataglobal.settipo(clase);
                                                            dataglobal.setmuelle(esmuelle);
                                                            dataglobal.setresistencia(tieneresistencia);
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
