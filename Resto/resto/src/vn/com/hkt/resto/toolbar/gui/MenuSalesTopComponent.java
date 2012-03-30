/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.resto.toolbar.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Lookup;
import vn.com.hkt.basic.api.IProductBN;
import vn.com.hkt.pilot.entities.Product;
import vn.com.hkt.resto.panel.ProductDesign;
import vn.com.hkt.resto.panel.TreeProductDesign;
import vn.com.hkt.resto.toolbar.gui.model.ProductCell;
import vn.com.hkt.resto.toolbar.gui.model.ProductTableModel;
//import vn.com.hkt.resto.panel.TreeProductDesign;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//vn.com.hkt.resto.toolbar.gui//MenuSales//EN",
autostore = false)
@TopComponent.Description(preferredID = "MenuSalesTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = true)
@ActionID(category = "Window", id = "vn.com.hkt.resto.toolbar.gui.MenuSalesTopComponent")
@ActionReference(path = "Menu/Restaurant" /*, position = 333 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_MenuSalesAction",
preferredID = "MenuSalesTopComponent")
public final class MenuSalesTopComponent extends TopComponent {

    private JButton btnchoise = null;
    private List<JButton> buttons = new ArrayList<JButton>();
    private JPanel panelchoise = null;
    private List<JPanel> panels = new ArrayList<JPanel>();
    private IProductBN productBN = Lookup.getDefault().lookup(IProductBN.class);
    private JComboBox cbPrice = null;
    //  private int r = 0;
    private List<Product> listProducts;
    private List<Integer> slg;// số lượng
    private List<String> dongia; // Don gia
    private int tableSelectedRow;

    private void resetColorButton() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setBackground(new Color(207, 218, 230)); // màu panel button (xanh ghi)
            // buttons.get(i).setForeground(new Color(255, 255, 255));
            buttons.get(i).setForeground(new Color(0, 0, 0)); // màu đen
            buttons.get(i).setFont(new Font("Tahoma", Font.PLAIN, 12));
        }
    }

    private class MyMouseListener implements MouseListener {

        private JButton btn;

        public MyMouseListener(JButton btn) {
            this.btn = btn;
            buttons.add(btn);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            resetColorButton();
            btn.setBackground(new Color(255, 128, 0));//ấn xuống màu cam
            btn.setForeground(new Color(255, 255, 255)); // chữ màu trắng
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            btn.setBackground(new Color(255, 128, 0));//nhả chuột
            btnchoise = btn;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (!btn.equals(btnchoise)) {
                btn.setBackground(new Color(192, 192, 192));// màu di chuột  
                btn.setForeground(Color.red);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!btn.equals(btnchoise)) //btn.setBackground(new Color(207, 218, 230));// rời chuột màu ghi về khu vực
            {
                btn.setBackground(new Color(208, 215, 255));
                btn.setForeground(new Color(0, 0, 0));
            }
        }
    }

    public MenuSalesTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(MenuSalesTopComponent.class, "CTL_MenuSalesTopComponent"));
        setToolTipText(NbBundle.getMessage(MenuSalesTopComponent.class, "HINT_MenuSalesTopComponent"));

        loadButton_Khuvuc();
        listProducts = new ArrayList<Product>();
        slg = new ArrayList<Integer>();
        dongia = new ArrayList<String>();
        //table_Sales.setModel(new ProductTableModel(listProducts, slg));

    }

    public void addProduct(Product p) {
        boolean has = false;
        for (int i = 0; i < listProducts.size(); i++) {
            if (listProducts.get(i).getId() == p.getId()) {
                slg.set(i, slg.get(i) + 1);
                has = true;
                break;
            }
        }
        if (!has) {
            listProducts.add(p);
            dongia.add("Lua chon");
            slg.add(1);
        }
    }

    public void loadButton_Khuvuc() {
        button_khuvuc1.setContentAreaFilled(false);
        button_khuvuc1.setOpaque(true);
        button_khuvuc1.addMouseListener(new MyMouseListener(button_khuvuc1));

        button_khuvuc2.setContentAreaFilled(false);
        button_khuvuc2.setOpaque(true);
        button_khuvuc2.addMouseListener(new MyMouseListener(button_khuvuc2));

        button_khuvuc3.setContentAreaFilled(false);
        button_khuvuc3.setOpaque(true);
        button_khuvuc3.addMouseListener(new MyMouseListener(button_khuvuc3));

        button_khuvuc4.setContentAreaFilled(false);
        button_khuvuc4.setOpaque(true);
        button_khuvuc4.addMouseListener(new MyMouseListener(button_khuvuc4));
        resetColorButton();

        button_khuvuc1.setBackground(new Color(255, 128, 0));//da cam click 
        button_khuvuc1.setForeground(new Color(255, 255, 255));
        btnchoise = button_khuvuc1;

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_tongMenuSales = new javax.swing.JPanel();
        panel_left = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        Combo = new javax.swing.JComboBox();
        Panel_tableSales = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_Sales = new javax.swing.JTable();
        btt_Hủy = new javax.swing.JButton();
        btt_Note = new javax.swing.JButton();
        btt_Thêm = new javax.swing.JButton();
        btt_Xóa = new javax.swing.JButton();
        btt_Free_Product = new javax.swing.JButton();
        btt_price_changes = new javax.swing.JButton();
        btt_returnSP = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        label_SumMoney = new javax.swing.JLabel();
        panel_bttTableSales = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        btt_in = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        panle_right = new javax.swing.JPanel();
        panle_Node_khuvuc = new javax.swing.JPanel();
        btt_back = new javax.swing.JButton();
        btt_next = new javax.swing.JButton();
        button_khuvuc2 = new javax.swing.JButton();
        button_khuvuc3 = new javax.swing.JButton();
        button_khuvuc4 = new javax.swing.JButton();
        button_khuvuc1 = new javax.swing.JButton();
        panle_bttSo = new javax.swing.JPanel();
        button_1 = new javax.swing.JButton();
        button_2 = new javax.swing.JButton();
        button_3 = new javax.swing.JButton();
        button_4 = new javax.swing.JButton();
        button_5 = new javax.swing.JButton();
        button_6 = new javax.swing.JButton();
        button_7 = new javax.swing.JButton();
        button_8 = new javax.swing.JButton();
        button_9 = new javax.swing.JButton();
        button_C = new javax.swing.JButton();
        button_0 = new javax.swing.JButton();
        panle_khuvuc = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        panel_spJava = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        panel_spCafe2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        panle_Button_MainMenuExit = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1398, 578));

        panel_tongMenuSales.setBackground(new java.awt.Color(103, 153, 255));

        panel_left.setBackground(new java.awt.Color(103, 153, 255));
        panel_left.setOpaque(false);

        jPanel6.setBackground(new java.awt.Color(153, 153, 153));

        Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất cả", "Sp mua\t", "Sp trả lại", "Sp Hủy" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(257, Short.MAX_VALUE)
                .addComponent(Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Panel_tableSales.setOpaque(false);

        table_Sales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên sản phẩm", "Số lượng", "Đơn giá", "Tiền"
            }
        ));
        table_Sales.setOpaque(false);
        table_Sales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_SalesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_Sales);
        table_Sales.getColumnModel().getColumn(0).setMinWidth(50);
        table_Sales.getColumnModel().getColumn(0).setMaxWidth(50);
        table_Sales.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.table_Sales.columnModel.title0")); // NOI18N
        table_Sales.getColumnModel().getColumn(1).setMinWidth(160);
        table_Sales.getColumnModel().getColumn(1).setPreferredWidth(160);
        table_Sales.getColumnModel().getColumn(1).setMaxWidth(160);
        table_Sales.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.table_Sales.columnModel.title1")); // NOI18N
        table_Sales.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.table_Sales.columnModel.title2")); // NOI18N
        table_Sales.getColumnModel().getColumn(3).setHeaderValue(org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.table_Sales.columnModel.title3")); // NOI18N
        table_Sales.getColumnModel().getColumn(4).setHeaderValue(org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.table_Sales.columnModel.title4")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btt_Hủy, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.btt_Hủy.text_1")); // NOI18N
        btt_Hủy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btt_HủyActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(btt_Note, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.btt_Note.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btt_Thêm, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.btt_Thêm.text_1")); // NOI18N
        btt_Thêm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btt_ThêmActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(btt_Xóa, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.btt_Xóa.text_1")); // NOI18N
        btt_Xóa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btt_XóaActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(btt_Free_Product, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.btt_Free_Product.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btt_price_changes, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.btt_price_changes.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btt_returnSP, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.btt_returnSP.text_1")); // NOI18N

        jTextField1.setBackground(new java.awt.Color(0, 0, 0));
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setText(org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.jTextField1.text_1")); // NOI18N
        jTextField1.setDisabledTextColor(new java.awt.Color(255, 255, 255));

        label_SumMoney.setBackground(new java.awt.Color(0, 0, 0));
        label_SumMoney.setFont(new java.awt.Font("Tahoma", 2, 12));
        label_SumMoney.setForeground(new java.awt.Color(255, 255, 255));
        label_SumMoney.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        org.openide.awt.Mnemonics.setLocalizedText(label_SumMoney, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.label_SumMoney.text_1")); // NOI18N
        label_SumMoney.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        label_SumMoney.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        label_SumMoney.setOpaque(true);
        label_SumMoney.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        panel_bttTableSales.setBackground(new java.awt.Color(153, 153, 153));
        panel_bttTableSales.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        panel_bttTableSales.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        panel_bttTableSales.setOpaque(true);

        jPanel5.setBackground(new java.awt.Color(153, 153, 153));

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.jButton1.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.jButton2.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButton4, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.jButton4.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButton3, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.jButton3.text_1")); // NOI18N

        jButton5.setBackground(new java.awt.Color(242, 140, 0));
        org.openide.awt.Mnemonics.setLocalizedText(jButton5, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.jButton5.text_1")); // NOI18N
        jButton5.setBorderPainted(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.setOpaque(false);

        org.openide.awt.Mnemonics.setLocalizedText(btt_in, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.btt_in.text_1")); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btt_in, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btt_in, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        panel_bttTableSales.addTab(org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.jPanel5.TabConstraints.tabTitle_1"), jPanel5); // NOI18N

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 369, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
        );

        panel_bttTableSales.addTab(org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.jPanel3.TabConstraints.tabTitle_1"), jPanel3); // NOI18N

        javax.swing.GroupLayout Panel_tableSalesLayout = new javax.swing.GroupLayout(Panel_tableSales);
        Panel_tableSales.setLayout(Panel_tableSalesLayout);
        Panel_tableSalesLayout.setHorizontalGroup(
            Panel_tableSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_tableSalesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(Panel_tableSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Panel_tableSalesLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(label_SumMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Panel_tableSalesLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                        .addGroup(Panel_tableSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btt_Xóa, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btt_Hủy, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btt_Thêm, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btt_Free_Product, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btt_price_changes, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btt_returnSP, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btt_Note, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(panel_bttTableSales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        Panel_tableSalesLayout.setVerticalGroup(
            Panel_tableSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_tableSalesLayout.createSequentialGroup()
                .addGroup(Panel_tableSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_SumMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(Panel_tableSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                    .addGroup(Panel_tableSalesLayout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(btt_Xóa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btt_Hủy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btt_Thêm, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btt_Free_Product, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btt_price_changes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btt_returnSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btt_Note, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addComponent(panel_bttTableSales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panel_leftLayout = new javax.swing.GroupLayout(panel_left);
        panel_left.setLayout(panel_leftLayout);
        panel_leftLayout.setHorizontalGroup(
            panel_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_leftLayout.createSequentialGroup()
                .addGroup(panel_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_leftLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Panel_tableSales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );
        panel_leftLayout.setVerticalGroup(
            panel_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_leftLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Panel_tableSales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );

        panle_right.setBackground(new java.awt.Color(103, 153, 255));
        panle_right.setOpaque(false);
        panle_right.setPreferredSize(new java.awt.Dimension(877, 533));

        panle_Node_khuvuc.setBackground(new java.awt.Color(208, 215, 255));

        btt_back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/resto/image/button_back.jpg"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btt_back, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.btt_back.text_1")); // NOI18N
        btt_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btt_backMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btt_backMousePressed(evt);
            }
        });

        btt_next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/resto/image/button_next.jpg"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btt_next, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.btt_next.text_1")); // NOI18N
        btt_next.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btt_nextMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btt_nextMousePressed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(button_khuvuc2, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.button_khuvuc2.text_1")); // NOI18N
        button_khuvuc2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_khuvuc2MouseClicked(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(button_khuvuc3, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.button_khuvuc3.text_1")); // NOI18N
        button_khuvuc3.setPreferredSize(new java.awt.Dimension(93, 23));

        org.openide.awt.Mnemonics.setLocalizedText(button_khuvuc4, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.button_khuvuc4.text_1")); // NOI18N
        button_khuvuc4.setPreferredSize(new java.awt.Dimension(93, 23));

        org.openide.awt.Mnemonics.setLocalizedText(button_khuvuc1, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.button_khuvuc1.text_1")); // NOI18N
        button_khuvuc1.setPreferredSize(new java.awt.Dimension(93, 23));
        button_khuvuc1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_khuvuc1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panle_Node_khuvucLayout = new javax.swing.GroupLayout(panle_Node_khuvuc);
        panle_Node_khuvuc.setLayout(panle_Node_khuvucLayout);
        panle_Node_khuvucLayout.setHorizontalGroup(
            panle_Node_khuvucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panle_Node_khuvucLayout.createSequentialGroup()
                .addComponent(btt_back, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(button_khuvuc1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(button_khuvuc2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(button_khuvuc3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(button_khuvuc4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 442, Short.MAX_VALUE)
                .addComponent(btt_next, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panle_Node_khuvucLayout.setVerticalGroup(
            panle_Node_khuvucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btt_back, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(button_khuvuc1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(button_khuvuc2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(button_khuvuc3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(button_khuvuc4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btt_next, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        panle_bttSo.setOpaque(false);

        button_1.setFont(new java.awt.Font("Tahoma", 1, 14));
        org.openide.awt.Mnemonics.setLocalizedText(button_1, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.button_1.text_1")); // NOI18N

        button_2.setFont(new java.awt.Font("Tahoma", 1, 14));
        org.openide.awt.Mnemonics.setLocalizedText(button_2, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.button_2.text_1")); // NOI18N

        button_3.setFont(new java.awt.Font("Tahoma", 1, 14));
        org.openide.awt.Mnemonics.setLocalizedText(button_3, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.button_3.text_1")); // NOI18N

        button_4.setFont(new java.awt.Font("Tahoma", 1, 14));
        org.openide.awt.Mnemonics.setLocalizedText(button_4, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.button_4.text_1")); // NOI18N

        button_5.setFont(new java.awt.Font("Tahoma", 1, 14));
        org.openide.awt.Mnemonics.setLocalizedText(button_5, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.button_5.text_1")); // NOI18N

        button_6.setFont(new java.awt.Font("Tahoma", 1, 14));
        org.openide.awt.Mnemonics.setLocalizedText(button_6, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.button_6.text_1")); // NOI18N

        button_7.setFont(new java.awt.Font("Tahoma", 1, 14));
        org.openide.awt.Mnemonics.setLocalizedText(button_7, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.button_7.text_1")); // NOI18N

        button_8.setFont(new java.awt.Font("Tahoma", 1, 14));
        org.openide.awt.Mnemonics.setLocalizedText(button_8, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.button_8.text_1")); // NOI18N

        button_9.setFont(new java.awt.Font("Tahoma", 1, 14));
        org.openide.awt.Mnemonics.setLocalizedText(button_9, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.button_9.text_1")); // NOI18N

        button_C.setFont(new java.awt.Font("Tahoma", 1, 14));
        org.openide.awt.Mnemonics.setLocalizedText(button_C, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.button_C.text_1")); // NOI18N

        button_0.setFont(new java.awt.Font("Tahoma", 1, 14));
        org.openide.awt.Mnemonics.setLocalizedText(button_0, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.button_0.text_1")); // NOI18N

        javax.swing.GroupLayout panle_bttSoLayout = new javax.swing.GroupLayout(panle_bttSo);
        panle_bttSo.setLayout(panle_bttSoLayout);
        panle_bttSoLayout.setHorizontalGroup(
            panle_bttSoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panle_bttSoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(button_0, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_1, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_2, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_3, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_4, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_5, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_6, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_7, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_8, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_9, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_C, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                .addContainerGap())
        );
        panle_bttSoLayout.setVerticalGroup(
            panle_bttSoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(button_8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(button_9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(button_C, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(button_7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(button_6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(button_5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(button_4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(button_3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(button_2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(button_1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(button_0, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        panle_khuvuc.setBackground(new java.awt.Color(103, 153, 255));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        panel_spJava.setBackground(new java.awt.Color(153, 204, 255));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/resto/image/java-icon.gif"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.jLabel1.text_1")); // NOI18N
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.jLabel2.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.jLabel3.text_1")); // NOI18N

        javax.swing.GroupLayout panel_spJavaLayout = new javax.swing.GroupLayout(panel_spJava);
        panel_spJava.setLayout(panel_spJavaLayout);
        panel_spJavaLayout.setHorizontalGroup(
            panel_spJavaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_spJavaLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_spJavaLayout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        panel_spJavaLayout.setVerticalGroup(
            panel_spJavaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_spJavaLayout.createSequentialGroup()
                .addGroup(panel_spJavaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_spJavaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jLabel3))
        );

        panel_spCafe2.setBackground(new java.awt.Color(255, 228, 121));
        panel_spCafe2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_spCafe2MouseClicked(evt);
            }
        });

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/resto/image/cafe.jpeg"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.jLabel10.text_1")); // NOI18N
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.jLabel11.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel12, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.jLabel12.text_1")); // NOI18N

        javax.swing.GroupLayout panel_spCafe2Layout = new javax.swing.GroupLayout(panel_spCafe2);
        panel_spCafe2.setLayout(panel_spCafe2Layout);
        panel_spCafe2Layout.setHorizontalGroup(
            panel_spCafe2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_spCafe2Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_spCafe2Layout.createSequentialGroup()
                .addContainerGap(67, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        panel_spCafe2Layout.setVerticalGroup(
            panel_spCafe2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_spCafe2Layout.createSequentialGroup()
                .addGroup(panel_spCafe2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_spCafe2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jLabel12))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(panel_spJava, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(panel_spCafe2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_spJava, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel_spCafe2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jScrollPane2.setViewportView(jPanel2);

        org.openide.awt.Mnemonics.setLocalizedText(jButton6, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.jButton6.text_1")); // NOI18N
        jButton6.setContentAreaFilled(false);
        jButton6.setOpaque(true);

        org.openide.awt.Mnemonics.setLocalizedText(jButton7, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.jButton7.text_1")); // NOI18N
        jButton7.setContentAreaFilled(false);
        jButton7.setOpaque(true);

        javax.swing.GroupLayout panle_khuvucLayout = new javax.swing.GroupLayout(panle_khuvuc);
        panle_khuvuc.setLayout(panle_khuvucLayout);
        panle_khuvucLayout.setHorizontalGroup(
            panle_khuvucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton6)
            .addComponent(jButton7)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panle_khuvucLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 816, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );
        panle_khuvucLayout.setVerticalGroup(
            panle_khuvucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panle_khuvucLayout.createSequentialGroup()
                .addContainerGap(287, Short.MAX_VALUE)
                .addGroup(panle_khuvucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panle_khuvucLayout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
        );

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout panle_rightLayout = new javax.swing.GroupLayout(panle_right);
        panle_right.setLayout(panle_rightLayout);
        panle_rightLayout.setHorizontalGroup(
            panle_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panle_rightLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panle_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panle_khuvuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panle_rightLayout.createSequentialGroup()
                        .addGroup(panle_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panle_rightLayout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 681, Short.MAX_VALUE)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(panle_Node_khuvuc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panle_bttSo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))))
        );
        panle_rightLayout.setVerticalGroup(
            panle_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panle_rightLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panle_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panle_Node_khuvuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panle_bttSo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panle_khuvuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panle_Button_MainMenuExit.setOpaque(false);

        org.openide.awt.Mnemonics.setLocalizedText(jButton9, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.jButton9.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButton8, org.openide.util.NbBundle.getMessage(MenuSalesTopComponent.class, "MenuSalesTopComponent.jButton8.text_1")); // NOI18N

        javax.swing.GroupLayout panle_Button_MainMenuExitLayout = new javax.swing.GroupLayout(panle_Button_MainMenuExit);
        panle_Button_MainMenuExit.setLayout(panle_Button_MainMenuExitLayout);
        panle_Button_MainMenuExitLayout.setHorizontalGroup(
            panle_Button_MainMenuExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panle_Button_MainMenuExitLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9))
        );
        panle_Button_MainMenuExitLayout.setVerticalGroup(
            panle_Button_MainMenuExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panle_Button_MainMenuExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton9)
                .addComponent(jButton8))
        );

        javax.swing.GroupLayout panel_tongMenuSalesLayout = new javax.swing.GroupLayout(panel_tongMenuSales);
        panel_tongMenuSales.setLayout(panel_tongMenuSalesLayout);
        panel_tongMenuSalesLayout.setHorizontalGroup(
            panel_tongMenuSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_tongMenuSalesLayout.createSequentialGroup()
                .addComponent(panel_left, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panel_tongMenuSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_tongMenuSalesLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(panle_right, javax.swing.GroupLayout.DEFAULT_SIZE, 922, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_tongMenuSalesLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panle_Button_MainMenuExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))))
        );
        panel_tongMenuSalesLayout.setVerticalGroup(
            panel_tongMenuSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_tongMenuSalesLayout.createSequentialGroup()
                .addGroup(panel_tongMenuSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panel_left, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel_tongMenuSalesLayout.createSequentialGroup()
                        .addComponent(panle_right, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panle_Button_MainMenuExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_tongMenuSales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_tongMenuSales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btt_backMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btt_backMouseExited
        btt_back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/resto/image/button_back.jpg")));
	}//GEN-LAST:event_btt_backMouseExited

    private void btt_backMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btt_backMousePressed

        btt_back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/resto/image/button_back2.jpg")));
	}//GEN-LAST:event_btt_backMousePressed

    private void btt_nextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btt_nextMouseExited

        btt_next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/resto/image/button_next.jpg")));
	}//GEN-LAST:event_btt_nextMouseExited

    private void btt_nextMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btt_nextMousePressed

        btt_next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/resto/image/btt_next2.jpg")));

	}//GEN-LAST:event_btt_nextMousePressed

    private void button_khuvuc2MouseClicked(java.awt.event.MouseEvent evt) {

        Dimension sizePanel = jPanel2.getPreferredSize();
        Dimension sizeProduct = new ProductDesign(null).getPreferredSize();
        JPanel panel = new JPanel(new GridLayout(5, 4, 6, 6));

        panel.setBackground(new Color(153, 153, 153));

        JPanel panelTong = new JPanel(new BorderLayout());
        panelTong.add(panel, BorderLayout.NORTH);
        panelTong.setBackground(new Color(153, 153, 153));
//Lấy listProduct
        List<Product> products = new ArrayList<Product>();
        products = productBN.selectAll();
        for (int i = 0; i < products.size(); i++) {
            final ProductDesign pd = new ProductDesign(products.get(i));
            pd.setProduct(new ImageIcon(), pd.getProduct().getProductName(), "10000");
            //add sản phẩm vào panel
            panel.add(pd);
//sự kiện click lấy dl từ panl sang table_Sales
            pd.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    saveAllPrice();
                    addProduct(pd.getProduct());
                    //table_Sales.setModel(new ProductTableModel(listProducts, slg));
                    table_Sales.setModel(getTableModel());
                    ProductCell cell = new ProductCell(table_Sales.getRowCount());
                    table_Sales.getColumnModel().getColumn(3).setCellEditor(cell);
                }
            });

        }
        jScrollPane2.setViewportView(panelTong);

    }

    private void saveAllPrice() {
        int totalRow = table_Sales.getRowCount();
        dongia.clear();
        if (totalRow > 0) {
            for (int i = 0; i < totalRow; i++) {
                dongia.add(table_Sales.getValueAt(i, 3).toString());
            }
        }
    }

    public DefaultTableModel getTableModel() {
        String[] header = {"STT", "Tên sản phẩm", " Số lượng", "Đơn giá", "Tiền"};
        DefaultTableModel m = new DefaultTableModel(header, 0);
        for (int i = 0; i < listProducts.size(); i++) {
            String dg = "Lua chon";
            if (listProducts.size() != 1) {
                dg = dongia.get(i);
            }
            Object[] rows = {i + 1, listProducts.get(i), slg.get(i), dg, ""};
            m.addRow(rows);
        }
        return m;
    }

    private void button_khuvuc1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_khuvuc1MouseClicked

        jScrollPane2.setViewportView(jPanel2);
	}//GEN-LAST:event_button_khuvuc1MouseClicked

    private void panel_spCafe2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_spCafe2MouseClicked
        Dimension sizePane = jPanel2.getPreferredSize();
        Dimension sizeTreeProduct = new TreeProductDesign().getPreferredSize();
        JPanel panel = new JPanel(new GridLayout(3, 5, 5, 5));
        panel.setBackground(new Color(153, 153, 153));

        JPanel panelTong = new JPanel(new BorderLayout());
        panelTong.add(panel, BorderLayout.NORTH);
        panelTong.setBackground(new Color(153, 153, 153)); // màu ghi

        List<Product> products = new ArrayList<Product>();
        products.add(new Product());
        for (int i = 0; i < products.size(); i++) {
            TreeProductDesign treepd = new TreeProductDesign();
            panel.add(treepd);
            treepd.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {

                    JOptionPane.showMessageDialog(null, "Hi");
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    // panel_spCafe2.setBackground(new Color(255, 128, 0));
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
        }
        jScrollPane2.setViewportView(panelTong);
    }//GEN-LAST:event_panel_spCafe2MouseClicked

    private void table_SalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_SalesMouseClicked
        tableSelectedRow = table_Sales.getSelectedRow();
    }//GEN-LAST:event_table_SalesMouseClicked

    private void btt_ThêmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btt_ThêmActionPerformed
        increase_Line_Products();
    }//GEN-LAST:event_btt_ThêmActionPerformed

    private void btt_XóaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btt_XóaActionPerformed
        delete_Line_Products();
    }//GEN-LAST:event_btt_XóaActionPerformed

    private void btt_HủyActionPerformed(java.awt.event.ActionEvent evt) {
        destroyTableProducts();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox Combo;
    private javax.swing.JPanel Panel_tableSales;
    private javax.swing.JButton btt_Free_Product;
    private javax.swing.JButton btt_Hủy;
    private javax.swing.JButton btt_Note;
    private javax.swing.JButton btt_Thêm;
    private javax.swing.JButton btt_Xóa;
    private javax.swing.JButton btt_back;
    private javax.swing.JButton btt_in;
    private javax.swing.JButton btt_next;
    private javax.swing.JButton btt_price_changes;
    private javax.swing.JButton btt_returnSP;
    private javax.swing.JButton button_0;
    private javax.swing.JButton button_1;
    private javax.swing.JButton button_2;
    private javax.swing.JButton button_3;
    private javax.swing.JButton button_4;
    private javax.swing.JButton button_5;
    private javax.swing.JButton button_6;
    private javax.swing.JButton button_7;
    private javax.swing.JButton button_8;
    private javax.swing.JButton button_9;
    private javax.swing.JButton button_C;
    private javax.swing.JButton button_khuvuc1;
    private javax.swing.JButton button_khuvuc2;
    private javax.swing.JButton button_khuvuc3;
    private javax.swing.JButton button_khuvuc4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel label_SumMoney;
    private javax.swing.JTabbedPane panel_bttTableSales;
    private javax.swing.JPanel panel_left;
    private javax.swing.JPanel panel_spCafe2;
    private javax.swing.JPanel panel_spJava;
    private javax.swing.JPanel panel_tongMenuSales;
    private javax.swing.JPanel panle_Button_MainMenuExit;
    private javax.swing.JPanel panle_Node_khuvuc;
    private javax.swing.JPanel panle_bttSo;
    private javax.swing.JPanel panle_khuvuc;
    private javax.swing.JPanel panle_right;
    private javax.swing.JTable table_Sales;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    //Hủy sp trên table_Sales
    public void destroyTableProducts() {
//        listProducts.remove(table_Sales.getSelectedRow());
        if (tableSelectedRow < 0 || tableSelectedRow >= table_Sales.getRowCount()) {
            return;
        }
        slg.set(tableSelectedRow, slg.get(tableSelectedRow) - 1);
        if (slg.get(tableSelectedRow) == 0) {
            slg.remove(tableSelectedRow);
            listProducts.remove(tableSelectedRow);
            dongia.remove(tableSelectedRow);
            tableSelectedRow = -1;
        }

        //table_Sales.setModel(new ProductTableModel(listProducts, slg));


        table_Sales.setModel(getTableModel());
        ProductCell cell = new ProductCell(table_Sales.getRowCount());
        table_Sales.getColumnModel().getColumn(3).setCellEditor(cell);
    }
    // Thêm sản phẩm trên table
    public void increase_Line_Products() {
        if (tableSelectedRow < 0 || tableSelectedRow >= table_Sales.getRowCount()) {
            return;
        }
        slg.set(tableSelectedRow, slg.get(tableSelectedRow) + 1);
        if (slg.get(tableSelectedRow) == 0) {
            slg.remove(tableSelectedRow);
            listProducts.remove(tableSelectedRow);
            dongia.remove(tableSelectedRow);
            tableSelectedRow = -1;
        }
        //table_Sales.setModel(new ProductTableModel(listProducts, slg));       
        table_Sales.setModel(getTableModel());
        ProductCell cell = new ProductCell(table_Sales.getRowCount());
        table_Sales.getColumnModel().getColumn(3).setCellEditor(cell);
    }
    //Xóa dòng trên table
    public void delete_Line_Products() {
        DefaultTableModel model = (DefaultTableModel) table_Sales.getModel();
        tableSelectedRow = table_Sales.getSelectedRow();
        if (tableSelectedRow < 0 || tableSelectedRow >= table_Sales.getRowCount()) {
            return;
        }
        if (tableSelectedRow != -1) {
             dongia.remove(tableSelectedRow);
            slg.remove(tableSelectedRow);           
            listProducts.remove(tableSelectedRow);
           
        }
         table_Sales.setModel(getTableModel());
        ProductCell cell = new ProductCell(table_Sales.getRowCount());
        table_Sales.getColumnModel().getColumn(3).setCellEditor(cell);
         model.removeRow(tableSelectedRow);
    }
}