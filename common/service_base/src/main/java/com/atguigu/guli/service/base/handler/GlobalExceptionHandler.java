package com.atguigu.guli.service.base.handler;

import com.atguigu.guli.service.base.result.R;
import com.atguigu.guli.service.base.result.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wxg
 * @create 2021 -07 -03 0:20
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        log.error(e.getLocalizedMessage());
        //e.printStackTrace();
        return R.error();
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseBody
    public R error(BadSqlGrammarException e){
        log.error(e.getLocalizedMessage());

        return R.setResult(ResultCodeEnum.BAD_SQL_GRAMMAR);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public R error(HttpMessageNotReadableException e){
        log.error(ExceptionUtils.getStackTrace(e));

        return R.setResult(ResultCodeEnum.JSON_PARSE_ERROR);
    }
}