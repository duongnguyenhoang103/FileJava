/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.keymanager.api;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import vn.com.hkt.pilot.entities.KeyGenerate;
import vn.com.hkt.pilot.entities.PrefixManager;

/**
 *
 * @author HKT01
 */
public class CreateKey {

    private KeyManager keyManager = new KeyManager();

    public String createKey(String prefix) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int value = year * 100 + month;
        int follow = 0;
        KeyGenerate keyGenerate = new KeyGenerate();
        List<KeyGenerate> check = keyManager.findKey(prefix, value);
        List<Integer> keySets = new ArrayList<Integer>();
        if (!check.isEmpty()) {
            for (KeyGenerate bean : check) {
                keySets.add(bean.getFollowNum());
            }
        }
        follow = getMaxValue(keySets) + 1;
        keyGenerate.setPrefix(prefix);
        keyGenerate.setFirstNum(value);
        keyGenerate.setFollowNum(follow);
        if (keyManager.insert(keyGenerate)) {
            System.out.println("Create key successed!");
        } else {
            System.out.println("Create key faied!");
        }

        return keyGenerate.toString();
    }

    public KeyGenerate createAKey(String prefix) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int value = year * 100 + month;
        int follow = 0;
        KeyGenerate keyGenerate = new KeyGenerate();
        List<KeyGenerate> check = keyManager.findKey(prefix, value);
        List<Integer> keySets = new ArrayList<Integer>();
        if (!check.isEmpty()) {
            for (KeyGenerate bean : check) {
                keySets.add(bean.getFollowNum());
            }
        }
        follow = getMaxValue(keySets) + 1;
        keyGenerate.setPrefix(prefix);
        keyGenerate.setFirstNum(value);
        keyGenerate.setFollowNum(follow);
        if (keyManager.insert(keyGenerate)) {
            System.out.println("Create key successed!");
        } else {
            System.out.println("Create key faied!");
        }
        return keyGenerate;
    }

    /**
     * Hàm tạo khóa kem theo kiểm tra khóa cha là gì
     * @param className: tên đối tượng định sinh khóa kèm theo VD: sinh khóa cho Enterprise
     * @param prefix: Tiếp đầu ngữ định gán VD: gán PE cho Enterprise
     * @return Đối tượng KeyGenerate
     */
    public KeyGenerate createAKeyByParent(Class className, String prefix) {
        /**
         * Khai báo tiếp đầu ngữ
         */
        List<PrefixManager> prefixManagers = new ArrayList<PrefixManager>();
        PrefixManagerBN prefixManagerBN = new PrefixManagerBN();

        prefixManagers = prefixManagerBN.select(PrefixManager.FIELD_ENTITY_TYPE_NAME, className.getSimpleName());

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int value = year * 100 + month;
        int follow = 0;
        KeyGenerate keyGenerate = new KeyGenerate();

        if (!prefixManagers.isEmpty()) {
            int idActual = prefixManagers.get(0).getId();
            List<KeyGenerate> check = keyManager.findKey(idActual, value);
            List<Integer> keySets = new ArrayList<Integer>();
            if (!check.isEmpty()) {
                for (KeyGenerate bean : check) {
                    keySets.add(bean.getFollowNum());
                }
            }
            follow = getMaxValue(keySets) + 1;
            keyGenerate.setPrefix(prefix);
            keyGenerate.setFirstNum(value);
            keyGenerate.setFollowNum(follow);
            keyGenerate.setPrefixIdActual(idActual);
            if (keyManager.insert(keyGenerate)) {
                System.out.println("Create key successed!");
            } else {
                System.out.println("Create key faied!");
            }
        } else {
            List<Integer> keySets = new ArrayList<Integer>();
            PrefixManager prefixManager = new PrefixManager();
            prefixManager.setEntityTypeName(className.getSimpleName());
            prefixManager.setLastPrefix(prefix);
            prefixManager.getAccessDataOfEntity().insert(prefixManager);
            follow = getMaxValue(keySets) + 1;
            keyGenerate.setPrefix(prefix);
            keyGenerate.setFirstNum(value);
            keyGenerate.setFollowNum(follow);
            keyGenerate.setPrefixIdActual(prefixManager.getId());
            if (keyManager.insert(keyGenerate)) {
                System.out.println("Create key successed!");
            } else {
                System.out.println("Create key faied!");
            }
        }
        return keyGenerate;
    }

    protected int getMaxValue(List<Integer> list) {
        int Max = 0;
        if (!list.isEmpty()) {
            for (int mid : list) {
                if (Max <= mid) {
                    Max = mid;
                }
            }
        }
        return Max;
    }
}
