package com.example.cartera_v1.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.cartera_v1.BBDD.BDCategorias;
import com.example.cartera_v1.R;

public class CreacionMeta extends AppCompatActivity {
    RadioButton rb_ingreso, rb_gasto, rb_ahorro;
    EditText objetivo;
    Spinner sp_categorias;
    Button btn_aceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion_meta);
        rb_ahorro = findViewById(R.id.rb_categoria_ahorro_meta);
        rb_gasto = findViewById(R.id.rb_categoria_gasto_meta);
        rb_ingreso = findViewById(R.id.rb_categoria_ingreso_meta);
        objetivo = findViewById(R.id.et_objetivo_meta);
        sp_categorias = findViewById(R.id.sp_categorias);
        btn_aceptar = findViewById(R.id.btn_aceptar_meta);
        agregarFuncionalidades();
    }

    private void agregarFuncionalidades() {
        rb_ahorro.setSelected(true);
        BDCategorias bd = new BDCategorias(this);

        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rb_ingreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp_categorias.setAdapter(new ArrayAdapter<String>(CreacionMeta.this,
                        android.R.layout.simple_spinner_item, bd.getNombresCategoriasIngreso()));
            }
        });
        rb_gasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp_categorias.setAdapter(new ArrayAdapter<String>(CreacionMeta.this,
                        android.R.layout.simple_spinner_item, bd.getNombresCategoriasGasto()));
            }
        });
        rb_ahorro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp_categorias.setAdapter(null);
            }
        });
    }
}