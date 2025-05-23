package com.example.cartera_v1.BBDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BBDDHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 17;
    private static final String DATABASE_NAME = "usuario.db";
    public static final String TABLA_CARTERAS = "t_carteras";
    public static final String TABLA_MOVIMIENTOS = "t_movimientos";
    public static final String TABLA_CATEGORIAS = "t_categorias";
    public static final String TABLA_METAS = "t_metas";
    public static final String TABLA_RECORDATORIOS = "t_recordatorios";


    public BBDDHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLA_CARTERAS + "(" +
                "nombre TEXT PRIMARY KEY, " +
                "balance NUMERIC NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLA_CATEGORIAS +" (" +
                "id NUMERIC NOT NULL, " +
                "nombre TEXT NOT NULL, " +
                "color TEXT NOT NULL, " +
                "icono TEXT NOT NULL, " +
                "tipo TEXT NOT NULL, " +
                "PRIMARY KEY (id,nombre,tipo))");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLA_MOVIMIENTOS + "(" +
                "id NUMERIC, " +
                "nombre_cartera TEXT NOT NULL, " +
                "anio NUMERIC, " +
                "mes NUMERIC, " +
                "dia NUMERIC, " +
                "transaccion NUMERIC NOT NULL, " +
                "categoria TEXT NOT NULL, " +
                "nota TEXT, " +
                "PRIMARY KEY(id, anio, mes ,dia))");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLA_METAS + "(" +
                "caracter TEXT NOT NULL, " +
                "categoria TEXT, " +
                "tipo_categoria TEXT, " +
                "objetivo NUMERIC NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLA_RECORDATORIOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "titulo TEXT NOT NULL, "+
                "descripcion TEXT NOT NULL,"+
                "fecha  TEXT NOT NULL, "+
                "repeticion NUMERIC NOT NULL, "+
                "intervalo NUMERIC NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE if exists " + TABLA_CARTERAS);
        sqLiteDatabase.execSQL("DROP TABLE if exists " + TABLA_MOVIMIENTOS);
        sqLiteDatabase.execSQL("DROP TABLE if exists " + TABLA_CATEGORIAS);
        sqLiteDatabase.execSQL("DROP TABLE if exists " + TABLA_METAS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLA_RECORDATORIOS);
        onCreate(sqLiteDatabase);
    }
}
