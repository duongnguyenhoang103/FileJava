/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.bom.product.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.*;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.TopComponent;
import vn.com.hkt.basic.api.IDepartmentBN;
import vn.com.hkt.basic.api.IEnterpriseBN;
import vn.com.hkt.basic.api.IPersonBN;
import vn.com.hkt.basic.api.IProductBN;
import vn.com.hkt.basic.toolbar.BasicToolbarManager;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnableTable;
import vn.com.hkt.pilot.enterprise.viewer.api.IGetObject;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.KeyGenerate;
import vn.com.hkt.pilot.entities.Person;
import vn.com.hkt.pilot.entities.Product;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.entities.system.dao.SystemSotfwareDAO;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.keymanager.api.CreateKey;
import vn.com.hkt.pilot.product.viewer.api.CreateKeyProduct;
import vn.com.hkt.pilot.product.viewer.api.HelpTutorialProduct;
import vn.com.hkt.pilot.product.viewer.api.IProductCreater;
import vn.com.hkt.pilot.toobar.api.IResetFontSize;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;
import vn.com.hkt.ui.main.ui.api.creater.ISaveBasic;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//vn.com.hkt.bom.product.ui//Product//EN",
autostore = false)
@TopComponent.Description(preferredID = "ProductTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "vn.com.hkt.bom.product.ui.ProductTopComponent")
@ActionReference(path = " " /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_ProductAction",
preferredID = "ProductTopComponent")
@ServiceProvider(service = IProductCreater.class)
public final class ProductTopComponent extends TopComponent implements ActionListener, ISaveBasic,
        IProductCreater, IEnableTable, CreateKeyProduct, IResetFontSize, IGetObject {

    private ProductPanel productPanel;
    private IProductBN productBN;
    private int size;
    private String font;
    private SystemSotfwareDAO sotfwareBN;
    private List<SystemSoftware> listS;
    private ProductCell productCell;
    private Product product = null;
    private ProductEditIDPanel editIDPanel;
    private String key = null;
    private CreateKey createKey = null;

    public ProductTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(ProductTopComponent.class, "CTL_ProductTopComponent"));
        setToolTipText(NbBundle.getMessage(ProductTopComponent.class, "HINT_ProductTopComponent"));
        panelForm.setLayout(new GridLayout());
        productPanel = new ProductPanel();
        panelForm.add(productPanel);
        sotfwareBN = new SystemSotfwareDAO();
        listS = new ArrayList<SystemSoftware>();
        productCell = new ProductCell();

        productBN = Lookup.getDefault().lookup(IProductBN.class);

        //         Bắt sự kiện select row của table 
        productPanel.getTableProduct().addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableMousePressed(evt);
            }
        });
    }

    public ProductPanel getProductPanel() {
        return productPanel;
    }

    public JPanel getPanelForm() {
        return panelForm;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelForm = new javax.swing.JPanel();

        javax.swing.GroupLayout panelFormLayout = new javax.swing.GroupLayout(panelForm);
        panelForm.setLayout(panelFormLayout);
        panelFormLayout.setHorizontalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 844, Short.MAX_VALUE)
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 238, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(53, 53, 53))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelForm;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
    }

    @Override
    public void componentClosed() {
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        // ListProductTopComponent listProductTopComponent = new ListProductTopComponent();

    }

    // reset lại table mỗi khi save hoặc muốn điền mới
    @Override
    public void reset() {
        product = null;
        Enterprise enterprise1 = BasicToolbarManager.getBasicToolbar().getEnterprise();
        if (enterprise1 != null) {
            productCell.resetCombobox(enterprise1);
            productPanel.reset();
        } else {
            productPanel.reset();
        }
        productPanel.getTableProduct().getColumnModel().getColumn(1).setCellEditor(productCell);
        productPanel.getTableProduct().getColumnModel().getColumn(0).setPreferredWidth(100);
        productPanel.getTableProduct().getColumnModel().getColumn(0).setMaxWidth(100);
        productPanel.getTableProduct().setRowSelectionAllowed(true);
        productPanel.getTableProduct().setColumnSelectionAllowed(false);
        productPanel.getTableProduct().setSelectionBackground(new Color(192, 210, 224));
        productPanel.getTableProduct().setRowHeight(26);
//        productPanel.setDataImages(null);
//        productPanel.showImage();
    }

    @Override
    public IEntity save() {
        Product bean = this.getProduct();
        productBN.update(bean);
        return bean;

    }

    public Product getProduct() {
        Product bean = new Product();
        if (product != null) {
            bean = product;
        }

        String idProduct = productPanel.getTableProduct().getValueAt(1, 1).toString().trim();
        String nameProduct = productPanel.getTableProduct().getValueAt(0, 1).toString().trim();
        int idEnterprise;
        int idDepartment;
        int idPerson;
        int idGroupProduct;
        byte[] imagePhoTo = productPanel.getDataImages();

        try {
            Enterprise e = (Enterprise) productPanel.getTableProduct().getValueAt(2, 1);
            idEnterprise = e.getId();
        } catch (Exception ex) {
            idEnterprise = 0;
        }
        // Lấy mã và lưu Product xuống csdl
        if (productPanel.getTableProduct().getValueAt(5, 1) != null) {
            if (productPanel.getTableProduct().getValueAt(5, 1).toString().trim().length() != 0) {
                Product pro = (Product) productPanel.getTableProduct().getValueAt(5, 1);
                idGroupProduct = pro.getId();
            } else {
                idGroupProduct = 0;
            }
        } else {
            idGroupProduct = 0;
        }
        // Lấy mã và lưu Department xuống csdl
        if (productPanel.getTableProduct().getValueAt(3, 1) != null) {
            if (productPanel.getTableProduct().getValueAt(3, 1).toString().trim().length() != 0) {
                Department department = (Department) productPanel.getTableProduct().getValueAt(3, 1);
                idDepartment = department.getId();
            } else {
                idDepartment = 0;
            }
        } else {
            idDepartment = 0;
        }
        // lấy và lưu mã Person xuống csdl 
        if (productPanel.getTableProduct().getValueAt(4, 1) != null) {
            if (productPanel.getTableProduct().getValueAt(4, 1).toString().trim().length() != 0) {
                Person person1 = (Person) productPanel.getTableProduct().getValueAt(4, 1);
                idPerson = person1.getId();
            } else {
                idPerson = 0;
            }

        } else {
            idPerson = 0;
        }
        bean.setProductId(idProduct);
        bean.setProductName(nameProduct);
        bean.setEnterpriseIdActual(idEnterprise);
        bean.setDepartmentIdActual(idDepartment);
        bean.setPersonDesignIdActual(idPerson);
        bean.setProductGroupIdActual(idGroupProduct);
        bean.setImagePhoto(imagePhoTo);
        return bean;
    }

    @Override
    public JPanel getProductCreater() {
        return this.productPanel;
    }

    @Override
    public Lookup getProductLookup() {
        return null;
    }

    @Override
    public double getLevelNumber() {
        return 4.00;
    }

    private void tableMousePressed(MouseEvent evt) {
        ProductTutorial productTutorial = new ProductTutorial();
        JTable table = (JTable) evt.getSource();
        if (table == productPanel.getTableProduct()) {
            if (productPanel.getTableProduct().getSelectedRow() == 1) {
                addFormEditID(0.1, "");
            }
            if (productPanel.getTableProduct().getSelectedRow() == 0) {
                addFormEditID(0, productTutorial.getTxtNameProduct().getText());
            }
            if (productPanel.getTableProduct().getSelectedRow() == 2) {
                addFormEditID(0.2, productTutorial.getTxtEnterprise().getText());
            }
            if (productPanel.getTableProduct().getSelectedRow() == 3) {
                addFormEditID(0.3, productTutorial.getTxtDepartment().getText());
            }
            if (productPanel.getTableProduct().getSelectedRow() == 4) {
                addFormEditID(0.4, productTutorial.getTxtDirector().getText());
            }
            if (productPanel.getTableProduct().getSelectedRow() == 5) {
                addFormEditID(0.5, productTutorial.getTxtProductParent().getText());
            }
        }
    }

    private void addFormEditID(double i, String str) {
        Collection<? extends HelpTutorialProduct> allSave = Lookup.getDefault().lookupAll(HelpTutorialProduct.class);
        for (HelpTutorialProduct editCookie : allSave) {
            editCookie.getTutorial(i, str);
        }
    }

    @Override
    public void createKey() {
        boolean change = false;
        KeyGenerate keyGenerate = null;

        createKey = new CreateKey();
        editIDPanel = ProductEditIDPanel.getPanel();
        change = editIDPanel.isGetBoolean();
        List<Product> listD = productBN.selectAll();
        for (Product beans : listD) {
            if (key == beans.getProductId()) {
                key = null;
            }
        }
        if (key == null) {
            keyGenerate = new KeyGenerate();
            keyGenerate = createKey.createAKeyByParent(Product.class, editIDPanel.getKeyManager().getLastPrefix());
            key = keyGenerate.toString();
        }
        if (change == true) {
            keyGenerate.setPrefix(editIDPanel.getKeyManager().getLastPrefix());
            keyGenerate.getAccessDataOfEntity().update(keyGenerate);
            key = keyGenerate.toString();
        }
        productCell = new ProductCell();
        productPanel.getTableProduct().setValueAt(key, 1, 1);
        productCell.getTxtIdPro().setText(key);
        productPanel.getTableProduct().getColumnModel().getColumn(1).setCellEditor(productCell);

        Enterprise enterprise1 = BasicToolbarManager.getBasicToolbar().getEnterprise();
        if (enterprise1 != null) {
            productPanel.getTableProduct().setValueAt(enterprise1, 2, 1);
            productCell.resetCombobox(enterprise1);
        }
    }

    // Liên thông font cỡ chữ, màu
    @Override
    public void resetFont() {
        listS = sotfwareBN.selectAll();
        size = productPanel.getTableProduct().getFont().getSize();
        font = listS.get(0).getFont().getFontName();
        productPanel.getTableProduct().setFont(new Font(font, 0, size));
    }

    @Override
    public void resetColorRowTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(productPanel.getTableProduct(), colorL, null, colorD, null);
        productPanel.getTableProduct().repaint();
    }

    @Override
    public void resetSize() {
        listS = sotfwareBN.selectAll();
        font = productPanel.getTableProduct().getFont().getFontName();
        size = listS.get(0).getSizeWord();
        productPanel.getTableProduct().setFont(new Font(font, 0, size));
        productPanel.getTableProduct().repaint();
    }

    @Override
    public void resetColorWord() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        productPanel.getTableProduct().setForeground(color);
        productPanel.getTableProduct().repaint();
    }

    @Override
    public void resetColorTitle() {
//        listS = sotfwareBN.selectAll();
//        Color color = new Color(listS.get(0).getColorTitle().getRed(), listS.get(0).getColorTitle().getGreen(), listS.get(0).getColorTitle().getBlue());
//        productPanel.getTableProduct().getTableHeader().setForeground(color);
//         productPanel.getTableProduct().repaint();
    }

    @Override
    public void resetColorMouse() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorMouseClick().getRed(), listS.get(0).getColorMouseClick().getGreen(), listS.get(0).getColorMouseClick().getBlue());
        productPanel.getTableProduct().setSelectionBackground(color);
        productPanel.getTableProduct().repaint();
    }

    @Override
    public void getObject(String id) {
        Product p;
        try {
            p = productBN.getByObjectId(id);
            if (p == null) {
                return;
            }
            product = p;
        } catch (Exception ex) {
            return;
        }

        try {
            resultEvent(p);
        } catch (Exception ex) {
        }
    }

    private void resultEvent(Product p) {
        IDepartmentBN departmentBN = Lookup.getDefault().lookup(IDepartmentBN.class);
        IPersonBN personBN = Lookup.getDefault().lookup(IPersonBN.class);
        IEnterpriseBN enterpriseBN = Lookup.getDefault().lookup(IEnterpriseBN.class);
        try {
            Enterprise enterprise = enterpriseBN.getById(p.getEnterpriseIdActual());
            Department department = departmentBN.getById(p.getDepartmentIdActual());
            Person person = personBN.getById(p.getPersonDesignIdActual());
            Product productParent = productBN.getById(p.getProductGroupIdActual());
            productPanel.getTableProduct().setValueAt(p.getProductName(), 0, 1);
            productPanel.getTableProduct().setValueAt(p.getProductId(), 1, 1);
            productPanel.getTableProduct().setValueAt(enterprise, 2, 1);
            productPanel.getTableProduct().setValueAt(department, 3, 1);
            productPanel.getTableProduct().setValueAt(person, 4, 1);
            productPanel.getTableProduct().setValueAt(productParent, 5, 1);
            productPanel.setDataImages(p.getImagePhoto());
            productPanel.showImage();
            resetColorTitle();
            resetColorWord();
            resetFont();
            resetColorRowTable();
        } catch (Exception ex) {
        }
    }

    @Override
    public void enableTable(boolean ok) throws IOException {
        productPanel.getTableProduct().setEnabled(ok);
//        productPanel.getLabelImage().setEnabled(ok);
    }

    @Override
    public List<JTable> getTables() {
        List<JTable> lt = new ArrayList<JTable>();
        lt.add(productPanel.getTableProduct());
        return lt;
    }
}