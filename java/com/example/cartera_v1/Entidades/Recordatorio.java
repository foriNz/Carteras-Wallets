package com.example.cartera_v1.Entidades;

public class Recordatorio {
    private int id, repeticion;
    private long intervalo;
    private String titulo, descripcion;
    private String fecha;

    public Recordatorio() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRepeticion() {
        return repeticion;
    }

    public void setRepeticion(boolean repeticion) {
        if (repeticion)
            this.repeticion = 1;
        else
            this.repeticion = 0;
    }
    public void setRepeticion(int repeticion) {
        this.repeticion=repeticion;
    }


    public long getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(long intervalo) {
        this.intervalo = intervalo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
