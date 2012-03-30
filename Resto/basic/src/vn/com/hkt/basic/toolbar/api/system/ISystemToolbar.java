/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.basic.toolbar.api.system;

import java.awt.Color;
import javax.swing.ImageIcon;
import vn.com.hkt.basic.toolbar.api.ITabToolbar;

/**
 *
 * @author Admin
 */
public interface ISystemToolbar extends ITabToolbar {

    public static final int TAB_INDEX = 10;
    public static final String TAB_NAME = "Hệ thống";

    public Color getColorLight();

    public Color getColorMouseMove();

    public Color getColorBackground();
    
    public Color getColorDark();
    
    public Color getColorMouseClick();
    
    public Color getColorWord();
    
    public Color getColorTitle();
    
    public ImageIcon getImageBackground();
    
    public boolean backgroundIsImage();
    
    public int getSizeF();

    public String getFontF();
}
