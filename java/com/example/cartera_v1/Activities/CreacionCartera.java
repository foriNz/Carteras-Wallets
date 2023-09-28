package com.example.cartera_v1.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cartera_v1.BBDD.BDCarteras;
import com.example.cartera_v1.Entidades.Cartera;
import com.example.cartera_v1.MainActivity;
import com.example.cartera_v1.R;

public class CreacionCartera extends AppCompatActivity {
    EditText et_saldo_inicial, et_nombre_cartera;
    Button btn_aceptar;
    Bundle datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion_cartera);
        et_saldo_inicial = findViewById(R.id.et_saldo_inicial_creacion_cartera);
        et_nombre_cartera = findViewById(R.id.et_nombre_cartera_creacion_cartera);
        btn_aceptar = findViewById(R.id.btn_aceptar_creacion_cartera);
        datos = getIntent().getExtras();
        agregarFuncionalidades();

    }

    private void agregarFuncionalidades() {
        if (datos != null) {
            et_nombre_cartera.setText(datos.getString("nombre"));
            et_saldo_inicial.setVisibility(View.INVISIBLE);
            btn_aceptar.setOnClickListener(view -> {
                if (et_nombre_cartera.getText().toString().trim().isEmpty())
                    btn_aceptar.setText(R.string.btn_aceptar_nombre_vacio);
                else {
                    BDCarteras bdCarteras = new BDCarteras(getApplicationContext());
                    bdCarteras.modificarNombre(et_nombre_cartera.getText().toString(), datos.getString("nombre"));
                    finish();
                }
            });
        } else
            btn_aceptar.setOnClickListener(view -> {
                if (et_nombre_cartera.getText().toString().trim().isEmpty())
                    btn_aceptar.setText(R.string.btn_aceptar_nombre_vacio);
                else {

                    BDCarteras bdCarteras = new BDCarteras(getApplicationContext());
                    if (et_saldo_inicial.getText().toString().isEmpty())
                        bdCarteras.addCartera(et_nombre_cartera.getText().toString(),
                                0);
                    else
                        bdCarteras.addCartera(et_nombre_cartera.getText().toString(),
                                Double.parseDouble(et_saldo_inicial.getText().toString()));
                    finish();

                }
            });
    }

}