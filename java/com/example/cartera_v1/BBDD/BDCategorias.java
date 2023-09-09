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
            values.put("id", getId(tipo));
            values.put("nombre", nombre);
            values.put("color", color);
            values.put("icono", icono);
            values.put("tipo", tipo);
            id = bd.insert(TABLA_CATEGORIAS, null, values);
        } catch (Exception e) {
            e.toString();
        }
        return id;
    }

    // Devuelve el ID siguiente (siempre lo pone el ultimo)
    public long getId(String tipo) {
        long resultado;
        if (tipo.equals("ingreso"))
            resultado = getCategoriasIngreso().size();
        else
            resultado = getCategoriasGasto().size();
        return resultado;

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
        if (cursorCategoria.moveToFirst())
            return cursorCategoria.getInt(3);
        else return 0;
    }
}
