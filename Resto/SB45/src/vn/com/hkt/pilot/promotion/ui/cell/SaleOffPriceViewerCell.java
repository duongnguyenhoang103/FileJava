/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.promotion.ui.cell;

import java.awt.Component;
import java.util.Collection;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import org.openide.util.Lookup;
import vn.com.hkt.pilot.promotion.entity.IClassificationOperation;

/**
 *
 * @author khangpn
 */
public class SaleOffPriceViewerCell extends AbstractCellEditor implements TableCellEditor{

    private Component component;
    private JComboBox cboClassificationOperation;
    private DefaultComboBoxModel comboBoxModel;

    public SaleOffPriceViewerCell() {
        
        fillCombo();
        cboClassificationOperation = new JComboBox(comboBoxModel);
        
    }
    
    public void fillCombo(){
        comboBoxModel = new DefaultComboBoxModel();
        Collection<? extends IClassificationOperation> classificationOperations;
        classificationOperations = Lookup.getDefault().lookupAll(IClassificationOperation.class);
        if(!classificationOperations.isEmpty()){
            for(IClassificationOperation classificationOperation:classificationOperations){
                comboBoxModel.addElement(classificationOperation);
            }            
        }
    }
    
    @Override
    public Object getCellEditorValue() {
        if(component instanceof JComboBox){
            JComboBox comboBox = (JComboBox)component;
            return comboBox.getSelectedItem();
        }
        return null;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if(column==9){
            component=cboClassificationOperation;
        }else{
            component = null;
        }
        return component;
    }
    
}
