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
public class Weekly {
    
    /*********** Properties *************/
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private List<Integer> dayOfWeek;
    private int stepNumber; // So tuan lap lai

    

    /*********** Constructors *************/
    
    public Weekly(List<Integer> dayOfWeek, int stepNumber) {
        this.dayOfWeek = dayOfWeek;
        this.stepNumber = stepNumber;
    }

    public Weekly() {
    }

    /*********** Getters and Setters *************/
    
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

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    
   
    
}
