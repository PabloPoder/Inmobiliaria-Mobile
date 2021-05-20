package com.grupo4.inmobiliaria.ui.ui.contratos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grupo4.inmobiliaria.R;
import com.grupo4.inmobiliaria.modelo.Inquilino;

public class TabInquilinoFragment extends Fragment {
    private Inquilino inquilino;
    private TextView tvIdInquilino, tvNombreCompletoInquilino, tvDniInquilino, tvEmailInquilino, tvTelefonoInquilino;

    public TabInquilinoFragment(Inquilino inquilino){
        this.inquilino = inquilino;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tab_inquilino, container, false);
        inicializarVista(root);

        return root;
    }

    private void inicializarVista(View root){
        tvIdInquilino = root.findViewById(R.id.tvIdInquilino);
        tvIdInquilino.setText("Detalles del inquilino #" + inquilino.getId());
        tvNombreCompletoInquilino = root.findViewById(R.id.tvNombreCompletoInquilino);
        tvNombreCompletoInquilino.setText("Nombre: " + inquilino.getNombre() + " " + inquilino.getApellido());
        tvDniInquilino = root.findViewById(R.id.tvDniInquilino);
        tvDniInquilino.setText("DNI: " + inquilino.getDni());
        tvEmailInquilino = root.findViewById(R.id.tvEmailInquilino);
        tvEmailInquilino.setText("E-Mail: " + inquilino.getEmail());
        tvTelefonoInquilino = root.findViewById(R.id.tvTelefonoInquilino);
        tvTelefonoInquilino.setText("Teléfono: " + inquilino.getTelefono());
    }
}