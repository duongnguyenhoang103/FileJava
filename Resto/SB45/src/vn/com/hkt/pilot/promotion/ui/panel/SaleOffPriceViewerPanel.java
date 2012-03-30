/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SaleOffPriceViewerPanel.java
 *
 * Created on Feb 28, 2012, 8:28:21 AM
 */
package vn.com.hkt.pilot.promotion.ui.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.openide.util.LookupEvent;
import vn.com.hkt.pilot.promotion.ui.cell.SaleOffPriceViewerCell;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.openide.util.Lookup;
import org.openide.util.LookupListener;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.api.ISystemSotfwareBN;
import vn.com.hkt.pilot.calendaroption.api.ICalendarOptionCreator;
import vn.com.hkt.pilot.calendaroption.entity.SaleOffOptionLookup;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.identity.presentation.api.IUserInterface;
import vn.com.hkt.pilot.keymanager.api.CreateKey;
import vn.com.hkt.pilot.promotion.Installer;
import vn.com.hkt.pilot.promotion.dao.PromotionBN;
import vn.com.hkt.pilot.promotion.dao.SaleOffBN;
import vn.com.hkt.pilot.promotion.dao.SaleOffOptionBN;
import vn.com.hkt.pilot.promotion.dao.SaleOffProductBN;
import vn.com.hkt.pilot.promotion.data.finance.ClassificationOperationFinance;
import vn.com.hkt.pilot.promotion.entity.Promotion;
import vn.com.hkt.pilot.promotion.entity.SaleOff;
import vn.com.hkt.pilot.promotion.entity.SaleOffOption;
import vn.com.hkt.pilot.promotion.entity.SaleOffProduct;
import vn.com.hkt.pilot.promotion.ui.panel.dialog.CalendarOptionDialog;
import vn.com.hkt.pilot.promotion.viewer.api.IPromotionViewer;
import vn.com.hkt.pilot.toobar.api.IResetFontSize;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;

/**
 *
 * @author khangpn
 */
@ServiceProvider(service = IPromotionViewer.class)
public class SaleOffPriceViewerPanel extends javax.swing.JPanel implements IPromotionViewer,
        IResetFontSize, LookupListener,IUserInterface {

    int i = 0;
    private Lookup lookup;
    private InstanceContent content = new InstanceContent();
    private SaleOffBN saleOffBN;
    private PromotionBN promotionBN;
    private SaleOffOptionBN saleOffOptionBN;
    private SaleOffProductBN saleOffProductBN;
    private ISystemSotfwareBN sotfwareBN = Lookup.getDefault().lookup(ISystemSotfwareBN.class);
    private List<SystemSoftware> listS = new ArrayList<SystemSoftware>();
    private ClassificationOperationFinance classificationOperationFinance = new ClassificationOperationFinance();
    private int size;
    private String font;
    private List<Lookup.Result<SaleOffOptionLookup>> results = new ArrayList<Lookup.Result<SaleOffOptionLookup>>();
    private Collection<? extends ICalendarOptionCreator> calendarOptionCreators;
    private DefaultTableModel tableModel;

    /** Creates new form SaleOffPriceViewerPanel */
    public SaleOffPriceViewerPanel() {
        initComponents();

        lookup = new AbstractLookup(content);

        calendarOptionCreators = Lookup.getDefault().lookupAll(ICalendarOptionCreator.class);

        saleOffBN = new SaleOffBN();
        saleOffProductBN = new SaleOffProductBN();
        promotionBN = new PromotionBN();
        saleOffOptionBN = new SaleOffOptionBN();

        SaleOffPriceViewerCell priceViewerCell = new SaleOffPriceViewerCell();
        String[] header = {"Mã", "Tên CT KM", "Giá trị(%)", "Giá trị(Tiền)", "Từ ngày",
            "Đến ngày", "Từ giờ", "Đến giờ", "Tùy chọn", "Phân loại", "Ghi chú"};
        tableModel = new DefaultTableModel(header, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 8) {
                    return false;
                }
                return true;
            }
        };
        //refreshTable();
        tblSaleOffPriceViewer.setModel(tableModel);
        tblSaleOffPriceViewer.setRowHeight(26);
        tblSaleOffPriceViewer.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getComponent().isEnabled() && e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
                    if (tblSaleOffPriceViewer.getSelectedColumn() == 8) {
                        CalendarOptionDialog dialog = new CalendarOptionDialog(new JFrame());
                        dialog.show();
                    }
                }
            }
        });
        tblSaleOffPriceViewer.getColumnModel().getColumn(9).setCellEditor(priceViewerCell);

        resetColorRowTable();

        if (!calendarOptionCreators.isEmpty()) {
            for (ICalendarOptionCreator calendarOptionCreator : calendarOptionCreators) {
                Lookup.Result<SaleOffOptionLookup> result = null;
                result = calendarOptionCreator.getCalendarOptionLookupCreator().lookupResult(SaleOffOptionLookup.class);
                result.addLookupListener(this);
                results.add(result);
            }
        }
    }

    public void fillTable() {

        JOptionPane.showMessageDialog(null, i++);

        List<SaleOff> saleOffs = new ArrayList<SaleOff>();
        List<SaleOffProduct> saleOffProducts = new ArrayList<SaleOffProduct>();
        saleOffs = saleOffBN.selectAll();
        saleOffProducts = saleOffProductBN.selectAll();
        if (!saleOffs.isEmpty()) {
            for (SaleOff saleOff : saleOffs) {
                fillDataToTable(saleOff, null);
            }
        }
        if (!saleOffProducts.isEmpty()) {
            for (SaleOffProduct saleOff : saleOffProducts) {
                fillDataToTable(null, saleOff);
            }
        }

    }

    public void fillDataToTable(SaleOff saleOff, SaleOffProduct saleOffProduct) {
        Promotion promotion = null;

        String id = " ";
        String promotionName = " "; // Ten chuong trinh khuyen mai
        String promotionIdActual = " "; // Ten chuong trinh khuyen mai
        String value = " "; // Tinh theo %
        String realValue = " "; // Gia tri output tien

        Calendar datefrom = null;
        Calendar dateto = null;
        Calendar hourfrom = null;
        Calendar hourto = null;
        String dateFrom = " "; // Tu ngay 
        String dateTo = " "; // Den ngay
        String hourFrom = " "; // Tu ngay 
        String hourTo = " "; // Den ngay

        String note = " "; // Ghi chu 
        String saleOffOptionIdActual = " "; // Ma tuy chon ngay thang KM
        String classificationOperation = "N/A";

        if (saleOff != null) {
            promotionIdActual = String.valueOf(saleOff.getPromotionIdActual());
            promotion = promotionBN.getById(Integer.parseInt(promotionIdActual));
            promotionName = promotion.getPromotionName();
            value = String.valueOf(saleOff.getValue());
            realValue = String.valueOf(saleOff.getRealValue());
            id = String.valueOf(saleOff.getId());
            classificationOperation = classificationOperationFinance.getClassificationOperationName(promotion.getClassificationIdActual());
            note = saleOff.getNote();
        }
        if (saleOffProduct != null) {
            promotionIdActual = String.valueOf(saleOffProduct.getPromotionIdActual());
            promotion = promotionBN.getById(Integer.parseInt(promotionIdActual));
            promotionName = promotion.getPromotionName();
            value = "N/A";
            realValue = "N/A";
            note = "N/A";
            id = String.valueOf(saleOffProduct.getId());
            classificationOperation = classificationOperationFinance.getClassificationOperationName(promotion.getClassificationIdActual());
        }

        datefrom = promotion.getDateSart();
        if (datefrom != null) {
            dateFrom = datefrom.get(Calendar.DAY_OF_MONTH) + "/" + (datefrom.get(Calendar.MONTH) + 1)
                    + "/" + datefrom.get(Calendar.YEAR);
        }
        dateto = promotion.getDateFinish();
        if (dateto != null) {
            dateTo = dateto.get(Calendar.DAY_OF_MONTH) + "/" + (dateto.get(Calendar.MONTH) + 1)
                    + "/" + dateto.get(Calendar.YEAR);
        }
        hourfrom = promotion.getHourStart();
        if (hourfrom != null) {
            hourFrom = hourfrom.get(Calendar.HOUR_OF_DAY) + ":" + hourfrom.get(Calendar.MINUTE);
        }
        hourto = promotion.getHourFinish();
        if (hourto != null) {
            hourTo = hourto.get(Calendar.HOUR_OF_DAY) + ":" + hourto.get(Calendar.MINUTE);
        }
        saleOffOptionIdActual = String.valueOf(promotion.getSaleOffOptionIdActual());
        SaleOffOption saleOffOption = saleOffOptionBN.getById(Integer.parseInt(saleOffOptionIdActual));
        String saleOption = saleOffOption == null ? "Không" : "Có";

        String[] row = {id, promotionName, value, realValue,
            dateFrom, dateTo, hourFrom, hourTo, saleOption,
            classificationOperation, note};

        tableModel.addRow(row);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblSaleOffPriceViewer = new javax.swing.JTable();

        tblSaleOffPriceViewer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã", "Tên CT", "Giá trị(%)", "Giá trị(Tiền)", "Từ ngày(dd/mm/yy)", "Đến ngày(dd/mm/yy)", "Từ giờ(hh:mm)", "Đến giờ(hh:mm)", "Tùy chọn", "Phân loại","Ghi chú"
            }) {

                boolean[] canEdit = new boolean[]{
                    true, true, true,true, true, true,true, true, false,true,true
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    if (columnIndex==8) {
                        return false;
                    }
                    return true;
                }

            });
            jScrollPane1.setViewportView(tblSaleOffPriceViewer);

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(59, Short.MAX_VALUE))
            );
        }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSaleOffPriceViewer;
    // End of variables declaration//GEN-END:variables

    @Override
    public JPanel getPromotionViewer() {
        refreshTable();
        return this;
    }

    @Override
    public Lookup getPromotionLookup() {
        return lookup;
    }

    @Override
    public double getLevelNumber() {
        return 1.3;
    }

    @Override
    public String toString() {
        return "Khuyến mãi giá";
    }

    @Override
    public void refreshTable() {
        int i;
        int n = tableModel.getRowCount();
        while(n>0){
            tableModel.removeRow(n-1);
            n--;
        }
        tblSaleOffPriceViewer.removeAll();
        tblSaleOffPriceViewer.validate();
        tblSaleOffPriceViewer.repaint();
        fillTable();
        
    }

    // Liên thông font cỡ chữ, màu
    @Override
    public void resetFont() {
        listS = sotfwareBN.selectAll();
        size = tblSaleOffPriceViewer.getFont().getSize();
        font = listS.get(0).getFont().getFontName();
        tblSaleOffPriceViewer.setFont(new Font(font, 0, size));
    }

    @Override
    public void resetColorRowTable() {
        listS = sotfwareBN.selectAll();
//        ISystemToolbar systemToolbar=  (ISystemToolbar) BasicToolbarManager.getToolbarManager().getBasicToolbar().getTabToolbar(ISystemToolbar.TAB_INDEX);
//        Color x= systemToolbar.getColorDark();

        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(tblSaleOffPriceViewer, colorL, null, colorD, null);
        tblSaleOffPriceViewer.repaint();
    }

    @Override
    public void resetSize() {
        listS = sotfwareBN.selectAll();
        font = tblSaleOffPriceViewer.getFont().getFontName();
        size = listS.get(0).getSizeWord();
        tblSaleOffPriceViewer.setFont(new Font(font, 0, size));
         
    }

    @Override
    public void resetColorWord() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        tblSaleOffPriceViewer.setForeground(color);
        tblSaleOffPriceViewer.repaint();
    }

    @Override
    public void resetColorTitle() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorTitle().getRed(), listS.get(0).getColorTitle().getGreen(), listS.get(0).getColorTitle().getBlue());
        tblSaleOffPriceViewer.getTableHeader().setForeground(color);
        tblSaleOffPriceViewer.repaint();
    }

    @Override
    public void resetColorMouse() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorMouseClick().getRed(), listS.get(0).getColorMouseClick().getGreen(), listS.get(0).getColorMouseClick().getBlue());
        tblSaleOffPriceViewer.setSelectionBackground(color);
        tblSaleOffPriceViewer.repaint();
    }

    @Override
    public void resultChanged(LookupEvent le) {
        SaleOffOption saleOffOption = null;
        CreateKey createKey = new CreateKey();
        boolean isInserted = false;
        Lookup.Result<SaleOffOptionLookup> result = (Lookup.Result<SaleOffOptionLookup>) le.getSource();
        Collection<? extends SaleOffOptionLookup> saleOffOptionLookups = result.allInstances();
        if (!saleOffOptionLookups.isEmpty()) {
            for (SaleOffOptionLookup saleOffOptionLookup : saleOffOptionLookups) {
                saleOffOption = new SaleOffOption();
                String id = createKey.createKey("SOF");
                saleOffOption.setDailyID(saleOffOptionLookup.getDailyID());
                saleOffOptionLookup.setIsDaily(saleOffOptionLookup.isIsDaily());
                saleOffOption.setIsMonthly(saleOffOptionLookup.isIsMonthly());
                saleOffOption.setIsWeekly(saleOffOptionLookup.isIsWeekly());
                saleOffOptionLookup.setIsYearly(saleOffOptionLookup.isIsYearly());
                saleOffOption.setMonthlyID(saleOffOptionLookup.getMonthlyID());
                saleOffOption.setWeeklyID(saleOffOptionLookup.getWeeklyID());
                saleOffOption.setYearlyID(saleOffOptionLookup.getYearlyID());
                saleOffOption.setSaleOffOptionID(id);
                if (saleOffOption.getAccessDataOfEntity().insert(saleOffOption)) {
                    JOptionPane.showMessageDialog(null, "Insert Option successed!");
                    isInserted = true;
                }
            }
        }
        int rowIndex = tblSaleOffPriceViewer.getSelectedRow();
        if (rowIndex >= 0) {
            if (isInserted) {
                int id = Integer.parseInt(tblSaleOffPriceViewer.getValueAt(rowIndex, 0).toString());
                SaleOff saleOff = saleOffBN.getById(id);
                int promotionId = saleOff.getPromotionIdActual();
                Promotion promotion = promotionBN.getById(promotionId);
                promotion.setSaleOffOptionIdActual(saleOffOption.getId());
                if (promotionBN.update(promotion)) {
                    JOptionPane.showMessageDialog(null, "Updated!");
                }
                tblSaleOffPriceViewer.setValueAt("Có", rowIndex, 8);
            }


        }
    }

     @Override
    public String getUserInterfaceName() {
        return getClass().getSimpleName();
    }

    @Override
    public String getUserInterfaceDescription() {
        return "Giao diện danh sách giảm giá";
    }

    @Override
    public String getModuleName() {
        return Installer.MODULE_NAME;
    }
}
