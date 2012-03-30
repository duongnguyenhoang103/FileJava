/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.history.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.authenticate.manager.tools.AuthenticateManager;
import vn.com.hkt.history.Installer;
import vn.com.hkt.history.spidao.ReferenceDAO;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author BinLe
 */
@ServiceProvider(service = IEntity.class)
@Entity
public class Reference implements IEntity {

    public static final String FIELD_ID = "id";
    public static final String FIELD_MODULE_OBJECT = "moduleObject";
    public static final String FIELD_OBJECT_TYPE = "objectType";
    public static final String FIELD_OBJECT_ID_ACTUAL = "objectIdActual";
    public static final String FIELD_ACCOUNT_ID_ACTUAL = "accountIdActual";
    public static final String FIELD_FIELD_NAME = "fieldName";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String moduleObject;
    private String objectType;
    private int objectIdActual;
    private int accountIdActual;
    private String fieldName;    
    private List<Integer> modificationsIdActual;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldNameIdActual(String fieldName) {
        this.fieldName = fieldName;
    }

    public int getAccountIdActual() {
        return accountIdActual;
    }

    public void setAccountIdActual(int accountIdActual) {
        this.accountIdActual = accountIdActual;
    }

    public int getObjectIdActual() {
        return objectIdActual;
    }

    public void setObjectIdActual(int objectId) {
        this.objectIdActual = objectId;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getModificationsIdActual() {
        return modificationsIdActual;
    }

    public void setModificationsIdActual(List<Integer> modifications) {
        this.modificationsIdActual = modifications;
    }

    public String getModuleObject() {
        return moduleObject;
    }

    public void setModuleObject(String moduleObject) {
        this.moduleObject = moduleObject;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    @Override
    public String getEntityName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getEntityDescription() {
        return "Mo ta tham chieu su thay doi";
    }

    @Override
    public String getModuleOfEntity() {
        return Installer.MODULE_NAME;
    }

    public Reference(String moduleObject, String objectType, int objectId, int accountIdActual, String fieldName, List<Integer> modifications) {
        this.moduleObject = moduleObject;
        this.objectType = objectType;
        this.objectIdActual = objectId;
        this.accountIdActual = accountIdActual;
        this.fieldName = fieldName;
        this.modificationsIdActual = modifications;
    }

    public Reference() {
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new ReferenceDAO();
    }

    @Override
    public String getFieldNameObjectId() {
        return "id";
    }

    @Override
    public String getDataRealyOfField(String fieldName, String data) {
        String dt = data;
        try {
            if (fieldName.equals(FIELD_ACCOUNT_ID_ACTUAL)) {
                dt = AuthenticateManager.getAuthenticateManager().getUsernameByAccountId(Integer.valueOf(data));
            }
            if (fieldName.equals(FIELD_OBJECT_ID_ACTUAL)) {
                List<IEntity> entitys = (List<IEntity>) Lookup.getDefault().lookupAll(IEntity.class);
                for (int i = 0; i < entitys.size(); i++) {
                    if (entitys.get(i).getEntityName().equals(objectType)) {
                        dt = entitys.get(i).getAccessDataOfEntity().getById(Integer.valueOf(data)).toString();
                        break;
                    }
                }
            }
            if (fieldName.equals(FIELD_FIELD_NAME)) {
                List<IEntity> entitys = (List<IEntity>) Lookup.getDefault().lookupAll(IEntity.class);
                for (int i = 0; i < entitys.size(); i++) {
                    if (entitys.get(i).getEntityName().equals(objectType)) {
                        dt = entitys.get(i).getDescriptionOfField(data);
                        break;
                    }
                }
            }
        } catch (Exception exception) {
        }
        return dt;
    }

    @Override
    public String getDescriptionOfField(String fieldName) {
        if (fieldName.equals(FIELD_ACCOUNT_ID_ACTUAL)) {
            return "Account thực hiện";
        } else if (fieldName.equals(FIELD_FIELD_NAME)) {
            return "Tên thuộc tính thay đổi";
        } else if (fieldName.equals(FIELD_MODULE_OBJECT)) {
            return "Module của đối tượng";
        } else if (fieldName.equals(FIELD_OBJECT_ID_ACTUAL)) {
            return "Đối tượng bị thay đổi";
        } else if (fieldName.equals(FIELD_OBJECT_TYPE)) {
            return "Loại đối tượng";
        }
        return null;
    }
}
