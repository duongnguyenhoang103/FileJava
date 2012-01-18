/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.person.extW31.entity;

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
public class PersonExt_W31 {

    @Id
    private String PersonID;
    @Temporal(TemporalType.TIME)// định dạng 
    private Date time;//    
    private String nameCongTrinh; // thông tin
    private String noteEffect; // ghi chú kêt quả

    public String getPersonID() {
        return PersonID;
    }

    public void setPersonID(String PersonID) {
        this.PersonID = PersonID;
    }

    public String getNameCongTrinh() {
        return nameCongTrinh;
    }

    public void setNameCongTrinh(String nameCongTrinh) {
        this.nameCongTrinh = nameCongTrinh;
    }

    public String getNoteEffect() {
        return noteEffect;
    }

    public void setNoteEffect(String noteEffect) {
        this.noteEffect = noteEffect;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public PersonExt_W31(String PersonID, Date time, String nameCongTrinh, String noteEffect) {
        this.PersonID = PersonID;
        this.time = time;
        this.nameCongTrinh = nameCongTrinh;
        this.noteEffect = noteEffect;
    }

    public PersonExt_W31() {
        super();
    }

    @Override
    public String toString() {
        return PersonID;
    }
}
