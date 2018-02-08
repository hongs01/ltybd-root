package com.ltybd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.ltybd.service.HbaseGpsService;
import com.ltybd.util.HbaseFactoryUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "HbaseGpsServiceImpl", description = "轨迹回放接口实现类")
@Service
public class HbaseGpsServiceImpl implements HbaseGpsService{
	
	private static final Logger logger = LoggerFactory
			.getLogger(HbaseGpsServiceImpl.class);
	private String sc="info";

	@ApiOperation(value = "轨迹回放查询")
	@Override
	public List<Map<String,Object>> findGpsList(Map<String,Object> params,String tableName) {
		logger.info("findGpsList --> params ==>" + params+",tableName ==>"+tableName);
		List<Map<String,Object>> listMap=new ArrayList<>();
		//查询的范围(rowKey的拼接)
		logger.info("开始连接");
		Connection conn = HbaseFactoryUtil.getInstance().con();
		logger.info("连接成功");
		Table table=null;
		
		String startTime = params.get("startTime").toString();
		String endTime = params.get("endTime").toString();
		int gprsId = Integer.parseInt(params.get("gprsId").toString());
		int terminalNo = Integer.parseInt(params.get("terminalNo").toString());
		logger.info("startTime = "+startTime+",endTime="+endTime+",gprsId ="+gprsId+",terminalNo="+terminalNo);
		try{
			
//					getrowKey(start, end, gprsId, terminalNo);
			String startRow = String.format("%04d%s%06d", new Object[]{gprsId,startTime, terminalNo});
			String stopRow = String.format("%04d%s%06d", new Object[]{gprsId,endTime, terminalNo});
			logger.info("startRow = "+startRow+",stopRow="+stopRow);
			Scan scan = new Scan();
			scan.setCaching(1000);
			scan.setStartRow(startRow.getBytes());
			scan.setStopRow(stopRow.getBytes());
			logger.info("开始查询");
			table = conn.getTable(TableName.valueOf(tableName));
//			logger.info("查询.....table = "+table.toString()+",startRow = "+list.get(0)+",StopRow = "+list.get(1));
			ResultScanner rs = table.getScanner(scan);
			logger.info("查询结束");
			//遍历数据
			for(Result r:rs){
				Map<String,Object> map = new HashMap<String,Object>();
				Map<byte[], byte[]> result = r.getFamilyMap(sc.getBytes());
				Iterator<Entry<byte[], byte[]>> it = result.entrySet().iterator();
				while (it.hasNext()) {
	                Entry<byte[], byte[]> entry = it.next();
	                if(Bytes.toString(entry.getKey()).equals("busState")){
	                	int busStatus = entry.getValue().length == 0 ? 0:Integer.parseInt(Bytes.toString(entry.getValue()));
	                	String imgStatKey = "0";//正常
		                if((busStatus & 16) > 0) {//偏离线路
		    				imgStatKey = "1";
		    			}
		    			if((busStatus & 64) > 0) {//超速
		    				imgStatKey = "2";
		    			}
		    			map.put(Bytes.toString(entry.getKey()), imgStatKey);
	                 } else if(Bytes.toString(entry.getKey()).equals("occurTime")){
	                	long occurTime = entry.getValue().length == 0 ? 0 : Long.parseLong(Bytes.toString(entry.getValue()));
	                	map.put(Bytes.toString(entry.getKey()), occurTime);
	                 }else {
	                	map.put(Bytes.toString(entry.getKey()), Bytes.toString(entry.getValue()));
	                 }
	                
				}
				String stationName = map.get("stationName") == null ? "":map.get("stationName").toString();
				map.put("stationName", stationName);
				listMap.add(map);
			}
			logger.info("根据时间段从hbase查询gps轨迹信息成功，开始时间："+startTime+",结束时间："+endTime);
		}catch(Exception e){
			logger.error("根据时间段从hbase查询gps轨迹信息异常", e);
			e.printStackTrace();
		}finally{
			  IOUtils.closeQuietly(table);
		}
		return listMap;
	}

	//查询范围的rowKey
	private List<String> getrowKey(String startTime,String endTime,int gprsId,int terminalNo){
		List<String> list=new ArrayList<String>();
		logger.info("getrowKey --> startTime ="+startTime+",endTime="+endTime+",gprsId="+gprsId+",terminalNo="+terminalNo);
		String rowKey=null;
		rowKey=String.format("%04d%s%06d", new Object[]{gprsId,startTime, terminalNo});
		String rowKeys=null;
		rowKeys=String.format("%04d%s%06d", new Object[]{gprsId,endTime, terminalNo});
		list.add(rowKey);
		list.add(rowKeys);
		logger.info("getrowKey --> startTime ="+list.get(0)+",endTime="+list.get(1)+",gprsId="+gprsId+",terminalNo="+terminalNo);
		return list;
	}
	//检查数据传入的合法行

	public static void main(String[] args) {
		String rowKey=null;
		rowKey=String.format("%04d%s%06d", new Object[]{361,"20170831142335", 141});
		System.out.println(rowKey);
	}

}
