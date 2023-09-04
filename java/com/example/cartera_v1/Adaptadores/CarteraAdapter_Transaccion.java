package com.example.cartera_v1.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartera_v1.Activities.Transacciones;
import com.example.cartera_v1.Entidades.Cartera;
import com.example.cartera_v1.R;

import java.util.ArrayList;

public class CarteraAdapter_Transaccion extends RecyclerView.Adapter<CarteraAdapter_Transaccion.CarteraViewHolder> {

    Context context;
    ArrayList<Cartera> listaCartera;

    public CarteraAdapter_Transaccion(ArrayList<Cartera> listaCartera, Context context) {
        this.listaCartera = listaCartera;
        this.context = context;
    }

    @NonNull
    @Override
    public CarteraAdapter_Transaccion.CarteraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_cartera_transaccion,null,false);
        view.setFocusable(true);

        return new CarteraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarteraAdapter_Transaccion.CarteraViewHolder holder, int position) {
        holder.tv_nombrebilletera.setText(listaCartera.get(position).getNombre());
        holder.tv_dinerobilletera.setText(String.valueOf(listaCartera.get(position).getBalance()));
    }

    @Override
    public int getItemCount() {
        return listaCartera.size();
    }
    public class CarteraViewHolder extends RecyclerView.ViewHolder {
        TextView tv_nombrebilletera, tv_dinerobilletera;
        public CarteraViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nombrebilletera = itemView.findViewById(R.id.tv_dial_nombre_billetera);
            tv_dinerobilletera = itemView.findViewById(R.id.tv_dial_dinero_billetera);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (context instanceof Transacciones)
                        ((Transacciones)context).aplicarEleccionBilletera(tv_nombrebilletera.getText().toString());
                }
            });
        }
    }
}
