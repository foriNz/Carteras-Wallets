package com.example.cartera_v1.Adaptadores;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartera_v1.Activities.Fragments.Cronologia;
import com.example.cartera_v1.Entidades.Movimiento;

import java.util.ArrayList;

public class MovimientosAdapter_Movimientos extends RecyclerView.Adapter<MovimientosAdapter_Movimientos.MovimientoViewHolder>{
    Context context;
    ArrayList<Movimiento> listaMovimientos;
    public MovimientosAdapter_Movimientos(ArrayList<Movimiento> listaMovimientos, Context context) {
        this.listaMovimientos = listaMovimientos;
        this.context = context;
    }
    @NonNull
    @Override
    public MovimientosAdapter_Movimientos.MovimientoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MovimientosAdapter_Movimientos.MovimientoViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    class MovimientoViewHolder extends RecyclerView.ViewHolder {
        public MovimientoViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
