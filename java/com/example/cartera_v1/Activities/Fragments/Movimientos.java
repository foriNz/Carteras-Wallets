package com.example.cartera_v1.Activities.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.cartera_v1.Activities.Transacciones;
import com.example.cartera_v1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Movimientos extends Fragment {
    ImageView iv_circuloCategoria;
    FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movimientos, container, false);
        fab = view.findViewById(R.id.fab);
        agregarFuncionalidades();
        return view;
    }
    private void agregarFuncionalidades() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Transacciones.class);
                getContext().startActivity(intent);
            }
        });
    }
}
