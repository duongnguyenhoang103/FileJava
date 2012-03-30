/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.promotion.ui.cell;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import org.openide.util.Lookup;
import vn.com.hkt.basic.api.IClassificationBN;
import vn.com.hkt.pilot.entities.Classification;
import vn.com.hkt.pilot.keymanager.api.CreateKey;
import vn.com.hkt.pilot.promotion.entity.IClassificationOperation;
import vn.com.hkt.pilot.promotion.ui.panel.ClassificationOperationPanel;
import vn.com.hkt.pilot.promotion.ui.panel.SaleOffPriceCreatorPanel;
import vn.com.hkt.pilot.promotion.ui.panel.SaleOffProductCreatorPanel;

/**
 *
 * @author khangpn
 */
public class SaleOffPiceTableCell extends AbstractCellEditor implements TableCellEditor,
        TableCellRenderer, ActionListener, ItemListener {

    private Component component;
    private JLabel label1, label2, label4, label5, label6, label7, label8, label9,
            label11, label12, label13;
    private ButtonGroup buttonGroupValue;
    private JRadioButton rdoValueMoney, rdoValuePercent;
    private JTextField txtPromotionName, txtPromotionId, txtPercentValue, txtMoneyValue;
    private JSpinner spnHourFrom, spnHourTo;
    SpinnerDateModel smFrom, smTo;
    private JComboBox cboClassificationBasic, cboClassificationSaleOff, cboCalculationWay;
    private DefaultComboBoxModel cboClassificationBasicModel, cboClassificationSaleOffModel,
            cboCalculationWayModel;
    private JDateChooser datechooserFrom, datechooserTo;
    private IClassificationBN classificationBN;
    private SaleOffPriceCreatorPanel saleOffPriceCreatorPanel;
    private String valuePecentMoney = "";

    public SaleOffPiceTableCell(SaleOffPriceCreatorPanel saleOffPriceCreatorPanel) {

        this.saleOffPriceCreatorPanel = saleOffPriceCreatorPanel;

        classificationBN = Lookup.getDefault().lookup(IClassificationBN.class);

        buttonGroupValue = new ButtonGroup();

        rdoValueMoney = new JRadioButton("Giá trị tiền");
        rdoValueMoney.addActionListener(this);

        rdoValuePercent = new JRadioButton("Giá trị (%)");
        rdoValuePercent.addActionListener(this);

        buttonGroupValue.add(rdoValueMoney);
        buttonGroupValue.add(rdoValuePercent);

        label1 = new JLabel("Tên CT KM");
        label2 = new JLabel("Mã CT KM");
        label4 = new JLabel("Theo giờ từ");
        label5 = new JLabel("Theo ngày từ");
        label6 = new JLabel("Phân loại");
        label7 = new JLabel("1");
        label8 = new JLabel("3");
        label9 = new JLabel("Phân loại");
        label11 = new JLabel("Đến");
        label12 = new JLabel("Đến");
        label13 = new JLabel("Cách tính");

        setUpSpinner();

        txtMoneyValue = new JTextField();
        txtMoneyValue.setEnabled(false);
        txtMoneyValue.addCaretListener(new CaretListener() {

            @Override
            public void caretUpdate(CaretEvent e) {
                valuePecentMoney = txtMoneyValue.getText();
                setValueTemp(1, txtMoneyValue);
            }
        });

        txtPercentValue = new JTextField();
        txtPercentValue.setEnabled(false);
        txtPercentValue.addCaretListener(new CaretListener() {

            @Override
            public void caretUpdate(CaretEvent e) {
                valuePecentMoney = txtPercentValue.getText();
                setValueTemp(0, txtPercentValue);
            }
        });

        txtPromotionId = new JTextField(createKey());
        setPromotionIdTemp();
        txtPromotionId.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent arg0) {
                setPromotionIdTemp();
            }
        });

        txtPromotionName = new JTextField();
        txtPromotionName.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent arg0) {
                setPromotionNameTemp();
            }
        });

        loadToCombo();

        cboCalculationWay = new JComboBox(cboCalculationWayModel);
        cboCalculationWay.setSelectedIndex(-1);
        cboCalculationWay.addItemListener(this);

        cboClassificationBasic = new JComboBox(cboClassificationBasicModel);
        cboClassificationBasic.setSelectedIndex(-1);
        cboClassificationBasic.addItemListener(this);

        cboClassificationSaleOff = new JComboBox(cboClassificationSaleOffModel);
        cboClassificationSaleOff.setSelectedIndex(-1);
        cboClassificationSaleOff.addItemListener(this);

        datechooserFrom = new JDateChooser();
        datechooserFrom.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                setDateTemp(1);
            }
        });
        datechooserFrom.setDateFormatString("dd/MM/yyyy");

        datechooserTo = new JDateChooser();
        datechooserTo.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                setDateTemp(0);
            }
        });
        datechooserTo.setDateFormatString("dd/MM/yyyy");
    }

    public void setPromotionNameTemp() {
        saleOffPriceCreatorPanel.getPromotion().setPromotionName(txtPromotionName.getText());
    }

    public void setValueTemp(int flag, JTextField textField) {
        try {
            valuePecentMoney = textField.getText().trim().length() == 0 ? "0" : textField.getText();
            int value = Integer.parseInt(valuePecentMoney);
            if (flag == 0) {
                saleOffPriceCreatorPanel.getSaleOff().setValue(value);
            } else {
                saleOffPriceCreatorPanel.getSaleOff().setRealValue(value);
            }
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null, "Bạn phải nhập số!");
        }

    }

    protected void setUpSpinner() {
        Date dateFrom = new Date();
        smFrom = new SpinnerDateModel(dateFrom, null, null, Calendar.HOUR_OF_DAY);
        spnHourFrom = new JSpinner(smFrom);
        JSpinner.DateEditor deFrom = new JSpinner.DateEditor(spnHourFrom, "HH:mm");
        spnHourFrom.setEditor(deFrom);
        setHourTemp(1, spnHourFrom);
        spnHourFrom.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                setHourTemp(1, spnHourFrom);
            }
        });

        Date dateTo = new Date();
        smTo = new SpinnerDateModel(dateTo, null, null, Calendar.HOUR_OF_DAY);
        spnHourTo = new JSpinner(smTo);
        JSpinner.DateEditor deTo = new JSpinner.DateEditor(spnHourTo, "HH:mm");
        spnHourTo.setEditor(deTo);
        setHourTemp(0, spnHourTo);
        spnHourTo.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                setHourTemp(0, spnHourTo);
            }
        });
    }

    public void setDateTemp(int flag) {
        Date d = null;
        if (flag == 0) {
            try {
                d = datechooserTo.getDate();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(d);

                saleOffPriceCreatorPanel.getPromotion().setDateFinish(calendar);
            } catch (Exception e) {
                return;
            }

        } else {
            try {
                d = datechooserFrom.getDate();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(d);
                saleOffPriceCreatorPanel.getPromotion().setDateSart(calendar);
            } catch (Exception e) {
                return;
            }

        }

    }

    public void setHourTemp(int flag, JSpinner spinner) { // flag == 0 thì setFinish, ngược lại setStart
        Calendar dateTemp = Calendar.getInstance();
        Date date = (Date) spinner.getValue();
        try {
            dateTemp.setTime(date);
            if (flag == 0) {
                saleOffPriceCreatorPanel.getPromotion().setHourFinish(dateTemp);
            } else {
                saleOffPriceCreatorPanel.getPromotion().setHourStart(dateTemp);
            }

        } catch (Exception e) {
            return;
        }
    }

    public void setPromotionIdTemp() {
        saleOffPriceCreatorPanel.getPromotion().setPromotionID(txtPromotionId.getText());
    }

    protected String createKey() {
        CreateKey createKey = new CreateKey();
        return createKey.createKey("PRM");
    }

    protected void loadToCombo() {

        String[] calculationway = {"Cộng dồn", "Không cộng dồn"};
        cboCalculationWayModel = new DefaultComboBoxModel(calculationway);

        cboClassificationBasicModel = new DefaultComboBoxModel();
        List<Classification> classifications = new ArrayList<Classification>();
        classifications = classificationBN.selectAll();
        if (!classifications.isEmpty()) {
            for (Classification c : classifications) {
                cboClassificationBasicModel.addElement(c);
            }
        }

        cboClassificationSaleOffModel = new DefaultComboBoxModel();
        Collection<? extends IClassificationOperation> classificationOperations;
        classificationOperations = Lookup.getDefault().lookupAll(IClassificationOperation.class);
        if (!classificationOperations.isEmpty()) {
            for (IClassificationOperation classificationOperation : classificationOperations) {
                cboClassificationSaleOffModel.addElement(classificationOperation);
            }
            cboClassificationSaleOffModel.addElement("Sản phẩm-Sản phẩm");
        }

    }

    @Override
    public Object getCellEditorValue() {
        if (component instanceof JTextField) {
            JTextField textField = (JTextField) component;
            return textField.getText();
        }
        if (component instanceof JComboBox) {
            JComboBox comboBox = (JComboBox) component;
            return comboBox.getSelectedItem();
        }
        if (component instanceof JDateChooser) {
            JDateChooser dateChooser = (JDateChooser) component;
            JTextField textField = (JTextField) dateChooser.getDateEditor().getUiComponent();
            return textField.getText();
        }
        if (component instanceof JSpinner) {
            JSpinner spinner = (JSpinner) component;
            return spinner.getValue();
        }
        return null;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return getComponentAbstract(row, column);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return getComponentAbstract(row, column);
    }

    protected Component getComponentAbstract(int row, int col) {
        if (col == 0) {
            switch (row) {
                case 0:
                    component = label1;
                    break;
                case 1:
                    component = label2;
                    break;
                case 2:
                    component = rdoValuePercent;
                    break;
                case 3:
                    component = label4;
                    break;
                case 4:
                    component = label5;
                    break;
                case 5:
                    component = label6;
                    break;
                case 6:
                    component = label7;
                    break;
                case 7:
                    component = label8;
                    break;
                default:
                    component = null;
            }
        } else if (col == 1) {
            switch (row) {
                case 0:
                    component = txtPromotionName;
                    break;
                case 1:
                    component = txtPromotionId;
                    break;
                case 2:
                    component = txtPercentValue;
                    break;
                case 3:
                    component = spnHourFrom;
                    break;
                case 4:
                    component = datechooserFrom;
                    break;
                case 5:
                    component = cboClassificationSaleOff;
                    break;
                default:
                    component = null;
            }
        } else if (col == 2) {
            switch (row) {
                case 1:
                    component = label9;
                    break;
                case 2:
                    component = rdoValueMoney;
                    break;
                case 3:
                    component = label11;
                    break;
                case 4:
                    component = label12;
                    break;
                case 5:
                    component = label13;
                    break;
                default:
                    component = null;
            }
        } else if (col == 3) {
            switch (row) {
                case 1:
                    component = cboClassificationBasic;
                    break;
                case 2:
                    component = txtMoneyValue;
                    break;
                case 3:
                    component = spnHourTo;
                    break;
                case 4:
                    component = datechooserTo;
                    break;
                case 5:
                    component = cboCalculationWay;
                    break;
                default:
                    component = null;
            }
        } else {
            component = null;
        }
        return component;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rdoValueMoney) {
            if (rdoValueMoney.isSelected()) {
                txtMoneyValue.setEnabled(true);
                txtPercentValue.setText("");
                valuePecentMoney = "";
                txtPercentValue.setEnabled(false);
                saleOffPriceCreatorPanel.getTblSaleOffPrice().repaint();
            }
        }
        if (e.getSource() == rdoValuePercent) {
            if (rdoValuePercent.isSelected()) {
                txtMoneyValue.setEnabled(false);
                txtMoneyValue.setText("");
                valuePecentMoney = "";
                txtPercentValue.setEnabled(true);
                saleOffPriceCreatorPanel.getTblSaleOffPrice().repaint();
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == cboCalculationWay) {
            int index = cboCalculationWay.getSelectedIndex();
            if (index > -1) {
                String strCalculationWay = cboCalculationWay.getSelectedItem().toString();
                saleOffPriceCreatorPanel.getSaleOff().setCalculationWay(strCalculationWay);
                //JOptionPane.showMessageDialog(null, "CalculateWay called!");
            }
        }
        if (e.getSource() == cboClassificationBasic) {
            int index = cboClassificationBasic.getSelectedIndex();
            if (index > -1) {
                Classification classification = null;
                classification = (Classification) cboClassificationBasic.getSelectedItem();
                int classificationIdActual = classification.getId();
                saleOffPriceCreatorPanel.getPromotion().setIdClassification(classificationIdActual);
            }
        }
        if (e.getSource() == cboClassificationSaleOff) {
            Object object = cboClassificationSaleOff.getSelectedItem();
            if (object != null) {
                if (object instanceof IClassificationOperation) {
                    int classificationSaleOffId = 0;
                    saleOffPriceCreatorPanel.getPanelContent().setLayout(new GridLayout(0, 1));
                    classificationSaleOffId = ((IClassificationOperation) cboClassificationSaleOff.getSelectedItem()).getHardId();
                    ClassificationOperationPanel panel = new ClassificationOperationPanel(classificationSaleOffId);
                    saleOffPriceCreatorPanel.getPromotion().setClassificationIdActual(classificationSaleOffId);
                    JPanel panelPrice = saleOffPriceCreatorPanel.getPanelPrice();
                    saleOffPriceCreatorPanel.getPanelContent().removeAll();
                    saleOffPriceCreatorPanel.getPanelContent().invalidate();
                    saleOffPriceCreatorPanel.getPanelContent().add(panelPrice);
                    saleOffPriceCreatorPanel.getPanelContent().add(panel.getClassificationOperationPanel());
                    saleOffPriceCreatorPanel.setIsProductSaleOff(false); // Chuyển sang KM giá
                    saleOffPriceCreatorPanel.getPanelContent().revalidate();
                    saleOffPriceCreatorPanel.getPanelContent().repaint();
                    rdoValueMoney.setEnabled(true);
                    rdoValuePercent.setEnabled(true);
                } else if (object.toString().equals("Sản phẩm-Sản phẩm")) {
                    saleOffPriceCreatorPanel.getPanelContent().setLayout(new GridLayout(0, 1));
                    SaleOffProductCreatorPanel saleOffProductCreatorPanel = new SaleOffProductCreatorPanel(saleOffPriceCreatorPanel);
                    JPanel panel = saleOffProductCreatorPanel.getPromotionCreator();
                    JPanel panelPrice = saleOffPriceCreatorPanel.getPanelPrice();
                    saleOffPriceCreatorPanel.setIsProductSaleOff(true); // Chuyển sang KM giá
                    saleOffPriceCreatorPanel.getPanelContent().removeAll();
                    saleOffPriceCreatorPanel.getPanelContent().invalidate();
                    saleOffPriceCreatorPanel.getPanelContent().add(panelPrice);
                    saleOffPriceCreatorPanel.getPanelContent().add(panel);
                    saleOffPriceCreatorPanel.getPanelContent().revalidate();
                    saleOffPriceCreatorPanel.getPanelContent().repaint();
                    rdoValueMoney.setEnabled(false);
                    rdoValuePercent.setEnabled(false);
                }
            }
        }
    }

    public void reset() {
        JRadioButton[] ra = {rdoValueMoney, rdoValuePercent};
        JTextField[] text = {txtPromotionName, txtPromotionId,
            txtPercentValue, txtMoneyValue};
        JDateChooser[] dc = {datechooserFrom, datechooserTo};
        JComboBox[] cbo = {cboClassificationBasic, cboClassificationSaleOff,
            cboCalculationWay};
        int i, j, k, m;
        /**
         * reset radiobutton
         */
        for (i = 0; i < ra.length; i++) {
            ra[i].setSelected(false);
        }
        /**
         * reset textField vaf Id
         */
        for (j = 0; j < text.length; j++) {
            text[j].setText(" ");
        }
        txtPromotionId.setText(createKey());
        /**
         * reset Spinner
         */
        setUpSpinner();
        /**
         * reset DateChooser
         */
        for (k = 0; k < dc.length; k++) {
            dc[k].setDate(new Date());
        }
        /**
         * reset ComboBox
         */
        for (m = 0; m < cbo.length; m++) {
            cbo[m].setSelectedIndex(-1);
        }

        saleOffPriceCreatorPanel.getTblSaleOffPrice().repaint();

    }
}
