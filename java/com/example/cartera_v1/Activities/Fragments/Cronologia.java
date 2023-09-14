package com.example.cartera_v1.Activities.Fragments;

import android.content.Intent;
import android.graphics.Color;
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
import com.example.cartera_v1.Entidades.Model_Data_MovimientoPorMes;
import com.example.cartera_v1.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.function.DoubleToIntFunction;

public class Cronologia extends Fragment {
    BarChart chart;
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
        rellenarChart();
        return view;
    }

    private void rellenarChart() {
        BDMovimientos bd = new BDMovimientos(getContext());
        Model_Data_MovimientoPorMes[] md = bd.getMovimientosAnio(Calendar.getInstance().get(Calendar.YEAR));
        ArrayList<BarEntry> entriesIngresos = new ArrayList<>();
        ArrayList<BarEntry> entriesGastos = new ArrayList<>();

        for (int i = 0; i < md.length; i++) {
            if (md[i] == null) {
                entriesIngresos.add(new BarEntry(i, 0));
                entriesGastos.add(new BarEntry(i, 0));
            } else {
                entriesIngresos.add(new BarEntry(i, Math.round(md[i].getIngresos())));
                entriesGastos.add(new BarEntry(i, Math.round(md[i].getGastos())));
            }
        }
        BarDataSet barDataSet1 = new BarDataSet(entriesIngresos, "ingresos");
        barDataSet1.setColor(Color.GREEN);
        BarDataSet barDataSet2 = new BarDataSet(entriesGastos, "gastos");
        barDataSet2.setColor(Color.RED);
        BarData data = new BarData(barDataSet1, barDataSet2);
        chart.setData(data);
        String[] meses = new String[]{"E", "F", "M", "A", "M", "J", "J", "A", "S", "O", "N", "D"};
        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(meses));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);
        chart.setDragEnabled(true);
        chart.setVisibleXRangeMinimum(12);
        float barSpace = 0.1f;
        float groupSpace = 0.5f;
        data.setBarWidth(0.16f);
        chart.getXAxis().setAxisMinimum(0);
        chart.groupBars(0, groupSpace, barSpace);
        chart.setDescription(null);

        chart.invalidate();
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
