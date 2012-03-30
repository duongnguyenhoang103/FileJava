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
public class Monthly {

    /*********** Properties *************/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int day; // Ngay
    private List<Integer> dayofWeek; // Thu - VD: thu 2..cn-> thu 1(1...7)
    private int stepOfMonth; // So thang lap lai
    /**
     * Thu tu lap - VD: Thu 6 dau tien, thu 6 thu 2 trong thang
     * Gia tri tu 1 den 4
     */
    private int dayLevel;

    /*********** Constructors *************/
    public Monthly(int day, List<Integer> dayofWeek, int stepOfMonth, int dayLevel) {
        this.day = day;
        this.dayofWeek = dayofWeek;
        this.stepOfMonth = stepOfMonth;
        this.dayLevel = dayLevel;
    }

    public Monthly() {
    }

    /*********** Getters and Setters *************/
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDayLevel() {
        return dayLevel;
    }

    public void setDayLevel(int dayLevel) {
        this.dayLevel = dayLevel;
    }

    public List<Integer> getDayofWeek() {
        return dayofWeek;
    }

    public void setDayofWeek(List<Integer> dayofWeek) {
        this.dayofWeek = dayofWeek;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStepOfMonth() {
        return stepOfMonth;
    }

    public void setStepOfMonth(int stepOfMonth) {
        this.stepOfMonth = stepOfMonth;
    }
}
