package com.ltybd.hbase;

import java.util.List;

import javax.annotation.Resource;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.mapred.Merger.Segment;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.stereotype.Service;

@Service("hbaseService")
public class HbaseServiceImpl implements  HbaseServiceI{
	
	 @Resource(name = "htemplate")  
	    private HbaseTemplate htemplate;  
	   // 获取表中指定行，列簇，列的值  
	    public String get(String tableName, String rowName, String familyName,  
	            String qualifier) {  
	        return htemplate.get(tableName, rowName, familyName, qualifier,  
	                new RowMapper<String>() {  
	        	        @Override
	                    public String mapRow(Result result, int rowNum)  
	                            throws Exception {  
	                        List<Cell> ceList = result.listCells();  
	                        String res = "";  
	                        if (ceList != null && ceList.size() > 0) {  
	                            for (Cell cell : ceList) {  
	                                res = Bytes.toString(cell.getValueArray(),  
	                                        cell.getValueOffset(),  
	                                        cell.getValueLength());  
	                            }  
	                        }  
	                        return res;  
	                    }

	                });  
	    } 
	    
	    
	    // 将值插入表中指定的行，列簇，列中  
	    public void put(final String tableName, final String rowKey,  
	            final String familyName, final String column, final String value) {  
	        htemplate.put(tableName, rowKey, familyName, column, value.getBytes());  
	    }  
}
