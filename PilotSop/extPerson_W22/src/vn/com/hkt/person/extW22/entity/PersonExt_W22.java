/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.person.extW22.entity;

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
public class PersonExt_W22 {

    @Id
    private String PersonID;    
    private String identityCard;// chứng minh thư của nhân viên 
    private boolean gender;// giới tinh của nhân viên( nam=true; nữ =false)
    @Temporal(TemporalType.DATE)// định dạng ngày sinh là kiểu date
    private Date birthDay;// ngày sinh của nhân viên
    private int age; // tuổi nhân viên
    private float tall;// chiều cao
    private float weight;// cân nặng
    private int children;// số con của nhân viên
    private String personAdderss;// địa chỉ nhân viên
    private String stateMarriage; //tình trạng hôn nhân
    private String telephone;// số điện thoại của nhân viên
    private String mobile;// số mobile của nhân viên   
    private String email;// email
    private String webSite;// website của nhân viên

    public String getPersonID() {
        return PersonID;
    }

    public void setPersonID(String PersonID) {
        this.PersonID = PersonID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPersonAdderss() {
        return personAdderss;
    }

    public void setPersonAdders(String personAdderss) {
        this.personAdderss = personAdderss;
    }

    public String getStateMarriage() {
        return stateMarriage;
    }

    public void setStateMarriage(String stateMarriage) {
        this.stateMarriage = stateMarriage;
    }

    public float getTall() {
        return tall;
    }

    public void setTall(float tall) {
        this.tall = tall;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public PersonExt_W22(String PersonID, String identityCard, boolean gender, Date birthDay, int age, float tall, float weight, int children, String personAdderss, String stateMarriage, String telephone, String mobile, String email, String webSite) {
        this.PersonID = PersonID;
        this.identityCard = identityCard;
        this.gender = gender;
        this.birthDay = birthDay;
        this.age = age;
        this.tall = tall;
        this.weight = weight;
        this.children = children;
        this.personAdderss = personAdderss;
        this.stateMarriage = stateMarriage;
        this.telephone = telephone;
        this.mobile = mobile;
        this.email = email;
        this.webSite = webSite;
    }
    
     public PersonExt_W22(){
         super();
     }

    @Override
    public String toString() {
        return  PersonID ;
    }
     
}
