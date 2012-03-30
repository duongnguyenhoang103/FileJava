/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.backup.checkboxNode;

import java.util.Vector;

/**
 *
 * @author Admin
 */
public class NamedVector extends Vector {

    String name;

    public NamedVector(String name) {
        this.name = name;
    }

    public NamedVector(String name, Object elements[]) {
        this.name = name;
        for (int i = 0, n = elements.length; i < n; i++) {
            add(elements[i]);
        }
    }

    @Override
    public String toString() {
        return "[" + name + "]";
    }
}
