package com.grupo4.inmobiliaria.ui.ui.perfil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.grupo4.inmobiliaria.modelo.Inmueble;
import com.grupo4.inmobiliaria.modelo.Propietario;
import com.grupo4.inmobiliaria.request.ApiClient;

import java.util.ArrayList;

public class PerfilViewModel extends ViewModel {
    public MutableLiveData<Propietario> propietarioMutable;

    public LiveData<Propietario> getPropietarioMutable(){
        if (propietarioMutable == null){
            propietarioMutable = new MutableLiveData<>();
        }
        return propietarioMutable;
    }

    public void LeerPropietario(){
        ApiClient api = ApiClient.getApi();
        Propietario p = api.obtenerUsuarioActual();

        propietarioMutable.setValue(p);
    }
}