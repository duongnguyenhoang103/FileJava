/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.history.spidao;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.history.apidao.IExportImportHistory;
import vn.com.hkt.history.apidao.IModificationDAO;
import vn.com.hkt.history.apidao.IReferenceDAO;
import vn.com.hkt.history.entities.Modification;
import vn.com.hkt.history.entities.Reference;
import vn.com.hkt.pilot.backup.excel.ExcelReading;
import vn.com.hkt.pilot.backup.tools.ExcelImport;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author Admin
 */
@ServiceProvider(service = IExportImportHistory.class)
public class ExportImportHistory implements IExportImportHistory {

    File fileExcel;
    private HSSFWorkbook workbook;
    private IReferenceDAO referenceAPI;
    private IModificationDAO modifcationAPI;
    private ExcelReading reading;
    private ExcelImport importData;

    public ExportImportHistory() {
        this.referenceAPI = Lookup.getDefault().lookup(IReferenceDAO.class);
        this.modifcationAPI = Lookup.getDefault().lookup(IModificationDAO.class);
        workbook = new HSSFWorkbook();
        importData = new ExcelImport();
    }

    @Override
    public void createFileExcel(String filePath) {
        fileExcel = new File(filePath);
        try {
            fileExcel.createNewFile();
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    @Override
    public void exportModuleHistoryToExcel(String filePath, String moduleName, Date fromDate, Date toDate) {
        List<IEntity> listEntity = (List<IEntity>) Lookup.getDefault().lookupAll(IEntity.class);
        List<IEntity> listObject = new ArrayList<IEntity>();
        for (int i = 0; i < listEntity.size(); i++) {
            if (listEntity.get(i).getModuleOfEntity().equals(moduleName)) {
                IEntity entity = listEntity.get(i);
                listObject.add(entity);
            }
        }
        for (int j = 0; j < listObject.size(); j++) {
            exportHistoryToExcel(filePath, moduleName, listObject.get(j).getClass(), fromDate, toDate);
        }
    }

    private void getDataByHistory(String filePath, String moduleName, Class typeObject, List<Integer> objectID) {
        fileExcel = new File(filePath);
        Field[] fields = typeObject.getDeclaredFields();
        boolean[] isFields = new boolean[fields.length];
        HSSFSheet sheet = workbook.createSheet(typeObject.getSimpleName());
        HSSFRow header = sheet.createRow(0);
        int count = 0;
        for (int i = 0; i < fields.length; i++) {
            if (!Modifier.isStatic(fields[i].getModifiers())
                    && !Modifier.isFinal(fields[i].getModifiers())
                    && !fields[i].getName().equals("id")) {
                isFields[i] = true;
                header.createCell(count).setCellValue(fields[i].getName());
                count++;
            } else {
                isFields[i] = false;
            }
        }

        List<IEntity> listEntities = (List<IEntity>) Lookup.getDefault().lookupAll(IEntity.class);
        for (int i = 0; i < listEntities.size(); i++) {
            if (listEntities.get(i).getModuleOfEntity().equals(moduleName)
                    && listEntities.get(i).getEntityName().equals(typeObject.getSimpleName())) {
                IEntity entity = listEntities.get(i);
                IAccessData ad = entity.getAccessDataOfEntity();
                for (int j = 0; j < objectID.size(); j++) {
                    HSSFRow row = sheet.createRow(j + 1);
                    IEntity objectCurrent = (IEntity) ad.getById(objectID.get(j));
                    count = 0;
                    for (int k = 0; k < fields.length; k++) {
                        fields[k].setAccessible(true);
                        try {
                            if (isFields[k]) {
                                row.createCell(count).setCellValue(fields[k].get(objectCurrent).toString());
                                count++;
                            }
                        } catch (Exception ex) {
                            Exceptions.printStackTrace(ex);
                        }
                    }
                }
            }
        }

    }

    @Override
    public void exportHistoryToExcel(String filePath, String moduleName, Class typeObject, Date fromDate, Date toDate) {
        try {
            fileExcel = new File(filePath);

            HSSFSheet sheet = workbook.createSheet(typeObject.getSimpleName() + "History");

            HSSFRow rowhead = sheet.createRow(0);
            rowhead.createCell(0).setCellValue("ModuleObject");
            rowhead.createCell(1).setCellValue("ObjectType");
            rowhead.createCell(2).setCellValue("ObjectID");
            rowhead.createCell(3).setCellValue("Account");
            rowhead.createCell(4).setCellValue("FieldName");
            rowhead.createCell(5).setCellValue("Data");
            rowhead.createCell(6).setCellValue("DateStart");
            rowhead.createCell(7).setCellValue("DateEnd");
            rowhead.createCell(8).setCellValue("Description");

            List<Reference> list = new ArrayList<Reference>();
            list = referenceAPI.getReferenceByTypeObject(typeObject.getSimpleName());
            List<Modification> listModifications = new ArrayList<Modification>();
            List<Integer> listObjectID = new ArrayList<Integer>();
            int lastObjectID = 0;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            int t = 0;
            for (int i = 0; i < list.size(); i++) {
                listModifications = new ArrayList<Modification>();
                for (int j=0;j<list.get(i).getModificationsIdActual().size();j++)       
                    listModifications.add(new ModificationDAO().getById(list.get(i).getModificationsIdActual().get(i)));

                for (int j = 0; j < listModifications.size(); j++) {
                    if (fromDate.compareTo(listModifications.get(j).getDateEnd()) <= 0 && toDate.compareTo(listModifications.get(j).getDateEnd()) >= 0) {
                        if (list.get(i).getObjectIdActual() != lastObjectID) {
                            listObjectID.add(list.get(i).getObjectIdActual());
                            lastObjectID = list.get(i).getObjectIdActual();
                        }
                        HSSFRow row = sheet.createRow(t + 1);
                        row.createCell(0).setCellValue(list.get(i).getModuleObject());
                        row.createCell(1).setCellValue(list.get(i).getObjectType());
                        row.createCell(2).setCellValue(list.get(i).getObjectIdActual());
                        row.createCell(3).setCellValue(list.get(i).getAccountIdActual());
                        row.createCell(4).setCellValue(list.get(i).getFieldName());
                        row.createCell(5).setCellValue(listModifications.get(j).getData());
                        row.createCell(6).setCellValue(sdf.format(listModifications.get(j).getDateStart()));
                        row.createCell(7).setCellValue(sdf.format(listModifications.get(j).getDateEnd()));
                        row.createCell(8).setCellValue(listModifications.get(j).getDescription());
                    }
                    t++;
                }
            }
            getDataByHistory(filePath, moduleName, typeObject, listObjectID);
            FileOutputStream fileOutputStream = new FileOutputStream(fileExcel);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void importHistory(String filePath) {
        reading = new ExcelReading(filePath);
        int numberOfSheets = reading.getSheetCount();
        try {
            for (int i = 0; i < numberOfSheets; i++) {
                String sheetName = reading.getSheetName(i);
                if (sheetName.contains("History")) {
                    List<String> listModuleObject = reading.getColumn(sheetName, 0);
                    List<String> listObjectType = reading.getColumn(sheetName, 1);
                    List<String> listObjectID = reading.getColumn(sheetName, 2);
                    List<String> listAccountID = reading.getColumn(sheetName, 3);
                    List<String> listFieldName = reading.getColumn(sheetName, 4);
                    List<String> listData = reading.getColumn(sheetName, 5);
                    List<String> listDateStart = reading.getColumn(sheetName, 6);
                    List<String> listDateEnd = reading.getColumn(sheetName, 7);
                    List<String> listDescription = reading.getColumn(sheetName, 8);

                    List<Integer> listModifications = new ArrayList<Integer>();

                    String lastModuleObject = null;
                    String lastObjectType = null;
                    int lastObjectID = 0;
                    int lastAccountID = 0;

                    for (int j = 1; j < reading.getRowLast(sheetName) + 1; j++) {
                        if (j == 1) {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                            Modification m = new Modification(listData.get(j), sdf.parse(listDateStart.get(j)), sdf.parse(listDateEnd.get(j)), listDescription.get(j));
                            modifcationAPI.insert(m);
                            listModifications.add(m.getId());

                            lastModuleObject = listModuleObject.get(1);
                            lastObjectType = listObjectType.get(1);
                            lastObjectID = Integer.parseInt(listObjectID.get(1));
                            lastAccountID = Integer.parseInt(listAccountID.get(1));
                        } else if (j == reading.getRowLast(sheetName)) {
                            if (listObjectType.get(j).equals(lastObjectType)
                                    && listModuleObject.get(j).equals(lastModuleObject)
                                    && Integer.parseInt(listObjectID.get(j)) == lastObjectID
                                    && Integer.parseInt(listAccountID.get(j)) == lastAccountID) {

                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                                Modification m = new Modification(listData.get(j), sdf.parse(listDateStart.get(j)), sdf.parse(listDateEnd.get(j)), listDescription.get(j));
                                modifcationAPI.insert(m);
                                listModifications.add(m.getId());                                
                                    
                                Reference r = new Reference(lastModuleObject, lastObjectType, lastObjectID, lastAccountID, listFieldName.get(j), listModifications);
                                referenceAPI.insert(r);
                            } else {
                                Reference r = new Reference(lastModuleObject, lastObjectType, lastObjectID, lastAccountID, listFieldName.get(j), listModifications);
                                referenceAPI.insert(r);

                                listModifications = new ArrayList<Integer>();

                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                                Modification m = new Modification(listData.get(j), sdf.parse(listDateStart.get(j)), sdf.parse(listDateEnd.get(j)), listDescription.get(j));
                                modifcationAPI.insert(m);
                                listModifications.add(m.getId());

                                r = new Reference(listModuleObject.get(j), listObjectType.get(j), Integer.parseInt(listObjectID.get(j)), Integer.parseInt(listAccountID.get(j)), listFieldName.get(j), listModifications);
                                referenceAPI.insert(r);
                            }
                        } else {
                            if (listObjectType.get(j).equals(lastObjectType)
                                    && listModuleObject.get(j).equals(lastModuleObject)
                                    && Integer.parseInt(listObjectID.get(j)) == lastObjectID
                                    && Integer.parseInt(listAccountID.get(i)) == lastAccountID) {

                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                                Modification m = new Modification(listData.get(j), sdf.parse(listDateStart.get(j)), sdf.parse(listDateEnd.get(j)), listDescription.get(j));
                                modifcationAPI.insert(m);
                                listModifications.add(m.getId());

                                lastModuleObject = listModuleObject.get(j);
                                lastObjectType = listObjectType.get(j);
                                lastObjectID = Integer.parseInt(listObjectID.get(j));
                                lastAccountID = Integer.parseInt(listAccountID.get(j));

                            } else {
                                Reference r = new Reference(lastModuleObject, lastObjectType, lastObjectID, lastAccountID, listFieldName.get(j), listModifications);
                                referenceAPI.insert(r);

                                listModifications = new ArrayList<Integer>();

                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                                Modification m = new Modification(listData.get(j), sdf.parse(listDateStart.get(j)), sdf.parse(listDateEnd.get(j)), listDescription.get(j));
                                modifcationAPI.insert(m);
                                listModifications.add(m.getId());

                                lastModuleObject = listModuleObject.get(j);
                                lastObjectType = listObjectType.get(j);
                                lastObjectID = Integer.parseInt(listObjectID.get(j));
                                lastAccountID = Integer.parseInt(listAccountID.get(j));
                            }
                        }
                    }
                } else {
                    List<IEntity> listEntity = (List<IEntity>) Lookup.getDefault().lookupAll(IEntity.class);
                    for (int k = 0; k < listEntity.size(); k++) {
                        if (listEntity.get(k).getEntityName().equals(sheetName)) {
                            IEntity entity = listEntity.get(k);
                            importData.ImportToDatabase(filePath, entity.getClass());
                            break;
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
