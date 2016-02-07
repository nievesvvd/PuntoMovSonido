package com.example.nieves.puntomovsonido;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class PtoMovSonido extends AppCompatActivity implements SensorEventListener {

    private SensorManager miSensor;
    private MediaPlayer player;
    boolean reinicio;

    /**Metodo con el que inicializamos los componentes de la actividad
     * ademas de ser con el que la creamos
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pto_mov_sonido);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**Metodo que es llamado cada vez que un sensor manda informacion, si el movil esta en una
     * posicion determinada, suena y no vuelve a sonar hasta que cambia
     * @param event
     */
    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    /**Metodo que necesita SensorEventListener
     *
     * @param sensor
     * @param accuracy
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    /**Metodo para reiniciar la aplicacion en caso de que se haya suspendido
     *
     */
    @Override
    protected void onResume() {
        super.onResume();
        // register this class as a listener for the orientation and
        // accelerometer sensors
        miSensor.registerListener(this,miSensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
    }
    /**Metodo con el que suspendemos la aplicacion
     *
     */
    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        miSensor.unregisterListener(this);
    }
}
