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

public class CDUsuario {

    // Método para obtener la lista de usuarios
    public List<CLUsuario> obtenerListaUsuarios() {
        String sql = "{CALL usp_mostrarUsuarios()}";
        List<CLUsuario> miLista = new ArrayList<>();

        try (Connection cn = Conexion.conectar();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                CLUsuario u = new CLUsuario();
                u.setIdUsuario(rs.getInt("idUsuario"));
                u.setNombre(rs.getString("nombre"));
                u.setApellidos(rs.getString("apellidos"));
                u.setNombreUsuario(rs.getString("nombreUsuario"));
                u.setContraseña(rs.getString("contraseña"));
                u.setEstado(rs.getBoolean("estado"));
                u.setIdRol(rs.getInt("idRol"));
                miLista.add(u);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener usuarios: " + e.getMessage());
            
        }

        return miLista;
    }

    // Método para insertar un nuevo usuario
    public void insertarUsuario(CLUsuario u) {
        String sql = "{CALL usp_insertarUsuario(?,?,?,?,?,?)}";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareCall(sql)) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellidos());
            ps.setString(3, u.getNombreUsuario());
            ps.setString(4, u.getContraseña());
            ps.setBoolean(5, u.getEstado());
            ps.setInt(6, u.getIdRol());
            ps.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar usuario: " + e.getMessage());
        }
    }

    // Método para actualizar un usuario existente
    public void actualizarUsuario(CLUsuario u) {
        String sql = "{CALL usp_actualizarUsuario(?,?,?,?,?,?)}";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareCall(sql)) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellidos());
            ps.setString(3, u.getNombreUsuario());
            ps.setString(4, u.getContraseña());
            ps.setBoolean(5, u.getEstado());
            ps.setInt(6, u.getIdRol());
            ps.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar usuario: " + e.getMessage());
        }
    }

    // Método para eliminar un usuario
    public void eliminarUsuario(CLUsuario u) {
        String sql = "{CALL usp_eliminarUsuario(?)}";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareCall(sql)) {

            ps.setInt(1, u.getIdUsuario());
            ps.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar usuario: " + e.getMessage());
        }
    }

    // Método para obtener la lista de roles
    public List<String> obtenerRoles() {
        List<String> roles = new ArrayList<>();
        String sql = "{CALL usp_mostrarRoles()}"; // Asegúrate de que este procedimiento sea el correcto

        try (Connection cn = Conexion.conectar();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                roles.add(rs.getString("nombreRol"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener roles: " + e.getMessage());
        }

        return roles;
    }
}
