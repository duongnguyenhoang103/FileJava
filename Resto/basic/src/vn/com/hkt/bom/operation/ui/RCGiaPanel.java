/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ExtensionEnterprisePanel.java
 *
 * Created on Dec 21, 2011, 4:35:59 PM
 */
package vn.com.hkt.bom.operation.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.api.IOperationDetailBN;
import vn.com.hkt.basic.Installer;
import vn.com.hkt.basic.api.IProductBN;
import vn.com.hkt.basic.toolbar.BasicToolbarManager;
import vn.com.hkt.bom.operation.dao.OperationBN;
import vn.com.hkt.pilot.enterprise.viewer.api.ResetCookie;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Operation;
import vn.com.hkt.pilot.entities.OperationDetail;
import vn.com.hkt.pilot.entities.Product;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.entities.system.dao.SystemSotfwareDAO;
import vn.com.hkt.pilot.identity.presentation.api.IUserInterface;
import vn.com.hkt.pilot.operation.viewer.api.IOperationCreater;
import vn.com.hkt.pilot.toobar.api.IResetFontSize;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;
import vn.com.hkt.pilot.operation.viewer.api.GetValueOperation;
import vn.com.hkt.pilot.operation.viewer.api.HelpTutorialOperation;

/**
 *
 * @author longnt
 */
@ServiceProvider(service = IOperationCreater.class)
public class RCGiaPanel extends javax.swing.JPanel implements IOperationCreater,
        IResetFontSize, ResetCookie, ItemListener, IUserInterface {

    private int size;
    private String font;
    private SystemSotfwareDAO sotfwareBN;
    private List<SystemSoftware> listS = null;
    private int i = 2;
    private DefaultTableModel modelDiaChi;
    private JComboBox cboIdProduct, cboProduct;
    private DefaultComboBoxModel modelIdProduct, modelProduct;
    private IProductBN productBN = Lookup.getDefault().lookup(IProductBN.class);
    private JTextField txtQuantity, txtUnitPrice, txtPromotion;
    private static RCGiaPanel rCGiaPanel;

    public static RCGiaPanel getRCGiaPanel() {
        if (rCGiaPanel == null) {
            rCGiaPanel = new RCGiaPanel();
        }
        return rCGiaPanel;
    }

    /**
     * Creates new form ExtensionEnterprisePanel
     */
    public RCGiaPanel() {
        initComponents();
        sotfwareBN = new SystemSotfwareDAO();
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());

        tableGia.setRowSelectionAllowed(true);
        tableGia.setColumnSelectionAllowed(false);
        tableGia.setSelectionBackground(new Color(192, 210, 224));
        tableGia.setForeground(color);
        StripedTableCellRenderer.installInColumn(tableGia, colorL, null, colorD, null);

        panelTong.removeAll();
        panelTong.setBackground(new Color(242, 241, 240));
        panelTong.setLayout(new GridLayout());
        panelTong.add(jScrollPane1);

        tableGia.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                tableMousePressed(e);
            }
        });

        modelIdProduct = new DefaultComboBoxModel();
        modelIdProduct.addElement(" ");
        modelProduct = new DefaultComboBoxModel();
        modelProduct.addElement(" ");

        List<Product> list = new ArrayList<Product>();
        Enterprise e = BasicToolbarManager.getBasicToolbar().getEnterprise();
        if (e == null) {
            list = productBN.selectAll();
        } else {
            list = productBN.select(Product.FIELD_ENTERPRISE_ID_ACTUAL, String.valueOf(e.getId()));
        }
        for (Product bean : list) {
            modelProduct.addElement(bean);
            modelIdProduct.addElement(bean.getProductId());
        }
        cboIdProduct = new JComboBox(modelIdProduct);
        cboProduct = new JComboBox(modelProduct);
        txtQuantity = new JTextField();
        txtUnitPrice = new JTextField();
        txtPromotion = new JTextField();

        tableGia.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(cboIdProduct));
        tableGia.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(cboProduct));
        tableGia.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(txtQuantity));
        tableGia.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(txtUnitPrice));
        tableGia.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(txtPromotion));
        cboIdProduct.addItemListener(this);
        cboProduct.addItemListener(this);

        txtUnitPrice.addKeyListener(new java.awt.event.KeyAdapter() {

            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUnitPricePressed(evt);
            }
        });
        txtPromotion.addKeyListener(new java.awt.event.KeyAdapter() {

            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPromotionPressed(evt);
            }
        });
        txtQuantity.addKeyListener(new java.awt.event.KeyAdapter() {

            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQuantityPressed(evt);
            }
        });
    }

    public JTable getTableGia() {
        return this.tableGia;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelTong = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableGia = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(845, 132));

        jPanel1.setPreferredSize(new java.awt.Dimension(845, 132));

        panelTong.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelTong.setPreferredSize(new java.awt.Dimension(827, 132));

        tableGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SPDV", "Tên SPDV", "Số lượng", "Đơn giá", "Chiết khấu/Khuyến mại", "Thành tiền", "Ghi chú"
            }
        ));
        tableGia.setRowHeight(26);
        jScrollPane1.setViewportView(tableGia);
        tableGia.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(RCGiaPanel.class, "RCGiaPanel.tableGia.columnModel.title0")); // NOI18N
        tableGia.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(RCGiaPanel.class, "RCGiaPanel.tableGia.columnModel.title1")); // NOI18N
        tableGia.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(RCGiaPanel.class, "RCGiaPanel.tableGia.columnModel.title2")); // NOI18N
        tableGia.getColumnModel().getColumn(3).setHeaderValue(org.openide.util.NbBundle.getMessage(RCGiaPanel.class, "RCGiaPanel.tableGia.columnModel.title3")); // NOI18N
        tableGia.getColumnModel().getColumn(4).setHeaderValue(org.openide.util.NbBundle.getMessage(RCGiaPanel.class, "RCGiaPanel.tableGia.columnModel.title4")); // NOI18N
        tableGia.getColumnModel().getColumn(5).setHeaderValue(org.openide.util.NbBundle.getMessage(RCGiaPanel.class, "RCGiaPanel.tableGia.columnModel.title5")); // NOI18N
        tableGia.getColumnModel().getColumn(6).setHeaderValue(org.openide.util.NbBundle.getMessage(RCGiaPanel.class, "RCGiaPanel.tableGia.columnModel.title6")); // NOI18N
        tableGia.getColumnModel().getColumn(7).setHeaderValue(org.openide.util.NbBundle.getMessage(RCGiaPanel.class, "RCGiaPanel.tableGia.columnModel.title7")); // NOI18N

        javax.swing.GroupLayout panelTongLayout = new javax.swing.GroupLayout(panelTong);
        panelTong.setLayout(panelTongLayout);
        panelTongLayout.setHorizontalGroup(
            panelTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTongLayout.createSequentialGroup()
                .addGap(184, 184, 184)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(191, Short.MAX_VALUE))
        );
        panelTongLayout.setVerticalGroup(
            panelTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTongLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(296, 296, 296))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTong, javax.swing.GroupLayout.DEFAULT_SIZE, 833, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTong, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelTong;
    private javax.swing.JTable tableGia;
    // End of variables declaration//GEN-END:variables

    @Override
    public double getLevelNumber() {
        return 5.02;
    }

    @Override
    public JPanel getOperationCreater() {
        return RCGiaPanel.getRCGiaPanel();
    }

    @Override
    public Lookup getOperationLookup() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Liên thông font cỡ chữ, màu
    @Override
    public void resetFont() {
        listS = sotfwareBN.selectAll();
        size = tableGia.getFont().getSize();
        font = listS.get(0).getFont().getFontName();
        tableGia.setFont(new Font(font, 0, size));
    }

    @Override
    public void resetColorRowTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(tableGia, colorL, null, colorD, null);
        tableGia.repaint();

    }

    @Override
    public void resetSize() {
        listS = sotfwareBN.selectAll();
        font = tableGia.getFont().getFontName();
        size = listS.get(0).getSizeWord();
        tableGia.setFont(new Font(font, 0, size));
//        for (int i = 0; i < tableGia.getRowCount(); i++) {
//            tableGia.setRowHeight(i, size + 10);
//        }
    }

    @Override
    public void resetColorWord() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        tableGia.setForeground(color);
        tableGia.repaint();
    }

    @Override
    public void resetColorTitle() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorTitle().getRed(), listS.get(0).getColorTitle().getGreen(), listS.get(0).getColorTitle().getBlue());
        tableGia.getTableHeader().setForeground(color);
        tableGia.repaint();
    }

    @Override
    public void resetColorMouse() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorMouseClick().getRed(), listS.get(0).getColorMouseClick().getGreen(), listS.get(0).getColorMouseClick().getBlue());
        tableGia.setSelectionBackground(color);
        tableGia.repaint();
    }

    private void tableMousePressed(MouseEvent e) {
        RCGiaTutorial giaTutorial = new RCGiaTutorial();
        if (tableGia.getSelectedRow() == i) {
            modelDiaChi = (DefaultTableModel) tableGia.getModel();
            i++;
            Object[] rows1 = {" ", " ", " ", " ", " ", " ", " ", " "};
            modelDiaChi.addRow(rows1);
            tableGia.repaint();
        }
        double k = 0;
        String[] str = {"", giaTutorial.getMaSanPhamDichVu().getText(), giaTutorial.getTenSanPhamDichVu().getText(), giaTutorial.getSoLuong().getText(),
            giaTutorial.getDonGia().getText(), giaTutorial.getChietKhau().getText(), giaTutorial.getThanhTien().getText(), giaTutorial.getGhiChu().getText()};
        for (int i = 1; i < str.length; i++) {

            if (tableGia.getSelectedColumn() == i) {
                k = (double) (1 + 0.01 * i);
                k = Math.round(k * 1000) * 1.0 / 1000;
                addFormEditID(k, str[i]);

            }
        }

    }

    @Override
    public void resetCookie() throws IOException {
        reset();
    }

    private void reset() {
        modelDiaChi = (DefaultTableModel) tableGia.getModel();
        for (int j = i; j >= 0; j--) {
            modelDiaChi.removeRow(j);
        }
        for (int j = 0; j < 3; j++) {
            Object[] rows1 = {" ", " ", " ", " ", " ", " ", " ", " "};
            modelDiaChi.addRow(rows1);
        }
        i = 2;
    }

    @Override
    public void resetSelectTable(double d) throws IOException {
        tableGia.clearSelection();
    }

    public void Save(int id) {
        OperationBN operationBN = new OperationBN();
        IOperationDetailBN operationDetailBN = Lookup.getDefault().lookup(IOperationDetailBN.class);
        Operation operation = operationBN.getById(id);
        int row = tableGia.getModel().getRowCount();

        operationBN.update(operation);
        for (int j = 0; j < row; j++) {
            if (tableGia.getValueAt(j, 2) != null) {
                Product product = (Product) tableGia.getValueAt(j, 2);
                OperationDetail operationDetail = new OperationDetail();
                operationDetail.setOperationIdActual(operation.getId());
                operationDetail.setProductIdActual(product.getId());
                operationDetailBN.insert(operationDetail);
                reset();
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JComboBox comboBox = (JComboBox) e.getSource();
        if (comboBox == cboIdProduct) {
            if (cboIdProduct.getSelectedIndex() != -1 && cboIdProduct.getSelectedItem().toString().trim().length() != 0) {
                int row = tableGia.getSelectedRow();
                Product product = productBN.getByObjectId(cboIdProduct.getSelectedItem().toString());
                tableGia.setValueAt(product, row, 2);
                txtQuantity.setText("1");
                tableGia.setValueAt(1, row, 3);
                tableGia.setValueAt(0, row, 5);
                tableGia.setValueAt(0, row, 4);
                tableGia.setValueAt(0, row, 6);
            }
        }
        if (comboBox == cboProduct) {
            if (cboProduct.getSelectedIndex() != -1 && cboProduct.getSelectedItem().toString().trim().length() != 0) {
                int row = tableGia.getSelectedRow();
                Product product = (Product) cboProduct.getSelectedItem();
                tableGia.setValueAt(product.getProductId(), row, 1);
                txtQuantity.setText("1");
                tableGia.setValueAt(1, row, 3);
                tableGia.setValueAt(0, row, 5);
                tableGia.setValueAt(0, row, 4);
                tableGia.setValueAt(0, row, 6);
            }
        }
    }

    private void txtUnitPricePressed(KeyEvent evt) {
        if (evt.getKeyCode() == 10 || evt.getKeyCode() == 9) {
            int sumPrice = 0;
            int a = 1;
            int b = 1;
            int c = 0;
            int row = tableGia.getSelectedRow();
            if (tableGia.getValueAt(row, 5) != null) {
                a = Integer.parseInt(tableGia.getValueAt(row, 3).toString());
                b = Integer.parseInt(txtUnitPrice.getText());
                c = Integer.parseInt(tableGia.getValueAt(row, 5).toString());
            } else {
                a = Integer.parseInt(tableGia.getValueAt(row, 3).toString());
                b = Integer.parseInt(txtUnitPrice.getText());

            }
            sumPrice = a * b - c;
            tableGia.setValueAt(sumPrice, row, 6);
            sumPrice = 0;
            sumPrice();
        }
    }

    private void txtQuantityPressed(KeyEvent evt) {
        if (evt.getKeyCode() == 10 || evt.getKeyCode() == 9) {
            int sumPrice;
            int a = 1;
            int b = 1;
            int c = 0;
            int row = tableGia.getSelectedRow();
            if (tableGia.getValueAt(row, 4) != null) {
                if (tableGia.getValueAt(row, 5) != null) {
                    a = Integer.parseInt(txtQuantity.getText());
                    b = Integer.parseInt(tableGia.getValueAt(row, 4).toString());
                    c = Integer.parseInt(tableGia.getValueAt(row, 5).toString());
                } else {
                    a = Integer.parseInt(txtQuantity.getText());
                    b = Integer.parseInt(tableGia.getValueAt(row, 4).toString());

                }
            }
            sumPrice = a * b - c;
            tableGia.setValueAt(sumPrice, row, 6);
            sumPrice = 0;
            sumPrice();
        }
    }

    private void txtPromotionPressed(KeyEvent evt) {
        if (evt.getKeyCode() == 10 || evt.getKeyCode() == 9) {
            int sumPrice;
            int a = 1;
            int b = 1;
            int c = 0;
            int row = tableGia.getSelectedRow();
            a = Integer.parseInt(tableGia.getValueAt(row, 3).toString());
            b = Integer.parseInt(tableGia.getValueAt(row, 4).toString());
            c = Integer.parseInt(txtPromotion.getText());
            sumPrice = a * b - c;
            tableGia.setValueAt(sumPrice, row, 6);
            sumPrice = 0;
            sumPrice();
        }

    }

    private void sumPrice() {
        int n = tableGia.getModel().getRowCount();
        int sum = 0;
        for (int j = 0; j < n; j++) {
            if (tableGia.getValueAt(j, 6) != null) {
                sum = sum + Integer.parseInt(tableGia.getValueAt(j, 6).toString());
            }
        }

        Collection<? extends GetValueOperation> allSave = Lookup.getDefault().lookupAll(GetValueOperation.class);
        for (GetValueOperation saveCookie : allSave) {
            saveCookie.getValue(sum);
        }
    }

    @Override
    public String getUserInterfaceName() {
        return getClass().getSimpleName();
    }

    @Override
    public String getUserInterfaceDescription() {
        return "Giao diện giá nghiệp vụ kinh doanh";
    }

    @Override
    public String getModuleName() {
        return Installer.MODULE_NAME;
    }

    public void addFormEditID(double k, String str) {
        Collection<? extends HelpTutorialOperation> allSave = Lookup.getDefault().lookupAll(HelpTutorialOperation.class);
        for (HelpTutorialOperation editCookie : allSave) {
            editCookie.getTutorial(i, str);
        }
    }

    @Override
    public List<JTable> getTables() {
        List<JTable> lt = new ArrayList<JTable>();
        lt.add(tableGia);
        return lt;
    }
}