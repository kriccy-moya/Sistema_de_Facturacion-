/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.curlp.capaDatos;

import com.curlp.capaLogica.CLProveedores;
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
 * @author USERS
 */
public class CDProveedores {
    
    // Declarar las vaiables de conexion y consulta
    private final Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;

    public CDProveedores() throws SQLException {
        this.cn = Conexion.conectar();
    }
    
    // Metodo para obtner los datos del proveedor
    public List<CLProveedores> obtenerListaProveedores() throws SQLException {
        String sql = "{CALL usp_mostrarProveedores()}";

        List<CLProveedores> miLista = null;

        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);

            miLista = new ArrayList<>();

            while (rs.next()) {
                CLProveedores prov = new CLProveedores();

                prov.setIdProveedor(rs.getInt("idProveedor"));
                prov.setNombreProveedor(rs.getString("nombreProveedor"));
                prov.setDireccionProveedor(rs.getString("direccionProveedor"));
                prov.setNumTelefono(rs.getString("numTelefono"));
                prov.setNombreContacto(rs.getString("nombreContacto"));
                prov.setRTNProveedor(rs.getString("RTNProveedor"));
                prov.setEstadoProveedor(rs.getBoolean("estadoProveedor"));
                prov.setCiudad(rs.getInt("IdCiudad")); // Linea momentanea

                miLista.add(prov);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }

    // Metodo para agregar un proveedor
        public void insertarProveedor(CLProveedores prov) throws SQLException {
        String sql = "{CALL usp_insertarProveedor(?,?,?,?,?,?,?)}";
        
        try {
            ps = cn.prepareCall(sql);
            ps.setString(1, prov.getNombreProveedor());
            ps.setString(2, prov.getNumTelefono());
            ps.setString(3, prov.getNombreContacto());
            ps.setString(4, prov.getDireccionProveedor());
            ps.setString(5, prov.getRTNProveedor());
            ps.setBoolean(6, prov.getEstadoProveedor());
            ps.setInt(7, prov.getCiudad()); // Linea momentanea
            ps.execute();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
        
    // Metodo para actualizar datos del proveedor
    public void actualizarProveedor(CLProveedores prov) throws SQLException {
        String sql = "{CALL usp_actualizarProveedor(?,?,?,?,?,?,?,?)}";
        
        try {
            ps = cn.prepareCall(sql);
            ps.setInt(1, prov.getIdProveedor());
            ps.setString(2, prov.getNombreProveedor());
            ps.setString(3, prov.getNumTelefono());
            ps.setString(4, prov.getNombreContacto());
            ps.setString(5, prov.getDireccionProveedor());
            ps.setString(6, prov.getRTNProveedor());
            ps.setBoolean(7, prov.getEstadoProveedor());
            ps.setInt(8, prov.getCiudad()); // Linea momentanea
            ps.execute();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    // Metodo paar eliminar a un proveedor
    public void eliminarProveedor(CLProveedores prov) throws SQLException {
        String sql = "{CALL usp_eliminarProveedor(?)}";
        
        try {
            ps = cn.prepareCall(sql);
            ps.setInt(1, prov.getIdProveedor());
            ps.execute();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}
