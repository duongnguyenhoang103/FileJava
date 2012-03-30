/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.backup.excel;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openide.util.Exceptions;

/**
 *
 * @author BinLe
 */
public class ExcelReading {

    private String fileExcelName;
    private HSSFWorkbook workbook;

    public ExcelReading(String fileExcelName) {
        this.fileExcelName = fileExcelName;
        initProcess();
    }

    private void initProcess() {
        workbook = null;
        InputStream ip = null;
        try {
            ip = new FileInputStream(fileExcelName);
            workbook = new HSSFWorkbook(ip);
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        } finally {
            try {
                ip.close();
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }

    public int getSheetCount() {
        if (workbook == null) {
            return 0;
        }
        return workbook.getNumberOfSheets();
    }

    public int getSheetIndex(String sheetName) {
        if (workbook == null) {
            return -1;
        }
        return workbook.getSheetIndex(sheetName);
    }

    public String getSheetName(int sheetIndex) {
        if (workbook == null) {
            return null;
        }
        if (sheetIndex >= workbook.getNumberOfSheets()) {
            return null;
        }
        return workbook.getSheetName(sheetIndex);
    }

    public int getRowFirst(int sheetIndex) {
        if (workbook == null) {
            return -1;
        }
        if (workbook.getNumberOfSheets() <= sheetIndex) {
            return -1;
        }
        return workbook.getSheetAt(sheetIndex).getFirstRowNum();
    }

    public int getRowLast(String sheetName) {
        int sheetIndex = getSheetIndex(sheetName);
        if (workbook == null) {
            return -1;
        }
        if (workbook.getNumberOfSheets() <= sheetIndex) {
            return -1;
        }
        return workbook.getSheetAt(sheetIndex).getLastRowNum();
    }

    public int getColumnFirst(int sheetIndex, int rowIndex) {
        if (workbook == null) {
            return -1;
        }
        if (workbook.getNumberOfSheets() <= sheetIndex) {
            return -1;
        }
        int first = workbook.getSheetAt(sheetIndex).getFirstRowNum();
        int last = workbook.getSheetAt(sheetIndex).getLastRowNum();
        if (first > rowIndex || last < rowIndex) {
            return -1;
        }
        return workbook.getSheetAt(sheetIndex).getRow(rowIndex).getFirstCellNum();
    }

    public int getColumnLast(String sheetName, int rowIndex) {
        int sheetIndex = getSheetIndex(sheetName);
        if (workbook == null) {
            return -1;
        }
        if (workbook.getNumberOfSheets() <= sheetIndex) {
            return -1;
        }
        int first = workbook.getSheetAt(sheetIndex).getFirstRowNum();
        int last = workbook.getSheetAt(sheetIndex).getLastRowNum();
        if (first > rowIndex || last < rowIndex) {
            return -1;
        }
        return workbook.getSheetAt(sheetIndex).getRow(rowIndex).getLastCellNum();
    }

    public String getCell(String sheetName, int rowIndex, int columnIndex) {
        int sheetIndex = getSheetIndex(sheetName);
        if (workbook == null) {
            return null;
        }
        if (workbook.getNumberOfSheets() <= sheetIndex) {
            return null;
        }
        int firstRow = workbook.getSheetAt(sheetIndex).getFirstRowNum();
        int lastRow = workbook.getSheetAt(sheetIndex).getLastRowNum();
        if (firstRow > rowIndex || lastRow < rowIndex) {
            return null;
        }
        int firstColumn = workbook.getSheetAt(sheetIndex).getRow(rowIndex).getFirstCellNum();
        int lastColumn = workbook.getSheetAt(sheetIndex).getRow(rowIndex).getLastCellNum();
        if (firstColumn > columnIndex || lastColumn < columnIndex) {
            return null;
        }
        try {
            return workbook.getSheetAt(sheetIndex).getRow(rowIndex).getCell(columnIndex).getStringCellValue();
        } catch (Exception e) {
            return null;
        }
    }

    public List<String> getHeader(String sheetName) {
        int sheetIndex = getSheetIndex(sheetName);
        if (workbook == null) {
            return null;
        }
        if (workbook.getNumberOfSheets() <= sheetIndex) {
            return null;
        }
        int firstRow = getRowFirst(sheetIndex);
        if (firstRow == -1) {
            return null;
        }
        int firstColumn = getColumnFirst(sheetIndex, firstRow);
        int lastColumn = getColumnLast(sheetName, firstRow);
        List<String> list = new ArrayList<String>();
        for (int i = firstColumn; i <= lastColumn; i++) {
            list.add(getCell(sheetName, firstRow, i));
        }
        return list;
    }

    public List<String> getRow(String sheetName, int rowIndex) {
        int sheetIndex = getSheetIndex(sheetName);
        if (workbook == null) {
            return null;
        }
        if (workbook.getNumberOfSheets() <= sheetIndex) {
            return null;
        }
        int first = workbook.getSheetAt(sheetIndex).getFirstRowNum();
        int last = workbook.getSheetAt(sheetIndex).getLastRowNum();
        if (first > rowIndex || last < rowIndex) {
            return null;
        }
        int firstColumn = getColumnFirst(sheetIndex, rowIndex);
        int lastColumn = getColumnLast(sheetName, rowIndex);
        List<String> list = new ArrayList<String>();
        for (int i = firstColumn; i <= lastColumn; i++) {
            list.add(getCell(sheetName, rowIndex, i));
        }
        return list;
    }

    public List<String> getColumn(String sheetName, int columnIndex) {
        int sheetIndex = getSheetIndex(sheetName);
        if (workbook == null) {
            return null;
        }
        if (workbook.getNumberOfSheets() <= sheetIndex) {
            return null;
        }
        int firstRow = workbook.getSheetAt(sheetIndex).getFirstRowNum();
        int lastRow = workbook.getSheetAt(sheetIndex).getLastRowNum();
        List<String> list = new ArrayList<String>();
        for (int i = firstRow; i <= lastRow; i++) {
            list.add(getCell(sheetName, i, columnIndex));
        }
        return list;
    }
}
