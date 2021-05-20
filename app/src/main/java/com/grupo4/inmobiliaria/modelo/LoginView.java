package com.grupo4.inmobiliaria.modelo;

public class LoginView {

    private String usuario;
    private String clave;

    public LoginView(String usuario, String clave){
        this.usuario = usuario;
        this.clave = clave;
    }

    public String getUsuario(){return usuario;}

    public void SetUsuario(String usuario){this.usuario = usuario;}

    public String getClave(){return clave;}

    public void SetClave(String clave){this.clave = clave;}


}
