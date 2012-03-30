/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.erm.enterprise.ui;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import org.japura.gui.LinkLabel;

/**
 *
 * @author longnt
 */
public class ListEnterpriseViewCell extends AbstractCellEditor implements TableCellEditor, MouseMotionListener {

    private boolean flag = false;
    final LinkLabel spinner = new LinkLabel();
    int itsRow = 0;

    public ListEnterpriseViewCell() {
        //spinner.addMouseMotionListener(this);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
            int row, int column) {
        spinner.setText(value.toString());
       // table.addMouseMotionListener(this);
        
        return spinner;
    }

    @Override
    public boolean isCellEditable(EventObject evt) {
        if (evt instanceof MouseEvent) {
            spinner.requestFocus();
            return true;
        }
        return true;
    }

    @Override
    public Object getCellEditorValue() {
        return spinner.getText();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        JTable aTable = (JTable) e.getSource();
        //itsRow = aTable.rowAtPoint(e.getPoint());
        spinner.requestFocus();
        //aTable.repaint();
    }
}
