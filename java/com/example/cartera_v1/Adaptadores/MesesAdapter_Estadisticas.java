package com.example.cartera_v1.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartera_v1.Activities.EstadisticasCartera;
import com.example.cartera_v1.R;

public class MesesAdapter_Estadisticas extends RecyclerView.Adapter<MesesAdapter_Estadisticas.MesViewHolder> {
    Context context;
    Integer[] listaUsos;
    String[] meses;

    public MesesAdapter_Estadisticas(Context context, Integer[] listaUsos) {
        this.context = context;
        this.listaUsos = listaUsos;
        meses = new String[12];
        meses[0] = context.getResources().getString(R.string.enero);
        meses[1] = context.getResources().getString(R.string.febrero);
        meses[2] = context.getResources().getString(R.string.marzo);
        meses[3] = context.getResources().getString(R.string.abril);
        meses[4] = context.getResources().getString(R.string.mayo);
        meses[5] = context.getResources().getString(R.string.junio);
        meses[6] = context.getResources().getString(R.string.julio);
        meses[7] = context.getResources().getString(R.string.agosto);
        meses[8] = context.getResources().getString(R.string.septiembre);
        meses[9] = context.getResources().getString(R.string.octubre);
        meses[10] = context.getResources().getString(R.string.noviembre);
        meses[11] = context.getResources().getString(R.string.diciembre);

    }

    @NonNull
    @Override
    public MesesAdapter_Estadisticas.MesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_mes_estadisticas, null, false);
        view.setFocusable(true);

        return new MesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MesesAdapter_Estadisticas.MesViewHolder holder, int position) {
        holder.mes.setText(meses[position]);
        if (listaUsos[position] == null) {
            holder.usos.setText("0 " + context.getResources().getString(R.string.uso_plural));

        } else if (listaUsos[position] <= 1)
            holder.usos.setText(String.valueOf(listaUsos[position]) + " " + context.getResources().getString(R.string.uso_singular));
        else
            holder.usos.setText(String.valueOf(listaUsos[position]) + " " + context.getResources().getString(R.string.uso_plural));
    }

    @Override
    public int getItemCount() {
        return meses.length;
    }

    class MesViewHolder extends RecyclerView.ViewHolder {
        TextView mes, usos;
        LinearLayout ll_item_mes;

        public MesViewHolder(@NonNull View itemView) {
            super(itemView);
            mes = itemView.findViewById(R.id.tv_item_mes);
            usos = itemView.findViewById(R.id.tv_item_mes_usanzas);
            ll_item_mes = itemView.findViewById(R.id.ll_item_mes);
            ll_item_mes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (context instanceof EstadisticasCartera)
                        ((EstadisticasCartera) context).aplicarEleccionMes(mes.getText().toString());
                }
            });
        }
    }
}
