package com.example.cartera_v1.Activities.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cartera_v1.R;

public class CategoriasIngresos extends Fragment {
    RecyclerView rv_categorias;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categorias_ingresos, container, false);
        rv_categorias = view.findViewById(R.id.rv_admin_categorias_ingresos);
        return view;
    }
}