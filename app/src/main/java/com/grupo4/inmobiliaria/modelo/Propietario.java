package com.grupo4.inmobiliaria.modelo;

import java.util.Objects;

public class Propietario {

    private int id;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String email;
    private String clave;

    public Propietario(){}

    public Propietario (int id, String nombre, String apellido, String dni, String telefono, String email, String clave) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
        this.clave = clave;
    }

    public int getId () {
        return id;
    }
    public void setId (int id) {
        this.id = id;
    }

    public String getNombre () {
        return nombre;
    }
    public void setNombre (String nombre) {
        this.nombre = nombre;
    }

    public String getApellido () {
        return apellido;
    }
    public void setApellido (String apellido) {
        this.apellido = apellido;
    }

    public String getDni () {
        return dni;
    }
    public void setDni (String dni) {
        this.dni = dni;
    }

    public String getTelefono () {
        return telefono;
    }
    public void setTelefono (String telefono) {
        this.telefono = telefono;
    }

    public String getEmail () {
        return email;
    }
    public void setEmail (String email) {
        this.email = email;
    }

    public String getClave () {
        return clave;
    }
    public void setClave (String clave) {
        this.clave = clave;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Propietario that = (Propietario) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
