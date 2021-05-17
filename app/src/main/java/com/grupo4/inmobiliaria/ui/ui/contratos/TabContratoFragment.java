package com.grupo4.inmobiliaria.ui.ui.contratos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.grupo4.inmobiliaria.R;
import com.grupo4.inmobiliaria.modelo.Contrato;

public class TabContratoFragment extends Fragment {
    private TextView tvFechaInicio, tvFechaFin, tvInquilino, tvInmueble, tvMonto, tvContratoId;
    private Button btInmuebleContrato;
    private Contrato contrato;

    public TabContratoFragment(Contrato contrato) {
        this.contrato = contrato;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tab_contrato, container, false);

        inicializarVista(root);

        return root;
    }

    private void inicializarVista(View root){
        tvFechaInicio = root.findViewById(R.id.tvFechaInicio);
        tvFechaFin = root.findViewById(R.id.tvFechaFin);
        tvMonto = root.findViewById(R.id.tvMontoAlquiler);
        tvInquilino = root.findViewById(R.id.tvInquilino);
        tvInmueble = root.findViewById(R.id.tvInmueble);
        tvContratoId = root.findViewById(R.id.tvContratoId);
        btInmuebleContrato = root.findViewById(R.id.btInmuebleContrato);

        tvFechaInicio.setText("Fecha de inicio: "+contrato.getFechaInicio());
        tvFechaFin.setText("Fecha de fin: "+contrato.getFechaFin());
        tvMonto.setText("Precio por mes: $"+contrato.getMontoAlquiler());
        tvInquilino.setText("Inquilino: "+contrato.getInquilino().getNombre() + " "+ contrato.getInquilino().getApellido());
        tvInmueble.setText("Inmueble: "+contrato.getInmueble().getDireccion());
        tvContratoId.setText("Detalles del contrato #"+contrato.getIdContrato());
        btInmuebleContrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a los detalles del inmueble de este contrato
                Bundle b = new Bundle();
                b.putSerializable("inmueble", contrato.getInmueble());
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_inmueble, b);
            }
        });
    }
}