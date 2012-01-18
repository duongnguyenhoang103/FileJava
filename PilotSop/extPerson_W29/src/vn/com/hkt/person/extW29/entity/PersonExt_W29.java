/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.person.extW29.entity;

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
public class PersonExt_W29 {

    @Id
    private String PersonID;
    @Temporal(TemporalType.DATE)// định dạng ngày sinh là kiểu date
    private Date dateStart;// 
    @Temporal(TemporalType.DATE)
    private Date dateEnd;
    private String information; // thông tin
    private String universityDegree; // học vị
    private String universityRank;// học hàm

    public String getPersonID() {
        return PersonID;
    }

    public void setPersonID(String PersonID) {
        this.PersonID = PersonID;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getUniversityRank() {
        return universityRank;
    }

    public void setUniversityRank(String universityRank) {
        this.universityRank = universityRank;
    }

    public PersonExt_W29(String PersonID, Date dateStart, Date dateEnd, String information, String universityDegree, String universityRank) {
        this.PersonID = PersonID;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.information = information;
        this.universityDegree = universityDegree;
        this.universityRank = universityRank;
    }
 
    public PersonExt_W29() {
        super();
    }

    @Override
    public String toString() {
        return PersonID;
    }
}
