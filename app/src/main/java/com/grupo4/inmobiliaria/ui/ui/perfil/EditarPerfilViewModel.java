package com.grupo4.inmobiliaria.ui.ui.perfil;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.grupo4.inmobiliaria.modelo.Propietario;
import com.grupo4.inmobiliaria.request.ApiClient;

public class EditarPerfilViewModel extends ViewModel {
    public MutableLiveData<Propietario> propietarioMutable;
    public MutableLiveData<String> errorMutable;

    public LiveData<Propietario> getPropietarioMutable(){
        if (propietarioMutable == null){
            propietarioMutable = new MutableLiveData<>();
        }
        return propietarioMutable;
    }

    public LiveData<String> getErrorMutable(){
        if (errorMutable == null){
            errorMutable = new MutableLiveData<>();
        }
        return errorMutable;
    }

    public void ObtenerPropietario(){
        ApiClient api = ApiClient.getApi();
        Propietario p = api.obtenerUsuarioActual();

        propietarioMutable.setValue(p);
    }

    public void ModificarPropietario(Propietario p){
        if(p.getNombre().isEmpty() || p.getApellido().isEmpty() || p.getDni() == -1 || p.getEmail().isEmpty() || p.getTelefono().isEmpty()){
            errorMutable.setValue("Los campos no pueden estar vacios.");
        }else if(p.getDni().toString().length() != 8){
            errorMutable.setValue("El DNI ingresado no es válido (8 dígitos necesarios)");
        }else if(p.getNombre().length() > 16 || p.getNombre().length() < 3 || p.getApellido().length() > 16 || p.getApellido().length() < 3){
            errorMutable.setValue("El nombre/apellido ingresado no es válido (3 caracteres mínimo, 16 máximo)");
        }else if(!Patterns.EMAIL_ADDRESS.matcher(p.getEmail()).matches()){
            errorMutable.setValue("La dirección de correo electrónico ingresada no es válida.");
        }else if(p.getTelefono().length() > 15 || p.getTelefono().length() < 9){
            errorMutable.setValue("El número de teléfono ingresado no es válido (9-15 dígitos)");
        }else{
            ApiClient api = ApiClient.getApi();
            api.actualizarPerfil(p);
            errorMutable.setValue("EXITO");
        }
    }
}