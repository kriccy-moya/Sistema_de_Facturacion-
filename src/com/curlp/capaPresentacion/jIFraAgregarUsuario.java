/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.curlp.capaPresentacion;

import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author USERS
 */
public class jIFraAgregarUsuario extends javax.swing.JInternalFrame {

    public jIFraAgregarUsuario() {
        initComponents();
        poblarCBEstado();
    }
    
    // Metodo para validar los datos
    private boolean validarDatosUsuario() {
        boolean estado;
        
        if (jTFNombreUsuario.getText().isEmpty() &&
                jPFContraseña.getText().isEmpty() && 
                jCBEstado.getSelectedIndex() == 0 && 
                jCBRol.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese datos en todos los campos", 
                    "Envoice System", JOptionPane.WARNING_MESSAGE);
            jTFNombreUsuario.requestFocus();
            estado = false;
            
        } else if (jTFNombreUsuario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese un nombre al usuario", 
                    "Envoice System", JOptionPane.WARNING_MESSAGE);
            jTFNombreUsuario.requestFocus();
            estado = false;
            
        } else if (jPFContraseña.getPassword().length == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese una contraseña", 
                    "Envoice System", JOptionPane.WARNING_MESSAGE);
            jPFContraseña.requestFocus();
            estado = false;
            
        } else if (jPFContraseña.getPassword().length < 8) {
            JOptionPane.showMessageDialog(null, "Ingrese una contraseña mayor a 8 digitos", 
                    "Envoice System", JOptionPane.WARNING_MESSAGE);
            jPFContraseña.requestFocus();
            estado = false;
            
        } else if (jCBEstado.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un estado valido", 
                    "Envoice System", JOptionPane.WARNING_MESSAGE);
            estado = false;
            
        }  else if (jCBRol.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un rol", 
                    "Envoice System", JOptionPane.WARNING_MESSAGE);
            estado = false;
            
        } else {
            estado = true;
        }
        
        return estado;
    }
    
    private void poblarCBEstado() {
        String estado[] = {"--Seleccione--", "Activo", "Inactivo"};
        
        jCBEstado.setModel(new DefaultComboBoxModel <> (estado));
    }
    
    // Metodo para validar el estado
    private boolean validarEstado() {
        boolean estado;
        
        if (jCBEstado.getSelectedIndex() == 1) {
            estado = true;
        } else if (jCBEstado.getSelectedIndex() == 2) {
            estado = false;
        } else {
            estado = false;
        }
        return estado;
    }
    
    private void poblarCBRol() {
        
    }
    
    /*/ Metodo que valida el estado
    private boolean validarEstado() {
        boolean estado;
        
        this.jCBEstado.getSelectedIndex()
        
        return estado;
    }*/
    
    // Metodo para mostar la contraseña
    private void mostrarContraseña() {
        
        jCkBMostrarContraseña.addActionListener((ActionEvent e) -> {
            if (jCkBMostrarContraseña.isSelected()) {
                jPFContraseña.setEchoChar((char) 0); // Muestra la contraseña
            } else {
                jPFContraseña.setEchoChar('*'); // La oculta
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTFNombreUsuario = new javax.swing.JTextField();
        jBtnAgregar = new javax.swing.JButton();
        jPFContraseña = new javax.swing.JPasswordField();
        jCkBMostrarContraseña = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jCBEstado = new javax.swing.JComboBox<>();
        jCBRol = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel4.setText("Contraseña:");

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel9.setText("Nombre Usuario:");

        jBtnAgregar.setBackground(new java.awt.Color(0, 102, 204));
        jBtnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnAgregar.setText("Agregar");
        jBtnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAgregarActionPerformed(evt);
            }
        });

        jCkBMostrarContraseña.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCkBMostrarContraseñaMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel7.setText("Estado:");

        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel8.setText("Rol:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jTFNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPFContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCkBMostrarContraseña))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jCBEstado, javax.swing.GroupLayout.Alignment.LEADING, 0, 111, Short.MAX_VALUE)
                        .addComponent(jCBRol, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jBtnAgregar)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTFNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCkBMostrarContraseña, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jPFContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jBtnAgregar)
                .addGap(16, 16, 16))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Agregar Nuevo Usuario");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(102, 102, 102))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAgregarActionPerformed
        
    }//GEN-LAST:event_jBtnAgregarActionPerformed

    private void jCkBMostrarContraseñaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCkBMostrarContraseñaMouseClicked
        mostrarContraseña();
    }//GEN-LAST:event_jCkBMostrarContraseñaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAgregar;
    private javax.swing.JComboBox<String> jCBEstado;
    private javax.swing.JComboBox<String> jCBRol;
    private javax.swing.JCheckBox jCkBMostrarContraseña;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField jPFContraseña;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTFNombreUsuario;
    // End of variables declaration//GEN-END:variables
}
