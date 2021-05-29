package com.grupo4.inmobiliaria.ui.ui.inmuebles;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.grupo4.inmobiliaria.R;
import com.grupo4.inmobiliaria.modelo.Contrato;
import com.grupo4.inmobiliaria.modelo.Inmueble;

import java.util.List;

public class ListaInmueblesAdapter extends ArrayAdapter {
    private Context context;
    private List contratos;
    private LayoutInflater inflater;

    int vistaDetallesOnClick;

    public ListaInmueblesAdapter(@NonNull Context context, int resource, @NonNull List<Object> objects, LayoutInflater layoutInflater, int vistaDetallesOnClick) {
        super(context, resource, objects);
        this.context = context;
        this.contratos = objects;
        this.inflater = layoutInflater;

        this.vistaDetallesOnClick = vistaDetallesOnClick;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //super.getView(position, convertView, parent);
        convertView = convertView != null ? convertView : inflater.inflate(R.layout.list_item_inmueble, parent, false);

        Inmueble inmueble = null;

        if(contratos.get(position) instanceof Contrato)
        {
            inmueble = ((Contrato)contratos.get(position)).getInmueble();
        }else{
            inmueble = (Inmueble)contratos.get(position);
        }

        TextView tvDireccion = convertView.findViewById(R.id.tvDireccion);
        TextView tvPrecio = convertView.findViewById(R.id.tvPrecio);
        ImageView ivFoto = convertView.findViewById(R.id.ivFoto);

        tvDireccion.setText(inmueble.getDireccion());
        tvPrecio.setText("$"+inmueble.getPrecio());
        Glide.with(getContext())
                .load("http://192.168.1.105:45455/" + inmueble.getFoto())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivFoto);
                //.placeholder(@Drawable.) imagen defecto

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                if(contratos.get(position) instanceof Contrato){
                    b.putSerializable("Contrato", (Contrato)contratos.get(position));
                }else{
                    b.putSerializable("Inmueble", (Inmueble)contratos.get(position));
                }

                Navigation.findNavController((Activity)context, R.id.nav_host_fragment).navigate(vistaDetallesOnClick, b);
            }
        });

        return convertView;
    }
}
