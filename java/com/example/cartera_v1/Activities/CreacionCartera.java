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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion_cartera);
        et_saldo_inicial = findViewById(R.id.et_saldo_inicial_creacion_cartera);
        et_nombre_cartera = findViewById(R.id.et_nombre_cartera_creacion_cartera);
        btn_aceptar = findViewById(R.id.btn_aceptar_creacion_cartera);
        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_nombre_cartera.getText().toString().trim().isEmpty())
                    btn_aceptar.setText(R.string.btn_aceptar_nombre_vacio);
                else {
                    BDCarteras bdCarteras = new BDCarteras(getApplicationContext());
                    bdCarteras.addCartera(et_nombre_cartera.getText().toString(),
                            Double.parseDouble(et_saldo_inicial.getText().toString()));
                    finish();
                    ((MainActivity)getApplicationContext()).redirigitAFragmento(0);
                }
            }
        });
    }

}