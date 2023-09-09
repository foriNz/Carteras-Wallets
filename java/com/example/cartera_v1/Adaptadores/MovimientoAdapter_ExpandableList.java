package com.example.cartera_v1.Adaptadores;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.cartera_v1.BBDD.BDCategorias;
import com.example.cartera_v1.Entidades.Movimiento;
import com.example.cartera_v1.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MovimientoAdapter_ExpandableList extends BaseExpandableListAdapter {

    private HashMap<String, List<Movimiento>> listaExpandibleDatos;
    private List<String> listaExpandibleFechas;
    Context context;

    public MovimientoAdapter_ExpandableList(ArrayList<Movimiento> listaMovimientos, Context context) {
        listaExpandibleDatos = getDatos(listaMovimientos);
        listaExpandibleFechas = new ArrayList<>(listaExpandibleDatos.keySet());
        this.context = context;
    }

    private HashMap<String, List<Movimiento>> getDatos(ArrayList<Movimiento> lista) {
        HashMap<String, List<Movimiento>> resultado = new HashMap<>();
        ArrayList<Movimiento> movimientos = new ArrayList<>();
        int anio = 0, mes = 0, dia = 0;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getAnio() != anio && lista.get(i).getMes() != mes && lista.get(i).getDia() != dia) {
                if (i != 0) {
                    resultado.put((lista.get(i).getDia() + "-" + lista.get(i).getMes() + "-" + lista.get(i).getAnio()), movimientos);
                    movimientos = new ArrayList<>();
                    movimientos.add(lista.get(i));
                    anio = lista.get(i).getAnio();
                    mes = lista.get(i).getMes();
                    dia = lista.get(i).getDia();
                }
            } else {
                movimientos.add(lista.get(i));
                anio = lista.get(i).getAnio();
                mes = lista.get(i).getMes();
                dia = lista.get(i).getDia();
            }
        }
        return resultado;
    }


    @Override
    public int getGroupCount() {
        return this.listaExpandibleFechas.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listaExpandibleDatos.get(this.listaExpandibleFechas.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listaExpandibleFechas.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listaExpandibleDatos.get(this.listaExpandibleFechas.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(groupPosition);
        LayoutInflater inflater;
        if (convertView == null) {
            inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_group_lista_fecha_movimiento_cronologa, null);
        }
        TextView tv_item_fecha_cronologia = convertView.findViewById(R.id.tv_item_fecha_cronologia);
        tv_item_fecha_cronologia.setText(listTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Movimiento expandedList = (Movimiento) getChild(groupPosition, childPosition);
        LayoutInflater inflater;
        if (convertView == null) {
            inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_child_lista_movimiento_cronologia, null);
        }
        BDCategorias bdCategorias = new BDCategorias(context);
        TextView categoria, cartera, balance;
        CircleImageView icono = convertView.findViewById(R.id.civ_icono_item_cronologia);
        categoria = convertView.findViewById(R.id.tv_categoria_item_cronologia);
        cartera = convertView.findViewById(R.id.tv_cartera_item_cronologia);
        balance = convertView.findViewById(R.id.tv_balance_item_cronologia);
        icono.setImageResource(bdCategorias.getIcono(expandedList.getCategoria()));
        categoria.setText(expandedList.getCategoria());
        cartera.setText(expandedList.getNombre_cartera());
        balance.setText(String.valueOf(expandedList.getTransaccion()));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
