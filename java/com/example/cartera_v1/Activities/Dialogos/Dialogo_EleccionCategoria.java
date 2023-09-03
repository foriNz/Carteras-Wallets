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
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartera_v1.Adaptadores.ListaCarteraAdapter_Transaccion;
import com.example.cartera_v1.BBDD.BDCarteras;
import com.example.cartera_v1.R;

public class Dialogo_EleccionCategoria extends AppCompatDialogFragment {
    public Window window;

    TableLayout tableLayout;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogo_eleccion_categoria, null);
        tableLayout = view.findViewById(R.id.tl_dialogo_eleccion_categoria);
        //refrescarCategorias();
        builder.setView(view);
        return builder.create();
    }

    // TODO: 03/09/2023 hacer cardview
   /* private void refrescarCategorias(int ingresoGastoTrans) {
        tableLayout.removeAllViews();
        BDCategorias bdCategorias = new BDCategorias(getApplicationContext());
        ArrayList<Categoria> categorias = bdCategorias.getCategorias(ingresoGastoTrans);


        TableRow tr = null;
        for (int i = 0; i < categorias.size(); i++) {
            if (i % 4 == 0) {
                tr = new TableRow(this);
                tableLayout.addView(tr);
            }
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View view  = inflater.inflate(R.layout.view_cardview_categoria);
            view.findViewById(R.id.cv)
            view.setWidth(275);
            if (tr != null)
                tr.addView(view);

        }
    }*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
