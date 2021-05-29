package com.grupo4.inmobiliaria.ui.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.common.api.Api;
import com.grupo4.inmobiliaria.modelo.Inmueble;
import com.grupo4.inmobiliaria.modelo.Propietario;
import com.grupo4.inmobiliaria.request.ApiClient;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmueblesViewModel extends AndroidViewModel {
    public MutableLiveData<List<Inmueble>> inmueblesMutable;
    private Context context;

    public InmueblesViewModel (@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<List<Inmueble>> getInmueblesMutable(){
        if (inmueblesMutable == null){
            inmueblesMutable = new MutableLiveData<>();
        }
        return inmueblesMutable;
    }

    public void LeerInmuebles(){

        String token = ApiClient.getToken(context);

        Call<List<Inmueble>> inmuebles = ApiClient.getMyApiClient().Inmuebles(token);
        inmuebles.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse (Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if(response.isSuccessful())
                    inmueblesMutable.postValue(response.body());
                else
                    Toast.makeText(context, "Error al cargar los inmuebles", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure (Call<List<Inmueble>> call, Throwable t) {
                Toast.makeText(context, "Error al cargar los inmuebles", Toast.LENGTH_LONG).show();
            }
        });
    }
}