/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.person.extW30.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author duong
 */
@Entity
public class PersonExt_W30 {

    @Id
    private String PersonID;
    @Temporal(TemporalType.TIME)// định dạng 
    private Date time;//    
    private String information; // thông tin
    private String note ; // ghi chú
   

    public String getPersonID() {
        return PersonID;
    }

    public void setPersonID(String PersonID) {
        this.PersonID = PersonID;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

   
    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public PersonExt_W30(String PersonID, Date time, String information, String note) {
        this.PersonID = PersonID;
        this.time = time;
        this.information = information;
        this.note = note;
    }

   
    public PersonExt_W30() {
        super();
    }

    @Override
    public String toString() {
        return PersonID;
    }
}
