package com.example.cartera_v1.Activities.Dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartera_v1.Adaptadores.CarterasAdapter_Transaccion;
import com.example.cartera_v1.BBDD.BDCarteras;
import com.example.cartera_v1.R;

public class EleccionBilletera extends AppCompatDialogFragment {
    public Window window;
    RecyclerView rv_dialogo;
    CarterasAdapter_Transaccion carterasAdapter;

    @Override
    public void onResume() {
        refrescarRecyclerView();
        super.onResume();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_eleccion_billetera,null);

        rv_dialogo = view.findViewById(R.id.rv_dialogo_eleccion_billetera);
        rv_dialogo.setLayoutManager(new LinearLayoutManager(getContext()));

        refrescarRecyclerView();
        builder.setView(view);
        return builder.create();
    }

    private void refrescarRecyclerView() {
        BDCarteras bdCarteras = new BDCarteras(getContext());
        carterasAdapter = new CarterasAdapter_Transaccion(bdCarteras.getCarteras(), getContext());
        rv_dialogo.setAdapter(carterasAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Asignamos la window, la posicion(gravity), la anchura(width), agregamos los atributo y
        // el activamos si clickamos fuera del dialogo se cierra
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
