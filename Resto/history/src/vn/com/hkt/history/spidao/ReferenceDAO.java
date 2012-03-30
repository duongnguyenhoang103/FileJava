/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.history.spidao;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.history.apidao.IReferenceDAO;
import vn.com.hkt.history.entities.Reference;
import vn.com.hkt.pilot.history.jpa.JPAUtility;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author BinLe
 */
@ServiceProvider(service = IReferenceDAO.class)
public class ReferenceDAO extends AccessData<Reference> implements IReferenceDAO {

    public ReferenceDAO() {
        setAccessData(JPAUtility.getEMF(), Reference.class);
    }

    @Override
    public List<Reference> getReferenceByTypeObject(String typeObject) {
        return select(Reference.FIELD_OBJECT_TYPE, typeObject);
    }

    @Override
    public Reference getReference(String moduleName, String objectType, int objectId, int accountId, String fieldName) {
        List<String> lf = new ArrayList<String>();
        lf.add(Reference.FIELD_MODULE_OBJECT);
        lf.add(Reference.FIELD_OBJECT_TYPE);
        lf.add(Reference.FIELD_OBJECT_ID_ACTUAL);
        lf.add(Reference.FIELD_ACCOUNT_ID_ACTUAL);
        lf.add(Reference.FIELD_FIELD_NAME);
        List<String> lv = new ArrayList<String>();
        lv.add(moduleName);
        lv.add(objectType);
        lv.add(String.valueOf(objectId));
        lv.add(String.valueOf(accountId));
        lv.add(fieldName);
        List<Reference> ls = select(lf, lv);
        if (ls.size() > 0) {
            return ls.get(0);
        }
        return null;
    }
}
