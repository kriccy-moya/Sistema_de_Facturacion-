/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.curlp.capaDatos;

import com.curlp.capaLogica.CLCiudad;
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
public class CDCiudad {
    
    private final Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;
    
    public CDCiudad () throws SQLException {
        this.cn = Conexion.conectar();
    }
    
    // Agregar metodos para insertar una ciudad en la tabla
    public void insertarCiudad(CLCiudad cl) throws SQLException {
        String sql ="{CALL sp_insertarCiudad(?)}";
        
        try {
            ps = cn.prepareCall(sql);
            ps.setString(1,cl.getNombreCiudad());
        }catch (SQLException e ) {
            JOptionPane.showMessageDialog(null,"Error" + e.getMessage());
        }
        
    }
    
    // metodo para actualizar la ciudad en la tabla 
     public void actualizarCiudad(CLCiudad cl) throws SQLException {
        String sql ="{CALL sp_actualizarCiudad(?)}";
        
        try {
            ps = cn.prepareCall(sql);
            ps.setInt(1,cl.getCiudadID());
            ps.setString(1,cl.getNombreCiudad());
            ps.execute();
            
        }catch (SQLException e ) {
            JOptionPane.showMessageDialog(null,"Error" + e.getMessage());
        }
        
    }
    
    //metodo para eliminar la ciudad de la tabla 
     
      public void eliminarCiudad(CLCiudad cl) throws SQLException {
        String sql ="{CALL sp_eliminarCiudad(?)}";
        
        try {
            ps = cn.prepareCall(sql);
            ps.setInt(1, cl.getCiudadID());
            ps.execute();
        }catch (SQLException e ) {
            JOptionPane.showMessageDialog(null,"Error" + e.getMessage());
        }
        
    }
      
    // metodo para poblar de datos la tabla 
      
      public List<CLCiudad> obtenerListaCiudades() throws SQLException {
          
          String sql ="{CALL sp_mostrarCiudades}";
          List<CLCiudad> miLista = null;
          
          try {
              st = cn.createStatement();
              rs = st.executeQuery(sql);
              
              miLista = new ArrayList<>();
              while(rs.next()) {
                  CLCiudad cl = new CLCiudad();
                  
                  cl.setCiudadID(rs.getInt("idCiudad"));
                  cl.setNombreCiudad(rs.getString("nombreCiudad"));
                  miLista.add(cl);
              }
              }catch (SQLException e) {
                      JOptionPane.showMessageDialog(null,"Error" + e.getMessage());
                      }
          return miLista;
          }
          
      
      // Metodos que nos va permitir el combo de ciudad 
      public List<String> cargarComboCiudades() throws SQLException {

        String sql = "{CALL sp_mostrarCiudades}";
        List<String> miLista = null;

        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);

            miLista = new ArrayList<>();
            miLista.add("--Seleccione---");
            while (rs.next()) {
               miLista.add(rs.getString("nombreCiudad"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
        
        return miLista;
    }
}
