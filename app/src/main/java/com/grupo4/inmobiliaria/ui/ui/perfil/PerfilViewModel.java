package com.grupo4.inmobiliaria.ui.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.grupo4.inmobiliaria.modelo.Inmueble;
import com.grupo4.inmobiliaria.modelo.Propietario;
import com.grupo4.inmobiliaria.request.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {

    public MutableLiveData<Propietario> propietarioMutable;
    private Context context;

    public PerfilViewModel (@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Propietario> getPropietarioMutable(){
        if (propietarioMutable == null){
            propietarioMutable = new MutableLiveData<>();
        }
        return propietarioMutable;
    }

    public void LeerPropietario(){

        String token = ApiClient.getToken(context);
        Call<Propietario> p = ApiClient.getMyApiClient().PropietarioActual(token);
        Log.d("Token" , token);
        p.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse (Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful())
                    propietarioMutable.postValue(response.body());
                else
                    Toast.makeText(context, "Error al cargar el perfil", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure (Call<Propietario> call, Throwable t) {
                Toast.makeText(context, "Error en la peticion a la api", Toast.LENGTH_LONG).show();
            }
        });
    }
}