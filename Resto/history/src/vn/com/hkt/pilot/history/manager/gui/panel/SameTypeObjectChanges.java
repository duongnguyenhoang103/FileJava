/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * HistoryOfObjectType.java
 *
 * Created on Feb 22, 2012, 8:23:55 AM
 */
package vn.com.hkt.pilot.history.manager.gui.panel;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.table.AbstractTableModel;
import org.openide.util.Exceptions;
import vn.com.hkt.authenticate.manager.tools.AuthenticateManager;
import vn.com.hkt.history.Installer;
import vn.com.hkt.history.apidao.IModificationDAO;
import vn.com.hkt.history.apidao.IReferenceDAO;
import vn.com.hkt.history.entities.Modification;
import vn.com.hkt.history.entities.Reference;
import vn.com.hkt.history.spidao.ModificationDAO;
import vn.com.hkt.history.spidao.ReferenceDAO;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.identity.presentation.api.IUserInterface;

/**
 *
 * @author BinLe
 */
public class SameTypeObjectChanges extends javax.swing.JPanel implements IUserInterface {

    private DateFormat df = new SimpleDateFormat("dd/MM/yyyy  hh:mm:ss");
    private IEntity entityType;
    private Field[] fields;
    private boolean[] isFields;
    private List<String> listFieldsChoise;
    private List<Integer> listIndexFieldsChoise;
    private IReferenceDAO referenceDAO = new ReferenceDAO();
    private IModificationDAO modifcationDAO = new ModificationDAO();
    private List<MyHistory> myHistorys;
    private List<String> header;
    private List<List<String>> data, data1;
    private int numberIsField = 0;
    private Date dateStart, dateEnd;
    private String workSearch;
    private Date dateChoise;
    private JScrollPane scPane;

    public void setScPane(JScrollPane scPane) {
        this.scPane = scPane;
    }

    /**
     * Creates new form HistoryOfObjectType
     */
    public SameTypeObjectChanges(IEntity entityType) {
        initComponents();
        this.entityType = entityType;
        checkFields();
        loadCombobox();
        loadHistory();
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        cbChoiseFieldSearch = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dcStart = new com.toedter.calendar.JDateChooser();
        dcEnd = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText(org.openide.util.NbBundle.getMessage(SameTypeObjectChanges.class, "SameTypeObjectChanges.jLabel1.text")); // NOI18N

        txtSearch.setText(org.openide.util.NbBundle.getMessage(SameTypeObjectChanges.class, "SameTypeObjectChanges.txtSearch.text")); // NOI18N
        txtSearch.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchCaretUpdate(evt);
            }
        });

        cbChoiseFieldSearch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbChoiseFieldSearch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbChoiseFieldSearchItemStateChanged(evt);
            }
        });

        jLabel2.setText(org.openide.util.NbBundle.getMessage(SameTypeObjectChanges.class, "SameTypeObjectChanges.jLabel2.text")); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText(org.openide.util.NbBundle.getMessage(SameTypeObjectChanges.class, "SameTypeObjectChanges.jLabel3.text")); // NOI18N

        dcStart.setDateFormatString(org.openide.util.NbBundle.getMessage(SameTypeObjectChanges.class, "SameTypeObjectChanges.dcStart.dateFormatString")); // NOI18N
        dcStart.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dcStartPropertyChange(evt);
            }
        });

        dcEnd.setDateFormatString(org.openide.util.NbBundle.getMessage(SameTypeObjectChanges.class, "SameTypeObjectChanges.dcEnd.dateFormatString")); // NOI18N
        dcEnd.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dcEndPropertyChange(evt);
            }
        });

        jLabel4.setText(org.openide.util.NbBundle.getMessage(SameTypeObjectChanges.class, "SameTypeObjectChanges.jLabel4.text")); // NOI18N

        jLabel5.setText(org.openide.util.NbBundle.getMessage(SameTypeObjectChanges.class, "SameTypeObjectChanges.jLabel5.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcStart, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcEnd, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(cbChoiseFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(259, Short.MAX_VALUE))
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {dcStart, txtSearch});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel2, jLabel5});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel4});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbChoiseFieldSearch, dcEnd});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbChoiseFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(dcEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void cbChoiseFieldSearchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbChoiseFieldSearchItemStateChanged
    search();
}//GEN-LAST:event_cbChoiseFieldSearchItemStateChanged

    private void dcStartPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcStartPropertyChange
        search();
    }//GEN-LAST:event_dcStartPropertyChange

    private void txtSearchCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchCaretUpdate
        search();
    }//GEN-LAST:event_txtSearchCaretUpdate

    private void dcEndPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcEndPropertyChange
        search();
    }//GEN-LAST:event_dcEndPropertyChange

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() < 2) {
            return;
        }
        dateChoise = null;
        try {
            dateChoise = df.parse(data.get(jTable1.getSelectedRow()).get(0));
        } catch (ParseException ex) {
            Exceptions.printStackTrace(ex);
        }
        if (dateChoise != null) {
            scPane.setViewportView(new SameTimeChanges(dateChoise, entityType));
        }
    }//GEN-LAST:event_jTable1MouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbChoiseFieldSearch;
    private com.toedter.calendar.JDateChooser dcEnd;
    private com.toedter.calendar.JDateChooser dcStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

    private void checkFields() {
        fields = entityType.getClass().getDeclaredFields();
        listFieldsChoise = new ArrayList<String>();
        listIndexFieldsChoise = new ArrayList<Integer>();
        isFields = new boolean[fields.length];
        numberIsField = 0;
        for (int i = 0; i < fields.length; i++) {
            if (Modifier.isFinal(fields[i].getModifiers())
                    || Modifier.isStatic(fields[i].getModifiers())
                    || fields[i].getName().equals("id")
                    || fields[i].getType().getCanonicalName().equals(List.class.getCanonicalName())) {
                isFields[i] = false;
            } else {
                isFields[i] = true;
                numberIsField++;
                listFieldsChoise.add(entityType.getDescriptionOfField(fields[i].getName()));
                listIndexFieldsChoise.add(i);
            }
        }
    }

    private void loadCombobox() {
        DefaultComboBoxModel model = new DefaultComboBoxModel(listFieldsChoise.toArray());
        cbChoiseFieldSearch.setModel(model);
    }

    private void initData() {
        header = new ArrayList<String>();
        data = new ArrayList<List<String>>();
        header.add("Thời gian");
        header.add("Account");
        for (int i = 0; i < fields.length; i++) {
            if (isFields[i]) {
                header.add(entityType.getDescriptionOfField(fields[i].getName()));
            }
        }
        int i = 0;
        while (i < myHistorys.size()) {
            List<String> row = new ArrayList<String>();

            row.add(df.format(myHistorys.get(i).getDateStart()));
            row.add(myHistorys.get(i).getUsername());
            int k = i;
            for (int j = 0; j < fields.length; j++) {
                if (isFields[j]) {
                    k = i;
                    boolean had = false;
                    while (true) {
                        if (k < myHistorys.size()
                                && myHistorys.get(k).getDateStart().equals(myHistorys.get(i).getDateStart())) {
                            if (myHistorys.get(k).getField().equals(fields[j].getName())) {
                                had = true;
                                row.add(entityType.getDataRealyOfField(fields[j].getName(), myHistorys.get(k).getData()));
                                break;
                            }
                            k++;
                        } else {
                            break;
                        }
                    }
                    if (!had) {
                        if (i == 0) {
                            row.add("");
                        } else {
                            row.add(data.get(data.size() - 1).get(row.size()));
                        }
                    }
                }
            }
            i = k + 1;
            data.add(row);
        }
    }

    private void sortMyHistory() {
        if (myHistorys == null) {
            return;
        }
        for (int i = 0; i < myHistorys.size(); i++) {
            for (int j = i + 1; j < myHistorys.size(); j++) {
                if (myHistorys.get(i).getDateStart().after(myHistorys.get(j).getDateStart())) {
                    MyHistory tg = myHistorys.get(i);
                    myHistorys.set(i, myHistorys.get(j));
                    myHistorys.set(j, tg);
                }
            }
        }
    }

    private void loadHistory() {
        myHistorys = new ArrayList<MyHistory>();
        List<Reference> references = referenceDAO.select(Reference.FIELD_OBJECT_TYPE, entityType.getEntityName(), IReferenceDAO.ASC, Reference.FIELD_OBJECT_ID_ACTUAL);
        if (references == null) {
            return;
        }
        for (int i = 0; i < references.size(); i++) {
            List<Integer> modifications = references.get(i).getModificationsIdActual();
            if (modifications == null) {
                break;
            }
            for (int j = 0; j < modifications.size(); j++) {
                Modification modification = new ModificationDAO().getById(modifications.get(j));
                MyHistory mh = new MyHistory(references.get(i), modification);
                myHistorys.add(mh);
            }
        }
        sortMyHistory();
        initData();
        data1 = data;
        loadTable();
    }

    private void loadTable() {
        jTable1.setModel(new MyTableModel(header, data1));
    }

    public boolean checkDate(Date date, Date dateStart, Date dateEnd) {
        if (dateStart == null && dateEnd == null) {
            return true;
        } else if (dateStart == null && date != null && dateEnd != null
                && (date.before(dateEnd) || date.equals(dateEnd))) {
            return true;
        } else if (dateEnd == null && date != null && dateStart != null
                && (date.after(dateStart) || date.equals(dateStart))) {
            return true;
        } else if (dateStart != null && dateEnd != null && date != null) {
            if ((date.equals(dateStart) || date.after(dateStart))
                    && (date.before(dateEnd) || date.equals(dateEnd))) {
                return true;
            }
        }
        return false;
    }

    private void search() {
        data1 = new ArrayList<List<String>>();
        workSearch = txtSearch.getText();
        dateStart = dcStart.getDate();
        dateEnd = dcEnd.getDate();
        String fieldSearch = (String) cbChoiseFieldSearch.getSelectedItem();
        if (workSearch.isEmpty()) {
            for (int i = 0; i < data.size(); i++) {
                Date date = null;
                try {
                    date = df.parse(data.get(i).get(0));
                } catch (ParseException ex) {
                    Exceptions.printStackTrace(ex);
                }
                if (checkDate(date, dateStart, dateEnd)) {
                    data1.add(data.get(i));
                }
            }
        } else {
            for (int i = 0; i < data.size(); i++) {
                Date date = null;
                try {
                    date = df.parse(data.get(i).get(0));
                } catch (ParseException ex) {
                    Exceptions.printStackTrace(ex);
                }
                if (checkDate(date, dateStart, dateEnd)) {

                    for (int j = 0; j < header.size(); j++) {
                        if (header.get(j).equals(fieldSearch)) {
                            if (data.get(i).get(j).contains(workSearch)) {
                                data1.add(data.get(i));
                                break;
                            }
                        }
                    }

                }
            }
        }
        jTable1.setModel(new MyTableModel(header, data1));
    }

    @Override
    public String getUserInterfaceName() {
        return getClass().getSimpleName();
    }

    @Override
    public String getUserInterfaceDescription() {
        return "Giao diện theo dõi lịch sử thay đổi cùng 1 đối tượng";
    }

    @Override
    public String getModuleName() {
        return Installer.MODULE_NAME;
    }

    private class MyHistory {

        private String username;
        private String objectType;
        private int idObject;
        private String field;
        private String data;
        private Date dateStart;
        private Date dateEnd;
        private String description;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public Date getDateEnd() {
            return dateEnd;
        }

        public void setDateEnd(Date dateEnd) {
            this.dateEnd = dateEnd;
        }

        public Date getDateStart() {
            return dateStart;
        }

        public void setDateStart(Date dateStart) {
            this.dateStart = dateStart;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public int getIdObject() {
            return idObject;
        }

        public void setIdObject(int idObject) {
            this.idObject = idObject;
        }

        public String getObjectType() {
            return objectType;
        }

        public void setObjectType(String objectType) {
            this.objectType = objectType;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public MyHistory(Reference re, Modification mo) {
            username = AuthenticateManager.getAuthenticateManager().getUsernameByAccountId(re.getAccountIdActual());
            objectType = re.getObjectType();
            idObject = re.getObjectIdActual();
            field = re.getFieldName();
            data = mo.getData();
            dateStart = mo.getDateStart();
            dateEnd = mo.getDateEnd();
            description = mo.getDescription();
        }
    }

    class MyTableModel extends AbstractTableModel {

        private List<String> header;
        private List<List<String>> data;

        public MyTableModel(List<String> header, List<List<String>> data) {
            this.header = header;
            this.data = data;
        }

        @Override
        public String getColumnName(int column) {
            return header.get(column);
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return header.size();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (data == null) {
                return "";
            }
            if (data.get(rowIndex) == null) {
                return "";
            }
            if (data.get(rowIndex).get(columnIndex) == null) {
                return "";
            }
            return data.get(rowIndex).get(columnIndex);
        }
    }
}
