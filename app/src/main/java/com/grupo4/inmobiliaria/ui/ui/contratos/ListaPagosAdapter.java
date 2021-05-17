package com.grupo4.inmobiliaria.ui.ui.contratos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.grupo4.inmobiliaria.R;
import com.grupo4.inmobiliaria.modelo.Pago;

import java.util.List;

public class ListaPagosAdapter extends ArrayAdapter<Pago> {
    private Context context;
    private List<Pago> pagos;
    private LayoutInflater inflater;

    public ListaPagosAdapter(@NonNull Context context, int resource, @NonNull List<Pago> objects, LayoutInflater layoutInflater) {
        super(context, resource, objects);
        this.context = context;
        this.pagos = objects;
        this.inflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = convertView != null ? convertView : inflater.inflate(R.layout.list_item_pago, parent, false);
        Pago pago = pagos.get(position);

        TextView tvPagoId = convertView.findViewById(R.id.tvPagoId);
        TextView tvPagoFecha = convertView.findViewById(R.id.tvPagoFecha);
        TextView tvPagoNumero = convertView.findViewById(R.id.tvPagoNumero);
        TextView tvPagoImporte = convertView.findViewById(R.id.tvPagoImporte);

        tvPagoId.setText("#" + String.valueOf(pago.getIdPago()));
        tvPagoFecha.setText(pago.getFechaDePago());
        tvPagoNumero.setText("Nro de pago: " + String.valueOf(pago.getNumero()));
        tvPagoImporte.setText("$" + String.valueOf(pago.getImporte()));

        return convertView;
    }
}