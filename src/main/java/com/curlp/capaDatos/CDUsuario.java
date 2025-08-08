/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.curlp.capaDatos;
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
 * @author USERS
 */
public class CDUsuario {
    
    // Declarar las vaiables de conexion y consulta
    private final Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;

    public CDUsuario() throws SQLException {
        this.cn = Conexion.conectar();
    }
    
    // Metodo para obtner los datos del proveedor
    public List<CLUsuario> obtenerListaUsuarios() throws SQLException {
        String sql = "{CALL usp_mostrarUsuarios()}";

        List<CLUsuario> miLista = null;

        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);

            miLista = new ArrayList<>();

            while (rs.next()) {
                CLUsuario u = new CLUsuario();

                u.setIdUsuario(rs.getInt("idUsuario"));
                u.setNombreUsuario(rs.getString("nombreUsuario"));
                u.setContraseña(rs.getString("contraseña"));
                u.setEstado(rs.getBoolean("estado"));
                u.setIdRol(rs.getInt("idRol")); // Linea momentanea

                miLista.add(u);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }

    // Metodo para agregar un nuevo usuario
        public void insertarUsuario(CLUsuario u) throws SQLException {
        String sql = "{CALL usp_insertarUsuario(?,?,?,?)}";
        
        try {
            ps = cn.prepareCall(sql);
            ps.setString(1, u.getNombreUsuario());
            ps.setString(2, u.getContraseña());
            ps.setBoolean(3, u.getEstado());
            ps.setInt(4, u.getIdRol()); // Linea momentanea
            ps.execute();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
        
    // Metodo para actualizar datos del usuario
    public void actualizarUsuario(CLUsuario u) throws SQLException {
        String sql = "{CALL usp_actualizarUsuario(?,?,?,?,?)}";
        
        try {
            ps = cn.prepareCall(sql);
            ps.setInt(1, u.getIdUsuario());
            ps.setString(2, u.getNombreUsuario());
            ps.setString(2, u.getContraseña());
            ps.setBoolean(4, u.getEstado());
            ps.setInt(5, u.getIdRol()); // Linea momentanea
            ps.execute();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    // Metodo paar eliminar a un usuario
    public void eliminarUsuario(CLUsuario u) throws SQLException {
        String sql = "{CALL usp_eliminarUsuario(?)}";
        
        try {
            ps = cn.prepareCall(sql);
            ps.setInt(1, u.getIdUsuario());
            ps.execute();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
}
