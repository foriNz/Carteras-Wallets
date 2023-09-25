package com.example.cartera_v1.BBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.cartera_v1.Entidades.Model_Data_Categoria;
import com.example.cartera_v1.Entidades.Model_Data_MovimientoPorAnio;
import com.example.cartera_v1.Entidades.Model_Data_MovimientoPorMes;
import com.example.cartera_v1.Entidades.Model_Fecha_Movimientos;
import com.example.cartera_v1.Entidades.Movimiento;
import com.example.cartera_v1.R;

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
        bd.close();
        return listaMovimientos;
    }

    public ArrayList<Movimiento> getMovimientos(String cartera) {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getWritableDatabase();
        ArrayList<Movimiento> listaMovimientos = new ArrayList<>();
        Movimiento m = new Movimiento();
        Cursor cursor;
        cursor = bd.rawQuery("SELECT * FROM " + TABLA_MOVIMIENTOS + " WHERE nombre_cartera = \'" + cartera + "\' ORDER BY anio DESC, mes DESC, dia DESC", null);
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
        bd.close();
        return listaMovimientos;
    }

    public ArrayList<Movimiento> getMovimientos(int anio) {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getWritableDatabase();
        ArrayList<Movimiento> listaMovimientos = new ArrayList<>();
        Movimiento m = new Movimiento();
        Cursor cursor;
        cursor = bd.rawQuery("SELECT * FROM " + TABLA_MOVIMIENTOS + " WHERE anio = " + anio + " ORDER BY anio DESC, mes DESC, dia DESC", null);
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
        bd.close();
        return listaMovimientos;
    }

    public ArrayList<Movimiento> getMovimientos(int anio, int mes) {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getWritableDatabase();
        ArrayList<Movimiento> listaMovimientos = new ArrayList<>();
        Movimiento m = new Movimiento();
        Cursor cursor;
        cursor = bd.rawQuery("SELECT * FROM " + TABLA_MOVIMIENTOS + " WHERE anio = " + anio + " AND mes = " + mes + " ORDER BY anio DESC, mes DESC, dia DESC", null);
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
        bd.close();
        return listaMovimientos;
    }

    public ArrayList<Movimiento> getMovimientos(int anio, String cartera) {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getWritableDatabase();
        ArrayList<Movimiento> listaMovimientos = new ArrayList<>();
        Movimiento m = new Movimiento();
        Cursor cursor;
        cursor = bd.rawQuery("SELECT * FROM " + TABLA_MOVIMIENTOS + " WHERE anio = " + anio + " AND nombre_cartera = \'" + cartera + "\' ORDER BY anio DESC, mes DESC, dia DESC", null);
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
        bd.close();
        return listaMovimientos;
    }

    public ArrayList<Movimiento> getMovimientos(int anio, int mes, String cartera) {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getWritableDatabase();
        ArrayList<Movimiento> listaMovimientos = new ArrayList<>();
        Movimiento m = new Movimiento();
        Cursor cursor;
        cursor = bd.rawQuery("SELECT * FROM " + TABLA_MOVIMIENTOS + " WHERE anio = " + anio + " AND mes = " + mes + " AND nombre_cartera = \'" + cartera + "\' ORDER BY anio DESC, mes DESC, dia DESC", null);
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
        bd.close();
        return listaMovimientos;
    }


    private ArrayList<Movimiento> getMovimientosDelMes(int mes, int anio) {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getWritableDatabase();
        ArrayList<Movimiento> listaMovimientos = new ArrayList<>();
        Movimiento m = new Movimiento();
        Cursor cursor;
        cursor = bd.rawQuery("SELECT * FROM " + TABLA_MOVIMIENTOS + " WHERE mes = " + mes + " AND anio = " + anio + " ORDER BY anio DESC, mes DESC, dia DESC", null);
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
        bd.close();
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
        bd.close();
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

    public ArrayList<Model_Data_MovimientoPorAnio> getMovimientosAnio() {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getWritableDatabase();
        ArrayList<Model_Data_MovimientoPorAnio> listaAnio = new ArrayList<>();
        Model_Data_MovimientoPorAnio m;
        Cursor cursor;
        cursor = bd.rawQuery("SELECT * FROM " + TABLA_MOVIMIENTOS + " ORDER BY anio DESC, mes DESC, dia DESC", null);
        if (cursor.moveToFirst()) {
            int i = 0;
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
                if (listaAnio.size() == 0) {
                    m = new Model_Data_MovimientoPorAnio();
                    m.setAnio(cursor.getInt(2));
                    listaAnio.add(m);
                    listaAnio.get(0).addMovimiento(mov);
                } else if (mov.getAnio() == listaAnio.get(i).getAnio()){
                    listaAnio.get(i).addMovimiento(mov);
                } else {
                    m = new Model_Data_MovimientoPorAnio();
                    m.setAnio(cursor.getInt(2));
                    listaAnio.add(m);
                    listaAnio.get(++i).addMovimiento(mov);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listaAnio;
    }
    public ArrayList<Model_Data_MovimientoPorAnio> getMovimientosAnio(String cartera) {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getWritableDatabase();
        ArrayList<Model_Data_MovimientoPorAnio> listaAnio = new ArrayList<>();
        Model_Data_MovimientoPorAnio m;
        Cursor cursor;
        cursor = bd.rawQuery("SELECT * FROM " + TABLA_MOVIMIENTOS + " where nombre_cartera = \'"+cartera+"\' ORDER BY anio DESC, mes DESC, dia DESC", null);
        if (cursor.moveToFirst()) {
            int i = 0;
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
                if (listaAnio.size() == 0) {
                    m = new Model_Data_MovimientoPorAnio();
                    m.setAnio(cursor.getInt(2));
                    listaAnio.add(m);
                    listaAnio.get(0).addMovimiento(mov);
                } else if (mov.getAnio() == listaAnio.get(i).getAnio()){
                    listaAnio.get(i).addMovimiento(mov);
                } else {
                    m = new Model_Data_MovimientoPorAnio();
                    m.setAnio(cursor.getInt(2));
                    listaAnio.add(m);
                    listaAnio.get(++i).addMovimiento(mov);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listaAnio;
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

    public Model_Data_MovimientoPorMes[] getMovimientosAnio(int anio, String cartera) {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getWritableDatabase();
        Model_Data_MovimientoPorMes[] listaAnio = new Model_Data_MovimientoPorMes[12];
        Model_Data_MovimientoPorMes m;
        Cursor cursor;
        cursor = bd.rawQuery("SELECT * FROM " + TABLA_MOVIMIENTOS + " WHERE nombre_cartera = \'"+cartera+"\' and anio = \'" + anio + "\' ORDER BY mes DESC, dia DESC", null);
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
// nuevo = 2,4,5
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
    public ArrayList<Model_Fecha_Movimientos> getMovimientosPorDias(int anio) {
        ArrayList<Movimiento> listaMovimientos = getMovimientos(anio);
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
    public ArrayList<Model_Fecha_Movimientos> getMovimientosPorDias(String cartera) {
        ArrayList<Movimiento> listaMovimientos = getMovimientos(cartera);
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

    public ArrayList<Model_Fecha_Movimientos> getMovimientosPorDias(int mes, int anio) {
        ArrayList<Movimiento> listaMovimientos = getMovimientosDelMes(mes, anio);
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
    public ArrayList<Model_Fecha_Movimientos> getMovimientosPorDias(int anio, String cartera) {
        ArrayList<Movimiento> listaMovimientos = getMovimientos(anio, cartera);
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
    public ArrayList<Model_Fecha_Movimientos> getMovimientosPorDias(int mes, int anio, String cartera) {
        ArrayList<Movimiento> listaMovimientos = getMovimientos(mes, anio, cartera);
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

    public ArrayList<Model_Data_Categoria> getTodoIngresosGastosPorCategorias(String gastoOIngreso) {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getReadableDatabase();
        String ingresoOGasto = ">";
        if (gastoOIngreso.equals("Gasto"))
            ingresoOGasto = "<";
        ArrayList<Model_Data_Categoria> resultado = new ArrayList<>();
        Cursor cursor = bd.rawQuery("" +
                "select categoria, icono, color, sum(transaccion), count(categoria) " +
                "from t_movimientos " +
                "inner join t_categorias on t_movimientos.categoria = t_categorias.nombre " +
                "where transaccion " + ingresoOGasto + " 0 " +
                "group by categoria", null);
        if (cursor.moveToFirst())
            do {
                Model_Data_Categoria m = new Model_Data_Categoria();
                m.setNombre(cursor.getString(0));
                m.setIcono(cursor.getInt(1));
                m.setColor(cursor.getString(2));
                m.setBalance(cursor.getDouble(3));
                m.setUsanzas(cursor.getInt(4));
                resultado.add(m);
            } while (cursor.moveToNext());

        cursor.close();
        return resultado;
    }

    public ArrayList<Model_Data_Categoria> getTodoIngresosGastosPorCategorias(String gastoOIngreso, String cartera) {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getReadableDatabase();
        String ingresoOGasto = ">";
        if (gastoOIngreso.equals("Gasto"))
            ingresoOGasto = "<";
        ArrayList<Model_Data_Categoria> resultado = new ArrayList<>();
        Cursor cursor = bd.rawQuery("" +
                "select categoria, icono, color, sum(transaccion), count(categoria) " +
                "from t_movimientos " +
                "inner join t_categorias on t_movimientos.categoria = t_categorias.nombre " +
                "where transaccion " + ingresoOGasto + " 0 AND nombre_cartera = \'" + cartera + "\'" +
                "group by categoria", null);
        if (cursor.moveToFirst())
            do {
                Model_Data_Categoria m = new Model_Data_Categoria();
                m.setNombre(cursor.getString(0));
                m.setIcono(cursor.getInt(1));
                m.setColor(cursor.getString(2));
                m.setBalance(cursor.getDouble(3));
                m.setUsanzas(cursor.getInt(4));
                resultado.add(m);
            } while (cursor.moveToNext());

        cursor.close();
        return resultado;
    }

    public ArrayList<Model_Data_Categoria> getIngresosGastosPorCategorias(int anio, String gastoOIngreso) {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getReadableDatabase();
        String ingresoOGasto = ">";
        if (gastoOIngreso.equals("Gasto"))
            ingresoOGasto = "<";
        ArrayList<Model_Data_Categoria> resultado = new ArrayList<>();
        Cursor cursor = bd.rawQuery("" +
                "select categoria, icono, color, sum(transaccion), count(categoria) " +
                "from t_movimientos " +
                "inner join t_categorias on t_movimientos.categoria = t_categorias.nombre " +
                "where anio = " + anio + " AND transaccion " + ingresoOGasto + " 0 " +
                "group by categoria", null);
        if (cursor.moveToFirst())
            do {
                Model_Data_Categoria m = new Model_Data_Categoria();
                m.setNombre(cursor.getString(0));
                m.setIcono(cursor.getInt(1));
                m.setColor(cursor.getString(2));
                m.setBalance(cursor.getDouble(3));
                m.setUsanzas(cursor.getInt(4));
                resultado.add(m);
            } while (cursor.moveToNext());

        cursor.close();
        return resultado;
    }

    public ArrayList<Model_Data_Categoria> getIngresosGastosPorCategorias(int anio, int mes, String gastoOIngreso) {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getReadableDatabase();
        String ingresoOGasto = ">";
        if (gastoOIngreso.equals("Gasto"))
            ingresoOGasto = "<";
        ArrayList<Model_Data_Categoria> resultado = new ArrayList<>();
        Cursor cursor = bd.rawQuery("" +
                "select categoria, icono, color, sum(transaccion), count(categoria) " +
                "from t_movimientos " +
                "inner join t_categorias on t_movimientos.categoria = t_categorias.nombre " +
                "where anio = " + anio + " AND mes = " + mes + " AND transaccion " + ingresoOGasto + " 0 " +
                "group by categoria", null);
        if (cursor.moveToFirst())
            do {
                Model_Data_Categoria m = new Model_Data_Categoria();
                m.setNombre(cursor.getString(0));
                m.setIcono(cursor.getInt(1));
                m.setColor(cursor.getString(2));
                m.setBalance(cursor.getDouble(3));
                m.setUsanzas(cursor.getInt(4));
                resultado.add(m);
            } while (cursor.moveToNext());

        cursor.close();
        return resultado;
    }

    public ArrayList<Model_Data_Categoria> getIngresosGastosPorCategorias(int anio, String ingresoGasto, String cartera) {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getReadableDatabase();
        String ingresoOGasto = ">";
        if (ingresoGasto.equals("Gasto"))
            ingresoOGasto = "<";
        ArrayList<Model_Data_Categoria> resultado = new ArrayList<>();
        Cursor cursor = bd.rawQuery("" +
                "select categoria, icono, color, sum(transaccion), count(categoria) " +
                "from t_movimientos " +
                "inner join t_categorias on t_movimientos.categoria = t_categorias.nombre " +
                "where anio = " + anio + " AND transaccion " + ingresoOGasto + " 0 " + "AND nombre_cartera = \'" + cartera + "\' " +
                "group by categoria", null);
        if (cursor.moveToFirst())
            do {
                Model_Data_Categoria m = new Model_Data_Categoria();
                m.setNombre(cursor.getString(0));
                m.setIcono(cursor.getInt(1));
                m.setColor(cursor.getString(2));
                m.setBalance(cursor.getDouble(3));
                m.setUsanzas(cursor.getInt(4));
                resultado.add(m);
            } while (cursor.moveToNext());

        cursor.close();
        return resultado;
    }

    public ArrayList<Model_Data_Categoria> getIngresosGastosPorCategorias(int anio, int mes, String ingresoGasto, String cartera) {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getReadableDatabase();
        String ingresoOGasto = ">";
        if (ingresoGasto.equals("Gasto"))
            ingresoOGasto = "<";
        ArrayList<Model_Data_Categoria> resultado = new ArrayList<>();
        Cursor cursor = bd.rawQuery("" +
                "select categoria, icono, color, sum(transaccion), count(categoria) " +
                "from t_movimientos " +
                "inner join t_categorias on t_movimientos.categoria = t_categorias.nombre " +
                "where anio = " + anio + " mes = " + mes + " AND transaccion " + ingresoOGasto + " 0 " + "AND nombre_cartera = \'" + cartera + "\' " +
                "group by categoria", null);
        if (cursor.moveToFirst())
            do {
                Model_Data_Categoria m = new Model_Data_Categoria();
                m.setNombre(cursor.getString(0));
                m.setIcono(cursor.getInt(1));
                m.setColor(cursor.getString(2));
                m.setBalance(cursor.getDouble(3));
                m.setUsanzas(cursor.getInt(4));
                resultado.add(m);
            } while (cursor.moveToNext());

        cursor.close();
        return resultado;
    }

    public int getIntDelMes(String mes) {
        int resultado = 12;
        if (mes.equals(contexto.getResources().getString(R.string.enero)))
            resultado = 0;
        else if (mes.equals(contexto.getResources().getString(R.string.febrero)))
            resultado = 1;
        else if (mes.equals(contexto.getResources().getString(R.string.marzo)))
            resultado = 2;
        else if (mes.equals(contexto.getResources().getString(R.string.abril)))
            resultado = 3;
        else if (mes.equals(contexto.getResources().getString(R.string.mayo)))
            resultado = 4;
        else if (mes.equals(contexto.getResources().getString(R.string.junio)))
            resultado = 5;
        else if (mes.equals(contexto.getResources().getString(R.string.julio)))
            resultado = 6;
        else if (mes.equals(contexto.getResources().getString(R.string.agosto)))
            resultado = 7;
        else if (mes.equals(contexto.getResources().getString(R.string.septiembre)))
            resultado = 8;
        else if (mes.equals(contexto.getResources().getString(R.string.octubre)))
            resultado = 9;
        else if (mes.equals(contexto.getResources().getString(R.string.noviembre)))
            resultado = 10;
        else if (mes.equals(contexto.getResources().getString(R.string.diciembre)))
            resultado = 11;
        return resultado;
    }


    public String getMesDelInt(int mes) {
        String resultado = "";
        switch (mes) {
            case 0:
                resultado = contexto.getResources().getString(R.string.enero);
                break;
            case 1:
                resultado = contexto.getResources().getString(R.string.febrero);
                break;
            case 2:
                resultado = contexto.getResources().getString(R.string.marzo);
                break;
            case 3:
                resultado = contexto.getResources().getString(R.string.abril);
                break;
            case 4:
                resultado = contexto.getResources().getString(R.string.mayo);
                break;
            case 5:
                resultado = contexto.getResources().getString(R.string.junio);
                break;
            case 6:
                resultado = contexto.getResources().getString(R.string.julio);
                break;
            case 7:
                resultado = contexto.getResources().getString(R.string.agosto);
                break;
            case 8:
                resultado = contexto.getResources().getString(R.string.septiembre);
                break;
            case 9:
                resultado = contexto.getResources().getString(R.string.octubre);
                break;
            case 10:
                resultado = contexto.getResources().getString(R.string.noviembre);
                break;
            case 11:
                resultado = contexto.getResources().getString(R.string.diciembre);
                break;
        }
        return resultado;
    }

    public ArrayList<String> getAniosConTransacciones() {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getReadableDatabase();
        ArrayList<String> listaAnios = new ArrayList<>();
        Cursor cursor = bd.rawQuery("select DISTINCT anio from t_movimientos order by anio ASC", null);
        if (cursor.moveToFirst())
            do {
                String s = cursor.getString(0);
                listaAnios.add(s);
            } while (cursor.moveToNext());

        cursor.close();
        return listaAnios;
    }

    public Integer[] getTodosUsosEnCadaMes() {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getReadableDatabase();
        Integer[] lista = new Integer[12];
        Cursor cursor = bd.rawQuery("select mes, count(mes) from t_movimientos order by mes ASC", null);
        if (cursor.moveToFirst())
            do {
                lista[cursor.getInt(0)] = cursor.getInt(1);
            } while (cursor.moveToNext());

        cursor.close();
        return lista;
    }

    public Integer[] getTodosUsosEnCadaMes(String nombre_cartera) {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getReadableDatabase();
        Integer[] lista = new Integer[12];
        Cursor cursor = bd.rawQuery("select mes, count(mes) from t_movimientos where nombre_cartera = \'" + nombre_cartera + "\' order by mes ASC", null);
        if (cursor.moveToFirst())
            do {
                lista[cursor.getInt(0)] = cursor.getInt(1);
            } while (cursor.moveToNext());

        cursor.close();
        return lista;
    }

    public Integer[] getUsosEnCadaMes(int anio) {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getReadableDatabase();
        Integer[] lista = new Integer[12];
        Cursor cursor = bd.rawQuery("select mes, count(mes) from t_movimientos where anio = " + anio + " order by mes ASC", null);
        if (cursor.moveToFirst())
            do {
                lista[cursor.getInt(0)] = cursor.getInt(1);
            } while (cursor.moveToNext());

        cursor.close();
        return lista;
    }

    public Integer[] getUsosEnCadaMes(int anio, String nombre_cartera) {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getReadableDatabase();
        Integer[] lista = new Integer[12];
        Cursor cursor = bd.rawQuery("select mes, count(mes) from t_movimientos where nombre_cartera = \'" + nombre_cartera + "\' and anio = " + anio + " order by mes ASC", null);
        if (cursor.moveToFirst())
            do {
                lista[cursor.getInt(0)] = cursor.getInt(1);
            } while (cursor.moveToNext());

        cursor.close();
        return lista;
    }
}
