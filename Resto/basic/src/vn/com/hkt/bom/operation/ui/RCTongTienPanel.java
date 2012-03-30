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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.Installer;
import vn.com.hkt.bom.operation.dao.OperationBN;
import vn.com.hkt.pilot.enterprise.viewer.api.ResetCookie;
import vn.com.hkt.pilot.entities.Operation;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.entities.system.dao.SystemSotfwareDAO;
import vn.com.hkt.pilot.identity.presentation.api.IUserInterface;
import vn.com.hkt.pilot.operation.viewer.api.GetValueOperation;
import vn.com.hkt.pilot.operation.viewer.api.HelpTutorialOperation;
import vn.com.hkt.pilot.operation.viewer.api.IGetObjectOperation;
import vn.com.hkt.pilot.operation.viewer.api.IOperationCreater;
import vn.com.hkt.pilot.toobar.api.IResetFontSize;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;

/**
 *
 * @author longnt
 */
@ServiceProvider(service = IOperationCreater.class)
public class RCTongTienPanel extends javax.swing.JPanel implements IOperationCreater,
        IResetFontSize, ResetCookie, GetValueOperation, IUserInterface , IGetObjectOperation {

    /**
     * Creates new form ExtensionEnterprisePanel
     */
    private int size;
    private String font;
    private SystemSotfwareDAO sotfwareBN;
    private List<SystemSoftware> listS = null;
    private RCTongTienCell tongTienCell = new RCTongTienCell();
    private static RCTongTienPanel tongTienPanel;
    private OperationBN operationBN = new OperationBN();

    public static RCTongTienPanel getRCTongTienPanel() {
        if (tongTienPanel == null) {
            tongTienPanel = new RCTongTienPanel();
        }
        return tongTienPanel;
    }

    public RCTongTienPanel() {
        initComponents();
        sotfwareBN = new SystemSotfwareDAO();
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        tableTongTien.setForeground(color);
        tableTongTien.setSelectionBackground(new Color(192, 210, 224));
        StripedTableCellRenderer.installInColumn(tableTongTien, colorL, null, colorD, null);

        tableTongTien.setTableHeader(null);
        tableTongTien.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableTongTien.getColumnModel().getColumn(0).setMaxWidth(100);
        tableTongTien.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableTongTien.getColumnModel().getColumn(2).setMaxWidth(100);
        tableTongTien.setRowSelectionAllowed(true);
        tableTongTien.setColumnSelectionAllowed(false);
        tableTongTien.getColumnModel().getColumn(1).setCellEditor(tongTienCell);
        tableTongTien.getColumnModel().getColumn(3).setCellEditor(tongTienCell);
        panelTong.removeAll();
        panelTong.setBackground(new Color(242, 241, 240));
        panelTong.setLayout(new BorderLayout());

        panelTong.add(tableTongTien, BorderLayout.NORTH);
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(jPanel1, BorderLayout.CENTER);
        this.setBackground(new Color(242, 241, 240));
        JLabel label = new JLabel("  ");
        label.setBackground(new Color(242, 241, 240));
        label.setFont((new Font(" ", Font.BOLD, 14)));
        label.setPreferredSize(new Dimension(WIDTH, 30));
        this.add(label, BorderLayout.NORTH);

        tongTienCell.getTxtChietKhau().addKeyListener(new java.awt.event.KeyAdapter() {

            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPromotionPressed(evt);
            }
        });
        tongTienCell.getTxtGiaTriCK().addKeyListener(new java.awt.event.KeyAdapter() {

            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPromotionPressed(evt);
            }
        });

        tableTongTien.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableMousePressed(evt);
            }
        });
    }

    public RCTongTienCell getTongTienCell() {
        return tongTienCell;
    }

    public JTable getTableTongTien() {
        return tableTongTien;
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTongTien = new javax.swing.JTable();

        panelTong.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelTong.setPreferredSize(new java.awt.Dimension(827, 130));

        tableTongTien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Tổng tiền", " ", "", null},
                {"Chiết khấu", " ", "Giá trị CK", " "},
                {"Loại thuế", " ", " Tiền thuế", " "},
                {"Tổng cộng", " ", "Thanh toán", " "},
                {"Tiền trả lại", " ", "Tiền nợ", " "}
            },
            new String [] {
                "", "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableTongTien.setRowHeight(26);
        tableTongTien.setShowHorizontalLines(false);
        tableTongTien.setShowVerticalLines(false);
        jScrollPane2.setViewportView(tableTongTien);
        tableTongTien.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(RCTongTienPanel.class, "RCTongTienPanel.tableTongTien.columnModel.title0")); // NOI18N
        tableTongTien.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(RCTongTienPanel.class, "RCTongTienPanel.tableTongTien.columnModel.title1")); // NOI18N
        tableTongTien.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(RCTongTienPanel.class, "RCTongTienPanel.tableTongTien.columnModel.title2")); // NOI18N
        tableTongTien.getColumnModel().getColumn(3).setHeaderValue(org.openide.util.NbBundle.getMessage(RCTongTienPanel.class, "RCTongTienPanel.tableTongTien.columnModel.title3")); // NOI18N

        javax.swing.GroupLayout panelTongLayout = new javax.swing.GroupLayout(panelTong);
        panelTong.setLayout(panelTongLayout);
        panelTongLayout.setHorizontalGroup(
            panelTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTongLayout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(249, Short.MAX_VALUE))
        );
        panelTongLayout.setVerticalGroup(
            panelTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTongLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTong, javax.swing.GroupLayout.DEFAULT_SIZE, 821, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panelTong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(452, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelTong;
    private javax.swing.JTable tableTongTien;
    // End of variables declaration//GEN-END:variables

    @Override
    public double getLevelNumber() {
        return 5.01;
    }

    @Override
    public JPanel getOperationCreater() {
        return RCTongTienPanel.getRCTongTienPanel();
    }

    @Override
    public Lookup getOperationLookup() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Liên thông font cỡ chữ, màu
    @Override
    public void resetFont() {
        listS = sotfwareBN.selectAll();
        size = tableTongTien.getFont().getSize();
        font = listS.get(0).getFont().getFontName();

        tableTongTien.setFont(new Font(font, 0, size));
    }

    @Override
    public void resetColorRowTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(tableTongTien, colorL, null, colorD, null);
        tableTongTien.repaint();
    }

    @Override
    public void resetSize() {
        listS = sotfwareBN.selectAll();
        font = tableTongTien.getFont().getFontName();
        size = listS.get(0).getSizeWord();
        tableTongTien.setFont(new Font(font, 0, size));
//        for (int i = 0; i < tableTongTien.getRowCount(); i++) {
//            tableTongTien.setRowHeight(i, size + 10);
//        }
    }

    @Override
    public void resetColorWord() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        tableTongTien.setForeground(color);
        tableTongTien.repaint();
    }

    @Override
    public void resetColorTitle() {
//        listS = sotfwareBN.selectAll();
//        Color color = new Color(listS.get(0).getColorTitle().getRed(), listS.get(0).getColorTitle().getGreen(), listS.get(0).getColorTitle().getBlue());
//        tableTongTien.getTableHeader().setForeground(color);
//        tableTongTien.repaint();
    }

    @Override
    public void resetColorMouse() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorMouseClick().getRed(), listS.get(0).getColorMouseClick().getGreen(), listS.get(0).getColorMouseClick().getBlue());
        tableTongTien.setSelectionBackground(color);
        tableTongTien.repaint();
    }

    @Override
    public void resetCookie() throws IOException {
        reset();
    }

    private void reset() {
        RCTongTienPanel.getRCTongTienPanel().getTableTongTien().setValueAt(0, 0, 1);
        RCTongTienPanel.getRCTongTienPanel().getTableTongTien().setValueAt(0, 1, 1);
        RCTongTienPanel.getRCTongTienPanel().getTableTongTien().setValueAt(0, 1, 3);
        RCTongTienPanel.getRCTongTienPanel().getTableTongTien().setValueAt(" ", 2, 1);
        RCTongTienPanel.getRCTongTienPanel().getTableTongTien().setValueAt(0, 2, 3);
        RCTongTienPanel.getRCTongTienPanel().getTableTongTien().setValueAt(0, 3, 1);
        RCTongTienPanel.getRCTongTienPanel().getTableTongTien().setValueAt(0, 3, 3);
        RCTongTienPanel.getRCTongTienPanel().getTableTongTien().setValueAt(0, 4, 1);
        RCTongTienPanel.getRCTongTienPanel().getTableTongTien().setValueAt(0, 4, 3);
    }

    @Override
    public void resetSelectTable(double d) throws IOException {
        tableTongTien.clearSelection();
    }

    public void Save(int id) {
        OperationBN operationBN = new OperationBN();
        Operation operation = operationBN.getById(id);

        int sumPrice = 0;
        if (tableTongTien.getValueAt(3, 1).toString().trim().length() != 0) {
            sumPrice = Integer.parseInt(tableTongTien.getValueAt(3, 1).toString().trim());
        }

        int saleoffValue = 0;
        if (tableTongTien.getValueAt(1, 1).toString().trim().length() != 0) {
            saleoffValue = Integer.parseInt(tableTongTien.getValueAt(1, 1).toString().trim());
        }

        float typeOfTaxes = 0;
        try {
            typeOfTaxes = Float.parseFloat(tableTongTien.getValueAt(2, 1).toString().trim());
        } catch (NumberFormatException exception) {
            typeOfTaxes = 0;
        }
        int taxesValue = 0;
        if (tableTongTien.getValueAt(2, 3).toString().trim().length() != 0) {
            taxesValue = Integer.parseInt(tableTongTien.getValueAt(2, 3).toString().trim());
        }

        int payment = 0;
        if (tableTongTien.getValueAt(3, 3).toString().trim().length() != 0) {
            payment = Integer.parseInt(tableTongTien.getValueAt(3, 3).toString().trim());
        }
        int changedMoney = 0;
        if (tableTongTien.getValueAt(4, 1).toString().trim().length() != 0) {
            changedMoney = Integer.parseInt(tableTongTien.getValueAt(4, 1).toString().trim());
        }
        int debt = 0;
        if (tableTongTien.getValueAt(4, 3).toString().trim().length() != 0) {
            debt = Integer.parseInt(tableTongTien.getValueAt(4, 3).toString().trim());
        }
        int saleoffRealValue = 0;
        if (tableTongTien.getValueAt(1, 3).toString().trim().length() != 0) {
            saleoffRealValue = Integer.parseInt(tableTongTien.getValueAt(1, 3).toString().trim());
        }

        operation.setDebt(debt);
        operation.setPayment(payment);
        operation.setSaleoffRealValue(saleoffRealValue);
        operation.setTypeOfTaxes(typeOfTaxes);
        operation.setTaxesValue(taxesValue);
        operation.setSumPrice(sumPrice);
        operation.setChangedMoney(changedMoney);
        operation.setSaleoffValue(saleoffValue);
        operationBN.update(operation);
    }

    @Override
    public void getValue(int sum) {
        RCTongTienPanel.getRCTongTienPanel().getTableTongTien().setValueAt(sum, 0, 1);
        RCTongTienPanel.getRCTongTienPanel().getTableTongTien().setValueAt(0, 1, 1);
        RCTongTienPanel.getRCTongTienPanel().getTableTongTien().setValueAt(0, 1, 3);
        RCTongTienPanel.getRCTongTienPanel().getTableTongTien().setValueAt(0, 2, 3);
        RCTongTienPanel.getRCTongTienPanel().getTableTongTien().setValueAt(0, 3, 1);
        RCTongTienPanel.getRCTongTienPanel().getTableTongTien().setValueAt(0, 3, 3);
        RCTongTienPanel.getRCTongTienPanel().getTableTongTien().setValueAt(0, 4, 1);
        RCTongTienPanel.getRCTongTienPanel().getTableTongTien().setValueAt(0, 4, 3);
    }

    private void txtPromotionPressed(KeyEvent evt) {
        if (evt.getKeyCode() == 10 || evt.getKeyCode() == 9) {
            int sumPrice;
            int a = 0;
            int b = 0;
            int c = 0;
            a = Integer.parseInt(tableTongTien.getValueAt(0, 1).toString().trim());

            if (tongTienCell.getTxtChietKhau().getText().trim().length() != 0) {
                b = Integer.parseInt(tongTienCell.getTxtChietKhau().getText());
            } else {
                b = Integer.parseInt(tableTongTien.getValueAt(1, 1).toString().trim());
            }
            if (tongTienCell.getTxtGiaTriCK().getText().trim().length() != 0) {
                c = Integer.parseInt(tongTienCell.getTxtGiaTriCK().getText());
            } else {
                c = Integer.parseInt(tableTongTien.getValueAt(1, 3).toString().trim());
            }

            sumPrice = a - b * c;
            tableTongTien.setValueAt(sumPrice, 3, 1);
            sumPrice = 0;
        }

    }

    private void tableMousePressed(MouseEvent evt) {
        RCTongTienTutorial tongTienTutorial = new RCTongTienTutorial();
        JTable table = (JTable) evt.getSource();
        if (table == tableTongTien) {
            if (tableTongTien.getSelectedRow() == 2 && tableTongTien.getSelectedColumn() == 1) {
                addFormEditID(5, "");
            }

            double k = 0;
            String[] str1 = {tongTienTutorial.getTongTien().getText(), tongTienTutorial.getChietKhau().getText(), tongTienTutorial.getLoaiThue().getText(),
                tongTienTutorial.getTongCong().getText(), tongTienTutorial.getTienTraLai().getText()};
            String[] str2 = {"", tongTienTutorial.getGiaTriCK().getText(), tongTienTutorial.getTienThue().getText(),
                tongTienTutorial.getThanhToan().getText(), tongTienTutorial.getTienNo().getText()};
            for (int i = 0; i < str1.length; i++) {
                if (table.getSelectedRow() == i && table.getSelectedColumn() == 1) {
                    k = (double) (4 + 0.1 * i + 0.01);
                    k = Math.round(k * 1000) * 1.0 / 1000;
                    addFormEditID(k, str1[i]);

                }
                if (table.getSelectedRow() == i && table.getSelectedColumn() == 3) {
                    k = (double) (4 + 0.1 * i + 0.01 * 3);
                    k = Math.round(k * 1000) * 1.0 / 1000;
                    addFormEditID(k, str2[i]);
                }
            }
        }
    }

    private void addFormEditID(double i, String str) {
        Collection<? extends HelpTutorialOperation> allSave = Lookup.getDefault().lookupAll(HelpTutorialOperation.class);
        for (HelpTutorialOperation editCookie : allSave) {
            editCookie.getTutorial(i, str);
        }
    }

    @Override
    public String getUserInterfaceName() {
        return getClass().getSimpleName();
    }

    @Override
    public String getUserInterfaceDescription() {
        return "Giao diện tổng tiền nghiệp vụ kinh doanh";
    }

    @Override
    public String getModuleName() {
        return Installer.MODULE_NAME;
    }

    @Override
    public List<JTable> getTables() {
        List<JTable> lt = new ArrayList<JTable>();
        lt.add(tableTongTien);
        return lt;
    }

    @Override
    public void getObject(int id) {
        reset();
        Operation bean = operationBN.getById(id);
        if (bean != null){
            tableTongTien.setValueAt(bean.getSumPrice(), 0, 1);
            tableTongTien.setValueAt(bean.getSaleoffValue(), 1, 1);
            tableTongTien.setValueAt(0, 1, 3);
            tableTongTien.setValueAt(" ", 2, 1);
            tableTongTien.setValueAt(bean.getTaxesValue(), 2, 3);
            
            tableTongTien.setValueAt(bean.getSaleoffRealValue(), 3, 1);
            tableTongTien.setValueAt(bean.getPayment(), 3, 3);
        }
    }
}
