package com.example.cartera_v1.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView tv_billeteraDer, tv_billeteraIzq, tv_fechaIzq, tv_fechaDer, tv_agregarFoto, tv_titulo;
    ImageView iv_foto, iv_categoria;
    Button btn_aceptar;
    LinearLayout ll_panel_transacciones, ll_billeteras_transaccion;
    private int anio, mes, dia;
    EleccionBilletera dialogoEleccionBilletera;
    EleccionCategoria dialogoEleccionCategoria;
    String categoria, tipo_categoria;
    Bundle datos;

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
        ll_billeteras_transaccion = findViewById(R.id.ll_billeteras_transaccion);
        tv_titulo = findViewById(R.id.tv_titulo_transaccion);
        datos = getIntent().getExtras();

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
        tv_fechaDer.setOnClickListener(view -> {
            final Calendar c1 = Calendar.getInstance();
            if (tv_fechaDer.getText().equals("Ayer")) {
                c1.add(Calendar.DATE, -1);
                int yearAyer = c1.get(Calendar.YEAR);
                int monthAyer = c1.get(Calendar.MONTH);
                int dayAyer = c1.get(Calendar.DAY_OF_MONTH);
                asignarFecha(yearAyer, monthAyer, dayAyer);
                tv_fechaIzq.setText(getResources().getString(R.string.ayer));
                tv_fechaDer.setText(getResources().getString(R.string.hoy));
            } else {
                int year1 = c1.get(Calendar.YEAR);
                int month1 = c1.get(Calendar.MONTH);
                int day1 = c1.get(Calendar.DAY_OF_MONTH);
                asignarFecha(year1, month1, day1);
                tv_fechaIzq.setText(getResources().getString(R.string.hoy));
                tv_fechaDer.setText(getResources().getString(R.string.ayer));
            }
        });
        tv_fechaIzq.setOnClickListener(view -> abrirDialogoFecha());
        tv_agregarFoto.setOnClickListener(view ->
                ImagePicker.Companion.with(Transaccion.this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start());
        ll_billeteras_transaccion.setOnClickListener(v -> abrirSeleccionCartera());
        iv_categoria.setOnClickListener(view -> abrirSeleccionCategoria());

        if (datos != null) {
            Movimiento m = new Movimiento();
            m.setId(datos.getInt("id"));
            m.setTransaccion(datos.getDouble("transaccion"));
            m.setNombre_cartera(datos.getString("nombre_cartera"));
            m.setCategoria(datos.getString("categoria"));
            m.setDia(datos.getInt("dia"));
            m.setMes(datos.getInt("mes"));
            m.setAnio(datos.getInt("anio"));
            m.setNota(datos.getString("nota"));

            tv_titulo.setText(getResources().getString(R.string.editar_transaccion));
            et_transaccion.setText(String.valueOf(m.getTransaccion()));
            tv_billeteraDer.setText(m.getNombre_cartera());

            if (m.getTransaccion() > 0)
                tipo_categoria = "ingreso";
            else tipo_categoria = "gasto";
            aplicarEleccionCategoria(m.getCategoria(), tipo_categoria);
            asignarFecha(dia, mes, anio);
            tv_fechaIzq.setText(dia + "-" + mes + "-" + anio);
            btn_aceptar.setOnClickListener(v -> {
                if (!et_transaccion.getText().toString().isEmpty() || et_transaccion.getText().toString().equals("0")) {
                    BDMovimientos bd = new BDMovimientos(Transaccion.this);
                    Movimiento mNuevo = new Movimiento();
                    m.setNombre_cartera(tv_billeteraDer.getText().toString());
                    double transac = Double.parseDouble(et_transaccion.getText().toString());
                    if (tipo_categoria.equals("gasto")) transac = -transac;
                    mNuevo.setTransaccion(transac);
                    mNuevo.setCategoria(categoria);
                    mNuevo.setNota(et_nota.getText().toString());
                    mNuevo.setAnio(anio);
                    mNuevo.setDia(dia);
                    mNuevo.setMes(mes);
                    mNuevo.setId(m.getId());
                    bd.modificarMovimiento(m, mNuevo);
                } else {
                    String s = getResources().getString(R.string.toast_falta_valor_transaccion);
                    Toast.makeText(Transaccion.this, s, Toast.LENGTH_SHORT).show();
                    et_transaccion.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(et_transaccion, InputMethodManager.SHOW_IMPLICIT);
                }
            });

        } else
            btn_aceptar.setOnClickListener(v -> {
                if (categoria != null) {
                    if (!et_transaccion.getText().toString().isEmpty() || et_transaccion.getText().toString().equals("0")) {
                        if (!tv_billeteraDer.getText().toString().isEmpty()) {
                            BDMovimientos bdMovimientos = new BDMovimientos(Transaccion.this);
                            Movimiento m = new Movimiento();
                            m.setNombre_cartera(tv_billeteraDer.getText().toString());
                            double transac = Double.parseDouble(et_transaccion.getText().toString());
                            if (tipo_categoria.equals("gasto")) transac = -transac;
                            m.setTransaccion(transac);
                            m.setCategoria(categoria);
                            m.setNota(et_nota.getText().toString());
                            m.setAnio(anio);
                            m.setDia(dia);
                            m.setMes(mes);
                            m.setId(bdMovimientos.getId());
                            bdMovimientos.addMovimiento(m);
                            finish();
                        } else {
                            String s = getResources().getString(R.string.toast_falta_cartera);
                            Toast.makeText(Transaccion.this, s, Toast.LENGTH_SHORT).show();
                            abrirSeleccionCartera();
                        }
                    } else {
                        String s = getResources().getString(R.string.toast_falta_valor_transaccion);
                        Toast.makeText(Transaccion.this, s, Toast.LENGTH_SHORT).show();
                        et_transaccion.requestFocus();
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(et_transaccion, InputMethodManager.SHOW_IMPLICIT);
                    }
                } else {
                    String s = getResources().getString(R.string.toast_falta_seleccionar_categoria);
                    Toast.makeText(Transaccion.this, s, Toast.LENGTH_SHORT).show();
                    abrirSeleccionCategoria();
                }
            });
    }

    private void abrirSeleccionCartera() {
        EleccionBilletera dialogo = new EleccionBilletera();
        dialogoEleccionBilletera = dialogo;
        dialogo.setCancelable(false);
        dialogo.show(getSupportFragmentManager(), "dialogo");
    }

    private void abrirSeleccionCategoria() {
        EleccionCategoria dialogo = new EleccionCategoria(Transaccion.this);
        dialogoEleccionCategoria = dialogo;
        dialogo.setCancelable(false);
        dialogo.show(getSupportFragmentManager(), "dialogo");
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
        if (nombre.equals(getResources().getString(R.string.cv_creacion_billetera))) {
            dialogoEleccionBilletera.dismiss();
        } else
            tv_billeteraDer.setText(nombre);
        dialogoEleccionBilletera.dismiss();
    }

    public void aplicarEleccionCategoria(String nombre_categoria, String tipo) {
        BDCategorias bdCategorias = new BDCategorias(this);
        iv_categoria.setBackgroundResource(0);
        iv_categoria.setImageResource(0);
        iv_categoria.setImageResource(bdCategorias.getIcono(nombre_categoria));
        categoria = nombre_categoria;
        ll_panel_transacciones.setBackgroundColor(Color.parseColor(bdCategorias.getColor(nombre_categoria)));
        if (tipo.equals("ingreso")) tipo_categoria = "ingreso";
        else {
            tipo_categoria = "gasto";
        }
        dialogoEleccionCategoria.dismiss();


    }


    private void asignarFecha(int year, int month, int day) {
        anio = year;
        mes = month;
        dia = day;
    }

}