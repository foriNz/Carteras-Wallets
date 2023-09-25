package com.example.cartera_v1.Activities.Dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.cartera_v1.Activities.CreacionRecordatorio;
import com.example.cartera_v1.Activities.EstadisticasCartera;
import com.example.cartera_v1.Adaptadores.IntervaloAdapter_Recordatorios;
import com.example.cartera_v1.MainActivity;
import com.example.cartera_v1.R;

import java.util.ArrayList;

public class EleccionIntervalo extends AppCompatDialogFragment {
    RecyclerView rv_dialogo_eleccion_intervalo;
    public Window window;
    IntervaloAdapter_Recordatorios.IntervaloListener listener;
    IntervaloAdapter_Recordatorios.PeriodosListener periodosListener;

    public EleccionIntervalo(IntervaloAdapter_Recordatorios.PeriodosListener periodosListener) {
        this.periodosListener = periodosListener;
    }

    public EleccionIntervalo() {
    }

    public EleccionIntervalo(IntervaloAdapter_Recordatorios.IntervaloListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_eleccion_intervalo, null);

        rv_dialogo_eleccion_intervalo = view.findViewById(R.id.rv_dialogo_eleccion_intervalo);
        rv_dialogo_eleccion_intervalo.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<String> listaParametros = new ArrayList<>();
        if (getContext() instanceof CreacionRecordatorio) {
            listaParametros.add(getResources().getString(R.string.todos_los_dias));
            listaParametros.add(getResources().getString(R.string.todos_las_semanas));
            listaParametros.add(getResources().getString(R.string.cada_cuatro_semanas));
            listaParametros.add(getResources().getString(R.string.cada_año));
        } else if (getContext() instanceof EstadisticasCartera || getContext() instanceof MainActivity) {
            listaParametros.add(getResources().getString(R.string.por_año));
            listaParametros.add(getResources().getString(R.string.por_meses));
            listaParametros.add(getResources().getString(R.string.todo_el_historial));
        }
        if (getContext() instanceof MainActivity)
            rv_dialogo_eleccion_intervalo.setAdapter(new IntervaloAdapter_Recordatorios(getContext(), listaParametros, periodosListener));
        else
            rv_dialogo_eleccion_intervalo.setAdapter(new IntervaloAdapter_Recordatorios(getContext(), listaParametros, listener));

        builder.setView(view);
        return builder.create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        window = getDialog().getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        getDialog().setCanceledOnTouchOutside(true);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}