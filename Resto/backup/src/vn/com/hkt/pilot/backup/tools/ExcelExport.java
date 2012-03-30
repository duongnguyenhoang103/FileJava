/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.backup.tools;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import vn.com.hkt.pilot.backup.excel.ExcelWriting;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author BinLe
 */
public class ExcelExport {

    private void writeNewSheet(String fileName, Class entityClass, List<IEntity> objects) {
        ExcelWriting excelWriting = new ExcelWriting(fileName);
        HSSFSheet sheet = excelWriting.createSheet(entityClass.getSimpleName());
        if (sheet == null) {
            return;
        }
        Field[] fields = entityClass.getDeclaredFields();
        boolean[] isFields = new boolean[fields.length];
        List<String> header = new ArrayList<String>();
        for (int i = 0; i < fields.length; i++) {
            if (!Modifier.isStatic(fields[i].getModifiers())
                    && !Modifier.isFinal(fields[i].getModifiers())
                    && !fields[i].getName().equals("id")) {
                header.add(fields[i].getName());
                isFields[i] = true;
            } else {
                isFields[i] = false;
            }
        }
        sheet = excelWriting.writeHeader(sheet, header);
        List<List<String>> listData = new ArrayList<List<String>>();
        for (int i = 0; i < objects.size(); i++) {
            List<String> list = new ArrayList<String>();
            for (int j = 0; j < fields.length; j++) {
                if (isFields[j]) {
                    fields[j].setAccessible(true);
                    try {
                        if (fields[j].getType().equals(List.class)) {
                        } else {
                            list.add(String.valueOf(fields[j].get(objects.get(i))));
                        }

                    } catch (Exception ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
            }
            listData.add(list);
        }
        for (int i = 0; i < listData.size(); i++) {
            sheet = excelWriting.writeDataRow(sheet, listData.get(i), i + 1);
        }
        excelWriting.writeExcel();
    }

    public void exportAll() {
        List<IEntity> listEntity = (List<IEntity>) Lookup.getDefault().lookupAll(IEntity.class);
        for (int i = 0; i < listEntity.size(); i++) {
            IEntity entity = listEntity.get(i);
            IAccessData access = entity.getAccessDataOfEntity();
            List<IEntity> listObject = access.selectAll();
            writeNewSheet(entity.getModuleOfEntity(), entity.getClass(), listObject);
        }
    }

    public void export(String moduleName) {
        List<IEntity> listEntity = (List<IEntity>) Lookup.getDefault().lookupAll(IEntity.class);
        for (int i = 0; i < listEntity.size(); i++) {
            if (listEntity.get(i).getModuleOfEntity().equals(moduleName)) {
                IEntity entity = listEntity.get(i);
                IAccessData access = entity.getAccessDataOfEntity();
                List<IEntity> listObject = access.selectAll();
                writeNewSheet(entity.getModuleOfEntity(), entity.getClass(), listObject);
            }
        }
    }

    public void export(String moduleName, String entityName) {
        List<IEntity> listEntity = (List<IEntity>) Lookup.getDefault().lookupAll(IEntity.class);
        for (int i = 0; i < listEntity.size(); i++) {
            if (listEntity.get(i).getModuleOfEntity().equals(moduleName)
                    && listEntity.get(i).getEntityName().equals(entityName)) {
                IEntity entity = listEntity.get(i);
                IAccessData access = entity.getAccessDataOfEntity();
                List<IEntity> listObject = access.selectAll();
                writeNewSheet(entity.getModuleOfEntity(), entity.getClass(), listObject);
            }
        }
    }
}
