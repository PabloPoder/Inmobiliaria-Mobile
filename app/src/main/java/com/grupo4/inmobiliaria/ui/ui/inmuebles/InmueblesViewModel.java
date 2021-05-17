package com.grupo4.inmobiliaria.ui.ui.inmuebles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.grupo4.inmobiliaria.modelo.Inmueble;
import com.grupo4.inmobiliaria.request.ApiClient;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class InmueblesViewModel extends ViewModel {
    public MutableLiveData<ArrayList<Inmueble>> inmueblesMutable;

    public LiveData<ArrayList<Inmueble>> getInmueblesMutable(){
        if (inmueblesMutable == null){
            inmueblesMutable = new MutableLiveData<>();
        }
        return inmueblesMutable;
    }

    public void LeerInmuebles(){
        ApiClient api = ApiClient.getApi();
        ArrayList<Inmueble> inmuebles = api.obtnerPropiedades();

        inmueblesMutable.setValue(inmuebles);
    }
}