/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.subenterprise13.ui.panel;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author VietAnh
 */
public class ExtEnterpriseSB13Cell extends AbstractCellEditor implements TableCellEditor {

    private JTextField txt01, txt03, txt11, txt13, txt21;
    private JComponent c;

    public ExtEnterpriseSB13Cell() {
        txt01 = new JTextField("");
        txt03 = new JTextField("");
        txt11 = new JTextField("");
        txt13 = new JTextField("");
        txt21 = new JTextField("");

        txt01.setDocument(new DigitsDocument());
        txt03.setDocument(new DigitsDocument());
        txt11.setDocument(new DigitsDocument());
        txt13.setDocument(new DigitsDocument());
        txt21.setDocument(new DigitsDocument());
    }

    @Override
    public Object getCellEditorValue() {
        if (c == txt01) {
            return txt01.getText();
        } else if (c == txt03) {
            return txt03.getText();
        } else if (c == txt11) {
            return txt11.getText();
        } else if (c == txt13) {
            return txt13.getText();
        }
        return txt21.getText();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (column == 1) {
            if (row == 0) {
                c = txt01;
            }
            if (row == 1) {
                c = txt11;
            }
            if (row == 2) {
                c = txt21;
            }
        }
        if (column == 3) {
            if (row == 0) {
                c = txt03;
            }
            if (row == 1) {
                c = txt13;
            }
        }
        return c;
    }

    public JTextField getTxt01() {
        return txt01;
    }

    public JTextField getTxt03() {
        return txt03;
    }

    public JTextField getTxt11() {
        return txt11;
    }

    public JTextField getTxt13() {
        return txt13;
    }

    public JTextField getTxt21() {
        return txt21;
    }

    public class DigitsDocument extends PlainDocument {

        @Override
        public void insertString(int offs, String str, AttributeSet a)
                throws BadLocationException {
            if (str == null) {
                return;
            }
            char[] addedFigures = str.toCharArray();
            char c;
            for (int i = addedFigures.length; i > 0; i--) {
                c = addedFigures[i - 1];
                if (Character.isDigit(c) || c == '.') {
                    super.insertString(offs, new String(new Character(c).toString()), a);
                }
            }
        }
    }
}
