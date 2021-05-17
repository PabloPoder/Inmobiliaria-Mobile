package com.grupo4.inmobiliaria.ui.ui.cerrar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CerrarSesionViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CerrarSesionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Cerrar Sesión");
    }

    public LiveData<String> getText() {
        return mText;
    }
}