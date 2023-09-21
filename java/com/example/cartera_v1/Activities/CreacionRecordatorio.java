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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.cartera_v1.Adaptadores.RecordatorioReceiver;
import com.example.cartera_v1.BBDD.BDRecordatorio;
import com.example.cartera_v1.Entidades.Recordatorio;
import com.example.cartera_v1.R;

import java.util.Calendar;

public class CreacionRecordatorio extends AppCompatActivity {
    private static final int EXISTING_VEHICLE_LOADER = 0;

    private TextView titulo_activity, intervalo;
    EditText txt_titulo, descripcion;
    DatePicker dp_fecha;
    TimePicker tp_tiempo;
    private Calendar calendario;
    private Switch switch_repeticion;
    Button btn_aceptar;
    public static final String channelId = "recordatorio_channel";


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
        // intervalo
        agregarFuncionalidades();
    }

    private void agregarFuncionalidades() {
        tp_tiempo.setIs24HourView(true);
        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Recordatorio r = new Recordatorio();
                r.setTitulo(txt_titulo.getText().toString());
                r.setDescripcion(descripcion.getText().toString());
                r.setFecha(dp_fecha.getDayOfMonth() + "/" + (dp_fecha.getMonth() + 1) + "/" + dp_fecha.getYear() + " " + tp_tiempo.getHour() + ":" + tp_tiempo.getMinute());
                r.setRepeticion(switch_repeticion.isSelected());
                //r.setIntervalo();
                crearCanalDeNotificaciones();

                BDRecordatorio bd = new BDRecordatorio(CreacionRecordatorio.this);
                bd.agregarRecordatorio(r);

                int id = bd.getRecordatoriosId(r);
                //long fechaEnMilis = obtenerTiempoDelRecordatorio();
                long fechaEnMilis = obtenerTiempoDelRecordatorio();

                programarNotificacion(CreacionRecordatorio.this,fechaEnMilis,id);
                finish();
            }
        });
    }

    private long obtenerTiempoDelRecordatorio() {
        Calendar fechaActual = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, dp_fecha.getYear());
        calendar.set(Calendar.MONTH, dp_fecha.getMonth());
        calendar.set(Calendar.DAY_OF_MONTH, dp_fecha.getDayOfMonth());
        calendar.set(Calendar.HOUR_OF_DAY, tp_tiempo.getHour());
        calendar.set(Calendar.MINUTE, tp_tiempo.getMinute());
        //  calendar.set(Calendar.HOUR_OF_DAY, 8);
        //  calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        fechaActual.set(Calendar.SECOND, 0);

        // Devolver la marca de tiempo en milisegundos
        //long resultado = System.currentTimeMillis()- fechaActual.getTimeInMillis();
        long resultado = calendar.getTimeInMillis() - fechaActual.getTimeInMillis();
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
    private void programarNotificacion(Context context, long triggerTimeMillis, int id) {
        System.out.println("TRIGERTIMEINTMILLIS : "+ triggerTimeMillis+"\n");
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, RecordatorioReceiver.class);
        intent.putExtra("titulo", txt_titulo.getText().toString());
        intent.putExtra("descripcion", descripcion.getText().toString());
        intent.putExtra("id", id);
        intent.setAction("com.example.cartera_v1.ALARMA");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_ONE_SHOT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            alarmManager.canScheduleExactAlarms();
        } else
        // Programar la notificación en el momento especificado
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTimeMillis, pendingIntent);
    }
}