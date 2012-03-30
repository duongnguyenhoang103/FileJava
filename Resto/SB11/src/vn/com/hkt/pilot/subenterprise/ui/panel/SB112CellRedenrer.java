/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.subenterprise.ui.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;

/**
 *
 * @author longnt
 */
public class SB112CellRedenrer extends StripedTableCellRenderer {

    private JLabel lable1, lable2, lable3, lable4, lable5, lable6, lable7;
    private Color color1, color2;
    private boolean check1 = true, check2 = true, check3 = true, check4 = true, check5 = true;
    private String font;
    private int size;
    private Color colorWord;

    public SB112CellRedenrer(Color colorL, Color colorD, boolean check1, boolean check2, boolean check3, boolean check4, boolean check5) {
        this.color1 = colorL;
        this.color2 = colorD;
        this.check1 = check1;
        this.check2 = check2;
        this.check3 = check3;
        this.check4 = check4;
        this.check5 = check5;
        lable1 = new JLabel();
        lable2 = new JLabel();
        lable3 = new JLabel();
        lable4 = new JLabel();
        lable5 = new JLabel();
        lable6 = new JLabel();
        lable7 = new JLabel();

    }

    public Color getColor1() {
        return color1;
    }

    public SB112CellRedenrer(TableCellRenderer targetRenderer, Color evenBack, Color evenFore, Color oddBack, Color oddFore) {
        super(targetRenderer, evenBack, evenFore, oddBack, oddFore);
        lable1 = new JLabel();
        lable2 = new JLabel();
        lable3 = new JLabel();
        lable4 = new JLabel();
        lable5 = new JLabel();
        lable6 = new JLabel();
        lable7 = new JLabel();
        this.color1 = evenBack;
        this.color2 = oddBack;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        font = table.getFont().getFontName();
        size = table.getFont().getSize();
        colorWord = table.getForeground();
        if (column == 1) {
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
                if (check1 == false) {
                    colorWord = Color.red;
                    lable2.setBorder(BorderFactory.createLineBorder(colorWord));
                }
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
                if (check2 == false) {
                    colorWord = Color.red;
                    lable3.setBorder(BorderFactory.createLineBorder(colorWord));
                }
                lable3.setBackground(color1);
                lable3.setFont(new Font(font, 0, size));
                lable3.setForeground(colorWord);
                return lable3;
            }
            if (row == 3) {
                if (table.getValueAt(3, 1) != null) {
                    lable4.setText(table.getValueAt(3, 1).toString());
                }

                lable4.setOpaque(true);
                lable4.setBackground(color2);
                lable4.setFont(new Font(font, 0, size));
                lable4.setForeground(colorWord);
                return lable4;
            }
        }
        if (column == 3) {
            if (row == 0) {
                if (table.getValueAt(0, 3) != null) {
                    lable5.setText(table.getValueAt(0, 3).toString());
                }
                lable5.setOpaque(true);
                if (check3 == false) {
                    colorWord = Color.red;
                    lable5.setBorder(BorderFactory.createLineBorder(colorWord));
                }
                lable5.setBackground(color1);
                lable5.setFont(new Font(font, 0, size));
                lable5.setForeground(colorWord);
                return lable5;
            }
            if (row == 1) {
                if (table.getValueAt(1, 3) != null) {
                    lable6.setText(table.getValueAt(1, 3).toString());
                }

                lable6.setOpaque(true);
                if (check4 == false) {
                    colorWord = Color.red;
                    lable6.setBorder(BorderFactory.createLineBorder(colorWord));
                }
                lable6.setBackground(color2);
                lable6.setFont(new Font(font, 0, size));
                lable6.setForeground(colorWord);
                return lable6;
            }
            if (row == 2) {
                if (table.getValueAt(2, 3) != null) {
                    lable7.setText(table.getValueAt(2, 3).toString());
                }

                lable7.setOpaque(true);
                if (check5 == false) {
                    colorWord = Color.red;
                    lable7.setBorder(BorderFactory.createLineBorder(colorWord));
                }
                lable7.setBackground(color1);
                lable7.setFont(new Font(font, 0, size));
                lable7.setForeground(colorWord);
                return lable7;
            }

        }
        return lable1;
    }
}
