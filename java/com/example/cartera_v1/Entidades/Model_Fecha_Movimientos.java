package com.example.cartera_v1.Entidades;

import java.util.ArrayList;

public class Model_Fecha_Movimientos {
    String fecha;
    int balance_total;
    ArrayList<Movimiento> listaMovimientos;
    boolean esVisible;

    public Model_Fecha_Movimientos(ArrayList<Movimiento> listaMovimientos, boolean esVisible) {
        this.listaMovimientos = listaMovimientos;
        this.esVisible = esVisible;
        setBalance_total();
    }

    public void setFecha() {
        if (listaMovimientos.size() != 0)
        fecha = String.valueOf(listaMovimientos.get(0).getDia())+" - "
                +String.valueOf(listaMovimientos.get(0).getMes())+" - "
                +String.valueOf(listaMovimientos.get(0).getAnio());
    }

    private void setBalance_total() {
        int r = 0;
        for (int i = 0; i < listaMovimientos.size(); i++) {
            r += listaMovimientos.get(i).getTransaccion();
        }
        balance_total = r;
    }
    public void addMovimiento(Movimiento m) {
        listaMovimientos.add(m);
        setBalance_total();
    }

    public int getDia() {
        return listaMovimientos.get(0).getDia();
    }
    public int getMes() {
        return listaMovimientos.get(0).getMes();
    }
    public int getAnio() {
        return listaMovimientos.get(0).getAnio();
    }

    public String getFecha() {
        return fecha;
    }

    public Movimiento getFirstMovimiento() {
        return listaMovimientos.get(0);
    }
    public int getBalance_total() {
        return balance_total;
    }

    public ArrayList<Movimiento> getListaMovimientos() {
        return listaMovimientos;
    }

    public boolean isEsVisible() {
        return esVisible;
    }
}
