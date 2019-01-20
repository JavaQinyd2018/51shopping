package com.qinyadong.shopping.controller;

import com.qinyadong.shopping.common.Result;
import com.qinyadong.shopping.emnus.ResultStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Yadong Qin
 * @Date: 2018/12/7
 */
public class BaseController {

    /**
     * 处理hibernate validation校验
     * @param bindingResult
     * @param <T>
     * @return
     */
    protected <T> Result<T> processValidation(BindingResult bindingResult) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            List<String> errorMessageList = fieldErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
            String message = StringUtils.join(errorMessageList, "|");
            return new Result<>(false, message, ResultStatus.REQUEST_PARAM_ERROR, null);
    }
}
