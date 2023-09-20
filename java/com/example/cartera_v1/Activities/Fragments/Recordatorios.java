package com.example.cartera_v1.Activities.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cartera_v1.Activities.CreacionRecordatorio;
import com.example.cartera_v1.Adaptadores.RecordatorioAlarmaAdapter;
import com.example.cartera_v1.BBDD.BDRecordatorio;
import com.example.cartera_v1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Recordatorios extends Fragment {

    FloatingActionButton fab_nuevo_recordatorio;
    RecyclerView lista_recordatorios;
    RecordatorioAlarmaAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recordatorios, container, false);
        fab_nuevo_recordatorio = view.findViewById(R.id.fab_frag_recordatorios);
        lista_recordatorios = view.findViewById(R.id.rv_frag_recordatorios);
        agregarFuncionalidades();
        refrescarListView();
        return view;

    }

    private void agregarFuncionalidades() {
        fab_nuevo_recordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Recordatorios.this.getContext(), CreacionRecordatorio.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        refrescarListView();
    }

    private void refrescarListView() {
        BDRecordatorio bdRecordatorio = new BDRecordatorio(getContext());
        lista_recordatorios.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecordatorioAlarmaAdapter(bdRecordatorio.getRecordatorios(),getContext());
        lista_recordatorios.setAdapter(adapter);
    }

   }