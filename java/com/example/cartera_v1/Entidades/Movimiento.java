package com.example.cartera_v1.Entidades;

public class Movimiento {
    int id, anio, mes, dia;
    double transaccion;
    String nombre_cartera, categoria, nota;

    @Override
    public String toString() {
        return "Movimiento{" +
                "anio=" + anio +
                ", mes=" + mes +
                ", dia=" + dia +
                ", transaccion=" + transaccion +
                ", nombre_cartera='" + nombre_cartera + '\'' +
                '}';
    }

    public Movimiento() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public double getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(double transaccion) {
        this.transaccion = transaccion;
    }

    public String getNombre_cartera() {
        return nombre_cartera;
    }

    public void setNombre_cartera(String nombre_cartera) {
        this.nombre_cartera = nombre_cartera;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
