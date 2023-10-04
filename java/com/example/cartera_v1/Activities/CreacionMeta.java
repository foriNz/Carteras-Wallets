package com.example.cartera_v1.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.cartera_v1.BBDD.BDCategorias;
import com.example.cartera_v1.BBDD.BDMetas;
import com.example.cartera_v1.Entidades.Meta;
import com.example.cartera_v1.R;

import java.util.ArrayList;

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
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
    }

    private void agregarFuncionalidades() {
        rb_ahorro.setSelected(true);
        BDCategorias bd = new BDCategorias(this);

        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BDMetas bdm = new BDMetas(CreacionMeta.this);
                Meta m = new Meta();
                m.setValor_objetivo(Integer.parseInt(objetivo.getText().toString()));
                if (rb_ahorro.isChecked()) {
                    m.setCaracter(getResources().getString(R.string.ahorro));
                } else if (rb_gasto.isChecked()) {
                    int a = Integer.parseInt(objetivo.getText().toString());
                    a = -a;
                    m.setValor_objetivo(a);

                    if (sp_categorias.getSelectedItem().toString().equals("General"))
                        m.setCaracter("General");
                    else {
                        m.setCaracter(getResources().getString(R.string.et_caracter_meta_especifico));
                        m.setCategoria(sp_categorias.getSelectedItem().toString());
                    }
                    m.setTipo_categoria(getResources().getString(R.string.gasto));
                } else if (rb_ingreso.isChecked()) {
                    if (sp_categorias.getSelectedItem().toString().equals("General"))
                        m.setCaracter("General");
                    else {
                        m.setCaracter(getResources().getString(R.string.et_caracter_meta_especifico));
                        m.setCategoria(sp_categorias.getSelectedItem().toString());
                    }
                    m.setTipo_categoria(getResources().getString(R.string.ingreso));
                }
                bdm.addMeta(m);
                finish();
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
                ArrayList<String> a = new ArrayList<>();
                a.add("General");
                sp_categorias.setAdapter(new ArrayAdapter<String>(CreacionMeta.this,
                        android.R.layout.simple_spinner_item, a));
            }
        });
    }
}