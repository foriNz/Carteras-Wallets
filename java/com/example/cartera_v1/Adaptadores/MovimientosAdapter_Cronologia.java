package com.example.cartera_v1.Adaptadores;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartera_v1.BBDD.BDCategorias;
import com.example.cartera_v1.Entidades.Model_Fecha_Movimientos;
import com.example.cartera_v1.Entidades.Movimiento;
import com.example.cartera_v1.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MovimientosAdapter_Cronologia extends RecyclerView.Adapter<MovimientosAdapter_Cronologia.FechaMovimientoViewHolder> {
    ArrayList<Model_Fecha_Movimientos> listaMovimientos;
    Context context;

    public MovimientosAdapter_Cronologia(ArrayList<Model_Fecha_Movimientos> listaMovimientos, Context context) {
        this.listaMovimientos = listaMovimientos;
        this.context = context;
    }

    @NonNull
    @Override
    public FechaMovimientoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_group_lista_fecha_movimiento_cronologa, null, false);

        return new FechaMovimientoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FechaMovimientoViewHolder holder, int position) {
        holder.fecha.setText(listaMovimientos.get(position).getFecha());
        holder.balance.setText(listaMovimientos.get(position).getBalance_total()+"");

        ListaMovimientosAdapter ad = new ListaMovimientosAdapter(listaMovimientos.get(position).getListaMovimientos(), context);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.recyclerView.setAdapter(ad);
    }

    @Override
    public int getItemCount() {
        return listaMovimientos.size();
    }

    class FechaMovimientoViewHolder extends RecyclerView.ViewHolder {

        TextView balance, fecha;
        RecyclerView recyclerView;

        public FechaMovimientoViewHolder(@NonNull View itemView) {
            super(itemView);
            fecha = itemView.findViewById(R.id.tv_item_fecha_cronologia);
            balance = itemView.findViewById(R.id.tv_item_balance_dia);
            recyclerView = itemView.findViewById(R.id.rv_item_lista_fecha_movimiento);
        }
    }

    private class ListaMovimientosAdapter extends RecyclerView.Adapter<ListaMovimientosAdapter.MovimientoViewHolder> {
        ArrayList<Movimiento> listaMovimientos;
        Context context;

        public ListaMovimientosAdapter(ArrayList<Movimiento> listaMovimientos, Context context) {
            this.listaMovimientos = listaMovimientos;
            this.context = context;
        }

        @NonNull
        @Override
        public ListaMovimientosAdapter.MovimientoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate((R.layout.item_child_lista_movimiento_cronologia), null, false);
            return new ListaMovimientosAdapter.MovimientoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ListaMovimientosAdapter.MovimientoViewHolder holder, int position) {
            BDCategorias bd = new BDCategorias(context);
            holder.civ_icono_item_cronologia.setImageResource(bd.getIcono(listaMovimientos.get(position).getCategoria()));
            holder.civ_icono_item_cronologia.setColorFilter(Color.parseColor(bd.getColor(listaMovimientos.get(position).getCategoria())));
            holder.tv_categoria_item_cronologia.setText(listaMovimientos.get(position).getCategoria());
            holder.tv_cartera_item_cronologia.setText(listaMovimientos.get(position).getNombre_cartera());
            holder.tv_balance_item_cronologia.setText(listaMovimientos.get(position).getTransaccion()+"");
            holder.id = listaMovimientos.get(position).getId();
        }

        @Override
        public int getItemCount() {
            return listaMovimientos.size();
        }

        class MovimientoViewHolder extends RecyclerView.ViewHolder {
            CircleImageView civ_icono_item_cronologia;
            TextView tv_categoria_item_cronologia, tv_cartera_item_cronologia, tv_balance_item_cronologia;
            int id;

            public MovimientoViewHolder(@NonNull View itemView) {
                super(itemView);
                civ_icono_item_cronologia = itemView.findViewById(R.id.civ_icono_item_cronologia);
                tv_categoria_item_cronologia = itemView.findViewById(R.id.tv_categoria_item_cronologia);
                tv_cartera_item_cronologia = itemView.findViewById(R.id.tv_cartera_item_cronologia);
                tv_balance_item_cronologia = itemView.findViewById(R.id.tv_balance_item_cronologia);
            }
        }
    }
}
