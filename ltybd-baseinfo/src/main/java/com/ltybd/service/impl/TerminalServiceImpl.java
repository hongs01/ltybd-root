package com.ltybd.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ltybd.entity.Terminal;
import com.ltybd.entity.TerminalBean;
import com.ltybd.mapper.TerminalMapper;
import com.ltybd.service.TerminalService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="TerminalServiceImpl", description = "设备信息接口实现类")
@Service
public class TerminalServiceImpl implements TerminalService{
	@Autowired
	private TerminalMapper terminalDao;
	
	@ApiOperation(value="查询设备信息集合")
	@Override
	public List<TerminalBean> findListObj(Terminal terminal){
		return terminalDao.findListObj(terminal);
	}
	
	@ApiOperation(value="查询设备信息对象")
	@Override
	public TerminalBean findByTerminalId(Terminal terminal){
		return terminalDao.findByTerminalId(terminal);
	}
	
	@ApiOperation(value="插入设备信息对象")
	@Override
	public int insert(Terminal terminal){
		return terminalDao.insert(terminal);
	}
	
	@ApiOperation(value="修改设备信息对象")
	@Override
	public int update(Terminal terminal){
		return terminalDao.updateByPrimaryKeySelective(terminal);
	}
	
	@ApiOperation(value="删除设备对象")
	@Override
	public int delete(Terminal terminal){
		return terminalDao.delete(terminal);
	}
	
	@ApiOperation(value="批量删除设备信息对象")
	@Override
	public int deleteItems(String  ids){
		return terminalDao.deleteItems(ids);
	}
	
	@ApiOperation(value="获取Sequence")
	@Override
	public Integer getSequence(String  code){
		return terminalDao.getSequence(code);
	}
	
	@ApiOperation(value = "批量更新设备信息对象")
	@Transactional
	@Override
	public int updateList(List<Terminal> list) {
		int result = 0;
		if (null != list && !list.isEmpty()) {
			List<Terminal> insertList = new ArrayList<Terminal>();
			List<Terminal> updateList = new ArrayList<Terminal>();
			for (Terminal terminal : list) {
				if (null == terminal.getStatus()) {
					terminal.setStatus(0);
				}
				if (null != terminal.getTerminal_id()) {
					updateList.add(terminal);
				} else {
					terminal.setCreate_time(new Date());
					insertList.add(terminal);
				}
			}
			if (null != insertList && !insertList.isEmpty()) {
				result += terminalDao.insertList(insertList);
			}
			if (null != updateList && !updateList.isEmpty()) {
				result += terminalDao.updateList(updateList);
			}
		}
		return result;
	}
}
