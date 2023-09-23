package com.example.cartera_v1.Entidades;

public class Model_Data_Categoria {
    double balance;
    String nombre;
    int icono, usanzas;
    String color;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIcono() {
        return icono;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Model_Data_Categoria() {
    }

    public void setUsanzas(int usanzas) {
        this.usanzas = usanzas;
    }

    public int getUsanzas() {
        return usanzas;
    }
}
