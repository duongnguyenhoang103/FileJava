/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelTongQuan.java
 *
 * Created on Jan 3, 2012, 2:39:21 PM
 */
package vn.com.hkt.tree.ui.enterprise.thuchi.tongquan;

import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.view.OutlineView;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Node;
import vn.com.hkt.pilot.datetime.utils.DateTimeUtils;
import vn.com.hkt.pilot.entities.Enterprise;

/**
 *
 * @author longnt
 */
public class PanelTongQuan extends javax.swing.JPanel implements ExplorerManager.Provider {
    private ExplorerManager explorerManager;
    private EnterpriseParentTongQuan enterpriseParent;
    private OutlineView ov;
    private Node rootNode;
    private Enterprise enterprise;
    private DateTimeUtils dateTimeUtils;
    private String month1, year2;
    private DefaultTableModel model;

    /** Creates new form PanelTongQuan */
    public PanelTongQuan() {
        initComponents();
        panelTree.setLayout(new BorderLayout());
        enterprise = new Enterprise();
        dateTimeUtils = new DateTimeUtils();
        month1 = dateTimeUtils.getCurrentMonth() + "/" + dateTimeUtils.getCurrentYear();
     
        year2 = String.valueOf(dateTimeUtils.getCurrentYear() - 1);
          explorerManager = new ExplorerManager();
        enterpriseParent = new EnterpriseParentTongQuan(enterprise);

        rootNode = new AbstractNode(enterpriseParent);
        ov = new OutlineView("Tên công ty");
        ov.setPropertyColumns(EnterpriseParentTongQuan.ThuMonth1_PROPERTY, "Thu",
                EnterpriseParentTongQuan.ChiMonth1_PROPERTY, "Chi",
                EnterpriseParentTongQuan.Lai1_PROPERTY, "Lãi(lỗ)",
            
                EnterpriseParentTongQuan.ThuYear1_PROPERTY, "Thu",
                EnterpriseParentTongQuan.ChiYear1_PROPERTY, "Chi",
                EnterpriseParentTongQuan.LaiYear1_PROPERTY, "Lãi(lỗ)",
           
                EnterpriseParentTongQuan.ThuAll_PROPERTY, "Thu",
                EnterpriseParentTongQuan.ChiAll_PROPERTY, "Chi",
                EnterpriseParentTongQuan.LaiAll_PROPERTY, "Lãi(lỗ)");

        ov.getOutline().setRootVisible(true);
        ov.getOutline().setRowHeight(25);
        ov.getOutline().setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
         ov.getOutline().getColumnModel().getColumn(0).setPreferredWidth(400);
        //JOptionPane.showMessageDialog(null,  ov.getOutline().getColumnModel().getColumn(1).getPreferredWidth());

        panelTree.add(ov, BorderLayout.CENTER);
       // setLocationRelativeTo(null); 

        explorerManager.setRootContext(rootNode);
        explorerManager.getRootContext().setDisplayName("Enterprise");
        

        String[] header = {"Tree Enterprise", month1, year2, "All"};
        model = new DefaultTableModel(header, 0);
        tableTitle.setModel(model);

        // Tạo title cho table Outline
        tableTitle.getColumnModel().getColumn(0).setPreferredWidth(350);
       
        tableTitle.getColumnModel().getColumn(1).setPreferredWidth(225);
        tableTitle.getColumnModel().getColumn(2).setPreferredWidth(225);
        tableTitle.getColumnModel().getColumn(3).setPreferredWidth(225);
 
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
        tableTitle = new javax.swing.JTable();
        panelTree = new javax.swing.JPanel();

        tableTitle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tree Enterprise", "12/2011", "11/2011", "10/2011", "2011", "2010", "All"
            }
        ));
        jScrollPane1.setViewportView(tableTitle);

        javax.swing.GroupLayout panelTreeLayout = new javax.swing.GroupLayout(panelTree);
        panelTree.setLayout(panelTreeLayout);
        panelTreeLayout.setHorizontalGroup(
            panelTreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        panelTreeLayout.setVerticalGroup(
            panelTreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 267, Short.MAX_VALUE)
        );

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
    public ExplorerManager getExplorerManager() {
      return explorerManager;
    }
}
