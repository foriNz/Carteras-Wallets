package com.example.cartera_v1.Activities.Dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartera_v1.Activities.EstadisticasCartera;
import com.example.cartera_v1.BBDD.BDMovimientos;
import com.example.cartera_v1.R;

import java.util.ArrayList;

public class EleccionAnio extends AppCompatDialogFragment {
    ListView anios;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_eleccion_anio, null);
        BDMovimientos bdm = new BDMovimientos(getContext());
        ArrayList<String> listaAnios = bdm.getAniosConTransacciones();



        anios = view.findViewById(R.id.lv_dialog_anio);
        anios.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, listaAnios));
        anios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((EstadisticasCartera)getContext()).aplicarEleccionAnio(listaAnios.get(position));
            }
        });
        builder.setView(view);
        return builder.create();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setCanceledOnTouchOutside(true);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
