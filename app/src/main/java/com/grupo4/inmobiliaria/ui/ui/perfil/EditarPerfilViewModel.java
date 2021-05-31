package com.grupo4.inmobiliaria.ui.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.util.Patterns;
import android.widget.Toast;

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

public class EditarPerfilViewModel extends AndroidViewModel {

    public MutableLiveData<Propietario> propietarioMutable;
    public MutableLiveData<String> errorMutable;
    private Context context;

    public EditarPerfilViewModel (@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

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
        String token = ApiClient.getToken(context);
        Call<Propietario> p = ApiClient.getMyApiClient().PropietarioActual(token);
        p.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse (Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful())
                    propietarioMutable.postValue(response.body());
                else
                    errorMutable.setValue("Error al cargar el perfil");
            }

            @Override
            public void onFailure (Call<Propietario> call, Throwable t) {
                errorMutable.setValue("Error al cargar el perfil");
            }
        });
    }

    public void ModificarPropietario(Propietario p){
        if(p.getNombre().isEmpty() || p.getApellido().isEmpty() || p.getDni().isEmpty() || p.getEmail().isEmpty() || p.getTelefono().isEmpty()){
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

            String token = ApiClient.getToken(context);
            Call<Propietario> propietario = ApiClient.getMyApiClient().EditarPerfil(p.getId(), p, token);
            propietario.enqueue(new Callback<Propietario>() {
                @Override
                public void onResponse (Call<Propietario> call, Response<Propietario> response) {
                    if(response.isSuccessful()){
                        errorMutable.setValue("EXITO");
                    }
                }

                @Override
                public void onFailure (Call<Propietario> call, Throwable t) {
                    errorMutable.setValue("Error al editar el usuario");
                }
            });

        }
    }
}