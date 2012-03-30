/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb51.panel;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.TableCellEditor;
import org.openide.util.Lookup;
import vn.com.hkt.basic.api.IAccountNumberBN;
import vn.com.hkt.pilot.entities.AccountNumber;
import vn.com.hkt.pilot.sb51.entity.BillType;
import vn.com.hkt.pilot.sb51.entity.OperationStatus;
import vn.com.hkt.pilot.sb51.entity.PaymentMethod;
import vn.com.hkt.pilot.sb51.spi.BillTypeBN;
import vn.com.hkt.pilot.sb51.spi.OperationStatusBN;
import vn.com.hkt.pilot.sb51.spi.PaymentMethodBN;

/**
 *
 * @author Admin
 */
public class SB51Cell extends AbstractCellEditor implements TableCellEditor {

    private JComboBox cboMethodOfPayment, cboTypeOfBill, cboStatus, cboAccountNo;
    private DefaultComboBoxModel modelMethodOfPayment, modelTypeOfBill, modelStatus, modelAccountNo;
    private JTextField txtBillNo, txtCorrespondingAccount, txtNote;
    private Component component;
    private List<OperationStatus> listStatuses = new ArrayList<OperationStatus>();
    private List<AccountNumber> listAccountNumbers = new ArrayList<AccountNumber>();
    private List<PaymentMethod> listPaymentMethods = new ArrayList<PaymentMethod>();
    private List<BillType> listBillTypes = new ArrayList<BillType>();
    private OperationStatusBN operationStatusBN = new OperationStatusBN();
    private PaymentMethodBN paymentMethodBN = new PaymentMethodBN();
    private BillTypeBN billTypeBN = new BillTypeBN();
    private IAccountNumberBN accountNumberBN = Lookup.getDefault().lookup(IAccountNumberBN.class);

    public SB51Cell() {
        modelMethodOfPayment = new DefaultComboBoxModel();
        modelMethodOfPayment.addElement("");

        modelStatus = new DefaultComboBoxModel();
        modelStatus.addElement("");

        modelTypeOfBill = new DefaultComboBoxModel();
        modelTypeOfBill.addElement("");

        modelAccountNo = new DefaultComboBoxModel();
        modelAccountNo.addElement("");

        loadCbo();

        cboMethodOfPayment = new JComboBox(modelMethodOfPayment);
       // cboMethodOfPayment.setSelectedIndex(0);
        cboStatus = new JComboBox(modelStatus);
       // cboStatus.setSelectedIndex(0);
        cboTypeOfBill = new JComboBox(modelTypeOfBill);
       // cboTypeOfBill.setSelectedIndex(0);
        cboAccountNo = new JComboBox(modelAccountNo);
        //cboAccountNo.setSelectedIndex(0);

        txtBillNo = new JTextField();
        txtCorrespondingAccount = new JTextField();
        txtNote = new JTextField();
    }

    public void reset() {
        txtBillNo.setText("");
        txtCorrespondingAccount.setText("");
        txtNote.setText("");
    }

    @Override
    public Object getCellEditorValue() {
        if (component == cboAccountNo) {
            return cboAccountNo.getSelectedItem();
        } else if (component == txtBillNo) {
            return txtBillNo.getText();
        } else if (component == txtCorrespondingAccount) {
            return txtCorrespondingAccount.getText();
        } else if (component == cboMethodOfPayment) {
            return cboMethodOfPayment.getSelectedItem();
        } else if (component == cboStatus) {
            return cboStatus.getSelectedItem();
        } else if (component == cboTypeOfBill) {
            return cboTypeOfBill.getSelectedItem();
        } else if (component == txtNote) {
            return txtNote.getText();
        }
        return null;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (column == 1) {
            if (row == 0) {
                component = cboStatus;
            }
            else if (row == 1) {
                component = cboTypeOfBill;
            }
            else if (row == 2) {
                component = cboAccountNo;
            }
            else if (row == 3) {
                component = txtNote;
            }
            else{
                component = null;
            }
        }
        else if (column == 3) {
            if (row == 1) {
                component = cboMethodOfPayment;
            }
            else if (row == 2) {
                component = txtBillNo;
            }
            else if (row == 3) {
                component = txtCorrespondingAccount;
            }
            else{
                component = null;
            }
        }
        else{
            component = null;
        }
        return component;
    }

    public void loadCbo() {
        listBillTypes = billTypeBN.selectAll();
        listAccountNumbers = accountNumberBN.selectAll();
        listPaymentMethods = paymentMethodBN.selectAll();
        listStatuses = operationStatusBN.selectAll();

        modelAccountNo.removeAllElements();
        modelMethodOfPayment.removeAllElements();
        modelStatus.removeAllElements();
        modelTypeOfBill.removeAllElements();
        for (PaymentMethod bean : listPaymentMethods) {
            modelMethodOfPayment.addElement(bean);
        }

        for (BillType bean : listBillTypes) {
            modelTypeOfBill.addElement(bean);
        }

        for (OperationStatus bean : listStatuses) {
            modelStatus.addElement(bean);
        }

        for (AccountNumber bean : listAccountNumbers) {
            modelAccountNo.addElement(bean);
        }
    }
}