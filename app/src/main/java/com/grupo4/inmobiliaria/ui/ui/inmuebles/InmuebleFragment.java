package com.grupo4.inmobiliaria.ui.ui.inmuebles;

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
import com.grupo4.inmobiliaria.modelo.Contrato;
import com.grupo4.inmobiliaria.modelo.Inmueble;

public class InmuebleFragment extends Fragment {

    private InmuebleViewModel inmuebleViewModel;

    private TextView tvDireccion2, tvPrecio2, tvSuperficie, tvAmbientes, tvLatitud, tvLongitud, tvInmuebleId;
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
                tvSuperficie.setText("Superficie: " + inmueble.getSuperficie());
                tvAmbientes.setText("Ambientes: "+inmueble.getAmbientes());
                tvLatitud.setText("Latitud: " + inmueble.getLatitud());
                tvLongitud.setText("Longitud: " + inmueble.getLongitud());
                tvInmuebleId.setText("Detalles del inmueble #"+inmueble.getId());
                Glide.with(getContext())
                        .load("http://192.168.1.105:45455/" + inmueble.getFoto())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(ivFoto2);

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
        inmuebleViewModel.getContratoVigenteMutable().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contratoVigente) {
                btContratoInmueble.setVisibility(contratoVigente != null ? View.VISIBLE : View.INVISIBLE);
                if (contratoVigente != null){
                    btContratoInmueble.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Navegar a los detalles del contrato vigente de este inmueble
                            Bundle b = new Bundle();
                            b.putSerializable("Contrato", contratoVigente);
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
        tvSuperficie = root.findViewById(R.id.tvSuperficie);
        tvLatitud = root.findViewById(R.id.tvLatitud);
        tvLongitud = root.findViewById(R.id.tvLongitud);
        tvAmbientes = root.findViewById(R.id.tvLongitud);
        ivFoto2 = root.findViewById(R.id.ivFoto2);
        swEstado = root.findViewById(R.id.swEstado);
        btContratoInmueble = root.findViewById(R.id.btContratoInmueble);
        tvInmuebleId = root.findViewById(R.id.tvInmuebleId);
    }
}
