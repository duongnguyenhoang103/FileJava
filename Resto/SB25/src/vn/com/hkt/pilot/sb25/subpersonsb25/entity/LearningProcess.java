/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb25.subpersonsb25.entity;

import java.lang.reflect.Field;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.sb25.Installer;
import vn.com.hkt.pilot.sb25.dao.LearningProcessBN;

/**
 *
 * @author khangpn
 */
@Entity
@ServiceProvider(service = IEntity.class)
public class LearningProcess implements IEntity { // Quá trình học tập

    public static final String FIELD_TIME_BEGIN = "timeBegin";
    public static final String FIELD_LEARNINGPROCCESS_ID = "learningProccessId";
    public static final String FIELD_TIME_END = "timeEnd";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_ACADEMIC_TITILE = "academicTitle";
    public static final String FIELD_QUALIFICATION = "qualification";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String learningProccessId;
    @Temporal(TemporalType.DATE)
    private Calendar timeBegin;
    @Temporal(TemporalType.DATE)
    private Calendar timeEnd;
    private String description; // Thông tin
    private String academicTitle;// học hàm
    private String qualification;// học vị

    public LearningProcess(String learningProccessId, Calendar timeBegin,
            Calendar timeEnd, String description, String academicTitle,
            String qualification) {
        this.learningProccessId = learningProccessId;
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
        this.description = description;
        this.academicTitle = academicTitle;
        this.qualification = qualification;
    }

    public LearningProcess() {
    }

    @Override
    public String getEntityName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getModuleOfEntity() {
        return Installer.MODULE_NAME;
    }

    @Override
    public String getEntityDescription() {
        return "Module mở rộng SB25";
    }

    public String getAcademicTitle() {
        return academicTitle;
    }

    public void setAcademicTitle(String academicTitle) {
        this.academicTitle = academicTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLearningProccessId() {
        return learningProccessId;
    }

    public void setLearningProccessId(String learningProccessId) {
        this.learningProccessId = learningProccessId;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(Calendar timeBegin) {
        this.timeBegin = timeBegin;
    }

    public Calendar getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Calendar timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new LearningProcessBN();
    }

    @Override
    public String getFieldNameObjectId() {
        return FIELD_LEARNINGPROCCESS_ID;
    }

    @Override
    public int getId() {
        return id;
    }

   @Override
    public String getDataRealyOfField(String fieldName, String data) {
        return data;
    }

    @Override
    public String getDescriptionOfField(String fieldName) {
        return fieldName;
    }
}
