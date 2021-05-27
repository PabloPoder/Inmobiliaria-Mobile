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
import com.grupo4.inmobiliaria.modelo.Pago;
import com.grupo4.inmobiliaria.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratoViewModel extends AndroidViewModel {

    public MutableLiveData<Contrato> contratoMutable;
    public MutableLiveData<List<Pago>> pagosMutable;
    private Context context;

    public ContratoViewModel (@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<List<Pago>> getPagosMutable(){
        if (pagosMutable == null){
            pagosMutable = new MutableLiveData<>();
        }
        return pagosMutable;
    }

    public LiveData<Contrato> getContratoMutable(){
        if (contratoMutable == null){
            contratoMutable = new MutableLiveData<>();
        }
        return contratoMutable;
    }

    public void LeerContrato(Bundle bundle){

        Call<Contrato> contrato = ApiClient.getMyApiClient().Contrato(id, ApiClient.getToken(context));
        contrato.enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse (Call<Contrato> call, Response<Contrato> response) {
                if (response.isSuccessful()) {
                    Log.d("salida", "on response");
                    contratoMutable.postValue(response.body());
                }else{
                    Log.d("salida", response.message());
                }
            }

            @Override
            public void onFailure (Call<Contrato> call, Throwable t) {
                Toast.makeText(context, "Error al intentar cargar el contrato", Toast.LENGTH_LONG).show();
            }
        });


    }

//    public void LeerPagosContrato(Contrato contrato){
//        List<Pago> pagos = ApiClient.getApi().obtenerPagos(contrato);
//        pagosMutable.setValue(pagos);
//    }
}
