/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.ui.main.product;

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
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.WindowManager;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnableButton;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnableTable;
import vn.com.hkt.pilot.enterprise.viewer.api.ResetCookie;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.product.viewer.api.CreateKeyProduct;
import vn.com.hkt.pilot.product.viewer.api.IProductCreater;
import vn.com.hkt.pilot.product.viewer.api.IProductExtCreater;
import vn.com.hkt.pilot.toobar.api.IResetFontSize;
import vn.com.hkt.ui.main.ui.api.creater.ISaveBasic;
import vn.com.hkt.ui.main.ui.api.creater.ISaveExtention;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//vn.com.hkt.ui.main.product//ProductCreator//EN",
autostore = false)
@TopComponent.Description(preferredID = "ProductCreatorTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "vn.com.hkt.ui.main.product.ProductCreatorTopComponent")
@ActionReference(path = "Menu/Nhập Số Liệu" /*, position = 333 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_ProductCreatorAction",
preferredID = "ProductCreatorTopComponent")
@ServiceProvider(service = IEnableButton.class)
public final class ProductCreatorTopComponent extends TopComponent implements IEnableButton {

    private JPanel panel04 = null;
    private JPanel panel418 = null;
    private JPanel panel417 = null;
    private JPanel panel419 = null;
    private JPanel panel43 = null;
    private Collection<? extends IProductExtCreater> productExtCreater;
    private IProductCreater productCreater;
    private static boolean isEdit = false;
    private static boolean isSave = true;
    private ProductTutorialCreatorTopComponent tc = (ProductTutorialCreatorTopComponent) WindowManager.getDefault().findTopComponent("ProductTutorialCreatorTopComponent");
    private List<JTable> listtTables = new ArrayList<JTable>();

    public ProductCreatorTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(ProductCreatorTopComponent.class, "CTL_ProductCreatorTopComponent"));
        setToolTipText(NbBundle.getMessage(ProductCreatorTopComponent.class, "HINT_ProductCreatorTopComponent"));
        panelForm.setLayout(new GridLayout());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
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

        buttonEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/modify1.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(buttonEdit, org.openide.util.NbBundle.getMessage(ProductCreatorTopComponent.class, "ProductCreatorTopComponent.buttonEdit.text")); // NOI18N
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
        org.openide.awt.Mnemonics.setLocalizedText(buttonExit, org.openide.util.NbBundle.getMessage(ProductCreatorTopComponent.class, "ProductCreatorTopComponent.buttonExit.text")); // NOI18N
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
        org.openide.awt.Mnemonics.setLocalizedText(buttonHelp, org.openide.util.NbBundle.getMessage(ProductCreatorTopComponent.class, "ProductCreatorTopComponent.buttonHelp.text")); // NOI18N
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
        org.openide.awt.Mnemonics.setLocalizedText(buttonSaveDS, org.openide.util.NbBundle.getMessage(ProductCreatorTopComponent.class, "ProductCreatorTopComponent.buttonSaveDS.text")); // NOI18N
        buttonSaveDS.setPreferredSize(new java.awt.Dimension(124, 30));
        buttonSaveDS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonSaveDSMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonSaveDSMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                buttonSaveDSMouseReleased(evt);
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
        org.openide.awt.Mnemonics.setLocalizedText(buttonSave, org.openide.util.NbBundle.getMessage(ProductCreatorTopComponent.class, "ProductCreatorTopComponent.buttonSave.text")); // NOI18N
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
        org.openide.awt.Mnemonics.setLocalizedText(buttonReset, org.openide.util.NbBundle.getMessage(ProductCreatorTopComponent.class, "ProductCreatorTopComponent.buttonReset.text")); // NOI18N
        buttonReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonResetMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonResetMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                buttonResetMouseReleased(evt);
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
                .addContainerGap(280, Short.MAX_VALUE)
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
                .addGap(23, 23, 23)
                .addGroup(panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSaveDS, javax.swing.GroupLayout.PREFERRED_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(buttonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 30, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelForm.setPreferredSize(new java.awt.Dimension(827, 590));
        panelForm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelFormMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panelFormLayout = new javax.swing.GroupLayout(panelForm);
        panelForm.setLayout(panelFormLayout);
        panelFormLayout.setHorizontalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1048, Short.MAX_VALUE)
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panelForm);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1067, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonEditMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonEditMousePressed

        buttonEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/modify3click.png")));
        buttonEdit.setMargin(new Insets(2, 3, 1, 1));
		}//GEN-LAST:event_buttonEditMousePressed

    private void buttonEditMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonEditMouseReleased

        buttonEdit.setMargin(new Insets(0, 0, 0, 0));
        buttonEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/modify2over.png")));
		}//GEN-LAST:event_buttonEditMouseReleased

    private void buttonEditMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonEditMouseExited

        buttonEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/modify1.png")));
		}//GEN-LAST:event_buttonEditMouseExited

    private void buttonEditMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonEditMouseMoved

        buttonEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/modify2over.png")));
		}//GEN-LAST:event_buttonEditMouseMoved

    private void buttonExitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonExitMousePressed

        buttonExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/cancel3click.png")));
        buttonExit.setMargin(new Insets(2, 3, 1, 1));
		}//GEN-LAST:event_buttonExitMousePressed

    private void buttonExitMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonExitMouseReleased

        buttonExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/cancel2over.png")));
        buttonExit.setMargin(new Insets(0, 0, 0, 0));
		}//GEN-LAST:event_buttonExitMouseReleased

    private void buttonExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonExitMouseExited

        buttonExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/cancel1.png")));
		}//GEN-LAST:event_buttonExitMouseExited

    private void buttonExitMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonExitMouseMoved

        buttonExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/cancel2over.png")));
		}//GEN-LAST:event_buttonExitMouseMoved

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed

        this.close();     }//GEN-LAST:event_buttonExitActionPerformed

    private void buttonHelpMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonHelpMousePressed

        buttonHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/help3click.png")));
        buttonHelp.setMargin(new Insets(2, 3, 1, 1));
		}//GEN-LAST:event_buttonHelpMousePressed

    private void buttonHelpMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonHelpMouseReleased

        buttonHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/help2over.png")));
        buttonHelp.setMargin(new Insets(0, 0, 0, 0));
		}//GEN-LAST:event_buttonHelpMouseReleased

    private void buttonHelpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonHelpMouseExited

        buttonHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/help1.png")));
		}//GEN-LAST:event_buttonHelpMouseExited

    private void buttonHelpMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonHelpMouseMoved

        buttonHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/help2over.png")));
		}//GEN-LAST:event_buttonHelpMouseMoved

    private void buttonSaveDSMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveDSMousePressed

        buttonSaveDS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/save_list3click.png")));
        buttonSaveDS.setMargin(new Insets(2, 3, 1, 1));
		}//GEN-LAST:event_buttonSaveDSMousePressed

    private void buttonSaveDSMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveDSMouseReleased

        buttonSaveDS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/save_list2over.png")));
        buttonSaveDS.setMargin(new Insets(0, 0, 0, 0));
		}//GEN-LAST:event_buttonSaveDSMouseReleased

    private void buttonSaveDSMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveDSMouseExited

        buttonSaveDS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/save_list1.png")));
		}//GEN-LAST:event_buttonSaveDSMouseExited

    private void buttonSaveDSMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveDSMouseMoved

        buttonSaveDS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/save_list2over.png")));
		}//GEN-LAST:event_buttonSaveDSMouseMoved

    private void buttonSaveDSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveDSActionPerformed
        save();
       // reset();
       // this.close();
        TopComponent enterComponent = WindowManager.getDefault().findTopComponent("ProductViewerTopComponent");
        enterComponent.open();
        enterComponent.requestActive();
    }//GEN-LAST:event_buttonSaveDSActionPerformed

    private void buttonSaveMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMousePressed

        buttonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/save3click.png")));
        buttonSave.setMargin(new Insets(2, 3, 1, 1));
		}//GEN-LAST:event_buttonSaveMousePressed

    private void buttonSaveMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseReleased

        buttonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/save2over.png")));
        buttonSave.setMargin(new Insets(0, 0, 0, 0));
		}//GEN-LAST:event_buttonSaveMouseReleased

    private void buttonSaveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseExited

        buttonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/save1.png")));
		}//GEN-LAST:event_buttonSaveMouseExited

    private void buttonSaveMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseMoved

        buttonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/save2over.png")));
	}//GEN-LAST:event_buttonSaveMouseMoved

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
        save();
        reset();
        this.requestActive();
	}//GEN-LAST:event_buttonSaveActionPerformed

    private void buttonResetMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonResetMousePressed

        buttonReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/refresh3click.png")));
        buttonReset.setMargin(new Insets(2, 3, 1, 1));
		}//GEN-LAST:event_buttonResetMousePressed

    private void buttonResetMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonResetMouseReleased

        buttonReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/refresh2over.png")));
        buttonReset.setMargin(new Insets(0, 0, 0, 0));
		}//GEN-LAST:event_buttonResetMouseReleased

    private void buttonResetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonResetMouseExited
        buttonReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/refresh1.png")));
    }//GEN-LAST:event_buttonResetMouseExited

    private void buttonResetMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonResetMouseMoved

        buttonReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/refresh2over.png")));
		}//GEN-LAST:event_buttonResetMouseMoved

    private void buttonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonResetActionPerformed
        reset();
    }//GEN-LAST:event_buttonResetActionPerformed

    private void buttonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHelpActionPerformed
        tc.open();
        tc.requestActive();
    }//GEN-LAST:event_buttonHelpActionPerformed

    private void panelFormMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFormMousePressed

        if (tc.getPanel04() == null) {
            tc.getPanelChecbox().removeAll();
            tc.loadFormExtension();
        }
    }//GEN-LAST:event_panelFormMousePressed

    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed
        // TODO add your handling code here:
        isSave = !isSave;
        setEnableTable();
        changeEnableButtonSave();
    }//GEN-LAST:event_buttonEditActionPerformed
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

    public JButton getButtonSave() {
        return buttonSave;
    }

    public JButton getButtonSaveDS() {
        return buttonSaveDS;
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
        productCreater = Lookup.getDefault().lookup(IProductCreater.class);
        productExtCreater = Lookup.getDefault().lookupAll(IProductExtCreater.class);
        for (int i = 0; i < productCreater.getTables().size(); i++) {
            listtTables.add(productCreater.getTables().get(i));
        }
        if (productCreater instanceof ResetCookie) {
            try {
                ((ResetCookie) productCreater).resetCookie();
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        for (IProductExtCreater iex : productExtCreater) {
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
        if (productCreater != null) {
            if (panel04 == null) {
                panel04 = productCreater.getProductCreater();
            }
        }
        if (productExtCreater != null) {
            for (IProductExtCreater extCreator : productExtCreater) {
                double index = extCreator.getLevelNumber();
                if (index == 4.18) {
                    if (panel418 == null) {
                        panel418 = extCreator.getProductExtCreater();
                    }
                }
                if (index == 4.19) {
                    if (panel419 == null) {
                        panel419 = extCreator.getProductExtCreater();
                    }
                }
                if (index == 4.17) {
                    if (panel417 == null) {
                        panel417 = extCreator.getProductExtCreater();
                    }
                }
                if (index == 4.3) {
                    if (panel43 == null) {
                        panel43 = extCreator.getProductExtCreater();
                    }
                }
            }

        }
        loadFormAll();
        createKey();
        isSave = true;
        isEdit = false;
        changeEnableButtonSave();
        changeEnableButtonEdit();
    }

    @Override
    public void componentClosed() {
        panelForm.removeAll();
        isEdit = false;
        isSave = true;
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

    private void save() {
        if (productCreater instanceof ISaveBasic) {
            ISaveBasic saveBasic = (ISaveBasic) productCreater;
            IEntity entity = saveBasic.save();
            if (entity != null) {
                for (IProductExtCreater iee : productExtCreater) {
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
        if (productCreater instanceof ISaveBasic) {
            ISaveBasic saveBasic = (ISaveBasic) productCreater;
            saveBasic.reset();
            for (IProductExtCreater iee : productExtCreater) {
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

    public void loadFormAll() {
        panelForm.setLayout(new GridLayout(3, 2, 10, WIDTH));
        panelForm.add(panel04);
        panelForm.add(panel418);
        panelForm.add(panel419);
        panelForm.add(panel417);
        panelForm.add(panel43);
        JPanel panel = new JPanel();
        panelForm.add(panel);
    }

    public void loadForm() {
        List<JPanel> listPanel = new ArrayList<JPanel>();
        if (tc.getCheckBoxSB04().isSelected()) {
            listPanel.add(panel04);
        }
        if (tc.getChecBoxSB41().isSelected()) {
            listPanel.add(panel418);
            listPanel.add(panel419);
            listPanel.add(panel417);
        }
        if (tc.getCheckBoxSB43().isSelected()) {
            listPanel.add(panel43);
        }

        if (listPanel.size() == 5 || listPanel.size() == 4) {
            panelForm.setLayout(new GridLayout(3, 2, 10, WIDTH));
        }
        if (listPanel.size() == 1 || listPanel.size() == 2) {
            panelForm.setLayout(new GridLayout(WIDTH, WIDTH, 10, WIDTH));
        }


        for (int i = 0; i < listPanel.size(); i++) {
            panelForm.add(listPanel.get(i));
        }
        if (listPanel.size() == 1 || listPanel.size() == 5) {
            JPanel panel = new JPanel();
            panelForm.add(panel);
        }
        if (listPanel.size() == 4) {
            JPanel panel = new JPanel();
            panelForm.add(panel);
            JPanel panel1 = new JPanel();
            panelForm.add(panel1);
        }
    }

    public JPanel getPanelForm() {
        return panelForm;
    }

    private void createKey() {
        if (productCreater instanceof CreateKeyProduct) {
            ((CreateKeyProduct) productCreater).createKey();
        }
    }

    public void reLoadColorRowTable() {
        if (productCreater instanceof IResetFontSize) {
            ((IResetFontSize) productCreater).resetColorRowTable();
        }
        for (IProductExtCreater iexc : productExtCreater) {
            if (iexc instanceof IResetFontSize) {
                ((IResetFontSize) iexc).resetColorRowTable();
            }
        }
        Collection<? extends IResetFontSize> allSave = Lookup.getDefault().lookupAll(IResetFontSize.class);
        for (IResetFontSize editCookie : allSave) {
            editCookie.resetColorRowTable();
        }
    }

    private void reLoadFont() {
        if (productCreater instanceof IResetFontSize) {
            ((IResetFontSize) productCreater).resetFont();
        }
        for (IProductExtCreater iexc : productExtCreater) {
            if (iexc instanceof IResetFontSize) {
                ((IResetFontSize) iexc).resetFont();
            }
        }
    }

    private void reLoadColor() {
        if (productCreater instanceof IResetFontSize) {
            ((IResetFontSize) productCreater).resetColorRowTable();
        }
        for (IProductExtCreater iexc : productExtCreater) {
            if (iexc instanceof IResetFontSize) {
                ((IResetFontSize) iexc).resetColorRowTable();
            }
        }
    }

    private void reLoadSize() {
        if (productCreater instanceof IResetFontSize) {
            ((IResetFontSize) productCreater).resetSize();
        }
        for (IProductExtCreater iexc : productExtCreater) {
            if (iexc instanceof IResetFontSize) {
                ((IResetFontSize) iexc).resetSize();
            }
        }
    }

    private void reLoadColorWord() {
        if (productCreater instanceof IResetFontSize) {
            ((IResetFontSize) productCreater).resetColorWord();
        }
        for (IProductExtCreater iexc : productExtCreater) {
            if (iexc instanceof IResetFontSize) {
                ((IResetFontSize) iexc).resetColorWord();
            }
        }
    }

    private void reLoadColorTitle() {
        if (productCreater instanceof IResetFontSize) {
            ((IResetFontSize) productCreater).resetColorTitle();
        }
        for (IProductExtCreater iexc : productExtCreater) {
            if (iexc instanceof IResetFontSize) {
                ((IResetFontSize) iexc).resetColorTitle();
            }
        }
    }

    private void reLoadColorMouse() {
        if (productCreater instanceof IResetFontSize) {
            ((IResetFontSize) productCreater).resetColorMouse();
        }
        for (IProductExtCreater iexc : productExtCreater) {
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
        productCreater = Lookup.getDefault().lookup(IProductCreater.class);
        productExtCreater = Lookup.getDefault().lookupAll(IProductExtCreater.class);
        if (productCreater instanceof IEnableTable) {
            try {
                ((IEnableTable) productCreater).enableTable(isSave);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        for (IProductExtCreater iexc : productExtCreater) {
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
