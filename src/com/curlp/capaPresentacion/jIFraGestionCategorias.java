/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.curlp.capaPresentacion;

import com.curlp.capaDatos.CDCategoriaProducto;
import com.curlp.capaLogica.CLCategoriaProducto;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Equipo 1
 */
public class jIFraGestionCategorias extends javax.swing.JInternalFrame {

    /**
     * Creates new form jIFraGestionCategorias
     */
    public jIFraGestionCategorias() throws SQLException {
        initComponents();
        poblarTablaCategorias();
        filtrarCategorias();
        jTFNombreCategoria.requestFocus();
    }
    
    // Metodo para limpiar los datos de la tabla
    private void limpiarTabla() {
        DefaultTableModel dtm = (DefaultTableModel) this.jTblCategorias.getModel();
        
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    }
    
    // Metodo para poblar de datos la tabla
    private void poblarTablaCategorias() throws SQLException {
        limpiarTabla();
        
        CDCategoriaProducto CDCP = new CDCategoriaProducto();
        List<CLCategoriaProducto> miLista = CDCP.obtenerListaCategoriasProducto();
        DefaultTableModel temp = (DefaultTableModel) jTblCategorias.getModel();
        
        miLista.stream().map((CLCategoriaProducto CLCP) -> {
            Object[] fila = new Object[2];
            fila[0] = CLCP.getIdCategoria();
            fila[1] = CLCP.getNombreCategoria();
            return fila;
            
        }).forEachOrdered(temp::addRow);
    }
    
    // Metodo para filtrar las categorias por ID
    private void filtrarCategorias() throws SQLException {
        CDCategoriaProducto CDCP = new CDCategoriaProducto();
        CLCategoriaProducto CLCP = new CLCategoriaProducto();
        
        CLCP.setIdCategoria(CDCP.filtrarCategoriaPorId());
        jTFIDCategoria.setText(String.valueOf((char) CLCP.getIdCategoria()));
    }
    
    // Metodo para habilitar o desactivar botones
    private void habilitarBotones(boolean guardar, boolean editar, 
            boolean eliminar, boolean limpiar) {
        jBtnGuardar.setEnabled(guardar);
        jBtnEditar.setEnabled(editar);
        jBtnEliminar.setEnabled(eliminar);
        jBtnLimpiar.setEnabled(limpiar);
        
    }
    
    // Metodo para limpiar las TextField
    private void limpiarTextField() {
        jTFIDCategoria.setText("");
        jTFNombreCategoria.setText("");
        jTFNombreCategoria.requestFocus();
    }
    
    // Metodo para validar la textField
    private boolean validarTextField() {
        boolean estado;
        
        if (jTFNombreCategoria.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tiene que "
                    + "ingresar un nombre para la categoria", 
                    "Envoice System", JOptionPane.WARNING_MESSAGE);
            this.jTFNombreCategoria.requestFocus();
            estado = false;
            
        } else {
            estado = true;
        }
        
        return estado;
    }
    
    // Metodo para insertar una categoria
    private void insertarCategoria() {
        if (validarTextField()) {
            try{
                CDCategoriaProducto CDCP = new CDCategoriaProducto();
                CLCategoriaProducto CLCP = new CLCategoriaProducto();
                CLCP.setNombreCategoria(jTFNombreCategoria.getText().trim());
                CDCP.agregarCategoria(CLCP);
                
                JOptionPane.showMessageDialog(null, "Registro almacenado correctamente", 
                        "Sistema Facturación", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al almacenar el registro"
                        + ex);
                jTFNombreCategoria.requestFocus();
            }
        }
    }
    
    // Metodo para guardar datos
    private void guardar() throws SQLException {
        insertarCategoria();
        poblarTablaCategorias();
        habilitarBotones(true, false, false, true);
        limpiarTextField();
        filtrarCategorias();
    }
    
    // Metodo para actualizar una categoria en la tabla
    private void actualizarCategoria() {
        if (validarTextField()) {
            try{
                CDCategoriaProducto CDCP = new CDCategoriaProducto();
                CLCategoriaProducto CLCP = new CLCategoriaProducto();
                CLCP.setNombreCategoria(jTFNombreCategoria.getText().trim());
                CDCP.actualizarCategoria(CLCP);
                
                JOptionPane.showMessageDialog(null, "Registro actualizado correctamente", 
                        "Sistema Facturación", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al actualizar el registro"
                        + ex);
                this.jTFNombreCategoria.requestFocus();
            }
        }
    }
    
    // Metodo para seleccionar los datos de la fila para modificarlos
    private void filaSeleccionada() {
        if (jTblCategorias.getSelectedRow() != -1) {
            jTFIDCategoria.setText(String.valueOf(
                    jTblCategorias.getValueAt(
                            jTblCategorias.getSelectedRow(), 0)));
            jTFNombreCategoria.setText(String.valueOf(
                    jTblCategorias.getValueAt(
                            jTblCategorias.getSelectedRow(), 1)));
        }
    }
    
    // Metodo para llamar el metodo de actualizar tabla
    private void editar() throws SQLException {
        actualizarCategoria();
        poblarTablaCategorias();
        habilitarBotones(true, false, false, true);
        limpiarTextField();
        filtrarCategorias();
    }
    
    // Metodo para eliminar
    private void eliminarCategoria() {
        try{
                CDCategoriaProducto CDCP = new CDCategoriaProducto();
                CLCategoriaProducto CLCP = new CLCategoriaProducto();
                CLCP.setNombreCategoria(jTFNombreCategoria.getText().trim());
                CDCP.eliminarCategoria(CLCP);
                
                JOptionPane.showMessageDialog(null, "Registro eliminado correctamente", 
                        "Sistema Facturación", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al eliminar el registro"
                        + ex);
                jTFNombreCategoria.requestFocus();
            }
    }
    
    private void eliminar() throws SQLException {
        
        int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de que "
                + "desea eliminar el registro?", "control credito", JOptionPane.YES_NO_OPTION);
        
        if (resp == JOptionPane.YES_OPTION) {
            try {
                eliminarCategoria ();
                poblarTablaCategorias();
                habilitarBotones(true, false, false, true);
                limpiarTextField();
                filtrarCategorias();
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error" + ex);
                jTFNombreCategoria.requestFocus();
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblCategorias = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTFIDCategoria = new javax.swing.JTextField();
        jTFNombreCategoria = new javax.swing.JTextField();
        jBtnGuardar = new javax.swing.JButton();
        jBtnEditar = new javax.swing.JButton();
        jBtnEliminar = new javax.swing.JButton();
        jBtnLimpiar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Geston de Categorias de Productos");

        jTblCategorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id Categoria", "Nombre Categoria"
            }
        ));
        jTblCategorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblCategoriasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTblCategorias);

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID Categoria");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nombre Categoria");

        jTFIDCategoria.setEnabled(false);

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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFIDCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFNombreCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(22, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jBtnGuardar)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBtnLimpiar)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jBtnEditar)
                                .addGap(18, 18, 18)
                                .addComponent(jBtnEliminar)))
                        .addGap(22, 22, 22))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTFIDCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jTFNombreCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnGuardar)
                    .addComponent(jBtnEditar)
                    .addComponent(jBtnEliminar))
                .addGap(18, 18, 18)
                .addComponent(jBtnLimpiar)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTblCategoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblCategoriasMouseClicked
        filaSeleccionada();
        habilitarBotones(false, true, true, true);
    }//GEN-LAST:event_jTblCategoriasMouseClicked

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnEditar;
    private javax.swing.JButton jBtnEliminar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JButton jBtnLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFIDCategoria;
    private javax.swing.JTextField jTFNombreCategoria;
    private javax.swing.JTable jTblCategorias;
    // End of variables declaration//GEN-END:variables
}
