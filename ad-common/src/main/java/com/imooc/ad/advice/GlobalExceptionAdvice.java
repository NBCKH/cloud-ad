package com.imooc.ad.advice;

import com.imooc.ad.execption.AdException;
import com.imooc.ad.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chenkaihua on 2019-04-19 11:01
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = AdException.class)
    public CommonResponse<String> handleAdException(HttpServletRequest request, AdException ex){
        CommonResponse<String> commonResponse = new CommonResponse().setCode(-1).setMessage("business error").setData(ex.getMessage());
        return commonResponse;
    }
}
