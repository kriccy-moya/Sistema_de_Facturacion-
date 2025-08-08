/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.curlp.capaLogica;

/**
 *
 * @author kricc
 */
public class CLCliente {
    private int ClienteID;
    private String nombre;
    private String apellidos;
    private String direccion;
    private char telefono;
    private String email;
    private String RTN;
    private int idCiudad;
    private int idSexo;

    public int getClienteID() {
        return ClienteID;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public char getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getRTN() {
        return RTN;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public int getIdSexo() {
        return idSexo;
    }

    public void setClienteID(int ClienteID) {
        this.ClienteID = ClienteID;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public void setTelefono(char telefono) {
        this.telefono = telefono;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setRTN(String RTN) {
        this.RTN = RTN;
    }
    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }
    public void setIdSexo(int idSexo) {
        this.idSexo = idSexo;
    }
    
    
}
