package com.grupo4.inmobiliaria.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Contrato implements Serializable {

    private int id;
    private String fechaDesde;
    private String fechaHasta;
    private Inquilino inquilino;
    private Inmueble inmueble;
    private boolean estado;

    public Contrato() {}

    public Contrato (int id, String fechaDesde, String fechaHasta, Inquilino inquilino, Inmueble inmueble, boolean estado) {
        this.id = id;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.inquilino = inquilino;
        this.inmueble = inmueble;
        this.estado = estado;
    }

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getFechaDesde () {
        return fechaDesde;
    }

    public void setFechaDesde (String fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public String getFechaHasta () {
        return fechaHasta;
    }

    public void setFechaHasta (String fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Inquilino getInquilino () {
        return inquilino;
    }

    public void setInquilino (Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public Inmueble getInmueble () {
        return inmueble;
    }

    public void setInmueble (Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public boolean isEstado () {
        return estado;
    }

    public void setEstado (boolean estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contrato contrato = (Contrato) o;
        return id == contrato.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
