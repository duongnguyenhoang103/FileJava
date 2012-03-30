/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.backup.checkboxNode;

/**
 *
 * @author Admin
 */
public class CheckBoxNode {

    String text;
    boolean selected;

    public CheckBoxNode(String text, boolean selected) {
        this.text = text;
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean newValue) {
        selected = newValue;
    }

    public String getText() {
        return text;
    }

    public void setText(String newValue) {
        text = newValue;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[" + text + "/" + selected + "]";
    }
}
