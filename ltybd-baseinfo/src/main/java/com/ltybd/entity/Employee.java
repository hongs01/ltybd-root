package com.ltybd.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Employee.java
 *
 * describe:员工信息
 * 
 * 2017年10月12日 下午2:20:41 created By Chenjw version 0.1
 *
 * 2017年10月12日 下午2:20:41 modifyed By Chenjw version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@ApiModel(value="员工信息")
@Entity
@Table(name = "op_employee")
public class Employee {

	@ApiModelProperty(value="员工id")
	@Id
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	private Integer employee_id;
	 
	@ApiModelProperty(value="员工姓名")
	/*@NotEmpty(message="员工姓名不能为空")*/
	@Length(max=40,message="长度不能超过40个字符")
	private String employee_name;
	
	@ApiModelProperty(value="员工类型")
	@NotNull(message="员工类型不能为空")
	private Integer employee_type;
	
	@ApiModelProperty(value="联系电话")
	@Pattern(regexp = "^1[3|4|5|7|8][0-9]{9}$",message="手机号码错误")
	private String phone_no;
	
	@ApiModelProperty(value="通讯地址")
	@NotNull(message="通讯地址不能为空")
	@Length(max=100,message="长度不能超过100个字符")
	private String address;
	
	@ApiModelProperty(value="身份证")
	@Pattern(regexp = "^\\d{17}[\\d|X|x]|\\d{15}$",message="身份证号码错误")
	private String card_id;
	
	@ApiModelProperty(value="生日")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	
	@ApiModelProperty(value="性别")
	@NotNull(message="性别不能为空")
	@Length(max=4,message="性别长度不能大于4个字符")
	private String sex;
	
	@ApiModelProperty(value="状态")
	@Max(value=2,message="状态长度不能大于2个字符")
	private Integer status;
	
	@ApiModelProperty(value="集团ID")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	private Integer group_id;
	
	@ApiModelProperty(value="公司ID")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	private Integer company_id;
	
	@ApiModelProperty(value="部门ID")
	@Min(value=1000,message="必须为大于或者等于1000的正整数")
	private Integer department_id;
	
	@ApiModelProperty(value="创建时间",hidden=true)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date create_time;
	
	@ApiModelProperty(value="上次修改时间",hidden=true)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date last_modified_time;
	
	@ApiModelProperty(value="年龄")
	private int age;
	
	@ApiModelProperty(value="国籍")
	private String nation;
	
	@ApiModelProperty(value="籍贯")
	private String native_place;
	
	@ApiModelProperty(value="办公电话")
	private String work_phone ;
	
	@ApiModelProperty(value="工作邮箱")
	@Email(message = "邮箱格式不正确")
	private String work_email;
	
	@ApiModelProperty(value="职称")
	private String title;
	
	@ApiModelProperty(value="公积金账号")
	private String housingprovidentaccount;
	
	@ApiModelProperty(value="驾照类型")
	private String drivelicense;
	
	@ApiModelProperty(value="婚姻状况")
	private String marital_status;
	
	@ApiModelProperty(value="社保账号")
	private String socialsecurityaccount;
	
	@ApiModelProperty(value="转正日期")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date passdate;
	
	@ApiModelProperty(value="人员属性")
	private String employeeattr;
	
	@ApiModelProperty(value="工资账户")
	private String salaryaccount;
	
	@ApiModelProperty(value="工号")
	private String jobnumber;
	
	@ApiModelProperty(value="驾照号码")
	private String drivelicensenumber;
	
	@ApiModelProperty(value="政治面貌")
	private String political_status;
	
	@ApiModelProperty(value="专业")
	private String specialty;
	
	@ApiModelProperty(value="IC卡")
	private int iccard;
	
	@ApiModelProperty(value="劳动合同")
	private String bargain;
	
	@ApiModelProperty(value="领证日期")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date drivelicensedata;
	
	@ApiModelProperty(value="入职时间")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date entrydate;
	
	@ApiModelProperty(value="学历")
	private String education;
	
	@ApiModelProperty(value="岗位id")
	private int workpost;

	public Integer getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public Integer getEmployee_type() {
		return employee_type;
	}

	public void setEmployee_type(Integer employee_type) {
		this.employee_type = employee_type;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public Integer getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getLast_modified_time() {
		return last_modified_time;
	}

	public void setLast_modified_time(Date last_modified_time) {
		this.last_modified_time = last_modified_time;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getNative_place() {
		return native_place;
	}

	public void setNative_place(String native_place) {
		this.native_place = native_place;
	}

	public String getWork_phone() {
		return work_phone;
	}

	public void setWork_phone(String work_phone) {
		this.work_phone = work_phone;
	}

	public String getWork_email() {
		return work_email;
	}

	public void setWork_email(String work_email) {
		this.work_email = work_email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHousingprovidentaccount() {
		return housingprovidentaccount;
	}

	public void setHousingprovidentaccount(String housingprovidentaccount) {
		this.housingprovidentaccount = housingprovidentaccount;
	}

	public String getDrivelicense() {
		return drivelicense;
	}

	public void setDrivelicense(String drivelicense) {
		this.drivelicense = drivelicense;
	}

	public String getMarital_status() {
		return marital_status;
	}

	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}

	public String getSocialsecurityaccount() {
		return socialsecurityaccount;
	}

	public void setSocialsecurityaccount(String socialsecurityaccount) {
		this.socialsecurityaccount = socialsecurityaccount;
	}

	public Date getPassdate() {
		return passdate;
	}

	public void setPassdate(Date passdate) {
		this.passdate = passdate;
	}

	public String getEmployeeattr() {
		return employeeattr;
	}

	public void setEmployeeattr(String employeeattr) {
		this.employeeattr = employeeattr;
	}

	public String getSalaryaccount() {
		return salaryaccount;
	}

	public void setSalaryaccount(String salaryaccount) {
		this.salaryaccount = salaryaccount;
	}

	public String getJobnumber() {
		return jobnumber;
	}

	public void setJobnumber(String jobnumber) {
		this.jobnumber = jobnumber;
	}

	public String getDrivelicensenumber() {
		return drivelicensenumber;
	}

	public void setDrivelicensenumber(String drivelicensenumber) {
		this.drivelicensenumber = drivelicensenumber;
	}

	public String getPolitical_status() {
		return political_status;
	}

	public void setPolitical_status(String political_status) {
		this.political_status = political_status;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public int getIccard() {
		return iccard;
	}

	public void setIccard(int iccard) {
		this.iccard = iccard;
	}

	public String getBargain() {
		return bargain;
	}

	public void setBargain(String bargain) {
		this.bargain = bargain;
	}

	public Date getDrivelicensedata() {
		return drivelicensedata;
	}

	public void setDrivelicensedata(Date drivelicensedata) {
		this.drivelicensedata = drivelicensedata;
	}

	public Date getEntrydate() {
		return entrydate;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public int getWorkpost() {
		return workpost;
	}

	public void setWorkpost(int workpost) {
		this.workpost = workpost;
	}
	

	
}
