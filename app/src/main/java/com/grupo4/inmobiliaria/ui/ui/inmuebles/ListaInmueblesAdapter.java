package com.grupo4.inmobiliaria.ui.ui.inmuebles;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.grupo4.inmobiliaria.R;
import com.grupo4.inmobiliaria.modelo.Inmueble;

import java.util.List;

public class ListaInmueblesAdapter extends ArrayAdapter<Inmueble> {
    private Context context;
    private List<Inmueble> inmuebles;
    private LayoutInflater inflater;

    int vistaDetallesOnClick;

    public ListaInmueblesAdapter(@NonNull Context context, int resource, @NonNull List<Inmueble> objects, LayoutInflater layoutInflater, int vistaDetallesOnClick) {
        super(context, resource, objects);
        this.context = context;
        this.inmuebles = objects;
        this.inflater = layoutInflater;

        this.vistaDetallesOnClick = vistaDetallesOnClick;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //super.getView(position, convertView, parent);
        convertView = convertView != null ? convertView : inflater.inflate(R.layout.list_item_inmueble, parent, false);
        Inmueble inmueble = inmuebles.get(position);

        TextView tvDireccion = convertView.findViewById(R.id.tvDireccion);
        TextView tvPrecio = convertView.findViewById(R.id.tvPrecio);
        ImageView ivFoto = convertView.findViewById(R.id.ivFoto);

        tvDireccion.setText(inmueble.getDireccion());
        tvPrecio.setText("$"+inmueble.getPrecio());
        Glide.with(getContext()).load(inmueble.getImagen()).diskCacheStrategy(DiskCacheStrategy.ALL).into(ivFoto);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putSerializable("inmueble", inmueble);
                Navigation.findNavController((Activity)context, R.id.nav_host_fragment).navigate(vistaDetallesOnClick, b);
            }
        });

        return convertView;
    }
}
