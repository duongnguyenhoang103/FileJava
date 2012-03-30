/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.subperson.ui.panel;

import javax.swing.JTextArea;

/**
 *
 * @author longnt
 */
public class SB21Tutorial {

    private JTextArea txtIdentityCard, txtBirthDay, txtHeight, txtMaritalStatus, txtPhoneNumber, txtEmail,
            txtAddress, txtGender, txtWeight, txtChildrenNum, txtMobileNumber, txtWebName, txtCountry, txtCity, txtAge;

    public JTextArea getTxtAge() {
        return txtAge;
    }

    public JTextArea getTxtAddress() {
        return txtAddress;
    }

    public JTextArea getTxtBirthDay() {
        return txtBirthDay;
    }

    public JTextArea getTxtChildrenNum() {
        return txtChildrenNum;
    }

    public JTextArea getTxtCity() {
        return txtCity;
    }

    public JTextArea getTxtCountry() {
        return txtCountry;
    }

    public JTextArea getTxtEmail() {
        return txtEmail;
    }

    public JTextArea getTxtGender() {
        return txtGender;
    }

    public JTextArea getTxtHeight() {
        return txtHeight;
    }

    public JTextArea getTxtIdentityCard() {
        return txtIdentityCard;
    }

    public JTextArea getTxtMaritalStatus() {
        return txtMaritalStatus;
    }

    public JTextArea getTxtMobileNumber() {
        return txtMobileNumber;
    }

    public JTextArea getTxtPhoneNumber() {
        return txtPhoneNumber;
    }

    public JTextArea getTxtWebName() {
        return txtWebName;
    }

    public JTextArea getTxtWeight() {
        return txtWeight;
    }

    public SB21Tutorial() {
        txtIdentityCard = new JTextArea();
        txtIdentityCard.setText("Nhập số chứng minh nhân dân, chỉ được nhập các ký tự số từ 0->9");

        txtHeight = new JTextArea();
        txtHeight.setText("Nhập chiều cao, chỉ được nhập số ví dụ chiều cao 1.56m thì nhập 1.56 ");
        txtBirthDay = new JTextArea();
        txtBirthDay.setText("Nhập ngày sinh định dạng ngày-tháng-năm hoặc ngày/tháng/năm\n"
                +"hoặc có thể chọn từ lịch");
        txtMaritalStatus = new JTextArea();
        txtMaritalStatus.setText("Chọn tình trạng hôn nhân từ combobox");

        txtPhoneNumber = new JTextArea();

        txtPhoneNumber.setText("Nhập số điện thoại, chỉ được nhập ký tự số 0->9 và ký tự + và khoảng trống\n"
                +"ví dụ như số điện thoại là +84 914 052 371 hoặc 0914 052 371");
        txtEmail = new JTextArea();

        txtEmail.setText("Nhập địa chỉ mail, định dạng theo kiểu mail");
        txtAddress = new JTextArea();

        txtAddress.setText("Nhập địa chỉ ");
        txtGender = new JTextArea();

        txtGender.setText("chọn giới tính từ combobox");

        txtWeight = new JTextArea();

        txtWeight.setText("Nhập cân nặng, chỉ được nhập số kiểu int hoặc float,\n"
                +"ví dụ như nặng 40kg thì nhập 40, nặng 45,5 kg thì nhập 45.5 ");
        txtWebName = new JTextArea();

        txtWebName.setText("Nhập địa chỉ website");
        txtCity = new JTextArea();

        txtCity.setText("Chọn thành phố từ combobox");
        txtCountry = new JTextArea();

        txtCountry.setText("Chọn quốc gia từ combobox");

        txtChildrenNum = new JTextArea();

        txtChildrenNum.setText("Nhập số con, chỉ được nhập kiểu số, ví dụ 3 con thì nhập 3");

        txtMobileNumber = new JTextArea();

        txtMobileNumber.setText("Nhập số điện thoại di dộng, chỉ nhập ký tự số,\n"
                + "khoảng trắng,có thể có + ở đầu sốn\n"
        + "ví dụ số điện thoại di động là +84 1682792736 hoặc 0168 279 2736");
        
        txtAge = new JTextArea();

        txtAge.setText("Hiển thị tuổi của nhân viên/ cá nhân");

    }
}
