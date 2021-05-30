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
        Contrato contrato = (Contrato)bundle.getSerializable("Contrato");
        contratoMutable.setValue(contrato);
    }

    public void LeerPagosContrato(Contrato contrato){

        String token = ApiClient.getToken(context);

        Call<List<Pago>> pagos = ApiClient.getMyApiClient().Pagos(contrato.getId(), token);
        pagos.enqueue(new Callback<List<Pago>>() {
            @Override
            public void onResponse (Call<List<Pago>> call, Response<List<Pago>> response) {
                if(response.isSuccessful())
                    pagosMutable.postValue(response.body());
                else
                    Toast.makeText(context, "Error al cargar los pagos", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure (Call<List<Pago>> call, Throwable t) {
                Toast.makeText(context, "Error al cargar los pagos", Toast.LENGTH_LONG).show();
            }
        });
    }


}


