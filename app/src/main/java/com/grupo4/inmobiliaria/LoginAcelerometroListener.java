package com.grupo4.inmobiliaria;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.net.Uri;

public class LoginAcelerometroListener implements SensorEventListener {
    private long ultimoEvento = 0;
    private float ultimoValor = 0;
    private Context context;

    public LoginAcelerometroListener(Context context){
        this.context = context;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        long tiempoActual = System.currentTimeMillis();

        if (tiempoActual - ultimoEvento > 200){
            ultimoEvento = tiempoActual;

            float valor = event.values[0];
            if (ultimoValor - valor > 10 || ultimoValor - valor < -10){
                if ((ultimoValor > 0 && valor > 0) || (ultimoValor < 0 && valor < 0)) {

                } else if (context.checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel: 2664123456"));
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
            }
            ultimoValor = valor;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}