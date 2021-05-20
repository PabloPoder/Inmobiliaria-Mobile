package com.grupo4.inmobiliaria.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Inmueble implements Serializable {

    private int id;
    private String direccion;
    private int ambientes;
    private int superficie;
    private double latitud;
    private double longitud;
    private double precio;
    private Propietario propietario;
    private String foto;
    private boolean estado = true;

    public Inmueble(int id, String direccion, int ambientes, int superficie,
                    double latitud, double longitud, double precio,
                    Propietario propietario, boolean estado, String foto) {

        this.id = id;
        this.direccion = direccion;
        this.ambientes = ambientes;
        this.superficie = superficie;
        this.latitud = latitud;
        this.longitud = longitud;
        this.precio = precio;
        this.propietario = propietario;
        this.estado = estado;
        this.foto = foto;
    }
    public Inmueble() {

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getAmbientes() {
        return ambientes;
    }
    public void setAmbientes(int ambientes) {
        this.ambientes = ambientes;
    }

    public int getSuperficie() {
        return superficie;
    }
    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public double getLatitud() {
        return latitud;
    }
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Propietario getPropietario() {
        return propietario;
    }
    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getFoto() {
        return foto;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inmueble inmueble = (Inmueble) o;
        return id == inmueble.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
