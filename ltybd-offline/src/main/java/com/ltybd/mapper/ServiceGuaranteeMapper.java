package com.ltybd.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.ltybd.entity.ServiceGuarantee;
import com.ltybd.util.MyMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * ServiceGuaranteeMapper.java
 *
 * describe:
 * 
 * 2017年10月17日 上午11:39:05 created By chenq version 0.1
 *
 * 2017年10月17日 上午11:39:05 modifyed By chenq version 0.1
 *
 * copyright 2002-2017 深圳市蓝泰源电子科技有限公司
 */
@Api(value = "ServiceGuaranteeMapper", description = "服务保障能Mapper")
public interface ServiceGuaranteeMapper extends MyMapper<ServiceGuarantee>{
	
	@ApiOperation(value = "服务保障能客流详情")
	@Select("<script>"
			+ " select CASE timeType "
			+ " WHEN 1 then offtime "
			+ " WHEN 2 THEN RIGHT(offdate,5) "
			+ " WHEN 3 then CONCAT(REPLACE(offdate,'@','-'),'周') "
			+ " WHEN 4 THEN CONCAT(REPLACE(offdate,'#','-'),'月') end  AS x_data, "
			+ " a.passengerFlow AS y_data_passenger_flow, "
			+ " '' as y_data_capacity_sugguest, "
			+ " '' as y_data_capacity "
			+ " from "
			+ " dm_service_Guarantee_dm a "
			+ " where  a.routeId=#{line_id} "
			+ " and a.citycode=#{city_code} "
			+ " and a.calculatedDate=#{date_end} "
			+ " <if test='date_period !=-1 '> "
			+ " and a.stat_cycle=#{date_period} "
			+ " </if> "
			+ " and a.lineDirection=#{direction} "
			+ " and a.timeType=#{step} "
			+ " and a.departmentid=#{company_id} "
			+ " order by x_data desc; "
			+ "</script>")
	public List<Map<String, Object>> passengerFlowDetail(Map<String, Object> param);

}
