/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EnterprisePanel.java
 *
 * Created on Nov 23, 2011, 1:56:14 PM
 */
package vn.com.hkt.erm.enterprise.ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.*;
import java.util.List;
import vn.com.hkt.basic.Installer;
import vn.com.hkt.basic.toolbar.BasicToolbarManager;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.entities.system.dao.SystemSotfwareDAO;
import vn.com.hkt.pilot.identity.presentation.api.IUserInterface;

/**
 *
 * @author longnt
 */
public class EnterprisePanel extends javax.swing.JPanel implements IUserInterface {

    private byte[] dataImages;
    private ImageIcon imageChoise;
    private List<SystemSoftware> listS = new ArrayList<SystemSoftware>();

    public byte[] getDataImages() {
        return dataImages;
    }

    @Override
    public String toString() {
        return "Thông tin doanh nghiệp";
    }
    /**
     * Creates new form EnterprisePanel
     */
    private EnterpriseCellEditor enterpriseCell;
    private SystemSotfwareDAO sotfwareBN = new SystemSotfwareDAO();

    public EnterprisePanel() {
        initComponents();
        listS = sotfwareBN.selectAll();
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        tabelEnterprise.setForeground(color);
        enterpriseCell = new EnterpriseCellEditor();
        tabelEnterprise.getColumnModel().getColumn(1).setCellEditor(enterpriseCell);
        EnterpriseCellRedenrer.installInColumn(tabelEnterprise, colorL, null, colorD, null);
        tabelEnterprise.getColumnModel().getColumn(0).setPreferredWidth(100);
        tabelEnterprise.getColumnModel().getColumn(0).setMaxWidth(100);
        tabelEnterprise.setRowSelectionAllowed(true);
        tabelEnterprise.setColumnSelectionAllowed(false);
        jScrollPane1.setViewportBorder(null);
        jScrollPane1.setBorder(null);
        tabelEnterprise.setTableHeader(null);
        this.removeAll();
        this.setLayout(new BorderLayout());
        panelTable.removeAll();
        panelTable.setLayout(new GridLayout());
        panelTable.add(tabelEnterprise);
        panelTP.setLayout(new GridLayout());
        panelTP.add(panlePhoto);
        panelTP.add(panelTable);
        this.add(panelTong, BorderLayout.CENTER);
        JLabel label = new JLabel("       Thông tin doanh nghiệp");
        label.setFont((new Font(" ", Font.BOLD, 14)));
        label.setForeground(new Color(0, 51, 102));
        label.setPreferredSize(new Dimension(WIDTH, 30));
        // label.setHorizontalAlignment(50);
        this.add(label, BorderLayout.NORTH);
        this.setAutoscrolls(true);
        // this.setBorder(BorderFactory.createLoweredBevelBorder());
//        try {
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
//            SwingUtilities.updateComponentTreeUI(this);
//        } catch (ClassNotFoundException ex) {
//            Exceptions.printStackTrace(ex);
//        } catch (InstantiationException ex) {
//            Exceptions.printStackTrace(ex);
//        } catch (IllegalAccessException ex) {
//            Exceptions.printStackTrace(ex);
//        } catch (UnsupportedLookAndFeelException ex) {
//            Exceptions.printStackTrace(ex);
//        }
    }

    public void reset() {
        Enterprise enterprise1 = BasicToolbarManager.getBasicToolbar().getEnterprise();
        if (enterprise1 != null) {
            enterpriseCell.loadPerson(enterprise1);
        }
        enterpriseCell.reset();
        enterpriseCell.loadCbo();
        lbImage.setText("");
        tabelEnterprise.getModel().setValueAt(null, 0, 1);
        tabelEnterprise.getModel().setValueAt(null, 1, 1);
        tabelEnterprise.getModel().setValueAt(null, 2, 1);
        tabelEnterprise.getModel().setValueAt(null, 3, 1);
        tabelEnterprise.getModel().setValueAt(null, 4, 1);
        tabelEnterprise.setRowHeight(26);
        dataImages = null;
        showImage();
    }

    public EnterpriseCellEditor getEnterpriseCell() {
        return enterpriseCell;
    }

    public JTable getTabelEnterprise() {
        return tabelEnterprise;
    }

    public JLabel getLabelEnterprise() {
        return lbImage;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTong = new javax.swing.JPanel();
        panelTP = new javax.swing.JPanel();
        panlePhoto = new javax.swing.JPanel();
        lbImage = new javax.swing.JLabel();
        panelTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelEnterprise = new javax.swing.JTable();

        panelTP.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelTP.setPreferredSize(new java.awt.Dimension(827, 165));

        lbImage.setText(org.openide.util.NbBundle.getMessage(EnterprisePanel.class, "EnterprisePanel.lbImage.text")); // NOI18N
        lbImage.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        lbImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbImageMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panlePhotoLayout = new javax.swing.GroupLayout(panlePhoto);
        panlePhoto.setLayout(panlePhotoLayout);
        panlePhotoLayout.setHorizontalGroup(
            panlePhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panlePhotoLayout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
        );
        panlePhotoLayout.setVerticalGroup(
            panlePhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        tabelEnterprise.setBackground(new java.awt.Color(242, 241, 240));
        tabelEnterprise.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Tên công ty", null},
                {"Mã công ty", null},
                {"Công ty mẹ", null},
                {"Đại diện pháp luật", null},
                {"Slogan", null}
            },
            new String [] {
                "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelEnterprise.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tabelEnterprise.setRowHeight(26);
        tabelEnterprise.setShowHorizontalLines(false);
        tabelEnterprise.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tabelEnterprise);

        javax.swing.GroupLayout panelTableLayout = new javax.swing.GroupLayout(panelTable);
        panelTable.setLayout(panelTableLayout);
        panelTableLayout.setHorizontalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
        );
        panelTableLayout.setVerticalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout panelTPLayout = new javax.swing.GroupLayout(panelTP);
        panelTP.setLayout(panelTPLayout);
        panelTPLayout.setHorizontalGroup(
            panelTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTPLayout.createSequentialGroup()
                .addComponent(panlePhoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTPLayout.setVerticalGroup(
            panelTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panlePhoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout panelTongLayout = new javax.swing.GroupLayout(panelTong);
        panelTong.setLayout(panelTongLayout);
        panelTongLayout.setHorizontalGroup(
            panelTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTP, javax.swing.GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE))
        );
        panelTongLayout.setVerticalGroup(
            panelTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTongLayout.createSequentialGroup()
                .addComponent(panelTP, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                .addGap(363, 363, 363))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lbImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbImageMouseClicked
        if (evt.getClickCount() < 2 || !tabelEnterprise.isEnabled()) {
            return;
        }
        FileDialog fd = new FileDialog(new JDialog(), "Chọn ảnh");
        fd.setVisible(true);
        String d = fd.getDirectory();
        String f = fd.getFile();
        if (d == null || f == null) {
            return;
        }
        String fileName = d + f;
        try {
            ImageIcon image = new ImageIcon(fileName);
            Image img = image.getImage();
            Image newimg = img.getScaledInstance(lbImage.getWidth(), lbImage.getHeight(), java.awt.Image.SCALE_SMOOTH);
            imageChoise = new ImageIcon(newimg);
        } catch (Exception e) {
            imageChoise = new ImageIcon();
        }
        loadImage();
    }//GEN-LAST:event_lbImageMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbImage;
    private javax.swing.JPanel panelTP;
    private javax.swing.JPanel panelTable;
    private javax.swing.JPanel panelTong;
    private javax.swing.JPanel panlePhoto;
    private javax.swing.JTable tabelEnterprise;
    // End of variables declaration//GEN-END:variables

    private byte[] convertImage2Byte(Image image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = bi.createGraphics();
            g2d.drawImage(image, 0, 0, null);
            ImageIO.write(bi, "JPEG", baos);
        } catch (IOException ex) {
        }
        return baos.toByteArray();
    }

    private void loadImage() {
        dataImages = convertImage2Byte(imageChoise.getImage());
        showImage();
    }
    private Icon icon;

    public void showImage() {
        try {
            if (dataImages == null) {
                lbImage.setText("Anh");
                lbImage.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
                icon = new ImageIcon();
                lbImage.setIcon(icon);
            } else {
                lbImage.setEnabled(true);
                lbImage.setText("");
                lbImage.setBorder(null);
                lbImage.setIcon(imageChoise);
            }
        } catch (Exception e) {
            lbImage.setText("Anh");
            lbImage.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
            icon = new ImageIcon();
            lbImage.setIcon(icon);
        }
    }

    public void setDataImages(byte[] dataImages) {
        this.dataImages = dataImages;
        if (dataImages != null) {
            this.imageChoise = new ImageIcon(dataImages);
        } else {
            this.imageChoise = new ImageIcon();
        }
    }

    @Override
    public String getUserInterfaceName() {
        return getClass().getSimpleName();
    }

    @Override
    public String getUserInterfaceDescription() {
        return "Giao diện thông tin cơ bản doanh nghiệp";
    }

    @Override
    public String getModuleName() {
        return Installer.MODULE_NAME;
    }
}
