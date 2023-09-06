package com.example.cartera_v1.BBDD;

import android.content.Context;

import androidx.annotation.Nullable;

import com.example.cartera_v1.Entidades.Movimiento;

import java.util.ArrayList;

public class BDMovimientos extends BBDDHelper{
    public BDMovimientos(@Nullable Context context) {
        super(context);
    }

    public ArrayList<Movimiento> getMovimientos() {
        return null;
    }
}
