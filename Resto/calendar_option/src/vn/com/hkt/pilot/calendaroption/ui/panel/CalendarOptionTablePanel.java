/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CalendarOptionTablePanel.java
 *
 * Created on Feb 21, 2012, 3:00:07 PM
 */
package vn.com.hkt.pilot.calendaroption.ui.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import vn.com.hkt.pilot.calendaroption.Installer;
import vn.com.hkt.pilot.calendaroption.api.ICalendarOptionCreator;
import vn.com.hkt.pilot.calendaroption.dao.DailyBN;
import vn.com.hkt.pilot.calendaroption.dao.MonthlyBN;
import vn.com.hkt.pilot.calendaroption.dao.WeeklyBn;
import vn.com.hkt.pilot.calendaroption.dao.YearlyBN;
import vn.com.hkt.pilot.calendaroption.entity.Daily;
import vn.com.hkt.pilot.calendaroption.entity.Monthly;
import vn.com.hkt.pilot.calendaroption.entity.SaleOffOptionLookup;
import vn.com.hkt.pilot.calendaroption.entity.Weekly;
import vn.com.hkt.pilot.calendaroption.entity.Yearly;
import vn.com.hkt.pilot.calendaroption.ui.api.ICalendarOptionPanel;
import vn.com.hkt.pilot.identity.presentation.api.IUserInterface;

/**
 *
 * @author khangpn
 */
@ServiceProviders(value = {
    @ServiceProvider(service = ICalendarOptionCreator.class),
    @ServiceProvider(service = ICalendarOptionPanel.class)
})
public class CalendarOptionTablePanel extends javax.swing.JPanel implements
        ActionListener, ICalendarOptionPanel, ICalendarOptionCreator, IUserInterface {

    private Lookup lookup;
    private InstanceContent content = new InstanceContent();
    private DefaultTableModel tableModel;
    private CalendarOptionRender calendarOptionRender;
    private int mon, tue, wed, thu, fri, sat, sun;
    private int dailyId = 0, weeklyId = 0, monthlyId = 0, yearlyId = 0;
    private List<Integer> daysOfWeek;

    public List<Integer> getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(List<Integer> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }
    private boolean isDaily = false;
    private boolean isWeekly = false;
    private boolean isMonthly = false;
    private boolean isYearly = false;
    private int stepNumberOfMonth, dayLevelOfMonth, dayOfMonthInYear,
            dayOfMonthInMonth, monthInYear, dayLevelOfYear, stepDayInDaily;
    private DailyBN dailyBN;
    private WeeklyBn weeklyBn;
    private MonthlyBN monthlyBN;
    private YearlyBN yearlyBN;

    /** Creates new form CalendarOptionTablePanel */
    public CalendarOptionTablePanel() {
        initComponents();

        lookup = new AbstractLookup(content);

        dailyBN = new DailyBN();
        weeklyBn = new WeeklyBn();
        monthlyBN = new MonthlyBN();
        yearlyBN = new YearlyBN();

        loadTable();

        tblCalendarOption.setRowHeight(26);

        calendarOptionRender = new CalendarOptionRender(this);
        tblCalendarOption.getColumnModel().getColumn(0).setCellRenderer(calendarOptionRender);
        tblCalendarOption.getColumnModel().getColumn(1).setCellRenderer(calendarOptionRender);
        tblCalendarOption.getColumnModel().getColumn(2).setCellRenderer(calendarOptionRender);
        tblCalendarOption.getColumnModel().getColumn(3).setCellRenderer(calendarOptionRender);

        tblCalendarOption.getColumnModel().getColumn(0).setCellEditor(calendarOptionRender);
        tblCalendarOption.getColumnModel().getColumn(1).setCellEditor(calendarOptionRender);
        tblCalendarOption.getColumnModel().getColumn(2).setCellEditor(calendarOptionRender);
        tblCalendarOption.getColumnModel().getColumn(3).setCellEditor(calendarOptionRender);

        btnOk.addActionListener(this);

    }

    protected void loadTable() {
        tblCalendarOption.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{
                    {"", " ", "", " "},
                    {"", " ", "", " "},
                    {"", " ", "", " "},
                    {"", " ", "", " "},
                    {"", " ", "", " "}
                },
                new String[]{
                    "", "", "", ""
                }));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblCalendarOption = new javax.swing.JTable();
        btnOk = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(CalendarOptionTablePanel.class, "CalendarOptionTablePanel.border.title"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 255))); // NOI18N

        tblCalendarOption.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblCalendarOption);

        btnOk.setText(org.openide.util.NbBundle.getMessage(CalendarOptionTablePanel.class, "CalendarOptionTablePanel.btnOk.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(331, Short.MAX_VALUE)
                .addComponent(btnOk)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(btnOk))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOk;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCalendarOption;
    // End of variables declaration//GEN-END:variables

    @Override
    public JPanel getCalendarOptionPanel() {
        return this;
    }

    @Override
    public Lookup getCalendarOptionLookup() {
        return lookup;
    }

    @Override
    public void saveCalendarOption() {

        if (isDaily) {
            Daily daily = new Daily();
            daily.setEveryWeekday(isWeekly);
            daily.setStepNumber(stepDayInDaily);
            if (dailyBN.insert(daily)) {
                dailyId = daily.getId();
            }

        }
        if (isWeekly) {
            Weekly weekly = new Weekly();
            weekly.setDayOfWeek(daysOfWeek);
            weekly.setStepNumber(1);
            if (weeklyBn.insert(weekly)) {
                weeklyId = weekly.getId();

            }

        }
        if (isMonthly) {
            Monthly monthly = new Monthly();
            monthly.setDay(dayOfMonthInMonth);
            monthly.setDayLevel(dayLevelOfMonth);
            monthly.setDayofWeek(daysOfWeek);
            monthly.setStepOfMonth(stepNumberOfMonth);
            if (monthlyBN.insert(monthly)) {
                monthlyId = monthly.getId();
            }

        }
        if (isYearly) {
            Yearly yearly = new Yearly();
            yearly.setDayLevel(dayLevelOfYear);
            yearly.setDayOfMonth(dayOfMonthInYear);
            yearly.setDayOfWeek(daysOfWeek);
            yearly.setMonthOfYear(monthInYear);
            if (yearlyBN.insert(yearly)) {
                yearlyId = yearly.getId();
            }
        }

        SaleOffOptionLookup saleOffOptionLookup = new SaleOffOptionLookup();
        saleOffOptionLookup.setDailyID(dailyId);
        saleOffOptionLookup.setIsDaily(isDaily);
        saleOffOptionLookup.setIsMonthly(isMonthly);
        saleOffOptionLookup.setIsWeekly(isWeekly);
        saleOffOptionLookup.setIsYearly(isYearly);
        saleOffOptionLookup.setMonthlyID(monthlyId);
        saleOffOptionLookup.setSaleOffOptionID(dayLevelOfMonth);
        saleOffOptionLookup.setWeeklyID(weeklyId);
        saleOffOptionLookup.setYearlyID(yearlyId);
        content.set(Collections.singleton(saleOffOptionLookup), null);
        JOptionPane.showMessageDialog(null, "Content seted!");

    }

    public void rePaintTable() {
        tblCalendarOption.repaint();
    }

    public int getDailyId() {
        return dailyId;
    }

    public void setDailyId(int dailyId) {
        this.dailyId = dailyId;
    }

    public int getDayOfMonthInYear() {
        return dayOfMonthInYear;
    }

    public void setDayOfMonthInYear(int dayOfMonthInYear) {
        this.dayOfMonthInYear = dayOfMonthInYear;
    }

    public int getDayLevelOfYear() {
        return dayLevelOfYear;
    }

    public void setDayLevelOfYear(int dayLevelOfYear) {
        this.dayLevelOfYear = dayLevelOfYear;
    }

    public int getDayOfMonthInMonth() {
        return dayOfMonthInMonth;
    }

    public void setDayOfMonthInMonth(int dayOfMonthInMonth) {
        this.dayOfMonthInMonth = dayOfMonthInMonth;
    }

    public int getMonthInYear() {
        return monthInYear;
    }

    public void setMonthInYear(int monthInYear) {
        this.monthInYear = monthInYear;
    }

    public int getDayLevelOfMonth() {
        return dayLevelOfMonth;
    }

    public void setDayLevelOfMonth(int dayLevelOfMonth) {
        this.dayLevelOfMonth = dayLevelOfMonth;
    }

    public int getFri() {
        return fri;
    }

    public void setFri(int fri) {
        this.fri = fri;
    }

    public boolean isIsDaily() {
        return isDaily;
    }

    public void setIsDaily(boolean isDaily) {
        this.isDaily = isDaily;
    }

    public boolean isIsMonthly() {
        return isMonthly;
    }

    public void setIsMonthly(boolean isMonthly) {
        this.isMonthly = isMonthly;
    }

    public boolean isIsWeekly() {
        return isWeekly;
    }

    public void setIsWeekly(boolean isWeekly) {
        this.isWeekly = isWeekly;
    }

    public boolean isIsYearly() {
        return isYearly;
    }

    public void setIsYearly(boolean isYearly) {
        this.isYearly = isYearly;
    }

    public Lookup getLookup() {
        return lookup;
    }

    public void setLookup(Lookup lookup) {
        this.lookup = lookup;
    }

    public int getMon() {
        return mon;
    }

    public void setMon(int mon) {
        this.mon = mon;
    }

    public int getMonthlyId() {
        return monthlyId;
    }

    public void setMonthlyId(int monthlyId) {
        this.monthlyId = monthlyId;
    }

    public int getSat() {
        return sat;
    }

    public void setSat(int sat) {
        this.sat = sat;
    }

    public int getStepNumberOfMonth() {
        return stepNumberOfMonth;
    }

    public void setStepNumberOfMonth(int stepNumberOfMonth) {
        this.stepNumberOfMonth = stepNumberOfMonth;
    }

    public int getSun() {
        return sun;
    }

    public void setSun(int sun) {
        this.sun = sun;
    }

    public JTable getTblCalendarOption() {
        return tblCalendarOption;
    }

    public void setTblCalendarOption(JTable tblCalendarOption) {
        this.tblCalendarOption = tblCalendarOption;
    }

    public int getThu() {
        return thu;
    }

    public void setThu(int thu) {
        this.thu = thu;
    }

    public int getTue() {
        return tue;
    }

    public void setTue(int tue) {
        this.tue = tue;
    }

    public int getWed() {
        return wed;
    }

    public void setWed(int wed) {
        this.wed = wed;
    }

    public int getWeeklyId() {
        return weeklyId;
    }

    public void setWeeklyId(int weeklyId) {
        this.weeklyId = weeklyId;
    }

    public int getYearlyId() {
        return yearlyId;
    }

    public void setYearlyId(int yearlyId) {
        this.yearlyId = yearlyId;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnOk) {
            calendarOptionRender.addData();
            JOptionPane.showMessageDialog(null, "Daily:" + isDaily);
            JOptionPane.showMessageDialog(null, "Weekly:" + isWeekly);
            JOptionPane.showMessageDialog(null, "Monthly:" + isMonthly);
            JOptionPane.showMessageDialog(null, "Yearly:" + isYearly);
            saveCalendarOption();
        }
    }

    @Override
    public JPanel getCalendarOptionCreator() {
        return this;
    }

    @Override
    public double getLevel() {
        return 5.2;
    }

    @Override
    public Lookup getCalendarOptionLookupCreator() {
        return lookup;
    }

    @Override
    public String getUserInterfaceName() {
        return getClass().getSimpleName();
    }

    @Override
    public String getUserInterfaceDescription() {
        return "Giao diện bảng thời gian lặp đối với các chương trình khuyến mãi";
    }

    @Override
    public String getModuleName() {
        return Installer.MODULE_NAME;
    }
}
