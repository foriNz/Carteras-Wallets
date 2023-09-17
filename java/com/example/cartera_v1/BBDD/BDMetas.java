package com.example.cartera_v1.BBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.cartera_v1.Entidades.Meta;
import com.example.cartera_v1.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BDMetas extends BBDDHelper {
    Context context;

    public BDMetas(@Nullable Context context) {
        super(context);
        this.context = context;
    }
    public long addMeta(Meta m) {
        long id = 0;
        try {
            BBDDHelper bbddHelper = new BBDDHelper(context);
            SQLiteDatabase bd = bbddHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put("caracter", m.getCaracter());
            values.put("categoria", m.getCategoria());
            values.put("tipo_categoria", m.getTipo_categoria());
            values.put("objetivo", m.getValor_objetivo());
            id = bd.insert(TABLA_METAS, null, values);
        } catch (Exception e) {
            e.toString();
        }
        return id;
    }
    private ArrayList<Meta> getMetas() {
        BBDDHelper bbddHelper = new BBDDHelper(context);
        SQLiteDatabase bd = bbddHelper.getWritableDatabase();
        ArrayList<Meta> listaMetas = new ArrayList<>();
        Meta meta;
        Cursor cursorMeta;

        cursorMeta = bd.rawQuery("SELECT * FROM " + TABLA_METAS + " ORDER BY caracter", null);
        if (cursorMeta.moveToFirst()) {
            do {
                meta = new Meta();
                if (cursorMeta.getString(0).equals(context.getResources().getString(R.string.ahorro))) {
                    meta.setCaracter(cursorMeta.getString(0));
                    meta.setValor_objetivo(cursorMeta.getInt(3));
                    listaMetas.add(meta);
                } else if (cursorMeta.getString(0).equals("General")) {
                    meta.setCaracter(cursorMeta.getString(0));
                    meta.setTipo_categoria(cursorMeta.getString(2));
                    meta.setValor_objetivo(cursorMeta.getInt(3));
                    listaMetas.add(meta);
                } else {
                    meta.setCaracter(cursorMeta.getString(0));
                    meta.setCategoria(cursorMeta.getString(1));
                    meta.setTipo_categoria(cursorMeta.getString(2));
                    meta.setValor_objetivo(cursorMeta.getInt(3));
                    listaMetas.add(meta);
                }
            } while (cursorMeta.moveToNext());
        }
        cursorMeta.close();
        return listaMetas;
    }

    public ArrayList<Meta> getMetas(int anio, int mes) {
        ArrayList<Meta> listaMetas = getMetas();
        BDMovimientos bdMovimientos = new BDMovimientos(context);
        for (int i = 0; i < listaMetas.size(); i++) {
            if (listaMetas.get(i).getCaracter().equals("Ahorro")) {
                // Si es ahorro, bdmovimientos de un mes y saco el balance total
                listaMetas.get(i).setValor_actual(bdMovimientos.getMovimientosMes(anio, mes).getBalance_total());
            } else if (listaMetas.get(i).getCaracter().equals("General")) {
                // Si es de caracter general, miro de que tipo y bdmovimientos del mes e igual
                listaMetas.get(i).setValor_actual(bdMovimientos.getMovimientosMes(anio, mes,
                        listaMetas.get(i).getTipo_categoria()).getBalance_total());
            } else {
                // Si es de caracter especifico, miro el tipo y categoria y bdmovimientos
                listaMetas.get(i).setValor_actual(bdMovimientos.getMovimientosMes(anio, mes,
                        listaMetas.get(i).getCategoria(), listaMetas.get(i).getTipo_categoria()).getBalance_total());

            }
        }


        return listaMetas;
    }
}
