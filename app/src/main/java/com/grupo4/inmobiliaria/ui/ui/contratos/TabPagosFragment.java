package com.grupo4.inmobiliaria.ui.ui.contratos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.service.autofill.TextValueSanitizer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.grupo4.inmobiliaria.R;
import com.grupo4.inmobiliaria.modelo.Pago;

import java.util.List;

public class TabPagosFragment extends Fragment {
    private List<Pago> pagos;
    private ListView lvPagos;
    private TextView tvNoPagos;

    public TabPagosFragment(List<Pago> pagos){
        this.pagos = pagos;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tab_pagos, container, false);
        inicializarVista(root);

        if (pagos.isEmpty()){
            tvNoPagos.setVisibility(View.VISIBLE);
            tvNoPagos.setText("No se realizaron pagos en este contrato.");
        } else {
            tvNoPagos.setVisibility(View.INVISIBLE);
            ArrayAdapter<Pago> adapter = new ListaPagosAdapter(getContext(), R.layout.list_item_pago, pagos, getLayoutInflater());
            lvPagos.setAdapter(adapter);
        }

        return root;
    }

    private void inicializarVista(View root){
        tvNoPagos = root.findViewById(R.id.tvNoPagos);
        lvPagos = root.findViewById(R.id.lvPagos);
    }
}