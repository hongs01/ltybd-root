package com.ltybd.service;

import java.util.List;

import com.ltybd.entity.Dictionary;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="DictionaryService", description = "数据字典接口")
public interface DictionaryService {
	
	@ApiOperation(value="查询字典集合")
	public List<Dictionary> findListObj(Dictionary dic);
	
	@ApiOperation(value="查询字典对象")
	public Dictionary findByDicId(Dictionary dic);
	
	@ApiOperation(value="插入字典对象")
	public int insert(Dictionary dic);
	
	@ApiOperation(value="更新字典对象")
	public int update(Dictionary dic);
	
	@ApiOperation(value="删除字典对象")
	public int delete(Dictionary dic);
	
	@ApiOperation(value="批量删除字典对象")
	public int deleteItems(String  ids);
	
	@ApiOperation(value="获取Sequence")
	public Integer getSequence(String  code);
	
	@ApiOperation(value="批量更新字典对象")
	public int updateDicObjList(List<Dictionary> dics);
}
