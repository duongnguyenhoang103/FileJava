/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.backup.tools;

import java.lang.reflect.Field;
import java.util.List;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import vn.com.hkt.pilot.backup.excel.ExcelReading;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author BinLe
 */
public class ExcelImport {

    private ExcelReading reading;

    public void ImportToDatabase(String fileName, Class entityClass) {
        reading = new ExcelReading(fileName);
        Field[] fields = entityClass.getDeclaredFields();
        int listSize = reading.getRowLast(entityClass.getSimpleName()) + 1;
        int listWidth = reading.getColumnLast(entityClass.getSimpleName(), 0);
        String[] header = new String[listWidth];
        for (int i = 0; i < listWidth; i++) {
            header[i] = reading.getCell(entityClass.getSimpleName(), 0, i);
        }
        try {
            for (int i = 1; i < listSize; i++) {
                IEntity entity = null;
                List<IEntity> list = (List<IEntity>) Lookup.getDefault().lookupAll(IEntity.class);
                for (int k = 0; k < list.size(); k++) {
                    if (list.get(k).getClass().equals(entityClass)) {
                        entity = list.get(k);
                        break;
                    }
                }
                for (int k = 0; k < header.length; k++) {
                    for (int j = 0; j < fields.length; j++) {
                        fields[j].setAccessible(true);
                        if (header[k].toLowerCase().compareTo(fields[j].getName().toLowerCase()) == 0) {
                            if (fields[j].getType().isArray()) {
                                String str = reading.getCell(entityClass.getSimpleName(), i, k);
                                if (str != null) {
                                    fields[j].set(entity, str.getBytes());
                                }
                            }
                            if (fields[j].getType().equals(String.class)) {                                
                                String data = String.valueOf(reading.getCell(entityClass.getSimpleName(), i, k));
                                fields[j].set(entity, data);
                            }
                            if (fields[j].getType().equals(int.class)) {
                                String data = reading.getCell(entityClass.getSimpleName(), i, k);
                                if (data != null) {
                                    int value = Integer.parseInt(data);
                                    fields[j].setInt(entity, value);
                                }                                
                            }
                            break;
                        }
                    }
                }
                IAccessData ad = entity.getAccessDataOfEntity();
                ad.insert(entity);
            }
        } catch (Exception e) {
            Exceptions.printStackTrace(e);
        }
    }
}
