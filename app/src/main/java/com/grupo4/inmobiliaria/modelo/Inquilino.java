package com.grupo4.inmobiliaria.modelo;

import java.io.Serializable;

public class Inquilino implements Serializable {

    private int id;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String email;


    public Inquilino() {}

    public Inquilino (int id, String nombre, String apellido, String dni, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
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
}
