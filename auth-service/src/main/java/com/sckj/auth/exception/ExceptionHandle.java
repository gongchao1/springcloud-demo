package com.sckj.auth.exception;


import com.sckj.auth.util.Result;
import com.sckj.auth.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wangheduo on 2018/8/9
 */
@ControllerAdvice
public class ExceptionHandle {
    //记录系统异常

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /** * @ExceptionHandler(value = Exception.class):声明要捕获的异常类 * @return */

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if(e instanceof ServiceException){
            ServiceException serviceException = (ServiceException) e;
            return ResultUtil.error(serviceException.getCode(),serviceException.getMessage());
        }else {
            log.error("【系统异常】 {}",e);
            return ResultUtil.error(-1,"服务器错误，请稍后再试！！"); }
    }



}
