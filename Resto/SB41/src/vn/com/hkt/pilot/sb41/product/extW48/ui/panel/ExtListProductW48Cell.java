/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb41.product.extW48.ui.panel;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import org.openide.util.Lookup;
import vn.com.hkt.basic.api.ICityBN;
import vn.com.hkt.basic.api.ICountryBN;
import vn.com.hkt.pilot.entities.City;
import vn.com.hkt.pilot.entities.Country;

/**
 *
 * @author VietAnh
 */
public class ExtListProductW48Cell extends AbstractCellEditor implements TableCellEditor, ItemListener {
    public Component component ;
    public DefaultComboBoxModel[] modelCountry , modelCity ;
    public JComboBox[] cboCountry , cboCity ;
    public JDateChooser[] d1 , d2, d3 ;
    public ICountryBN countryBN ;
    public ICityBN cityBN ;
    public int n ;
    public ExtListProductW48Cell(int n){
        this.n = n ;
        countryBN = Lookup.getDefault().lookup(ICountryBN.class);
        cityBN =  Lookup.getDefault().lookup(ICityBN.class);
        
        List<Country> listCountry = countryBN.selectAll();
        List<City> listCity = cityBN.selectAll();
        
        modelCountry = new DefaultComboBoxModel[n];
        modelCity = new DefaultComboBoxModel[n];
        cboCity = new JComboBox[n];
        cboCountry = new JComboBox[n];
        d1 = new JDateChooser[n];
        d2 = new JDateChooser[n];
        d3 = new JDateChooser[n];
        for (int i=0;i< n;i++) {
            modelCountry[i] = new DefaultComboBoxModel();
            modelCity[i] = new DefaultComboBoxModel();
            modelCountry[i].addElement("");
            modelCity[i].addElement("");
            if (!listCountry.isEmpty())
            for (Country c : listCountry ){
                modelCountry[i].addElement(c);
            }

            if (!listCity.isEmpty())
            

            d1[i] = new JDateChooser();
            d2[i] = new JDateChooser();
            d3[i] = new JDateChooser();
        }
        
        for (int i=0;i< n;i++){
            cboCountry[i] = new JComboBox(modelCountry[i]);
            cboCity[i] = new JComboBox(modelCity[i]);
            cboCountry[i].addItemListener(this);
        }
        
        
    }
    
    @Override
    public Object getCellEditorValue() {
        SimpleDateFormat sdf =
          new SimpleDateFormat("dd-MM-yyyy");
    
        try {
            for (int i=0;i< n; i++){
                if (component == d1[i]) return sdf.format(d1[i].getDate());
                if (component == d2[i]) return sdf.format(d2[i].getDate());
                if (component == d3[i]) return sdf.format(d3[i].getDate());
                if (component == cboCountry[i] ) return cboCountry[i].getSelectedItem();
                if (component == cboCity[i]) return cboCity[i].getSelectedItem() ;
            }
            return null ;
            
        } catch(Exception e){
            return null ;
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        for (int i=0;i< n ;i++) 
        if (i == row ) {
            if (column == 7) component = d1[i] ;
            if (column == 8) component = d2[i] ;
            if (column == 9) component = d3[i] ;
            if (column == 6) component = cboCity[i] ;
            if (column == 5) component = cboCountry[i] ;
        }
        
        return component;
    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        for (int i=0;i< n;i++) {
            if (e.getSource() == cboCountry[i]){
                modelCity[i].removeAllElements();
                if (cboCountry[i].getSelectedIndex() == 0) return ;
                Country country = (Country) cboCountry[i].getSelectedItem();
                for (City c : cityBN.getCityByCountry(country)) {
                        modelCity[i].addElement(c);
                }
            }
        }
    }

    
}
