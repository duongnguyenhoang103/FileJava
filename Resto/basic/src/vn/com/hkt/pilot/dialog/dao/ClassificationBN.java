/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.dialog.dao;

import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.jpa.PersistenceUltility;
import vn.com.hkt.basic.api.IClassificationBN;
import vn.com.hkt.pilot.entities.Classification;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author duong
 */
@ServiceProvider(service = IClassificationBN.class)
public class ClassificationBN extends AccessData<Classification> implements IClassificationBN {

    public ClassificationBN() {
        setAccessData(PersistenceUltility.getEMF(), Classification.class);
    }

    @Override
    public List<Classification> filterByClassificationName(String name) {
        return filter(Classification.FIELD_CLASSIFICATION_NAME, name);
    }
}