/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BasicToolbar.java
 *
 * Created on Feb 15, 2012, 10:14:19 AM
 */
package vn.com.hkt.basic.toolbar.gui;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import vn.com.hkt.basic.api.IEnterpriseBN;
import vn.com.hkt.basic.api.IPersonBN;
import vn.com.hkt.basic.toolbar.api.IBasicToolbar;
import vn.com.hkt.basic.toolbar.api.ITabToolbar;
import vn.com.hkt.basic.toolbar.api.system.ISystemToolbar;
import vn.com.hkt.erm.enterprise.dao.EnterpriseBN;
import vn.com.hkt.hrm.person.dao.PersonBN;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Person;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;
import vn.com.hkt.ui.main.ui.api.ITopComponentList;

/**
 *
 * @author Admin
 */
//@ServiceProvider(service=IBasicToolbar.class)
public final class BasicToolbar extends javax.swing.JPanel implements IBasicToolbar {

    private int listType = ENTERPRISE;
    private IEnterpriseBN enterpriseBN = new EnterpriseBN();
    private IPersonBN personBN = new PersonBN();
    private static BasicToolbar basicToolbar;
    private List<ITabToolbar> list;
    private ImageIcon imageChoise;
    private Person personChoise = null;
    private Enterprise enterpriseChoise = null;
    private Color colorBackground = new Color(250, 250, 250);
    private Color colorMouseClick = new Color(252, 230, 145);
    private Color colorMouseMove = new Color(252, 242, 198);
    private Color colorWord = Color.black;

    public static BasicToolbar getBasicToolbar() {
        if (basicToolbar == null) {
            basicToolbar = new BasicToolbar();
        }
        return basicToolbar;
    }

    /**
     * Creates new form BasicToolbar
     */
    private BasicToolbar() {
        initComponents();
        loadTabToolbar();
        changeColor();
        loadEnterprise();
    }

    public ImageIcon getIcon(int x, int y, ImageIcon image) {
        try {
            Image img = image.getImage();
            Image newimg = img.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
            return new ImageIcon(newimg);
        } catch (Exception e) {
            return null;
        }
    }

    private void setIconDefault(ITabToolbar tabToolBar) {
        ImageIcon image = new ImageIcon(getClass().getResource("/vn/com/hkt/basic/toolbar/icon/coming_soon_icon.png"));
        Field[] fields = tabToolBar.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                Object obj = fields[i].get(tabToolBar);
                if (obj instanceof JButton) {
                    JButton btn = (JButton) obj;
                    btn.setContentAreaFilled(false);
                    btn.setBorder(null);
                    if (btn.getIcon() == null) {
                        Dimension d = btn.getPreferredSize();
                        ImageIcon icon = getIcon(d.width * 3 / 4, d.height / 2, image);
                        btn.setIcon(icon);
                        btn.setHorizontalTextPosition(SwingConstants.CENTER);
                        btn.setVerticalTextPosition(SwingConstants.BOTTOM);
                    }
                    btn.setOpaque(true);
                    btn.setContentAreaFilled(false);
                    btn.setFocusable(false);
                    btn.addMouseListener(new MouseListener(btn));
                }
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }

    private List<ITabToolbar> sortListTabToolbar(List<ITabToolbar> list) {
        List<ITabToolbar> l = new ArrayList<ITabToolbar>();
        int min = 0, mark = -1;
        int leng = list.size();
        boolean[] bs = new boolean[leng];
        for (int i = 0; i < leng; i++) {
            bs[i] = false;
        }
        for (int i = 0; i < leng; i++) {
            min = Integer.MAX_VALUE;
            mark = -1;
            for (int j = 0; j < leng; j++) {
                if (!bs[j] && (list.get(j).getTabToolbarIndex() < min)) {
                    min = list.get(j).getTabToolbarIndex();
                    mark = j;
                }
            }
            if (mark >= 0) {
                l.add(list.get(mark));
                bs[mark] = true;
            }
        }
        return l;
    }

    @Override
    public void loadTabToolbar() {
        jTabbedPane1.removeAll();
        list = (List<ITabToolbar>) Lookup.getDefault().lookupAll(ITabToolbar.class);
        list = sortListTabToolbar(list);
        for (int i = 0; i < list.size(); i++) {
            JScrollPane sp = new JScrollPane();
            sp.setVisible(true);
            ITabToolbar panel = list.get(i);
            setIconDefault(panel);
            if (panel instanceof JPanel) {
                ((JPanel) panel).setOpaque(false);
                ((JPanel) panel).setBackground(colorBackground);
            }
            sp.setViewportView((JPanel) panel);
            if (jTabbedPane1.getTabCount() >= i + 1) {
                jTabbedPane1.setComponentAt(i, sp);
                jTabbedPane1.setTitleAt(i, panel.getTabToolbarName());
            } else {
                jTabbedPane1.add(panel.getTabToolbarName(), sp);
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        cbList = new javax.swing.JComboBox();
        lbImage = new javax.swing.JLabel();

        jTabbedPane1.setBackground(new java.awt.Color(255, 51, 51));
        jTabbedPane1.setOpaque(true);
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(8, 125));
        jTabbedPane1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jTabbedPane1ComponentResized(evt);
            }
        });

        cbList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbListItemStateChanged(evt);
            }
        });
        cbList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbListActionPerformed(evt);
            }
        });

        lbImage.setBackground(new java.awt.Color(204, 255, 204));
        lbImage.setText(org.openide.util.NbBundle.getMessage(BasicToolbar.class, "BasicToolbar.lbImage.text")); // NOI18N
        lbImage.setOpaque(true);
        lbImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbImageMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbImage, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
            .addComponent(cbList, 0, 186, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbListItemStateChanged
        if (cbList.getSelectedItem() instanceof Enterprise) {
            Enterprise enterprise = (Enterprise) cbList.getSelectedItem();
            enterpriseChoise = enterprise;
            loadImage();
            try {
                Set<TopComponent> listTop = WindowManager.getDefault().getRegistry().getOpened();
                for (TopComponent tl : listTop) {
                    if (tl instanceof ITopComponentList) {
                        ((ITopComponentList) tl).load();
                    }
                }
            } catch (Exception e) {
            }
        }
        else if (cbList.getSelectedItem() instanceof Person) {
            Person person = (Person) cbList.getSelectedItem();
            personChoise = person;
            loadImage();
            try {
                Set<TopComponent> listTop = WindowManager.getDefault().getRegistry().getOpened();
                for (TopComponent tl : listTop) {
                    if (tl instanceof ITopComponentList) {
                        ((ITopComponentList) tl).load();
                    }
                }
            } catch (Exception e) {
            }
        }

    }//GEN-LAST:event_cbListItemStateChanged

    private void cbListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbListActionPerformed
    }//GEN-LAST:event_cbListActionPerformed

    private void lbImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbImageMouseClicked
        if (evt.getClickCount() < 2) {
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
            saveImage();
            loadImage();
        } catch (Exception e) {
            return;
        }
    }//GEN-LAST:event_lbImageMouseClicked

    private void jTabbedPane1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTabbedPane1ComponentResized
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1ComponentResized
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbList;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbImage;
    // End of variables declaration//GEN-END:variables

    @Override
    public void loadEnterprise() {
        listType = ENTERPRISE;
        List<Enterprise> enterprises = enterpriseBN.selectAll();
        DefaultComboBoxModel model = new DefaultComboBoxModel(enterprises.toArray());
        cbList.setModel(model);
        if (enterprises.size() > 0) {
            cbList.setSelectedItem(enterprises.get(0));
            enterpriseChoise = (Enterprise) cbList.getSelectedItem();
            loadImage();
        }
    }

    @Override
    public void setEnterprise(Enterprise enterprise) {
        loadEnterprise();
        for (int i = 0; i < cbList.getModel().getSize(); i++) {
            if (enterprise.getId() == ((Enterprise) cbList.getModel().getElementAt(i)).getId()) {
                cbList.setSelectedIndex(i);
                enterpriseChoise = (Enterprise) cbList.getSelectedItem();
                return;
            }
        }
    }

    @Override
    public Enterprise getEnterprise() {
        if (listType == ENTERPRISE) {
            return enterpriseChoise;
        } else {
            return null;
        }
    }

    @Override
    public void loadPerson() {
        listType = PERSON;
        List<Person> persons = personBN.selectAll();
        DefaultComboBoxModel model = new DefaultComboBoxModel(persons.toArray());
        cbList.setModel(model);
        if (persons.size() > 0) {
            cbList.setSelectedItem(persons.get(0));
            personChoise = (Person) cbList.getSelectedItem();
            loadImage();
        }
    }

    @Override
    public void setPerson(Person person) {
        loadPerson();
        for (int i = 0; i < cbList.getModel().getSize(); i++) {
            if (person.getId() == ((Person) cbList.getModel().getElementAt(i)).getId()) {
                cbList.setSelectedIndex(i);
                personChoise = (Person) cbList.getSelectedItem();
                return;
            }
        }
    }

    @Override
    public Person getPerson() {
        if (listType == PERSON) {
            return (Person) cbList.getSelectedItem();
        } else {
            return null;
        }
    }

    @Override
    public int getTypeList() {
        return listType;
    }

    private void loadImage() {
        if (listType == PERSON) {
            try {
                imageChoise = new ImageIcon(personChoise.getImage());
                lbImage.setIcon(imageChoise);
            } catch (Exception e) {
                lbImage.setIcon(null);
            }
        } else if (listType == ENTERPRISE) {
            try {
                imageChoise = new ImageIcon(enterpriseChoise.getPicture());
                lbImage.setIcon(imageChoise);
            } catch (Exception e) {
                lbImage.setIcon(null);
            }
        }
    }

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
            //handle it here.... not implemented yet...
        }
        return baos.toByteArray();
    }

    private void saveImage() {
        if (listType == PERSON && personChoise != null) {
            try {
                personChoise.setImage(convertImage2Byte(imageChoise.getImage()));
                personBN.update(personChoise);
            } catch (Exception ex) {
            }
        } else if (listType == ENTERPRISE && enterpriseChoise != null) {
            try {
                enterpriseChoise.setPicture(convertImage2Byte(imageChoise.getImage()));
                enterpriseBN.update(enterpriseChoise);
            } catch (Exception ex) {
            }
        }
    }

    @Override
    public ITabToolbar getTabToolbar(int index) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTabToolbarIndex() == index) {
                return list.get(i);
            }
        }
        return null;
    }

    private void setColor(JTextField txt, boolean ccw) {
        if (ccw) {
            txt.setForeground(colorWord);
        }
        txt.setOpaque(true);
        txt.addMouseListener(new MouseListener(txt, colorBackground, colorMouseClick, colorMouseMove));
    }

    private void setColor(JComboBox cb, boolean ccw) {
        cb.setRenderer(new BasicComboBoxRenderer() {

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                c.addMouseListener(new MouseListener(c, colorBackground, colorMouseClick, colorMouseMove));
                return c;
            }
        });
        cb.setOpaque(true);
        cb.setEditable(true);
        cb.setBackground(colorBackground);
        ((JTextField) cb.getEditor().getEditorComponent()).setEditable(false);
        ((JTextField) cb.getEditor().getEditorComponent()).addMouseListener(new MouseListener(((JTextField) cb.getEditor().getEditorComponent()), colorBackground, colorMouseClick, colorMouseMove));
        for (int j = 0; j < cb.getComponentCount(); j++) {
            if (cb.getComponent(j) instanceof JLabel) {
                JLabel lb = (JLabel) cb.getComponent(j);
                lb.setOpaque(true);
                if (ccw) {
                    lb.setForeground(colorWord);
                }
                lb.addMouseListener(new MouseListener(lb, colorBackground, colorMouseClick, colorMouseMove));
            }
            if (cb.getComponent(j) instanceof JTextField) {
                JTextField lb = (JTextField) cb.getComponent(j);
                lb.setOpaque(true);
                if (ccw) {
                    lb.setForeground(colorWord);
                }
                lb.addMouseListener(new MouseListener(lb, colorBackground, colorMouseClick, colorMouseMove));
            }
        }
    }

    private void setColor(JButton btn, boolean ccw) {
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        if (ccw) {
            btn.setForeground(colorWord);
        }
        btn.addMouseListener(new MouseListener(btn, colorBackground, colorMouseClick, colorMouseMove));
    }

    private void setColor(JCheckBox chb, boolean ccw) {
        chb.setOpaque(true);
        if (ccw) {
            chb.setForeground(colorWord);
        }
        chb.addMouseListener(new MouseListener(chb, colorBackground, colorMouseClick, colorMouseMove));
    }

    private void setColor(JRadioButton rbtn, boolean ccw) {
        rbtn.setOpaque(true);
        if (ccw) {
            rbtn.setForeground(colorWord);
        }
        rbtn.addMouseListener(new MouseListener(rbtn, colorBackground, colorMouseClick, colorMouseMove));
    }

    private void setColor(JDateChooser dc, boolean ccw) {
        dc.setOpaque(true);
        if (ccw) {
            dc.setForeground(colorWord);
        }
        dc.addMouseListener(new MouseListener(dc, colorBackground, colorMouseClick, colorMouseMove));
        for (int j = 0; j < dc.getComponentCount(); j++) {
            if (dc.getComponent(j) instanceof JLabel) {
                JLabel lb = (JLabel) dc.getComponent(j);
                lb.setOpaque(true);
                if (ccw) {
                    lb.setForeground(colorWord);
                }
                lb.addMouseListener(new MouseListener(lb, colorBackground, colorMouseClick, colorMouseMove));
            }
            if (dc.getComponent(j) instanceof JTextField) {
                JTextField lb = (JTextField) dc.getComponent(j);
                lb.setOpaque(true);
                if (ccw) {
                    lb.setForeground(colorWord);
                }
                lb.addMouseListener(new MouseListener(lb, colorBackground, colorMouseClick, colorMouseMove));
            }
        }
    }

    private void setColor(JLabel lb, boolean ccw) {
        lb.setOpaque(true);
        if (ccw) {
            lb.setForeground(colorWord);
        }
        lb.addMouseListener(new MouseListener(lb, colorBackground, colorMouseClick, colorMouseMove));
    }

    private void setColor(JScrollPane pane, boolean ccw) {
        pane.setOpaque(true);
        pane.getViewport().setOpaque(true);
        pane.getViewport().setBackground(colorBackground);
        pane.setBackground(colorBackground);
        for (int j = 0; j < pane.getComponentCount(); j++) {
            setColor(pane.getComponent(j), ccw);
        }
    }

    private void setColor(JTable tbl, final boolean ccw) {
        tbl.setOpaque(true);
        if (ccw) {
            tbl.setForeground(colorWord);
        }
        tbl.setBackground(colorBackground);
        for (int j = 0; j < tbl.getComponentCount(); j++) {
            setColor(tbl.getComponent(j), ccw);
        }
        for (int i = 0; i < tbl.getColumnModel().getColumnCount(); i++) {
            TableCellEditor tce = tbl.getColumnModel().getColumn(i).getCellEditor();
            if (tce != null) {
                Field[] fe = tce.getClass().getDeclaredFields();
                for (int j = 0; j < fe.length; j++) {
                    try {
                        fe[j].setAccessible(true);
                        Object obj = fe[j].get(tce);
                        setColor(obj, ccw);
                    } catch (IllegalArgumentException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (IllegalAccessException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
            }
            TableCellRenderer tcr = tbl.getColumnModel().getColumn(i).getCellRenderer();
            if (tcr != null) {
                if (tcr instanceof StripedTableCellRenderer) {
                    tcr = new DefaultTableCellRenderer() {

                        @Override
                        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                            if (c instanceof Container) {
                                setColor((Container) c, ccw);

                            }
                            return c;
                        }
                    };
                    tbl.getColumnModel().getColumn(i).setCellRenderer(tcr);
                }
//                else {
//                    Field[] fr = tcr.getClass().getDeclaredFields();
//                    for (int j = 0; j < fr.length; j++) {
//                        try {
//                            fr[j].setAccessible(true);
//                            Object obj = fr[j].get(tcr);
//                            setColor(obj);
//                        } catch (IllegalArgumentException ex) {
//                            Exceptions.printStackTrace(ex);
//                        } catch (IllegalAccessException ex) {
//                            Exceptions.printStackTrace(ex);
//                        }
//                    }
//                }
            } else {
                tbl.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {

                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                        setColor(c, ccw);
                        return c;
                    }
                });
            }
        }
    }

    private void setColor(JPanel panel, boolean ccw) {
        panel.setOpaque(true);
        panel.setBackground(colorBackground);
        for (int j = 0; j < panel.getComponentCount(); j++) {
            setColor(panel.getComponent(j), ccw);
        }
        Field[] fields = panel.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                Object obj = fields[i].get(panel);
                if (obj instanceof JComponent) {
                    JComponent jc = (JComponent) obj;
                    if (ccw) {
                        jc.setForeground(colorWord);
                    }
                    if (jc.getBorder() != null) {
                        Border b = jc.getBorder();
                        if (b instanceof TitledBorder) {
                            ((TitledBorder) b).setTitleColor(colorWord);
                        }
                    }
                }
                setColor(obj, ccw);
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }

    private void setColor(Object obj, boolean ccw) {
        if (obj instanceof JComponent) {
            JComponent jc = (JComponent) obj;
            jc.setForeground(colorWord);
            if (!(jc.getBorder() instanceof TitledBorder)) {
                jc.setBorder(null);
            } else {
                ((TitledBorder) jc.getBorder()).setTitleColor(colorWord);
            }
        }
        if (obj instanceof JButton) {
            setColor((JButton) obj, ccw);
        }
        if (obj instanceof JPanel) {
            JPanel panel = (JPanel) obj;
            setColor(panel, ccw);
        }
        if (obj instanceof JComboBox) {
            setColor((JComboBox) obj, ccw);
        }
        if (obj instanceof JTextField) {
            setColor((JTextField) obj, ccw);
        }
        if (obj instanceof JTable) {
            setColor((JTable) obj, ccw);
        }
        if (obj instanceof JScrollPane) {
            setColor((JScrollPane) obj, ccw);
        }
        if (obj instanceof JRadioButton) {
            setColor((JRadioButton) obj, ccw);
        }
        if (obj instanceof JLabel) {
            setColor((JLabel) obj, ccw);
        }
        if (obj instanceof JCheckBox) {
            setColor((JCheckBox) obj, ccw);
        }
        if (obj instanceof JDateChooser) {
            setColor((JDateChooser) obj, ccw);
        }
    }

    private void setColor(TopComponent top) {
        Field[] fields = top.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                Object obj = fields[i].get(top);
                if (obj instanceof JComponent) {
                    JComponent jc = (JComponent) obj;
                    if (jc.getBorder() != null) {
                        Border b = jc.getBorder();
                        if (b instanceof TitledBorder) {
                            ((TitledBorder) b).setTitleColor(colorWord);
                        }
                    } else {
                        jc.setBorder(null);
                    }
                }
                setColor(obj, false);
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }

    @Override
    public void changeColor() {
        ISystemToolbar systemToolbar = (ISystemToolbar) getTabToolbar(ISystemToolbar.TAB_INDEX);
        colorBackground = systemToolbar.getColorBackground();
        colorMouseClick = systemToolbar.getColorMouseClick();
        colorMouseMove = systemToolbar.getColorMouseMove();
        colorWord = systemToolbar.getColorWord();
        jTabbedPane1.setOpaque(true);
        jTabbedPane1.setBackground(colorBackground);
        this.setOpaque(true);
        this.setBackground(colorBackground);
        setColor(jPanel1, true);
        for (int i = 0; i < list.size(); i++) {
            ITabToolbar panel = list.get(i);
            setIconDefault(panel);
            if (panel instanceof JPanel) {
                setColor((JPanel) panel, true);
                ((JPanel) panel).setOpaque(true);
                ((JPanel) panel).setBackground(colorBackground);
            }
        }
        Set<TopComponent> setTop = WindowManager.getDefault().getRegistry().getOpened();
        for (TopComponent top : setTop) {
            if (top instanceof ITopComponentList) {
                setColor(top);
            }
        }
    }
}

class MouseListener implements java.awt.event.MouseListener {

    private Component component;
    private Color colorBackground = new Color(250, 250, 250);
    private Color colorMouseClick = new Color(252, 230, 145);
    private Color colorMouseMove = new Color(252, 242, 198);

    public MouseListener(Component component) {
        this.component = component;
        component.setBackground(colorBackground);
    }

    public MouseListener(Component component, Color background, Color mouseClick, Color mouseMove) {
        this.component = component;
        this.colorBackground = background;
        this.colorMouseClick = mouseClick;
        this.colorMouseMove = mouseMove;
        component.setBackground(colorBackground);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        component.setBackground(colorMouseClick);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        component.setBackground(colorMouseMove);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        component.setBackground(colorBackground);
    }
}
