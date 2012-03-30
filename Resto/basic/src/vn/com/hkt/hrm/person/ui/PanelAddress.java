/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelAddress.java
 *
 * Created on Mar 7, 2012, 2:00:38 PM
 */
package vn.com.hkt.hrm.person.ui;

import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.Installer;
import vn.com.hkt.basic.api.ICityBN;
import vn.com.hkt.basic.api.ICountryBN;
import vn.com.hkt.basic.api.IPartitionBN;
import vn.com.hkt.hrm.person.dao.ForeignLanguageBN;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnterpriseHelp;
import vn.com.hkt.pilot.entities.City;
import vn.com.hkt.pilot.entities.Country;
import vn.com.hkt.pilot.entities.ForeignLanguage;
import vn.com.hkt.pilot.entities.Partition;
import vn.com.hkt.pilot.identity.presentation.api.IUserInterface;
import vn.com.hkt.pilot.operation.viewer.api.ILoadComboCountry;

/**
 *
 * @author longnt
 */
@ServiceProvider(service = IEnterpriseHelp.class)
public class PanelAddress extends javax.swing.JPanel implements IEnterpriseHelp, ItemListener, ILoadComboCountry, IUserInterface {

    private ICountryBN countryBN;
    private IPartitionBN partition1BN;
    private ICityBN cityBN;
    private ForeignLanguageBN foreignLanguageBN = new ForeignLanguageBN();
    private DefaultComboBoxModel modelCboQG, modelCboV1, modelCboV2, modelCBoCity, modelNational, modelCode, modelLanguages;
    private List<Country> listQG = new ArrayList<Country>();
    private List<Partition> listV1 = new ArrayList<Partition>();
    private List<Partition> listV2 = new ArrayList<Partition>();
    private List<City> listTP = new ArrayList<City>();
    private List<ForeignLanguage> listLanguages = new ArrayList<ForeignLanguage>();
    private JPanel panel1, panel2, panel3, panel4, panel5, panel6, panel7, panel8;

    /**
     * Creates new form PanelAddress
     */
    public PanelAddress() {
        initComponents();
        panel1 = new JPanel(new GridLayout());
        panel2 = new JPanel(new GridLayout());
        panel3 = new JPanel(new GridLayout());
        panel4 = new JPanel(new GridLayout());
        panel5 = new JPanel(new GridLayout());
        panel6 = new JPanel(new GridLayout());
        panel7 = new JPanel(new GridLayout());
        panel8 = new JPanel(new GridLayout());
        panel1.add(cboCountry);
        panel2.add(cboQuocTich);
        panel3.add(cboVung1);
        panel4.add(cboCity);
        panel5.add(jLabel1);
        panel6.add(cboNgonNgu);
        panel7.add(cboVung2);
        panel8.add(cboCode);
        panel1.setBorder(BorderFactory.createTitledBorder("CS quốc gia"));
        panel2.setBorder(BorderFactory.createTitledBorder("CS quốc tịch"));
        panel3.setBorder(BorderFactory.createTitledBorder("CS vùng 1"));
        panel4.setBorder(BorderFactory.createTitledBorder("CS thành phố"));
        panel5.setBorder(BorderFactory.createTitledBorder("Quốc kỳ"));
        panel6.setBorder(BorderFactory.createTitledBorder("CS ngôn ngữ"));
        panel7.setBorder(BorderFactory.createTitledBorder("CS vùng 2"));
        panel8.setBorder(BorderFactory.createTitledBorder("CS Postal Code"));
        panelCbo.removeAll();
        panelCbo.setLayout(new GridLayout(2, 4, 3, 3));
        panelCbo.add(panel1);
        panelCbo.add(panel2);
        panelCbo.add(panel3);
        panelCbo.add(panel4);
        panelCbo.add(panel5);
        panelCbo.add(panel6);
        panelCbo.add(panel7);
        panelCbo.add(panel8);
        countryBN = Lookup.getDefault().lookup(ICountryBN.class);
        partition1BN = Lookup.getDefault().lookup(IPartitionBN.class);
        cityBN = Lookup.getDefault().lookup(ICityBN.class);
        modelCBoCity = new DefaultComboBoxModel();
        modelCboQG = new DefaultComboBoxModel();
        modelCboV1 = new DefaultComboBoxModel();
        modelCboV2 = new DefaultComboBoxModel();
        modelLanguages = new DefaultComboBoxModel();
        modelNational = new DefaultComboBoxModel();
        modelCode = new DefaultComboBoxModel();
        listQG = countryBN.selectAll();
        for (Country country1 : listQG) {
            modelCboQG.addElement(country1);
        }
        cboCountry.setModel(modelCboQG);

//        listV2 = partition1BN.selectAll();
//        listV1 = partition1BN.selectAll();
//        listTP = cityBN.selectAll();
//        for (Partition bean : listV1) {
//            modelCboV1.addElement(bean);
//        }
        cboVung1.setModel(modelCboV1);

//        for (Partition bean : listV2) {
//            modelCboV2.addElement(bean);
//        }
        cboVung2.setModel(modelCboV2);
//
//        for (City bean : listTP) {
//            modelCBoCity.addElement(bean);
//        }
        cboQuocTich.setModel(modelNational);
        cboCode.setModel(modelCode);
        cboCity.setModel(modelCBoCity);
        cboNgonNgu.setModel(modelLanguages);
        cboCity.addItemListener(this);
        cboCountry.addItemListener(this);
        cboVung1.addItemListener(this);
        cboVung2.addItemListener(this);

//        panelForm.setLayout(new GridLayout());
//        panelForm.add(EditCountryPanel.getcContryPanel());

    }

    public JPanel getPanelForm() {
        return panelForm;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCbo = new javax.swing.JPanel();
        cboCountry = new javax.swing.JComboBox();
        cboQuocTich = new javax.swing.JComboBox();
        cboVung1 = new javax.swing.JComboBox();
        cboCity = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        cboNgonNgu = new javax.swing.JComboBox();
        cboVung2 = new javax.swing.JComboBox();
        cboCode = new javax.swing.JComboBox();
        panelForm = new javax.swing.JPanel();

        cboCountry.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cboCountry.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cboCountryMousePressed(evt);
            }
        });

        cboQuocTich.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cboQuocTich.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cboQuocTichMousePressed(evt);
            }
        });

        cboVung1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cboVung1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cboVung1MousePressed(evt);
            }
        });

        cboCity.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cboCity.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cboCityMousePressed(evt);
            }
        });

        jLabel1.setText(org.openide.util.NbBundle.getMessage(PanelAddress.class, "PanelAddress.jLabel1.text")); // NOI18N

        cboNgonNgu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cboNgonNgu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cboNgonNguMousePressed(evt);
            }
        });

        cboVung2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cboVung2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cboVung2MousePressed(evt);
            }
        });

        cboCode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cboCode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cboCodeMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panelCboLayout = new javax.swing.GroupLayout(panelCbo);
        panelCbo.setLayout(panelCboLayout);
        panelCboLayout.setHorizontalGroup(
            panelCboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCboLayout.createSequentialGroup()
                .addGroup(panelCboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCboLayout.createSequentialGroup()
                        .addComponent(cboCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboQuocTich, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboVung1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboCity, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCboLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cboNgonNgu, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboVung2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboCode, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        panelCboLayout.setVerticalGroup(
            panelCboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCboLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboQuocTich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboVung1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(panelCboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(cboNgonNgu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboVung2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout panelFormLayout = new javax.swing.GroupLayout(panelForm);
        panelForm.setLayout(panelFormLayout);
        panelFormLayout.setHorizontalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 402, Short.MAX_VALUE)
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 208, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCbo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelCbo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboVung1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboVung1MousePressed

        if (cboCountry.getSelectedItem() != null) {
            panelForm.removeAll();
            Country country = (Country) cboCountry.getSelectedItem();
            EditVung1Panel vung1Panel = new EditVung1Panel(country);
            panelForm.setLayout(new GridLayout());
            panelForm.add(vung1Panel);
            panelForm.validate();
            panelForm.repaint();
        }
    }//GEN-LAST:event_cboVung1MousePressed

    private void cboVung2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboVung2MousePressed
        if (cboCountry.getSelectedItem() != null) {
            panelForm.removeAll();
            Country country = (Country) cboCountry.getSelectedItem();
            Partition partition;
            if (cboVung1.getSelectedItem() != null) {
                partition = (Partition) cboVung1.getSelectedItem();
            } else {
                partition = null;
            }
            EditVung2Panel vung2Panel = new EditVung2Panel(country, partition);
            panelForm.setLayout(new GridLayout());
            panelForm.add(vung2Panel);
            panelForm.validate();
            panelForm.repaint();
        }
    }//GEN-LAST:event_cboVung2MousePressed

    private void cboCountryMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboCountryMousePressed
        panelForm.removeAll();
        panelForm.setLayout(new GridLayout());
        panelForm.add(EditCountryPanel.getcContryPanel());
        panelForm.validate();
        panelForm.repaint();
    }//GEN-LAST:event_cboCountryMousePressed

    private void cboCityMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboCityMousePressed
        if (cboCountry.getSelectedItem() != null) {
            panelForm.removeAll();
            Country country = (Country) cboCountry.getSelectedItem();
            Partition partition;
            if (cboVung2.getSelectedItem() != null) {
                partition = (Partition) cboVung2.getSelectedItem();
            } else {
                if (cboVung1.getSelectedItem() != null) {
                    partition = (Partition) cboVung1.getSelectedItem();
                } else {
                    partition = null;
                }

            }
            EditCityPanel editCityPanel = new EditCityPanel(country, partition);
            panelForm.setLayout(new GridLayout());
            panelForm.add(editCityPanel);
            panelForm.validate();
            panelForm.repaint();
        }
    }//GEN-LAST:event_cboCityMousePressed

    private void cboQuocTichMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboQuocTichMousePressed
        if (cboCountry.getSelectedItem() != null) {
            panelForm.removeAll();
            Country country = (Country) cboCountry.getSelectedItem();
            EditNationalityPanel editNationalityPanel = new EditNationalityPanel(country);
            panelForm.setLayout(new GridLayout());
            panelForm.add(editNationalityPanel);
            panelForm.validate();
            panelForm.repaint();
        }
    }//GEN-LAST:event_cboQuocTichMousePressed

    private void cboCodeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboCodeMousePressed
        if (cboCity.getSelectedItem() != null) {
            panelForm.removeAll();
            City city = (City) cboCity.getSelectedItem();
            EditCodePanel editCodePanel = new EditCodePanel(city);
            panelForm.setLayout(new GridLayout());
            panelForm.add(editCodePanel);
            panelForm.validate();
            panelForm.repaint();
        }
    }//GEN-LAST:event_cboCodeMousePressed

    private void cboNgonNguMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboNgonNguMousePressed

        if (cboCountry.getSelectedItem() != null) {
            panelForm.removeAll();
            Country country = (Country) cboCountry.getSelectedItem();
            EditLanguagePanel languagePanel = new EditLanguagePanel(country);
            panelForm.setLayout(new GridLayout());
            panelForm.add(languagePanel);
            panelForm.validate();
            panelForm.repaint();
        }
    }//GEN-LAST:event_cboNgonNguMousePressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboCity;
    private javax.swing.JComboBox cboCode;
    private javax.swing.JComboBox cboCountry;
    private javax.swing.JComboBox cboNgonNgu;
    private javax.swing.JComboBox cboQuocTich;
    private javax.swing.JComboBox cboVung1;
    private javax.swing.JComboBox cboVung2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panelCbo;
    private javax.swing.JPanel panelForm;
    // End of variables declaration//GEN-END:variables

    @Override
    public JPanel getPanel1() {
        return this;
    }

    @Override
    public JPanel getPanel2() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double getLevelNumber() {
        return 1.2;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JComboBox comboBox = (JComboBox) e.getSource();
        if (comboBox == cboCountry) {
            if (cboCountry.getSelectedItem() != null) {
                Country country = (Country) cboCountry.getSelectedItem();
                modelCBoCity.removeAllElements();
                modelCboV1.removeAllElements();
                modelCboV2.removeAllElements();
                modelNational.removeAllElements();
                modelLanguages.removeAllElements();

                listV2 = partition1BN.getPartition1ByCountry(country);
                listV1 = partition1BN.getPartition1ByCountry(country);
                listTP = cityBN.getCityByCountry(country);
                listLanguages = foreignLanguageBN.selectLanguageByCountryId(country.getId());
                for (Partition bean : listV1) {
                    modelCboV1.addElement(bean);
                }

                for (Partition bean : listV2) {
                    modelCboV2.addElement(bean);
                }

                for (City bean : listTP) {
                    modelCBoCity.addElement(bean);
                }

                for (ForeignLanguage bean : listLanguages) {
                    modelLanguages.addElement(bean);
                }
                modelNational.addElement(country.getNationality());
            }

        }
        if (comboBox == cboVung1) {
            if (cboVung1.getSelectedItem() != null) {
                Partition partition = (Partition) cboVung1.getSelectedItem();
                modelCBoCity.removeAllElements();
                modelCboV2.removeAllElements();

                listV2 = partition1BN.getPartition2ByParent(partition);
                listTP = cityBN.getCityByPartition(partition);
                for (Partition bean : listV2) {
                    modelCboV2.addElement(bean);
                }

                for (City bean : listTP) {
                    modelCBoCity.addElement(bean);
                }
            }

        }
        if (comboBox == cboVung2) {
            if (cboVung2.getSelectedItem() != null) {
                Partition partition = (Partition) cboVung2.getSelectedItem();
                modelCBoCity.removeAllElements();
                listTP = cityBN.getCityByPartition(partition);
                for (City bean : listTP) {
                    modelCBoCity.addElement(bean);
                }
            }

        }
        if (comboBox == cboCity) {
            if (cboCity.getSelectedItem() != null) {
                City city = (City) cboCity.getSelectedItem();
                modelCode.removeAllElements();
                modelCode.addElement(city.getPostalCode());
            }
        }
    }

    @Override
    public void loadCombo() {

        modelCboQG.removeAllElements();
        listQG = countryBN.selectAll();
        for (Country country1 : listQG) {
            modelCboQG.addElement(country1);
        }
    }

    @Override
    public String getUserInterfaceName() {
        return getClass().getSimpleName();
    }

    @Override
    public String getUserInterfaceDescription() {
        return "Giao diện chỉnh sửa địa chỉ doanh nghiệp";
    }

    @Override
    public String getModuleName() {
        return Installer.MODULE_NAME;
    }
}
