/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ExtensionEnterprisePanel.java
 *
 * Created on Dec 21, 2011, 4:35:59 PM
 */
package vn.com.hkt.pilot.sb51.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.api.ISystemSotfwareBN;
import vn.com.hkt.pilot.entities.AccountNumber;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.identity.presentation.api.IUserInterface;
import vn.com.hkt.pilot.operation.viewer.api.HelpTutorialOperation;
import vn.com.hkt.pilot.operation.viewer.api.IOperationExtCreater;
import vn.com.hkt.pilot.sb51.Installer;
import vn.com.hkt.pilot.sb51.entity.BillType;
import vn.com.hkt.pilot.sb51.entity.OperationPayment;
import vn.com.hkt.pilot.sb51.entity.OperationStatus;
import vn.com.hkt.pilot.sb51.entity.PaymentMethod;
import vn.com.hkt.pilot.sb51.spi.OperationPaymentBN;
import vn.com.hkt.pilot.toobar.api.IResetFontSize;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;
import vn.com.hkt.ui.main.ui.api.creater.ISaveExtention;

/**
 *
 * @author longnt
 */
@ServiceProvider(service = IOperationExtCreater.class)
public class SB51Panel extends javax.swing.JPanel implements IOperationExtCreater,
        IResetFontSize, ISaveExtention, IUserInterface {

    private int size;
    private String font;
    private ISystemSotfwareBN sotfwareBN = Lookup.getDefault().lookup(ISystemSotfwareBN.class);
    private List<SystemSoftware> listS = new ArrayList<SystemSoftware>();
    private SB51Cell cell;
    private int idoperation = 0;

    /**
     * Creates new form ExtensionEnterprisePanel
     */
    public SB51Panel() {
        initComponents();
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        cell = new SB51Cell();
        tableSB51.setTableHeader(null);
        tableSB51.getColumnModel().getColumn(1).setCellEditor(cell);
        tableSB51.getColumnModel().getColumn(3).setCellEditor(cell);
        tableSB51.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableSB51.getColumnModel().getColumn(0).setMaxWidth(100);
        tableSB51.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableSB51.getColumnModel().getColumn(2).setMaxWidth(100);
        tableSB51.setRowSelectionAllowed(true);
        tableSB51.setColumnSelectionAllowed(false);
        tableSB51.setSelectionBackground(new Color(192, 210, 224));
        tableSB51.setForeground(color);
        StripedTableCellRenderer.installInColumn(tableSB51, colorL, null, colorD, null);

        panelTong.removeAll();
        panelTong.setBackground(new Color(242, 241, 240));
        panelTong.setLayout(new BorderLayout());

        panelTong.add(tableSB51, BorderLayout.NORTH);
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(jPanel1, BorderLayout.CENTER);
        this.setBackground(new Color(242, 241, 240));
        JLabel label = new JLabel("             Thông tin thanh toán");
        label.setBackground(new Color(242, 241, 240));
        label.setFont((new Font(" ", Font.BOLD, 14)));
        label.setPreferredSize(new Dimension(WIDTH, 30));
        this.add(label, BorderLayout.NORTH);

        tableSB51.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableMousePressed(evt);
            }
        });
    }

    public JTable getTableSB51() {
        return this.tableSB51;
    }

    public void Reset() {
        cell.reset();
        tableSB51.setValueAt("", 0, 1);
        tableSB51.setValueAt("", 0, 3);
        tableSB51.setValueAt("", 1, 1);
        tableSB51.setValueAt("", 1, 3);
        tableSB51.setValueAt("", 2, 1);
        tableSB51.setValueAt("", 2, 3);
        tableSB51.setValueAt("", 1, 3);
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
        tableSB51 = new javax.swing.JTable();

        panelTong.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelTong.setPreferredSize(new java.awt.Dimension(827, 165));

        tableSB51.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{
                {"Tình trạng", null, "", null},
                {"Loại hóa đơn", null, "Hình thức", null},
                {"Số tài khoản", null, "Số hóa đơn", null},
                {"Ghi chú", null, "Tài khoản đối ứng", null},
                {" ", " ", " ", " "}
            },
            new String[]{
                "", "", "", ""
            }));
            tableSB51.setRowHeight(26);
            tableSB51.setShowHorizontalLines(false);
            tableSB51.setShowVerticalLines(false);
            jScrollPane2.setViewportView(tableSB51);

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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
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
                    .addContainerGap(417, Short.MAX_VALUE))
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
    private javax.swing.JTable tableSB51;
    // End of variables declaration//GEN-END:variables

    @Override
    public String toString() {
        return "Thông tin thanh toán";
    }

    @Override
    public double getLevelNumber() {
        return 5.1;
    }

    @Override
    public JPanel getOperationExtCreater() {
        return this;
    }

    @Override
    public Lookup getOperationExtCreaterLookup() {
        cell.loadCbo();
        return null;
    }

    // Liên thông font cỡ chữ, màu
    @Override
    public void resetFont() {
        listS = sotfwareBN.selectAll();
        size = tableSB51.getFont().getSize();
        font = listS.get(0).getFont().getFontName();
        tableSB51.setFont(new Font(font, 0, size));
    }

    @Override
    public void resetColorRowTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(tableSB51, colorL, null, colorD, null);
        tableSB51.repaint();
    }

    @Override
    public void resetSize() {
        listS = sotfwareBN.selectAll();
        font = tableSB51.getFont().getFontName();
        size = listS.get(0).getSizeWord();
        tableSB51.setFont(new Font(font, 0, size));

    }

    @Override
    public void resetColorWord() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        tableSB51.setForeground(color);
        tableSB51.repaint();
    }

    @Override
    public void resetColorTitle() {
//        listS = sotfwareBN.selectAll();
//        Color color = new Color(listS.get(0).getColorTitle().getRed(), listS.get(0).getColorTitle().getGreen(), listS.get(0).getColorTitle().getBlue());
//        tableSB51.getTableHeader().setForeground(color);
//        tableSB51.repaint();
    }

    @Override
    public void resetColorMouse() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorMouseClick().getRed(), listS.get(0).getColorMouseClick().getGreen(), listS.get(0).getColorMouseClick().getBlue());
        tableSB51.setSelectionBackground(color);
        tableSB51.repaint();
    }

    @Override
    public void reset() {
        Reset();
    }

    @Override
    public IEntity save() {
        OperationPaymentBN operationPaymentDAO = new OperationPaymentBN();
        OperationPayment beanOP = operationPaymentDAO.getByObjectId(String.valueOf(idoperation));
        if (beanOP == null) {
            beanOP = new OperationPayment();
        }
        beanOP.setOperationIdActual(idoperation);
        if (tableSB51.getValueAt(0, 1) != null && tableSB51.getValueAt(0, 1).toString().trim().length() != 0) {
            OperationStatus beanStatus = (OperationStatus) tableSB51.getValueAt(0, 1);
            beanOP.setOperationStatusIdActual(beanStatus.getId());
        }

        if (tableSB51.getValueAt(1, 1) != null && tableSB51.getValueAt(1, 1).toString().trim().length() != 0) {
            BillType beanBillType = (BillType) tableSB51.getValueAt(1, 1);
            beanOP.setBillTypeIdActual(beanBillType.getId());
        }

        if (tableSB51.getValueAt(1, 3) != null && tableSB51.getValueAt(1, 3).toString().trim().length() != 0) {
            PaymentMethod beanPaymentMethod = (PaymentMethod) tableSB51.getValueAt(1, 3);
            beanOP.setPaymentMethodIdActual(beanPaymentMethod.getId());
        }

        if (tableSB51.getValueAt(2, 1) != null && tableSB51.getValueAt(2, 1).toString().trim().length() != 0) {
            AccountNumber beanAccountNo = (AccountNumber) tableSB51.getValueAt(2, 1);
            beanOP.setAccount(beanAccountNo.getId());
        }

        if (tableSB51.getValueAt(2, 3) != null) {
            beanOP.setBillCode(tableSB51.getValueAt(2, 3).toString());
        }

        if (tableSB51.getValueAt(3, 1) != null) {
            beanOP.setDescription(tableSB51.getValueAt(3, 1).toString());
        }

        if (tableSB51.getValueAt(3, 3) != null) {
            beanOP.setCorrespondingAccount(Integer.parseInt(tableSB51.getValueAt(3, 3).toString()));

        }
        operationPaymentDAO.insert(beanOP);

        return beanOP;
    }

    private void tableMousePressed(MouseEvent evt) {
        SB51Tutorial sB51Tutorial = new SB51Tutorial();
        JTable table = (JTable) evt.getSource();
        if (table == tableSB51) {
            if (tableSB51.getSelectedRow() == 1 && tableSB51.getSelectedColumn() == 3) {
                addFormEditID(3.13, "");
            }
            if (tableSB51.getSelectedRow() == 1 && tableSB51.getSelectedColumn() == 1) {
                addFormEditID(3.11, "");
            }
            if (tableSB51.getSelectedRow() == 0 && tableSB51.getSelectedColumn() == 1) {
                addFormEditID(3.01, "");
            }
            if (tableSB51.getSelectedRow() == 2 && tableSB51.getSelectedColumn() == 1) {
                addFormEditID(3.21, "");
            }
            if (tableSB51.getSelectedRow() == 3 && tableSB51.getSelectedColumn() == 1) {
                addFormEditID(3.31, sB51Tutorial.getGhiChu().getText());
            }
            if (tableSB51.getSelectedRow() == 2 && tableSB51.getSelectedColumn() == 3) {
                addFormEditID(3.23, sB51Tutorial.getSoHoaDon().getText());
            }
            if (tableSB51.getSelectedRow() == 3 && tableSB51.getSelectedColumn() == 3) {
                addFormEditID(3.33, sB51Tutorial.getTaiKhoanDoiUng().getText());
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
        return "Giao diện danh sách thanh toán";
    }

    @Override
    public String getModuleName() {
        return Installer.MODULE_NAME;
    }

    @Override
    public List<JTable> getTables() {
        List<JTable> lt = new ArrayList<JTable>();
        lt.add(tableSB51);
        return lt;
    }

    @Override
    public void setEntity(IEntity entity) {
        idoperation = entity.getId();
    }
}
