package com.example.admin.component.spring;

import com.example.admin.common.R.R;
import com.example.admin.common.R.RException;
import com.example.admin.common.R.RStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public R resolveConstraintViolationException(ConstraintViolationException ex) {
        log.error(ex.getMessage());
        ex.printStackTrace();
        return R.status(RStatus.INVALID_REQUEST_PARAMETERS);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public R resolveMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.error(ex.getMessage());
        ex.printStackTrace();
        return R.status(RStatus.INVALID_REQUEST_PARAMETERS);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public R resolveMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage());
        ex.printStackTrace();
        return R.status(RStatus.INVALID_REQUEST_PARAMETERS);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public R resolveMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        log.error(ex.getMessage());
        ex.printStackTrace();
        return R.status(RStatus.INVALID_REQUEST_PARAMETERS);
    }

    @ExceptionHandler(RException.class)
    @ResponseBody
    public R resolveRException(RException ex) {
        log.error("{Message："+ex.getStatus() + "，code：" + ex.getStatus().getCode() + "}");
//        ex.printStackTrace();
        return R.status(ex.getStatus());
    }

    @ExceptionHandler(SQLException.class)
    @ResponseBody
    public R resolveSQLException(SQLException ex) {
//        log.error(ex.getMessage());
        log.error("", ex);
        ex.printStackTrace();
        return R.status(RStatus.SYSTEM_ERROR);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R resolveException(Exception ex) {
        log.error("", ex);
        ex.printStackTrace();
        return R.status(RStatus.SYSTEM_ERROR);
    }
}