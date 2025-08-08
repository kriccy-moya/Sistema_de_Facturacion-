/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.curlp.capaDatos;

import com.curlp.capaLogica.CLDetalle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author kricc
 */
public class CDDetalle {
    
    private final Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;
    
    public CDDetalle() throws SQLException {
        this.cn = Conexion.conectar();
    }
    // metodo para insertar detalle 
    public void insertarDetalle(CLDetalle cl) {
        String sql ="{CALL sp_insertarDetalle(?)}";
        
           try { 
            ps = cn.prepareCall(sql);
            ps.setInt(1,cl.getCantidad());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error" + e.getMessage());
        }
    }
    // metodo para actualizar detalle 
      public void actualizarCiudad(CLDetalle cl) throws SQLException {
        String sql ="{CALL sp_actualizarDetalle(?)}";
        
        try {
            ps = cn.prepareCall(sql);
            ps.setInt(1,cl.getDetalleID());
            ps.setInt(1,cl.getCantidad());
            ps.setDouble(1,cl.getPrecioUnitario());
            ps.setInt(1,cl.getFacturaID());
            ps.setInt(1,cl.getCodProducto());
            ps.execute();
            
        }catch (SQLException e ) {
            JOptionPane.showMessageDialog(null,"Error" + e.getMessage());
        }
        
    }      
    
    // metodo para eliminar detalle de una factura 
     public void eliminarDetalle(CLDetalle cl) throws SQLException { 
        String sql ="{CALL sp_eliminarDetalle(?)}";
        
        try {
            ps = cn.prepareCall(sql);
            ps.setInt(1, cl.getDetalleID());
            ps.execute();
        }catch (SQLException e ) {
            JOptionPane.showMessageDialog(null,"Error" + e.getMessage());
        } 
              
    }
}    
