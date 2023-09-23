package com.example.cartera_v1.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.cartera_v1.BBDD.BDMovimientos;
import com.example.cartera_v1.Entidades.Model_Data_Categoria;
import com.example.cartera_v1.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.Calendar;

public class EstadisticasCartera extends AppCompatActivity {
    Button org_periodos, org_billeteras;
    RadioButton ingreso, gasto;
    PieChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas_cartera);
        org_billeteras = findViewById(R.id.btn_org_billeteras_estadisticas);
        org_periodos = findViewById(R.id.btn_org_periodos_estadisticas);
        ingreso = findViewById(R.id.rb_categoria_ingreso_estadisticas);
        gasto = findViewById(R.id.rb_categoria_gasto_estadisticas);
        chart = findViewById(R.id.pc_ingreso_gasto_estadisticas);
        agregarFuncionalidades();
    }

    private void agregarFuncionalidades() {
        org_periodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        org_billeteras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ingreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rellenarChart("Ingreso");
            }
        });
        gasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rellenarChart("Gasto");

            }
        });
        rellenarChart("Ingreso");
    }

    private void rellenarChart(String ingresoGasto) {
        chart.setUsePercentValues(true);
        chart.setHighlightPerTapEnabled(true);
        chart.setHoleRadius(58f);
        chart.setRotationEnabled(false);
        ArrayList<PieEntry> listaCategorias = new ArrayList<>();
        ArrayList<Integer> listaColores = new ArrayList<>();

        BDMovimientos bdm = new BDMovimientos(this);
        int anioActual = Calendar.getInstance().get(Calendar.YEAR);
        ArrayList<Model_Data_Categoria> cats;
        if (ingresoGasto.equals("Ingreso"))
            cats = bdm.getIngresosGastosPorCategorias(anioActual, "Ingreso");
        else
            cats = bdm.getIngresosGastosPorCategorias(anioActual, "Gasto");

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

        PieDataSet dataSet = new PieDataSet(listaCategorias, " ");
        dataSet.setColors(listaColores);
        dataSet.setDrawIcons(true);
        PieData data = new PieData(dataSet);
        data.setValueTextSize(0f);
        //data.setValueFormatter(new PercentFormatter());
        data.setValueTextColor(Color.BLACK);
        chart.setData(data);
        chart.invalidate();
    }
}