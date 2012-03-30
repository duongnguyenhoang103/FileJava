/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ExtensionEnterprisePanel.java
 *
 * Created on Dec 21, 2011, 4:35:59 PM
 */
package vn.com.hkt.pilot.sb43.ui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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
import vn.com.hkt.basic.api.IProductBN;
import vn.com.hkt.basic.api.ISystemSotfwareBN;
import vn.com.hkt.pilot.entities.Product;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnableTable;
import vn.com.hkt.pilot.enterprise.viewer.api.IGetObject;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.identity.presentation.api.IUserInterface;
import vn.com.hkt.pilot.product.viewer.api.HelpTutorialProduct;
import vn.com.hkt.pilot.product.viewer.api.IProductExtCreater;
import vn.com.hkt.pilot.sb43.Installer;
import vn.com.hkt.pilot.sb43.entity.ProductExt_W52;
import vn.com.hkt.pilot.sb43.product.extW48.dao.ProductExt_W52BN;
import vn.com.hkt.pilot.toobar.api.IResetFontSize;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;
import vn.com.hkt.ui.main.ui.api.creater.ISaveExtention;

/**
 *
 * @author longnt
 */
@ServiceProvider(service = IProductExtCreater.class)
public class SB43_W52_Panel extends javax.swing.JPanel implements IProductExtCreater,
        ISaveExtention, IEnableTable, IResetFontSize, IGetObject, IUserInterface {

    private int idProduct = 0;
    private IProductBN productBN;
    private ProductExt_W52BN dao;
    private int size;
    private String font;
    private ISystemSotfwareBN sotfwareBN;
    private List<SystemSoftware> listS;
    /**
     * Creates new form ExtensionEnterprisePanel
     */
    public SB43_W52_Panel() {
        initComponents();
        dao = new ProductExt_W52BN();
        productBN = Lookup.getDefault().lookup(IProductBN.class);
        sotfwareBN = Lookup.getDefault().lookup(ISystemSotfwareBN.class);
        listS = new ArrayList<SystemSoftware>();
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());

        tableSB43_W52.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableSB43_W52.getColumnModel().getColumn(0).setMaxWidth(100);
        tableSB43_W52.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableSB43_W52.getColumnModel().getColumn(2).setMaxWidth(100);
        tableSB43_W52.setRowSelectionAllowed(true);
        tableSB43_W52.setColumnSelectionAllowed(false);
        tableSB43_W52.setSelectionBackground(new Color(192, 210, 224));
        tableSB43_W52.setForeground(color);
        StripedTableCellRenderer.installInColumn(tableSB43_W52, colorL, null, colorD, null);
        tableSB43_W52.setTableHeader(null);

        jScrollPane1.setViewportBorder(null);

        panelTong.removeAll();
        panelTong.setBackground(new Color(242, 241, 240));
        panelTong.setLayout(new GridLayout());
        panelTong.add(tableSB43_W52);
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(jPanel1, BorderLayout.CENTER);
        this.setBackground(new Color(242, 241, 240));
        JLabel label = new JLabel("      Thông tin phân tích");
        label.setBackground(new Color(242, 241, 240));
        label.setFont((new Font(" ", Font.BOLD, 14)));
        label.setPreferredSize(new Dimension(WIDTH, 30));
        this.add(label, BorderLayout.NORTH);

        tableSB43_W52.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                tableSB43_W52tMousePressed(e);
            }
        });
    }

    private void tableSB43_W52tMousePressed(MouseEvent evt) {
        double k = 0;
        SB43_W52Tutorial sB43_W52Tutorial = new SB43_W52Tutorial();
        String[] str1 = {sB43_W52Tutorial.getTongLuongXuat().getText(), sB43_W52Tutorial.getTongTienXuat().getText(),
            sB43_W52Tutorial.getTongLoiNhuan().getText(), sB43_W52Tutorial.getDanhGia().getText()};
        String[] str2 = {sB43_W52Tutorial.getTongLuongNhap().getText(), sB43_W52Tutorial.getTongTienNhap().getText(),
            sB43_W52Tutorial.getTySuatLoiNhuan().getText(), ""};
        for (int i = 0; i < str1.length; i++) {
            if (tableSB43_W52.getSelectedRow() == i && tableSB43_W52.getSelectedColumn() == 1) {
                k = (double) (4 + i * 0.1 + 0.01);
                k = Math.round(k * 1000) * 1.0 / 1000;
                addFormEditID(k, str1[i]);
            }
            if (tableSB43_W52.getSelectedRow() == i && tableSB43_W52.getSelectedColumn() == 3) {
                k = (double) (4 + i * 0.1 + 0.03);
                k = Math.round(k * 1000) * 1.0 / 1000;
                addFormEditID(k, str2[i]);

            }
        }
    }

    public JTable getTableSB43_W52() {
        return this.tableSB43_W52;
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
        tableSB43_W52 = new javax.swing.JTable();

        jPanel1.setPreferredSize(new java.awt.Dimension(833, 522));

        panelTong.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelTong.setPreferredSize(new java.awt.Dimension(827, 161));

        tableSB43_W52.setBackground(new java.awt.Color(242, 241, 240));
        tableSB43_W52.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{
                {"Tổng lượng suất", 0 , "Tổng lượng nhập", 0},
                {"Tổng tiền suất", 0, "Tổng tiền nhập", 0},
                {"Tổng lợi nhuận", 0, "Tỷ suất lợi nhuận", 0},
                {"Đánh giá", " ", " ", " "}

            },
            new String[]{
                "", "", "", ""
            }) {

                boolean[] canEdit = new boolean[]{
                    false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });
            tableSB43_W52.setIntercellSpacing(new java.awt.Dimension(0, 0));
            tableSB43_W52.setRowHeight(26);
            tableSB43_W52.setShowHorizontalLines(false);
            tableSB43_W52.setShowVerticalLines(false);
            jScrollPane1.setViewportView(tableSB43_W52);

            javax.swing.GroupLayout panelTongLayout = new javax.swing.GroupLayout(panelTong);
            panelTong.setLayout(panelTongLayout);
            panelTongLayout.setHorizontalGroup(
                panelTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 829, Short.MAX_VALUE)
            );
            panelTongLayout.setVerticalGroup(
                panelTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelTongLayout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                    .addGap(92, 92, 92))
            );

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelTong, javax.swing.GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(panelTong, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(372, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 845, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
            );
        }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelTong;
    private javax.swing.JTable tableSB43_W52;
    // End of variables declaration//GEN-END:variables

    @Override
    public JPanel getProductExtCreater() {
        return this;
    }

    @Override
    public Lookup getProductCreaterLookup() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString() {
        return "Thông tin phân tích";
    }

    @Override
    public double getLevelNumber() {
        return 4.3;
    }

    public void resetSelectTable(double d) throws IOException {
        if (d != this.getLevelNumber()) {
            this.tableSB43_W52.clearSelection();
        }
    }

    @Override
    public void reset() {
        idProduct = 0;
        tableSB43_W52.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {"Tổng lượng suất", 0, "Tổng lượng nhập", 0},
                    {"Tổng tiền suất", 0, "Tổng tiền nhập", 0},
                    {"Tổng lợi nhuận", 0, "Tỷ suất lợi nhuận", 0},
                    {"Đánh giá", " ", " ", " "}
                },
                new String[]{
                    "", "", "", ""
                }) {

            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        resetColorWord();
        resetFont();
        resetSize();
        resetColorRowTable();
    }

    // Liên thông font cỡ chữ, màu
    @Override
    public void resetFont() {
        listS = sotfwareBN.selectAll();
        size = tableSB43_W52.getFont().getSize();
        font = listS.get(0).getFont().getFontName();
        tableSB43_W52.setFont(new Font(font, 0, size));
    }

    @Override
    public void resetColorRowTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(tableSB43_W52, colorL, null, colorD, null);
        tableSB43_W52.repaint();
    }

    @Override
    public void resetSize() {
        listS = sotfwareBN.selectAll();
        font = tableSB43_W52.getFont().getFontName();
        size = listS.get(0).getSizeWord();
        tableSB43_W52.setFont(new Font(font, 0, size));

    }

    @Override
    public void resetColorWord() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        tableSB43_W52.setForeground(color);
        tableSB43_W52.repaint();
    }

    @Override
    public void resetColorTitle() {
//        listS = sotfwareBN.selectAll();
//        Color color = new Color(listS.get(0).getColorTitle().getRed(), listS.get(0).getColorTitle().getGreen(), listS.get(0).getColorTitle().getBlue());
//        tableSB43_W52.getTableHeader().setForeground(color);
//        tableSB43_W52.repaint();
    }

    @Override
    public void resetColorMouse() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorMouseClick().getRed(), listS.get(0).getColorMouseClick().getGreen(), listS.get(0).getColorMouseClick().getBlue());
        tableSB43_W52.setSelectionBackground(color);
        tableSB43_W52.repaint();
    }

    @Override
    public void getObject(String id) {
        Product product = productBN.getByObjectId(id);
        try {
            if (product != null) {
                ProductExt_W52 bean = dao.getByObjectId(String.valueOf(product.getId()));
                Object obj1[] = {bean.getTotalNumberExport(), bean.getTotalVauleExport(), bean.getTotalProfit(), bean.getDescription()};
                Object obj2[] = {bean.getTotalNumberImport(), bean.getTotalVauleImport(), bean.getPercentProfit(), ""};

                for (int i = 0; i < tableSB43_W52.getRowCount(); i++) {
                    tableSB43_W52.setValueAt(obj1[i], i, 1);
                    tableSB43_W52.setValueAt(obj2[i], i, 3);
                }
                resetColorTitle();
                resetColorWord();
                resetFont();
                resetColorRowTable();
            }
        } catch (Exception ex) {
        }
    }

    @Override
    public String getUserInterfaceName() {
        return getClass().getSimpleName();
    }

    @Override
    public String getUserInterfaceDescription() {
        return "Giao diện thống kê kinh doanh của sản phẩm";
    }

    @Override
    public String getModuleName() {
        return Installer.MODULE_NAME;
    }

    public void addFormEditID(double i, String str) {
        Collection<? extends HelpTutorialProduct> allSave = Lookup.getDefault().lookupAll(HelpTutorialProduct.class);
        for (HelpTutorialProduct editCookie : allSave) {
            editCookie.getTutorial(i, str);
        }
    }

    @Override
    public void enableTable(boolean ok) throws IOException {
        tableSB43_W52.setEnabled(ok);
    }

    @Override
    public List<JTable> getTables() {
        List<JTable> lt = new ArrayList<JTable>();
        lt.add(tableSB43_W52);
        return lt;
    }

    @Override
    public void setEntity(IEntity entity) {
        idProduct = entity.getId();
    }

    @Override
    public IEntity save() {
        if (idProduct == 0) {
            return null;
        }
        ProductExt_W52 bean = dao.getByObjectId(String.valueOf(idProduct));
        if (bean == null) {
            bean = new ProductExt_W52();
        }

        bean.setProductIdActual(idProduct);
        bean.setTotalNumberImport(0);
        bean.setTotalNumberExport(0);
        bean.setTotalVauleImport(0);
        bean.setTotalVauleExport(0);
        bean.setTotalProfit(0);
        bean.setPercentProfit(0);
        bean.setDescription("");
        dao.update(bean);
        return bean;
    }
}
