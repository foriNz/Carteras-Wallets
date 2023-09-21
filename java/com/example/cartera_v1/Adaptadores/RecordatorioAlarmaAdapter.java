package com.example.cartera_v1.Adaptadores;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.metrics.Event;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartera_v1.Activities.CreacionRecordatorio;
import com.example.cartera_v1.Activities.Fragments.Recordatorios;
import com.example.cartera_v1.Activities.Transaccion;
import com.example.cartera_v1.BBDD.BDRecordatorio;
import com.example.cartera_v1.Entidades.Recordatorio;
import com.example.cartera_v1.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecordatorioAlarmaAdapter extends RecyclerView.Adapter<RecordatorioAlarmaAdapter.RecordatorioViewHolder>{
    ArrayList<Recordatorio> listaRecordatorios;
    Context context;
    EventListener eventListener;

    public RecordatorioAlarmaAdapter(ArrayList<Recordatorio> listaRecordatorios, Context context, EventListener eventListener) {
        this.listaRecordatorios = listaRecordatorios;
        this.context = context;
        this.eventListener = eventListener;
    }

    @NonNull
    @Override
    public RecordatorioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lista_recordatorio,null, false);
        return new RecordatorioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordatorioViewHolder holder, int position) {
        holder.titulo.setText(listaRecordatorios.get(position).getTitulo());
        holder.descripcion.setText(listaRecordatorios.get(position).getDescripcion());
        holder.fecha.setText(listaRecordatorios.get(position).getFecha());
        holder.eventListener = eventListener;
    }

    @Override
    public int getItemCount() {
        return listaRecordatorios.size();
    }

    class RecordatorioViewHolder extends RecyclerView.ViewHolder {
        TextView titulo, descripcion, fecha;
        ImageView borrar;
        EventListener eventListener;
        public RecordatorioViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.tv_titulo_recordatorio);
            descripcion = itemView.findViewById(R.id.tv_descripcion);
            fecha = itemView.findViewById(R.id.tv_fecha_recordatorio);
            borrar = itemView.findViewById(R.id.iv_borrar_recordatorio);
            borrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BDRecordatorio bdr = new BDRecordatorio(context);
                    Recordatorio r = new Recordatorio();
                    r.setTitulo(titulo.getText().toString());
                    r.setDescripcion(descripcion.getText().toString());
                    r.setId(bdr.getRecordatoriosId(r));
                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                    Intent intent = new Intent(context, RecordatorioReceiver.class);
                    intent.putExtra("titulo", titulo.getText().toString());
                    intent.putExtra("descripcion", descripcion.getText().toString());
                    intent.putExtra("id", r.getId());
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(context,r.getId() , intent, PendingIntent.FLAG_ONE_SHOT);

                    // Eliminar la notificación
                    alarmManager.cancel(pendingIntent);
                    bdr.eliminarRecordatorio(r.getId());
                    eventListener.aplicarEliminacion();
                }
            });

            }

    }
    public interface EventListener {
        void aplicarEliminacion();
    }
}
