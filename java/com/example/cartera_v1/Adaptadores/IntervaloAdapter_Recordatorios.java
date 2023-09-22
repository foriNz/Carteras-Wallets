package com.example.cartera_v1.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartera_v1.Entidades.Cartera;
import com.example.cartera_v1.R;

import java.util.ArrayList;

public class IntervaloAdapter_Recordatorios extends RecyclerView.Adapter<IntervaloAdapter_Recordatorios.IntervaloViewHolder>{
    Context context;
    ArrayList<String> lista;
    IntervaloListener eventListener;

    public IntervaloAdapter_Recordatorios(Context context, ArrayList<String> lista, IntervaloListener listener) {
        this.context = context;
        this.lista = lista;
        eventListener = listener;
    }


    @NonNull
    @Override
    public IntervaloViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_intervalo_recordatorios,null,false);
        view.setFocusable(true);

        return new IntervaloViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IntervaloViewHolder holder, int position) {
        holder.nombre.setText(lista.get(position));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    class IntervaloViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        public IntervaloViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tv_nombre_intervalo);
            nombre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eventListener.aplicarEleccion(nombre.getText().toString());
                }
            });
        }
    }
    public interface IntervaloListener {
        void aplicarEleccion(String intervalo);
    }
}
