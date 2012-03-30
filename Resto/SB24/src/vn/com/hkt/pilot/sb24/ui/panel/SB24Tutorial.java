/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb24.ui.panel;

import javax.swing.JTextArea;

/**
 *
 * @author tamdong
 */
public class SB24Tutorial {

    private JTextArea particularity; // Đặc tính
    private JTextArea personalPreferences; // Sở thích cá nhân
    private JTextArea careerInterests; // Sở thích nghề nghiệp
    private JTextArea researchInterests; // Sở thích nghiên cứu
    private JTextArea informatics; // Tin học
   // private JTextArea foreignLanguageIdActual; // Ngoại ngữ
    public SB24Tutorial() {
        particularity = new JTextArea();
        particularity.setText("Nhập đặc điểm tính cách \n");
               

        personalPreferences = new JTextArea();
        personalPreferences.setText("Nhập sở thích cá nhân \n");
        careerInterests = new JTextArea();
        careerInterests.setText("Nhập sở thích nghề nghiệp \n");
        researchInterests = new JTextArea();
        researchInterests.setText("Nhập sở thích nghiên cứu\n");

        informatics = new JTextArea();

        informatics.setText("Nhập trình độ tin học \n");
      
        
    }

    public JTextArea getCareerInterests() {
        return careerInterests;
    }

  
    public JTextArea getInformatics() {
        return informatics;
    }
    public JTextArea getParticularity() {
        return particularity;
    }

    public JTextArea getPersonalPreferences() {
        return personalPreferences;
    }

    public JTextArea getResearchInterests() {
        return researchInterests;
    }
}
