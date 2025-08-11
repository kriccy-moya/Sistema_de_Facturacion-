/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.curlp.capaPresentacion;

import com.curlp.capaDatos.CDCiudad;
import com.curlp.capaLogica.CLCiudad;
import java.sql.SQLException;
import java.util.List;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Equipo 1
 */
public class jIFraCiudad extends javax.swing.JInternalFrame {

    public jIFraCiudad() throws SQLException {
        initComponents();
        poblarTablaCiudades();
        //encontrarCorrelativo();
        jTFNombreCiudad.requestFocus();
    }
    
    // Metodo para limpiar los datos de la tabla
    private void limpiarTabla() {
        DefaultTableModel dtm = (DefaultTableModel) this.jTblCiudades.getModel();
        
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    }
    
    // Metodo para poblar de datos la tabla
    private void poblarTablaCiudades() throws SQLException {
        limpiarTabla();
        
        CDCiudad cdc = new CDCiudad();
        List<CLCiudad> miLista = cdc.obtenerListaCiudades();
        DefaultTableModel temp = (DefaultTableModel) this.jTblCiudades.getModel();
        
        miLista.stream().map((CLCiudad cl) -> {
            Object[] fila = new Object[2];
            fila[0] = cl.getCiudadID();
            fila[1] = cl.getNombreCiudad();
            return fila;
            
        }).forEachOrdered(temp::addRow);
    }
    
    // Metodo para encontrar el corelativo del id Ciudad
    /*private void encontrarCorrelativo() throws SQLException {
        CDCiudad cdc = new CDCiudad();
        CLCiudad cl = new CLCiudad();
        
        cl.setIdCiudad(cdc.autoIncrementableCiudadID());
        this.jTFIDCiudad.setText(String.valueOf(cl.getIdCiudad()));
    } */
    
    // Metodo para habilitar o desactivar botones
    private void habilitarBotones(boolean guardar, boolean editar, 
            boolean eliminar, boolean limpiar) {
        this.jBtnGuardar.setEnabled(guardar);
        this.jBtnEditar.setEnabled(editar);
        this.jBtnGuardar.setEnabled(guardar);
        this.jBtnEliminar.setEnabled(eliminar);
    }
    
    // Metodo para limpiar las TextField
    private void limpiarTextField() {
        jTFIDCiudad.setText("");
        jTFNombreCiudad.setText("");
        jTFNombreCiudad.requestFocus();
    }
    
    // Metodo para validar la textField
    private boolean validarTextField() {
        boolean estado;
        
        estado = jTFNombreCiudad.getText().isEmpty();
        
        return estado;
    }
    
    // Metodo para insertar una ciudad en la tabla
    private void insertarCiudad() {
        if (validarTextField()) {
            JOptionPane.showMessageDialog(null, "Tiene que "
                    + "ingresar el nombre de la ciudad", "Control Cliente", JOptionPane.WARNING_MESSAGE);
            this.jTFNombreCiudad.requestFocus();
        } else {
            try{
                CDCiudad cdc = new CDCiudad();
                CLCiudad cl = new CLCiudad();
                cl.setNombreCiudad(jTFNombreCiudad.getText().trim());
                cdc.insertarCiudad(cl);
                
                JOptionPane.showMessageDialog(null, "Registro almacenado correctamente", 
                        "Control Cliente", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al almacenar el registro"
                        + ex);
                this.jTFNombreCiudad.requestFocus();
            }
        }
    }
    
    // Metodo para guardar datos
    private void guardar() throws SQLException {
        insertarCiudad();
        poblarTablaCiudades();
        habilitarBotones(true, false, false, true);
        limpiarTextField();
        //encontrarCorrelativo();
    }
    
    // Metodo para actualizar una ciudad en la tabla
    private void actualizarCiudad() {
        if (validarTextField()) {
            JOptionPane.showMessageDialog(null, "Tiene que "
                    + "ingresar el nombre de la ciudad", "Control Cliente", JOptionPane.WARNING_MESSAGE);
            this.jTFNombreCiudad.requestFocus();
        } else {
            try{
                CDCiudad cdc = new CDCiudad();
                CLCiudad cl = new CLCiudad();
                cl.setCiudadID(Integer.parseInt(jTFIDCiudad.getText().trim()));
                cl.setNombreCiudad(jTFNombreCiudad.getText().trim());
                cdc.actualizarCiudad(cl);
                
                JOptionPane.showMessageDialog(null, "Registro actualizado correctamente", 
                        "Control Cliente", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al modificar el registro"
                        + ex);
                this.jTFNombreCiudad.requestFocus();
            }
        }
    }
    
    // Metodo para seleccionar los datos de la fila para modificarlos
    private void filaSeleccionada() {
        if (jTblCiudades.getSelectedRow() != -1) {
            jTFIDCiudad.setText(String.valueOf(
                    jTblCiudades.getValueAt(
                            jTblCiudades.getSelectedRow(), 0)));
            jTFNombreCiudad.setText(String.valueOf(
                    jTblCiudades.getValueAt(
                            jTblCiudades.getSelectedRow(), 1)));
        }
    }
    
    // Metodo para llamar el metodo de actualizar tabla
    private void editar() throws SQLException {
        actualizarCiudad();
        poblarTablaCiudades();
        habilitarBotones(true, false, false, true);
        limpiarTextField();
        //encontrarCorrelativo();
    }
    
    // Metodo para eliminar
    private void eliminarCiudad() {
        try{
                CDCiudad cdc = new CDCiudad();
                CLCiudad cl = new CLCiudad();
                cl.setCiudadID(Integer.parseInt(jTFIDCiudad.getText().trim()));
                cdc.eliminarCiudad(cl);
                
                JOptionPane.showMessageDialog(null, "Registro eliminado correctamente", 
                        "Control Cliente", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al eliminar el registro"
                        + ex);
                this.jTFNombreCiudad.requestFocus();
            }
    }
    
    private void eliminar() throws SQLException {
        
        int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de que "
                + "desea eliminar el registro?", "control credito", JOptionPane.YES_NO_OPTION);
        
        if (resp == JOptionPane.YES_OPTION) {
            try {
                eliminarCiudad();
                poblarTablaCiudades();
                habilitarBotones(true, false, false, true);
                limpiarTextField();
                //encontrarCorrelativo();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error" + ex);
                this.jTFNombreCiudad.requestFocus();
            }
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTFIDCiudad = new javax.swing.JTextField();
        jTFNombreCiudad = new javax.swing.JTextField();
        jBtnGuardar = new javax.swing.JButton();
        jBtnEditar = new javax.swing.JButton();
        jBtnEliminar = new javax.swing.JButton();
        jBtnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblCiudades = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));

        jPanel3.setBackground(new java.awt.Color(102, 102, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID Ciudad");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre Ciudad");

        jTFIDCiudad.setEnabled(false);

        jBtnGuardar.setForeground(new java.awt.Color(51, 51, 255));
        jBtnGuardar.setText("Guardar");
        jBtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGuardarActionPerformed(evt);
            }
        });

        jBtnEditar.setForeground(new java.awt.Color(51, 51, 255));
        jBtnEditar.setText("Editar");
        jBtnEditar.setEnabled(false);
        jBtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEditarActionPerformed(evt);
            }
        });

        jBtnEliminar.setForeground(new java.awt.Color(51, 51, 255));
        jBtnEliminar.setText("Eliminar");
        jBtnEliminar.setEnabled(false);
        jBtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEliminarActionPerformed(evt);
            }
        });

        jBtnLimpiar.setForeground(new java.awt.Color(0, 0, 255));
        jBtnLimpiar.setText("Limpiar");
        jBtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1))
                    .addComponent(jBtnGuardar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnLimpiar)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jBtnEditar)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnEliminar))
                    .addComponent(jTFIDCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFNombreCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTFIDCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jTFNombreCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnGuardar)
                    .addComponent(jBtnEditar)
                    .addComponent(jBtnEliminar))
                .addGap(18, 18, 18)
                .addComponent(jBtnLimpiar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTblCiudades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id Ciudad", "Nombre Ciudad"
            }
        ));
        jTblCiudades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblCiudadesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTblCiudades);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(0, 51, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Gestion Ciudad");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(183, 183, 183)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel3)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
        try {
            guardar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al almacenar el registro"
                + ex);
        }
    }//GEN-LAST:event_jBtnGuardarActionPerformed

    private void jBtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEditarActionPerformed
        try {
            editar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al editar el registro"
                + ex);
        }
    }//GEN-LAST:event_jBtnEditarActionPerformed

    private void jBtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEliminarActionPerformed
        try {
            eliminar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el registro"
                + ex);
        }
    }//GEN-LAST:event_jBtnEliminarActionPerformed

    private void jBtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLimpiarActionPerformed
        limpiarTextField();
    }//GEN-LAST:event_jBtnLimpiarActionPerformed

    private void jTblCiudadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblCiudadesMouseClicked
        filaSeleccionada();
        habilitarBotones(false, true, true, true);
    }//GEN-LAST:event_jTblCiudadesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnEditar;
    private javax.swing.JButton jBtnEliminar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JButton jBtnLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFIDCiudad;
    private javax.swing.JTextField jTFNombreCiudad;
    private javax.swing.JTable jTblCiudades;
    // End of variables declaration//GEN-END:variables
}
