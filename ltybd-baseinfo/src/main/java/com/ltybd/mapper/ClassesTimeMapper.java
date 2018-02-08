package com.ltybd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ltybd.entity.ClassesTime;
import com.ltybd.entity.ClassesTimeBean;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="ClassesTimeMapper", description = "班次时间Mapper")
public interface ClassesTimeMapper extends MyMapper<ClassesTime> {
	
	@ApiOperation(value = "插入班次时间信息")
	@Insert("<script>insert into op_classes_time(start_time,end_time,classes_id,create_date)values "
            +"(#{start_time},#{end_time},#{classes_id},#{create_date})" 
			+ "</script>")
	public int insertClassesTime(ClassesTime classesTime);
	
	@ApiOperation(value = "修改班次时间信息")
	@Update("<script>update op_classes_time "
			+"<set>"
			+ "<if test='start_time != null'>"
			+ "start_time=#{start_time},"
			+ "</if>"
			+ "<if test='end_time != null'>"
			+ "end_time=#{end_time}, "
			+ "</if>"
			+ "<if test='classes_id != null'>"
			+ "classes_id=#{classes_id}, "
			+ "</if>"
			+"</set>"
            +"where classes_time_id=#{classes_time_id}" 
			+ "</script>")
	public int updateClassesTime(ClassesTime classesTime);
	
	@ApiOperation(value="查询班次时间信息")
	@Select("<script>"
			+ "select c.*,c1.classes_name from op_classes_time c "
			+ "left join op_classes c1 on c1.classes_id=c.classes_id "
			+ " where 1=1"
			+ " and c.classes_time_id = #{classes_time_id}"
			+ "</script>")
	public ClassesTime findById(ClassesTime classesTime);
	
	@ApiOperation(value="查询班次对应时间列表")
	@Select("<script>"
			+ "select c.* from op_classes_time c "
			+ " where 1=1"
			+ " and c.classes_id = #{classes_id}"
			+ "</script>")
	public List<ClassesTimeBean> findTimeList(Integer classes_id);
	
}
