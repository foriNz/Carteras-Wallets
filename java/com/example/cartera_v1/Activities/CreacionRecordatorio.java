package com.example.cartera_v1.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.cartera_v1.R;

public class CreacionRecordatorio extends AppCompatActivity {
    EditText et_titulo, et_mensaje;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion_recordatorio);

    }
}