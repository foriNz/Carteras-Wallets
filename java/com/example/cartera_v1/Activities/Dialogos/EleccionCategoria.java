package com.example.cartera_v1.Activities.Dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.cartera_v1.Activities.AdministradorCategorias;
import com.example.cartera_v1.Activities.Transaccion;
import com.example.cartera_v1.BBDD.BDCategorias;
import com.example.cartera_v1.Entidades.Categoria;
import com.example.cartera_v1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class EleccionCategoria extends AppCompatDialogFragment {
    public Window window;
    RadioButton rb_categoria_ingreso, rb_categoria_gasto, rb_categoria_transferencia;
    RadioGroup rg_categorias;
    TableLayout tableLayout;
    FloatingActionButton floatingButton;
    Context context;

    public EleccionCategoria(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_eleccion_categoria, null);
        tableLayout = view.findViewById(R.id.tl_dialogo_eleccion_categoria);
        floatingButton = view.findViewById(R.id.fb_eleccion_categoria);
        rb_categoria_ingreso = view.findViewById(R.id.rb_categoria_ingreso);
        rb_categoria_gasto = view.findViewById(R.id.rb_categoria_gasto);
        rb_categoria_transferencia = view.findViewById(R.id.rb_categoria_transferencia);
        rg_categorias = view.findViewById(R.id.rg_categorias);
        rg_categorias.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_categoria_gasto:
                        refrescarCategorias(0);
                        break;
                    case R.id.rb_categoria_ingreso:
                        refrescarCategorias(1);
                        break;
                    case R.id.rb_categoria_transferencia:
                        // TODO: 13/09/2023 hacer activity transferencia
                        break;
                }
            }
        });
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

    // 0 -> Gasto 1 -> Ingresos
    public void refrescarCategorias(int ingresoGasto) {
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
                tr.setPadding(10, 10, 10, 10);
                tr.setGravity(Gravity.CENTER);
                tableLayout.addView(tr);
            }
            LayoutInflater inflater = getActivity().getLayoutInflater();
            CircleImageView imageView;
            TextView textView;
            View view = inflater.inflate(R.layout.view_cardview_categoria, null);
            imageView = view.findViewById(R.id.iv_cardview_icono_categoria);
            textView = view.findViewById(R.id.tv_cardview_nombre_categoria);
            imageView.setImageResource(getResources().getIdentifier(categorias.get(i).getIcono(),
                    "drawable", getActivity().getPackageName()));
            // Color del icono
            imageView.setColorFilter(Color.parseColor(categorias.get(i).getColor()));
            textView.setText(categorias.get(i).getNombre());
            textView.setTextSize(12);
            imageView.setForegroundGravity(Gravity.CENTER);
            textView.setForegroundGravity(Gravity.CENTER);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Transaccion) context).aplicarEleccionCategoria(textView.getText().toString());
                }
            });
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
