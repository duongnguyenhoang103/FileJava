/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.promotion.ui.cell;

import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.TableCellEditor;
import org.openide.util.Lookup;
import vn.com.hkt.basic.api.IProductBN;
import vn.com.hkt.basic.cookie.api.IClassificationCookie;
import vn.com.hkt.pilot.entities.Classification;
import vn.com.hkt.pilot.entities.Product;
import vn.com.hkt.pilot.promotion.calculation.PromotionCalculate;
import vn.com.hkt.pilot.promotion.dao.PromotionBN;
import vn.com.hkt.pilot.promotion.entity.ClassifiedByProduct;
import vn.com.hkt.pilot.promotion.entity.Promotion;
import vn.com.hkt.pilot.promotion.entity.SaleOff;
import vn.com.hkt.pilot.promotion.temp.object.FloatObject;
import vn.com.hkt.pilot.promotion.ui.panel.OperationSalePanel;
import vn.com.hkt.pilot.sb42.pricesheet.dao.api.IPriceSheetBN;
import vn.com.hkt.pilot.sb42.pricesheet.finance.PriceSheetFinance;
import vn.com.hkt.pilot.sb42.pricesheeta.entity.PriceSheet;
import vn.com.hkt.pilot.ui.setup.JComboBoxFilterAPI;

/**
 *  Cell dùng cho bảng liệt kê các SP bán trong hóa đơn
 * @author khangpn
 */
public class OperationSaleProductCell extends AbstractCellEditor implements
        TableCellEditor, ItemListener {

    private Component component;
    private JComboBox cboListProduct, cboListPriceSheet, cboListPromotion;
    private JTextField txtQuantity;
    private DefaultComboBoxModel cboListProductModel, cboListPriceSheetModel, cboListPromotionModel;
    private IPriceSheetBN priceSheetBN;
    private IProductBN productBN;
    private PromotionBN promotionBN;
    private PromotionCalculate promotionCalculate;
    private OperationSalePanel operationSalePanel;
    private List<PriceSheet> priceSheets = new ArrayList<PriceSheet>();
    private List<Product> products = new ArrayList<Product>();
    private List<Promotion> promotions = new ArrayList<Promotion>();
    double total = 0;

    public OperationSaleProductCell(OperationSalePanel operationSalePanel) {

        this.operationSalePanel = operationSalePanel;
        priceSheetBN = Lookup.getDefault().lookup(IPriceSheetBN.class);
        productBN = Lookup.getDefault().lookup(IProductBN.class);
        promotionBN = new PromotionBN();
        promotionCalculate = new PromotionCalculate();

        fillCombo();
        cboListPriceSheet = new JComboBox();
        cboListPriceSheet.setModel(cboListPriceSheetModel);
        cboListPriceSheet.setRenderer(new TableComboPriceSheetApi());
        cboListPriceSheet.setEditable(true);
        new JComboBoxFilterAPI(cboListPriceSheet);
        cboListPriceSheet.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        cboListPriceSheet.addItemListener(this);

        cboListProduct = new JComboBox();
        cboListProduct.setModel(cboListProductModel);
        cboListProduct.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                if (cboListPriceSheet.getSelectedItem() != null) {
                    choosePromotion(cboListProduct.getSelectedItem().toString());
                }

            }
        });
        cboListProduct.addItemListener(this);

        cboListPromotion = new JComboBox();
        cboListPromotion.setModel(cboListPromotionModel);
        cboListPromotion.setRenderer(new TableComboSaleOffApi());
        cboListPromotion.setEditable(true);
        new JComboBoxFilterAPI(cboListPromotion);
        cboListPromotion.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                choosePromotion(getProductIdFromTable());
            }
        });
        cboListPromotion.addItemListener(this);

        txtQuantity = new JTextField();
        txtQuantity.addCaretListener(new CaretListener() {

            @Override
            public void caretUpdate(CaretEvent e) {
                try {
                    calculateTotal();
                } catch (Exception e1) {
                    return;
                }
            }
        });
    }

    protected String getProductIdFromTable() {
        int rowSelected = operationSalePanel.getTblOperationSale().getSelectedRow();
        String productId = operationSalePanel.getTblOperationSale().
                getValueAt(rowSelected, 1).toString();
        return productId;
    }

    public void fillCombo() {
        products = productBN.selectAll();
        promotions = promotionBN.selectAll();

        cboListPriceSheetModel = new DefaultComboBoxModel();

        cboListProductModel = new DefaultComboBoxModel();

        cboListPromotionModel = new DefaultComboBoxModel();

        validateCombo();

    }

    @Override
    public Object getCellEditorValue() {
        Object object = null;
        if (component instanceof JComboBox) {
            JComboBox comboBox = (JComboBox) component;
            object = comboBox.getSelectedItem();
        }
        if (component instanceof JTextField) {
            JTextField txt = (JTextField) component;
            object = txt.getText();
        }
        return object;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        if (column == 4) {
            component = cboListPriceSheet;
        } else if (column == 1) {
            component = cboListProduct;
        } else if (column == 5) {
            component = cboListPromotion;
        } else if (column == 3) {
            component = txtQuantity;
        } else {
            component = null;
        }
        return component;
    }

    public void refreshCombo() {
        validateCombo();
    }

    protected void validateCombo() {
        cboListPriceSheetModel.removeAllElements();
        cboListProductModel.removeAllElements();
        cboListPromotionModel.removeAllElements();
        products = productBN.selectAll();
        promotions = promotionBN.selectAll();

        cboListProductModel.addElement(" ");

        if (!products.isEmpty()) {
            for (Product product : products) {
                cboListProductModel.addElement(product.getProductId());
            }
        }
    }

    public void loadPriceSheet(int productIdActual) {
        cboListPriceSheetModel.removeAllElements();
        priceSheets = priceSheetBN.select(PriceSheet.FIELD_PRODUCT_ID_ACTUAL, String.valueOf(productIdActual));
        if (!priceSheets.isEmpty()) {
            for (PriceSheet priceSheet : priceSheets) {
                IClassificationCookie cookie = Lookup.getDefault().lookup(IClassificationCookie.class);
                if (cookie != null) {
                    Classification classification = cookie.getClassification();
                    if (classification != null) {
                        int idClassification = priceSheet.getIdClassification();
                        Classification classification1 = (Classification) classification.getAccessDataOfEntity().getById(idClassification);
                        if (classification1 != null) {
                            if (classification.getId() == classification1.getId()) {
                                PriceSheetFinance priceSheetFinance = new PriceSheetFinance();
                                if (priceSheetFinance.isValidate(priceSheet)) {
                                    cboListPriceSheetModel.addElement(priceSheet);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public Product getProduct() {
        Product product = new Product();
        if (cboListProduct.getSelectedIndex() != -1) {
            product = (Product) cboListProduct.getSelectedItem();
        }
        return product;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == cboListPriceSheet) {
            if (cboListProduct.getSelectedIndex() != -1) {
                String id = cboListProduct.getSelectedItem().toString();
                choosePromotion(id);
            }
            calculateTotal();
        }
        if (e.getSource() == cboListPromotion) {
            calculateTotal();
        }
        if (e.getSource() == cboListProduct) {
            Product product = null;
            if (cboListProduct.getSelectedIndex() >= 0) {
                cboListPriceSheetModel.removeAllElements();
                String id = cboListProduct.getSelectedItem().toString();
                product = (Product) productBN.getByObjectId(id);
                int row = operationSalePanel.getTblOperationSale().getSelectedRow();
                if (row >= 0) {
                    if (product != null) {
                        operationSalePanel.getTblOperationSale().setValueAt(product.getProductName(), row, 2);
                        loadPriceSheet(product.getId());
                        choosePromotion(id);
                    }
                }


            }
        }

    }

    public void calculateTotal() {
        int rowIndex = operationSalePanel.getTblOperationSale().getSelectedRow();
        int quantity = 0;
        float total = 0;
        int i = cboListPriceSheet.getSelectedIndex();
        int j = cboListPromotion.getSelectedIndex();
        int percent = 0, saleOffValue = 0;
        try {
            quantity = Integer.parseInt(txtQuantity.getText());
        } catch (Exception e) {
            quantity = 0;
            return;
        }
        if (i != -1) {
            PriceSheet priceSheet = (PriceSheet) cboListPriceSheet.getSelectedItem();
            total = priceSheet.getExportUnit();
            if (j != -1) {
                Promotion promotion = (Promotion) cboListPromotion.getSelectedItem();
                if (promotion != null) {
                    int saleOffId = promotion.getId();
                    List<SaleOff> saleOffs = new ArrayList<SaleOff>();
                    SaleOff saleOff = new SaleOff();
                    saleOffs = saleOff.getAccessDataOfEntity().select(saleOff.FIELD_PROMOTION_ID_ACTUAL, String.valueOf(saleOffId));
                    if (!saleOffs.isEmpty()) {
                        saleOff = saleOffs.get(0);
                        saleOffValue = saleOff.getRealValue();
                        percent = saleOff.getValue();
                    }
                }
            }
            if (saleOffValue != 0) {
                total = priceSheet.getExportUnit() * quantity * 1.0f - (float) saleOffValue * 1.0f;
            } else if (percent != 0) {
                total = (float) (priceSheet.getExportUnit() * quantity * 1.0f
                        - (priceSheet.getExportUnit() * 1.0f * percent / 100));
            } else {
                total = priceSheet.getExportUnit() * quantity * 1.0f;
            }
        } else {
            operationSalePanel.getTblOperationSale().setValueAt("0", rowIndex, 6);
        }
        if (rowIndex != -1) {
            float s = 0;
            BigDecimal bigDecimal = new BigDecimal(total);
            BigDecimal decimalToLookup = null;
            operationSalePanel.getTblOperationSale().setValueAt(bigDecimal.toEngineeringString(), rowIndex, 6);
            decimalToLookup = calculateBigDecimal(operationSalePanel.getTblOperationSale());
            s = calculateFloat(operationSalePanel.getTblOperationSale());
            if (decimalToLookup != null) {
                FloatObject f = new FloatObject(s);
                operationSalePanel.getContent().set(Collections.singleton(f), null);
            }
        }

    }

    public BigDecimal calculateBigDecimal(JTable table) {
        float sum = 0;
        int n = table.getRowCount();
        int i;
        for (i = 0; i < n; i++) {
            if (table.getValueAt(i, 6).toString().trim().length() != 0) {
                float tg = Float.parseFloat(table.getValueAt(i, 6).toString());
                sum += tg;
            }
        }
        BigDecimal T = new BigDecimal(sum);
        return T;
    }

    public float calculateFloat(JTable table) {
        float sum = 0;
        int n = table.getRowCount();
        int i;
        for (i = 0; i < n; i++) {
            if (table.getValueAt(i, 6).toString().trim().length() != 0) {
                float tg = Float.parseFloat(table.getValueAt(i, 6).toString());
                sum += tg;
            }
        }
        return sum;
    }

    public void choosePromotion(String productId) {
        int rowSelected = operationSalePanel.getTblOperationSale().getSelectedRow();
        cboListPromotionModel.removeAllElements();
        cboListPromotion.removeAllItems();
        if (rowSelected >= 0) {
            operationSalePanel.getTblOperationSale().setValueAt(" ", rowSelected, 5);
            cboListPromotion.setSelectedIndex(-1);
        }
        if (!promotions.isEmpty()) {

            Calendar calendar = Calendar.getInstance();
            for (Promotion promotion : promotions) {
                if (promotionCalculate.checkPromotionTimeLimit(promotion, calendar)) {


                    IClassificationCookie cookie = Lookup.getDefault().lookup(IClassificationCookie.class);
                    if (cookie != null) {
                        Classification classification = cookie.getClassification();
                        if (classification != null) {
                            int idClassification = promotion.getIdClassification();
                            Classification classification1 = (Classification) classification.getAccessDataOfEntity().getById(idClassification);
                            if (classification1 != null) {
                                if (classification.getId() == classification1.getId()) {
                                    /**
                                     * Nếu là 1-> thực thi theo Department
                                     * Nếu là 2-> thực thi theo Enterprise
                                     * Nếu là 3-> thực thi theo Operation
                                     * Nếu là 4-> thực thi theo Person
                                     * Nếu là 5-> thực thi theo Product
                                     */
                                    int classificationId = promotion.getClassificationIdActual();

                                    if (rowSelected != -1 && classificationId > 0) {
                                        if (classificationId == 5) { // Neu la phan loai theo san pham
                                            if (productId.trim().length() > 0) {
                                                Product product = new Product();
                                                product = (Product) product.getAccessDataOfEntity().getByObjectId(productId);
                                                if (product != null) {
                                                    int productIdActual = product.getId();
                                                    ClassifiedByProduct classifiedByProduct = new ClassifiedByProduct();
                                                    List<ClassifiedByProduct> classifiedByProducts = new ArrayList<ClassifiedByProduct>();
                                                    classifiedByProducts = classifiedByProduct.getAccessDataOfEntity().
                                                            select(classifiedByProduct.FIELD_PROMOTION_ID_ACTUAL, String.valueOf(promotion.getId()));
                                                    if (!classifiedByProducts.isEmpty()) {
                                                        classifiedByProduct = classifiedByProducts.get(0);

                                                        List<Integer> integers = classifiedByProduct.getProductIdActuals();
                                                        if (!integers.isEmpty()) {
                                                            for (Integer i : integers) {

                                                                if (i == productIdActual) {
                                                                    cboListPromotionModel.addElement(promotion);
                                                                }
                                                            }
                                                        }
                                                    }

                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }

                }
            }
        }
        cboListPromotion.setModel(cboListPromotionModel);
    }

    /**
     * reset method
     */
    public void reset() {
        JComboBox[] combos = {cboListProduct, cboListPriceSheet, cboListPromotion};
        txtQuantity.setText("");
        int i, n = combos.length;
        for (i = 0; i < n; i++) {
            combos[i].setSelectedIndex(-1);
        }
    }
}
