package com.example.cartera_v1.Activities.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.cartera_v1.Activities.CreacionMeta;
import com.example.cartera_v1.Adaptadores.MetasAdapter;
import com.example.cartera_v1.BBDD.BDMetas;
import com.example.cartera_v1.Entidades.Meta;
import com.example.cartera_v1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class Metas extends Fragment {

    // METAS: al crear metas pueden ser generales (ahorro mensual)
    // meta de gastos o ingresos generales (o de una categoria en especifico)

    FloatingActionButton fabNuevaMeta;
    RecyclerView rv_metas;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_metas, container, false);
        fabNuevaMeta = view.findViewById(R.id.fab_frag_metas);
        rv_metas = view.findViewById(R.id.rv_lista_metas);
        agregarFuncionalidades();
        return view;
    }

    private void agregarFuncionalidades() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        BDMetas bdMetas = new BDMetas(getContext());
        rv_metas.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_metas.setAdapter(new MetasAdapter(getContext(), bdMetas.getMetas(year,month)));
        fabNuevaMeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), CreacionMeta.class);
                startActivity(i);
            }
        });

    }
}