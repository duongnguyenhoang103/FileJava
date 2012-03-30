/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb31.ui.panel.ext_d_w41;

import java.awt.Component;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import org.openide.util.Lookup;
import vn.com.hkt.basic.api.IEnterpriseBN;
import vn.com.hkt.basic.api.IPersonBN;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Person;

/**
 *
 * @author VietAnh
 */
public class ExtDepartmentW42Cell extends AbstractCellEditor implements TableCellEditor, ItemListener, ActionListener {

    private boolean isRdb1 = false;
    private IEnterpriseBN enterpriseBN;
    private IPersonBN personBN;
    private JComponent component;
    private JTextField txtName;
    private JComboBox cboChoose, cboEnterprise;
    private DefaultComboBoxModel model, modelEnterprise;
    private JRadioButton rdb1, rdb2;
    private ButtonGroup bg;

    public ExtDepartmentW42Cell() {
        enterpriseBN = Lookup.getDefault().lookup(IEnterpriseBN.class);
        personBN = Lookup.getDefault().lookup(IPersonBN.class);

        txtName = new JTextField();
        model = new DefaultComboBoxModel();
        modelEnterprise = new DefaultComboBoxModel();
        modelEnterprise.removeAllElements();


        model.addElement("");
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

        cboEnterprise.setEnabled(false);
    }

    public void reset() {
        txtName.setText("");
        bg.clearSelection();
        modelEnterprise.removeAllElements();
    }

    @Override
    public Object getCellEditorValue() {
        rdb1.setEnabled(true);
        rdb2.setEnabled(true);
        if (component == txtName) {
            return txtName.getText();
        } else if (component == cboChoose) {        // Lua Chon 

            return cboChoose.getSelectedItem();
        } else if (component == cboEnterprise) {    // Neu chon KH la cong ty
            return cboEnterprise.getSelectedItem();
        } else if (component == rdb1) {
            isRdb1 = true;
            return rdb1;
        } else if (component == rdb2) {
            isRdb1 = false;
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
            if (cboChoose.getSelectedIndex() == 0) {
                cboEnterprise.removeAllItems();
            } else {
                txtName.setEnabled(true);
            }
            if (cboChoose.getSelectedIndex() == 1) {
                rdb1.setEnabled(true);
                rdb2.setEnabled(true);
                setComboBoxModel(true);
            } else if (cboChoose.getSelectedIndex() == 2) {
                setComboBoxModel(false);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rdb1) {
            if (rdb1.isSelected()) {
                isRdb1 = true;
                setEnterpriseEnable(true);
                rdb2.setSelected(false);
            }

        }
        if (e.getSource() == rdb2) {
            if (rdb2.isSelected()) {
                isRdb1 = false;
                setEnterpriseEnable(false);
                rdb1.setSelected(false);
            }
        }
    }

    public JRadioButton getRdb1() {
        return rdb1;
    }

    public JRadioButton getRdb2() {
        return rdb2;
    }

    public void setComboBoxModel(boolean ok) {
        if (ok) {
            modelEnterprise.removeAllElements();
            modelEnterprise.addElement("");
            List<Enterprise> list = enterpriseBN.selectAll();
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    modelEnterprise.addElement(list.get(i));
                }
            }
        } else {
            modelEnterprise.removeAllElements();
            modelEnterprise.addElement("");
            List<Person> list = personBN.selectAll();
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    modelEnterprise.addElement(list.get(i));
                }
            }
        }
    }

    public void setEnterpriseEnable(boolean ok) {
        if (ok) {
            rdb1.setSelected(true);
            rdb2.setSelected(false);
            cboEnterprise.setEnabled(true);
            txtName.setEnabled(false);
        } else {
            rdb1.setSelected(false);
            rdb2.setSelected(true);
            cboEnterprise.setEnabled(false);
            txtName.setEnabled(true);
        }
    }

    public boolean isIsRdb1() {
        return isRdb1;
    }

    public void setCustomerName(String s) {
        txtName.setText(s);

    }

    public void setEnterpriseChoose(boolean ok, Object obj) {
        if (ok) {
            if (cboChoose.getModel().getSize() > 1) {
                cboChoose.setSelectedIndex(1);
                try {
                    Enterprise e = (Enterprise) obj;
                    setComboBoxModel(true);
                    cboEnterprise.setSelectedItem(e);
                } catch (Exception ex) {
                    cboEnterprise.setSelectedIndex(0);
                }
            }
        } else {
            if (cboChoose.getModel().getSize() > 2) {
                cboChoose.setSelectedIndex(2);
                try {
                    Person p = (Person) obj;
                    setComboBoxModel(false);
                    cboEnterprise.setSelectedItem(p);
                } catch (Exception ex) {
                    cboEnterprise.setSelectedIndex(0);
                }
            }
        }
    }
}
