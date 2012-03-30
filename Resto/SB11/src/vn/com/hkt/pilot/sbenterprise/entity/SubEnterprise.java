/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sbenterprise.entity;

import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.api.IEnterpriseBN;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.sbenterprise.Installer;
import vn.com.hkt.pilot.subenterprise.dao.SubEnterpriseBN;

/**
 *
 * @author khangpn
 */
@Entity
@ServiceProvider(service = IEntity.class)
public class SubEnterprise implements IEntity {

    public static final String FIELD_ENTERPRISE_ID_ACTUAL = "enterpriseIdActual";
    public static final String FIELD_NAME_VN = "nameVN";
    public static final String FIELD_NAME_ENGLISH = "nameEnglish";
    public static final String FIELD_TYPE = "type";
    public static final String FIELD_REGISTER = "register";
    public static final String FIELD_DATE_START = "dateStart";
    public static final String FIELD_DATE_REGISTER = "dateRegister";
    public static final String FIELD_BOOK_REGISTER = "bookRegisterBussines";
    public static final String FIELD_ID_TAX = "idTax";
    public static final String FIELD_BUSINESS_CODE = "businessCode";
    public static final String FIELD_DESCRIPTTION = "description";
    // W11-------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int enterpriseIdActual;
    private String nameVN;
    private String nameEnglish;
    private String type; //loại hình doanh nghiep
    private int register;//đăng ký KD
    @Temporal(TemporalType.DATE)
    private Calendar dateStart;//ngày thành lập
    @Temporal(TemporalType.DATE)
    private Calendar dateRegister;//ngày đăng ký
    private int bookRegisterBussines; // số đăng ký kinh doanh
    private int idTax;//mã số kinh doanh
    private String state; // tình trạng

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    // W12-------------------------
    private List<Integer> businessAreasIdActual;

    public SubEnterprise(int enterpriseIdActual, String nameVN, String nameEnglish, String type, int register, Calendar dateStart, Calendar dateRegister, int bookRegisterBussines, int idTax, List<Integer> businessAreasIdActual) {
        this.enterpriseIdActual = enterpriseIdActual;
        this.nameVN = nameVN;
        this.nameEnglish = nameEnglish;
        this.type = type;
        this.register = register;
        this.dateStart = dateStart;
        this.dateRegister = dateRegister;
        this.bookRegisterBussines = bookRegisterBussines;
        this.idTax = idTax;
        this.businessAreasIdActual = businessAreasIdActual;
    }

    public List<Integer> getBusinessAreasIdActual() {
        return businessAreasIdActual;
    }

    public void setBusinessAreasIdActual(List<Integer> businessAreasIdActual) {
        this.businessAreasIdActual = businessAreasIdActual;
    }

    /**
     * Constructors
     *
     */
    public int getBookRegisterBussines() {
        return bookRegisterBussines;
    }

    public void setBookRegisterBussines(int bookRegisterBussines) {
        this.bookRegisterBussines = bookRegisterBussines;
    }

    public Calendar getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Calendar dateRegister) {
        this.dateRegister = dateRegister;
    }

    public int getEnterpriseIdActual() {
        return enterpriseIdActual;
    }

    public void setEnterpriseIdActual(int enterpriseIdActual) {
        this.enterpriseIdActual = enterpriseIdActual;
    }

    public SubEnterprise() {
    }

    public Calendar getDateStart() {
        return dateStart;
    }

    public void setDateStart(Calendar dateStart) {
        this.dateStart = dateStart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTax() {
        return idTax;
    }

    public void setIdTax(int idTax) {
        this.idTax = idTax;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public void setNameEnglish(String nameEnglish) {
        this.nameEnglish = nameEnglish;
    }

    public String getNameVN() {
        return nameVN;
    }

    public void setNameVN(String nameVN) {
        this.nameVN = nameVN;
    }

    public int getRegister() {
        return register;
    }

    public void setRegister(int register) {
        this.register = register;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.valueOf(enterpriseIdActual);
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
        return "Module mở rộng SB11";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new SubEnterpriseBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_ENTERPRISE_ID_ACTUAL;
    }

    @Override
    public String getDataRealyOfField(String fieldName, String data) {
        String dt = data;
        if (fieldName.equals(FIELD_ENTERPRISE_ID_ACTUAL)) {
            IEnterpriseBN ebn = Lookup.getDefault().lookup(IEnterpriseBN.class);
            dt = ebn.getById(Integer.valueOf(data)).toString();
        }
        return dt;
    }

    @Override
    public String getDescriptionOfField(String fieldName) {
        if (fieldName.equals(FIELD_BOOK_REGISTER)) {
            return "Sổ đăng ký";
        }       
        if (fieldName.equals(FIELD_DATE_REGISTER)) {
            return "Ngày đăng ký";
        }
        if (fieldName.equals(FIELD_DATE_START)) {
            return "Ngày hoạt động";
        }
        if (fieldName.equals(FIELD_DESCRIPTTION)) {
            return "Miêu tả";
        }
        if (fieldName.equals(FIELD_ENTERPRISE_ID_ACTUAL)) {
            return "Tên thường gọi";
        }
        if (fieldName.equals(FIELD_ID_TAX)) {
            return "Mã số thuê";
        }
        if (fieldName.equals(FIELD_NAME_ENGLISH)) {
            return "Tên tiếng Anh";
        }
        if (fieldName.equals(FIELD_NAME_VN)) {
            return "Tên tiếng Việt";
        }
        if (fieldName.equals(FIELD_REGISTER)) {
            return "Đăng ký kinh doanh";
        }
        if (fieldName.equals(FIELD_TYPE)) {
            return "Loại hình doanh nghiệp";
        }
        return "";
    }
}
