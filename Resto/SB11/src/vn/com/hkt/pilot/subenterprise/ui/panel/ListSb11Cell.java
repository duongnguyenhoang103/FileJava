/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.subenterprise.ui.panel;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.text.SimpleDateFormat;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author longnt
 */
public class ListSb11Cell extends AbstractCellEditor implements TableCellEditor {
    private Component component;
    private JComboBox cboState[], cboType[] ;
    private DefaultComboBoxModel modelState, modelType ;
    private JDateChooser dateS[], dateR[];
    private JTextField txtRegister[] , txtNumber[] , txtIdTax [];
    private int n;

    public ListSb11Cell(int n) {
        this.n = n;
        
        
        modelState = new DefaultComboBoxModel();
        modelState.addElement("Chưa HT");
        modelState.addElement("Hoàn Thành");

        modelType = new DefaultComboBoxModel();
        modelType.addElement("LH 1");
        modelType.addElement("LH 2");



        dateS = new com.toedter.calendar.JDateChooser[n];
        dateR = new com.toedter.calendar.JDateChooser[n];
        cboState = new JComboBox[n];
        cboType = new JComboBox[n];
        
        txtRegister = new JTextField[n];
        txtNumber = new JTextField[n];
        txtIdTax = new JTextField[n];

        for (int i = 0; i < n; i++) {
            cboState[i] = new JComboBox(modelState);
            cboType[i] = new JComboBox(modelType);
            dateR[i] = new JDateChooser();
            dateS[i] = new JDateChooser();
            
            txtRegister[i] = new JTextField();
            txtNumber[i] = new JTextField();
            txtIdTax[i] = new JTextField();
            
            txtRegister[i].setDocument(new DigitsDocument());
            txtNumber[i].setDocument(new DigitsDocument());
            txtIdTax[i].setDocument(new DigitsDocument());
        }
    }

    @Override
    public Object getCellEditorValue() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            for (int i = 0; i < n; i++) {
                if (component == cboState[i]) {
                    return cboState[i].getSelectedItem();
                }
                if (component == cboType[i]) {
                    return cboType[i].getSelectedItem();
                }
                if (component == dateS[i]) {
                    return sdf.format(dateS[i].getDate());
                }
                if (component == dateR[i]) {
                    return sdf.format(dateR[i].getDate());
                }
                if (component == txtRegister[i])
                    return txtRegister[i].getText();
                if (component == txtNumber[i])
                    return txtNumber[i].getText();
                if (component == txtIdTax[i])
                    return txtIdTax[i].getText();

            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        for (int i = 0; i < n; i++) {
            if (i == row) {
                if (column == 3) {
                    component = cboType[i];

                }
                if (column == 4) {
                    component = dateS[i];
                }
                if (column == 5) {
                    component = dateR[i];
                }
                if (column == 6){
                    component = txtRegister[i];
                }
                if (column == 7){
                    component = txtNumber[i];
                }
                if (column == 8){
                    component = txtIdTax[i];
                }
                if (column == 9) {
                    component = cboState[i];
                }
            }

        }

        return component;
    }
    
    
      public class DigitsDocument extends PlainDocument {
    public void insertString(int offs, String str, AttributeSet a)
        throws BadLocationException
    {
        if(str==null)
        {
            return;
        }
        char[] addedFigures = str.toCharArray();
        char c;
        for(int i=addedFigures.length; i>0; i--)
        {
            c=addedFigures[i-1];
            if(Character.isDigit(c)||c=='.')
            {
                super.insertString(offs, new String(new Character(c).toString()), a);
            }
        }
        }
     }
}
