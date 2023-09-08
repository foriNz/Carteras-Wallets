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

public class CategoriasIngresos extends Fragment {
    RecyclerView rv_categorias;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categorias_ingresos, container, false);
        rv_categorias = view.findViewById(R.id.rv_admin_categorias_ingresos);
        refrescarRecyclerView();
        return view;
    }

    private void refrescarRecyclerView() {
        rv_categorias.setLayoutManager(new LinearLayoutManager(getContext()));
        BDCategorias bdCategorias = new BDCategorias(getContext());
        try {
            rv_categorias.setAdapter(new CategoriasAdapter_Administrador(bdCategorias.getCategoriasIngreso(),getContext()));

        } catch (Exception e) {
            e.toString();
        }
    }

    @Override
    public void onResume() {
        refrescarRecyclerView();
        super.onResume();
    }
}