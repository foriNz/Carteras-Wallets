package com.example.cartera_v1.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cartera_v1.Activities.Dialogos.EleccionBilletera;
import com.example.cartera_v1.Activities.Dialogos.EleccionCategoria;
import com.example.cartera_v1.BBDD.BDCategorias;
import com.example.cartera_v1.BBDD.BDMovimientos;
import com.example.cartera_v1.Entidades.Movimiento;
import com.example.cartera_v1.R;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.util.Calendar;

public class Transaccion extends AppCompatActivity {
    EditText et_transaccion, et_nota;
    TextView tv_billeteraDer, tv_billeteraIzq, tv_fechaIzq, tv_fechaDer, tv_agregarFoto;
    ImageView iv_foto, iv_categoria;
    Button btn_aceptar;
    LinearLayout ll_panel_transacciones;
    private int anio, mes, dia;
    EleccionBilletera dialogoEleccionBilletera;
    EleccionCategoria dialogoEleccionCategoria;
    String categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transacciones);
        et_transaccion = findViewById(R.id.et_transaccion);
        et_nota = findViewById(R.id.et_nota);
        tv_billeteraDer = findViewById(R.id.tv_billeteraDer);
        tv_billeteraIzq = findViewById(R.id.tv_billeteraIzq);
        tv_fechaIzq = findViewById(R.id.tv_fechaIzq);
        tv_fechaDer = findViewById(R.id.tv_fechaDer);
        btn_aceptar = findViewById(R.id.btn_aceptar);
        tv_agregarFoto = findViewById(R.id.tv_agregarFoto);
        iv_foto = findViewById(R.id.iv_foto);
        iv_categoria = findViewById(R.id.iv_circuloCategoria);
        ll_panel_transacciones = findViewById(R.id.ll_panel_transacciones);
        agregarfuncionalidades();
    }

    // Pone la imagen insertada
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        iv_foto.setImageURI(uri);
    }


    private void agregarfuncionalidades() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        asignarFecha(year, month, day);
        // Shortcut al día de hoy y a ayer
        tv_fechaDer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                if (tv_fechaDer.getText().equals("Ayer")) {
                    c.add(Calendar.DATE, -1);
                    int yearAyer = c.get(Calendar.YEAR);
                    int monthAyer = c.get(Calendar.MONTH);
                    int dayAyer = c.get(Calendar.DAY_OF_MONTH);
                    asignarFecha(yearAyer, monthAyer, dayAyer);
                    tv_fechaIzq.setText(getResources().getString(R.string.ayer));
                    tv_fechaDer.setText(getResources().getString(R.string.hoy));
                } else {
                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH);
                    int day = c.get(Calendar.DAY_OF_MONTH);
                    asignarFecha(year, month, day);
                    tv_fechaIzq.setText(getResources().getString(R.string.hoy));
                    tv_fechaDer.setText(getResources().getString(R.string.ayer));
                }
            }
        });
        tv_fechaIzq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirDialogoFecha();
            }
        });
        tv_agregarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.Companion.with(Transaccion.this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start();
            }
        });
      /*  tv_billeteraDer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EleccionBilletera dialogo = new EleccionBilletera();
                dialogoEleccionBilletera = dialogo;
                dialogo.setCancelable(false);
                dialogo.show(getSupportFragmentManager(), "dialogo");

            }
        });*/
        LinearLayout ll_billeteras_transaccion = findViewById(R.id.ll_billeteras_transaccion);
        ll_billeteras_transaccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            EleccionBilletera dialogo = new EleccionBilletera();
            dialogoEleccionBilletera = dialogo;
            dialogo.setCancelable(false);
            dialogo.show(getSupportFragmentManager(), "dialogo");
            }
        });
        iv_categoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EleccionCategoria dialogo = new EleccionCategoria(Transaccion.this);
                dialogoEleccionCategoria = dialogo;
                dialogo.setCancelable(false);
                dialogo.show(getSupportFragmentManager(), "dialogo");
            }
        });
        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 09/09/2023 mirar primero si estan las variables requeridas
                BDMovimientos bdMovimientos = new BDMovimientos(Transaccion.this);
                Movimiento m = new Movimiento();
                m.setNombre_cartera(tv_billeteraDer.getText().toString());
                m.setTransaccion(Double.parseDouble(et_transaccion.getText().toString()));
                m.setCategoria(categoria);
                m.setNota(et_nota.getText().toString());
                m.setAnio(anio);
                m.setDia(dia);
                m.setMes(mes);
                m.setId(bdMovimientos.getId());
                bdMovimientos.addMovimiento(m);
                finish();
            }
        });
    }

    private void abrirDialogoFecha() {
        final Calendar c = Calendar.getInstance();
        final Calendar cAyer = Calendar.getInstance();
        cAyer.add(Calendar.DATE, -1);
        int yearAyer = cAyer.get(Calendar.YEAR);
        int monthAyer = cAyer.get(Calendar.MONTH);
        int dayAyer = cAyer.get(Calendar.DAY_OF_MONTH);

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                if (i == year && i1 == month && i2 == day) {
                    tv_fechaIzq.setText(getResources().getString(R.string.hoy));
                    asignarFecha(year, month, day);
                } else if (i == yearAyer && i1 == monthAyer && i2 == dayAyer) {
                    tv_fechaIzq.setText(getResources().getString(R.string.ayer));
                    asignarFecha(yearAyer, monthAyer, dayAyer);
                } else {
                    tv_fechaIzq.setText(i + "-" + (i1 + 1) + "-" + i2);
                    asignarFecha(i, i1, i2);
                }
            }
        }, year, month + 1, day);//Previsualizacion


        datePickerDialog.updateDate(year, month, day);
        datePickerDialog.show();
    }

    public void aplicarEleccionCartera(String nombre) {
        tv_billeteraDer.setText(nombre);
        dialogoEleccionBilletera.dismiss();
    }
    public void aplicarEleccionCategoria(String nombre_categoria) {
        BDCategorias bdCategorias = new BDCategorias(this);
        iv_categoria.setBackgroundResource(0);
        iv_categoria.setImageResource(0);
        iv_categoria.setImageResource(bdCategorias.getIcono(nombre_categoria));
        categoria = nombre_categoria;
        ll_panel_transacciones.setBackgroundColor(Color.parseColor(bdCategorias.getColor(nombre_categoria)));



        dialogoEleccionCategoria.dismiss();


    }


    private void asignarFecha(int year, int month, int day) {
        anio = year;
        mes = month;
        dia = day;
    }

}