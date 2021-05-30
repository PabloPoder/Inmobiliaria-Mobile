package com.grupo4.inmobiliaria.ui.ui.contratos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.grupo4.inmobiliaria.modelo.Contrato;
import com.grupo4.inmobiliaria.modelo.Inmueble;
import com.grupo4.inmobiliaria.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratosViewModel extends AndroidViewModel {

    public MutableLiveData<List<Contrato>> inmueblesMutable;
    private Context context;

    public ContratosViewModel (@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<List<Contrato>> getInmueblesMutable(){
        if (inmueblesMutable == null){
            inmueblesMutable = new MutableLiveData<>();
        }
        return inmueblesMutable;
    }

    public void LeerInmueblesAlquilados() {
        Call<List<Contrato>> respuestaToken = ApiClient.getMyApiClient().ContratosVigentes(ApiClient.getToken(context));
        respuestaToken.enqueue(new Callback<List<Contrato>>() {
            @Override
            public void onResponse (Call<List<Contrato>> call, Response<List<Contrato>> response) {
                if (response.isSuccessful())
                    inmueblesMutable.postValue(response.body());
            }
            @Override
            public void onFailure (Call<List<Contrato>> call, Throwable t) {
                Toast.makeText(context, "Error al cargar los inmuebles", Toast.LENGTH_LONG).show();
            }
        });
    }
}


