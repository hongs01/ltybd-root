package com.ltybd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ltybd.entity.Dictionary;
import com.ltybd.mapper.DictionaryMapper;
import com.ltybd.service.DictionaryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="DictionaryServiceImpl", description = "数据字典接口实现类")
@Service
public class DictionaryServiceImpl implements DictionaryService{

	@Autowired
	private DictionaryMapper dictionaryDao;
	
	@ApiOperation(value="查询字典集合")
	@Override
	public List<Dictionary> findListObj(Dictionary dic){
		return dictionaryDao.findListObj(dic);
	}
	
	@ApiOperation(value="查询字典对象")
	@Override
	public Dictionary findByDicId(Dictionary dic){
		return dictionaryDao.selectOne(dic);
	}
	
	@ApiOperation(value="插入字典对象")
	@Override
	public int insert(Dictionary dic){
		return dictionaryDao.insert(dic);
	}
	
	@ApiOperation(value="修改字典对象")
	@Override
	public int update(Dictionary dic){
		return dictionaryDao.updateByPrimaryKeySelective(dic);
	}
	
	@ApiOperation(value="删除字典对象")
	@Override
	public int delete(Dictionary dic){
		return dictionaryDao.delete(dic);
	}
	
	@ApiOperation(value="批量删除字典对象")
	@Override
	public int deleteItems(String  ids){
		return dictionaryDao.deleteItems(ids);
	}
	
	@ApiOperation(value="获取Sequence")
	@Override
	public Integer getSequence(String  code){
		return dictionaryDao.getSequence(code);
	}
	
	@ApiOperation(value="批量更新字典对象")
	@Transactional
	@Override
	public int updateDicObjList(List<Dictionary> dics){
		int result = 0;
		if (null != dics && !dics.isEmpty()) {
			List<Dictionary> insertList = new ArrayList<Dictionary>();
			List<Dictionary> updateList = new ArrayList<Dictionary>();
			for (Dictionary dictionary : dics) {
				Dictionary queryVO = new Dictionary();
				Dictionary obj =null;
				if(!StringUtils.isEmpty(dictionary.getDict_id())){
					queryVO.setDict_id(dictionary.getDict_id());
					obj=this.findByDicId(queryVO);
				}
				if (null != obj) {
					updateList.add(dictionary);
				} else {
					insertList.add(dictionary);
				}
			}
			if (null != insertList && !insertList.isEmpty()) {
				result += dictionaryDao.insertList(insertList);
			}
			if (null != updateList && !updateList.isEmpty()) {
				result += dictionaryDao.updateList(updateList);
			}
		}
		return result;
	}
}
