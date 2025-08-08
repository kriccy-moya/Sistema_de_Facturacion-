/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.curlp.capaDatos;

import com.curlp.capaLogica.CLSexo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author USERS
 */
public class CDSexo {
    private final Connection cn;
    ResultSet rs;
    Statement st;

    public CDSexo(Connection cn) {
        this.cn = cn;
    }

    public List<CLSexo> listarSexos() throws SQLException {
        String sql = "SELECT idSexo, descSexo "
                + "FROM sexo";
        
        List<CLSexo> miLista = null;
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            
            while (rs.next()) {
                
                CLSexo s = new CLSexo();
                s.setIdSexo(rs.getInt("idSexo"));
                s.setDescSexo(rs.getString("descSexo"));
                miLista.add(s);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        
        return miLista;
    }
}

