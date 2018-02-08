package com.ltybd.service;

import java.util.List;

import com.ltybd.entity.Terminal;
import com.ltybd.entity.TerminalBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="TerminalService", description = "设备信息接口")
public interface TerminalService {
	@ApiOperation(value="查询设备信息集合")
	public List<TerminalBean> findListObj(Terminal terminal);
	
	@ApiOperation(value="查询设备信息对象")
	public TerminalBean findByTerminalId(Terminal terminal);
	
	@ApiOperation(value="插入设备信息对象")
	public int insert(Terminal terminal);
	
	@ApiOperation(value="修改设备信息对象")
	public int update(Terminal terminal);
	
	@ApiOperation(value = "批量更设备信息")
	public int updateList(List<Terminal> list);
	
	@ApiOperation(value="删除设备信息对象")
	public int delete(Terminal terminal);
	
	@ApiOperation(value="批量删除设备信息对象")
	public int deleteItems(String  ids);
	
	@ApiOperation(value="获取Sequence")
	public Integer getSequence(String  code);
}
