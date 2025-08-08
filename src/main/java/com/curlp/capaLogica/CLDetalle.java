/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.curlp.capaLogica;

/**
 *
 * @author kricc
 */
public class CLDetalle {
    
    private int detalleID;
    private int cantidad;
    private double precioUnitario;
    private int facturaID;
    private char codProducto;

    public int getDetalleID() {
        return detalleID;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public int getFacturaID() {
        return facturaID;
    }

    public char getCodProducto() {
        return codProducto;
    }

    public void setDetalleID(int detalleID) {
        this.detalleID = detalleID;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void setFacturaID(int facturaID) {
        this.facturaID = facturaID;
    }

    public void setCodProducto(char codProducto) {
        this.codProducto = codProducto;
    }
    
    
}
