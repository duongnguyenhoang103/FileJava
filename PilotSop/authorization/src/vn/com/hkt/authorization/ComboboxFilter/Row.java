/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.authorization.ComboboxFilter;

/**
 *
 * @author Admin
 */
public class Row {

    private String id, val, extra;

    public Row(String id, String val) {
        this.id = id;
        this.val = val;             
    }

    public String getId() {
        return id;
    }

    public String getVal() {
        return val;
    }

    public String getExtra() {
        return extra;
    }

    @Override
    public String toString() {
        return id + "-" + val;
    }
}
