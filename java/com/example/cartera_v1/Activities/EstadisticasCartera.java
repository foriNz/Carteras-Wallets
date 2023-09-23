package com.example.cartera_v1.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.cartera_v1.Activities.Dialogos.EleccionBilletera;
import com.example.cartera_v1.Activities.Dialogos.EleccionIntervalo;
import com.example.cartera_v1.Adaptadores.CategoriasAdapter_Estadisticas;
import com.example.cartera_v1.BBDD.BDMovimientos;
import com.example.cartera_v1.Entidades.Model_Data_Categoria;
import com.example.cartera_v1.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.Calendar;

public class EstadisticasCartera extends AppCompatActivity {
    Button org_periodos, org_billeteras, meses, anios;
    RadioButton ingreso, gasto;
    PieChart chart;
    RecyclerView rv_categorias_estadisticas;
    CategoriasAdapter_Estadisticas adapter;
    EleccionBilletera dialogoEleccionBilletera;
    EleccionIntervalo dialogoPeriodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas_cartera);
        org_billeteras = findViewById(R.id.btn_org_billeteras_estadisticas);
        org_periodos = findViewById(R.id.btn_org_periodos_estadisticas);
        ingreso = findViewById(R.id.rb_categoria_ingreso_estadisticas);
        gasto = findViewById(R.id.rb_categoria_gasto_estadisticas);
        chart = findViewById(R.id.pc_ingreso_gasto_estadisticas);
        rv_categorias_estadisticas = findViewById(R.id.rv_categorias_estadisticas);
        anios = findViewById(R.id.btn_org_periodos_anios_estadisticas);
        meses = findViewById(R.id.btn_org_periodos_meses_estadisticas);
        agregarFuncionalidades();
    }

    private void agregarFuncionalidades() {
        anios.setVisibility(View.INVISIBLE);
        meses.setVisibility(View.INVISIBLE);
        ingreso.setChecked(true);
        org_periodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EleccionIntervalo dialogo = new EleccionIntervalo();
                dialogoPeriodo = dialogo;
                dialogo.setCancelable(false);
                dialogo.show(getSupportFragmentManager(), "dialogo");
            }
        });
        org_billeteras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EleccionBilletera dialogo = new EleccionBilletera();
                dialogoEleccionBilletera = dialogo;
                dialogo.setCancelable(false);
                dialogo.show(getSupportFragmentManager(), "dialogo");
            }
        });
        ingreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refrescarDatos();
            }
        });
        gasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refrescarDatos();
            }
        });
        refrescarDatos();
    }

    private void refrescarDatos() {
        BDMovimientos bdm = new BDMovimientos(EstadisticasCartera.this);
        ArrayList<Model_Data_Categoria> lista;

        String billeteras, periodo;
        int mes, anio;
        billeteras = org_billeteras.getText().toString();
        periodo = org_periodos.getText().toString();
        // TODAS LAS BILLETERAS
        if (billeteras.equals(getResources().getString(R.string.todas_las_billeteras))) {
            if (periodo.equals(getResources().getString(R.string.por_año))) {
                anio = Integer.parseInt(anios.getText().toString());
                lista = bdm.getIngresosGastosPorCategorias(anio, getSeleccionRadioGroup());
            } else if (periodo.equals(getResources().getString(R.string.todo_el_historial)))
                lista = bdm.getTodoIngresosGastosPorCategorias(getSeleccionRadioGroup());
            else {
                // POR MES Y POR AÑO
                mes = bdm.getIntDelMes(meses.getText().toString());
                anio = Integer.parseInt(anios.getText().toString());
                lista = bdm.getIngresosGastosPorCategorias(anio, mes, getSeleccionRadioGroup());
            }
        }
        // UNA BILLETERA EN CONCRETO
        else {
            if (periodo.equals(getResources().getString(R.string.por_año))) {
                anio = Integer.parseInt(anios.getText().toString());
                lista = bdm.getIngresosGastosPorCategorias(anio, getSeleccionRadioGroup(), billeteras);
            } else if (periodo.equals(getResources().getString(R.string.todo_el_historial)))
                lista = bdm.getTodoIngresosGastosPorCategorias(getSeleccionRadioGroup(),billeteras);
            else {
                // POR MES Y POR AÑO
                mes = bdm.getIntDelMes(meses.getText().toString());
                anio = Integer.parseInt(anios.getText().toString());
                lista = bdm.getIngresosGastosPorCategorias(anio, mes, getSeleccionRadioGroup(), billeteras);
            }
        }
        refrescarDatos(lista);
    }

    private String getSeleccionRadioGroup() {
        if (gasto.isChecked())
            return "Gasto";
        else return "Ingreso";
    }

    private void refrescarDatos(ArrayList<Model_Data_Categoria> arrayList) {
        rellenarChart(arrayList);
        refrescarRecyclerView(arrayList);
    }

    private void refrescarRecyclerView(ArrayList<Model_Data_Categoria> arrayList) {
        adapter = new CategoriasAdapter_Estadisticas(this, arrayList);
        rv_categorias_estadisticas.setLayoutManager(new LinearLayoutManager(this));
        rv_categorias_estadisticas.setAdapter(adapter);
    }

    private void rellenarChart(ArrayList<Model_Data_Categoria> arrayList) {
        chart.setUsePercentValues(true);
        chart.setHighlightPerTapEnabled(true);
        chart.setHoleRadius(58f);
        chart.setRotationEnabled(false);
        ArrayList<PieEntry> listaCategorias = new ArrayList<>();
        ArrayList<Integer> listaColores = new ArrayList<>();

        ArrayList<Model_Data_Categoria> cats = arrayList;
        for (int i = 0; i < cats.size(); i++) {
            Model_Data_Categoria m = cats.get(i);
            Drawable icon = ContextCompat.getDrawable(this, m.getIcono());
            icon.setTint(Color.WHITE);
            if (m.getBalance() > 0)
                listaCategorias.add(new PieEntry(Math.round(m.getBalance()), m.getNombre()));
            else
                listaCategorias.add(new PieEntry(-Math.round(m.getBalance()), m.getNombre()));
            listaCategorias.get(i).setIcon(icon);
            listaColores.add(Color.parseColor(m.getColor()));
        }

        chart.setEntryLabelColor(0);
        PieDataSet dataSet = new PieDataSet(listaCategorias, "");
        dataSet.setColors(listaColores);
        dataSet.setDrawIcons(true);
        PieData data = new PieData(dataSet);
        data.setValueTextSize(0f);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextColor(Color.BLACK);
        chart.setData(data);
        chart.invalidate();
    }

    public void aplicarEleccionCartera(String cartera) {
        refrescarDatos();
        org_billeteras.setText(cartera);
        dialogoEleccionBilletera.dismiss();
    }

    public void aplicarEleccionIntervalo(String nombre) {
        BDMovimientos bdm = new BDMovimientos(this);
        refrescarDatos();
        org_periodos.setText(nombre);
        if (nombre.equals(getResources().getString(R.string.por_año))) {
            anios.setVisibility(View.VISIBLE);
            anios.setText(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
        } else if (nombre.equals(getResources().getString(R.string.por_meses))) {
            anios.setVisibility(View.VISIBLE);
            anios.setText(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
            meses.setVisibility(View.VISIBLE);
            meses.setText(bdm.getMesDelInt(Calendar.getInstance().get(Calendar.MONTH)));
        }
        else {
            anios.setVisibility(View.INVISIBLE);
            meses.setVisibility(View.INVISIBLE);
        }
        dialogoPeriodo.dismiss();
    }
}