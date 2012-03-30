/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.calendaroption.ui.panel;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author khangpn
 */
public class CalendarOptionRender extends AbstractCellEditor implements TableCellEditor,
        TableCellRenderer, ItemListener, ActionListener {

    private Component component;
    private JCheckBox chkMon, chkTue, chkWed, chkThu, chkFri, chkSat, chkSun;
    private JComboBox cboChooseMonthYear, cboDayOfWeekInMonthLevel, cboDayOfWeekInYearLevel;
    private JTextField txtDayOfMonthInMonth, txtMonthOfYearInYear,
            txtDayOfMonthInYear;
    private JSpinner sprMonthStepInMonth;
    private CalendarOptionTablePanel calendarOptionTablePanel;
    private DefaultComboBoxModel cboChooseMonthYearModel, cboDayOfWeekInMonthLevelModel,
            cboDayOfWeekInYearLevelModel;
    private JLabel lblWeekTitle, lblMonthTitle, lblWeekInMonthStep,
            lblDayOfMonth;
    private int mon = 0, tue = 0, wed = 0, thu = 0, fri = 0, sat = 0, sun = 0;
    private int monthInYear = 0;
    private boolean isDaily = false;
    private boolean isWeekly = false;
    private boolean isMonthly = false;
    private boolean isYearly = false;
    private int stepNumberOfMonth = 1, dayLevelOfMonth = 1, dayOfMonthInYear = 0,
            dayOfMonthInMonth = 1, dayLevelOfYear = 1;

    public CalendarOptionRender(CalendarOptionTablePanel calendarOptionTablePanel) {

        this.calendarOptionTablePanel = calendarOptionTablePanel;

        lblWeekTitle = new JLabel("Theo tuần");
        lblMonthTitle = new JLabel("Tháng");
        lblWeekInMonthStep = new JLabel("Khoảng cách");
        lblDayOfMonth = new JLabel("Ngày trong tháng");

        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(1, 1, 200, 1);
        sprMonthStepInMonth = new JSpinner(spinnerNumberModel);
        sprMonthStepInMonth.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                stepNumberOfMonth = Integer.parseInt(sprMonthStepInMonth.getValue().toString());
            }
        });

        chkFri = new JCheckBox("Thứ 6");
        chkFri.addActionListener(this);
        chkMon = new JCheckBox("Thứ 2");
        chkMon.addActionListener(this);
        chkSat = new JCheckBox("Thứ 7");
        chkSat.addActionListener(this);
        chkSun = new JCheckBox("Chủ nhật");
        chkSun.addActionListener(this);
        chkThu = new JCheckBox("Thứ 5");
        chkThu.addActionListener(this);
        chkTue = new JCheckBox("Thứ 3");
        chkTue.addActionListener(this);
        chkWed = new JCheckBox("Thứ 4");
        chkWed.addActionListener(this);

        loadCombo();

        cboChooseMonthYear = new JComboBox(cboChooseMonthYearModel);
        cboChooseMonthYear.setSelectedIndex(2);
        cboChooseMonthYear.addItemListener(this);

        cboDayOfWeekInMonthLevel = new JComboBox(cboDayOfWeekInMonthLevelModel);
        cboDayOfWeekInMonthLevel.addItemListener(this);

        cboDayOfWeekInYearLevel = new JComboBox(cboDayOfWeekInYearLevelModel);
        cboDayOfWeekInYearLevel.addItemListener(this);

        txtDayOfMonthInMonth = new JTextField();
        txtDayOfMonthInMonth.addCaretListener(new CaretListener() {

            @Override
            public void caretUpdate(CaretEvent e) {
                try {
                    dayOfMonthInMonth = Integer.parseInt(txtDayOfMonthInMonth.getText());
                } catch (Exception exception) {
                    System.out.println("Lỗi nhập dữ liệu!");
                }
            }
        });

        txtDayOfMonthInYear = new JTextField();
        txtDayOfMonthInYear.addCaretListener(new CaretListener() {

            @Override
            public void caretUpdate(CaretEvent e) {
                try {
                    dayOfMonthInYear = Integer.parseInt(txtDayOfMonthInYear.getText());
                } catch (Exception exception) {
                    System.out.println("Lỗi nhập dữ liệu!");
                }
            }
        });
        txtMonthOfYearInYear = new JTextField();
        txtMonthOfYearInYear.addCaretListener(new CaretListener() {

            @Override
            public void caretUpdate(CaretEvent e) {
                try {
                    monthInYear = Integer.parseInt(txtMonthOfYearInYear.getText());
                } catch (Exception exception) {
                    System.out.println("Lỗi nhập dữ liệu!");
                }
            }
        });        
        
    }

    protected void loadCombo() {
        String[] strMonthYearModel = {"Theo tháng", "Theo năm", "N/A"};
        cboChooseMonthYearModel = new DefaultComboBoxModel(strMonthYearModel);        

        String[] level = {"Đầu tiên", "Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5"};
        cboDayOfWeekInMonthLevelModel = new DefaultComboBoxModel(level);

        cboDayOfWeekInYearLevelModel = new DefaultComboBoxModel(level);


    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        return setUpComponent(row, column);

    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        return setUpComponent(row, column);
    }

    @Override
    public Object getCellEditorValue() {
        if (component instanceof JComboBox) {
            JComboBox comboBox = (JComboBox) component;
            return comboBox.getSelectedItem();
        } else if (component instanceof JSpinner) {
            JSpinner spinner = (JSpinner) component;
            return spinner.getValue();
        } else if (component instanceof JCheckBox) {
            JCheckBox checkBox = (JCheckBox) component;
            return checkBox.isSelected();
        } else if (component instanceof JTextField) {
            JTextField textField = (JTextField) component;
            return textField.getText();
        }
        return null;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == cboChooseMonthYear) {
            calendarOptionTablePanel.rePaintTable();
            if (cboChooseMonthYear.getSelectedIndex() == 2) {
                resetBoolean();
                this.isWeekly = true;
            } else if (cboChooseMonthYear.getSelectedIndex() == 0) {
                resetBoolean();
                this.isMonthly = true;
            } else {
                resetBoolean();
                this.isYearly = true;
            }
        }
        if (e.getSource() == cboDayOfWeekInMonthLevel) {
            dayLevelOfMonth = cboDayOfWeekInMonthLevel.getSelectedIndex() + 1;
        }
        if (e.getSource() == cboDayOfWeekInYearLevel) {
            dayLevelOfYear = cboDayOfWeekInYearLevel.getSelectedIndex() + 1;
        }
    }

    protected Component setUpComponent(int row, int column) {
        if (column == 0) {
            if (row == 0) {
                component = lblWeekTitle;
            } else if (row == 3) {
                component = cboChooseMonthYear;
            } else if (row == 4) {
                if (cboChooseMonthYear.getSelectedIndex() == 0) {
                    component = lblDayOfMonth;
                } else {
                    component = null;
                }

            } else {
                component = null;
            }
        } else if (column == 1) {
            if (row == 0) {
                component = chkMon;
            } else if (row == 1) {
                component = chkTue;
            } else if (row == 2) {
                component = chkWed;
            } else if (row == 3) {
                if (cboChooseMonthYear.getSelectedIndex() == 0) {
                    component = cboDayOfWeekInMonthLevel;
                } else if (cboChooseMonthYear.getSelectedIndex() == 1) {
                    component = cboDayOfWeekInYearLevel;
                } else {
                    component = null;
                }
            } else if (row == 4) {
                if (cboChooseMonthYear.getSelectedIndex() == 0) {
                    component = txtDayOfMonthInMonth;
                } else if (cboChooseMonthYear.getSelectedIndex() == 1) {
                    component = null;
                } else {
                    component = null;
                }
            } else {
                component = null;
            }

        } else if (column == 2) {
            if (row == 0) {
                component = chkThu;
            } else if (row == 1) {
                component = chkFri;
            } else if (row == 3) {
                if (cboChooseMonthYear.getSelectedIndex() == 0) {
                    component = null;
                } else if (cboChooseMonthYear.getSelectedIndex() == 1) {
                    component = lblMonthTitle;
                } else {
                    component = null;
                }
            } else if (row == 4) {
                if (cboChooseMonthYear.getSelectedIndex() == 0) {
                    return lblWeekInMonthStep;
                } else if (cboChooseMonthYear.getSelectedIndex() == 1) {
                    component = lblDayOfMonth;
                } else {
                    component = null;
                }

            } else {
                component = null;
            }
        } else if (column == 3) {
            if (row == 0) {
                component = chkSat;
            } else if (row == 1) {
                component = chkSun;
            } else if (row == 3) {
                if (cboChooseMonthYear.getSelectedIndex() == 1) {
                    component = txtMonthOfYearInYear;
                } else {
                    component = null;
                }
            } else if (row == 4) {
                if (cboChooseMonthYear.getSelectedIndex() == 1) {
                    component = txtDayOfMonthInYear;
                } else if (cboChooseMonthYear.getSelectedIndex() == 0) {
                    component = sprMonthStepInMonth;
                } else {
                    component = null;
                }
            } else {
                component = null;
            }
        }
        return component;
    }

    public void addData() {

        /**
         * Set các tùy chọn theo ngày, tuần, tháng năm
         */
        calendarOptionTablePanel.setIsDaily(isDaily);
        calendarOptionTablePanel.setIsWeekly(isWeekly);
        calendarOptionTablePanel.setIsMonthly(isMonthly);
        calendarOptionTablePanel.setIsYearly(isYearly);

        /**
         * Các tùy chọn theo tuần
         */
        calendarOptionTablePanel.setMon(mon);
        calendarOptionTablePanel.setTue(tue);
        calendarOptionTablePanel.setWed(wed);
        calendarOptionTablePanel.setThu(thu);
        calendarOptionTablePanel.setFri(fri);
        calendarOptionTablePanel.setSat(sat);
        calendarOptionTablePanel.setSun(sun);

        /**
         * Các tùy chọn theo tháng
         * List được dùng cho tùy chọn năm nữa
         */
        List<Integer> daysOfWeek = new ArrayList<Integer>();
        daysOfWeek.add(mon);
        daysOfWeek.add(tue);
        daysOfWeek.add(wed);
        daysOfWeek.add(thu);
        daysOfWeek.add(fri);
        daysOfWeek.add(sat);
        daysOfWeek.add(sun);
        calendarOptionTablePanel.setDaysOfWeek(daysOfWeek);
        calendarOptionTablePanel.setDayOfMonthInMonth(dayOfMonthInMonth);
        calendarOptionTablePanel.setStepNumberOfMonth(stepNumberOfMonth);
        calendarOptionTablePanel.setDayLevelOfMonth(dayLevelOfMonth);



        /**
         * Các tùy chọn theo năm
         */
        calendarOptionTablePanel.setDayLevelOfYear(dayLevelOfYear);
        calendarOptionTablePanel.setMonthInYear(monthInYear);
        calendarOptionTablePanel.setDayOfMonthInYear(dayOfMonthInYear);

        JOptionPane.showMessageDialog(null, "Added!");
    }

    protected void checkDayChooser(){
        if(cboChooseMonthYear.getSelectedIndex()==2){
            isWeekly = true;
        }
        else{
            isWeekly = false;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == chkFri) {
            checkDayChooser();
            fri = chkFri.isSelected() ? 6 : 0;
        }
        if (e.getSource() == chkMon) {
            checkDayChooser();
            mon = chkMon.isSelected() ? 2 : 0;
        }
        if (e.getSource() == chkSat) {
            checkDayChooser();
            sat = chkSat.isSelected() ? 7 : 0;
        }
        if (e.getSource() == chkSun) {
            checkDayChooser();
            sun = chkSun.isSelected() ? 1 : 0;
        }
        if (e.getSource() == chkThu) {
            checkDayChooser();
            thu = chkThu.isSelected() ? 5 : 0;
        }
        if (e.getSource() == chkTue) {
            checkDayChooser();
            tue = chkTue.isSelected() ? 3 : 0;
        }
        if (e.getSource() == chkWed) {
            checkDayChooser();
            wed = chkWed.isSelected() ? 4 : 0;
        }
    }
    
    public void resetBoolean(){
        this.isDaily = false;
        this.isMonthly = false;
        this.isWeekly = false;
        this.isYearly = false;
    }
}
