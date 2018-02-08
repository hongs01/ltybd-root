package com.ltybd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ltybd.entity.Employee;
import com.ltybd.entity.EmployeeBean;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="EmployeeMapper", description = "员工信息Mapper")
public interface EmployeeMapper extends MyMapper<Employee>{
	
	@ApiOperation(value="模糊查询员工信息对象列表")
	@Select("<script>"
			+ "select e.*,d.dict_name as employee_type_name from op_employee e "
			+ "left join dict_item d on d.dict_id=e.employee_type "
			+ "where 1=1 "
			+ "<if test='employee_id != null '>"
			+ " and employee_id like '%${employee_id}%' "
			+ "</if>"
			+ "<if test='employee_name != null'>"
			+ " and employee_name like '%${employee_name}%'"
			+ "</if>"
			+ "<if test='status != null '>"
			+ " and status = #{status}"
			+ "</if>"
			+ "</script>")
	public List<EmployeeBean> findListObj(Employee employee);
	
	@ApiOperation(value="根据员工 ID查询员工信息对象")
	@Select("<script>"
			+ "select e.*,d.dict_name as employee_type_name from op_employee e "
			+ "left join dict_item d on d.dict_id=e.employee_type "
			+ "where 1=1 "
			+ " and employee_id = ${employee_id} "
			+ "</script>")
	public EmployeeBean findByEmployeeId(Employee employee);
	
	@ApiOperation(value="批量删除员工信息对象")
	@Delete("delete from op_employee where employee_id in (${ids})") 
	public int deleteItems(@Param("ids")String  ids);
	
	@ApiOperation(value="批量修改员工对象")
	@Update("<script>"
			+ "<foreach collection='list' item='item' index='index' separator=';' >"
			+ "update op_employee "
			+ "<set>"
			+ "<if test='item.employee_name != null'>"
			+ "employee_name=#{item.employee_name},"
			+ "</if>"
			+ "<if test='item.employee_type != null'>"
			+ "employee_type=#{item.employee_type},"
			+ "</if>"
			+ "<if test='item.phone_no != null'>"
			+ "phone_no=#{item.phone_no},"
			+ "</if>"
			+ "<if test='item.address != null'>"
			+ "address=#{item.address},"
			+ "</if>"
			+ "<if test='item.card_id != null'>"
			+ "card_id=#{item.card_id},"
			+ "</if>"
			+ "<if test='item.birthday != null'>"
			+ "birthday=#{item.birthday},"
			+ "</if>"
			+ "<if test='item.sex != null'>"
			+ "sex=#{item.sex},"
			+ "</if>"
			+ "<if test='item.status != null'>"
			+ "status=#{item.status},"
			+ "</if>"
			+ "<if test='item.group_id != null'>"
			+ "group_id=#{item.group_id},"
			+ "</if>"
			+ "<if test='item.company_id != null'>"
			+ "company_id=#{item.company_id},"
			+ "</if>"
			+ "<if test='item.department_id != null'>"
			+ "department_id=#{item.department_id},"
			+ "</if>"
			+ "<if test='item.last_modified_time != null'>"
			+ "last_modified_time=#{item.last_modified_time},"
			+ "</if>"
			+ "<if test='item.age != null'>"
			+ "age=#{item.age},"
			+ "</if>"
			+ "<if test='item.nation != null'>"
			+ "nation=#{item.nation},"
			+ "</if>"
			+ "<if test='item.native_place != null'>"
			+ "native_place=#{item.native_place},"
			+ "</if>"
			+ "<if test='item.work_phone != null'>"
			+ "work_phone=#{item.work_phone},"
			+ "</if>"
			+ "<if test='item.work_email != null'>"
			+ "work_email=#{item.work_email},"
			+ "</if>"
			+ "<if test='item.title != null'>"
			+ "title=#{item.title},"
			+ "</if>"
			+ "<if test='item.housingprovidentaccount != null'>"
			+ "housingprovidentaccount=#{item.housingprovidentaccount},"
			+ "</if>"
			+ "<if test='item.drivelicense != null'>"
			+ "drivelicense=#{item.drivelicense},"
			+ "</if>"
			+ "<if test='item.marital_status != null'>"
			+ "marital_status=#{item.marital_status},"
			+ "</if>"
			+ "<if test='item.socialsecurityaccount != null'>"
			+ "socialsecurityaccount=#{item.socialsecurityaccount},"
			+ "</if>"
			+ "<if test='item.passdate != null'>"
			+ "passdate=#{item.passdate},"
			+ "</if>"
			+ "<if test='item.employeeattr != null'>"
			+ "employeeattr=#{item.employeeattr},"
			+ "</if>"
			+ "<if test='item.salaryaccount != null'>"
			+ "salaryaccount=#{item.salaryaccount},"
			+ "</if>"
			+ "<if test='item.jobnumber != null'>"
			+ "jobnumber=#{item.jobnumber},"
			+ "</if>"
			+ "<if test='item.drivelicensenumber != null'>"
			+ "drivelicensenumber=#{item.drivelicensenumber},"
			+ "</if>"
			+ "<if test='item.political_status != null'>"
			+ "political_status=#{item.political_status},"
			+ "</if>"
			+ "<if test='item.specialty != null'>"
			+ "specialty=#{item.specialty},"
			+ "</if>"
			+ "<if test='item.iccard != null'>"
			+ "iccard=#{item.iccard},"
			+ "</if>"
			+ "<if test='item.bargain != null'>"
			+ "bargain=#{item.bargain},"
			+ "</if>"
			+ "<if test='item.drivelicensedata != null'>"
			+ "drivelicensedata=#{item.drivelicensedata},"
			+ "</if>"
			+ "<if test='item.entrydate != null'>"
			+ "entrydate=#{item.entrydate},"
			+ "</if>"
			+ "<if test='item.education != null'>"
			+ "education=#{item.education},"
			+ "</if>"
			+ "<if test='item.workpost != null'>"
			+ "workpost=#{item.workpost},"
			+ "</if>"
			+ "</set>"
			+ "where 1=1 "
			+ "<if test='item.employee_id != null'>"
			+ " AND employee_id=#{item.employee_id}"
			+ "</if>"
			+"</foreach>"
			+ "</script>")
	public int updateList(@Param("list")List<Employee> list);
	
	@ApiOperation(value = "批量插入员工信息")
	@Insert("<script>"
			+ "INSERT INTO op_employee (employee_id, employee_name, employee_type, phone_no, address, card_id, birthday, sex, status, group_id, company_id, department_id, create_time, last_modified_time,age,nation,native_place,work_phone,work_email,title,housingprovidentaccount,drivelicense,marital_status,socialsecurityaccount,passdate,employeeattr,salaryaccount,jobnumber,drivelicensenumber,political_status,specialty,iccard,bargain,drivelicensedata,entrydate,education,workpost)"
			+ " values"
			+ "<foreach collection='list' item='item' index='index' separator=',' >"
            +"(nextval('employee_code'),#{item.employee_name},#{item.employee_type},#{item.phone_no},#{item.address},#{item.card_id},#{item.birthday},#{item.sex},#{item.status},#{item.group_id},#{item.company_id},#{item.department_id},#{item.create_time},#{item.last_modified_time},#{item.native_place},#{item.age},#{item.nation},#{item.work_phone},#{item.work_email},#{item.title},#{item.housingprovidentaccount},#{item.drivelicense},#{item.marital_status},#{item.socialsecurityaccount},#{item.passdate},#{item.employeeattr},#{item.salaryaccount},#{item.jobnumber},#{item.drivelicensenumber},#{item.political_status},#{item.specialty},#{item.iccard},#{item.bargain},#{item.drivelicensedata},#{item.entrydate},#{item.education},#{item.workpost})" 
        	+"</foreach>"
			+ "</script>")
	public int insertList(@Param("list")List<Employee> list);
	
}
