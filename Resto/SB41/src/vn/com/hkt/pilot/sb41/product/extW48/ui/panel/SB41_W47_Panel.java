/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ExtensionEnterprisePanel.java
 *
 * Created on Dec 21, 2011, 4:35:59 PM
 */
package vn.com.hkt.pilot.sb41.product.extW48.ui.panel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.api.IEnterpriseBN;
import vn.com.hkt.basic.api.IPersonBN;
import vn.com.hkt.basic.api.IProductBN;
import vn.com.hkt.basic.api.ISystemSotfwareBN;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnableTable;
import vn.com.hkt.pilot.enterprise.viewer.api.IGetObject;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Person;
import vn.com.hkt.pilot.entities.Product;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.product.viewer.api.HelpTutorialProduct;
import vn.com.hkt.pilot.identity.presentation.api.IUserInterface;
import vn.com.hkt.pilot.product.viewer.api.IProductExtCreater;
import vn.com.hkt.pilot.sb41.Installer;
import vn.com.hkt.pilot.sb41.product.extW48.dao.ProductExt_W47BN;
import vn.com.hkt.pilot.sb41.product.extW48.entity.ProductExt_W47;
import vn.com.hkt.pilot.toobar.api.IResetFontSize;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;
import vn.com.hkt.ui.main.ui.api.creater.ISaveExtention;

/**
 *
 * @author longnt
 */
@ServiceProvider(service = IProductExtCreater.class)
public class SB41_W47_Panel extends javax.swing.JPanel implements IProductExtCreater,
        ISaveExtention, IEnableTable, IResetFontSize, IUserInterface,
        IGetObject {

    private ExtProductW47Cell cell;
    private ProductExt_W47BN dao;
    private int size;
    private String font;
    private ISystemSotfwareBN sotfwareBN;
    private List<SystemSoftware> listS;
    private int idProduct = 0;

    /**
     * Creates new form ExtensionEnterprisePanel
     */
    public SB41_W47_Panel() {
        initComponents();
        dao = new ProductExt_W47BN();
        sotfwareBN = Lookup.getDefault().lookup(ISystemSotfwareBN.class);
        cell = new ExtProductW47Cell();
        listS = new ArrayList<SystemSoftware>();
        listS = sotfwareBN.selectAll();

        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());

        tableSB41_W47.getColumnModel().getColumn(0).setCellEditor(cell);
        tableSB41_W47.getColumnModel().getColumn(1).setCellEditor(cell);
        tableSB41_W47.getColumnModel().getColumn(2).setCellEditor(cell);
        TableCell tableCell = new TableCell();
        tableSB41_W47.getColumnModel().getColumn(0).setCellRenderer(tableCell);
        tableSB41_W47.getColumnModel().getColumn(1).setCellRenderer(tableCell);
        tableSB41_W47.getColumnModel().getColumn(2).setCellRenderer(tableCell);

        tableSB41_W47.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableSB41_W47.getColumnModel().getColumn(0).setMaxWidth(100);
        tableSB41_W47.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableSB41_W47.getColumnModel().getColumn(2).setMaxWidth(100);
        tableSB41_W47.setRowSelectionAllowed(true);
        tableSB41_W47.setColumnSelectionAllowed(false);
        tableSB41_W47.setTableHeader(null);
        tableSB41_W47.setForeground(color);
        StripedTableCellRenderer.installInColumn(tableSB41_W47, colorL, null, colorD, null);

        jScrollPane1.setViewportBorder(null);

        panelTong.removeAll();
        panelTong.setBackground(new Color(242, 241, 240));
        panelTong.setLayout(new GridLayout());
        panelTong.add(tableSB41_W47);
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(jPanel1, BorderLayout.CENTER);
        this.setBackground(new Color(242, 241, 240));
        JLabel label = new JLabel("      Thông tin chi tiết");
        label.setBackground(new Color(242, 241, 240));
        label.setFont((new Font(" ", Font.BOLD, 14)));
        label.setPreferredSize(new Dimension(WIDTH, 30));
        this.add(label, BorderLayout.NORTH);

        tableSB41_W47.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                tableExtensionProductMousePressed(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                tableExtensionProductMousePressed(e);
            }
        });

    }

    @Override
    public String toString() {
        return "Thông tin chi tiết";
    }

    public JTable getTableExtensionE() {
        return this.tableSB41_W47;
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
        tableSB41_W47 = new javax.swing.JTable();

        panelTong.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelTong.setPreferredSize(new java.awt.Dimension(827, 161));

        tableSB41_W47.setBackground(new java.awt.Color(242, 241, 240));
        tableSB41_W47.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{
                {"Nhà cung cấp", " ", " "},
                {" ", " ", " "},
                {" ", " ", " "},
                {" ", " ", " "},
                {" ", " ", " "}
            },
            new String[]{
                "", "", ""
            }) {

                public boolean isCellEditable(int rowIndex, int columnIndex) {

                    if ((rowIndex == 0 && columnIndex == 0) || (rowIndex >= 3)) {
                        return false;
                    } else {
                        return true;
                    }
                }
            });
            tableSB41_W47.setIntercellSpacing(new java.awt.Dimension(0, 0));
            tableSB41_W47.setRowHeight(26);
            tableSB41_W47.setShowHorizontalLines(false);
            tableSB41_W47.setShowVerticalLines(false);
            jScrollPane1.setViewportView(tableSB41_W47);

            javax.swing.GroupLayout panelTongLayout = new javax.swing.GroupLayout(panelTong);
            panelTong.setLayout(panelTongLayout);
            panelTongLayout.setHorizontalGroup(
                panelTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
            );
            panelTongLayout.setVerticalGroup(
                panelTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelTongLayout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                    .addGap(96, 96, 96))
            );

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addComponent(panelTong, javax.swing.GroupLayout.DEFAULT_SIZE, 833, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(panelTong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap(361, Short.MAX_VALUE))
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelTong;
    private javax.swing.JTable tableSB41_W47;
    // End of variables declaration//GEN-END:variables

    private void tableExtensionProductMousePressed(MouseEvent evt) {
        SB41_W47Tutorial sB41_W47Tutorial = new SB41_W47Tutorial();
        if (tableSB41_W47.getSelectedColumn() == 0) {
            if (tableSB41_W47.getSelectedRow() == 1) {
                tableSB41_W47.repaint();
            }
            if (tableSB41_W47.getSelectedRow() == 2) {

                tableSB41_W47.repaint();
            }
        }
        tableSB41_W47.repaint();
        if (tableSB41_W47.getSelectedRow() == 2 && tableSB41_W47.getSelectedColumn() == 1) {
            addFormEditID(3.21, sB41_W47Tutorial.getVangLai().getText());
        }
    }

    @Override
    public JPanel getProductExtCreater() {
        return this;
    }

    @Override
    public Lookup getProductCreaterLookup() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double getLevelNumber() {
        return 4.17;
    }

    @Override
    public void reset() {
        cell.reset();
        idProduct = 0;
        tableSB41_W47.setValueAt("", 0, 1);
        tableSB41_W47.setValueAt("", 1, 1);
        tableSB41_W47.setValueAt("", 2, 1);

        tableSB41_W47.getColumnModel().getColumn(0).setCellEditor(cell);
        tableSB41_W47.getColumnModel().getColumn(1).setCellEditor(cell);
        TableCell tableCell = new TableCell();
        tableSB41_W47.getColumnModel().getColumn(0).setCellRenderer(tableCell);
        tableSB41_W47.getColumnModel().getColumn(1).setCellRenderer(tableCell);
        tableSB41_W47.getColumnModel().getColumn(2).setCellRenderer(tableCell);
        tableSB41_W47.repaint();
    }

    @Override
    public IEntity save() {
        if (idProduct == 0) {
            return null;
        }
        ProductExt_W47 bean = this.getProductW47();
        dao.update(bean);
        return bean;
    }

    public ProductExt_W47 getProductW47() {
        String parnershipType;
        String nameCustommer;
        ProductExt_W47 bean = dao.getByObjectId(String.valueOf(idProduct));
        if (bean == null) {
            bean = new ProductExt_W47();
        }

        bean.setProductIdActual(idProduct);
        boolean isCustomer = cell.isIsCustommer();
        // Neu la doi tac
        if (isCustomer == true) {
            bean.setSupplierType(ProductExt_W47.SUPPLIER_TYPE_PARNER);
            // Nếu đối tác là tổ chức - công ty
            if (tableSB41_W47.getValueAt(0, 1).toString().contains("Tổ Chức")) {
                parnershipType = ProductExt_W47.PARNERSHIP_TYPE_ENTERPRISE;
                if (tableSB41_W47.getValueAt(1, 1) != null && tableSB41_W47.getValueAt(1, 1).toString().trim().length() != 0) {
                    Enterprise e = (Enterprise) tableSB41_W47.getValueAt(1, 1);
                    bean.setEnterpriseIdActual(e.getId());
                } else {
                    bean.setEnterpriseIdActual(0);
                }
                bean.setPersonIdActual(0);
                bean.setParnershipType(parnershipType);

            } else if (tableSB41_W47.getValueAt(0, 1).toString().contains("Cá Nhân")) {
                // Nếu đối tác là cá nhân
                parnershipType = ProductExt_W47.PARNERSHIP_TYPE_PERSON;
                if (tableSB41_W47.getValueAt(1, 1) != null && tableSB41_W47.getValueAt(1, 1).toString().trim().length() != 0) {
                    Person p = (Person) tableSB41_W47.getValueAt(1, 1);
                    bean.setPersonIdActual(p.getId());
                } else {
                    bean.setPersonIdActual(0);
                }
                bean.setParnershipType(parnershipType);
                bean.setEnterpriseIdActual(0);
            }

            bean.setSupplierIrregularName(" ");

        } else { // Neu la vang lai
            bean.setSupplierType(ProductExt_W47.FIELD_SUPPLIER_IRREGULAR_NAME);
            nameCustommer = tableSB41_W47.getValueAt(2, 1).toString();
            bean.setSupplierIrregularName(nameCustommer);
            bean.setPersonIdActual(0);
            bean.setEnterpriseIdActual(0);
        }

        return bean;
    }

    // Liên thông font cỡ chữ, màu
    @Override
    public void resetFont() {
        listS = sotfwareBN.selectAll();
        size = tableSB41_W47.getFont().getSize();
        font = listS.get(0).getFont().getFontName();
        tableSB41_W47.setFont(new Font(font, 0, size));
    }

    @Override
    public void resetColorRowTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(tableSB41_W47, colorL, null, colorD, null);
        tableSB41_W47.repaint();
    }

    @Override
    public void resetSize() {
        listS = sotfwareBN.selectAll();
        font = tableSB41_W47.getFont().getFontName();
        size = listS.get(0).getSizeWord();
        tableSB41_W47.setFont(new Font(font, 0, size));

    }

    @Override
    public void resetColorWord() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        tableSB41_W47.setForeground(color);
        tableSB41_W47.repaint();
    }

    @Override
    public void resetColorTitle() {
//        listS = sotfwareBN.selectAll();
//        Color color = new Color(listS.get(0).getColorTitle().getRed(), listS.get(0).getColorTitle().getGreen(), listS.get(0).getColorTitle().getBlue());
//        tableSB41_W47.getTableHeader().setForeground(color);
//        tableSB41_W47.repaint();
    }

    @Override
    public void resetColorMouse() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorMouseClick().getRed(), listS.get(0).getColorMouseClick().getGreen(), listS.get(0).getColorMouseClick().getBlue());
        tableSB41_W47.setSelectionBackground(color);
        tableSB41_W47.repaint();
    }

    @Override
    public String getUserInterfaceName() {
        return getClass().getSimpleName();
    }

    @Override
    public String getUserInterfaceDescription() {
        return "Giao diện nhà cung cấp sản phẩm";
    }

    @Override
    public String getModuleName() {
        return Installer.MODULE_NAME;
    }

    @Override
    public void getObject(String id) {
        IEnterpriseBN enterpriseBN = Lookup.getDefault().lookup(IEnterpriseBN.class);
        IPersonBN personBN = Lookup.getDefault().lookup(IPersonBN.class);
        ProductExt_W47BN productExt_W47BN = new ProductExt_W47BN();
        IProductBN productBN = Lookup.getDefault().lookup(IProductBN.class);
        Product product = productBN.getByObjectId(id);
        if (product != null) {
            ProductExt_W47 bean = productExt_W47BN.select(ProductExt_W47.PRODUCT_ID_ACTUAL, String.valueOf(product.getId())).get(0);
            int idEnterprise = bean.getEnterpriseIdActual();
            int idPerson = bean.getPersonIdActual();
            // Neu co enterprise
            if (idEnterprise != 0) {
                Enterprise e = enterpriseBN.getById(idEnterprise);
                tableSB41_W47.setValueAt("Tổ Chức", 0, 1);
                tableSB41_W47.setValueAt(e, 1, 1);
                cell.getRdb1().setSelected(true);
                tableSB41_W47.repaint();
            } else if (idPerson != 0) {
                Person p = personBN.getById(idPerson);
                tableSB41_W47.setValueAt("Cá Nhân", 0, 1);
                tableSB41_W47.setValueAt(p, 1, 1);
                cell.getRdb1().setSelected(true);
                tableSB41_W47.repaint();
            } else {
                tableSB41_W47.setValueAt(" ", 0, 1);
                tableSB41_W47.setValueAt(bean.getSupplierIrregularName(), 2, 1);
                cell.getRdb2().setSelected(true);
            }

        }
    }

    @Override
    public void enableTable(boolean ok) throws IOException {
        tableSB41_W47.setEnabled(ok);
    }

    @Override
    public void setEntity(IEntity entity) {
        idProduct = entity.getId();
    }

    public class TableCell extends JLabel implements TableCellRenderer {

        public TableCell() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            listS = sotfwareBN.selectAll();
            Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
            Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
            if (column == 0) {
                if (row == 0) {
                    this.setText("Khách hàng");
                    this.setBackground(colorL);
                    return this;
                }
                if (row == 1) {
                    cell.getRdb1().setBackground(colorD);
                    return cell.getRdb1();
                }
                if (row == 2) {
                    cell.getRdb2().setBackground(colorL);
                    return cell.getRdb2();
                }
                if (row == 3) {
                    this.setBackground(colorD);
                    this.setText(" ");
                    return this;
                }
                if (row == 4) {
                    this.setBackground(colorL);
                    this.setText(" ");
                    return this;
                }
            }
            if (column == 1) {
                if (row == 0) {

                    this.setBackground(colorL);
                    this.setText(table.getValueAt(0, 1).toString());
                    return this;
                }
                if (row == 1) {
                    this.setBackground(colorD);
                    if (table.getValueAt(1, 1) != null) {
                        this.setText(table.getValueAt(1, 1).toString());
                    }
                    return this;
                }
                if (row == 2) {
                    this.setBackground(colorL);
                    this.setText(table.getValueAt(2, 1).toString());
                    return this;
                }
                if (row == 3) {
                    this.setBackground(colorD);
                    this.setText(" ");
                    return this;
                }
                if (row == 4) {
                    this.setBackground(colorL);
                    this.setText(" ");
                    return this;
                }
            }
            if (column == 2) {
                if (row == 0) {

                    this.setBackground(colorL);
                    this.setText(" ");
                    return this;
                }
                if (row == 1) {
                    this.setBackground(colorD);
                    this.setText(" ");
                    return this;
                }
                if (row == 2) {
                    this.setBackground(colorL);
                    this.setText(" ");
                    return this;
                }
                if (row == 3) {
                    this.setBackground(colorD);
                    this.setText(" ");
                    return this;
                }
                if (row == 4) {
                    this.setBackground(colorL);
                    this.setText(" ");
                    return this;
                }
            }
            this.setBackground(colorD);
            this.setText(" ");
            return this;
        }
    }

    public void addFormEditID(double i, String str) {
        Collection<? extends HelpTutorialProduct> allSave = Lookup.getDefault().lookupAll(HelpTutorialProduct.class);
        for (HelpTutorialProduct editCookie : allSave) {
            editCookie.getTutorial(i, str);
        }
    }

    @Override
    public List<JTable> getTables() {
        List<JTable> lt = new ArrayList<JTable>();
        lt.add(tableSB41_W47);
        return lt;
    }
}