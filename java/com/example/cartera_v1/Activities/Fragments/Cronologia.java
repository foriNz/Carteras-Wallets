package com.example.cartera_v1.Activities.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartera_v1.Activities.Transaccion;
import com.example.cartera_v1.Adaptadores.MovimientosAdapter_Cronologia;
import com.example.cartera_v1.BBDD.BDMovimientos;
import com.example.cartera_v1.R;
import com.github.mikephil.charting.charts.CombinedChart;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Cronologia extends Fragment {
    CombinedChart chart;
    TextView tv_balance;
    RecyclerView rv_listaMovimientos;
    FloatingActionButton fab;
    MovimientosAdapter_Cronologia adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cronologia, container, false);
        fab = view.findViewById(R.id.fab_frag_movimientos);
        chart = view.findViewById(R.id.chart_frag_movimientos);
        tv_balance = view.findViewById(R.id.tv_balance_frag_movimiento);
        rv_listaMovimientos = view.findViewById(R.id.elv_frag_movimientos);
        agregarFuncionalidades();
        return view;
    }
    private void agregarFuncionalidades() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Transaccion.class);
                getContext().startActivity(intent);
            }
        });

        refrescarRecyclerView();
    }

    private void refrescarRecyclerView() {
        BDMovimientos bdMovimientos = new BDMovimientos(getContext());

        adapter = new MovimientosAdapter_Cronologia(bdMovimientos.getMovimientosPorDias(), getContext());
        rv_listaMovimientos.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_listaMovimientos.setAdapter(adapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        refrescarRecyclerView();

    }
}
