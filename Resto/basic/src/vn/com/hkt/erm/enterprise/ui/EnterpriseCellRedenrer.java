/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.erm.enterprise.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;

/**
 *
 * @author longnt
 */
public class EnterpriseCellRedenrer extends StripedTableCellRenderer {

    private JLabel lable1, lable2, lable3, lable4, lable5;
    private Color color1, color2, color3, color4;
    private String font;
    private int size;
    private Color colorWord;

    public EnterpriseCellRedenrer(Color color1, Color color2, Color color3, Color color4) {
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
        this.color4 = color4;
        lable1 = new JLabel();
        lable2 = new JLabel();
        lable3 = new JLabel();
        lable4 = new JLabel();
        lable5 = new JLabel();
        
    }

   

    public Color getColor1() {
        return color1;
    }

    public EnterpriseCellRedenrer(TableCellRenderer targetRenderer, Color evenBack, Color evenFore, Color oddBack, Color oddFore) {
        super(targetRenderer, evenBack, evenFore, oddBack, oddFore);
        lable1 = new JLabel();
        lable2 = new JLabel();
        lable3 = new JLabel();
        lable4 = new JLabel();
        lable5 = new JLabel();
        this.color1 = evenBack;
        this.color2 = oddBack;
        this.color3 = evenBack;
        this.color4 = oddBack;

    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (column == 1) {
            font = table.getFont().getFontName();
            size = table.getFont().getSize();
            colorWord = table.getForeground();
            if (row == 0) {
                if (table.getValueAt(0, 1) != null) {
                    lable1.setText(table.getValueAt(0, 1).toString());
                }
                lable1.setOpaque(true);
                lable1.setBackground(color1);
                lable1.setFont(new Font(font, 0, size));
                lable1.setForeground(colorWord);
                return lable1;
            }
            if (row == 1) {
                if (table.getValueAt(1, 1) != null) {
                    lable2.setText(table.getValueAt(1, 1).toString());
                }

                lable2.setOpaque(true);
                lable2.setBackground(color2);
                lable2.setFont(new Font(font, 0, size));
                lable2.setForeground(colorWord);
                return lable2;
            }
            if (row == 2) {
                if (table.getValueAt(2, 1) != null) {
                    lable3.setText(table.getValueAt(2, 1).toString());
                }

                lable3.setOpaque(true);
                lable3.setBackground(color3);
                lable3.setFont(new Font(font, 0, size));
                lable3.setForeground(colorWord);
                return lable3;
            }
            if (row == 3) {
                if (table.getValueAt(3, 1) != null) {
                    lable4.setText(table.getValueAt(3, 1).toString());
                }

                lable4.setOpaque(true);
                lable4.setBackground(color4);
                lable4.setFont(new Font(font, 0, size));
                lable4.setForeground(colorWord);
                return lable4;
            }
            if (row == 4) {
                if (table.getValueAt(4, 1) != null) {
                    lable5.setText(table.getValueAt(4, 1).toString());
                }
                lable5.setOpaque(true);
                lable5.setBackground(color3);
                lable5.setFont(new Font(font, 0, size));
                lable5.setForeground(colorWord);
                return lable5;
            }
        }
        return lable1;
    }
}
