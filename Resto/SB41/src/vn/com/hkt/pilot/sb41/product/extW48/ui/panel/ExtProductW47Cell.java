/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb41.product.extW48.ui.panel;

import java.awt.Component;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.TableCellEditor;
import org.openide.util.Lookup;
import vn.com.hkt.basic.api.IEnterpriseBN;
import vn.com.hkt.basic.api.IPersonBN;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Person;

/**
 *
 * @author VietAnh
 */
public class ExtProductW47Cell extends AbstractCellEditor implements TableCellEditor, ItemListener, ActionListener {

    public boolean isIsCustommer() {
        return isCustommer;
    }
    private boolean isCustommer = true;
    private IEnterpriseBN enterpriseBN;
    private IPersonBN personBN;
    private JComponent component;
    private JTextField txtName;
    private JComboBox cboChoose, cboEnterprise;
    private DefaultComboBoxModel model, modelEnterprise;
    private JRadioButton rdb1, rdb2;
    private ButtonGroup bg;

    public ExtProductW47Cell() {
        enterpriseBN = Lookup.getDefault().lookup(IEnterpriseBN.class);
        personBN = Lookup.getDefault().lookup(IPersonBN.class);

        txtName = new JTextField("");
        model = new DefaultComboBoxModel();
        modelEnterprise = new DefaultComboBoxModel();
        modelEnterprise.removeAllElements();
        List<Enterprise> list = enterpriseBN.selectAll();
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                modelEnterprise.addElement(list.get(i));
            }
        }

        model.addElement(" ");
        model.addElement("Tổ Chức");
        model.addElement("Cá Nhân");

        cboChoose = new JComboBox(model);
        cboEnterprise = new JComboBox(modelEnterprise);

        rdb1 = new JRadioButton("Đối Tác");
        rdb2 = new JRadioButton("Vãng Lai");
        bg = new ButtonGroup();
        bg.add(rdb1);
        bg.add(rdb2);

        cboChoose.addItemListener(this);
        rdb1.addActionListener(this);
        rdb2.addActionListener(this);

    }

    public void reset() {
        txtName.setText("");
    }

    @Override
    public Object getCellEditorValue() {
        rdb1.setEnabled(true);
        rdb2.setEnabled(true);
        if (component == txtName) {
            return txtName.getText();
        } else if (component == cboChoose) {
            return cboChoose.getSelectedItem();
        } else if (component == cboEnterprise) {
            return cboEnterprise.getSelectedItem();
        } else if (component == rdb1) {
            isCustommer = true;
            return rdb1;
        } else if (component == rdb2) {
            isCustommer = false;
            return rdb2;
        }
        return "";
    }

    @Override
    public Component getTableCellEditorComponent(final JTable table, Object value, boolean isSelected, final int row, final int column) {

        if (row == 0) {
            if (column == 1) {
                component = cboChoose;
            }
        }
        if (row == 1) {
            if (column == 0) {
                component = rdb1;
            }
            if (column == 1) {
                component = cboEnterprise;
            }
        }
        if (row == 2) {
            if (column == 0) {
                component = rdb2;
            }
            if (column == 1) {
                component = txtName;
            }
        }
        return component;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == cboChoose) {
            modelEnterprise.removeAllElements();
            if (cboChoose.getSelectedIndex() == 1) {
                List<Enterprise> list = enterpriseBN.selectAll();
                if (!list.isEmpty()) {
                    for (int i = 0; i < list.size(); i++) {
                        modelEnterprise.addElement(list.get(i));
                    }
                }
            } else if (cboChoose.getSelectedIndex() == 2) {
                List<Person> list = personBN.selectAll();
                if (!list.isEmpty()) {
                    for (int i = 0; i < list.size(); i++) {
                        modelEnterprise.addElement(list.get(i));
                    }
                }
            } 
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rdb1) {
            if (rdb1.isSelected()) {
                bg.clearSelection();
                cboEnterprise.setEnabled(true);
                txtName.setEnabled(false);
                rdb1.setSelected(true);
                rdb2.setSelected(false);
            }

        }
        if (e.getSource() == rdb2) {
            if (rdb2.isSelected()) {
                bg.clearSelection();
                cboEnterprise.setEnabled(false);
                txtName.setEnabled(true);
                rdb1.setSelected(false);
                rdb2.setSelected(true);
            }
        }
    }

    public JRadioButton getRdb1() {
        return rdb1;
    }

    public JRadioButton getRdb2() {
        return rdb2;
    }
}
