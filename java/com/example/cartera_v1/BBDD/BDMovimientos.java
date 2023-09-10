package com.example.cartera_v1.BBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.cartera_v1.Entidades.Movimiento;

import java.util.ArrayList;

public class BDMovimientos extends BBDDHelper{
    Context contexto;
    public BDMovimientos(@Nullable Context context) {
        super(context);
        contexto = context;
    }

    public ArrayList<Movimiento> getMovimientos() {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getWritableDatabase();
        ArrayList<Movimiento> listaMovimientos = new ArrayList<>();
        Movimiento m = new Movimiento();
        Cursor cursor;
        cursor = bd.rawQuery("SELECT * FROM "+ TABLA_MOVIMIENTOS + " ORDER BY dia, mes, anio DESC", null);
        if (cursor.moveToFirst()) {
            do {
                m.setId(cursor.getInt(0));
                m.setNombre_cartera(cursor.getString(1));
                m.setAnio(cursor.getInt(2));
                m.setMes(cursor.getInt(3));
                m.setDia(cursor.getInt(4));
                m.setTransaccion(cursor.getDouble(5));
                m.setCategoria(cursor.getString(6));
                m.setNota(cursor.getString(7));
                listaMovimientos.add(m);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listaMovimientos;
    }
    public int getId() {
        return getMovimientos().size();
    }

    public long addMovimiento(Movimiento m) {
        long id = 0;
        try {
            BBDDHelper bbddHelper = new BBDDHelper(contexto);
            SQLiteDatabase bd = bbddHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put("id", m.getId());
            values.put("nombre_cartera", m.getNombre_cartera());
            values.put("anio", m.getAnio());
            values.put("mes", m.getMes());
            values.put("dia", m.getDia());
            values.put("transaccion", m.getTransaccion());
            values.put("categoria", m.getCategoria());
            values.put("nota", m.getNota());

            id = bd.insert(TABLA_MOVIMIENTOS, null, values);
        } catch (Exception e) {
            e.toString();
        }
        return id;
    }
}
