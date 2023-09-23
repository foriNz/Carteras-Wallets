package com.example.cartera_v1.Adaptadores;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartera_v1.Entidades.Model_Data_Categoria;
import com.example.cartera_v1.R;

import java.util.ArrayList;

public class CategoriasAdapter_Estadisticas extends RecyclerView.Adapter<CategoriasAdapter_Estadisticas.CategoriaViewHolder> {
    Context context;
    ArrayList<Model_Data_Categoria> listaCategorias;

    public CategoriasAdapter_Estadisticas(Context context, ArrayList<Model_Data_Categoria> listaCategorias) {
        this.context = context;
        this.listaCategorias = listaCategorias;
    }

    @NonNull
    @Override
    public CategoriasAdapter_Estadisticas.CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_categoria_estadisticas, null, false);
        view.setFocusable(true);

        return new CategoriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriasAdapter_Estadisticas.CategoriaViewHolder holder, int position) {
        Drawable icon = ContextCompat.getDrawable(context, listaCategorias.get(position).getIcono());
        holder.icono.setImageDrawable(icon);
        holder.icono.setColorFilter(Color.parseColor(listaCategorias.get(position).getColor()));
        holder.nombre.setText(listaCategorias.get(position).getNombre());
        holder.balance.setText(String.valueOf(listaCategorias.get(position).getBalance()));
        if (listaCategorias.get(position).getBalance() > 0)
            holder.balance.setTextColor(Color.GREEN);
        else holder.balance.setTextColor(Color.RED);
        if (listaCategorias.get(position).getUsanzas() == 1)
            holder.usanzas.setText(String.valueOf(listaCategorias.get(position).getUsanzas()) + " " + context.getResources().getString(R.string.usanzas_singular));
        else
            holder.usanzas.setText(String.valueOf(listaCategorias.get(position).getUsanzas()) + " " + context.getResources().getString(R.string.usanzas_plural));

    }

    @Override
    public int getItemCount() {
        return listaCategorias.size();
    }

    class CategoriaViewHolder extends RecyclerView.ViewHolder {
        ImageView icono;
        TextView nombre, usanzas, balance;

        public CategoriaViewHolder(@NonNull View itemView) {
            super(itemView);
            icono = itemView.findViewById(R.id.iv_estadisticas_icono);
            nombre = itemView.findViewById(R.id.tv_nombre_categoria_estadisticas);
            usanzas = itemView.findViewById(R.id.tv_usanzas_categoria_estadisticas);
            balance = itemView.findViewById(R.id.tv_balance_categoria_estadisticas);
        }
    }
}
