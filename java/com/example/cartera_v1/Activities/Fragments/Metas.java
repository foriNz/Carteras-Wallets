package com.example.cartera_v1.Activities.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.cartera_v1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Metas extends Fragment {

    // METAS: al crear metas pueden ser generales (ahorro mensual)
    // meta de gastos o ingresos generales (o de una categoria en especifico)

    FloatingActionButton fabNuevaMeta;
    RadioButton rb_ingreso, rb_gasto, rb_ahorro;
    EditText objetivo;
    Spinner sp_categorias;
    Button btn_aceptar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_metas, container, false);
        fabNuevaMeta = view.findViewById(R.id.fab_frag_metas);

        return view;
    }
}