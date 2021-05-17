package com.grupo4.inmobiliaria.ui.ui.contratos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.grupo4.inmobiliaria.R;
import com.grupo4.inmobiliaria.modelo.Contrato;
import com.grupo4.inmobiliaria.modelo.Inmueble;
import com.grupo4.inmobiliaria.modelo.Pago;
import com.grupo4.inmobiliaria.ui.ui.inmuebles.InmuebleViewModel;

import java.util.List;

public class ContratoFragment extends Fragment {

    private ContratoViewModel contratoViewModel;
    private Contrato contrato;
    private List<Pago> pagos;

    private AppBarLayout appBar;
    private ViewPager viewPage;
    private TabLayout tabLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        contratoViewModel =
                new ViewModelProvider(this).get(ContratoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_contrato, container, false);

        contratoViewModel.getContratoMutable().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato c) {
                contrato = c;
                contratoViewModel.LeerPagosContrato(c);
            }
        });
        contratoViewModel.getPagosMutable().observe(getViewLifecycleOwner(), new Observer<List<Pago>>() {
            @Override
            public void onChanged(List<Pago> p) {
                pagos = p;
                inicializarVista(root);
            }
        });
        contratoViewModel.LeerContrato(getArguments());
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void inicializarVista(View root){
        viewPage = root.findViewById(R.id.viewPage);
        appBar = root.findViewById(R.id.appBar);
        tabLayout = new TabLayout(getContext());
        appBar.addView(tabLayout);

        if (contrato != null){
            ViewPageAdapter vpa = new ViewPageAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            vpa.addFragment(new TabContratoFragment(contrato), "Contrato");
            vpa.addFragment(new TabInquilinoFragment(contrato.getInquilino()), "Inquilino");
            vpa.addFragment(new TabPagosFragment(pagos), "Pagos");

            viewPage.setAdapter(vpa);
            tabLayout.setupWithViewPager(viewPage);
        }
    }
}

