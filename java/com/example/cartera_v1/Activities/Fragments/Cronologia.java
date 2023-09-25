package com.example.cartera_v1.Activities.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartera_v1.Activities.Dialogos.EleccionAnio;
import com.example.cartera_v1.Activities.Dialogos.EleccionBilletera;
import com.example.cartera_v1.Activities.Dialogos.EleccionIntervalo;
import com.example.cartera_v1.Activities.Dialogos.EleccionMes;
import com.example.cartera_v1.Activities.Transaccion;
import com.example.cartera_v1.Adaptadores.CarterasAdapter_Transaccion;
import com.example.cartera_v1.Adaptadores.IntervaloAdapter_Recordatorios;
import com.example.cartera_v1.Adaptadores.MesesAdapter_Estadisticas;
import com.example.cartera_v1.Adaptadores.MovimientosAdapter_Cronologia;
import com.example.cartera_v1.BBDD.BDMovimientos;
import com.example.cartera_v1.Entidades.Model_Data_MovimientoPorAnio;
import com.example.cartera_v1.Entidades.Model_Data_MovimientoPorMes;
import com.example.cartera_v1.Entidades.Model_Data_MovimientoPorSemana;
import com.example.cartera_v1.Entidades.Model_Fecha_Movimientos;
import com.example.cartera_v1.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class Cronologia extends Fragment {
    BarChart chart;
    TextView tv_balance;
    RecyclerView rv_listaMovimientos;
    Button org_billeteras, org_periodos, anios, meses;
    FloatingActionButton fab;
    MovimientosAdapter_Cronologia adapter;
    EleccionBilletera dialogoEleccionBilletera;
    EleccionAnio dialogoEleccionAnios;
    EleccionMes dialogoEleccionMes;
    EleccionIntervalo dialogoEleccionIntervalo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cronologia, container, false);
        fab = view.findViewById(R.id.fab_frag_movimientos);
        chart = view.findViewById(R.id.chart_frag_movimientos);
        tv_balance = view.findViewById(R.id.tv_balance_frag_movimiento);
        rv_listaMovimientos = view.findViewById(R.id.elv_frag_movimientos);
        org_billeteras = view.findViewById(R.id.btn_organizacion_cronologia);
        org_periodos = view.findViewById(R.id.btn_periodos_cronologia);
        anios = view.findViewById(R.id.btn_org_periodos_anios_cronologia);
        meses = view.findViewById(R.id.btn_org_periodos_meses_cronologia);
        agregarFuncionalidades();
        rellenarChartPorMes(Calendar.getInstance().get(Calendar.YEAR));
        return view;
    }

    private void agregarFuncionalidades() {
        anios.setVisibility(View.INVISIBLE);
        meses.setVisibility(View.INVISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Transaccion.class);
                getContext().startActivity(intent);
            }
        });
        org_billeteras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EleccionBilletera dialogo = new EleccionBilletera(cartera -> {
                    org_billeteras.setText(cartera);
                    refrescarDatos();
                    dialogoEleccionBilletera.dismiss();
                });
                dialogoEleccionBilletera = dialogo;
                dialogo.setCancelable(false);
                dialogo.show(getParentFragmentManager(), "dialogo");
            }
        });
        org_periodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EleccionIntervalo dialogo = new EleccionIntervalo(new IntervaloAdapter_Recordatorios.PeriodosListener() {
                    @Override
                    public void aplicarPeriodo(String periodo) {
                        BDMovimientos bdm = new BDMovimientos(getContext());
                        org_periodos.setText(periodo);
                        if (periodo.equals(getResources().getString(R.string.por_año))) {
                            anios.setVisibility(View.VISIBLE);
                            anios.setText(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
                            meses.setVisibility(View.INVISIBLE);
                        } else if (periodo.equals(getResources().getString(R.string.por_meses))) {
                            anios.setVisibility(View.VISIBLE);
                            anios.setText(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
                            meses.setVisibility(View.VISIBLE);
                            meses.setText(bdm.getMesDelInt(Calendar.getInstance().get(Calendar.MONTH)));
                        } else {
                            anios.setVisibility(View.INVISIBLE);
                            meses.setVisibility(View.INVISIBLE);
                        }
                        refrescarDatos();

                        dialogoEleccionIntervalo.dismiss();
                    }
                }

                );
                dialogoEleccionIntervalo = dialogo;
                dialogo.setCancelable(false);
                dialogo.show(getParentFragmentManager(), "dialogo");
            }
        });
        anios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EleccionAnio dialogo = new EleccionAnio();
                dialogoEleccionAnios = dialogo;
                dialogo.setCancelable(false);
                dialogo.show(getParentFragmentManager(), "dialogo");
            }
        });
        meses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EleccionMes dialogo = new EleccionMes(Integer.parseInt(anios.getText().toString()), new MesesAdapter_Estadisticas.AplicarEleccionMes() {
                    @Override
                    public void aplicarEleccionMes(String mes) {
                        meses.setText(mes);
                        dialogoEleccionMes.dismiss();
                        refrescarDatos();
                    }
                });
                dialogoEleccionMes = dialogo;
                dialogo.setCancelable(false);
                dialogo.show(getParentFragmentManager(), "dialogo");
            }
        });
        refrescarDatos();
    }

    private void refrescarDatos() {
        BDMovimientos bdm = new BDMovimientos(getContext());
        ArrayList<Model_Fecha_Movimientos> lista_rv;

        String billeteras, periodo;
        int mes, anio;
        billeteras = org_billeteras.getText().toString();
        periodo = org_periodos.getText().toString();
        // TODAS LAS BILLETERAS
        if (billeteras.equals(getResources().getString(R.string.todas_las_billeteras))) {
            if (periodo.equals(getResources().getString(R.string.por_año))) {
                anio = Integer.parseInt(anios.getText().toString());
                lista_rv = bdm.getMovimientosPorDias(anio);
                rellenarChartPorMes(anio);
            } else if (periodo.equals(getResources().getString(R.string.todo_el_historial))) {
                lista_rv = bdm.getMovimientosPorDias();
                rellenarChart();
            } else {
                // POR MES Y POR AÑO
                mes = bdm.getIntDelMes(meses.getText().toString());
                anio = Integer.parseInt(anios.getText().toString());
                lista_rv = bdm.getMovimientosPorDias(mes, anio);
                rellenarChartPorSemana(anio, mes);
            }
        }
        // UNA BILLETERA EN CONCRETO
        else {
            if (periodo.equals(getResources().getString(R.string.por_año))) {
                anio = Integer.parseInt(anios.getText().toString());
                lista_rv = bdm.getMovimientosPorDias(anio, billeteras);
                rellenarChartPorMes(anio, billeteras);
            } else if (periodo.equals(getResources().getString(R.string.todo_el_historial))) {
                lista_rv = bdm.getMovimientosPorDias(billeteras);
                rellenarChart(billeteras);
            } else {
                // POR MES Y POR AÑO
                mes = bdm.getIntDelMes(meses.getText().toString());
                anio = Integer.parseInt(anios.getText().toString());
                lista_rv = bdm.getMovimientosPorDias(mes, anio, billeteras);
                rellenarChartPorSemana(anio, mes, billeteras);
            }
        }
        refrescarRecyclerView(lista_rv);
    }

    private void rellenarChartPorSemana(int year, int mes) {
        BDMovimientos bd = new BDMovimientos(getContext());
        ArrayList<Model_Data_MovimientoPorSemana> md = bd.getMovimientosSemana(year, mes);
        ArrayList<BarEntry> entriesIngresos = new ArrayList<>();
        ArrayList<BarEntry> entriesGastos = new ArrayList<>();
        ArrayList<String> semanas = new ArrayList<>();

        for (int i = 0; i < md.size(); i++) {
            entriesIngresos.add(new BarEntry(i, Math.round(md.get(i).getIngresos())));
            entriesGastos.add(new BarEntry(i, -(Math.round(md.get(i).getGastos()))));
            semanas.add(String.valueOf(md.get(i).getListaMovimientos().get(0).getDia()));
        }
        BarDataSet barDataSet1 = new BarDataSet(entriesIngresos, getResources().getString(R.string.ingreso));
        barDataSet1.setColor(Color.GREEN);
        BarDataSet barDataSet2 = new BarDataSet(entriesGastos, getResources().getString(R.string.gasto));
        barDataSet2.setColor(Color.RED);
        BarData data = new BarData(barDataSet1, barDataSet2);
        chart.setData(data);
        XAxis xAxis = chart.getXAxis();
        YAxis yAxis = chart.getAxisRight();
        yAxis.setSpaceTop(2f);
        yAxis.setCenterAxisLabels(true);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(semanas));
        xAxis.setTextSize(12f);

        yAxis.setAxisMinimum(0f);

        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);
        chart.setDragEnabled(true);
        chart.setVisibleXRangeMinimum(12);
        float barSpace = 0.05f;
        float groupSpace = 0.5f;
        data.setBarWidth(0.16f);
        chart.getXAxis().setAxisMinimum(0);
        chart.groupBars(0, groupSpace, barSpace);
        chart.setDescription(null);

        chart.invalidate();
    }

    private void rellenarChartPorSemana(int year, int mes, String cartera) {
        BDMovimientos bd = new BDMovimientos(getContext());
        ArrayList<Model_Data_MovimientoPorSemana> md = bd.getMovimientosSemana(year, mes, cartera);
        ArrayList<BarEntry> entriesIngresos = new ArrayList<>();
        ArrayList<BarEntry> entriesGastos = new ArrayList<>();
        ArrayList<String> semanas = new ArrayList<>();

        for (int i = 0; i < md.size(); i++) {
            entriesIngresos.add(new BarEntry(i, Math.round(md.get(i).getIngresos())));
            entriesGastos.add(new BarEntry(i, -(Math.round(md.get(i).getGastos()))));
            semanas.add(String.valueOf(md.get(i).getListaMovimientos().get(0).getDia()));
        }
        BarDataSet barDataSet1 = new BarDataSet(entriesIngresos, getResources().getString(R.string.ingreso));
        barDataSet1.setColor(Color.GREEN);
        BarDataSet barDataSet2 = new BarDataSet(entriesGastos, getResources().getString(R.string.gasto));
        barDataSet2.setColor(Color.RED);
        BarData data = new BarData(barDataSet1, barDataSet2);
        chart.setData(data);
        XAxis xAxis = chart.getXAxis();
        YAxis yAxis = chart.getAxisRight();
        yAxis.setSpaceTop(2f);
        yAxis.setCenterAxisLabels(true);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(semanas));
        xAxis.setTextSize(12f);

        yAxis.setAxisMinimum(0f);

        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);
        chart.setDragEnabled(true);
        chart.setVisibleXRangeMinimum(12);
        float barSpace = 0.05f;
        float groupSpace = 0.5f;
        data.setBarWidth(0.16f);
        chart.getXAxis().setAxisMinimum(0);
        chart.groupBars(0, groupSpace, barSpace);
        chart.setDescription(null);

        chart.invalidate();
    }

    private void rellenarChartPorMes(int year) {
        BDMovimientos bd = new BDMovimientos(getContext());
        Model_Data_MovimientoPorMes[] md = bd.getMovimientosAnio(year);
        ArrayList<BarEntry> entriesIngresos = new ArrayList<>();
        ArrayList<BarEntry> entriesGastos = new ArrayList<>();

        for (int i = 0; i < md.length; i++) {
            if (md[i] == null) {
                entriesIngresos.add(new BarEntry(i, 0));
                entriesGastos.add(new BarEntry(i, 0));
            } else {
                entriesIngresos.add(new BarEntry(i, Math.round(md[i].getIngresos())));
                entriesGastos.add(new BarEntry(i, -(Math.round(md[i].getGastos()))));
            }
        }
        BarDataSet barDataSet1 = new BarDataSet(entriesIngresos, getResources().getString(R.string.ingreso));
        barDataSet1.setColor(Color.GREEN);
        BarDataSet barDataSet2 = new BarDataSet(entriesGastos, getResources().getString(R.string.gasto));
        barDataSet2.setColor(Color.RED);
        BarData data = new BarData(barDataSet1, barDataSet2);
        chart.setData(data);
        String[] meses = new String[]{"Ene", "Fe", "Mar", "Ab", "May", "Jun", "Jul", "Ag", "Sep", "Oct", "Nov", "Dic"};
        XAxis xAxis = chart.getXAxis();
        YAxis yAxis = chart.getAxisRight();
        yAxis.setSpaceTop(2f);
        yAxis.setCenterAxisLabels(true);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(meses));
        xAxis.setTextSize(12f);
        yAxis.setAxisMinimum(0f);

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);
        chart.setDragEnabled(true);
        chart.setVisibleXRangeMinimum(12);
        float barSpace = 0.05f;
        float groupSpace = 0.5f;
        data.setBarWidth(0.16f);
        chart.getXAxis().setAxisMinimum(0);
        chart.groupBars(0, groupSpace, barSpace);
        chart.setDescription(null);

        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                int i = entriesGastos.indexOf(e);
                //refrescarRecyclerView(i);
            }

            @Override
            public void onNothingSelected() {
                refrescarRecyclerView();
            }
        });

        chart.invalidate();
    }

    private void rellenarChartPorMes(int year, String cartera) {
        BDMovimientos bd = new BDMovimientos(getContext());
        Model_Data_MovimientoPorMes[] md = bd.getMovimientosAnio(year, cartera);
        ArrayList<BarEntry> entriesIngresos = new ArrayList<>();
        ArrayList<BarEntry> entriesGastos = new ArrayList<>();

        for (int i = 0; i < md.length; i++) {
            if (md[i] == null) {
                entriesIngresos.add(new BarEntry(i, 0));
                entriesGastos.add(new BarEntry(i, 0));
            } else {
                entriesIngresos.add(new BarEntry(i, Math.round(md[i].getIngresos())));
                entriesGastos.add(new BarEntry(i, -(Math.round(md[i].getGastos()))));
            }
        }
        BarDataSet barDataSet1 = new BarDataSet(entriesIngresos, getResources().getString(R.string.ingreso));
        barDataSet1.setColor(Color.GREEN);
        BarDataSet barDataSet2 = new BarDataSet(entriesGastos, getResources().getString(R.string.gasto));
        barDataSet2.setColor(Color.RED);
        BarData data = new BarData(barDataSet1, barDataSet2);
        chart.setData(data);
        String[] meses = new String[]{"Ene", "Fe", "Mar", "Ab", "May", "Jun", "Jul", "Ag", "Sep", "Oct", "Nov", "Dic"};
        XAxis xAxis = chart.getXAxis();
        YAxis yAxis = chart.getAxisRight();
        yAxis.setSpaceTop(2f);
        yAxis.setCenterAxisLabels(true);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(meses));
        xAxis.setTextSize(12f);

        yAxis.setAxisMinimum(0f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);
        chart.setDragEnabled(true);
        chart.setVisibleXRangeMinimum(12);
        float barSpace = 0.05f;
        float groupSpace = 0.5f;
        data.setBarWidth(0.16f);
        chart.getXAxis().setAxisMinimum(0);
        chart.groupBars(0, groupSpace, barSpace);
        chart.setDescription(null);

        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                int i = entriesGastos.indexOf(e);
                //refrescarRecyclerView(i);
            }

            @Override
            public void onNothingSelected() {
                refrescarRecyclerView();
            }
        });

        chart.invalidate();
    }

    private void rellenarChart() {
        BDMovimientos bd = new BDMovimientos(getContext());
        ArrayList<Model_Data_MovimientoPorAnio> md = bd.getMovimientosAnio();
        ArrayList<BarEntry> entriesIngresos = new ArrayList<>();
        ArrayList<BarEntry> entriesGastos = new ArrayList<>();

        for (int i = 0; i < md.size(); i++) {
            entriesIngresos.add(new BarEntry(i, Math.round(md.get(i).getIngresos())));
            entriesGastos.add(new BarEntry(i, -(Math.round(md.get(i).getGastos()))));
        }
        BarDataSet barDataSet1 = new BarDataSet(entriesIngresos, getResources().getString(R.string.ingreso));
        barDataSet1.setColor(Color.GREEN);
        BarDataSet barDataSet2 = new BarDataSet(entriesGastos, getResources().getString(R.string.gasto));
        barDataSet2.setColor(Color.RED);
        BarData data = new BarData(barDataSet1, barDataSet2);
        chart.setData(data);
        ArrayList<String> meses = bd.getAniosConTransacciones();
        // String[] meses = new String[]{"Ene", "Fe", "Mar", "Ab", "May", "Jun", "Jul", "Ag", "Sep", "Oct", "Nov", "Dic"};
        XAxis xAxis = chart.getXAxis();
        YAxis yAxis = chart.getAxisRight();
        yAxis.setSpaceTop(2f);
        yAxis.setCenterAxisLabels(true);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(meses));
        xAxis.setTextSize(12f);

        yAxis.setAxisMinimum(0f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);
        chart.setDragEnabled(true);
        chart.setVisibleXRangeMinimum(12);
        float barSpace = 0.05f;
        float groupSpace = 0.5f;
        data.setBarWidth(0.16f);
        chart.getXAxis().setAxisMinimum(0);
        chart.groupBars(0, groupSpace, barSpace);
        chart.setDescription(null);

        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                int i = entriesGastos.indexOf(e);
                //refrescarRecyclerView(i);
            }

            @Override
            public void onNothingSelected() {
                refrescarRecyclerView();
            }
        });

        chart.invalidate();
    }

    private void rellenarChart(String cartera) {
        BDMovimientos bd = new BDMovimientos(getContext());
        ArrayList<Model_Data_MovimientoPorAnio> md = bd.getMovimientosAnio(cartera);
        ArrayList<BarEntry> entriesIngresos = new ArrayList<>();
        ArrayList<BarEntry> entriesGastos = new ArrayList<>();

        for (int i = 0; i < md.size(); i++) {
            entriesIngresos.add(new BarEntry(i, Math.round(md.get(i).getIngresos())));
            entriesGastos.add(new BarEntry(i, -(Math.round(md.get(i).getGastos()))));
        }
        BarDataSet barDataSet1 = new BarDataSet(entriesIngresos, getResources().getString(R.string.ingreso));
        barDataSet1.setColor(Color.GREEN);
        BarDataSet barDataSet2 = new BarDataSet(entriesGastos, getResources().getString(R.string.gasto));
        barDataSet2.setColor(Color.RED);
        BarData data = new BarData(barDataSet1, barDataSet2);
        chart.setData(data);
        ArrayList<String> meses = bd.getAniosConTransacciones();
        // String[] meses = new String[]{"Ene", "Fe", "Mar", "Ab", "May", "Jun", "Jul", "Ag", "Sep", "Oct", "Nov", "Dic"};
        XAxis xAxis = chart.getXAxis();
        YAxis yAxis = chart.getAxisRight();
        yAxis.setSpaceTop(2f);
        yAxis.setCenterAxisLabels(true);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(meses));
        xAxis.setTextSize(12f);
        yAxis.setAxisMinimum(0f);

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);
        chart.setDragEnabled(true);
        chart.setVisibleXRangeMinimum(12);
        float barSpace = 0.05f;
        float groupSpace = 0.5f;
        data.setBarWidth(0.16f);
        chart.getXAxis().setAxisMinimum(0);
        chart.groupBars(0, groupSpace, barSpace);
        chart.setDescription(null);

        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                int i = entriesGastos.indexOf(e);
                //refrescarRecyclerView(i);
            }

            @Override
            public void onNothingSelected() {
                refrescarRecyclerView();
            }
        });

        chart.invalidate();
    }

    private void refrescarRecyclerView() {
        BDMovimientos bdMovimientos = new BDMovimientos(getContext());

        adapter = new MovimientosAdapter_Cronologia(bdMovimientos.getMovimientosPorDias(), getContext());
        rv_listaMovimientos.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_listaMovimientos.setAdapter(adapter);

    }

    private void refrescarRecyclerView(ArrayList<Model_Fecha_Movimientos> lista) {

        adapter = new MovimientosAdapter_Cronologia(lista, getContext());
        rv_listaMovimientos.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_listaMovimientos.setAdapter(adapter);

    }


    @Override
    public void onResume() {
        super.onResume();
        refrescarDatos();

    }
}
