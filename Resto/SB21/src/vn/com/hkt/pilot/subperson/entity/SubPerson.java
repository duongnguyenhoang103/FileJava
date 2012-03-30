/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.subperson.entity;

import java.lang.reflect.Field;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.subperson.Installer;
import vn.com.hkt.pilot.subperson.dao.SubPersonBN;

/**
 *
 * @author khangpn
 */
@Entity
@ServiceProvider(service = IEntity.class)
public class SubPerson implements IEntity {

    public static final String FIELD_PERSON_ID = "personIdActual";
    public static final String FIELD_IDENTITY_CARD = "identityCard";
    public static final String FIELD_BIRTHDAY = "birthDay";
    public static final String FIELD_HEIGHT = "height";
    public static final String FIELD_MARITAL_STATUS = "maritalStatus";
    public static final String FIELD_PHONE_NUMBER = "phoneNumber";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_ADDRESS = "address";
    public static final String FIELD_GENDER = "gender";
    public static final String FIELD_WEIGHT = "weight";
    public static final String FIELD_CHILDREN_NUM = "childrenNum";
    public static final String FIELD_MOBILE_NUMBER = "mobileNumber";
    public static final String FIELD_WEB_NAME = "webName";
    public static final String FIELD_COUNTRY_ID = "countryId";
    public static final String FIELD_CITY_ID = "cityId";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int personIdActual;
    private String identityCard; // Số CMND
    @Temporal(TemporalType.DATE)
    private Calendar birthDay; // Ngày sinh
    private int height; // Chiều cao
    private String maritalStatus; // Tình trạng hôn nhân
    private String phoneNumber; // Số điện thoại
    private String email;
    private String address; // Địa chỉ
    private String gender; // Giới tính
    private double weight; // Cân nặng
    private int childrenNum; // Số con
    private String mobileNumber; // Đt DĐ
    private String webName; // Địa chỉ web
    private int countryIdActual; // Quốc gia
    private int cityIdActual; // Thành phố thuộc Quốc gia trên

    public SubPerson() {
    }

    public SubPerson(int personIdActual, String identityCard, Calendar birthDay,
            int height, String maritalStatus, String phoneNumber, String email,
            String address, String gender, double weight, int childrenNum,
            String mobileNumber, String webName, int countryIdActual,
            int cityIdActual) {
        this.personIdActual = personIdActual;
        this.identityCard = identityCard;
        this.birthDay = birthDay;
        this.height = height;
        this.maritalStatus = maritalStatus;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.weight = weight;
        this.childrenNum = childrenNum;
        this.mobileNumber = mobileNumber;
        this.webName = webName;
        this.countryIdActual = countryIdActual;
        this.cityIdActual = cityIdActual;
    }

    public int getCityIdActual() {
        return cityIdActual;
    }

    public void setCityIdActual(int cityIdActual) {
        this.cityIdActual = cityIdActual;
    }

    public int getCountryIdActual() {
        return countryIdActual;
    }

    public void setCountryIdActual(int countryIdActual) {
        this.countryIdActual = countryIdActual;
    }

    public int getPersonIdActual() {
        return personIdActual;
    }

    public void setPersonIdActual(int personIdActual) {
        this.personIdActual = personIdActual;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Calendar getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Calendar birthDay) {
        this.birthDay = birthDay;
    }

    public int getChildrenNum() {
        return childrenNum;
    }

    public void setChildrenNum(int childrenNum) {
        this.childrenNum = childrenNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebName() {
        return webName;
    }

    public void setWebName(String webName) {
        this.webName = webName;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.valueOf(personIdActual);
    }

    @Override
    public String getEntityName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getModuleOfEntity() {
        return Installer.MODULE_NAME;
    }

    @Override
    public String getEntityDescription() {
        return "Mở rộng Module SB21";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new SubPersonBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_PERSON_ID;
    }

     @Override
    public String getDataRealyOfField(String fieldName, String data) {
        return data;
    }

    @Override
    public String getDescriptionOfField(String fieldName) {
        return fieldName;
    }
}
