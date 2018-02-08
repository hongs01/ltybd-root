package com.ltybd.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ltybd.hbase.HbaseServiceI;
import com.ltybd.service.HbaseGpsService;
import com.ltybd.util.DateUtil;
import com.ltybd.util.HbaseFactoryUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 从hbase中查询gps轨迹
 * @author fuchengyan
 *
 */
@Api(value = "/hbaseGps", description = "轨迹回放")
@RestController
@RequestMapping("/hbaseGps/")
public class HbaseGpsController{
	private static final Logger logger = LoggerFactory.getLogger(HbaseGpsController.class);
	
	@Autowired
	private HbaseGpsService hbaseGpsService;
	 @Autowired
	 private HbaseServiceI hService;
	
	/**
	 *@param 根据条件查询查询gps轨迹
	 *@param startTime  查询开始时间
	 *@param endTime    查询结束时间、
	 *@param gprsId    路线gprsId
	 *@param terminalNo  设备ID
	 */
	
	@ApiOperation(value = "轨迹回放code:003003001", produces = "application/json")
	@RequestMapping(value="findGpsInfo", method = { RequestMethod.POST, RequestMethod.GET })
	@ApiImplicitParams({
	@ApiImplicitParam(paramType = "query", name = "apikey", dataType = "String", required = false, value = "系统注册生成apiKey"),
	@ApiImplicitParam(paramType = "query", name = "gprsId", dataType = "int", required = false, value = "GprsId"),
	@ApiImplicitParam(paramType = "query", name = "terminalNo", dataType = "int", required = false, value = "车号"),
	@ApiImplicitParam(paramType = "query", name = "startTime", dataType = "String", required = false, value = "开始时间"),
	@ApiImplicitParam(paramType = "query", name = "endTime", dataType = "String", required = false, value = "结束时间"),})
	@ResponseBody
	public Map<String, Object> findGpsInfo(String apikey, Integer gprsId,Integer terminalNo,String startTime,String endTime){
		//1. 定义全局变量：返回结果
		Map<String,Object> doResult=new HashMap<String,Object>();
		List<Map<String,Object>> resultMap = new ArrayList<>();
		int result = -1;
		
		try {
			if(StringUtils.isEmpty(gprsId)){
				doResult.put("result", 100);
				doResult.put("resultMsg", "参数gprsId不能为空");
				doResult.put("response", "");
				return doResult;
			}
			if(StringUtils.isEmpty(terminalNo)){
				doResult.put("result", 100);
				doResult.put("resultMsg", "参数terminalNo不能为空");
				doResult.put("response", "");
				return doResult;
			}
			if(StringUtils.isEmpty(startTime)){
				doResult.put("result", 100);
				doResult.put("resultMsg", "参数startTime不能为空");
				doResult.put("response", "");
				return doResult;
			}
			if(StringUtils.isEmpty(endTime)){
				doResult.put("result", 100);
				doResult.put("resultMsg", "参数endTime不能为空");
				doResult.put("response", "");
				return doResult;
			}
			
			String gprsId2 = String.valueOf(gprsId);
			String terminalNo2 = String.valueOf(terminalNo);
			logger.info("startTime = "+startTime+",endTime="+endTime+",gprsId ="+gprsId2+",terminalNo="+terminalNo2);
			
			Date startDate = DateUtil.stringToDate(startTime);
			Date endDate = DateUtil.stringToDate(endTime);
			int secends = DateUtil.getIntervalMinutes(startDate, endDate);//两个时间相差多少分钟
			int minute = 24 * 60;//24小时多少分钟
			
			if(secends >= minute) {//选中的时间大于等于24小时
				doResult.put("result",100);
				doResult.put("resultMsg", "参数时间范围需要小于24小时");
				doResult.put("response", "");
				return doResult;
	 		}

			Map<String, Object> mapParam = new HashMap<>();
			mapParam.put("startTime", DateUtil.StringToString(startTime,"yyyyMMddHHmmss"));
			mapParam.put("endTime", DateUtil.StringToString(endTime,"yyyyMMddHHmmss"));
	 		mapParam.put("gprsId", gprsId2);
	 		mapParam.put("terminalNo", terminalNo2);
			try{
				resultMap = hbaseGpsService.findGpsList(mapParam, "t_r_fcy");
				logger.info("resultMap="+resultMap);
				 Collections.sort(resultMap, new Comparator<Map<String,Object>>() {  

						@Override
						public int compare(Map<String, Object> o1,Map<String, Object> o2) {
							long t1 = o1.get("occurTime") == null ? 0 : Long.parseLong(o1.get("occurTime").toString());
							long t2 = o2.get("occurTime") == null ? 0 : Long.parseLong(o2.get("occurTime").toString());
							int i = (int)(t1 - t2);
							return i;
						}  
			        }); 
				doResult.put("response", resultMap);
				result=0;
				doResult.put("result", 0);
				return doResult;
			}catch(Exception e){
				logger.error("查询gps轨迹信息异常", e);
				doResult.put("response", "服务器异常");
			}
		} catch (Exception e) {
			logger.info("Exception ==>"+e.getMessage());
			doResult.put("response", "服务器异常");
		}
		 return doResult;
	}

	@ResponseBody
	@RequestMapping(value="/test", method = RequestMethod.GET, produces="application/json; charset=utf-8")
	public void test(){
		
		Configuration conn = HbaseFactoryUtil.getInstance().fig();
		
		HTablePool pool = new HTablePool(conn, 1000); 
		String tablename = "t_r_fcy";
		//String tablename = "t_r_gpscoord";
        HTable table = (HTable) pool.getTable(tablename);  
        try {  
           // Get scan = new Get("181820170929171000001801".getBytes());// 根据rowkey查询  
        	Get scan = new Get("118120170825174322011816".getBytes());
            Result r = table.get(scan);  
            System.out.println("获得到rowkey:" + new String(r.getRow()));  
            for (KeyValue keyValue : r.raw()) {  
                System.out.println("列：" + new String(keyValue.getFamily())  
                        + "====值:" + new String(keyValue.getValue()));  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
	}
	
	@ResponseBody
	@RequestMapping(value="/test1", method = RequestMethod.GET, produces="application/json; charset=utf-8")
	public void test1() throws IOException{
		 Connection conn;
		 conn=HbaseFactoryUtil.getInstance().con();
		 Table table = null;
		 try {
		 table = conn.getTable(TableName.valueOf("t_r_fcy"));
		 ResultScanner rs = table.getScanner(new Scan());
		 for (Result r : rs) {
             System.out.println("获得到rowkey:" + new String(r.getRow()));
             for (KeyValue keyValue : r.raw()) {
                       System.out.println(
                                         "列：" + new String(keyValue.getFamily()) + "====值:" + new String(keyValue.getValue()));
             }
    }
		 } finally {
	         //    IOUtils.closeQuietly(table);
			 }
	
	}
	
	
	@ResponseBody
	@RequestMapping(value="/testput", method = RequestMethod.GET, produces="application/json; charset=utf-8")
	public void testput(){
		 hService.put("t_r_fcy", "118120170825174322011816", "info", "sex", "woman");  
	}

	
}
