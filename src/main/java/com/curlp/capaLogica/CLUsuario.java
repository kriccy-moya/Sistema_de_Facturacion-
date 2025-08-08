/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.curlp.capaLogica;

/**
 *
 * @author USERS
 */
public class CLUsuario {
    
    private int idUsuario;
    private String nombreUsuario;
    private String contraseña;
    private boolean estado;
    private int idRol;

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public boolean getEstado() {
        return estado;
    }

    public int getIdRol() {
        return idRol;
    }
}
