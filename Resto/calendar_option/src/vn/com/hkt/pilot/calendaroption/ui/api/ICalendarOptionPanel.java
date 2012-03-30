/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.calendaroption.ui.api;

import javax.swing.JPanel;
import org.openide.util.Lookup;

/**
 *
 * @author khangpn
 */
public interface ICalendarOptionPanel {
    
    public JPanel getCalendarOptionPanel();
    public Lookup getCalendarOptionLookup();
    public void saveCalendarOption();
}
