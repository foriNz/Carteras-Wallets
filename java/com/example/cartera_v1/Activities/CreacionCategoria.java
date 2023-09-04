package com.example.cartera_v1.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.cartera_v1.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class CreacionCategoria extends AppCompatActivity {
    ImageView btn_atras, btn_guardar_borrar;
    RadioGroup rg_colores, rg_iconos;
    EditText nombre_categoria;
    CircleImageView icono_seleccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion_categoria);
        agregarFuncionalidades();
    }

    private void agregarFuncionalidades() {
        btn_atras = findViewById(R.id.btn_atras_creacion_categoria);
        btn_guardar_borrar = findViewById(R.id.btn_guardar_borrar_categoria);
        nombre_categoria = findViewById(R.id.et_nombre_categoria_creacion);
        icono_seleccion = findViewById(R.id.civ_icono_seleccionado);
        rg_colores = findViewById(R.id.rg_colores_categorias);
        rg_iconos = findViewById(R.id.rg_iconos_categorias);
        btn_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_guardar_borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 04/09/2023 BOTON GUARDAR/BORRAR CATEGORIA
            }
        });
        rellenarColores();
        rellenarIconos();
    }

    private void rellenarIconos() {
        // TODO: 04/09/2023 rellenear ICONOS
    }


    private void rellenarColores() {
        // TODO: 04/09/2023 RELLENAR PALA DE COLORES 
    }
}