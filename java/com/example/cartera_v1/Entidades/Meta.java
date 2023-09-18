package com.example.cartera_v1.Entidades;

public class Meta {
    String caracter;
    String categoria;

    String tipo_categoria;
    double porcentaje_objetivo;
    double valor_actual;

    double valor_objetivo;

    public Meta() {
    }

    public String getCategoria() {
        return categoria;
    }

    public void setTipo_categoria(String tipo_categoria) {
        this.tipo_categoria = tipo_categoria;
    }

    public String getTipo_categoria() {
        return tipo_categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


    public String getCaracter() {
        return caracter;
    }

    public void setCaracter(String caracter) {
        this.caracter = caracter;
    }

    public double getValor_actual() {
        return valor_actual;
    }

    public double getPorcentaje_objetivo() {
        return porcentaje_objetivo;
    }

    public void setPorcentaje_objetivo() {
        porcentaje_objetivo = (valor_actual * 100) / valor_objetivo;
    }

    public void setValor_actual(double valor_actual) {
        this.valor_actual = valor_actual;

        setPorcentaje_objetivo();
    }

    public double getValor_objetivo() {
        return valor_objetivo;
    }

    public void setValor_objetivo(double valor_objetivo) {
        this.valor_objetivo =valor_objetivo;
    }


}
