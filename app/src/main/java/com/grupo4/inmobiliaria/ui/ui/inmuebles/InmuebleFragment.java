package com.grupo4.inmobiliaria.ui.ui.inmuebles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.grupo4.inmobiliaria.R;
import com.grupo4.inmobiliaria.modelo.Inmueble;
import com.grupo4.inmobiliaria.request.ApiClient;

public class InmuebleFragment extends Fragment {

    private InmuebleViewModel inmuebleViewModel;
    private TextView tvDireccion2, tvPrecio2, tvTipo, tvUso, tvAmbientes, tvInmuebleId;
    private ImageView ivFoto2;
    private Button btContratoInmueble;
    private Switch swEstado;

    private Inmueble inmueble;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        inmuebleViewModel =
                new ViewModelProvider(this).get(InmuebleViewModel.class);
        View root = inflater.inflate(R.layout.fragment_inmueble, container, false);

        InicializarVista(root);

        // Mostrar datos del inmueble
        inmuebleViewModel.getInmuebleMutable().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble i) {
                inmueble = i;
                tvDireccion2.setText("Dirección: "+inmueble.getDireccion());
                tvPrecio2.setText("Precio por mes: $"+inmueble.getPrecio());
                tvTipo.setText("Tipo: "+inmueble.getTipo());
                tvUso.setText("Uso: "+inmueble.getUso());
                tvAmbientes.setText("Ambientes: "+inmueble.getAmbientes());
                tvInmuebleId.setText("Detalles del inmueble #"+inmueble.getIdInmueble());
                Glide.with(getContext()).load(inmueble.getImagen()).diskCacheStrategy(DiskCacheStrategy.ALL).into(ivFoto2);

                swEstado.setChecked(inmueble.isEstado());
                swEstado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (inmueble != null)
                            inmuebleViewModel.CambioEstado(inmueble);
                    }
                });

                inmuebleViewModel.ConsultarContratoVigente(inmueble);
            }
        });

        // Mostrar boton "Ver Contrato" si el inmueble tiene o no un contrato vigente
        inmuebleViewModel.getContratoVigenteMutable().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean contratoVigente) {
                btContratoInmueble.setVisibility(contratoVigente ? View.VISIBLE : View.INVISIBLE);
                if (contratoVigente){
                    btContratoInmueble.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Navegar a los detalles del contrato vigente de este inmueble
                            Bundle b = new Bundle();
                            b.putSerializable("inmueble", inmueble);
                            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_contrato, b);
                        }
                    });
                }
            }
        });

        inmuebleViewModel.LeerInmueble(getArguments());
        return root;
    }

    private void InicializarVista(View root){
        tvDireccion2 = root.findViewById(R.id.tvDireccion2);
        tvPrecio2 = root.findViewById(R.id.tvPrecio2);
        tvTipo = root.findViewById(R.id.tvTipo);
        tvUso = root.findViewById(R.id.tvUso);
        tvAmbientes = root.findViewById(R.id.tvAmbientes);
        ivFoto2 = root.findViewById(R.id.ivFoto2);
        swEstado = root.findViewById(R.id.swEstado);
        btContratoInmueble = root.findViewById(R.id.btContratoInmueble);
        tvInmuebleId = root.findViewById(R.id.tvInmuebleId);
    }
}
