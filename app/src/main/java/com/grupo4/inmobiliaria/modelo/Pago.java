package com.grupo4.inmobiliaria.modelo;

import java.io.Serializable;

public class Pago implements Serializable {

    private int id;
    private Contrato contrato;
    private double precio;
    private String fechaPago;
    private boolean estado;

    public Pago() {}

    public Pago (int id, Contrato contrato, double precio, String fechaPago, boolean estado) {
        this.id = id;
        this.contrato = contrato;
        this.precio = precio;
        this.fechaPago = fechaPago;
        this.estado = estado;
    }

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public Contrato getContrato () {
        return contrato;
    }

    public void setContrato (Contrato contrato) {
        this.contrato = contrato;
    }

    public double getPrecio () {
        return precio;
    }

    public void setPrecio (double precio) {
        this.precio = precio;
    }

    public String getFechaPago () {
        return fechaPago;
    }

    public void setFechaPago (String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public boolean getEstado () {
        return estado;
    }

    public void setEstado (boolean estado) {
        this.estado = estado;
    }
}
