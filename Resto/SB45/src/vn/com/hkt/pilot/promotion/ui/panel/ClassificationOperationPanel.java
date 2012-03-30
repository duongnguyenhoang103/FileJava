/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ClassificationOperationPanel.java
 *
 * Created on Mar 8, 2012, 10:03:03 AM
 */
package vn.com.hkt.pilot.promotion.ui.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import vn.com.hkt.basic.api.IDepartmentBN;
import vn.com.hkt.basic.api.IEnterpriseBN;
import vn.com.hkt.basic.api.IOperationBN;
import vn.com.hkt.basic.api.IPersonBN;
import vn.com.hkt.basic.api.IProductBN;
import vn.com.hkt.basic.api.ISystemSotfwareBN;
import vn.com.hkt.basic.toolbar.BasicToolbarManager;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Operation;
import vn.com.hkt.pilot.entities.Person;
import vn.com.hkt.pilot.entities.Product;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.identity.presentation.api.IUserInterface;
import vn.com.hkt.pilot.keymanager.api.CreateKey;
import vn.com.hkt.pilot.promotion.Installer;
import vn.com.hkt.pilot.promotion.entity.ClassifiedByDepartment;
import vn.com.hkt.pilot.promotion.entity.ClassifiedByEnterprise;
import vn.com.hkt.pilot.promotion.entity.ClassifiedByOperation;
import vn.com.hkt.pilot.promotion.entity.ClassifiedByPerson;
import vn.com.hkt.pilot.promotion.entity.ClassifiedByProduct;
import vn.com.hkt.pilot.promotion.entity.Promotion;
import vn.com.hkt.pilot.promotion.viewer.api.IPromotionCreator;
import vn.com.hkt.pilot.toobar.api.IResetFontSize;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;

/**
 *
 * @author khangpn
 */
public class ClassificationOperationPanel extends javax.swing.JPanel implements
        IResetFontSize, LookupListener, ItemListener, IUserInterface {

    private List<Lookup.Result<Promotion>> results = new ArrayList<Lookup.Result<Promotion>>();
    private Collection<? extends IPromotionCreator> promotionCreators;
    private List<Integer> listObjectId;
    private ISystemSotfwareBN sotfwareBN = Lookup.getDefault().lookup(ISystemSotfwareBN.class);
    private List<SystemSoftware> listS = new ArrayList<SystemSoftware>();
    private int size;
    private String font;
    private String operationType = " ";
    private IEnterpriseBN enterpriseBN;
    private IDepartmentBN departmentBN;
    private IProductBN productBN;
    private IPersonBN personBN;
    private IOperationBN operationBN;
    /**
     * Nếu là 1-> thực thi theo Department
     * Nếu là 2-> thực thi theo Enterprise
     * Nếu là 3-> thực thi theo Operation
     * Nếu là 4-> thực thi theo Person
     * Nếu là 5-> thực thi theo Product
     */
    private int flag = 0;
    private int enterpriseId = 0;
    private DefaultComboBoxModel comboBoxModel;
    private Enterprise enterprise;
    private DefaultTableModel tableModel;

    /** Creates new form ClassificationOperationPanel */
    public ClassificationOperationPanel(int flag) {
        initComponents();

        enterpriseBN = Lookup.getDefault().lookup(IEnterpriseBN.class);
        departmentBN = Lookup.getDefault().lookup(IDepartmentBN.class);
        productBN = Lookup.getDefault().lookup(IProductBN.class);
        personBN = Lookup.getDefault().lookup(IPersonBN.class);
        operationBN = Lookup.getDefault().lookup(IOperationBN.class);

        enterprise = BasicToolbarManager.getBasicToolbar().getEnterprise();
        if (enterprise != null) {
            enterpriseId = enterprise.getId();
        }

        this.flag = flag;

        String[] header = {"Thứ tự", "Tên"};
        tableModel = new DefaultTableModel(header, 0);

        tblClassificationOperation.setModel(tableModel);

        comboBoxModel = new DefaultComboBoxModel();

        fillCombo();

        cboSelect.setModel(comboBoxModel);
        cboSelect.addItemListener(this);

        promotionCreators = Lookup.getDefault().lookupAll(IPromotionCreator.class);
        if (!promotionCreators.isEmpty()) {
            for (IPromotionCreator promotionCreator : promotionCreators) {
                Lookup.Result<Promotion> r = promotionCreator.getPromotionLookup().
                        lookupResult(Promotion.class);
                r.addLookupListener(this);
                results.add(r);
            }
        }

        listObjectId = new ArrayList<Integer>();
        tblClassificationOperation.setRowHeight(26);

        resetColorRowTable();

    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(int enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public JTable getTblClassificationOperation() {
        return tblClassificationOperation;
    }

    public void setTblClassificationOperation(JTable tblClassificationOperation) {
        this.tblClassificationOperation = tblClassificationOperation;
    }

    public List<Integer> getListObjectId() {
        return listObjectId;
    }

    public void setListObjectId(List<Integer> listObjectId) {
        this.listObjectId = listObjectId;
    }

    public JPanel getClassificationOperationPanel() {
        fillCombo();
        return this;
    }

    public void fillCombo() {
        /**
         * Khởi tạo
         */
        comboBoxModel.removeAllElements();
        comboBoxModel.addElement(" ");

        if (enterpriseId != 0) {
            List<Enterprise> enterprises = new ArrayList<Enterprise>();
            List<Product> products = productBN.select(Product.FIELD_ENTERPRISE_ID_ACTUAL, String.valueOf(enterprise.getId()));
            List<Operation> operations = operationBN.select(Product.FIELD_ENTERPRISE_ID_ACTUAL,
                    String.valueOf(enterprise.getId()));
            List<Person> persons = personBN.selectAll();
            List<Department> departments = departmentBN.select(Department.FIELD_ENTERPRISE_ID_ACTUAL, String.valueOf(enterprise.getId()));

            if (flag == 1) {

                if (!departments.isEmpty()) {
                    for (Department department : departments) {
                        comboBoxModel.addElement(department);
                    }
                }
            }
            if (flag == 2) {
                if (enterprise != null) {
                    enterprises = getEnterpriseChildren(enterprise);
                    if (!enterprises.isEmpty()) {
                        for (Enterprise e : enterprises) {
                            comboBoxModel.addElement(e);
                        }
                    }
                }
            }
            if (flag == 3) {
                if (!operations.isEmpty()) {
                    for (Operation o : operations) {
                        comboBoxModel.addElement(o);
                    }
                }
            }
            if (flag == 4) {
                if (!persons.isEmpty()) {
                    for (Person p : persons) {
                        comboBoxModel.addElement(p);
                    }
                }
            }
            if (flag == 5) {
                if (!products.isEmpty()) {
                    for (Product p : products) {
                        comboBoxModel.addElement(p);
                    }
                }
            }

        }
    }

    public List<Enterprise> getEnterpriseChildren(Enterprise enterprise) {
        List<Enterprise> le = new ArrayList<Enterprise>();
        List<Enterprise> list = enterpriseBN.select(
                Enterprise.FILED_ENTERPRISE_PARENT_ID_ACTUAL, String.valueOf(enterprise.getId()));
        for (int i = 0; i < list.size(); i++) {
            le.add(list.get(i));
            List<Enterprise> l = getEnterpriseChildren(list.get(i));
            for (int j = 0; j < l.size(); j++) {
                le.add(l.get(j));
            }
        }
        return le;

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClassificationOperation = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboSelect = new javax.swing.JComboBox();

        tblClassificationOperation.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{
                {"1", " "},
                {"3", " "}
            },
            new String[]{
                "", ""
            }) {

                boolean[] canEdit = new boolean[]{
                    false,false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {

                    return false;
                }
            });
            jScrollPane1.setViewportView(tblClassificationOperation);

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
            );

            jLabel1.setText(org.openide.util.NbBundle.getMessage(ClassificationOperationPanel.class, "ClassificationOperationPanel.jLabel1.text")); // NOI18N

            cboSelect.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(26, 26, 26)
                    .addComponent(cboSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(440, Short.MAX_VALUE))
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                        .addComponent(cboSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
        }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboSelect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblClassificationOperation;
    // End of variables declaration//GEN-END:variables

    // Liên thông font cỡ chữ, màu
    @Override
    public void resetFont() {
        listS = sotfwareBN.selectAll();
        size = tblClassificationOperation.getFont().getSize();
        font = listS.get(0).getFont().getFontName();
        tblClassificationOperation.setFont(new Font(font, 0, size));
    }

    @Override
    public void resetColorRowTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(tblClassificationOperation, colorL, null, colorD, null);
        tblClassificationOperation.repaint();
    }

    @Override
    public void resetSize() {
        listS = sotfwareBN.selectAll();
        font = tblClassificationOperation.getFont().getFontName();
        size = listS.get(0).getSizeWord();
        tblClassificationOperation.setFont(new Font(font, 0, size));

    }

    @Override
    public void resetColorWord() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        tblClassificationOperation.setForeground(color);
        tblClassificationOperation.repaint();
    }

    @Override
    public void resetColorTitle() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorTitle().getRed(), listS.get(0).getColorTitle().getGreen(), listS.get(0).getColorTitle().getBlue());
        tblClassificationOperation.getTableHeader().setForeground(color);
        tblClassificationOperation.repaint();
    }

    @Override
    public void resetColorMouse() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorMouseClick().getRed(), listS.get(0).getColorMouseClick().getGreen(), listS.get(0).getColorMouseClick().getBlue());
        tblClassificationOperation.setSelectionBackground(color);
        tblClassificationOperation.repaint();
    }

    @Override
    public void resultChanged(LookupEvent le) {
        Lookup.Result<Promotion> r = (Lookup.Result<Promotion>) le.getSource();
        Collection<? extends Promotion> promotions = r.allInstances();
        for (Promotion promotion : promotions) {
            if (save(promotion)) {
                JOptionPane.showMessageDialog(null, "Saved Options!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed!");
            }
        }
    }

    /**
     * Nếu là 1-> thực thi theo Department
     * Nếu là 2-> thực thi theo Enterprise
     * Nếu là 3-> thực thi theo Operation
     * Nếu là 4-> thực thi theo Person
     * Nếu là 5-> thực thi theo Product
     */
    public boolean save(Promotion promotion) {
        boolean isSaved = false;
        int promotionId = promotion.getId();
        String key = " ";
        CreateKey createKey = new CreateKey();
        key = createKey.createKey("CLF");
        /**
         * Lưu thông tin Department
         */
        if (flag == 1) {
            ClassifiedByDepartment classifiedByDepartment = new ClassifiedByDepartment();
            classifiedByDepartment.setClassificationId(key);
            classifiedByDepartment.setDepartmentIdActuals(listObjectId);
            classifiedByDepartment.setPromotionIdActual(promotionId);
            isSaved = classifiedByDepartment.getAccessDataOfEntity().insert(classifiedByDepartment);
        }
        /**
         * Lưu thông tin Enterprise
         */
        if (flag == 2) {
            ClassifiedByEnterprise classifiedByEnterprise = new ClassifiedByEnterprise();
            classifiedByEnterprise.setClassificationId(key);
            classifiedByEnterprise.setEnterpriseIdActuals(listObjectId);
            classifiedByEnterprise.setPromotionIdActual(promotionId);
            isSaved = classifiedByEnterprise.getAccessDataOfEntity().insert(classifiedByEnterprise);
        }
        /**
         * Lưu thông tin Department
         */
        if (flag == 3) {
            ClassifiedByOperation classifiedByOperation = new ClassifiedByOperation();
            classifiedByOperation.setClassificationId(key);
            classifiedByOperation.setOperationType(operationType);
            classifiedByOperation.setPromotionIdActual(promotionId);
            isSaved = classifiedByOperation.getAccessDataOfEntity().insert(classifiedByOperation);
        }
        /**
         * Lưu thông tin Enterprise
         */
        if (flag == 4) {
            ClassifiedByPerson classifiedByPerson = new ClassifiedByPerson();
            classifiedByPerson.setClassificationId(key);
            classifiedByPerson.setPersonIdActuals(listObjectId);
            classifiedByPerson.setPromotionIdActual(promotionId);
            isSaved = classifiedByPerson.getAccessDataOfEntity().insert(classifiedByPerson);
        }
        /**
         * Lưu thông tin Enterprise
         */
        if (flag == 5) {
            ClassifiedByProduct classifiedByProduct = new ClassifiedByProduct();
            classifiedByProduct.setClassificationId(key);
            classifiedByProduct.setProductIdActuals(listObjectId);
            classifiedByProduct.setPromotionIdActual(promotionId);
            isSaved = classifiedByProduct.getAccessDataOfEntity().insert(classifiedByProduct);
        }
        return isSaved;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        int m = e.getStateChange();
        if (e.getSource() == cboSelect) {
            Object o = cboSelect.getSelectedItem();
            if ((m == 2) && (o instanceof Enterprise)) {
                Enterprise e1 = (Enterprise) o;
                int n = e1.getId();
                if (pushListObjectId(n)) {
                    pushRowTable(e1.getEnterpriseName());
                }
            }
            if ((m == 2) && (o instanceof Department)) {
                Department e1 = (Department) o;
                int n = e1.getId();
                if (pushListObjectId(n)) {
                    pushRowTable(e1.getDepartmentName());
                }
            }
            if ((m == 2) && (o instanceof Operation)) {
                Operation e1 = (Operation) o;
                int n = e1.getId();
                if (pushListObjectId(n)) {
                    pushRowTable(e1.getOperationName());
                }
            }
            if ((m == 2) && (o instanceof Person)) {
                Person e1 = (Person) o;
                int n = e1.getId();
                if (pushListObjectId(n)) {
                    pushRowTable(e1.getPersonName());
                }
            }
            if ((m == 2) && (o instanceof Product)) {
                Product e1 = (Product) o;
                int n = e1.getId();
                if (pushListObjectId(n)) {
                    pushRowTable(e1.getProductName());
                }
            }
        }
    }

    public void pushRowTable(String str) {
        int rowCount = tblClassificationOperation.getRowCount();
        String rowIndext = String.valueOf(rowCount + 1);
        String[] row = {rowIndext, str};
        tableModel.addRow(row);
    }

    public boolean pushListObjectId(int n) {
        List<Integer> integers = getListObjectId();
        if (!integers.isEmpty()) {
            if (!checkListObjectId(n, integers)) {
                listObjectId.add(n);
                return true;
            }
        } else {
            listObjectId.add(n);
            return true;
        }
        return false;
    }

    public boolean checkListObjectId(int n, List<Integer> integers) {
        if (!integers.isEmpty()) {
            for (Integer i : integers) {
                if (i == n) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String getUserInterfaceName() {
        return getClass().getSimpleName();
    }

    @Override
    public String getUserInterfaceDescription() {
        return "Giao diện phân loại nghiệp vụ theo sản phẩm, doanh nghiêp.....";
    }

    @Override
    public String getModuleName() {
        return Installer.MODULE_NAME;
    }
}
