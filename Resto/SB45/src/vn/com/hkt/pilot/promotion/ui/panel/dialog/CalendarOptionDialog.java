/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.promotion.ui.panel.dialog;

import java.awt.Frame;
import java.util.Collection;
import javax.swing.JDialog;
import javax.swing.JPanel;
import org.openide.util.Lookup;
import vn.com.hkt.pilot.calendaroption.api.ICalendarOptionCreator;

/**
 *
 * @author khangpn
 */
public class CalendarOptionDialog extends JDialog{
    
    private Collection<? extends ICalendarOptionCreator> calendarOptionCreators;

    public CalendarOptionDialog(Frame owner) {
        super(owner);
        calendarOptionCreators = Lookup.getDefault().lookupAll(ICalendarOptionCreator.class);
        if(!calendarOptionCreators.isEmpty()){
            for(ICalendarOptionCreator calendarOptionCreator:calendarOptionCreators){
                if(calendarOptionCreator.getLevel()==5.2){
                    JPanel panel = calendarOptionCreator.getCalendarOptionCreator();
                    getContentPane().add(panel);
                }
            }
        }
        setSize(400, 350);
    }
    
}
