/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.ui.setup;

import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.api.IWidthTableBN;

/**
 *
 * @author VietAnh
 */
@ServiceProvider(service = IWidthTableBN.class)
public class WidthTableBN implements IWidthTableBN{

    @Override
    public int getWidth1() {
        return 115;
    }

    @Override
    public int getWidth2() {
        return 75;
    }

    @Override
    public int getWidth3() {
        return 500;
    }
    
}
