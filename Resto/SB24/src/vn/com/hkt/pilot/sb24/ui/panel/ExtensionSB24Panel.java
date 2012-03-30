/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ExtensionEnterprisePanel.java
 *
 * Created on Dec 21, 2011, 4:35:59 PM
 */
package vn.com.hkt.pilot.sb24.ui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.api.IForeignLanguageBN;
import vn.com.hkt.basic.api.IPersonBN;
import vn.com.hkt.basic.api.ISystemSotfwareBN;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnableTable;
import vn.com.hkt.pilot.enterprise.viewer.api.IGetObject;
import vn.com.hkt.pilot.entities.ForeignLanguage;
import vn.com.hkt.pilot.entities.Person;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.identity.presentation.api.IUserInterface;
import vn.com.hkt.pilot.person.viewer.api.HelpTutorialPerson;
import vn.com.hkt.pilot.person.viewer.api.IPersonExtCreater;
import vn.com.hkt.pilot.sb24.Installer;
import vn.com.hkt.pilot.sb24.dao.LevelLanguageBN;
import vn.com.hkt.pilot.sb24.dao.SkillBN;
import vn.com.hkt.pilot.sb24.dao.SkillLanguageBN;
import vn.com.hkt.pilot.sb24.entity.LevelLanguage;
import vn.com.hkt.pilot.sb24.entity.SkillLanguage;
import vn.com.hkt.pilot.sb24.entity.Skill;
import vn.com.hkt.pilot.toobar.api.IResetFontSize;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;
import vn.com.hkt.ui.main.ui.api.creater.ISaveExtention;

/**
 *
 * @author longnt
 */
@ServiceProvider(service = IPersonExtCreater.class)
public class ExtensionSB24Panel extends javax.swing.JPanel implements IPersonExtCreater,
        ISaveExtention, IEnableTable, IResetFontSize, IUserInterface, IGetObject {

    private int size;
    private String font;
    private ISystemSotfwareBN sotfwareBN = Lookup.getDefault().lookup(ISystemSotfwareBN.class);
    private List<SystemSoftware> listS = new ArrayList<SystemSoftware>();
    private int i = 4;
    private DefaultTableModel modelDiaChi;
    private SkillBN skillDAO = new SkillBN();
    private int idPerson = 0;
    private ExtensionSB24Cell sB24Cell;

    /**
     * Creates new form ExtensionEnterprisePanel
     */
    public ExtensionSB24Panel() {
        initComponents();
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        sB24Cell = new ExtensionSB24Cell(i);
        tableExtensionPerson.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableExtensionPerson.getColumnModel().getColumn(0).setMaxWidth(100);
        tableExtensionPerson.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableExtensionPerson.getColumnModel().getColumn(2).setMaxWidth(100);
        tableExtensionPerson.setRowSelectionAllowed(true);
        tableExtensionPerson.setColumnSelectionAllowed(false);
        tableExtensionPerson.setSelectionBackground(new Color(192, 210, 224));
        tableExtensionPerson.setForeground(color);
        StripedTableCellRenderer.installInColumn(tableExtensionPerson, colorL, null, colorD, null);
        tableExtensionPerson.setTableHeader(null);

        tableTA.setTableHeader(null);
        tableTA.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableTA.getColumnModel().getColumn(0).setMaxWidth(100);
        tableTA.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableTA.getColumnModel().getColumn(2).setMaxWidth(100);
        tableTA.getColumnModel().getColumn(1).setCellEditor(sB24Cell);
        tableTA.getColumnModel().getColumn(2).setCellEditor(sB24Cell);
        tableTA.setRowSelectionAllowed(true);
        tableTA.setColumnSelectionAllowed(false);
        tableTA.setSelectionBackground(new Color(192, 210, 224));
        tableTA.setForeground(color);
        StripedTableCellRenderer.installInColumn(tableTA, colorL, null, colorD, null);

        jScrollPane1.setViewportBorder(null);

        panelTong.removeAll();
        panelTong.setLayout(new BorderLayout());
        panelTong.add(tableExtensionPerson, BorderLayout.NORTH);
        panelTong.add(jScrollPane2, BorderLayout.CENTER);
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(jPanel1, BorderLayout.CENTER);
        JLabel label = new JLabel("          Kỹ năng, Ngôn ngữ, Chuyên ngành");
        label.setFont((new Font(" ", Font.BOLD, 12)));
        label.setPreferredSize(new Dimension(WIDTH, 30));
        this.add(label, BorderLayout.NORTH);

        tableTA.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableMousePressed(evt);
            }
        });
        tableExtensionPerson.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableMousePressedE(evt);
            }
        });
    }

    public ExtensionSB24Cell getsB24Cell() {
        return sB24Cell;
    }

    @Override
    public String toString() {
        return "Kỹ năng, Ngôn ngữ, Chuyên ngành";
    }

    public JTable getTableExtensionE() {
        return this.tableExtensionPerson;
    }

    public JTable getTableTA() {
        return this.tableTA;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelTong = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableExtensionPerson = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTA = new javax.swing.JTable();

        panelTong.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelTong.setPreferredSize(new java.awt.Dimension(827, 164));

        tableExtensionPerson.setBackground(new java.awt.Color(242, 241, 240));
        tableExtensionPerson.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{
                {"Đặc tính", " ", "Sở thích cá nhân", " "},
                {"Sở thích nghề nghiệp", " ", "Sở thích nghiên cứu", " "},
                {"Tin học", " ", " ", " "}
            },
            new String[]{
                "", "", "", ""
            }) {

                boolean[] canEdit = new boolean[]{
                    false, true, false, true
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    if (rowIndex ==2)
                    if (columnIndex == 2 || columnIndex == 3) return false;
                    return canEdit[columnIndex];
                }
            });
            tableExtensionPerson.setIntercellSpacing(new java.awt.Dimension(0, 0));
            tableExtensionPerson.setRowHeight(26);
            tableExtensionPerson.setShowHorizontalLines(false);
            tableExtensionPerson.setShowVerticalLines(false);
            jScrollPane1.setViewportView(tableExtensionPerson);

            jScrollPane2.setMinimumSize(new java.awt.Dimension(106, 106));
            jScrollPane2.setPreferredSize(new java.awt.Dimension(106, 106));

            tableTA.setBackground(new java.awt.Color(242, 241, 240));
            tableTA.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {"Ngoại ngữ 1", " ", " "},
                    {"                2", " ", " " },
                    {"                3", " ", " "},
                    {"                4", " ", " "}
                },
                new String[]{
                    "", "", ""
                }) {

                    boolean[] canEdit = new boolean[]{
                        false, true, true
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                });
                tableTA.setIntercellSpacing(new java.awt.Dimension(0, 0));
                tableTA.setRowHeight(26);
                tableTA.setShowHorizontalLines(false);
                tableTA.setShowVerticalLines(false);
                jScrollPane2.setViewportView(tableTA);

                javax.swing.GroupLayout panelTongLayout = new javax.swing.GroupLayout(panelTong);
                panelTong.setLayout(panelTongLayout);
                panelTongLayout.setHorizontalGroup(
                    panelTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                );
                panelTongLayout.setVerticalGroup(
                    panelTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTongLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelTong, javax.swing.GroupLayout.DEFAULT_SIZE, 833, Short.MAX_VALUE)
                        .addContainerGap())
                );
                jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelTong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(358, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
            }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelTong;
    private javax.swing.JTable tableExtensionPerson;
    private javax.swing.JTable tableTA;
    // End of variables declaration//GEN-END:variables

    @Override
    public JPanel getPersonExtCreater() {
        return this;
    }

    @Override
    public Lookup getPersonExtLookup() {
        return null;
    }

    @Override
    public double getLevelNumber() {
        sB24Cell.loadCbo(i);
        this.getsB24Cell().loadCbo(i);
        return 2.4;
    }
    // Liên thông font cỡ chữ, màu

    @Override
    public void resetFont() {
        listS = sotfwareBN.selectAll();
        size = tableTA.getFont().getSize();
        font = listS.get(0).getFont().getFontName();
        tableTA.setFont(new Font(font, 0, size));
        tableExtensionPerson.setFont(new Font(font, 0, size));
    }

    @Override
    public void resetColorRowTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(tableTA, colorL, null, colorD, null);
        StripedTableCellRenderer.installInColumn(tableExtensionPerson, colorL, null, colorD, null);
        tableTA.repaint();
        tableExtensionPerson.repaint();
    }

    @Override
    public void resetSize() {
        listS = sotfwareBN.selectAll();
        font = tableTA.getFont().getFontName();
        size = listS.get(0).getSizeWord();
        tableTA.setFont(new Font(font, 0, size));
        tableExtensionPerson.setFont(new Font(font, 0, size));

    }

    @Override
    public void resetColorWord() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        tableTA.setForeground(color);
        tableExtensionPerson.setForeground(color);
        tableTA.repaint();
        tableExtensionPerson.repaint();
    }

    @Override
    public void resetColorTitle() {
//        listS = sotfwareBN.selectAll();
//        Color color = new Color(listS.get(0).getColorTitle().getRed(), listS.get(0).getColorTitle().getGreen(), listS.get(0).getColorTitle().getBlue());
//        tableTA.getTableHeader().setForeground(color);
//        tableExtensionPerson.getTableHeader().setForeground(color);
//        tableTA.repaint();
//        tableExtensionPerson.repaint();
    }

    @Override
    public void resetColorMouse() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorMouseClick().getRed(), listS.get(0).getColorMouseClick().getGreen(), listS.get(0).getColorMouseClick().getBlue());
        tableTA.setSelectionBackground(color);
        tableExtensionPerson.setSelectionBackground(color);
        tableTA.repaint();
        tableExtensionPerson.repaint();
    }

    private void tableMousePressed(MouseEvent evt) {
        if (tableTA.getSelectedRow() == i) {
            modelDiaChi = (DefaultTableModel) tableTA.getModel();
            i++;
            Object[] rows1 = {" ", " ", " "};
            modelDiaChi.addRow(rows1);
            tableTA.repaint();
        }
        if (tableTA.getSelectedColumn() == 1) {
            addFormEditID(2.06, " ");
        }
        if (tableTA.getSelectedColumn() == 2) {
            addFormEditID(7, " ");
        }
    }

    @Override
    public void reset() {
        sB24Cell.reset();
        idPerson = 0;
        tableExtensionPerson.setValueAt("", 0, 1);
        tableExtensionPerson.setValueAt("", 1, 1);
        tableExtensionPerson.setValueAt("", 2, 1);

        tableExtensionPerson.setValueAt("", 0, 3);
        tableExtensionPerson.setValueAt("", 1, 3);

        for (int j = 0; j < i; j++) {
            tableTA.setValueAt("", j, 1);
            tableTA.setValueAt("", j, 2);
        }
        i = 4;
    }

    private void tableMousePressedE(MouseEvent evt) {
        SB24Tutorial sB24Tutorial = new SB24Tutorial();
        int i = 0, j = 1;
        double k = 0;
        String[] str1 = {sB24Tutorial.getParticularity().getText(), sB24Tutorial.getCareerInterests().getText(), sB24Tutorial.getInformatics().getText()};
        String[] str2 = {sB24Tutorial.getPersonalPreferences().getText(), sB24Tutorial.getResearchInterests().getText(), ""};
        for (i = 0; i < str1.length; i++) {
            if (tableExtensionPerson.getSelectedRow() == i && tableExtensionPerson.getSelectedColumn() == 1) {
                k = (double) (3 + 0.1 * i + 0.01 * 1);
                k = Math.round(k * 1000) * 1.0 / 1000;

                addFormEditID(k, str1[i]);
            }
        }
        for (i = 0; i < str2.length; i++) {
            if (tableExtensionPerson.getSelectedRow() == i && tableExtensionPerson.getSelectedColumn() == 3) {
                k = (double) (3 + 0.1 * i + 0.01 * 3);
                k = Math.round(k * 1000) * 1.0 / 1000;
                addFormEditID(k, str2[i]);
            }
        }
        this.tableExtensionPerson.clearSelection();
    }

    private void addFormEditID(double k, String str) {
        Collection<? extends HelpTutorialPerson> allSave = Lookup.getDefault().lookupAll(HelpTutorialPerson.class);
        for (HelpTutorialPerson editCookie : allSave) {
            editCookie.getTutorial(k, str);
        }
    }

    @Override
    public String getUserInterfaceName() {
        return getClass().getSimpleName();
    }

    @Override
    public String getUserInterfaceDescription() {
        return "Giao diện chi tiết đặc điểm học thức cá nhân";
    }

    @Override
    public String getModuleName() {
        return Installer.MODULE_NAME;
    }

    @Override
    public void getObject(String id) {
        IPersonBN personBN = Lookup.getDefault().lookup(IPersonBN.class);
        SkillLanguageBN skillLanguageBN = new SkillLanguageBN();
        IForeignLanguageBN foreignLanguageBN = Lookup.getDefault().lookup(IForeignLanguageBN.class);
        LevelLanguageBN levelLanguageBN = new LevelLanguageBN();
        Person person = personBN.getByObjectId(id);
        if (person != null) {
            List<Skill> list = skillDAO.select(Skill.FIELD_PERSON_ID_ACTUAL, String.valueOf(person.getId()));
            Skill skill = list.get(0);
            tableExtensionPerson.setValueAt(skill.getParticularity(), 0, 1);
            tableExtensionPerson.setValueAt(skill.getPersonalPreferences(), 0, 3);
            tableExtensionPerson.setValueAt(skill.getCareerInterests(), 1, 1);
            tableExtensionPerson.setValueAt(skill.getResearchInterests(), 1, 3);
            tableExtensionPerson.setValueAt(skill.getInformatics(), 2, 1);

            List<Integer> skillLanguagesIdActual = new ArrayList<Integer>();
            skillLanguagesIdActual = skill.getSkillLanguagesIdActual();

            if (skillLanguagesIdActual != null && skillLanguagesIdActual.size() <= i) {
                for (int j = 0; j < skillLanguagesIdActual.size(); j++) {
                    SkillLanguage skillLanguage = skillLanguageBN.getById(skillLanguagesIdActual.get(j));
                    ForeignLanguage language = foreignLanguageBN.getById(skillLanguage.getForeignLanguageId());
                    LevelLanguage levelLanguage = levelLanguageBN.getById(skillLanguage.getLevelLanguageIdActual());
                    tableTA.setValueAt(language, j, 1);
                    tableTA.setValueAt(levelLanguage, j, 2);
                }

            }

            if (skillLanguagesIdActual.size() > i) {
                for (int j = 0; j < skillLanguagesIdActual.size(); j++) {
                    SkillLanguage skillLanguage = skillLanguageBN.getById(skillLanguagesIdActual.get(j));
                    ForeignLanguage language = foreignLanguageBN.getById(skillLanguage.getForeignLanguageId());
                    LevelLanguage levelLanguage = levelLanguageBN.getById(skillLanguage.getLevelLanguageIdActual());
                    tableTA.setValueAt(language, j, 1);
                    tableTA.setValueAt(levelLanguage, j, 2);
                }
                for (int j = i; j < skillLanguagesIdActual.size(); j++) {
                    DefaultTableModel model = (DefaultTableModel) tableTA.getModel();
                    SkillLanguage skillLanguage = skillLanguageBN.getById(skillLanguagesIdActual.get(j));
                    ForeignLanguage language = foreignLanguageBN.getById(skillLanguage.getForeignLanguageId());
                    LevelLanguage levelLanguage = levelLanguageBN.getById(skillLanguage.getLevelLanguageIdActual());
                    Object object[] = {language, levelLanguage};
                    model.addRow(object);
                }

            }
        }
    }

    @Override
    public void enableTable(boolean ok) throws IOException {
        tableExtensionPerson.setEnabled(ok);
        tableTA.setEnabled(ok);
    }

    @Override
    public List<JTable> getTables() {
        List<JTable> lt = new ArrayList<JTable>();
        lt.add(tableExtensionPerson);
        lt.add(tableTA);
        return lt;
    }

    @Override
    public void setEntity(IEntity entity) {
        idPerson = entity.getId();
    }

    @Override
    public IEntity save() {
        SkillLanguageBN skillLanguageBN = new SkillLanguageBN();
        if (idPerson == 0) {
            return null;
        }

        List<Integer> skillLanguagesIdActual = new ArrayList<Integer>();
        Skill beanSkill = skillDAO.getByObjectId(String.valueOf(idPerson));
        if (beanSkill == null) {
            beanSkill = new Skill();
        }

        beanSkill.setPersonIdActual(idPerson);
        beanSkill.setParticularity(tableExtensionPerson.getValueAt(0, 1).toString());
        beanSkill.setPersonalPreferences(tableExtensionPerson.getValueAt(0, 3).toString());
        beanSkill.setCareerInterests(tableExtensionPerson.getValueAt(1, 1).toString());
        beanSkill.setResearchInterests(tableExtensionPerson.getValueAt(1, 3).toString());
        beanSkill.setInformatics(tableExtensionPerson.getValueAt(2, 1).toString());
        for (int j = 0; j < tableTA.getRowCount(); j++) {

            if (tableTA.getValueAt(j, 1) != null && tableTA.getValueAt(j, 1).toString().trim().length() > 0) {
                SkillLanguage beanLanguage = new SkillLanguage();
                ForeignLanguage foreignLanguage = (ForeignLanguage) tableTA.getValueAt(j, 1);
                beanLanguage.setForeignLanguageId(foreignLanguage.getId());
                if (tableTA.getValueAt(j, 2) != null && tableTA.getValueAt(j, 2).toString().trim().length() > 0) {

                    LevelLanguage levelLanguage = (LevelLanguage) tableTA.getValueAt(j, 2);
                    beanLanguage.setLevelLanguageIdActual(levelLanguage.getId());
                } else {
                    beanLanguage.setLevelLanguageIdActual(0);
                }
                skillLanguageBN.insert(beanLanguage);
                skillLanguagesIdActual.add(beanLanguage.getId());
            }

        }
        beanSkill.setSkillLanguagesIdActual(skillLanguagesIdActual);
        skillDAO.update(beanSkill);
        return beanSkill;

    }
}