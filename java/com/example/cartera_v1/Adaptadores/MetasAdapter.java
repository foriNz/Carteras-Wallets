package com.example.cartera_v1.Adaptadores;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartera_v1.Entidades.Meta;
import com.example.cartera_v1.R;

import java.util.ArrayList;

public class MetasAdapter extends RecyclerView.Adapter<MetasAdapter.MetaViewHolder> {
    Context context;
    ArrayList<Meta> listaMetas;

    public MetasAdapter(Context context, ArrayList<Meta> listaMetas) {
        this.context = context;
        this.listaMetas = listaMetas;
    }

    @NonNull
    @Override
    public MetaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_metas, null, false);
        return new MetaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MetaViewHolder holder, int position) {
        holder.titulo.setText(listaMetas.get(position).getCaracter() + " - " + listaMetas.get(position).getCategoria());
        holder.valor_objetivo.setText(String.valueOf(listaMetas.get(position).getValor_objetivo()));
        holder.valor_actual.setText(String.valueOf(listaMetas.get(position).getValor_actual()));
        holder.meta.setProgress((int) Math.round(listaMetas.get(position).getPorcentaje_objetivo()));
        if (listaMetas.get(position).getTipo_categoria().equals(context.getResources().getString(R.string.gasto)))
            holder.meta.getProgressDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
        ;
    }

    @Override
    public int getItemCount() {
        return listaMetas.size();
    }

    class MetaViewHolder extends RecyclerView.ViewHolder {
        TextView titulo, valor_actual, valor_objetivo;
        ProgressBar meta;

        public MetaViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.tv_titulo_meta);
            valor_actual = itemView.findViewById(R.id.tv_valor_actual_meta);
            valor_objetivo = itemView.findViewById(R.id.tv_valor_objetivo);
            meta = itemView.findViewById(R.id.pb_meta);
        }

    }
}
