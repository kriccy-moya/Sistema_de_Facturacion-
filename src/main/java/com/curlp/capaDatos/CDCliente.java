/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.curlp.capaDatos;

import com.curlp.capaLogica.CLCliente;
import com.curlp.capaLogica.CLUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

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
    public List<CLCliente> obtenerListaClientes() {
        String sql = "{CALL sp_mostrarClientes}";
        List<CLCliente> miLista = new ArrayList<>();

        try (Connection cn = Conexion.conectar();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                CLCliente c = new CLCliente();
                c.setNombre(rs.getString("nombre"));
                
                miLista.add(c);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener los clientes : " + e.getMessage());
            
        }

        return miLista;
    }
    // Agregar metodos para insertar clientes en la tabla 
    public void insertarCliente(CLCliente lc) throws SQLException {
        String sql = "{CALL sp_insertarCliente}";
        
        try  (Connection cn = Conexion.conectar();
            PreparedStatement ps = cn.prepareCall(sql)){
         
            
            
        }catch (SQLException e) {
         
            cn.close();
        }
        
    }
}
