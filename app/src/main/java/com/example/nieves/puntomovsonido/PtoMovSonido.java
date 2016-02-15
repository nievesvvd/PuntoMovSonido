/*
 *   Copyright (C) 2015, 2016 - Samuel Peregrina Morillas <gaedr@correo.ugr.es>, Nieves V. Velásquez Díaz <nievesvvd@correo.ugr.es>
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.example.nieves.puntomovsonido;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.Toast;

public class PtoMovSonido extends AppCompatActivity implements SensorEventListener {

    private SensorManager miSensor;
    private MediaPlayer player;
    private float lastX, lastY, lastZ;

    /**
     * Metodo con el que inicializamos los componentes de la actividad
     * ademas de ser con el que la creamos
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pto_mov_sonido);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //inicializacion de las variables que vamos a usar
        miSensor = (SensorManager) getSystemService(SENSOR_SERVICE);
        player = MediaPlayer.create(PtoMovSonido.this, R.raw.whip);

    }

    /**
     * Metodo que es llamado cada vez que un sensor manda informacion, si el movil esta en una
     * posicion determinada, suena y no vuelve a sonar hasta que cambia
     *
     * @param event evento producido, en este caso es el acelerometro el que pasa el evento
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        float valX = Math.abs(lastX - event.values[0]);
        float valY = Math.abs(lastY - event.values[1]);
        float valZ = Math.abs(lastZ - event.values[2]);

        //si el cambio es por debajo de 10 para la x lo tomamos como ruido para oblicar a hacer un
        //movimiento especifico, y los demas los "ignoramos" exagerando los requisitos para que suenen
        if ((valX < 10) && (valY < 200) && (valZ < 200)) {
            valX = 0;
            valY = 0;
            valZ = 0;
        } else {
            reproducirSonido();
        }

        lastX = event.values[0];
        lastY = event.values[1];
        lastZ = event.values[2];
    }

    public void reproducirSonido() {
        CharSequence texto = getString(R.string.volume);
        int duracion = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, texto, duracion);
        AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
        int volume_level = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        try {
            if (volume_level < 5) {
                toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 0);
                toast.show();
            }
            player.start();

        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que necesita SensorEventListener
     * pero que no es necesario sobreescribirlo luego lo dejamos como esta
     *
     * @param sensor    el sensor con el que trabaja
     * @param accuracy  la precision con la que se mueve el sensor
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /**
     * Metodo para reiniciar la aplicacion en caso de que se haya suspendido
     */
    @Override
    protected void onResume() {
        super.onResume();
        miSensor.registerListener(this, miSensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    /**
     * Metodo con el que suspendemos la aplicacion
     */
    @Override
    protected void onPause() {
        super.onPause();
        miSensor.unregisterListener(this);
    }
}
