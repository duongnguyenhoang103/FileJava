/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.tree.ui.enterprise.thuchi.bytime;

import java.awt.BorderLayout;
import java.util.Collection;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import org.openide.util.LookupEvent;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.propertysheet.PropertySheet;
import org.openide.explorer.view.OutlineView;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.LookupListener;
import org.openide.util.Utilities;
import vn.com.hkt.pilot.datetime.utils.DateTimeUtils;
import vn.com.hkt.pilot.entities.Enterprise;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//vn.com.hkt.tree.ui.enterprise.thuchi.bytime//TreeEnterpriseThuChiByYear//EN",
autostore = false)
@TopComponent.Description(preferredID = "TreeEnterpriseThuChiByYearTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "vn.com.hkt.tree.ui.enterprise.thuchi.bytime.TreeEnterpriseThuChiByYearTopComponent")
@ActionReference(path = "Menu/Kinh Doanh/Doanh Thu Theo Lĩnh Vực" /*, position = 333 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_TreeEnterpriseThuChiByYearAction",
preferredID = "TreeEnterpriseThuChiByYearTopComponent")
public final class TreeEnterpriseThuChiByYearTopComponent extends TopComponent implements ExplorerManager.Provider, LookupListener {

    private ExplorerManager explorerManager;
    private EnterpriseParent enterpriseParent;
    private Lookup.Result<Enterprise> result = null;
    private OutlineView ov;
    private Node rootNode;
    private Enterprise enterprise;
    private DateTimeUtils dateTimeUtils;
    private String month1, month2, month3, year1, year2;
    private DefaultTableModel model;

    public TreeEnterpriseThuChiByYearTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(TreeEnterpriseThuChiByYearTopComponent.class, "CTL_TreeEnterpriseThuChiByYearTopComponent"));
        setToolTipText(NbBundle.getMessage(TreeEnterpriseThuChiByYearTopComponent.class, "HINT_TreeEnterpriseThuChiByYearTopComponent"));
        panelTree.setLayout(new BorderLayout());
        enterprise = new Enterprise();
        dateTimeUtils = new DateTimeUtils();
        month1 = dateTimeUtils.getCurrentMonth() + "/" + dateTimeUtils.getCurrentYear();
        month2 = String.valueOf(dateTimeUtils.addMonth(-1)) + "/" + dateTimeUtils.addYearWithMonth(-1);
        month3 = String.valueOf(dateTimeUtils.addMonth(-2)) + "/" + dateTimeUtils.addYearWithMonth(-2);
        year1 = String.valueOf(dateTimeUtils.getCurrentYear());
        year2 = String.valueOf(dateTimeUtils.getCurrentYear() - 1);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTree = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableTitle = new javax.swing.JTable();

        javax.swing.GroupLayout panelTreeLayout = new javax.swing.GroupLayout(panelTree);
        panelTree.setLayout(panelTreeLayout);
        panelTreeLayout.setHorizontalGroup(
            panelTreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        panelTreeLayout.setVerticalGroup(
            panelTreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 282, Short.MAX_VALUE)
        );

        tableTitle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tree Enterprise", "12/2011", "11/2011", "10/2011", "2011", "2010", "All"
            }
        ));
        jScrollPane1.setViewportView(tableTitle);
        tableTitle.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(TreeEnterpriseThuChiByYearTopComponent.class, "TreeEnterpriseThuChiByYearTopComponent.tableTitle.columnModel.title0")); // NOI18N
        tableTitle.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(TreeEnterpriseThuChiByYearTopComponent.class, "TreeEnterpriseThuChiByYearTopComponent.tableTitle.columnModel.title1")); // NOI18N
        tableTitle.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(TreeEnterpriseThuChiByYearTopComponent.class, "TreeEnterpriseThuChiByYearTopComponent.tableTitle.columnModel.title2")); // NOI18N
        tableTitle.getColumnModel().getColumn(3).setHeaderValue(org.openide.util.NbBundle.getMessage(TreeEnterpriseThuChiByYearTopComponent.class, "TreeEnterpriseThuChiByYearTopComponent.tableTitle.columnModel.title3")); // NOI18N
        tableTitle.getColumnModel().getColumn(4).setHeaderValue(org.openide.util.NbBundle.getMessage(TreeEnterpriseThuChiByYearTopComponent.class, "TreeEnterpriseThuChiByYearTopComponent.tableTitle.columnModel.title4")); // NOI18N
        tableTitle.getColumnModel().getColumn(5).setHeaderValue(org.openide.util.NbBundle.getMessage(TreeEnterpriseThuChiByYearTopComponent.class, "TreeEnterpriseThuChiByYearTopComponent.tableTitle.columnModel.title5")); // NOI18N
        tableTitle.getColumnModel().getColumn(6).setHeaderValue(org.openide.util.NbBundle.getMessage(TreeEnterpriseThuChiByYearTopComponent.class, "TreeEnterpriseThuChiByYearTopComponent.tableTitle.columnModel.title6")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addComponent(panelTree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelTree;
    private javax.swing.JTable tableTitle;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        explorerManager = new ExplorerManager();
        enterpriseParent = new EnterpriseParent(enterprise);

        rootNode = new AbstractNode(enterpriseParent);
        ov = new OutlineView("Tên công ty");
        ov.setPropertyColumns(EnterpriseParent.ThuMonth1_PROPERTY, "Thu",
                EnterpriseParent.ChiMonth1_PROPERTY, "Chi",
                EnterpriseParent.Lai1_PROPERTY, "Lãi(lỗ)",
                EnterpriseParent.ThuMonth2_PROPERTY, "Thu",
                EnterpriseParent.ChiMonth2_PROPERTY, "Chi",
                EnterpriseParent.Lai2_PROPERTY, "Lãi(lỗ)",
                EnterpriseParent.ThuMonth3_PROPERTY, "Thu",
                EnterpriseParent.ChiMonth3_PROPERTY, "Chi",
                EnterpriseParent.Lai3_PROPERTY, "Lãi(lỗ)",
                EnterpriseParent.ThuYear1_PROPERTY, "Thu",
                EnterpriseParent.ChiYear1_PROPERTY, "Chi",
                EnterpriseParent.LaiYear1_PROPERTY, "Lãi(lỗ)",
                EnterpriseParent.ThuYear2_PROPERTY, "Thu",
                EnterpriseParent.ChiYear2_PROPERTY, "Chi",
                EnterpriseParent.LaiYear2_PROPERTY, "Lãi(lỗ)",
                EnterpriseParent.ThuAll_PROPERTY, "Thu",
                EnterpriseParent.ChiAll_PROPERTY, "Chi",
                EnterpriseParent.LaiAll_PROPERTY, "Lãi(lỗ)");

        ov.getOutline().setRootVisible(true);
        ov.getOutline().setRowHeight(25);
        ov.getOutline().setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        ov.getOutline().getColumnModel().getColumn(0).setPreferredWidth(300);
        // JOptionPane.showMessageDialog(null,  ov.getOutline().getColumnModel().getColumn(1).getPreferredWidth());

        panelTree.add(ov, BorderLayout.CENTER);
       // setLocationRelativeTo(null); 

        explorerManager.setRootContext(rootNode);
        explorerManager.getRootContext().setDisplayName("Enterprise");


        result = Utilities.actionsGlobalContext().lookupResult(Enterprise.class);
        result.addLookupListener(this);

        String[] header = {"Tree Enterprise", month1, month2, month3, year1, year2, "All"};
        model = new DefaultTableModel(header, 0);
        tableTitle.setModel(model);

        // Tạo title cho table Outline
        tableTitle.getColumnModel().getColumn(0).setPreferredWidth(290);
        
        tableTitle.getColumnModel().getColumn(1).setPreferredWidth(225);
        tableTitle.getColumnModel().getColumn(2).setPreferredWidth(225);
        tableTitle.getColumnModel().getColumn(3).setPreferredWidth(225);
        tableTitle.getColumnModel().getColumn(4).setPreferredWidth(225);
        tableTitle.getColumnModel().getColumn(5).setPreferredWidth(225);
        tableTitle.getColumnModel().getColumn(6).setPreferredWidth(225);
      
    }

    @Override
    public void componentClosed() {
        panelTree.remove(ov);
        result.removeLookupListener(this);
        result = null;
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
    public ExplorerManager getExplorerManager() {
        return explorerManager;
    }

    @Override
    public void resultChanged(LookupEvent le) {
        Collection<? extends Enterprise> allEvents = result.allInstances();
        if (!allEvents.isEmpty()) {
            Enterprise enterprise = allEvents.iterator().next();
        }
    }
}
