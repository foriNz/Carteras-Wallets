package com.example.cartera_v1.Activities.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cartera_v1.Activities.CreacionCartera;
import com.example.cartera_v1.Adaptadores.CarteraAdapter_Transaccion;
import com.example.cartera_v1.BBDD.BDCarteras;
import com.example.cartera_v1.R;
import com.github.mikephil.charting.charts.LineChart;

public class Billeteras extends Fragment {
    LineChart volumeReportChart;
    RecyclerView rv_billeteras;
    CardView cv_creacion_cartera;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_billeteras, container, false);
        volumeReportChart = view.findViewById(R.id.lineChart_billetera);
        cv_creacion_cartera = view.findViewById(R.id.cv_creacion_cartera);
        rv_billeteras = view.findViewById(R.id.rv_billeteras_billetera);
        agregarFuncionalidades();
        setLineChart();
        return view;
    }

    private void agregarFuncionalidades() {
        BDCarteras bdCarteras = new BDCarteras(getContext());
        rv_billeteras.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_billeteras.setAdapter(new CarteraAdapter_Transaccion(bdCarteras.getCarteras(),getContext()));
        cv_creacion_cartera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CreacionCartera.class);
                getContext().startActivity(intent);
            }
        });
    }

    private void setLineChart() {
        // TODO: 02/09/2023 hacer linechart
        volumeReportChart.setTouchEnabled(true);
    }
}