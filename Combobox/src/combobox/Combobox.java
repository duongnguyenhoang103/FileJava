/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package combobox;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 *
 * @author duong
 */
public class Combobox extends JFrame {

    private JComboBox comboBox = new JComboBox();

    public Combobox() {

        JButton changeComboBoxData = new JButton("Change data");
        changeComboBoxData.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                DefaultComboBoxModel cbm = new DefaultComboBoxModel(
                        new String[]{"hola", "adios",""});
                comboBox.setModel(cbm);
            }
        });

        super.setLayout(new BorderLayout(10, 10));
        super.setSize(100, 100);
        super.add(changeComboBoxData, BorderLayout.NORTH);
        super.add(comboBox, BorderLayout.SOUTH);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       new Combobox().setVisible(true);

    }
}
