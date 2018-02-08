package com.ltybd.exception;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ltybd.entity.ResponseData;


/**
 * Description: 异常拦截处理<br/>
 * Date: 2017年7月30日 下午3:24:35 <br/>
 * 
 * @author 
 * @version
 * @see
 */
@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseData defaultErrorHandler(Exception e,HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResponseData r = new ResponseData();
        r.setResult("1");
        r.setResPonse("请求异常");
       if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
             r.setResultMsg("您请求的接口地址不存在,请确定访问地址是否正确");
        } else {
             r.setResultMsg("请求失败,请检查所传参数是否合法");
        }
        return r;
    }

}
