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
    private CLCiudad ciudad;
    private CLSexo sexo;

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

    public CLCiudad getCiudad() {
        return ciudad;
    }

    public CLSexo getSexo() {
        return sexo;
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

    public void setCiudad(CLCiudad ciudad) {
        this.ciudad = ciudad;
    }

    public void setSexo(CLSexo sexo) {
        this.sexo = sexo;
    }
}
