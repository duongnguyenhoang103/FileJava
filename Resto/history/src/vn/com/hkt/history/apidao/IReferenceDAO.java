/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.history.apidao;

import java.util.List;
import vn.com.hkt.history.entities.Reference;
import vn.com.hkt.pilot.identity.access.api.IAccessData;

/**
 *
 * @author BinLe
 */
public interface IReferenceDAO extends IAccessData<Reference> {

    public List<Reference> getReferenceByTypeObject(String typeObject);

    public Reference getReference(String moduleName, String objectType, int objectId, int accountId, String fieldName);
}