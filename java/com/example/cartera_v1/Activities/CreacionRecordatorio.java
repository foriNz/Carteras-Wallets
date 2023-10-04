package com.example.cartera_v1.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.cartera_v1.Activities.Dialogos.EleccionBilletera;
import com.example.cartera_v1.Activities.Dialogos.EleccionIntervalo;
import com.example.cartera_v1.Adaptadores.IntervaloAdapter_Recordatorios;
import com.example.cartera_v1.Adaptadores.RecordatorioReceiver;
import com.example.cartera_v1.BBDD.BDRecordatorio;
import com.example.cartera_v1.Entidades.Recordatorio;
import com.example.cartera_v1.R;

import java.util.Calendar;
import java.util.Locale;

public class CreacionRecordatorio extends AppCompatActivity implements IntervaloAdapter_Recordatorios.IntervaloListener {
    private static final int EXISTING_VEHICLE_LOADER = 0;

    private TextView titulo_activity, intervalo;
    EditText txt_titulo, descripcion;
    DatePicker dp_fecha;
    TimePicker tp_tiempo;
    private Calendar calendario;
    private Switch switch_repeticion;
    Button btn_aceptar;
    public static final String channelId = "recordatorio_channel";
    EleccionIntervalo dialogoEleccionIntervalo;


    private static final int valor_minuto = 60000;
    private static final long valor_hora = 33600000L;
    private static final long valor_dia = 86400000L;
    private static final long valor_semana = 604800000L;
    private static final long valor_anio = 2592000000L;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titulo_activity = findViewById(R.id.tv_titulo_activity_recordatorio);
        setContentView(R.layout.activity_creacion_recordatorio);
        txt_titulo = findViewById(R.id.et_titulo_recordatorio);
        dp_fecha = findViewById(R.id.dp_recordatorio);
        tp_tiempo = findViewById(R.id.tp_recordatorio);
        switch_repeticion = findViewById(R.id.switch_repeticion);
        descripcion = findViewById(R.id.et_mensaje_recordatorio);
        btn_aceptar = findViewById(R.id.btn_aceptar_recordatorio);
        intervalo = findViewById(R.id.tv_intervalo_recordatorio);
        agregarFuncionalidades();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
    }

    private void agregarFuncionalidades() {
        tp_tiempo.setIs24HourView(true);
        intervalo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switch_repeticion.isChecked()) {
                    EleccionIntervalo dialogo = new EleccionIntervalo(new IntervaloAdapter_Recordatorios.IntervaloListener() {
                        @Override
                        public void aplicarEleccion(String intervalo) {
                            CreacionRecordatorio.this.intervalo.setText(intervalo);
                            dialogoEleccionIntervalo.dismiss();
                        }
                    });
                    dialogoEleccionIntervalo = dialogo;
                    dialogo.setCancelable(false);
                    dialogo.show(getSupportFragmentManager(), "dialogo");
                }
            }
        });
        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txt_titulo.getText().toString().trim().isEmpty()) {
                    if ((switch_repeticion.isChecked() && !intervalo.equals(R.string.repeticion_intervalo_recordatorio)) || !switch_repeticion.isChecked()) {
                        Recordatorio r = new Recordatorio();
                        r.setTitulo(txt_titulo.getText().toString());
                        r.setDescripcion(descripcion.getText().toString());
                        r.setFecha(dp_fecha.getDayOfMonth() + "/" + (dp_fecha.getMonth() + 1) + "/" + dp_fecha.getYear() + " " + tp_tiempo.getHour() + ":" + tp_tiempo.getMinute());
                        r.setRepeticion(switch_repeticion.isChecked());
                        if (r.getRepeticion() == 1)
                            if (intervalo.getText().toString().equals(getResources().getString(R.string.todos_los_dias))) {
                                r.setIntervalo(valor_dia);
                            } else if (intervalo.getText().toString().equals(getResources().getString(R.string.todos_las_semanas))) {
                                r.setIntervalo(valor_semana);
                            } else if (intervalo.getText().toString().equals(getResources().getString(R.string.cada_cuatro_semanas))) {
                                r.setIntervalo(valor_semana * 4);
                            } else if (intervalo.getText().toString().equals(getResources().getString(R.string.cada_año))) {
                                r.setIntervalo(valor_anio);
                            }

                        crearCanalDeNotificaciones();

                        BDRecordatorio bd = new BDRecordatorio(CreacionRecordatorio.this);
                        bd.agregarRecordatorio(r);

                        int id = bd.getRecordatoriosId(r);
                        long fechaEnMilis = obtenerTiempoDelRecordatorio() + System.currentTimeMillis();

                        programarNotificacion(CreacionRecordatorio.this, fechaEnMilis, r);
                        finish();
                    } else {
                        Toast.makeText(CreacionRecordatorio.this, getResources().getString(R.string.toast_falta_intervalo), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CreacionRecordatorio.this, getResources().getString(R.string.toast_falta_titulo_recordatorio), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private long obtenerTiempoDelRecordatorio() {
        Calendar fechaActual = Calendar.getInstance();
        Calendar fechaSeleccionada = Calendar.getInstance();

        fechaSeleccionada.set(Calendar.YEAR, dp_fecha.getYear());
        fechaSeleccionada.set(Calendar.MONTH, dp_fecha.getMonth());
        fechaSeleccionada.set(Calendar.DAY_OF_MONTH, dp_fecha.getDayOfMonth());
        fechaSeleccionada.set(Calendar.HOUR_OF_DAY, tp_tiempo.getHour());
        fechaSeleccionada.set(Calendar.MINUTE, tp_tiempo.getMinute());
        //  calendar.set(Calendar.HOUR_OF_DAY, 8);
        //  calendar.set(Calendar.MINUTE, 0);
        fechaSeleccionada.set(Calendar.SECOND, 0);
        fechaActual.set(Calendar.SECOND, 0);

        // Devolver la marca de tiempo en milisegundos
        //long resultado = System.currentTimeMillis()- fechaActual.getTimeInMillis();
        long resultado = fechaSeleccionada.getTimeInMillis() - fechaActual.getTimeInMillis();
        //long resultado = fechaActual.getTimeInMillis()- calendar.getTimeInMillis();
        return resultado;
    }

    private void crearCanalDeNotificaciones() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence channelName = "Recordatorio Channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void programarNotificacion(Context context, long triggerTimeMillis, Recordatorio r) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, RecordatorioReceiver.class);
        intent.putExtra("titulo", txt_titulo.getText().toString());
        intent.putExtra("descripcion", descripcion.getText().toString());
        intent.putExtra("id", r.getId());
        intent.setAction("com.example.cartera_v1.ALARMA");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, r.getId(), intent, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            alarmManager.canScheduleExactAlarms();
        } else {
            if (r.getRepeticion() == 1)
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, triggerTimeMillis, r.getIntervalo(), pendingIntent);
            else // Programar la notificación en el momento especificado
                alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTimeMillis, pendingIntent);
        }
    }

    @Override
    public void aplicarEleccion(String intervalo) {
        this.intervalo.setText(intervalo);
    }
}