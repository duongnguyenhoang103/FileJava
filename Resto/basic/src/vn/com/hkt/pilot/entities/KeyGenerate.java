/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.Installer;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.keymanager.api.KeyManager;

/**
 *
 * @author HKT01
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class KeyGenerate implements IEntity {

    public static final String FIELD_ID = "id";
    public static final String FIELD_PREFIX = "prefix";
    public static final String FIELD_PREFIXID_ACTUAL = "prefixIdActual";
    public static final String FIELD_FIRST_NUM = "firstNum";
    public static final String FIELD_FOLLOW_NUM = "followNum";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String prefix; // Phan tien to
    private int prefixIdActual; // Ma cua prefix cha thuc su
    private int firstNum; // Phan So truoc-kieu nam+thang
    private int followNum; // Phan so sau

    public KeyGenerate() {
    }

    public KeyGenerate(String prefix, int prefixIdActual, int firstNum, int followNum) {
        this.prefix = prefix;
        this.prefixIdActual = prefixIdActual;
        this.firstNum = firstNum;
        this.followNum = followNum;
    }

    public int getPrefixIdActual() {
        return prefixIdActual;
    }

    public void setPrefixIdActual(int prefixIdActual) {
        this.prefixIdActual = prefixIdActual;
    }

    public long getFirstNum() {
        return firstNum;
    }

    public void setFirstNum(int firstNum) {
        this.firstNum = firstNum;
    }

    public int getFollowNum() {
        return followNum;
    }

    public void setFollowNum(int followNum) {
        this.followNum = followNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public long addKey() {
        this.followNum += 1;
        return followNum;
    }

    @Override
    public String toString() {
        return String.valueOf(prefix) + String.valueOf(firstNum)
                + String.valueOf(followNum);
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
        return "Mô tả thông tin mã đối tượng";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new KeyManager();
    }

    @Override
    public String getFieldNameObjectId() {        
        return "";
    }

    @Override
    public String getDataRealyOfField(String fieldName, String data) {
        return data;
    }

    @Override
    public String getDescriptionOfField(String fieldName) {
        if (fieldName.equals(FIELD_FIRST_NUM)) {
            return "Phần số trước";
        } else if (fieldName.equals(FIELD_FOLLOW_NUM)) {
            return "Phần số sau";
        } else if (fieldName.equals(FIELD_PREFIX)) {
            return "Tiền tố";
        }
        return null;
    }
}
