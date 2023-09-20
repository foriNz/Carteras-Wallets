package com.example.cartera_v1.BBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.cartera_v1.Entidades.Movimiento;
import com.example.cartera_v1.Entidades.Recordatorio;

import java.util.ArrayList;

public class BDRecordatorio extends BBDDHelper{
    Context context;
    public BDRecordatorio(@Nullable Context context) {
        super(context);
        this.context = context;
    }
    public long agregarRecordatorio(Recordatorio r) {
        BBDDHelper bbddHelper = new BBDDHelper(context);
        SQLiteDatabase bd = bbddHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("titulo", r.getTitulo());
        values.put("fecha", r.getFecha());
        values.put("descripcion", r.getDescripcion());
        values.put("repeticion", r.getRepeticion());
        values.put("intervalo", r.getIntervalo());

        return bd.insert(TABLA_RECORDATORIOS, null, values);
    }

    public ArrayList<Recordatorio> getRecordatorios() {
        BBDDHelper bbddHelper = new BBDDHelper(context);
        SQLiteDatabase bd = bbddHelper.getReadableDatabase();
        ArrayList<Recordatorio> listaRecordatorios = new ArrayList<>();
        Recordatorio r = new Recordatorio();
        Cursor cursor;
        cursor = bd.rawQuery("SELECT * FROM " + TABLA_RECORDATORIOS + " ORDER BY id ASC", null);
        if (cursor.moveToFirst()) {
            do {
                r = new Recordatorio();
                r.setId(cursor.getInt(0));
                r.setTitulo(cursor.getString(1));
                r.setDescripcion(cursor.getString(2));
                r.setFecha(cursor.getString(3));
                r.setRepeticion(cursor.getInt(4));
                r.setIntervalo(cursor.getLong(5));
                listaRecordatorios.add(r);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listaRecordatorios;
    }
    public long actualizarRecordatorio(Recordatorio r) {
        BBDDHelper bbddHelper = new BBDDHelper(context);
        SQLiteDatabase bd = bbddHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("titulo", r.getTitulo());
        values.put("fecha", r.getFecha());
        values.put("descripcion", r.getDescripcion());
        values.put("repeticion", r.getRepeticion());
        values.put("intervalo", r.getIntervalo());

        long resultado = bd.update(TABLA_RECORDATORIOS, values,
                "id = "+ values.get("id"),null);
        bd.close();
        return resultado;
    }
    public void eliminarRecordatorio(long id) {
        BBDDHelper bbddHelper = new BBDDHelper(context);
        SQLiteDatabase bd = bbddHelper.getWritableDatabase();
        long resultado = bd.delete(TABLA_RECORDATORIOS,
                "id = "+ id,null);
        bd.close();
    }

    public int getRecordatoriosId(Recordatorio r) {
        BBDDHelper bbddHelper = new BBDDHelper(context);
        SQLiteDatabase bd = bbddHelper.getReadableDatabase();
        Cursor c = bd.rawQuery("SELECT * FROM "+ TABLA_RECORDATORIOS+ " WHERE titulo = \'"+ r.getTitulo()+"\' AND descripcion = \'" + r.getDescripcion() +"\'",null );
        if (c.moveToFirst())
            return c.getInt(0);
        else return 0;
    }
}
