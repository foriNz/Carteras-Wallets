package com.example.cartera_v1.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartera_v1.Activities.CreacionCartera;
import com.example.cartera_v1.Activities.EstadisticasCartera;
import com.example.cartera_v1.Activities.Transaccion;
import com.example.cartera_v1.Entidades.Cartera;
import com.example.cartera_v1.MainActivity;
import com.example.cartera_v1.R;

import java.util.ArrayList;

public class CarterasAdapter_Transaccion extends RecyclerView.Adapter<CarterasAdapter_Transaccion.CarteraViewHolder> {

    Context context;
    ArrayList<Cartera> listaCartera;

    public CarterasAdapter_Transaccion(ArrayList<Cartera> listaCartera, Context context) {
        this.listaCartera = listaCartera;
        this.context = context;
        if (listaCartera.size() == 0)
            listaCartera.add(new Cartera(context.getResources().getString(R.string.cv_creacion_billetera)));

    }

    @NonNull
    @Override
    public CarterasAdapter_Transaccion.CarteraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_cartera_transaccion, null, false);
        view.setFocusable(true);

        return new CarteraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarterasAdapter_Transaccion.CarteraViewHolder holder, int position) {
        if (listaCartera.get(position).getNombre().equals(context.getResources().getString(R.string.cv_creacion_billetera))) {
            holder.tv_nombrebilletera.setText(context.getResources().getString(R.string.cv_creacion_billetera));
            holder.tv_nombrebilletera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, CreacionCartera.class);
                    context.startActivity(i);
                }
            });
        } else {
            holder.tv_nombrebilletera.setText(listaCartera.get(position).getNombre());
            holder.tv_dinerobilletera.setText(String.valueOf(listaCartera.get(position).getBalance()));
        }
    }

    @Override
    public int getItemCount() {
        return listaCartera.size();
    }

    public class CarteraViewHolder extends RecyclerView.ViewHolder {
        TextView tv_nombrebilletera, tv_dinerobilletera;
        LinearLayout ll_item_cartera_eleccion;

        public CarteraViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nombrebilletera = itemView.findViewById(R.id.tv_dial_nombre_billetera);
            tv_dinerobilletera = itemView.findViewById(R.id.tv_dial_dinero_billetera);
            ll_item_cartera_eleccion = itemView.findViewById(R.id.ll_item_cartera_eleccion);

            ll_item_cartera_eleccion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (context instanceof Transaccion)
                        ((Transaccion) context).aplicarEleccionCartera(tv_nombrebilletera.getText().toString());
                    else if (context instanceof MainActivity)
                        context.startActivity(new Intent(context, EstadisticasCartera.class));
                }
            });
        }
    }
}
