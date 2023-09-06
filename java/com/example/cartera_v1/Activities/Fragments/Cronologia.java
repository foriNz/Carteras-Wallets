package com.example.cartera_v1.Activities.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartera_v1.Activities.Transacciones;
import com.example.cartera_v1.Adaptadores.MovimientosAdapter_Movimientos;
import com.example.cartera_v1.BBDD.BDMovimientos;
import com.example.cartera_v1.R;
import com.github.mikephil.charting.charts.CombinedChart;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Cronologia extends Fragment {
    CombinedChart chart;
    TextView tv_balance;
    RecyclerView rv_listaMovimientos;
    FloatingActionButton fab;
    MovimientosAdapter_Movimientos adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movimientos, container, false);
        fab = view.findViewById(R.id.fab_frag_movimientos);
        chart = view.findViewById(R.id.chart_frag_movimientos);
        tv_balance = view.findViewById(R.id.tv_balance_frag_movimiento);
        rv_listaMovimientos = view.findViewById(R.id.rv_frag_movimientos);
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
        rv_listaMovimientos.setLayoutManager(new LinearLayoutManager(getContext()));
        refrescarRecyclerView();
    }

    private void refrescarRecyclerView() {
        BDMovimientos bdMovimientos = new BDMovimientos(getContext());
        adapter = new MovimientosAdapter_Movimientos(bdMovimientos.getMovimientos(), getContext());
        rv_listaMovimientos.setAdapter(adapter);

    }
}
