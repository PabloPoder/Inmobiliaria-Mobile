package com.grupo4.inmobiliaria.ui.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.grupo4.inmobiliaria.modelo.Contrato;
import com.grupo4.inmobiliaria.modelo.Inmueble;
import com.grupo4.inmobiliaria.modelo.Pago;
import com.grupo4.inmobiliaria.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleViewModel extends AndroidViewModel {

    public MutableLiveData<Inmueble> inmuebleMutable;
    public MutableLiveData<Contrato> contratoVigenteMutable;
    private Context context;

    public InmuebleViewModel (@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Inmueble> getInmuebleMutable(){
        if (inmuebleMutable == null){
            inmuebleMutable = new MutableLiveData<>();
        }
        return inmuebleMutable;
    }

    public LiveData<Contrato> getContratoVigenteMutable(){
        if (contratoVigenteMutable == null){
            contratoVigenteMutable = new MutableLiveData<>();
        }
        return contratoVigenteMutable;
    }

    public void LeerInmueble(Bundle bundle){
        Inmueble inmueble = (Inmueble)bundle.getSerializable("inmueble");
        inmuebleMutable.setValue(inmueble);
    }

    public void CambioEstado(Inmueble inmueble)
    {
        inmueble.setEstado(!inmueble.isEstado());
        String token = ApiClient.getToken(context);

        Call<Inmueble> i = ApiClient.getMyApiClient().EditarInmueble(inmueble.getId(), inmueble, token);
        i.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse (Call<Inmueble> call, Response<Inmueble> response) {
                if(response.isSuccessful()) {
                    inmuebleMutable.postValue(response.body());
                    Toast.makeText(context, "Edición exitosa", Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(context, "Error al editar inmueble", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure (Call<Inmueble> call, Throwable t) {
                Toast.makeText(context, "Error al editar inmueble", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void ConsultarContratoVigente(Inmueble inmueble)
    {
        String token = ApiClient.getToken(context);

        Call<Contrato> contrato = ApiClient.getMyApiClient().ContratoVigentePorInmueble(inmueble.getId(), token);
        contrato.enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse (Call<Contrato> call, Response<Contrato> response) {
                if(response.isSuccessful())
                    contratoVigenteMutable.postValue(response.body());
                else
                    Toast.makeText(context, "Error al cargar los pagos", Toast.LENGTH_LONG).show();
                    contratoVigenteMutable.setValue(null);
            }
            @Override
            public void onFailure (Call<Contrato> call, Throwable t) {
                Toast.makeText(context, "Error al cargar los pagos", Toast.LENGTH_LONG).show();
                contratoVigenteMutable.setValue(null);
            }
        });
    }
}
