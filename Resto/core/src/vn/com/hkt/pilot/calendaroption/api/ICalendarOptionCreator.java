/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.calendaroption.api;

import javax.swing.JPanel;
import org.openide.util.Lookup;

/**
 *
 * @author khangpn
 */
public interface ICalendarOptionCreator {
    public JPanel getCalendarOptionCreator();
    public Lookup getCalendarOptionLookupCreator();
    public double getLevel();
}
