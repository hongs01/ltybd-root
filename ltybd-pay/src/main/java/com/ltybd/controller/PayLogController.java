package com.ltybd.controller;


import com.ltybd.common.domain.ResultObject;
import com.ltybd.common.exception.PayException;
import com.ltybd.entity.PayRecord;
import com.ltybd.service.IPayLogSearchService;
import com.ltybd.service.IPayLogService;
import com.ltybd.service.impl.elasticsearch.PayLogSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 2017/10/27 zhouyongbo
 * 支付记录
 */
@RestController
@RequestMapping("/services")
public class PayLogController {

    @Autowired
    IPayLogService iPayLogService;

    @Autowired
    IPayLogSearchService iPayLogSearchService;

    @RequestMapping(value="/payLog",method= RequestMethod.POST)
    public ResultObject save(@RequestBody PayRecord payRecord){
        try{
            iPayLogService.savePayLog(payRecord);
        }catch (PayException e){
            return new ResultObject(e.getMessage(),false);
        }
        return new ResultObject("",true);
    }

    @RequestMapping(value="/payLog",method= RequestMethod.GET)
    public ResultObject get(@RequestParam String key){
        Map<String, Object> payLog = null;
        try{
           payLog = iPayLogService.getPayLog(key);
        }catch (PayException e){
            return new ResultObject(e.getMessage(),false);
        }
        return new ResultObject(payLog,true);
    }

    @RequestMapping(value="/payLoges",method= RequestMethod.POST)
    public ResultObject get(@RequestBody PayRecord payRecord){
        int i1 = iPayLogSearchService.savePayLog(payRecord);
        return new ResultObject(i1,true);
    }



}
