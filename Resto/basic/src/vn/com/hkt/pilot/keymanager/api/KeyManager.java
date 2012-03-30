/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.keymanager.api;

import java.util.ArrayList;
import java.util.List;
import vn.com.hkt.basic.jpa.PersistenceUltility;
import vn.com.hkt.pilot.entities.KeyGenerate;
import vn.com.hkt.pilot.identity.access.spi.AccessData;

/**
 *
 * @author HKT01
 */
public class KeyManager extends AccessData<KeyGenerate> {

    public KeyManager() {
        setAccessData(PersistenceUltility.getEMF(), KeyGenerate.class);
    }

    public List<KeyGenerate> findKey(String prefix, long first, long follow) {
        List<String> lf = new ArrayList<String>();
        lf.add(KeyGenerate.FIELD_PREFIX);
        lf.add(KeyGenerate.FIELD_FIRST_NUM);
        lf.add(KeyGenerate.FIELD_FOLLOW_NUM);
        List<String> lv = new ArrayList<String>();
        lv.add(prefix);
        lv.add(String.valueOf(first));
        lv.add(String.valueOf(follow));
        return select(lf, lv);
    }

    public List<KeyGenerate> findKey(String prefix, long first) {
        List<String> lf = new ArrayList<String>();
        lf.add(KeyGenerate.FIELD_PREFIX);
        lf.add(KeyGenerate.FIELD_FIRST_NUM);
        List<String> lv = new ArrayList<String>();
        lv.add(prefix);
        lv.add(String.valueOf(first));
        return select(lf, lv);
    }
    
    public List<KeyGenerate> findKey(int prefixIdActual, long first) {
        List<String> lf = new ArrayList<String>();
        lf.add(KeyGenerate.FIELD_PREFIXID_ACTUAL);
        lf.add(KeyGenerate.FIELD_FIRST_NUM);
        List<String> lv = new ArrayList<String>();
        lv.add(String.valueOf(prefixIdActual));
        lv.add(String.valueOf(first));
        return select(lf, lv);
    }
}
