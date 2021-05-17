package com.grupo4.inmobiliaria.ui.ui.cerrar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.grupo4.inmobiliaria.MainActivity;
import com.grupo4.inmobiliaria.R;
import com.grupo4.inmobiliaria.ui.MenuNavegacion;


public class CerrarSesionFragment extends Fragment {

    private boolean cerrar = false;
    private CerrarSesionViewModel cerrarSesionViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cerrarSesionViewModel =
                new ViewModelProvider(this).get(CerrarSesionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cerrarsesion, container, false);


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        new AlertDialog.Builder(getActivity())
                .setTitle("Cerrar sesión")
                .setMessage("¿Seguro que quiere cerrar sesión?")
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if(!cerrar)
                            startActivity(new Intent(getActivity(), MenuNavegacion.class));
                    }
                })
                .setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cerrar = true;
                        startActivity(new Intent(getActivity(), MainActivity.class));
                    }
                }).show();
    }
}