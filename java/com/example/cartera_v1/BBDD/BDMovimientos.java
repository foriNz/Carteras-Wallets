package com.example.cartera_v1.BBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.cartera_v1.Entidades.Model_Data_MovimientoPorMes;
import com.example.cartera_v1.Entidades.Model_Fecha_Movimientos;
import com.example.cartera_v1.Entidades.Movimiento;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BDMovimientos extends BBDDHelper {
    Context contexto;

    public BDMovimientos(@Nullable Context context) {
        super(context);
        contexto = context;
    }

    public int getBalanceTotalDia(int dia, int mes, int anio) {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getWritableDatabase();
        int resultado;
        Cursor cursor;
        cursor = bd.rawQuery("SELECT SUMA, SUM(transaccion) FROM " + TABLA_MOVIMIENTOS + " WHERE dia = \'" + dia + "\' AND mes = \'" + mes + "\' AND anio = \'" + anio + "\' DESC", null);
        resultado = cursor.getInt(1);
        cursor.close();
        return resultado;
    }

    public ArrayList<Movimiento> getMovimientos() {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getWritableDatabase();
        ArrayList<Movimiento> listaMovimientos = new ArrayList<>();
        Movimiento m = new Movimiento();
        Cursor cursor;
        cursor = bd.rawQuery("SELECT * FROM " + TABLA_MOVIMIENTOS + " ORDER BY anio DESC, mes DESC, dia DESC", null);
        if (cursor.moveToFirst()) {
            do {
                m = new Movimiento();
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

    public Model_Data_MovimientoPorMes getMovimientosMes(int anio, int mes) {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getWritableDatabase();
        Model_Data_MovimientoPorMes[] listaAnio = new Model_Data_MovimientoPorMes[12];
        Model_Data_MovimientoPorMes m = new Model_Data_MovimientoPorMes();
        Cursor cursor;
        cursor = bd.rawQuery("SELECT * FROM " + TABLA_MOVIMIENTOS + " WHERE anio = \'" + anio + "\' AND mes = \'" + mes + "\'  ORDER BY mes DESC, dia DESC", null);
        if (cursor.moveToFirst()) {
            do {
                Movimiento mov = new Movimiento();
                mov.setId(cursor.getInt(0));
                mov.setNombre_cartera(cursor.getString(1));
                mov.setAnio(cursor.getInt(2));
                mov.setMes(cursor.getInt(3));
                mov.setDia(cursor.getInt(4));
                mov.setTransaccion(cursor.getDouble(5));
                mov.setCategoria(cursor.getString(6));
                mov.setNota(cursor.getString(7));
                m.addMovimiento(mov);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return m;
    }

    public Model_Data_MovimientoPorMes getMovimientosMes(int anio, int mes, String tipo) {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getWritableDatabase();
        Model_Data_MovimientoPorMes m = new Model_Data_MovimientoPorMes();
        String d = "";
        if (tipo.equals("Gasto"))
            d = "<";
        else if (tipo.equals("Ingreso"))
            d = ">";

        Cursor cursor;
        cursor = bd.rawQuery("SELECT * FROM " + TABLA_MOVIMIENTOS + " WHERE anio = \'" + anio + "\' AND mes = \'" + mes + "\' AND transaccion " + d + " 0  ORDER BY mes DESC, dia DESC", null);
        if (cursor.moveToFirst()) {
            do {
                Movimiento mov = new Movimiento();
                mov.setId(cursor.getInt(0));
                mov.setNombre_cartera(cursor.getString(1));
                mov.setAnio(cursor.getInt(2));
                mov.setMes(cursor.getInt(3));
                mov.setDia(cursor.getInt(4));
                mov.setTransaccion(cursor.getDouble(5));
                mov.setCategoria(cursor.getString(6));
                mov.setNota(cursor.getString(7));
                m.addMovimiento(mov);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return m;
    }

    public Model_Data_MovimientoPorMes getMovimientosMes(int anio, int mes, String categoria, String tipo) {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getWritableDatabase();
        Model_Data_MovimientoPorMes m = new Model_Data_MovimientoPorMes();
        String d = "";
        if (tipo.equals("Gasto"))
            d = "<";
        else if (tipo.equals("Ingreso"))
            d = ">";

        Cursor cursor;
        cursor = bd.rawQuery("SELECT * FROM " + TABLA_MOVIMIENTOS + " WHERE anio = \'" + anio +
                "\' AND mes = \'" + mes + "\' AND transaccion " + d + " 0" +
                " AND categoria = \'" + categoria + "\'  ORDER BY mes DESC, dia DESC", null);
        if (cursor.moveToFirst()) {
            do {
                Movimiento mov = new Movimiento();
                mov.setId(cursor.getInt(0));
                mov.setNombre_cartera(cursor.getString(1));
                mov.setAnio(cursor.getInt(2));
                mov.setMes(cursor.getInt(3));
                mov.setDia(cursor.getInt(4));
                mov.setTransaccion(cursor.getDouble(5));
                mov.setCategoria(cursor.getString(6));
                mov.setNota(cursor.getString(7));
                m.addMovimiento(mov);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return m;
    }

    public Model_Data_MovimientoPorMes[] getMovimientosAnio(int anio) {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getWritableDatabase();
        Model_Data_MovimientoPorMes[] listaAnio = new Model_Data_MovimientoPorMes[12];
        Model_Data_MovimientoPorMes m;
        Cursor cursor;
        cursor = bd.rawQuery("SELECT * FROM " + TABLA_MOVIMIENTOS + " WHERE anio = \'" + anio + "\' ORDER BY mes DESC, dia DESC", null);
        if (cursor.moveToFirst()) {
            do {
                Movimiento mov = new Movimiento();
                mov.setId(cursor.getInt(0));
                mov.setNombre_cartera(cursor.getString(1));
                mov.setAnio(cursor.getInt(2));
                mov.setMes(cursor.getInt(3));
                mov.setDia(cursor.getInt(4));
                mov.setTransaccion(cursor.getDouble(5));
                mov.setCategoria(cursor.getString(6));
                mov.setNota(cursor.getString(7));
                if (listaAnio[mov.getMes()] == null) {
                    m = new Model_Data_MovimientoPorMes();
                    listaAnio[mov.getMes()] = m;
                    listaAnio[mov.getMes()].addMovimiento(mov);
                } else {
                    listaAnio[mov.getMes()].addMovimiento(mov);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listaAnio;
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
            BDCarteras bdC = new BDCarteras(contexto);
            bdC.modificarBalance(m.getNombre_cartera(), m.getTransaccion());
            id = bd.insert(TABLA_MOVIMIENTOS, null, values);
        } catch (Exception e) {
            e.toString();
        }
        return id;
    }

    public boolean sonFechasIguales(Movimiento m1, Movimiento m2) {
        boolean r = false;
        if (m1.getDia() == m2.getDia())
            if (m1.getMes() == m2.getMes()) if (m1.getAnio() == m2.getAnio()) r = true;
        return r;
    }

    public ArrayList<Model_Fecha_Movimientos> getMovimientosPorDias() {
        ArrayList<Movimiento> listaMovimientos = getMovimientos();
        Model_Fecha_Movimientos mfm = new Model_Fecha_Movimientos(new ArrayList<>(), true);
        ArrayList<Model_Fecha_Movimientos> resultado = new ArrayList<>();
        if (listaMovimientos.size() != 0) {
            mfm.addMovimiento(listaMovimientos.get(0));
            mfm.setFecha();
            if (listaMovimientos.size() == 1) resultado.add(mfm);
        }
        for (int i = 1; i < listaMovimientos.size(); i++) {
            Movimiento mov = listaMovimientos.get(i);
            if (sonFechasIguales(mov, mfm.getFirstMovimiento())) {
                mfm.addMovimiento(mov);

            } else {
                resultado.add(mfm);
                mfm = new Model_Fecha_Movimientos(new ArrayList<>(), true);
                mfm.addMovimiento(listaMovimientos.get(i));
                mfm.setFecha();
            }
            if ((listaMovimientos.size() - i == 1))
                resultado.add(mfm);
        }
        return resultado;
    }
}
