package com.ltybd.hbase;

public interface HbaseServiceI {
	
	public String get(String tableName, String rowName, String familyName,  
            String qualifier);
	
	public void put(final String tableName, final String rowKey,  
            final String familyName, final String column, final String value);
	
}
