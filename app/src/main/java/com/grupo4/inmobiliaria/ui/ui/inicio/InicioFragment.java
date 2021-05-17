package com.grupo4.inmobiliaria.ui.ui.inicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.grupo4.inmobiliaria.R;


public class InicioFragment extends Fragment {

    private static final LatLng INMOBILIARIA = new LatLng(-32.410615, -65.009314);
    private GoogleMap map;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        ((SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map)).getMapAsync(new Mapa());

        return view;
    }

    private class Mapa implements OnMapReadyCallback {

        @Override
        public void onMapReady (GoogleMap googleMap) {

            map = googleMap;

            map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

            CameraPosition camPosition = new CameraPosition.Builder()
                    .target(INMOBILIARIA) // Objetivo en el mapa
                    .zoom(17)             // Zoom
                    .bearing(45)          // Angulo de inclinacion
                    .tilt(60)             // Angulo de inclinacion
                    .build();

            // Pasar efectos al mapa
            CameraUpdate camUpdate = CameraUpdateFactory.newCameraPosition(camPosition);
            map.animateCamera(camUpdate);

            // Agregar marcador
            map.addMarker(new MarkerOptions()
                        .position(INMOBILIARIA))
                        .setTitle("Inmobiliaria Grupo 4");

        }
    }
}