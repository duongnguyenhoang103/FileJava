/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.person.extW32.entity;

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
public class PersonExt_W32 {

    @Id
    private String PersonID;
    @Temporal(TemporalType.DATE)// định dạng ngày sinh là kiểu date
    private Date dateStart;// 
    @Temporal(TemporalType.DATE)
    private Date dateEnd;
    private String position; //chứ vụ
    private String company; //công ty    
    private String describe;//mô tả công việc
    private String commentCause;//Bình luận , lý do

    public String getPersonID() {
        return PersonID;
    }

    public void setPersonID(String PersonID) {
        this.PersonID = PersonID;
    }

    public String getCommentCause() {
        return commentCause;
    }

    public void setCommentCause(String commentCause) {
        this.commentCause = commentCause;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public PersonExt_W32(String PersonID, Date dateStart, Date dateEnd, String position, String company, String describe, String commentCause) {
        this.PersonID = PersonID;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.position = position;
        this.company = company;
        this.describe = describe;
        this.commentCause = commentCause;
    }

    public PersonExt_W32() {
        super();
    }

    @Override
    public String toString() {
        return PersonID;
    }
}
