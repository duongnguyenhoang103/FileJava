/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.calendaroption.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author HKT01
 */
@Entity
public class Daily {
    
    /*********** Properties *************/
    
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private int stepNumber; // So ngay lap lai
    private boolean everyWeekday; // Co thuong xuyen khong

    /*********** Constructors *************/
    
    public Daily(int stepNumber, boolean everyWeekday) {
        this.stepNumber = stepNumber;
        this.everyWeekday = everyWeekday;
    }

    public Daily() {
    }

    /*********** Getters and Setters *************/
  
    public boolean isEveryWeekday() {
        return everyWeekday;
    }

    public void setEveryWeekday(boolean everyWeekday) {
        this.everyWeekday = everyWeekday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    @Override
    public String toString() {
        return "Daily{" + "everyWeekday=" + everyWeekday + '}';
    }
    
}
