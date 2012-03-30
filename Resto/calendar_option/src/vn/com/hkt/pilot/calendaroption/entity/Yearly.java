/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.calendaroption.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author HKT01
 */
@Entity
public class Yearly {

    /*********** Properties *************/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int dayOfMonth; // Ngay trong thang - VD ngay 23
    private int monthOfYear; // Thang trong nam
    private int dayLevel; // Tuan thu may - VD: tat ca cac ngay cua tuan thu 2 trong thang
    /**
     * Thu trong tuan - VD: thu cn,2,...7--> 1,2...7
     * 0 - Day 
     * 1..7 : CN..Thu 7
     * 8 - Weekday Cac ngay thuoc tuan thu n
     * 9 - WeekendDay Cac ngay cuoi tuan
     */
    private List<Integer> dayOfWeek;

    /*********** Constructors *************/
    public Yearly(int dayOfMonth, int monthOfYear, int dayLevel,
            List<Integer> dayOfWeek) {
        this.dayOfMonth = dayOfMonth;
        this.monthOfYear = monthOfYear;
        this.dayLevel = dayLevel;
        this.dayOfWeek = dayOfWeek;
    }

    public Yearly() {
    }

    /*********** Getters and setters *************/
    public int getDayLevel() {
        return dayLevel;
    }

    public void setDayLevel(int dayLevel) {
        this.dayLevel = dayLevel;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public List<Integer> getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(List<Integer> dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonthOfYear() {
        return monthOfYear;
    }

    public void setMonthOfYear(int monthOfYear) {
        this.monthOfYear = monthOfYear;
    }
}
