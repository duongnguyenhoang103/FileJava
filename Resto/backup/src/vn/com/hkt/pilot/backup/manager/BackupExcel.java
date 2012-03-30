/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.backup.manager;

import java.lang.Integer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import vn.com.hkt.pilot.backup.excel.ExcelReading;
import vn.com.hkt.pilot.backup.excel.ExcelWriting;
import vn.com.hkt.pilot.backup.manager.api.IBackupModule;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author BinLe
 */
public class BackupExcel {

    private String fileName = "data.xls";
    private List<IEntity> listeEntity;
    private ExcelWriting writerExcel = null;
    private ExcelReading readerExcel = null;
    private Hashtable<String, Hashtable<Integer, Integer>> hashtableEntityId;

    public Hashtable<String, Hashtable<Integer, Integer>> getHashtableEntityId() {
        return hashtableEntityId;
    }
    private Hashtable<Integer, Integer> hast;

    public BackupExcel() {
        try {
            listeEntity = (List<IEntity>) Lookup.getDefault().lookupAll(IEntity.class);
            writerExcel = new ExcelWriting(fileName);
        } catch (Exception e) {
            e.printStackTrace();
            listeEntity = null;
        }
    }

    public BackupExcel(String fileName) {
        this.fileName = fileName;
        try {
            listeEntity = (List<IEntity>) Lookup.getDefault().lookupAll(IEntity.class);
        } catch (Exception e) {
            e.printStackTrace();
            listeEntity = null;
        }
    }

    private void writing(HSSFSheet sheet, int rowIndex, int columnIndex, Object obj) {
        String string = "";
        try {
            if (obj instanceof List) {
                for (int i = 0; i < ((List) obj).size(); i++) {
                    string += String.valueOf(((List) obj).get(i)) + "///";
                }
            } else if (obj instanceof byte[]) {
                byte[] bs = (byte[]) obj;
                string = new String(bs);
            } else if (obj instanceof Date) {
                string = new SimpleDateFormat().format(obj);
            } else {
                string = String.valueOf(obj);
            }
        } catch (Exception e) {
            string = String.valueOf(obj);
        }
        writerExcel.setData(sheet, rowIndex, columnIndex, string);
    }

    private void backupOneEntity(HSSFSheet sheet, int rowIndex, IEntity obj, List<Field> listFields) {
        for (int i = 0; i < listFields.size(); i++) {
            try {
                listFields.get(i).setAccessible(true);
                Object vaule = listFields.get(i).get(obj);
                writing(sheet, rowIndex, i, vaule);
            } catch (IllegalArgumentException ex) {
                Exceptions.printStackTrace(ex);
            } catch (IllegalAccessException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }

    private void backupEntityType(IEntity entity) {
        Field[] fieldOfEntity = entity.getClass().getDeclaredFields();
        List<Field> listFields = new ArrayList<Field>();
        for (int i = 0; i < fieldOfEntity.length; i++) {
            if (!(Modifier.isFinal(fieldOfEntity[i].getModifiers())
                    || Modifier.isStatic(fieldOfEntity[i].getModifiers()))) {
                listFields.add(fieldOfEntity[i]);
            }
        }
        HSSFSheet sheet = writerExcel.createSheet(entity.getModuleOfEntity() + "." + entity.getEntityName());
        List<String> header = new ArrayList<String>();
        for (int k = 0; k < listFields.size(); k++) {
            header.add(listFields.get(k).getName());
        }
        sheet = writerExcel.writeHeader(sheet, header);
        List<IEntity> listObjects = entity.getAccessDataOfEntity().selectAll();
        for (int k = 0; k < listObjects.size(); k++) {
            IEntity objectEntity = listObjects.get(k);
            backupOneEntity(sheet, k + 1, objectEntity, listFields);
        }
    }

    public boolean backup() {
        if (listeEntity == null) {
            return false;
        }
        try {
            writerExcel = new ExcelWriting(fileName);
            for (int i = 0; i < listeEntity.size(); i++) {
                backupEntityType(listeEntity.get(i));
            }
            writerExcel.writeExcel();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean restore() {
        if (listeEntity == null) {
            return false;
        }
        hashtableEntityId = new Hashtable<String, Hashtable<Integer, Integer>>();
        try {
            readerExcel = new ExcelReading(fileName);
            for (int i = 0; i < listeEntity.size(); i++) {
                try{
                restoreEntityType(listeEntity.get(i));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            Collection<? extends IBackupModule> backupModules = Lookup.getDefault().lookupAll(IBackupModule.class);           
            for (IBackupModule bm : backupModules) {
                try {
                    bm.setHashId(hashtableEntityId);
                    bm.backupAll();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void restoreEntityType(IEntity entity) {
        Field[] fieldOfEntity = entity.getClass().getDeclaredFields();
        hast = new Hashtable<Integer, Integer>();
        List<Field> listFields = new ArrayList<Field>();
        for (int i = 0; i < fieldOfEntity.length; i++) {
            if (!(Modifier.isFinal(fieldOfEntity[i].getModifiers())
                    || Modifier.isStatic(fieldOfEntity[i].getModifiers()))) {
                listFields.add(fieldOfEntity[i]);
            }
        }
        String sheetName = entity.getModuleOfEntity() + "." + entity.getEntityName();
        int sheetIndex = readerExcel.getSheetIndex(sheetName);
        if (sheetIndex >= 0) {
            int firstRow = readerExcel.getRowFirst(sheetIndex);
            int lastRow = readerExcel.getRowLast(sheetName);
            for (int i = firstRow + 1; i < lastRow; i++) {
                List<String> valueFields = readerExcel.getRow(sheetName, i);
                restoreOneEntity(entity, valueFields, listFields);
            }
        }
        hashtableEntityId.put(entity.getEntityName(), hast);
    }

    private void restoreOneEntity(IEntity entity, List<String> valueFields, List<Field> listFields) {
        try {
            IAccessData<IEntity> ad = entity.getAccessDataOfEntity();
            IEntity objectEnity = entity.getClass().newInstance();
            int idOld, idNew;
            for (int i = 0; i < listFields.size(); i++) {
                listFields.get(i).setAccessible(true);
                Object data = getVauleRealy(listFields.get(i), valueFields.get(i));
                listFields.get(i).set(objectEnity, data);
            }
            idOld = objectEnity.getId();
            ad.update(objectEnity);
            idNew = objectEnity.getId();
            hast.put(idOld, idNew);
        } catch (InstantiationException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IllegalAccessException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private int checkList(Class ca) {
        if (ca.equals(List.class)) {
            try {
                List<String> ls = (List<String>) ca.newInstance();
                ls = new ArrayList<String>();
                return 0;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                List<Integer> li = (List<Integer>) ca.newInstance();
                li = new ArrayList<Integer>();
                return 1;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return -1;
    }

    private Object getVauleRealy(Field field, String vaule) {
        List<Integer> li = new ArrayList<Integer>();
        List<String> ls = new ArrayList<String>();
        String[] sa = vaule.split("///");

        if (field.getType().equals(List.class)) {
            if (checkList(field.getType()) == 0) {
                ls.addAll(Arrays.asList(sa));
                return ls;
            } else if (checkList(field.getType()) == 1) {
                for (int i = 0; i < sa.length; i++) {
                    li.add(Integer.valueOf(sa[i]));
                }
                return li;
            }
        } else if (field.getType().isArray()) {
            byte[] bs = vaule.getBytes();
            return bs;
        } else if (field.getType().equals(int.class)) {
            return Integer.valueOf(vaule);
        } else if (field.getType().equals(float.class)) {
            return Float.valueOf(vaule);
        } else if (field.getType().equals(Date.class)) {
            try {
                if (vaule == null || "null".equals(vaule)) {
                    return null;
                }
                return new SimpleDateFormat().parse(vaule);
            } catch (ParseException ex) {
                ex.printStackTrace();
                return null;
            }
        }
        return vaule;
    }
}
