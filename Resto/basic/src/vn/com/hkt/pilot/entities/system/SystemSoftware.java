/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.entities.system;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import vn.com.hkt.pilot.entities.system.dao.SystemSotfwareDAO;
import vn.com.hkt.pilot.identity.access.api.IAccessData;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 *
 * @author BinLe
 */
@Entity
public class SystemSoftware implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    private MyColor colorLight;//màu nhạt    
    @OneToOne(cascade = CascadeType.ALL)
    private MyColor colorMouseMove;// màu di chuột
    @OneToOne(cascade = CascadeType.ALL)
    private MyColor colorBackground;// màu nền
    @OneToOne(cascade = CascadeType.ALL)
    private MyColor colorDark;// màu tối
    @OneToOne(cascade = CascadeType.ALL)
    private MyColor colorMouseClick;// mày click chuột
    @OneToOne(cascade = CascadeType.ALL)
    private MyColor colorWord;//màu chữ
    @OneToOne(cascade = CascadeType.ALL)
    private MyColor colorTitle;// màu tiêu đề
    private byte[] imageBackground;
    @OneToOne(cascade = CascadeType.ALL)
    private MyFont font;
    private int sizeWord;
    private int withRowTable;
    private int widthColumnTable;
    private boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public SystemSoftware() {
        colorBackground = new MyColor();
        colorDark = new MyColor();
        colorLight = new MyColor();
        colorMouseClick = new MyColor();
        colorMouseMove = new MyColor();
        colorTitle = new MyColor();
        colorWord = new MyColor();
        font = new MyFont();
    }

    public SystemSoftware(MyColor colorLight, MyColor colorMouseMove, MyColor colorBackground, MyColor colorDark, MyColor colorMouseClick, MyColor colorWord, MyColor colorTitle, byte[] imageBackground, MyFont font, int sizeWord, int withRowTable, int widthColumnTable, boolean flag) {
        this.colorLight = colorLight;
        this.colorMouseMove = colorMouseMove;
        this.colorBackground = colorBackground;
        this.colorDark = colorDark;
        this.colorMouseClick = colorMouseClick;
        this.colorWord = colorWord;
        this.colorTitle = colorTitle;
        this.imageBackground = imageBackground;
        this.font = font;
        this.sizeWord = sizeWord;
        this.withRowTable = withRowTable;
        this.widthColumnTable = widthColumnTable;
        this.flag = flag;
    }

    public MyColor getColorBackground() {
        return colorBackground;
    }

    public void setColorBackground(MyColor colorBackground) {
        this.colorBackground = colorBackground;
    }

    public MyColor getColorDark() {
        return colorDark;
    }

    public void setColorDark(MyColor colorDark) {
        this.colorDark = colorDark;
    }

    public MyColor getColorLight() {
        return colorLight;
    }

    public void setColorLight(MyColor colorLight) {
        this.colorLight = colorLight;
    }

    public MyColor getColorMouseClick() {
        return colorMouseClick;
    }

    public void setColorMouseClick(MyColor colorMouseClick) {
        this.colorMouseClick = colorMouseClick;
    }

    public MyColor getColorMouseMove() {
        return colorMouseMove;
    }

    public void setColorMouseMove(MyColor colorMouseMove) {
        this.colorMouseMove = colorMouseMove;
    }

    public MyColor getColorTitle() {
        return colorTitle;
    }

    public void setColorTitle(MyColor colorTitle) {
        this.colorTitle = colorTitle;
    }

    public MyColor getColorWord() {
        return colorWord;
    }

    public void setColorWord(MyColor colorWord) {
        this.colorWord = colorWord;
    }

    public MyFont getFont() {
        return font;
    }

    public void setFont(MyFont font) {
        this.font = font;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImageBackground() {
        return imageBackground;
    }

    public void setImageBackground(byte[] imageBackground) {
        this.imageBackground = imageBackground;
    }

    public int getSizeWord() {
        return sizeWord;
    }

    public void setSizeWord(int sizeWord) {
        this.sizeWord = sizeWord;
    }

    public int getWidthColumnTable() {
        return widthColumnTable;
    }

    public void setWidthColumnTable(int widthColumnTable) {
        this.widthColumnTable = widthColumnTable;
    }

    public int getWithRowTable() {
        return withRowTable;
    }

    public void setWithRowTable(int withRowTable) {
        this.withRowTable = withRowTable;
    }

    @Override
    public String getEntityName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getModuleOfEntity() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getEntityDescription() {
        return "Thong so cua he thong";
    }

    @Override
    public IAccessData getAccessDataOfEntity() {
        return new SystemSotfwareDAO();
    }

    @Override
    public String getFieldNameObjectId() {
        return "id";
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
