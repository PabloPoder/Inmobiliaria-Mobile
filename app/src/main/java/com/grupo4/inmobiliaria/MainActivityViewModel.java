package com.grupo4.inmobiliaria;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.grupo4.inmobiliaria.modelo.Propietario;
import com.grupo4.inmobiliaria.request.ApiClient;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<String> mensajeMutable;
    private MutableLiveData<Boolean> resultadoMutable;

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
            ApiClient api = ApiClient.getApi();
            Propietario propietario = api.login(mail, clave);

            if (propietario == null){
                mensajeMutable.setValue("Credenciales incorrectas");
            } else {
                resultadoMutable.setValue(true);
            }
        } else {
            mensajeMutable.setValue("Datos insuficientes");
        }
    }
}
