/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.curlp.capaDatos;

import com.curlp.capaLogica.CLMarca;
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
public class CDMarca {
    
    private final Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;
    
    public CDMarca() throws SQLException {
        this.cn = Conexion.conectar();
    }
    
    // agregamos metodos para insertar una marca a la tabla 
    public void insertarMarca(CLMarca cl) {
        String sql = "{CALL sp_insertarMarca(?)}";
        
        try { 
            ps = cn.prepareCall(sql);
            ps.setString(1,cl.getNombreMarca());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error" + e.getMessage());
        }
    }
    // metodo para eliminar marca de la tabla 
    public void eliminarMarca(CLMarca cl ) throws SQLException {
        String sql = "{CALL sp_eliminarMarca}";
        
        try {
            ps = cn.prepareCall(sql);
            ps.setInt(1, cl.getMarcaID());
            ps.execute();
        } catch (SQLException e ) {
            JOptionPane.showMessageDialog(null,"Error"  + e.getMessage());
        }
    }
    
    //metodo para poblar la tabla 
    public List<CLMarca> obtenerListaMarcar() throws SQLException {
          
          String sql ="{CALL sp_mostrarCiudades}";
          List<CLMarca> miLista = null;
          
          try {
              st = cn.createStatement();
              rs = st.executeQuery(sql);
              
              miLista = new ArrayList<>();
              while(rs.next()) {
                  CLMarca cl = new CLMarca();
                  
                  cl.setMarcaID(rs.getInt("idCiudad"));
                  cl.setNombreMarca(rs.getString("nombreCiudad"));
                  miLista.add(cl);
              }
              }catch (SQLException e) {
                      JOptionPane.showMessageDialog(null,"Error" + e.getMessage());
                      }
          return miLista;
          }
}
