package com.grupo4.inmobiliaria.ui.ui.contratos;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.grupo4.inmobiliaria.modelo.Inmueble;
import com.grupo4.inmobiliaria.request.ApiClient;

import java.util.ArrayList;

public class ContratosViewModel extends ViewModel {

    public MutableLiveData<ArrayList<Inmueble>> inmueblesMutable;

    public LiveData<ArrayList<Inmueble>> getInmueblesMutable(){
        if (inmueblesMutable == null){
            inmueblesMutable = new MutableLiveData<>();
        }
        return inmueblesMutable;
    }

    public void LeerInmueblesAlquilados(){
        ApiClient api = ApiClient.getApi();
        ArrayList<Inmueble> inmuebles = api.obtenerPropiedadesAlquiladas();

        inmueblesMutable.setValue(inmuebles);
    }
}