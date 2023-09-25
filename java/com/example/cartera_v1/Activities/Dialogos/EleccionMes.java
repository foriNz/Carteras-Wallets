package com.example.cartera_v1.Activities.Dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartera_v1.Adaptadores.MesesAdapter_Estadisticas;
import com.example.cartera_v1.BBDD.BDMovimientos;
import com.example.cartera_v1.R;

public class EleccionMes extends AppCompatDialogFragment {
    RecyclerView meses;
    Integer[] listaUsanzasPorMes;
    int anio = 0;
    String nombre_cartera = "";

    public EleccionMes() {
    }

    public EleccionMes(String nombre_cartera) {
        this.nombre_cartera = nombre_cartera;
    }

    public EleccionMes(int anio) {
        this.anio = anio;
    }

    public EleccionMes(int anio, String nombre_cartera) {
        this.anio = anio;
        this.nombre_cartera = nombre_cartera;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_eleccion_meses, null);
        BDMovimientos bdm = new BDMovimientos(getContext());
        if (nombre_cartera.isEmpty() && anio == 0)
            listaUsanzasPorMes = bdm.getTodosUsosEnCadaMes();
        else if (anio == 0)
            listaUsanzasPorMes = bdm.getTodosUsosEnCadaMes(nombre_cartera);
        else if (nombre_cartera.isEmpty())
            listaUsanzasPorMes = bdm.getUsosEnCadaMes(anio);
        else
            listaUsanzasPorMes = bdm.getUsosEnCadaMes(anio, nombre_cartera);
        meses = view.findViewById(R.id.rv_dialog_meses);
        meses.setLayoutManager(new LinearLayoutManager(getContext()));
        meses.setAdapter(new MesesAdapter_Estadisticas(getContext(),listaUsanzasPorMes));
        builder.setView(view);
        return builder.create();
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
