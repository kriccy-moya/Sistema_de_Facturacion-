/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.curlp.capaPresentacion;

import com.curlp.capaDatos.CDProveedores;
import com.curlp.capaLogica.CLCiudad;
import com.curlp.capaLogica.CLProveedores;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USERS
 */
public class jPnlProveedor extends javax.swing.JPanel {

    /**
     * Creates new form jPnlProveedor
     */
    public jPnlProveedor() {
        initComponents();
    }
    
    // Metodo para limpiar los datos de la tabla
    private void limpiarTabla() {
        DefaultTableModel dtm = (DefaultTableModel) jTblProveedor.getModel();
        
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    }
    
    // Metodo para poblar de datos la tabla
    private void poblarTablaProveedor() throws SQLException {
        limpiarTabla();
        
        CDProveedores prov = new CDProveedores();
        List<CLProveedores> miLista = prov.obtenerListaProveedores();
        DefaultTableModel temp = (DefaultTableModel) jTblProveedor.getModel();
        
        miLista.stream().map((CLProveedores clp) -> {
            Object[] fila = new Object[9];
            fila[0] = clp.getIdProveedor();
            fila[1] = clp.getNombreProveedor();
            fila[2] = clp.getNumTelefono();
            fila[3] = clp.getNombreContacto();
            fila[4] = clp.getDireccionProveedor();
            fila[5] = clp.getRTNProveedor();
            fila[6] = clp.getEstadoProveedor();
            fila[7] = clp.getCiudad();
            return fila;
            
        }).forEachOrdered(temp::addRow);
    }
    
    // Validar si se han agregado datos
    private boolean validarJPnlProveedor() {
        boolean estado;
        
        if (jTFNombreProveedor.getText().trim().isEmpty() && 
                jFTFNumProveedor.getText().isEmpty() &&
                jTFContacto.getText().trim().isEmpty() &&
                JTADireccion.getText().trim().isEmpty() &&
                jBtnGpEstado.getSelection() == null &&
                jFTFRTN.getText().isEmpty() &&
                jTFCiudad.getText().isEmpty()) {
            
            JOptionPane.showMessageDialog(null, "Ingrese todos los campos", 
                    "Envoice System", JOptionPane.WARNING_MESSAGE);
            jTFNombreProveedor.requestFocus();
            estado = false;
            
        } else if (jTFNombreProveedor.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                    "Debe ingresar el nombre del proveedor", 
                    "Envoice System", JOptionPane.WARNING_MESSAGE);
            jTFNombreProveedor.requestFocus();
            estado = false;
            
        } else if (jFTFNumProveedor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "Debe ingresar un número de telefono válido", 
                "Envoice System", 
                JOptionPane.WARNING_MESSAGE);
            jFTFNumProveedor.requestFocus();
            estado = false;
            
        } else if (jFTFNumProveedor.getText().contains("#")) {
            JOptionPane.showMessageDialog(null, 
                "El número de telefono debe contener 8 digitos", 
                "Envoice System", 
                JOptionPane.WARNING_MESSAGE);
            jFTFNumProveedor.requestFocus();
            estado = false;
            
        } else if (JTADireccion.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "Debe ingresar la dirección", 
                "Envoice System", 
                JOptionPane.WARNING_MESSAGE);
            JTADireccion.requestFocus();
            estado = false;
            
        } else if (jTFContacto.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "Debe asignar a un contacto válido", 
                "Envoice System", 
                JOptionPane.WARNING_MESSAGE);
            jTFContacto.requestFocus();
            estado = false;
            
        } else if (jBtnGpEstado.getSelection() == null) {
            JOptionPane.showMessageDialog(null, 
                "Debe seleccionar un estado", 
                "Envoice System", 
                JOptionPane.WARNING_MESSAGE);
            estado = false;
            
        } else if (jFTFRTN.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "Debe ingresar un RTN válido", 
                "Envoice System", 
                JOptionPane.WARNING_MESSAGE);
            jFTFRTN.requestFocus();
            estado = false;
            
        } else if (jFTFRTN.getText().contains("#")) {
            JOptionPane.showMessageDialog(null, 
                "Su RTN debe contener 16 digitos", 
                "Envoice System", 
                JOptionPane.WARNING_MESSAGE);
            jFTFRTN.requestFocus();
            estado = false;
            
        } else if (jTFCiudad.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "Debe seleccionar una ciudad.", 
                "Envoice System", 
                JOptionPane.WARNING_MESSAGE);
            estado = false;
            
        } else {
            estado = true;
        }
        
        return estado;
    }

    
    // Metodo para agregar proveedores
    private void insertarProveedores() {
        if (validarJPnlProveedor()) {
            try{
                CDProveedores dProv = new CDProveedores();
                CLProveedores lProv = new CLProveedores();
                CLCiudad lCiu = new CLCiudad();
                
                lProv.setNombreProveedor(jTFNombreProveedor.getText().trim());
                lProv.setNumTelefono(jFTFNumProveedor.getText());
                lProv.setNombreContacto(jTFContacto.getText().trim());
                lProv.setDireccionProveedor(JTADireccion.getText().trim());
                lProv.setRTNProveedor(jFTFRTN.getText());
                lCiu.setNombreCiudad(jTFCiudad.getText());
                lProv.setCiudad(lCiu);
                
                dProv.insertarProveedor(lProv);
                
                
                JOptionPane.showMessageDialog(null, "Proveedor almacenado correctamente", 
                        "Envoice System", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al almacenar el registro"
                        + ex);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBtnGpEstado = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTFCiudad = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTFNombreProveedor = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTFIDProveedor = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTADireccion = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jRBtnActivo = new javax.swing.JRadioButton();
        jRBtnInactivo = new javax.swing.JRadioButton();
        jFTFNumProveedor = new javax.swing.JFormattedTextField();
        jFTFRTN = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jTFContacto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblProveedor = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jBtnAgregar = new javax.swing.JButton();
        jBtnActualizar = new javax.swing.JButton();
        jBtnEliminar = new javax.swing.JButton();
        jBtnModificar = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de la compra"));

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel2.setText("Telefono");

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel4.setText("Contacto");

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel5.setText("Dirección");

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel9.setText("Nombre del proveedor");

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel10.setText("ID");

        jTFIDProveedor.setEnabled(false);

        JTADireccion.setColumns(20);
        JTADireccion.setRows(5);
        jScrollPane2.setViewportView(JTADireccion);

        jLabel11.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel11.setText("RTN");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estado", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jBtnGpEstado.add(jRBtnActivo);
        jRBtnActivo.setText("Activo");

        jBtnGpEstado.add(jRBtnInactivo);
        jRBtnInactivo.setText("Inactivo");
        jRBtnInactivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBtnInactivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRBtnActivo)
                .addGap(18, 18, 18)
                .addComponent(jRBtnInactivo)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRBtnActivo)
                    .addComponent(jRBtnInactivo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        try {
            jFTFNumProveedor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            jFTFRTN.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####-######")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel6.setText("Ciudad");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(jFTFRTN, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jTFCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jTFContacto))
                                .addGap(36, 36, 36)))
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jTFIDProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jTFNombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jFTFNumProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addGap(5, 5, 5)
                                    .addComponent(jTFNombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addGap(5, 5, 5)
                                    .addComponent(jTFIDProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jFTFNumProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addGap(12, 12, 12)
                                .addComponent(jTFContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFTFRTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setText("SFa");

        jTblProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Telefono", "Contacto", "Dirección", "RTN", "Estado", "Ciudad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTblProveedor);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones"));

        jBtnAgregar.setBackground(new java.awt.Color(0, 102, 255));
        jBtnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnAgregar.setText("Agregar");

        jBtnActualizar.setBackground(new java.awt.Color(0, 102, 255));
        jBtnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnActualizar.setText("Actualizar");

        jBtnEliminar.setBackground(new java.awt.Color(0, 102, 255));
        jBtnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnEliminar.setText("Eliminar");

        jBtnModificar.setBackground(new java.awt.Color(0, 102, 255));
        jBtnModificar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnModificar.setText("Modificar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jBtnAgregar)
                .addGap(56, 56, 56)
                .addComponent(jBtnActualizar)
                .addGap(67, 67, 67)
                .addComponent(jBtnModificar)
                .addGap(68, 68, 68)
                .addComponent(jBtnEliminar)
                .addContainerGap(182, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBtnModificar)
                        .addComponent(jBtnEliminar))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBtnAgregar)
                        .addComponent(jBtnActualizar)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(267, 267, 267))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jRBtnInactivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBtnInactivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBtnInactivoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea JTADireccion;
    private javax.swing.JButton jBtnActualizar;
    private javax.swing.JButton jBtnAgregar;
    private javax.swing.JButton jBtnEliminar;
    private javax.swing.ButtonGroup jBtnGpEstado;
    private javax.swing.JButton jBtnModificar;
    private javax.swing.JFormattedTextField jFTFNumProveedor;
    private javax.swing.JFormattedTextField jFTFRTN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRBtnActivo;
    private javax.swing.JRadioButton jRBtnInactivo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTFCiudad;
    private javax.swing.JTextField jTFContacto;
    private javax.swing.JTextField jTFIDProveedor;
    private javax.swing.JTextField jTFNombreProveedor;
    private javax.swing.JTable jTblProveedor;
    // End of variables declaration//GEN-END:variables
}
