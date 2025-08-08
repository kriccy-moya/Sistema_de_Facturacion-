/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.curlp.capaDatos;

import com.curlp.capaLogica.CLTipoVenta;
import java.sql.Connection;
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
public class CDTipoVenta {
    
    // Declarar las vaiables de conexion y consulta
    private final Connection cn;
    ResultSet rs;
    Statement st;

    public CDTipoVenta() throws SQLException {
        this.cn = Conexion.conectar();
    }
    
    // Metodo para obtner los datos del proveedor
    public List<CLTipoVenta> obtenerTipoVenta() throws SQLException {
        String sql = "{CALL usp_mostrarTipoVenta()}";

        List<CLTipoVenta> miLista = null;

        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);

            miLista = new ArrayList<>();

            while (rs.next()) {
                CLTipoVenta tVenta = new CLTipoVenta();

                tVenta.setIdVenta(rs.getInt("idVenta"));
                tVenta.setTipoDeVenta(rs.getString("tipoDeVenta"));
                tVenta.setInteres(rs.getDouble("interes"));

                miLista.add(tVenta);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
    
    // Metodo para llenar el comboBox para el tipo de venta
    public List<String> cargarComboTipoVenta() throws SQLException {
        
        String sql = "{CALL usp_mostrarTipoVenta()}";
        
        List<String> miLista = null;
        
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            
            miLista = new ArrayList<>();
            miLista.add("--Seleccione--");
            
            while (rs.next()) {
                miLista.add(rs.getString("tipoDeVenta"));
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
}
