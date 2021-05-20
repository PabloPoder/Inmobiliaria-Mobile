package com.grupo4.inmobiliaria;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.grupo4.inmobiliaria.modelo.Propietario;
import com.grupo4.inmobiliaria.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {
    private MutableLiveData<String> mensajeMutable;
    private MutableLiveData<Boolean> resultadoMutable;
    private Context context;

    public MainActivityViewModel (@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<String> getMensajeMutable(){
        if (mensajeMutable == null)
            mensajeMutable = new MutableLiveData<>();
        return mensajeMutable;
    }

    public LiveData<Boolean> getResultadoMutable(){
        if (resultadoMutable == null)
            resultadoMutable = new MutableLiveData<>();
        return resultadoMutable;
    }

    public void verificarDatos(String mail, String clave){
        if (mail != null && clave != null && clave.length() > 0 && mail.length() > 0){

            Call<String> respuestaToken = ApiClient.getMyApiClient().Login(mail, clave);
            respuestaToken.enqueue(new Callback<String>() {
                @Override
                public void onResponse (Call<String> call, Response<String> response) {
                    if(response.isSuccessful()){
                        Log.d("Token", response.body());

                        SharedPreferences sharedPreferences = context.getSharedPreferences("token.dat", 0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("token", "Bearer " + response.body());
                        editor.commit();

                        resultadoMutable.setValue(true);

                    }else{
                        mensajeMutable.setValue("Usuario y/o Clave Incorrecta");
                    }
                }

                @Override
                public void onFailure (Call<String> call, Throwable t) {
                    Log.d("Token", "Salida Error" + t.getMessage());
                }
            });

        } else {
            mensajeMutable.setValue("Datos insuficientes");
        }
    }
}
