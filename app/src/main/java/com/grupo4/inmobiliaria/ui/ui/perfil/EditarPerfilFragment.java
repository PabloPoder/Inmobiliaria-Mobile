package com.grupo4.inmobiliaria.ui.ui.perfil;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.grupo4.inmobiliaria.MainActivity;
import com.grupo4.inmobiliaria.R;
import com.grupo4.inmobiliaria.modelo.Propietario;
import com.grupo4.inmobiliaria.ui.MenuNavegacion;

public class EditarPerfilFragment extends Fragment {

    private EditarPerfilViewModel mViewModel;

    public static EditarPerfilFragment newInstance() {
        return new EditarPerfilFragment();
    }

    private EditarPerfilViewModel editarPerfilViewModel;
    public TextView tvUsuarioId;
    public EditText etEditarNombreUsuario;
    public EditText etEditarApellidoUsuario;
    public EditText etEditarDniUsuario;
    public EditText etEditarEmailUsuario;
    public EditText etEditarTelefonoUsuario;
    public Button btGuardar;
    private Propietario propietarioActual;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        editarPerfilViewModel = new ViewModelProvider(this).get(EditarPerfilViewModel.class);
        View root = inflater.inflate(R.layout.fragment_editar_perfil, container, false);

        InicializarVista(root);
        editarPerfilViewModel.getErrorMutable().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s == "EXITO")
                    startActivity(new Intent(getActivity(), MainActivity.class));
                else{
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Error")
                            .setMessage(s)
                            .setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                }
            }
        });
        editarPerfilViewModel.getPropietarioMutable().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                propietarioActual = propietario;
                tvUsuarioId.setText("Editando el usuario #"+propietario.getId());
                etEditarNombreUsuario.setText(propietario.getNombre());
                etEditarApellidoUsuario.setText(propietario.getApellido());
                etEditarDniUsuario.setText(String.valueOf(propietario.getDni()));
                etEditarEmailUsuario.setText((propietario.getEmail()));
                etEditarTelefonoUsuario.setText(propietario.getTelefono());
            }
        });
        editarPerfilViewModel.ObtenerPropietario();

        return root;
    }

    private void InicializarVista(View v){
        tvUsuarioId = v.findViewById(R.id.tvUsuarioId);
        etEditarNombreUsuario = v.findViewById(R.id.etEditarNombreUsuario);
        etEditarApellidoUsuario = v.findViewById(R.id.etEditarApellidoUsuario);
        etEditarDniUsuario = v.findViewById(R.id.etEditarDniUsuario);
        etEditarEmailUsuario = v.findViewById(R.id.etEditarEmailUsuario);
        etEditarTelefonoUsuario = v.findViewById(R.id.etEditarTelefonoUsuario);
        btGuardar = v.findViewById(R.id.btGuardar);

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Propietario p = propietarioActual;

                p.setNombre(etEditarNombreUsuario.getText().toString());
                p.setApellido(etEditarApellidoUsuario.getText().toString());
                p.setDni(TextUtils.isEmpty(etEditarDniUsuario.getText())? -1 : Long.parseLong(etEditarDniUsuario.getText().toString()));
                p.setEmail(etEditarEmailUsuario.getText().toString());
                p.setTelefono(etEditarTelefonoUsuario.getText().toString());

                editarPerfilViewModel.ModificarPropietario(p);
            }
        });
    }

}