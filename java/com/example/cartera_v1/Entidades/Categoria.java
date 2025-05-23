package com.example.cartera_v1.Entidades;

public class Categoria {
    private String nombre, color, icono;
    int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Categoria(int id, String nombre, String color, String icono) {
        this.id = id;
        this.nombre = nombre;
        this.color = color;
        this.icono = icono;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
