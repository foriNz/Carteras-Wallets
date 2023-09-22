package com.example.cartera_v1.Adaptadores;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.cartera_v1.Activities.CreacionRecordatorio;
import com.example.cartera_v1.BBDD.BDRecordatorio;
import com.example.cartera_v1.Entidades.Recordatorio;
import com.example.cartera_v1.R;

public class RecordatorioReceiver extends BroadcastReceiver {
    Context context;

    public void onReceive(Context context, Intent intent) {
        // Aquí puedes mostrar la notificación periódica al usuario
        this.context = context;
        Bundle d = intent.getExtras();
        showNotification(context, d.getString("titulo"), d.getString("descripcion"), d.getInt("id"), d.getInt("repeticion"));
    }

    private void showNotification(Context context, String title, String message, int id, int repeticion) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CreacionRecordatorio.channelId)
                .setSmallIcon(R.drawable.outline_notifications_active_24)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(id, builder.build());
        if (repeticion == 0){
            BDRecordatorio bdr = new BDRecordatorio(context);
            bdr.eliminarRecordatorio(id);
        }
    }
}
