/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.resto.toolbar.gui;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import vn.com.hkt.resto.panel.TableEat_A;
import vn.com.hkt.resto.panel.TableEat_B;
import vn.com.hkt.resto.panel.TableEat_Vip;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//vn.com.hkt.resto.toolbar.gui//MenuFoodTable//EN",
autostore = false)
@TopComponent.Description(preferredID = "MenuFoodTableTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "vn.com.hkt.resto.toolbar.gui.MenuFoodTableTopComponent")
@ActionReference(path = "Menu/Restaurant" /*, position = 333 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_MenuFoodTableAction",
preferredID = "MenuFoodTableTopComponent")
public final class MenuFoodTableTopComponent extends TopComponent {

    private TableEat_A tableEat_A = new TableEat_A();
    private TableEat_B tableEat_B = new TableEat_B();
    private TableEat_Vip tableEat_Vip = new TableEat_Vip();
    private JRadioButton radiochoise;

    public MenuFoodTableTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(MenuFoodTableTopComponent.class, "CTL_MenuFoodTableTopComponent"));
        setToolTipText(NbBundle.getMessage(MenuFoodTableTopComponent.class, "HINT_MenuFoodTableTopComponent"));
        loadTableEat();

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpanel = new javax.swing.JPanel();
        panle_Button_MainMenu = new javax.swing.JPanel();
        buttonHide = new javax.swing.JButton();
        buttonOpen = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        panelKhuVuc = new javax.swing.JPanel();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();

        jpanel.setBackground(new java.awt.Color(103, 153, 255));

        panle_Button_MainMenu.setOpaque(false);

        buttonHide.setBackground(new java.awt.Color(0, 0, 0));
        org.openide.awt.Mnemonics.setLocalizedText(buttonHide, org.openide.util.NbBundle.getMessage(MenuFoodTableTopComponent.class, "MenuFoodTableTopComponent.buttonHide.text")); // NOI18N
        buttonHide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHideActionPerformed(evt);
            }
        });

        buttonOpen.setBackground(new java.awt.Color(0, 0, 0));
        buttonOpen.setFont(new java.awt.Font("Tahoma", 1, 14));
        org.openide.awt.Mnemonics.setLocalizedText(buttonOpen, org.openide.util.NbBundle.getMessage(MenuFoodTableTopComponent.class, "MenuFoodTableTopComponent.buttonOpen.text")); // NOI18N
        buttonOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOpenActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 0, 0));
        org.openide.awt.Mnemonics.setLocalizedText(jButton4, org.openide.util.NbBundle.getMessage(MenuFoodTableTopComponent.class, "MenuFoodTableTopComponent.jButton4.text")); // NOI18N

        jButton6.setBackground(new java.awt.Color(0, 0, 0));
        org.openide.awt.Mnemonics.setLocalizedText(jButton6, org.openide.util.NbBundle.getMessage(MenuFoodTableTopComponent.class, "MenuFoodTableTopComponent.jButton6.text")); // NOI18N

        jButton5.setBackground(new java.awt.Color(0, 0, 0));
        org.openide.awt.Mnemonics.setLocalizedText(jButton5, org.openide.util.NbBundle.getMessage(MenuFoodTableTopComponent.class, "MenuFoodTableTopComponent.jButton5.text")); // NOI18N

        jButton7.setBackground(new java.awt.Color(0, 0, 0));
        org.openide.awt.Mnemonics.setLocalizedText(jButton7, org.openide.util.NbBundle.getMessage(MenuFoodTableTopComponent.class, "MenuFoodTableTopComponent.jButton7.text")); // NOI18N

        javax.swing.GroupLayout panle_Button_MainMenuLayout = new javax.swing.GroupLayout(panle_Button_MainMenu);
        panle_Button_MainMenu.setLayout(panle_Button_MainMenuLayout);
        panle_Button_MainMenuLayout.setHorizontalGroup(
            panle_Button_MainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panle_Button_MainMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonHide, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );
        panle_Button_MainMenuLayout.setVerticalGroup(
            panle_Button_MainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panle_Button_MainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonHide, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.setBackground(new java.awt.Color(103, 153, 255));

        panelKhuVuc.setBackground(new java.awt.Color(170, 122, 66));

        org.openide.awt.Mnemonics.setLocalizedText(jRadioButton2, org.openide.util.NbBundle.getMessage(MenuFoodTableTopComponent.class, "MenuFoodTableTopComponent.jRadioButton2.text")); // NOI18N
        jRadioButton2.setBorderPainted(true);
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jRadioButton3, org.openide.util.NbBundle.getMessage(MenuFoodTableTopComponent.class, "MenuFoodTableTopComponent.jRadioButton3.text")); // NOI18N
        jRadioButton3.setBorderPainted(true);
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jRadioButton4, org.openide.util.NbBundle.getMessage(MenuFoodTableTopComponent.class, "MenuFoodTableTopComponent.jRadioButton4.text")); // NOI18N
        jRadioButton4.setBorderPainted(true);
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jRadioButton5, org.openide.util.NbBundle.getMessage(MenuFoodTableTopComponent.class, "MenuFoodTableTopComponent.jRadioButton5.text")); // NOI18N
        jRadioButton5.setBorderPainted(true);
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelKhuVucLayout = new javax.swing.GroupLayout(panelKhuVuc);
        panelKhuVuc.setLayout(panelKhuVucLayout);
        panelKhuVucLayout.setHorizontalGroup(
            panelKhuVucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKhuVucLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelKhuVucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        panelKhuVucLayout.setVerticalGroup(
            panelKhuVucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKhuVucLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(107, Short.MAX_VALUE))
        );

        jScrollPane1.setBackground(new java.awt.Color(170, 122, 66));

        jPanel3.setBackground(new java.awt.Color(170, 122, 66));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 585, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 317, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelKhuVuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelKhuVuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
        );

        org.openide.awt.Mnemonics.setLocalizedText(jButton8, org.openide.util.NbBundle.getMessage(MenuFoodTableTopComponent.class, "MenuFoodTableTopComponent.jButton8.text")); // NOI18N

        javax.swing.GroupLayout jpanelLayout = new javax.swing.GroupLayout(jpanel);
        jpanel.setLayout(jpanelLayout);
        jpanelLayout.setHorizontalGroup(
            jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panle_Button_MainMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelLayout.createSequentialGroup()
                .addContainerGap(635, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jpanelLayout.setVerticalGroup(
            jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panle_Button_MainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        jRadioButton2.setSelected(false);
        jRadioButton4.setSelected(false);
        jRadioButton5.setSelected(false);
        TableEat_B tableEat_B = new TableEat_B();
        jPanel3.add(tableEat_B);
        jScrollPane1.setViewportView(tableEat_B);
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        jRadioButton2.setSelected(false);
        jRadioButton3.setSelected(false);
        jRadioButton5.setSelected(false);
        TableEat_Vip tableEat_Vip = new TableEat_Vip();
        jPanel3.add(tableEat_Vip);
        jScrollPane1.setViewportView(tableEat_Vip);
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        loadTableEat();        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        jRadioButton2.setSelected(false);
        jRadioButton3.setSelected(false);
        jRadioButton4.setSelected(false);
        JPanel panleTong = new JPanel();
        panleTong.setLayout(new GridLayout(2, 2));
        panleTong.add(tableEat_A);
        panleTong.add(tableEat_B);
        panleTong.add(tableEat_Vip);
        jPanel3.removeAll();
        jPanel3.setLayout(new GridLayout());
        jPanel3.add(panleTong);
        
        panleTong.setBackground(new Color (170,122,66));
       // panleTong.add(panleTong);
        jScrollPane1.setBackground( new Color (170,122,66));
          jScrollPane1.setViewportView(jPanel3);
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void buttonHideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHideActionPerformed
      buttonHide.setText("Hiển thị bàn có khách");
    }//GEN-LAST:event_buttonHideActionPerformed

    private void buttonOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOpenActionPerformed
      buttonOpen.setText("Bàn chính");
    }//GEN-LAST:event_buttonOpenActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonHide;
    private javax.swing.JButton buttonOpen;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpanel;
    private javax.swing.JPanel panelKhuVuc;
    private javax.swing.JPanel panle_Button_MainMenu;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
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

    public void loadTableEat() {
        jRadioButton2.setSelected(true);
        jRadioButton3.setSelected(false);
        jRadioButton4.setSelected(false);
        jRadioButton5.setSelected(false);

        TableEat_A tableEate_A = new TableEat_A();
        jPanel3.add(tableEate_A);
        jScrollPane1.setViewportView(tableEate_A);
    }
}
