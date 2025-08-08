/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.curlp.capaLogica;

/**
 *
 * @author USERS
 */
public class CLProveedores {
    
    private int idProveedor;
    private String nombreProveedor;
    private String numTelefono;
    private String nombreContacto;
    private String direccionProveedor;
    private String RTNProveedor;
    private boolean estadoProveedor;
    private CLCiudad ciudad;

    public int getIdProveedor() {
        return idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public String getNumTelefono() {
        return numTelefono;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public String getDireccionProveedor() {
        return direccionProveedor;
    }

    public String getRTNProveedor() {
        return RTNProveedor;
    }

    public boolean getEstadoProveedor() {
        return estadoProveedor;
    }

    public CLCiudad getCiudad() {
        return ciudad;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public void setDireccionProveedor(String direccionProveedor) {
        this.direccionProveedor = direccionProveedor;
    }

    public void setRTNProveedor(String RTNProveedor) {
        this.RTNProveedor = RTNProveedor;
    }

    public void setEstadoProveedor(boolean estadoProveedor) {
        this.estadoProveedor = estadoProveedor;
    }

    public void setCiudad(CLCiudad ciudad) {
        this.ciudad = ciudad;
    }
    
    
}
