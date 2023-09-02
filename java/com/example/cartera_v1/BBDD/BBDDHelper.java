package com.example.cartera_v1.BBDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BBDDHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "usuario.db";
    public static final String TABLA_CARTERAS = "t_carteras";
    public static final String TABLA_MOVIMIENTOS = "t_movimientos";
    public static final String TABLA_CATEGORIAS_GASTOS = "t_categorias_gastos";
    public static final String TABLA_CATEGORIAS_INGRESOS = "t_categorias_ingresos";

    public BBDDHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLA_CARTERAS + "(" +
                "nombre TEXT PRIMARY KEY, " +
                "balance NUMERIC NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE if exists " + TABLA_CARTERAS);
        //sqLiteDatabase.execSQL("DROP TABLE if exists " + TABLA_MOVIMIENTOS);
        //sqLiteDatabase.execSQL("DROP TABLE if exists " + TABLA_CATEGORIAS_GASTOS);
        //sqLiteDatabase.execSQL("DROP TABLE if exists " + TABLA_CATEGORIAS_INGRESOS);
        onCreate(sqLiteDatabase);
    }
}
