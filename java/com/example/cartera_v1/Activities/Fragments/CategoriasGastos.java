package com.example.cartera_v1.Activities.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cartera_v1.Adaptadores.CategoriasAdapter_Administrador;
import com.example.cartera_v1.BBDD.BDCategorias;
import com.example.cartera_v1.R;

public class CategoriasGastos extends Fragment {
    RecyclerView rv_categorias;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categorias_gastos, container, false);
        rv_categorias = view.findViewById(R.id.rv_admin_categorias_gastos);

        refrescarRecycler();
        return view;
    }

    private void refrescarRecycler() {
        rv_categorias.setLayoutManager(new LinearLayoutManager(getContext()));
        BDCategorias bdCategorias = new BDCategorias(getContext());
        try {
            rv_categorias.setAdapter(new CategoriasAdapter_Administrador(bdCategorias.getCategoriasGasto(),getContext()));

        } catch (Exception e) {
            e.toString();
        }
    }

    @Override
    public void onResume() {
        refrescarRecycler();
        super.onResume();
    }
}