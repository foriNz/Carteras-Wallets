package com.example.cartera_v1.Activities.Dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartera_v1.Adaptadores.ListaCarteraAdapter_Transaccion;
import com.example.cartera_v1.BBDD.BDCarteras;
import com.example.cartera_v1.BBDD.BDCategorias;
import com.example.cartera_v1.Entidades.Categoria;
import com.example.cartera_v1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Dialogo_EleccionCategoria extends AppCompatDialogFragment {
    public Window window;

    TableLayout tableLayout;
    FloatingActionButton floatingButton;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogo_eleccion_categoria, null);
        tableLayout = view.findViewById(R.id.tl_dialogo_eleccion_categoria);
        floatingButton = view.findViewById(R.id.fb_eleccion_categoria);
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AdministradorCategorias.class);
                startActivity(intent);
            }
        });
        refrescarCategorias(0);
        builder.setView(view);
        return builder.create();
    }

    // TODO: 03/09/2023 radiobutton de gasto e ingreso
    private void refrescarCategorias(int ingresoGasto) {
        tableLayout.removeAllViews();
        BDCategorias bdCategorias = new BDCategorias(getContext());
        ArrayList<Categoria> categorias;
        if (ingresoGasto == 0)
            categorias = bdCategorias.getCategoriasGasto();
        else
            categorias = bdCategorias.getCategoriasIngreso();


        TableRow tr = null;
        for (int i = 0; i < categorias.size(); i++) {
            if (i % 4 == 0) {
                tr = new TableRow(getContext());
                tableLayout.addView(tr);
            }
            LayoutInflater inflater = getActivity().getLayoutInflater();
            ImageView imageView;
            TextView textView;
            View view = inflater.inflate(R.layout.view_cardview_categoria, null);
            imageView = view.findViewById(R.id.iv_cardview_icono_categoria);
            textView = view.findViewById(R.id.tv_cardview_nombre_categoria);
            imageView.setBackgroundResource(getResources().getIdentifier(categorias.get(i).getIcono(),
                    "drawable", getActivity().getPackageName()));
            imageView.setColorFilter(Color.parseColor(categorias.get(i).getColor()));
            textView.setText(categorias.get(i).getNombre());
            if (tr != null)
                tr.addView(view);

        }
    }

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
