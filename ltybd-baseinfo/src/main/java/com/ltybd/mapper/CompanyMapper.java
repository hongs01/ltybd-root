package com.ltybd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ltybd.entity.Company;
import com.ltybd.entity.CompanyBean;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="CompanyMapper", description = "公司Mapper")
public interface CompanyMapper extends MyMapper<Company> {
	@ApiOperation(value="模糊查询公司信息对象列表")
	@Select("<script>"
			+ "select c.*,c1.company_name as parent_company_name from op_company c "
			+ "left join op_company c1 on c1.company_id=c.parent_company_id "
			+ "where 1=1"
			+ "<if test='company_id != null'>"
			+ " and c.company_id like '%${company_id}%'"
			+ "</if>"
			+ "<if test='company_name != null '>"
			+ " and c.company_name like '%${company_name}%'"
			+ "</if>"
			+ "<if test='status != null '>"
			+ " and c.status = #{status}"
			+ "</if>"
			+ "</script>")
	public List<CompanyBean> findListObj(Company company);
	
	@ApiOperation(value="查询公司信息对象")
	@Select("<script>"
			+ "select c.*,c1.company_name as parent_company_name from op_company c "
			+ "left join op_company c1 on c1.company_id=c.parent_company_id "
			+ " where 1=1"
			+ " and c.company_id = ${company_id}"
			+ "</script>")
	public CompanyBean findByCompanyId(Company company);
	
	@ApiOperation(value="批量删除公司信息对象")
	@Delete("delete from op_company where company_id in (${ids})") 
	public int deleteItems(@Param("ids")String  ids);
	
	@ApiOperation(value = "更新公司信息")
	@Update("<script>"
			+ "<foreach collection='list' item='item' index='index' separator=';' >"
			+"UPDATE op_company "
			+"<set>"
			+"<if test='item.company_name != null'>"
			+ "company_name = #{item.company_name},"
			+ "</if>"
			+"<if test='item.remark != null'>"
			+ "remark = #{item.remark},"
			+ "</if>"
			+"<if test='item.parent_company_id != null'>"
			+ "parent_company_id = #{item.parent_company_id},"
			+ "</if>"
			+" <if test='item.status != null'>"
			+ "status = #{item.status},"
			+ "</if>"
			+" <if test='item.last_modified_time != null'>"
			+ "last_modified_time = #{item.last_modified_time},"
			+ "</if>"
			+"</set>"
			+"WHERE 1=1"
			+" and company_id = #{item.company_id}"
			+"</foreach>"
			+ "</script>")
	public int updateList(@Param("list")List<Company> list);
	
	@ApiOperation(value = "新增公司信息")
	@Insert("<script>"
			+ "INSERT INTO op_company (company_id, company_name, parent_company_id,remark,status,create_time,last_modified_time)"
			+ " VALUES"
			+ "<foreach collection='list' item='item' index='index' separator=',' >"
            +"(nextval('company_code'),#{item.company_name},#{item.parent_company_id},#{item.remark},#{item.status},#{item.create_time},#{item.last_modified_time})" 
        	+"</foreach>"
			+ "</script>")
	public int insertList(@Param("list")List<Company> list);
}
