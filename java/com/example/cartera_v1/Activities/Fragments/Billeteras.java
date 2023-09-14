package com.example.cartera_v1.Activities.Fragments;

import android.content.Intent;
import android.graphics.Color;
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
import com.example.cartera_v1.BBDD.BDMovimientos;
import com.example.cartera_v1.Entidades.Model_Fecha_Movimientos;
import com.example.cartera_v1.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Collections;

public class Billeteras extends Fragment {
    LineChart chart;
    RecyclerView rv_billeteras;
    CardView cv_creacion_cartera;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_billeteras, container, false);
        chart = view.findViewById(R.id.lineChart_billetera);
        cv_creacion_cartera = view.findViewById(R.id.cv_creacion_cartera);
        rv_billeteras = view.findViewById(R.id.rv_billeteras_billetera);
        agregarFuncionalidades();
        setLineChart();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        refrescarRecylclerView();
    }

    private void agregarFuncionalidades() {

        rv_billeteras.setLayoutManager(new LinearLayoutManager(getContext()));
        refrescarRecylclerView();
        cv_creacion_cartera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CreacionCartera.class);
                getContext().startActivity(intent);
            }
        });
    }

    private void refrescarRecylclerView() {
        BDCarteras bdCarteras = new BDCarteras(getContext());
        rv_billeteras.setAdapter(new CarteraAdapter_Transaccion(bdCarteras.getCarteras(), getContext()));

    }


    private void setLineChart() {
        /*chart.setDescription(null);
        chart.setNoDataText("No data");
        chart.setPinchZoom(true);
        //chart.setBackgroundColor(Color.BLACK);
        LineData data = new LineData();
        data.setValueTextColor(Color.GREEN);
        chart.setData(data);
        //editar leyenda
        Legend l = chart.getLegend();
        l.setForm(Legend.LegendForm.CIRCLE);
        l.setTextColor(Color.BLUE);

        XAxis x1 = chart.getXAxis();
        x1.setTextColor(Color.BLACK);
        x1.setDrawGridLines(false);
        x1.setAvoidFirstLastClipping(true);

        YAxis y1 = chart.getAxisLeft();
        y1.setTextColor(Color.BLACK);
        y1.setDrawGridLines(true);
        //y1.setAxisMaximum(120f);
        */
        BDMovimientos bd = new BDMovimientos(getContext());
        ArrayList<Model_Fecha_Movimientos> lista = (bd.getMovimientosPorDias());
        Collections.reverse(lista);
        ArrayList<Entry> dataValues = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            dataValues.add(new Entry(lista.get(i).getBalance_total(), lista.get(i).getBalance_total()));
        }
        LineDataSet lineDataSet = new LineDataSet(dataValues, "DATA SET");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        LineData data = new LineData(dataSets);

        chart.setData(data);
        chart.invalidate();

    }
}