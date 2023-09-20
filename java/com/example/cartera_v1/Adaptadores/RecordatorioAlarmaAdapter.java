package com.example.cartera_v1.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartera_v1.Entidades.Recordatorio;
import com.example.cartera_v1.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecordatorioAlarmaAdapter extends RecyclerView.Adapter<RecordatorioAlarmaAdapter.RecordatorioViewHolder>{
    ArrayList<Recordatorio> listaRecordatorios;
    Context context;

    public RecordatorioAlarmaAdapter(ArrayList<Recordatorio> listaRecordatorios, Context context) {
        this.listaRecordatorios = listaRecordatorios;
        this.context = context;
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
    }

    @Override
    public int getItemCount() {
        return listaRecordatorios.size();
    }

    class RecordatorioViewHolder extends RecyclerView.ViewHolder {
        TextView titulo, descripcion, fecha;
        ImageView borrar;
        public RecordatorioViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.tv_titulo_recordatorio);
            descripcion = itemView.findViewById(R.id.tv_descripcion);
            fecha = itemView.findViewById(R.id.tv_fecha_recordatorio);
            borrar = itemView.findViewById(R.id.iv_borrar_recordatorio);
        }
    }
}
