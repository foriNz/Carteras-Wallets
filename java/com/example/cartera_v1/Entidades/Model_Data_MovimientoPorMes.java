package com.example.cartera_v1.Entidades;

import java.util.ArrayList;

public class Model_Data_MovimientoPorMes {
    double ingresos, gastos;
    double balance_total;
    ArrayList<Movimiento> listaMovimientos;

    public Model_Data_MovimientoPorMes() {
        listaMovimientos = new ArrayList<>();
    }

    public void addMovimiento(Movimiento m) {
        listaMovimientos.add(m);
        setGastos();
        setIngresos();
        setBalanceTotal();
    }

    private void setBalanceTotal() {
        balance_total = ingresos + gastos;
    }

    public void setIngresos() {
        double contador = 0;
        for (int i = 0; i < listaMovimientos.size(); i++) {
            if (listaMovimientos.get(i).getTransaccion() > 0)
                contador += listaMovimientos.get(i).getTransaccion();
        }
        ingresos = contador;
    }

    public void setGastos() {
        double contador = 0;
        for (int i = 0; i < listaMovimientos.size(); i++) {
            if (listaMovimientos.get(i).getTransaccion() < 0)
                contador += listaMovimientos.get(i).getTransaccion();
        }
        gastos = contador;
    }

    public void setListaMovimientos(ArrayList<Movimiento> listaMovimientos) {
        this.listaMovimientos = listaMovimientos;
        setGastos();
        setIngresos();
    }

    public double getIngresos() {
        return ingresos;
    }

    public double getGastos() {
        return gastos;
    }

    public ArrayList<Movimiento> getListaMovimientos() {
        return listaMovimientos;
    }
}
