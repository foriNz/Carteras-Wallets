package com.example.cartera_v1.Entidades;

public class Cartera {
    private double balance;
    private String nombre;

    public Cartera(String nombre) {
        this.nombre = nombre;
        balance = 0;
    }

    public Cartera(String nombre, double balance) {
        this.nombre = nombre;
        this.balance = balance;
    }

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
}
