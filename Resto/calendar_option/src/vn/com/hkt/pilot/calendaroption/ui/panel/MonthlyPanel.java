/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MonthlyPanel.java
 *
 * Created on Feb 21, 2012, 10:09:28 AM
 */
package vn.com.hkt.pilot.calendaroption.ui.panel;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author khangpn
 */
public class MonthlyPanel extends javax.swing.JPanel {

    private SpinnerNumberModel spinnerNumberModel;
    private ButtonGroup buttonGroup;
    
    /** Creates new form MonthlyPanel */
    public MonthlyPanel() {
        initComponents();
        loadSpinnerModel();
        
        sprDay.setModel(spinnerNumberModel);
        sprStepOfMonth.setModel(spinnerNumberModel);
        
        buttonGroup = new ButtonGroup();
        buttonGroup.add(rdoDay);
        buttonGroup.add(rdoLevel);
    }
    
    protected void load(){
        
    }
    
    
    
    
    protected void loadSpinnerModel(){
        spinnerNumberModel = new SpinnerNumberModel(1, 1, 200, 1);
    }

    public JComboBox getCboDayLevel() {
        return cboDayLevel;
    }

    public void setCboDayLevel(JComboBox cboDayLevel) {
        this.cboDayLevel = cboDayLevel;
    }

    public JComboBox getCboDayOfWeek() {
        return cboDayOfWeek;
    }

    public void setCboDayOfWeek(JComboBox cboDayOfWeek) {
        this.cboDayOfWeek = cboDayOfWeek;
    }

    public JRadioButton getRdoDay() {
        return rdoDay;
    }

    public void setRdoDay(JRadioButton rdoDay) {
        this.rdoDay = rdoDay;
    }

    public JRadioButton getRdoLevel() {
        return rdoLevel;
    }

    public void setRdoLevel(JRadioButton rdoLevel) {
        this.rdoLevel = rdoLevel;
    }

    public JSpinner getSprDay() {
        return sprDay;
    }

    public void setSprDay(JSpinner sprDay) {
        this.sprDay = sprDay;
    }

    public JSpinner getSprStepOfMonth() {
        return sprStepOfMonth;
    }

    public void setSprStepOfMonth(JSpinner sprStepOfMonth) {
        this.sprStepOfMonth = sprStepOfMonth;
    }

    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rdoDay = new javax.swing.JRadioButton();
        rdoLevel = new javax.swing.JRadioButton();
        sprDay = new javax.swing.JSpinner();
        sprStepOfMonth = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        cboDayLevel = new javax.swing.JComboBox();
        cboDayOfWeek = new javax.swing.JComboBox();

        rdoDay.setText(org.openide.util.NbBundle.getMessage(MonthlyPanel.class, "MonthlyPanel.rdoDay.text")); // NOI18N

        rdoLevel.setText(org.openide.util.NbBundle.getMessage(MonthlyPanel.class, "MonthlyPanel.rdoLevel.text")); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(MonthlyPanel.class, "MonthlyPanel.jLabel2.text")); // NOI18N

        cboDayLevel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboDayOfWeek.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rdoDay)
                        .addGap(18, 18, 18)
                        .addComponent(sprDay))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(sprStepOfMonth))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rdoLevel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboDayLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboDayOfWeek, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoDay)
                    .addComponent(sprDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoLevel)
                    .addComponent(cboDayLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboDayOfWeek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(sprStepOfMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboDayLevel;
    private javax.swing.JComboBox cboDayOfWeek;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JRadioButton rdoDay;
    private javax.swing.JRadioButton rdoLevel;
    private javax.swing.JSpinner sprDay;
    private javax.swing.JSpinner sprStepOfMonth;
    // End of variables declaration//GEN-END:variables
}
