/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.promotion.ui.cell;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellEditor;
import org.openide.util.Lookup;
import vn.com.hkt.basic.cookie.api.IClassificationCookie;
import vn.com.hkt.pilot.entities.Classification;
import vn.com.hkt.pilot.promotion.calculation.PromotionCalculate;
import vn.com.hkt.pilot.promotion.dao.PromotionBN;
import vn.com.hkt.pilot.promotion.dao.SaleOffBN;
import vn.com.hkt.pilot.promotion.entity.Promotion;
import vn.com.hkt.pilot.promotion.entity.SaleOff;
import vn.com.hkt.pilot.promotion.ui.panel.OPerationCalculatorPanel;
import vn.com.hkt.pilot.ui.setup.JComboBoxFilterAPI;

/**
 *
 * @author khangutc
 */
public class OperationCalculatorCell extends AbstractCellEditor implements
        TableCellEditor, ItemListener, ChangeListener, CaretListener {

    private JComboBox cboListPromotion;
    private JSpinner spnTax;
    private JTextField txtPay;
    private SpinnerNumberModel numberModel;
    private DefaultComboBoxModel cboListPromotionModel;
    private Component component;
    private List<Promotion> promotions = new ArrayList<Promotion>();
    private PromotionBN promotionBN;
    private SaleOffBN saleOffBN;
    private OPerationCalculatorPanel oPerationCalculatorPanel;
    private PromotionCalculate promotionCalculate = new PromotionCalculate();

    public OperationCalculatorCell(OPerationCalculatorPanel oPerationCalculatorPanel) {

        this.oPerationCalculatorPanel = oPerationCalculatorPanel;
        promotionBN = new PromotionBN();
        saleOffBN = new SaleOffBN();

        cboListPromotion = new JComboBox();
        fillCombo();        
        cboListPromotion.setRenderer(new TableComboSaleOffApi());
        cboListPromotion.setEditable(true);
        new JComboBoxFilterAPI(cboListPromotion);
        cboListPromotion.addItemListener(this);

        numberModel = new SpinnerNumberModel(0, 0, 1000, 1);
        spnTax = new JSpinner(numberModel);
        spnTax.addChangeListener(this);

        txtPay = new JTextField();
        txtPay.addCaretListener(this);

    }

    @Override
    public Object getCellEditorValue() {
        if (component instanceof JComboBox) {
            JComboBox comboBox = (JComboBox) component;
            return comboBox.getSelectedItem();
        }
        if (component instanceof JSpinner) {
            JSpinner spinner = (JSpinner) component;
            return spinner.getValue();
        }
        if (component instanceof JTextField) {
            JTextField textField = (JTextField) component;
            return textField.getText();
        }
        return null;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        switch (column) {
            case 1:
                switch (row) {
                    case 1:
                        component = cboListPromotion;
                        break;
                    case 2:
                        component = spnTax;
                        break;
                    default:
                        component = null;
                }
                break;
            case 3:
                switch (row) {
                    case 3:
                        component = txtPay;
                        break;
                    default:
                        component = null;
                }
                break;
            default:
                component = null;
        }
        return component;
    }

    public void fillCombo() {
        promotions = promotionBN.selectAll();
        cboListPromotionModel = new DefaultComboBoxModel();
        validateCombo();  
        cboListPromotion.setModel(cboListPromotionModel);
    }

    protected void validateCombo() {
        cboListPromotionModel.removeAllElements();
        promotions = promotionBN.selectAll();
        int classifiedByOperationId = 3; // Phan loai theo hoa don
        if (!promotions.isEmpty()) {
            IClassificationCookie cookie = Lookup.getDefault().lookup(IClassificationCookie.class);
            if (cookie != null) {
                Classification classification = cookie.getClassification();
                if (classification != null) {
                    for (Promotion p : promotions) {
                        int idClassification = p.getIdClassification();
                        Classification classification1 = (Classification) classification.getAccessDataOfEntity().getById(idClassification);
                        if (classification1 != null) {
                            if (classification.getId() == classification1.getId()) {
                                Calendar calendar = Calendar.getInstance();
                                if (promotionCalculate.checkPromotionTimeLimit(p, calendar)) {
                                    if (p.getClassificationIdActual() == classifiedByOperationId) {
                                        cboListPromotionModel.addElement(p);
                                    }
                                }
                            }
                        }
                    }
                }
            }


        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == cboListPromotion) {
            calculateValueSaleOff();
            oPerationCalculatorPanel.autoFitSaleOff();
        }
    }

    /**
     * Ham tinh toan tong tien theo % hay theo gia tri tien mat
     * @param sumPrice Tong tien
     * @param value Gia tri (% or Price)
     * @param isValue Co cho biet dang thuc hien tinh % hay tien mat
     */
    public void calculateValueSaleOff() {
        float realValue = 0, value = 0;
        Object o = cboListPromotion.getSelectedItem();
        if (o instanceof Promotion) {
            Promotion p = (Promotion) o;
            int id = p.getId();
            List<SaleOff> saleOffs = saleOffBN.select(SaleOff.FIELD_PROMOTION_ID_ACTUAL, String.valueOf(id));
            if (!saleOffs.isEmpty()) {
                SaleOff saleOff = saleOffs.get(0);
                float sumPrice = oPerationCalculatorPanel.getSumPrice();
                if (saleOff.getValue() != 0) {
                    value = saleOff.getValue();
                    oPerationCalculatorPanel.setSaleOffPercent((int) value);
                    realValue = sumPrice * value / 100;
                } else if (saleOff.getRealValue() != 0) {
                    value = saleOff.getRealValue();
                    oPerationCalculatorPanel.setSaleOffValue((int) value);
                    realValue = value;
                }
                BigDecimal bigDecimal = new BigDecimal(realValue);
                oPerationCalculatorPanel.setRealValue(realValue);
                String strValue = bigDecimal.toEngineeringString();
                oPerationCalculatorPanel.getTblOperationCalculator().setValueAt(strValue, 1, 3);

            }
        }

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == spnTax) {
            float realTax = Float.parseFloat(spnTax.getValue().toString());
            oPerationCalculatorPanel.setPercentOfTax(realTax);
            oPerationCalculatorPanel.autoFitSaleOff();
        }
    }

    @Override
    public void caretUpdate(CaretEvent e) {
        if (e.getSource() == txtPay) {
            float pay = 0, onloanPrice = 0, result = 0;
            float sumPrice = oPerationCalculatorPanel.getTotalPrice();
            BigDecimal bigResult;
            try {
                pay = Float.parseFloat(txtPay.getText());
            } catch (Exception e1) {
                pay = 0;
                return;
            }

            result = calculatePayMoney(sumPrice, pay);
            onloanPrice = Math.abs(result);
            if (result < 0) {
                bigResult = new BigDecimal(onloanPrice);
                oPerationCalculatorPanel.getTblOperationCalculator().
                        setValueAt(bigResult.toEngineeringString(), 4, 1);                
                oPerationCalculatorPanel.getTblOperationCalculator().
                        setValueAt("0", 4, 3);
                oPerationCalculatorPanel.setReturnPrice(0);
                oPerationCalculatorPanel.setOnLoanPrice(onloanPrice);
            } else {
                bigResult = new BigDecimal(onloanPrice);
                oPerationCalculatorPanel.getTblOperationCalculator().
                        setValueAt(bigResult.toEngineeringString(), 4, 3);
                bigResult = new BigDecimal(onloanPrice);
                oPerationCalculatorPanel.getTblOperationCalculator().
                        setValueAt("0", 4, 1);
                oPerationCalculatorPanel.setReturnPrice(onloanPrice);
                oPerationCalculatorPanel.setOnLoanPrice(0);
            }

        }
    }

    /**
     * Tính số tiền dư hoặc nợ khi khách trả tiền
     * @param sumPrice Tiền phải trả
     * @param payPrice Tiền khách trả
     * @return 
     */
    protected float calculatePayMoney(float sumPrice, float payPrice) {
        return sumPrice - payPrice;
    }
    
    public void reset(){
        
    }
}
