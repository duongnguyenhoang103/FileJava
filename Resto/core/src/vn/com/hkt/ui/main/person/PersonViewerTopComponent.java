/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.ui.main.person;

import com.vn.hkt.core.cookie.api.FilterCokieTable;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.windows.WindowManager;
import vn.com.hkt.pilot.enterprise.viewer.api.EditCookieList;
import vn.com.hkt.pilot.enterprise.viewer.api.RemoveCookiePerson;
import vn.com.hkt.pilot.enterprise.viewer.api.SaveCookieList;
import vn.com.hkt.pilot.enterprise.viewer.api.ViewCookieList;
import vn.com.hkt.pilot.person.viewer.api.IPersonExtViewer;
import vn.com.hkt.pilot.person.viewer.api.IPersonViewer;
import vn.com.hkt.pilot.report.api.IReportListGUI;
import vn.com.hkt.pilot.toobar.api.IResetFontSize;
import vn.com.hkt.ui.main.ui.api.ITopComponentList;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//vn.com.hkt.ui.main.person//PersonViewer//EN",
autostore = false)
@TopComponent.Description(preferredID = "PersonViewerTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "vn.com.hkt.ui.main.person.PersonViewerTopComponent")
@ActionReference(path = "Menu/Nhân sự" /*, position = 333 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_PersonViewerAction",
preferredID = "PersonViewerTopComponent")
public final class PersonViewerTopComponent extends TopComponent implements ItemListener, ITopComponentList {

    private Collection<? extends IPersonViewer> personViewer = Lookup.getDefault().lookupAll(IPersonViewer.class);
    private Collection<? extends IPersonExtViewer> personExtViewer = Lookup.getDefault().lookupAll(IPersonExtViewer.class);
    private DefaultComboBoxModel model = null;
    private boolean isEdit = false;
    private IReportListGUI panelReport = null;
    private DefaultComboBoxModel filterModel;
    private Hashtable<Integer, String> hashtable = new Hashtable<Integer, String>();
    private int key = 0;

    public PersonViewerTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(PersonViewerTopComponent.class, "CTL_PersonViewerTopComponent"));
        setToolTipText(NbBundle.getMessage(PersonViewerTopComponent.class, "HINT_PersonViewerTopComponent"));
        buttonDelete.setEnabled(false);
        buttonSave.setEnabled(false);
        cboSelectView.setEnabled(true);
        filterModel = new DefaultComboBoxModel();
        cboTimkiem.setModel(filterModel);
        cboTimkiem.addItemListener(this);

        load();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboTimkiem = new javax.swing.JComboBox();
        txtTimKiem = new javax.swing.JTextField();
        cboSelectView = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        panelForm = new javax.swing.JPanel();
        panleButton = new javax.swing.JPanel();
        buttonEdit = new javax.swing.JButton();
        buttonSave = new javax.swing.JButton();
        buttonExit = new javax.swing.JButton();
        buttonHelp = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(PersonViewerTopComponent.class, "PersonViewerTopComponent.jLabel1.text_1")); // NOI18N

        cboTimkiem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tên", "ID", " " }));

        txtTimKiem.setText(org.openide.util.NbBundle.getMessage(PersonViewerTopComponent.class, "PersonViewerTopComponent.txtTimKiem.text_1")); // NOI18N
        txtTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimKiemMouseClicked(evt);
            }
        });
        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });

        cboSelectView.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cboSelectView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSelectViewActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(PersonViewerTopComponent.class, "PersonViewerTopComponent.jLabel2.text_1")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(cboTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(cboSelectView, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSelectView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelFormLayout = new javax.swing.GroupLayout(panelForm);
        panelForm.setLayout(panelFormLayout);
        panelFormLayout.setHorizontalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 918, Short.MAX_VALUE)
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 181, Short.MAX_VALUE)
        );

        panleButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        buttonEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/modify1.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(buttonEdit, org.openide.util.NbBundle.getMessage(PersonViewerTopComponent.class, "PersonViewerTopComponent.buttonEdit.text_1")); // NOI18N
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });

        buttonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/save1.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(buttonSave, org.openide.util.NbBundle.getMessage(PersonViewerTopComponent.class, "PersonViewerTopComponent.buttonSave.text_1")); // NOI18N
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });

        buttonExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/exit1.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(buttonExit, org.openide.util.NbBundle.getMessage(PersonViewerTopComponent.class, "PersonViewerTopComponent.buttonExit.text_1")); // NOI18N

        buttonHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/help1.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(buttonHelp, org.openide.util.NbBundle.getMessage(PersonViewerTopComponent.class, "PersonViewerTopComponent.buttonHelp.text_1")); // NOI18N

        buttonDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vn/com/hkt/pilot/icon/delete1.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(buttonDelete, org.openide.util.NbBundle.getMessage(PersonViewerTopComponent.class, "PersonViewerTopComponent.buttonDelete.text_1")); // NOI18N
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(PersonViewerTopComponent.class, "PersonViewerTopComponent.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panleButtonLayout = new javax.swing.GroupLayout(panleButton);
        panleButton.setLayout(panleButtonLayout);
        panleButtonLayout.setHorizontalGroup(
            panleButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panleButtonLayout.createSequentialGroup()
                .addContainerGap(211, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panleButtonLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {buttonEdit, jButton1});

        panleButtonLayout.setVerticalGroup(
            panleButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panleButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panleButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonHelp)
                    .addComponent(buttonExit)
                    .addComponent(buttonDelete)
                    .addComponent(buttonSave)
                    .addComponent(buttonEdit)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panleButtonLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {buttonEdit, jButton1});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMouseClicked

        if (txtTimKiem.getText().trim().equals("Tìm theo tên hoặc mã")) {
            txtTimKiem.setText("");
        }
	}//GEN-LAST:event_txtTimKiemMouseClicked

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
        if (txtTimKiem.getText().trim().length() != 0) {
            Object selected = cboSelectView.getSelectedItem();
            if (selected instanceof FilterCokieTable) {
                String value = txtTimKiem.getText().trim();
                ((FilterCokieTable) selected).filterTable(value, key);
            }
        } else {
            loadView();
        }

    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private void cboSelectViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSelectViewActionPerformed
        // TODO add your handling code here:
        for (int i = 0; i < model.getSize(); i++) {
            Object selected = cboSelectView.getItemAt(i);
            if (selected instanceof IPersonExtViewer) {
                IPersonExtViewer p = (IPersonExtViewer) selected;
                p.getTable().clearSelection();
            }
            if (selected instanceof IPersonViewer) {
                IPersonViewer p = (IPersonViewer) selected;
                p.getTable().clearSelection();
            }
        }

        panelForm.setLayout(new GridLayout());
        Object selected = cboSelectView.getSelectedItem();
        int row = cboSelectView.getSelectedIndex();
        if (selected instanceof IPersonExtViewer) {
            IPersonExtViewer p = (IPersonExtViewer) selected;
            fillCombo(p);
            JPanel p2 = p.getPersonExtViewer();
            panelForm.removeAll();
            panelForm.setLayout(new GridLayout());
            panelForm.add(p2);
            this.close();
            this.open();
            cboSelectView.setSelectedIndex(row);
            this.requestActive();
        }
        if (selected instanceof IPersonViewer) {
            IPersonViewer p = (IPersonViewer) selected;
            fillCombo(p);
            JPanel p2 = p.getPersonViewer();
            panelForm.removeAll();
            panelForm.setLayout(new GridLayout());
            panelForm.add(p2);
            this.close();
            this.open();
            cboSelectView.setSelectedIndex(row);
            this.requestActive();
        }
        if (selected instanceof IReportListGUI) {
            panelReport = (IReportListGUI) selected;
        } else {
            panelReport = null;
        }

    }//GEN-LAST:event_cboSelectViewActionPerformed

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        remove();
        loadView();
	}//GEN-LAST:event_buttonDeleteActionPerformed

private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed
// TODO add your handling code here:
    if (isEdit == false) {
        buttonEdit.setText("Hủy Bỏ");
        isEdit = true;
        buttonDelete.setEnabled(true);
        buttonSave.setEnabled(true);
    } else {
        isEdit = false;
        buttonEdit.setText("Chỉnh Sửa");
        buttonDelete.setEnabled(false);
        buttonSave.setEnabled(false);
    }
    editList();
}//GEN-LAST:event_buttonEditActionPerformed

private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
// TODO add your handling code here:
    saveList();
}//GEN-LAST:event_buttonSaveActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    if (panelReport != null) {
        panelReport.exportReport();
    }
}//GEN-LAST:event_jButton1ActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        loadView();
    }//GEN-LAST:event_formComponentShown
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonSave;
    private javax.swing.JComboBox cboSelectView;
    private javax.swing.JComboBox cboTimkiem;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelForm;
    private javax.swing.JPanel panleButton;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        Set<TopComponent> tcs = WindowManager.getDefault().getRegistry().getOpened();
        for (TopComponent tc : tcs) {
            tc.close();
        }
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

    private void remove() {
        Object selected = cboSelectView.getSelectedItem();
        if (selected != null && selected instanceof RemoveCookiePerson) {
            try {
                ((RemoveCookiePerson) selected).remove();
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }

    @Override
    public void load() {
        loadToCombo();
        cboSelectView.setModel(model);
        if ((personViewer != null)) {
            panelForm.setLayout(new GridLayout());
            Object selected = cboSelectView.getItemAt(0);
            IPersonViewer middle = (IPersonViewer) selected;
            fillCombo(middle);
            JPanel p1 = middle.getPersonViewer();
            panelForm.removeAll();
            panelForm.add(p1);
            if (selected instanceof IReportListGUI) {
                panelReport = (IReportListGUI) selected;
            }
        }
        loadView();
    }

    public void loadView() {
        for (IPersonViewer iev : personViewer) {
            if (iev instanceof ViewCookieList) {
                try {
                    ((ViewCookieList) iev).ViewCookieList();
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
        for (IPersonExtViewer iexv : personExtViewer) {
            if (iexv instanceof ViewCookieList) {
                try {
                    ((ViewCookieList) iexv).ViewCookieList();
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
        reLoadFont();
        reLoadSize();
        reLoadColorMouse();
        reLoadColorTitle();
        reLoadColorWord();
    }

    public void editList() {
        for (IPersonViewer iev : personViewer) {
            if (iev instanceof EditCookieList) {
                try {
                    ((EditCookieList) iev).EditCookieList(isEdit);
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
        for (IPersonExtViewer iexv : personExtViewer) {
            if (iexv instanceof EditCookieList) {
                try {
                    ((EditCookieList) iexv).EditCookieList(isEdit);
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }

    public void saveList() {
        for (IPersonViewer iev : personViewer) {
            if (iev instanceof SaveCookieList) {
                try {
                    ((SaveCookieList) iev).SaveCookieList();
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
        for (IPersonExtViewer iexv : personExtViewer) {
            if (iexv instanceof SaveCookieList) {
                try {
                    ((SaveCookieList) iexv).SaveCookieList();
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }

    protected void loadToCombo() {
        model = new DefaultComboBoxModel();
        if (!personViewer.isEmpty()) {
            Iterator it = personViewer.iterator();
            while (it.hasNext()) {
                model.addElement(it.next());
            }
        }
        if (!personExtViewer.isEmpty()) {
            Iterator it = personExtViewer.iterator();
            while (it.hasNext()) {
                model.addElement(it.next());
            }
        }

    }

    public void reLoadColorRowTable() {
        for (IPersonViewer pv : personViewer) {
            if (pv instanceof IResetFontSize) {
                ((IResetFontSize) pv).resetColorRowTable();
            }
        }
        for (IPersonExtViewer iexc : personExtViewer) {
            if (iexc instanceof IResetFontSize) {
                ((IResetFontSize) iexc).resetColorRowTable();
            }
        }
    }

    private void reLoadFont() {
        for (IPersonViewer personCreater : personViewer) {
            if (personCreater instanceof IResetFontSize) {
                ((IResetFontSize) personCreater).resetFont();
            }
        }
        for (IPersonExtViewer iexc : personExtViewer) {
            if (iexc instanceof IResetFontSize) {
                ((IResetFontSize) iexc).resetFont();
            }
        }
    }

    private void reLoadSize() {

        for (IPersonViewer personCreater : personViewer) {
            if (personCreater instanceof IResetFontSize) {
                ((IResetFontSize) personCreater).resetSize();
            }
        }
        for (IPersonExtViewer iexc : personExtViewer) {
            if (iexc instanceof IResetFontSize) {
                ((IResetFontSize) iexc).resetSize();
            }
        }
    }

    private void reLoadColorWord() {
        for (IPersonViewer personCreater : personViewer) {
            if (personCreater instanceof IResetFontSize) {
                ((IResetFontSize) personCreater).resetColorWord();
            }
        }
        for (IPersonExtViewer iexc : personExtViewer) {
            if (iexc instanceof IResetFontSize) {
                ((IResetFontSize) iexc).resetColorWord();
            }
        }
    }

    private void reLoadColorTitle() {
        for (IPersonViewer personCreater : personViewer) {
            if (personCreater instanceof IResetFontSize) {
                ((IResetFontSize) personCreater).resetColorTitle();
            }
        }
        for (IPersonExtViewer iexc : personExtViewer) {
            if (iexc instanceof IResetFontSize) {
                ((IResetFontSize) iexc).resetColorTitle();
            }
        }
    }

    private void reLoadColorMouse() {
        for (IPersonViewer personCreater : personViewer) {
            if (personCreater instanceof IResetFontSize) {
                ((IResetFontSize) personCreater).resetColorMouse();
            }
        }
        for (IPersonExtViewer iexc : personExtViewer) {
            if (iexc instanceof IResetFontSize) {
                ((IResetFontSize) iexc).resetColorMouse();
            }
        }
    }

    public Hashtable<Integer, String> getHashtable() {
        return hashtable;
    }

    public void setHashtable(Hashtable<Integer, String> hashtable) {
        this.hashtable = hashtable;
    }

    public void fillCombo(IPersonViewer personViewer) {

        filterModel.removeAllElements();

        hashtable = personViewer.getTableHeader();

        setHashtable(hashtable);
        Collection<String> enumeration = getHashtable().values();
        for (String str : enumeration) {
            filterModel.addElement(str);
        }

    }

    public void fillCombo(IPersonExtViewer personExtViewer) {

        filterModel.removeAllElements();

        hashtable = personExtViewer.getTableHeader();

        setHashtable(hashtable);
        Collection<String> enumeration = getHashtable().values();
        for (String str : enumeration) {
            filterModel.addElement(str);
        }

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == cboTimkiem) {
            if (cboTimkiem.getSelectedIndex() > -1) {
                String value = cboTimkiem.getSelectedItem().toString().trim();
                if (value.length() != 0) {
                    Set set = hashtable.entrySet();
                    Iterator iterator = set.iterator();
                    while (iterator.hasNext()) {
                        Map.Entry entry = (Entry) iterator.next();
                        if (entry.getValue().toString().equals(value)) {
                            key = Integer.parseInt(entry.getKey().toString());
                        }
                    }
                }
            }
        }
    }
}
