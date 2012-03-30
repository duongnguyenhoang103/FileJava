/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.history.manager;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.openide.util.Exceptions;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.authenticate.manager.tools.AuthenticateManager;
import vn.com.hkt.history.apidao.IModificationDAO;
import vn.com.hkt.history.apidao.IReferenceDAO;
import vn.com.hkt.history.entities.Modification;
import vn.com.hkt.history.entities.Reference;
import vn.com.hkt.history.spidao.ModificationDAO;
import vn.com.hkt.history.spidao.ReferenceDAO;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.identity.history.api.IHistoryManager;

/**
 *
 * @author Admin
 */
@ServiceProvider(service = IHistoryManager.class)
public class HistoryManager implements IHistoryManager {

    private IReferenceDAO rdao = new ReferenceDAO();
    private IModificationDAO mdao = new ModificationDAO();

    private boolean checkEntity(IEntity e) {
        if (e.getEntityName().equals(new Modification().getEntityName())
                || e.getEntityName().equals(new Reference().getEntityName())) {
            return false;
        }
        return true;
    }

    private boolean[] getFieldHasInformaton(IEntity e, Field[] fields) {
        if (fields == null) {
            return null;
        }
        boolean isFields[] = new boolean[fields.length];
        for (int i = 0; i < fields.length; i++) {
            if (Modifier.isFinal(fields[i].getModifiers()) || Modifier.isStatic(fields[i].getModifiers())) {
                isFields[i] = false;
            } else {
                if (fields[i].getType().isArray()) {
                    try {
                        fields[i].setAccessible(true);
                        byte[] bytes = (byte[]) fields[i].get(e);
                        isFields[i] = true;
                    } catch (Exception ex) {
                        isFields[i] = false;
                    }
                } else {
                    isFields[i] = true;
                }
            }
        }
        return isFields;
    }

    private String getStringofObject(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj.getClass().isArray()) {
            byte[] data = (byte[]) obj;
            return new String(data);
        }
        return String.valueOf(obj);
    }

    @Override
    public void checkInsertHistory(IEntity e) {
        if (!checkEntity(e)) {
            return;
        }
        Date date = Calendar.getInstance().getTime();
        Field[] fields = e.getClass().getDeclaredFields();
        boolean[] isFields = getFieldHasInformaton(e, fields);
        for (int i = 0; i < fields.length; i++) {
            if (isFields[i]) {
                fields[i].setAccessible(true);
                try {
                    String modulename = e.getModuleOfEntity();
                    String objecType = e.getEntityName();
                    int objectId = e.getId();
                    int accountId = AuthenticateManager.getAuthenticateManager().getIdOfAccountCurrent();
                    String fieldName = fields[i].getName();
                    Reference reference = new Reference(modulename, objecType, objectId, accountId, fieldName, null);
                    String data = getStringofObject(fields[i].get(e));
                    List<Modification> list = new ArrayList<Modification>();
                    if (data != null) {
                        list = new ArrayList<Modification>();
                        Modification modification = new Modification(data, date, null, "New Data");
                        if (!mdao.insert(modification)) {
                            //TODO 
                            return;
                        }
                        list.add(modification);
                    }
                    List<Integer> li = new ArrayList<Integer>();
                    for (int j = 0; j < list.size(); j++) {
                        li.add(list.get(j).getId());
                    }
                    reference.setModificationsIdActual(li);
                    if (!rdao.insert(reference)) {
                        //TODO
                        return;
                    }
                } catch (Exception ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }

    private Modification getModification(List<Integer> list, String data) {
        if (list == null) {
            return null;
        }
        for (int i = 0; i < list.size(); i++) {
            Modification mo = new ModificationDAO().getById(list.get(i));
            if (mo != null && mo.getData().equals(data) && mo.getDateEnd() == null) {
                return mo;
            }
        }
        return null;
    }

    @Override
    public void checkUpdateHistory(IEntity e) {
        if (!checkEntity(e)) {
            return;
        }
        Date date = Calendar.getInstance().getTime();
        IEntity eold = (IEntity) e.getAccessDataOfEntity().getById(e.getId());
        Field[] fields = e.getClass().getDeclaredFields();
        boolean[] isFields = getFieldHasInformaton(e, fields);
        for (int i = 0; i < fields.length; i++) {
            if (isFields[i]) {
                fields[i].setAccessible(true);
                try {
                    String dataOld = getStringofObject(fields[i].get(eold));
                    String dataNew = getStringofObject(fields[i].get(e));
                    if (!dataOld.equals(dataNew)) {
                        String modulename = e.getModuleOfEntity();
                        String objecType = e.getEntityName();
                        int objectId = e.getId();
                        int accountId = AuthenticateManager.getAuthenticateManager().getIdOfAccountCurrent();
                        String fieldName = fields[i].getName();
                        Reference re = rdao.getReference(modulename, objecType, objectId, accountId, fieldName);
                        if (re == null) {
                            re = new Reference(modulename, objecType, objectId, accountId, fieldName, null);
                            if (!rdao.insert(re)) {
//                                JOptionPane.showMessageDialog(null, "Không lưu được lịch sử thay đổi");
                                return;
                            }
                        }
                        Modification modification = getModification(re.getModificationsIdActual(), dataOld);
                        if (modification != null) {
                            modification.setDateEnd(date);
                            modification.setDescription(modification.getDescription() + " - updated");
                            if (!mdao.update(modification)) {
                                //TODO cập nhật thông tin sửa đổi cũ
                                return;
                            }
                        }
                        modification = new Modification(dataNew, date, null, "new data");
                        if (!mdao.insert(modification)) {
                            //TODO cập nhật thông tin sửa đổi mới
                        }
                        List<Integer> li = re.getModificationsIdActual();
                        if (li == null) {
                            li = new ArrayList<Integer>();
                        }
                        li.add(modification.getId());
                        re.setModificationsIdActual(li);
                        if (!rdao.update(re)) {
                            //TODO
                            return;
                        }
                    }
                } catch (Exception ex) {
                    //TODO
                }
            }
        }
    }

    @Override
    public void checkDeleteHistory(IEntity e) {
        if (!checkEntity(e)) {
            return;
        }
        Date date = Calendar.getInstance().getTime();
        Field[] fields = e.getClass().getDeclaredFields();
        boolean[] isFields = getFieldHasInformaton(e, fields);
        for (int i = 0; i < fields.length; i++) {
            if (isFields[i]) {
                fields[i].setAccessible(true);
                try {
                    String modulename = e.getModuleOfEntity();
                    String objecType = e.getEntityName();
                    int objectId = e.getId();
                    int accountId = AuthenticateManager.getAuthenticateManager().getIdOfAccountCurrent();
                    String fieldName = fields[i].getName();
                    Reference re = rdao.getReference(modulename, objecType, objectId, accountId, fieldName);
                    if (re == null) {
                        re = new Reference(modulename, objecType, objectId, accountId, fieldName, null);
                        if (!rdao.insert(re)) {
//                            JOptionPane.showMessageDialog(null, "Không lưu được lịch sử thay đổi");
                            return;
                        }
                    }
                    String dataOld = getStringofObject(fields[i].get(e));
                    Modification modification = getModification(re.getModificationsIdActual(), dataOld);
                    if (modification != null) {
                        modification.setDateEnd(date);
                        modification.setDescription(modification.getDescription() + " - deleted");
                        if (!mdao.update(modification)) {
                            //TODO cập nhật thông tin sửa đổi cũ
                            return;
                        }
                    } else {
                        modification = new Modification(dataOld, date, date, "delete");
                        if (!mdao.insert(modification)) {
                            //TODO
                            return;
                        }
                        List<Integer> list = re.getModificationsIdActual();
                        if (list == null) {
                            list = new ArrayList<Integer>();
                        }
                        list.add(modification.getId());
                        re.setModificationsIdActual(list);
                        if (!rdao.update(re)) {
                            //TODO
                            return;
                        }
                    }
                } catch (Exception ex) {
                    //TODO
                }
            }
        }
    }

    @Override
    public void insertHistory(IEntity e) {
        //TODO thừa chưa xóa
    }
}
