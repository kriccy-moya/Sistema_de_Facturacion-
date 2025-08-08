/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.curlp.capaDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *    
 * @author kricc
 */
public class Conexion {
    
    private static String url = "jdbc:mysql://localhost:3306/clientedatabase?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static String usec = "root";
    private static String clave ="123456789";
    
    public static Connection conectar() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            return DriverManager.getConnection(url,usec,clave) ;
        } catch (ClassNotFoundException e) {
          throw new SQLException(e.getMessage());
        }  
    }
}
