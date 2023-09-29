package com.example.cartera_v1.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartera_v1.Activities.CreacionCategoria;
import com.example.cartera_v1.BBDD.BDCategorias;
import com.example.cartera_v1.BBDD.BDMovimientos;
import com.example.cartera_v1.Entidades.Categoria;
import com.example.cartera_v1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoriasAdapter_Administrador extends RecyclerView.Adapter<CategoriasAdapter_Administrador.CategoriaViewHolder> {

    Context context;
    ArrayList<Categoria> listaCategorias;
    String tipo;

    public CategoriasAdapter_Administrador(ArrayList<Categoria> listaCategorias, Context context, String tipo) {
        this.listaCategorias = listaCategorias;
        this.context = context;
        this.tipo = tipo;
    }

    @NonNull
    @Override
    public CategoriasAdapter_Administrador.CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_categoria_admin, null, false);
        view.setFocusable(true);

        return new CategoriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriasAdapter_Administrador.CategoriaViewHolder holder, int position) {
        holder.icono_categoria.setImageResource(Integer.parseInt(listaCategorias.get(position).getIcono()));
        holder.categoria.setText(listaCategorias.get(position).getNombre());
        holder.icono_categoria.setCircleBackgroundColor(Color.parseColor(listaCategorias.get(position).getColor()));
        BDMovimientos bd = new BDMovimientos(context);
        int usos = bd.getUsosTotales(listaCategorias.get(position).getNombre(), tipo);
        if (usos > 1)
            holder.usanzas.setText(usos + " " + context.getResources().getString(R.string.usanzas_plural));
        else
            holder.usanzas.setText(usos + " " + context.getResources().getString(R.string.usanzas_singular));
    }

    @Override
    public int getItemCount() {
        return listaCategorias.size();
    }

    public class CategoriaViewHolder extends RecyclerView.ViewHolder {
        CircleImageView icono_categoria;
        TextView categoria, usanzas;
        FloatingActionButton fab_movimiento;

        public CategoriaViewHolder(@NonNull View itemView) {
            super(itemView);
            icono_categoria = itemView.findViewById(R.id.ci_recyclerview_icono_categoria);
            categoria = itemView.findViewById(R.id.tv_item_categoria);
            usanzas = itemView.findViewById(R.id.tv_item_usanzas);
            fab_movimiento = itemView.findViewById(R.id.fab_movimiento_categoria);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    BDCategorias bd = new BDCategorias(context);
                    Intent intent = new Intent(context, CreacionCategoria.class);
                    intent.putExtra("nombre", categoria.getText().toString());
                    intent.putExtra("icono", bd.getIcono(categoria.getText().toString()));
                    intent.putExtra("color", bd.getColor(categoria.getText().toString()));
                    intent.putExtra("tipo", tipo);
                    context.startActivity(intent);
                    return true;
                }
            });

            fab_movimiento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: 04/09/2023 MOVIMIENTO DE ID CATEGORIAS
                }
            });
        }
    }
}
