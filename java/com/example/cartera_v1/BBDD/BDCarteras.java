package com.example.cartera_v1.BBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.cartera_v1.Entidades.Cartera;

import java.util.ArrayList;
import java.util.Objects;

public class BDCarteras extends BBDDHelper{
    Context contexto;

    public BDCarteras(@Nullable Context context) {
        super(context);
        contexto = context;
    }

    public long addCartera(String nombre, double balance) {
        long id = 0;
        try {
            BBDDHelper bbddHelper = new BBDDHelper(contexto);
            SQLiteDatabase bd = bbddHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("balance", balance);
            id = bd.insert(TABLA_CARTERAS, null, values);
        } catch (Exception e) {
            e.toString();
        }
        return id;
    }

    public ArrayList<Cartera> getCarteras() {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getWritableDatabase();
        ArrayList<Cartera> listaCarteras = new ArrayList<>();
        Cartera cartera = null;
        Cursor cursorCartera = null;

        cursorCartera = bd.rawQuery("SELECT * FROM " + TABLA_CARTERAS, null);
        if (cursorCartera.moveToFirst()) {
            do {
                cartera = new Cartera(cursorCartera.getString(0), cursorCartera.getDouble(1));
                listaCarteras.add(cartera);
            } while (cursorCartera.moveToNext());
        }
        cursorCartera.close();
        return listaCarteras;
    }

    public long modificarBalance(String cartera, double transaccion) {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getWritableDatabase();
        long id = 0;
        ArrayList<Cartera> carteras = getCarteras();
        for (int i = 0; i < carteras.size(); i++) {
            if (Objects.equals(cartera, carteras.get(i).getNombre())) {
                ContentValues cv = new ContentValues();
                cv.put("balance", carteras.get(i).getBalance() + transaccion);
                id = bd.update(TABLA_CARTERAS, cv, "nombre = \'" + cartera + "\'", null);
                break;
            }
        }
        bd.close();
        return id;
    }

    public double getBalanceTotal() {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getReadableDatabase();
        Cursor c;
        int resultado = 0;
        c = bd.rawQuery("SELECT sum(balance) from " + TABLA_CARTERAS,null);
        if (c.moveToFirst())
            resultado = c.getInt(0);
        return resultado;
    }

    public void modificarNombre(String nombre_nuevo, String nombre_antiguo) {
        try {
            // TODO: 27/09/2023 update de movimientos
            BBDDHelper bbddHelper = new BBDDHelper(contexto);
            SQLiteDatabase bd = bbddHelper.getWritableDatabase();
            bd.execSQL("UPDATE " + TABLA_CARTERAS + " SET nombre = \'" + nombre_nuevo + "\' WHERE nombre = \'" + nombre_antiguo + "\'");
            bd.close();
        } catch (Exception e) {
            e.toString();
        }
    }
}
