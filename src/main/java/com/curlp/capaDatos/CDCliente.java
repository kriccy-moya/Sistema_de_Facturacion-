/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.curlp.capaDatos;

import com.curlp.capaLogica.CLCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author kricc
 */
public class CDCliente {
        
     private final Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;
    
    public CDCliente() throws SQLException {
        this.cn = Conexion.conectar();
    }
    
    // Agregar metodos para insertar clientes en la tabla 
    public void insertarCliente(CLCliente lc) {
        String sql = "{CALL sp_insertarCliente}";
        
        try {
            ps = cn.prepareCall(sql);
            
        }catch (SQLException e) {
            
        }
    }
}
