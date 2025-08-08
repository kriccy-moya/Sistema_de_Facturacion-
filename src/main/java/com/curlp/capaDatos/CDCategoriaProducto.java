/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.curlp.capaDatos;

import com.curlp.capaLogica.CLCategoriaProducto;
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
public class CDCategoriaProducto {
    
    // Declarar las vaiables de conexion y consulta
    private final Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;

    public CDCategoriaProducto() throws SQLException {
        this.cn = Conexion.conectar();
    }
    
    // Metodo para almacenar las categorias de los productos
    public List<CLCategoriaProducto> obtenerListaCategoriasProducto() throws SQLException {
        
        String sql = "{CALL usp_mostrarCategorias()}";
        
        List<CLCategoriaProducto> miLista = null;
        
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            
            miLista = new ArrayList<>();
            
            while (rs.next()) {
                CLCategoriaProducto catPro = new CLCategoriaProducto();
                
                catPro.setIdCategoria(rs.getInt("idCategoria"));
                catPro.setNombreCategoria(rs.getString("nombreCategoria"));
                miLista.add(catPro);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
    
    // Metodo para agregar categorias
    public void agregarCategoria(CLCategoriaProducto catPro) throws SQLException {
        String sql = "{CALL usp_insertarCategoria(?)}";
        
        try {
            ps = cn.prepareCall(sql);
            ps.setString(1, catPro.getNombreCategoria());
            ps.execute();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    // Metodo para actualizar una categoria
    public void actualizarCategoria(CLCategoriaProducto catPro) throws SQLException {
        String sql = "{CALL usp_actualizarCategoria(?,?)}";
        
        try {
            ps = cn.prepareCall(sql);
            ps.setInt(1, catPro.getIdCategoria());
            ps.setString(2, catPro.getNombreCategoria());
            ps.execute();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    // Metodo para eliminar una categoria
    public void eliminarCategoria(CLCategoriaProducto catPro) throws SQLException {
        String sql = "{CALL usp_eliminarCategoria(?)}";
        
        try {
            ps = cn.prepareCall(sql);
            ps.setInt(1, catPro.getIdCategoria());
            ps.execute();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    // Metodo para filtrar por el ID de una categoria
    public int filtrarCategoriaPorId() throws SQLException {
        int idCategoria = 0;
        
        String sql = "{CALL usp_filtrarCategoriaPorId()}";
        
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            
            idCategoria = rs.getInt("idCategoria");
            
            if (idCategoria == 0) {
                idCategoria = 1;
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        
        return idCategoria;
    }
}
