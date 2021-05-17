package com.grupo4.inmobiliaria.ui.ui.contratos;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.grupo4.inmobiliaria.modelo.Contrato;
import com.grupo4.inmobiliaria.modelo.Inmueble;
import com.grupo4.inmobiliaria.modelo.Pago;
import com.grupo4.inmobiliaria.request.ApiClient;

import java.util.List;

public class ContratoViewModel extends ViewModel {

    public MutableLiveData<Contrato> contratoMutable;
    public MutableLiveData<List<Pago>> pagosMutable;

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
        Contrato contrato = ApiClient.getApi().obtenerContratoVigente((Inmueble) bundle.getSerializable("inmueble"));
        contratoMutable.setValue(contrato);
    }

    public void LeerPagosContrato(Contrato contrato){
        List<Pago> pagos = ApiClient.getApi().obtenerPagos(contrato);
        pagosMutable.setValue(pagos);
    }
}
