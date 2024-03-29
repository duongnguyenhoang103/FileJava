    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.ui.main.enterprise;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.WindowManager;
import vn.com.hkt.pilot.enterprise.viewer.api.CreateKeyEnterprise;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnableButton;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnableTable;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnterpriseCreator;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnterpriseExtCreator;
import vn.com.hkt.pilot.enterprise.viewer.api.ResetCookie;
import vn.com.hkt.pilot.toobar.api.IResetFontSize;
import vn.com.hkt.ui.main.ui.api.creater.ISaveBasic;
import vn.com.hkt.ui.main.ui.api.creater.ISaveExtention;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//vn.com.hkt.ui.main//EnterpriseCreator//EN",
autostore = false)
@TopComponent.Description(preferredID = "EnterpriseCreatorTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "vn.com.hkt.ui.main.EnterpriseCreatorTopComponent")
@ActionReference(path = "Menu/Nhập Số Liệu" /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_EnterpriseCreatorAction",
preferredID = "EnterpriseCreatorTopComponent")
@ServiceProvider(service = IEnableButton.class)
public class EnterpriseCreatorTopComponent extends TopComponent implements IEnableButton {

    private IEnterpriseCreator iec = Lookup.getDefault().lookup(IEnterpriseCreator.class);
    private Collection<? extends IEnterpriseExtCreator> ieec = Lookup.getDefault().lookupAll(IEnterpriseExtCreator.class);
    private JPanel panelSB01 = null;
    private JPanel panelSB12 = null;
    private JPanel panelSB111 = null;
    private JPanel panelSB112 = null;
    private JPanel panelSB13 = null;
    private JPanel panelSB122 = null;
    private static boolean isEdit = false;
    private static boolean isSave = true;
    private EnterpriseTutorialCreatorTopComponent tc = (EnterpriseTutorialCreatorTopComponent) WindowManager.getDefault().findTopComponent("EnterpriseTutorialCreatorTopComponent");
    private List<JTable> listtTables = new ArrayList<JTable>();

    public EnterpriseCreatorTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(EnterpriseCreatorTopComponent.class, "CTL_EnterpriseCreatorTopComponent"));
        setToolTipText(NbBundle.getMessage(EnterpriseCreatorTopComponent.class, "HINT_EnterpriseCreatorTopComponent"));
        panelForm.setLayout(new GridLayout());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelButton = new javax.swing.JPanel();
        buttonEdit = new javax.swing.JButton();
        buttonExit = new javax.swing.JButton();
        buttonHelp = new javax.swing.JButton();
        buttonSaveDS = new javax.swing.JButton();
        buttonSave = new javax.swing.JButton();
        buttonReset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelForm = new javax.swing.JPanel();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        buttonEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/modify1.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(buttonEdit, org.openide.util.NbBundle.getMessage(EnterpriseCreatorTopComponent.class, "EnterpriseCreatorTopComponent.buttonEdit.text")); // NOI18N
        buttonEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonEditMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonEditMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                buttonEditMouseReleased(evt);
            }
        });
        buttonEdit.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                buttonEditMouseMoved(evt);
            }
        });
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });

        buttonExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/cancel1.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(buttonExit, org.openide.util.NbBundle.getMessage(EnterpriseCreatorTopComponent.class, "EnterpriseCreatorTopComponent.buttonExit.text")); // NOI18N
        buttonExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonExitMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                buttonExitMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonExitMouseExited(evt);
            }
        });
        buttonExit.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                buttonExitMouseMoved(evt);
            }
        });
        buttonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExitActionPerformed(evt);
            }
        });

        buttonHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/help1.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(buttonHelp, org.openide.util.NbBundle.getMessage(EnterpriseCreatorTopComponent.class, "EnterpriseCreatorTopComponent.buttonHelp.text")); // NOI18N
        buttonHelp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonHelpMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonHelpMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                buttonHelpMouseReleased(evt);
            }
        });
        buttonHelp.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                buttonHelpMouseMoved(evt);
            }
        });
        buttonHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHelpActionPerformed(evt);
            }
        });

        buttonSaveDS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/save_list1.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(buttonSaveDS, org.openide.util.NbBundle.getMessage(EnterpriseCreatorTopComponent.class, "EnterpriseCreatorTopComponent.buttonSaveDS.text")); // NOI18N
        buttonSaveDS.setPreferredSize(new java.awt.Dimension(124, 30));
        buttonSaveDS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonSaveDSMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                buttonSaveDSMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonSaveDSMouseExited(evt);
            }
        });
        buttonSaveDS.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                buttonSaveDSMouseMoved(evt);
            }
        });
        buttonSaveDS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveDSActionPerformed(evt);
            }
        });

        buttonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/save1.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(buttonSave, org.openide.util.NbBundle.getMessage(EnterpriseCreatorTopComponent.class, "EnterpriseCreatorTopComponent.buttonSave.text")); // NOI18N
        buttonSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonSaveMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonSaveMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                buttonSaveMouseReleased(evt);
            }
        });
        buttonSave.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                buttonSaveMouseMoved(evt);
            }
        });
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });

        buttonReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/refresh1.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(buttonReset, org.openide.util.NbBundle.getMessage(EnterpriseCreatorTopComponent.class, "EnterpriseCreatorTopComponent.buttonReset.text")); // NOI18N
        buttonReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                buttonResetMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonResetMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonResetMousePressed(evt);
            }
        });
        buttonReset.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                buttonResetMouseMoved(evt);
            }
        });
        buttonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelButtonLayout = new javax.swing.GroupLayout(panelButton);
        panelButton.setLayout(panelButtonLayout);
        panelButtonLayout.setHorizontalGroup(
            panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelButtonLayout.createSequentialGroup()
                .addContainerGap(227, Short.MAX_VALUE)
                .addComponent(buttonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSaveDS, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelButtonLayout.setVerticalGroup(
            panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelButtonLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSaveDS, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(buttonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelForm.setMinimumSize(new java.awt.Dimension(0, 0));
        panelForm.setPreferredSize(new java.awt.Dimension(888, 600));
        panelForm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelFormMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panelFormLayout = new javax.swing.GroupLayout(panelForm);
        panelForm.setLayout(panelFormLayout);
        panelFormLayout.setHorizontalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 995, Short.MAX_VALUE)
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panelForm);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1014, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
        save();
        reset();
        this.requestActive();
    }//GEN-LAST:event_buttonSaveActionPerformed

    private void buttonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonResetActionPerformed
        reset();
    }//GEN-LAST:event_buttonResetActionPerformed

    private void buttonSaveDSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveDSActionPerformed
        save();
       // reset();
      //  this.close();
        TopComponent enterComponent = WindowManager.getDefault().findTopComponent("EnterpriseViewerTopComponent");
        enterComponent.open();
        enterComponent.requestActive();

    }//GEN-LAST:event_buttonSaveDSActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        this.close();
    }//GEN-LAST:event_buttonExitActionPerformed

    private void buttonSaveMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseMoved
        buttonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/save2over.png")));
    }//GEN-LAST:event_buttonSaveMouseMoved

    private void buttonSaveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseExited
        buttonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/save1.png")));
    }//GEN-LAST:event_buttonSaveMouseExited

    private void buttonSaveMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMousePressed
        buttonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/save3click.png")));
        buttonSave.setMargin(new Insets(2, 3, 1, 1));
    }//GEN-LAST:event_buttonSaveMousePressed

    private void buttonEditMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonEditMouseMoved
        buttonEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/modify2over.png")));
    }//GEN-LAST:event_buttonEditMouseMoved

    private void buttonEditMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonEditMouseExited
        buttonEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/modify1.png")));
    }//GEN-LAST:event_buttonEditMouseExited

    private void buttonEditMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonEditMousePressed
        buttonEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/modify3click.png")));
        buttonEdit.setMargin(new Insets(2, 3, 1, 1));
    }//GEN-LAST:event_buttonEditMousePressed

    private void buttonResetMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonResetMouseMoved
        buttonReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/refresh2over.png")));
    }//GEN-LAST:event_buttonResetMouseMoved

    private void buttonResetMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonResetMousePressed
        buttonReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/refresh3click.png")));
        buttonReset.setMargin(new Insets(2, 3, 1, 1));
    }//GEN-LAST:event_buttonResetMousePressed

    private void buttonResetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonResetMouseExited
        buttonReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/refresh1.png")));

    }//GEN-LAST:event_buttonResetMouseExited

    private void buttonSaveDSMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveDSMouseMoved
        buttonSaveDS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/save_list2over.png")));
    }//GEN-LAST:event_buttonSaveDSMouseMoved

    private void buttonSaveDSMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveDSMousePressed
        buttonSaveDS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/save_list3click.png")));
        buttonSaveDS.setMargin(new Insets(2, 3, 1, 1));
    }//GEN-LAST:event_buttonSaveDSMousePressed

    private void buttonExitMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonExitMouseMoved
        buttonExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/cancel2over.png")));
    }//GEN-LAST:event_buttonExitMouseMoved

    private void buttonExitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonExitMousePressed
        buttonExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/cancel3click.png")));
        buttonExit.setMargin(new Insets(2, 3, 1, 1));
    }//GEN-LAST:event_buttonExitMousePressed

    private void buttonSaveDSMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveDSMouseExited
        buttonSaveDS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/save_list1.png")));
    }//GEN-LAST:event_buttonSaveDSMouseExited

    private void buttonExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonExitMouseExited
        buttonExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/cancel1.png")));
    }//GEN-LAST:event_buttonExitMouseExited

    private void buttonHelpMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonHelpMouseMoved
        buttonHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/help2over.png")));
    }//GEN-LAST:event_buttonHelpMouseMoved

    private void buttonHelpMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonHelpMousePressed
        buttonHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/help3click.png")));
        buttonHelp.setMargin(new Insets(2, 3, 1, 1));
    }//GEN-LAST:event_buttonHelpMousePressed

    private void buttonResetMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonResetMouseReleased
        buttonReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/refresh2over.png")));
        buttonReset.setMargin(new Insets(0, 0, 0, 0));
    }//GEN-LAST:event_buttonResetMouseReleased

    private void buttonEditMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonEditMouseReleased
        buttonEdit.setMargin(new Insets(0, 0, 0, 0));
        buttonEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/modify2over.png")));
    }//GEN-LAST:event_buttonEditMouseReleased

    private void buttonSaveMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseReleased
        buttonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/save2over.png")));
        buttonSave.setMargin(new Insets(0, 0, 0, 0));
    }//GEN-LAST:event_buttonSaveMouseReleased

    private void buttonSaveDSMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveDSMouseReleased
        buttonSaveDS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/save_list2over.png")));
        buttonSaveDS.setMargin(new Insets(0, 0, 0, 0));
    }//GEN-LAST:event_buttonSaveDSMouseReleased

    private void buttonExitMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonExitMouseReleased
        buttonExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/cancel2over.png")));
        buttonExit.setMargin(new Insets(0, 0, 0, 0));
    }//GEN-LAST:event_buttonExitMouseReleased

    private void buttonHelpMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonHelpMouseReleased
        buttonHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/help2over.png")));
        buttonHelp.setMargin(new Insets(0, 0, 0, 0));
    }//GEN-LAST:event_buttonHelpMouseReleased

    private void buttonHelpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonHelpMouseExited
        buttonHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/help1.png")));
    }//GEN-LAST:event_buttonHelpMouseExited

    private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelpActionPerformed
        tc.open();
        tc.requestActive();   // TODO add your handling code here:
    }//GEN-LAST:event_buttonHelpActionPerformed

    public JButton getButtonSave() {
        return buttonSave;
    }

    public JButton getButtonSaveDS() {
        return buttonSaveDS;
    }

private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed
// TODO add your handling code here:
    isSave = !isSave;
    setEnableTable();
    changeEnableButtonSave();
}//GEN-LAST:event_buttonEditActionPerformed

    private void panelFormMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFormMousePressed

        if (tc.getPanelEnterprise1() == null || tc.getPanelEnterprise2() == null) {
            tc.getPanelChecbox().removeAll();
            tc.loadFormExtension();
        }

    }//GEN-LAST:event_panelFormMousePressed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentShown
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonReset;
    private javax.swing.JButton buttonSave;
    private javax.swing.JButton buttonSaveDS;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelButton;
    private javax.swing.JPanel panelForm;
    // End of variables declaration//GEN-END:variables

    public JPanel getPanelEnterprise1() {
        return panelSB01;
    }

    public JPanel getPanelEnterprise2() {
        return panelSB12;
    }

    public JPanel getPanelForm() {
        return panelForm;
    }

    public JButton getButtonEdit() {
        return buttonEdit;
    }

    @Override
    public void componentOpened() {
        Set<TopComponent> tcs = WindowManager.getDefault().getRegistry().getOpened();
        for (TopComponent tc : tcs) {
            tc.close();
        }
        loadDefault();
        reset();
    }

    @Override
    public void componentClosed() {
        tc.close();
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

    private void loadDefault() {
        for (int i = 0; i < iec.getTables().size(); i++) {
            listtTables.add(iec.getTables().get(i));
        }
        if (iec instanceof ResetCookie) {
            try {
                ((ResetCookie) iec).resetCookie();
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        for (IEnterpriseExtCreator iex : ieec) {
            for (int i = 0; i < iex.getTables().size(); i++) {
                listtTables.add(iex.getTables().get(i));
            }
            if (iex instanceof ResetCookie) {
                try {
                    ((ResetCookie) iex).resetCookie();
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
        setFocusable();
        tc.open();
        if (ieec != null) {
            for (IEnterpriseExtCreator extCreator : ieec) {
                double index = extCreator.getLevelNumber();
                if (index == 1.11) {
                    if (panelSB111 == null) {
                        panelSB111 = extCreator.getEnterpriseExtCreator();
                    }
                }
                if (index == 1.12) {
                    if (panelSB112 == null) {
                        panelSB112 = extCreator.getEnterpriseExtCreator();
                    }
                }
                if (index == 1.21) {
                    if (panelSB12 == null) {
                        panelSB12 = extCreator.getEnterpriseExtCreator();
                    }
                }
                if (index == 1.3) {
                    if (panelSB13 == null) {
                        panelSB13 = extCreator.getEnterpriseExtCreator();
                    }
                }
                if (index == 1.22) {
                    if (panelSB122 == null) {
                        panelSB122 = extCreator.getEnterpriseExtCreator();
                    }
                }
            }
        }

        if (iec != null) {
            if (panelSB01 == null) {
                panelSB01 = iec.getEnterpriseCreator();
            }
        }
        loadForm();
        createKey();
        isSave = true;
        isEdit = false;
        changeEnableButtonSave();
        changeEnableButtonEdit();
    }

    private void save() {
        if (iec instanceof ISaveBasic) {
            ISaveBasic saveBasic = (ISaveBasic) iec;
            IEntity entity = saveBasic.save();
            if (entity != null) {
                for (IEnterpriseExtCreator iee : ieec) {
                    if (iee instanceof ISaveExtention) {
                        ISaveExtention saveExtention = (ISaveExtention) iee;
                        saveExtention.setEntity(entity);
                        saveExtention.save();
                    }
                }
            }
        }
    }

    private void reset() {
        if (iec instanceof ISaveBasic) {
            ISaveBasic saveBasic = (ISaveBasic) iec;
            saveBasic.reset();

            for (IEnterpriseExtCreator iee : ieec) {
                if (iee instanceof ISaveExtention) {
                    ISaveExtention saveExtention = (ISaveExtention) iee;
                    saveExtention.reset();
                }
            }
        }

        // Enable cac nut sau khi reset
        isSave = true;
        isEdit = false;
        changeEnableButtonSave();
        changeEnableButtonEdit();

        createKey();
        reLoadFont();
        reLoadSize();
        reLoadColor();
        reLoadColorMouse();
        reLoadColorTitle();
        reLoadColorWord();

    }

    private void createKey() {
        if (iec instanceof CreateKeyEnterprise) {
            ((CreateKeyEnterprise) iec).createKey();
        }
    }

    public void loadForm() {
        panelForm.removeAll();
        List<JPanel> listPanel = new ArrayList<JPanel>();
        if (tc.getCheckBoxBasic().isSelected()) {
            listPanel.add(panelSB01);
        }
        if (tc.getCheckSB12().isSelected()) {
            listPanel.add(panelSB12);
        }
        if (tc.getCheckBoxSB11().isSelected()) {
            listPanel.add(panelSB112);
        }
        if (tc.getCheckBoxSB13().isSelected()) {
            listPanel.add(panelSB13);
        }
        if (tc.getCheckBoxSB11().isSelected()) {
            listPanel.add(panelSB111);
        }
        if (tc.getCheckSB12().isSelected()) {
            listPanel.add(panelSB122);
        }

        if (listPanel.size() == 6 || listPanel.size() == 3 || listPanel.size() == 4) {
            panelForm.setLayout(new GridLayout(3, 2, 10, WIDTH));
        }
        if (listPanel.size() == 1 || listPanel.size() == 2) {
            panelForm.setLayout(new GridLayout(WIDTH, WIDTH, 10, WIDTH));
        }


        for (int i = 0; i < listPanel.size(); i++) {
            panelForm.add(listPanel.get(i));
        }
        if (listPanel.size() == 1) {
            JPanel panel = new JPanel();
            panelForm.add(panel);
        }
        if (listPanel.size() == 4) {
            JPanel panel = new JPanel();
            panelForm.add(panel);
            JPanel panel1 = new JPanel();
            panelForm.add(panel1);
        }
        if (listPanel.size() == 3) {
            JPanel panel = new JPanel();
            panelForm.add(panel);
            JPanel panel1 = new JPanel();
            panelForm.add(panel1);
            JPanel panel2 = new JPanel();
            panelForm.add(panel2);
        }

    }

    public void reLoadColorRowTable() {
        if (iec instanceof IResetFontSize) {
            ((IResetFontSize) iec).resetColorRowTable();
        }
        for (IEnterpriseExtCreator iexc : ieec) {
            if (iexc instanceof IResetFontSize) {
                ((IResetFontSize) iexc).resetColorRowTable();
            }
        }
    }

    private void reLoadFont() {
        if (iec instanceof IResetFontSize) {
            ((IResetFontSize) iec).resetFont();
        }
        for (IEnterpriseExtCreator iexc : ieec) {
            if (iexc instanceof IResetFontSize) {
                ((IResetFontSize) iexc).resetFont();
            }
        }
    }

    private void reLoadColor() {
        if (iec instanceof IResetFontSize) {
            ((IResetFontSize) iec).resetColorRowTable();
        }
        for (IEnterpriseExtCreator iexc : ieec) {
            if (iexc instanceof IResetFontSize) {
                ((IResetFontSize) iexc).resetColorRowTable();
            }
        }
    }

    private void reLoadSize() {
        if (iec instanceof IResetFontSize) {
            ((IResetFontSize) iec).resetSize();
        }
        for (IEnterpriseExtCreator iexc : ieec) {
            if (iexc instanceof IResetFontSize) {
                ((IResetFontSize) iexc).resetSize();
            }
        }
    }

    private void reLoadColorWord() {
        if (iec instanceof IResetFontSize) {
            ((IResetFontSize) iec).resetColorWord();
        }
        for (IEnterpriseExtCreator iexc : ieec) {
            if (iexc instanceof IResetFontSize) {
                ((IResetFontSize) iexc).resetColorWord();
            }
        }
    }

    private void reLoadColorTitle() {
        if (iec instanceof IResetFontSize) {
            ((IResetFontSize) iec).resetColorTitle();
        }
        for (IEnterpriseExtCreator iexc : ieec) {
            if (iexc instanceof IResetFontSize) {
                ((IResetFontSize) iexc).resetColorTitle();
            }
        }
    }

    private void reLoadColorMouse() {
        if (iec instanceof IResetFontSize) {
            ((IResetFontSize) iec).resetColorMouse();
        }
        for (IEnterpriseExtCreator iexc : ieec) {
            if (iexc instanceof IResetFontSize) {
                ((IResetFontSize) iexc).resetColorMouse();
            }
        }
    }

    private void changeEnableButtonSave() {
        buttonSave.setEnabled(isSave);
        buttonSaveDS.setEnabled(isSave);
    }

    private void changeEnableButtonEdit() {
        setEnableTable();
        buttonEdit.setEnabled(isEdit);
    }

    @Override
    public void enableButton() throws IOException {
        isEdit = true;
        isSave = false;
        changeEnableButtonEdit();
        changeEnableButtonSave();

    }

    private void setEnableTable() {
        if (iec instanceof IEnableTable) {
            try {
                ((IEnableTable) iec).enableTable(isSave);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        for (IEnterpriseExtCreator iexc : ieec) {
            if (iexc instanceof IEnableTable) {
                try {
                    ((IEnableTable) iexc).enableTable(isSave);
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }

    private void setFocusable() {
        for (int i = 0; i < listtTables.size(); i++) {
            listtTables.get(i).addMouseListener(new MyMouseListener(listtTables.get(i), listtTables));
        }
        buttonEdit.addMouseListener(new MyMouseListener(null, listtTables));
        buttonExit.addMouseListener(new MyMouseListener(null, listtTables));
        buttonHelp.addMouseListener(new MyMouseListener(null, listtTables));
        buttonReset.addMouseListener(new MyMouseListener(null, listtTables));
        buttonSave.addMouseListener(new MyMouseListener(null, listtTables));
        buttonSaveDS.addMouseListener(new MyMouseListener(null, listtTables));

    }
}

class MyMouseListener extends MouseAdapter {

    private JTable table = null;
    private List<JTable> listtTables;

    public MyMouseListener(JTable table, List<JTable> listtTables) {
        this.table = table;
        this.listtTables = listtTables;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (int j = 0; j < listtTables.size(); j++) {
            if (listtTables.get(j) != table) {
                TableCellEditor tce = listtTables.get(j).getCellEditor();
                if (tce != null) {
                    tce.stopCellEditing();
                }
                listtTables.get(j).clearSelection();
            } else {
                if (table != null) {
                    table.setCellSelectionEnabled(true);
                    table.setRowSelectionAllowed(true);
                    table.setColumnSelectionAllowed(true);
                    table.changeSelection(table.getSelectedRow(), table.getSelectedColumn(), false, false);
                    table.requestFocus();
                }
            }
        }
    }
}
