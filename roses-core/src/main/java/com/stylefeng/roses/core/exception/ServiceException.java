package com.stylefeng.roses.core.exception;

import com.stylefeng.roses.api.common.AbstractServiceException;

/**
 * 业务异常的封装
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午5:05:10
 */
public class ServiceException extends RuntimeException {

    private Integer code;

    private String errorMessage;

    public ServiceException(AbstractServiceException exception) {
        this.code = exception.getCode();
        this.errorMessage = exception.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
