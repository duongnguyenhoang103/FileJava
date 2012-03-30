/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vn.hkt.core.cookie.api;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.openide.nodes.Node.Cookie;

/**
 *
 * @author khangpn
 */
public interface FilterCokieTable extends Cookie{
    public void filterTable(JTable table, DefaultTableModel tableModel);
    public void filterTable(String stringKey, int column);
}
