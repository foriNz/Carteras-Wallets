package com.example.cartera_v1.BBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.cartera_v1.Entidades.Categoria;

import java.util.ArrayList;

public class BDCategorias extends BBDDHelper {
    Context contexto;

    public BDCategorias(@Nullable Context context) {
        super(context);
        contexto = context;
    }

    public void modificarCategoria(int id, String nombre, String color, String icono, String tipo) {
        try {
            BBDDHelper bbddHelper = new BBDDHelper(contexto);
            SQLiteDatabase bd = bbddHelper.getWritableDatabase();
            Cursor c = null;
            String d = "";
            if (tipo.equals("Gasto"))
                d = "<";
            else if (tipo.equals("Ingreso"))
                d = ">";
            c = bd.rawQuery("SELECT nombre from " + TABLA_CATEGORIAS + " where id = " + id, null);
            String nombreAntiguo = c.getString(0);
            bd.execSQL("UPDATE " + TABLA_CATEGORIAS + " SET nombre = \'" + nombre + "\', color = \'" + color + "\', icono =\'" + icono + "\' WHERE id = " + id + " and tipo = \'" + tipo + "\'");
            bd.execSQL("UPDATE " + TABLA_MOVIMIENTOS + " SET categoria = \'" + nombre + "\' where categoria = \'" + nombreAntiguo + "\'" + " transaccion " + d + " 0");
            bd.execSQL("UPDATE " + TABLA_METAS + " SET categoria = \'" + nombre + "\' where categoria = \'" + nombreAntiguo + "\'");
            c.close();
            bd.close();
        } catch (Exception e) {
            e.toString();
        }

    }

    public long addCategoria(String nombre, String color, String icono, String tipo) {
        long id = 0;
        try {
            BBDDHelper bbddHelper = new BBDDHelper(contexto);
            SQLiteDatabase bd = bbddHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            switch (tipo) {
                case "gasto":
                    values.put("id", getCategoriasGasto().size());
                    break;
                case "ingreso":
                    values.put("id", getCategoriasIngreso().size());
                    break;
                case "transferencia":
                    values.put("id", getTransferencias().size());
                    break;
                default:
                    assert false;
            }
            values.put("id", getIdSiguiente(tipo));
            values.put("nombre", nombre);
            values.put("color", color);
            values.put("icono", icono);
            values.put("tipo", tipo);
            id = bd.insert(TABLA_CATEGORIAS, null, values);
            bd.close();
        } catch (Exception e) {
            e.toString();
        }

        return id;
    }

    // Devuelve el ID siguiente (siempre lo pone el ultimo)
    public long getIdSiguiente(String tipo) {
        long resultado;
        if (tipo.equals("ingreso"))
            resultado = getCategoriasIngreso().size();
        else
            resultado = getCategoriasGasto().size();
        return resultado;

    }

    public int getId(String nombre, String tipo) {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getReadableDatabase();

        Cursor cursorCategoria = null;

        cursorCategoria = bd.rawQuery("SELECT id FROM " + TABLA_CATEGORIAS + " WHERE nombre = \'" + nombre + "\' and tipo = \'" + tipo + "\'", null);
        close();
        if (cursorCategoria.moveToFirst())
            return cursorCategoria.getInt(0);
        else return 0;
    }

    public ArrayList<Categoria> getCategoriasIngreso() {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getWritableDatabase();
        ArrayList<Categoria> listaCategorias = new ArrayList<>();
        Categoria categoria = null;
        Cursor cursorCategoria = null;

        cursorCategoria = bd.rawQuery("SELECT * FROM " + TABLA_CATEGORIAS + " WHERE tipo = \'ingreso\' ORDER BY id ASC", null);
        if (cursorCategoria.moveToFirst()) {
            do {
                categoria = new Categoria(cursorCategoria.getInt(0), cursorCategoria.getString(1), cursorCategoria.getString(2), cursorCategoria.getString(3));
                listaCategorias.add(categoria);
            } while (cursorCategoria.moveToNext());
        }
        cursorCategoria.close();
        return listaCategorias;
    }

    public ArrayList<Categoria> getCategoriasGasto() {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getWritableDatabase();
        ArrayList<Categoria> listaCategorias = new ArrayList<>();
        Categoria categoria = null;
        Cursor cursorCategoria = null;

        cursorCategoria = bd.rawQuery("SELECT * FROM " + TABLA_CATEGORIAS + " WHERE tipo = \'gasto\' ORDER BY id ASC", null);
        if (cursorCategoria.moveToFirst()) {
            do {
                categoria = new Categoria(cursorCategoria.getInt(0), cursorCategoria.getString(1), cursorCategoria.getString(2), cursorCategoria.getString(3));
                listaCategorias.add(categoria);
            } while (cursorCategoria.moveToNext());
        }
        cursorCategoria.close();
        return listaCategorias;
    }

    public ArrayList<Categoria> getTransferencias() {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getWritableDatabase();
        ArrayList<Categoria> listaCategorias = new ArrayList<>();
        Categoria categoria = null;
        Cursor cursorCategoria = null;

        cursorCategoria = bd.rawQuery("SELECT * FROM " + TABLA_CATEGORIAS + " WHERE tipo = \'transferencia\' ORDER BY id ASC", null);
        if (cursorCategoria.moveToFirst()) {
            do {
                categoria = new Categoria(cursorCategoria.getInt(0), cursorCategoria.getString(1), cursorCategoria.getString(2), cursorCategoria.getString(3));
                listaCategorias.add(categoria);
            } while (cursorCategoria.moveToNext());
        }
        cursorCategoria.close();
        return listaCategorias;
    }

    // Devuelve el icono
    public int getIcono(String nombre_categoria) {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getWritableDatabase();
        int resultado;
        Cursor cursorCategoria = null;

        cursorCategoria = bd.rawQuery("SELECT * FROM " + TABLA_CATEGORIAS + " WHERE nombre = \'" + nombre_categoria + "\'", null);

        if (cursorCategoria.moveToFirst()) {
            resultado = cursorCategoria.getInt(3);
        } else resultado = 0;
        bd.close();
        return resultado;
    }

    public String getColor(String nombre_categoria) {
        BBDDHelper bbddHelper = new BBDDHelper(contexto);
        SQLiteDatabase bd = bbddHelper.getWritableDatabase();
        int resultado;
        Cursor cursorCategoria = null;

        cursorCategoria = bd.rawQuery("SELECT * FROM " + TABLA_CATEGORIAS + " WHERE nombre = \'" + nombre_categoria + "\'", null);
        if (cursorCategoria.moveToFirst())
            return cursorCategoria.getString(2);
        else return "";
    }

    public ArrayList<String> getNombresCategoriasIngreso() {
        ArrayList<String> resultado = new ArrayList<>();
        resultado.add(0, "General");
        ArrayList<Categoria> categorias = getCategoriasIngreso();
        for (int i = 0; i < categorias.size(); i++) {
            resultado.add(categorias.get(i).getNombre());
        }
        return resultado;
    }

    public ArrayList<String> getNombresCategoriasGasto() {
        ArrayList<String> resultado = new ArrayList<>();
        resultado.add(0, "General");
        ArrayList<Categoria> categorias = getCategoriasGasto();
        for (int i = 0; i < categorias.size(); i++) {
            resultado.add(categorias.get(i).getNombre());
        }
        return resultado;
    }

    public void borrarCategoria(String nombre, String tipo) {
        try {
            BBDDHelper bbddHelper = new BBDDHelper(contexto);
            SQLiteDatabase bd = bbddHelper.getWritableDatabase();
            Cursor c = null;
            String d = "";
            if (tipo.equalsIgnoreCase("Gasto"))
                d = "<";
            else if (tipo.equalsIgnoreCase("Ingreso"))
                d = ">";
            bd.execSQL("DELETE FROM " + TABLA_CATEGORIAS + " WHERE nombre = \'" + nombre + "\' AND tipo =\'" + tipo + "\'");
            bd.execSQL("DELETE FROM " + TABLA_MOVIMIENTOS + " where categoria = \'" + nombre + "\' AND transaccion " + d + " 0");
            bd.execSQL("DELETE FROM " + TABLA_METAS + " where categoria = \'" + nombre + "\' AND tipo_categoria = \'"+tipo+"\'");
            c.close();
            bd.close();
        } catch (Exception e) {
            e.toString();
        }
    }
}
