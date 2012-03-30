/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.identity.access.api;

import java.util.List;
import javax.persistence.EntityManagerFactory;

/**
 * Mô tả chung nhất việc truy xuất cơ sở dữ liệu
 *
 * @author Lê Xuân Bách
 */
public interface IAccessData<E> {

    public static final String DESC = "desc";
    public static final String ASC = "asc";

    /**z
     * emf: Truyền vào EntityManagerFactory kết nối dữ liệu typeClass: Truyền
     * vào kiểu đối tượng muốn truy cập
     */
    public void setAccessData(EntityManagerFactory emf, Class typeClass);

    /**
     * Thực hiện việc thêm đối tượng obj vào cơ sở dữ liệu
     */
    public boolean insert(E obj);

    /**
     * Thực hiện việc sửa đối tượng obj trong cơ sở dữ liệu
     */
    public boolean update(E obj);

    /**
     * Thực hiện việc xóa đối tượng obj trong cơ sở dữ liệu
     */
    public boolean delete(E obj);

    /**
     * Thực hiện việc xóa đối tượng obj có mã id kiểu integer trong cơ sở dữ
     * liệu
     */
    public boolean delete(int id);

    /**
     * Thực hiện việc xóa đối tượng obj có mã id kiểu String trong cơ sở dữ liệu
     */
    public boolean delete(String id);

    /**
     * Thực hiện lấy đối tượng có mã Id kiểu interger trong cơ sở dữ liệu
     */
    public E getById(int id);

    /**
     * Thực hiện lấy đối tượng có mã Id kiểu String trong cơ sở dữ liệu
     */
    public E getByObjectId(String id);

    /**
     * Hàm trả về danh sách đối tượng của một câu lệnh sql
     *
     * @param query: câu lệnh sql
     * @return
     */
    public List<Object> queryList(String query);

    /**
     * Lấy danh sách tất cả các đối tượng trong cơ sở dữ liệu
     */
    public List<E> selectAll();

    /**
     * Lấy danh sách tất cả các đối tượng trong cơ sở dữ liệu và sắp xếp chúng
     * theo thứ tự typeOrder: kiểu sắp xếp : DESC hay ASC fieldNameOrder: tên
     * trường muốn sử dụng để sắp xếp
     */
    public List<E> selectAll(String typeOrder, String fieldNameOrder);

    /**
     * Lấy danh sách tất cả các đối tượng trong cơ sở dữ liệu có 1 thuộc tính
     * đúng như yêu cầu: fieldName: tên thuộc tính wordSearch: yêu cầu thuộc
     * tính.
     */
    public List<E> select(String fieldName, String wordSearch);
    
    /**
     * Lấy danh sách tất cả các đối tượng trong cơ sở dữ liệu có 1 thuộc tính
     * đúng như yêu cầu: fieldName: tên thuộc tính wordSearch: yêu cầu thuộc
     * tính.
     */
    public List<E> selectOrCondition(List<String> fieldNames, List<String> wordSearchs);

    /**
     * Lấy danh sách tất cả các đối tượng trong cơ sở dữ liệu có 1 thuộc tính
     * như yêu cầu và có sắp xếp: fieldName: tên thuộc tính wordSearch: yêu cầu
     * thuộc tính. typeOrder: kiểu sắp xếp : DESC hay ASC fieldNameOrder: tên
     * trường muốn sử dụng để sắp xếp
     */
    public List<E> select(String fieldName, String wordSearch, String typeOrder, String fieldNameOrder);

    /**
     * Lấy danh sách tất cả các đối tượng trong cơ sở dữ liệu có các thuộc tính
     * đúng như yêu cầu: fieldNames: tên các thuộc tính có yêu câu wordSearchs:
     * các yêu cầu tương ứng với thuộc tính theo đúng thứ tự
     */
    public List<E> select(List<String> fieldNames, List<String> wordSearchs);

    /**
     * Lấy danh sách tất cả các đối tượng trong cơ sở dữ liệu có các thuộc tính
     * như yêu cầu và có sắp xếp: fieldNames: tên các thuộc tính có yêu câu
     * wordSearchs: các yêu cầu tương ứng với thuộc tính theo đúng thứ tự
     * typeOrder: kiểu sắp xếp : DESC hay ASC fieldNameOrder: tên trường muốn sử
     * dụng để sắp xếp
     */
    public List<E> select(List<String> fieldNames, List<String> wordSearchs, String typeOrder, String fieldNameOrder);

    /**
     * Lấy danh sách tất cả các đối tượng trong cơ sở dữ liệu có 1 thuộc tính
     * gần như yêu cầu: fieldName: tên các thuộc tính wordFilter: yêu cầu thuộc
     * tính.
     */
    public List<E> filter(String fieldName, String wordFilter);

    /**
     * Lấy danh sách tất cả các đối tượng trong cơ sở dữ liệu có 1 thuộc tính
     * gần như yêu cầu và có sắp xếp: fieldName: tên thuộc tính wordFilter: yêu
     * cầu thuộc tính. typeOrder: kiểu sắp xếp : DESC hay ASC fieldNameOrder:
     * tên trường muốn sử dụng để sắp xếp
     */
    public List<E> filter(String fieldName, String wordFilter, String typeOrder, String fieldNameOrder);

    /**
     * Lấy danh sách tất cả các đối tượng trong cơ sở dữ liệu có các thuộc tính
     * gần như yêu cầu: fieldNames: tên các thuộc tính có yêu cầu wordFilters:
     * các yêu cầu tương ứng với thuộc tính theo đúng thứ tự.
     */
    public List<E> filter(List<String> fieldNames, List<String> wordFilters);

    /**
     * Lấy danh sách tất cả các đối tượng trong cơ sở dữ liệu có các thuộc tính
     * gần như yêu cầu và có sắp xếp: fieldNames: tên các thuộc tính có yêu cầu
     * wordFilters: các yêu cầu tương ứng với thuộc tính theo đúng thứ tự.
     * typeOrder: kiểu sắp xếp : DESC hay ASC fieldNameOrder: tên trường muốn sử
     * dụng để sắp xếp
     */
    public List<E> filter(List<String> fieldNames, List<String> wordFilters, String typeOrder, String fieldNameOrder);
}
