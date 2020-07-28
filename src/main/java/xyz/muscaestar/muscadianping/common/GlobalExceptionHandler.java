package xyz.muscaestar.muscadianping.common;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by muscaestar on 7/27/20
 *
 * @author muscaestar
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonRes handleException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                     Exception ex) {
        if (ex instanceof BusinessException) {
            return CommonRes.create(CommonRes.STATUS_FAILURE, ((BusinessException) ex).getCommonError());
        } else if (ex instanceof NoHandlerFoundException) {
            return CommonRes.create(CommonRes.STATUS_FAILURE, new CommonError(EmBusinessError.HANDLER_NOT_FOUND));
        } else if (ex instanceof ServletRequestBindingException) {
            return CommonRes.create(CommonRes.STATUS_FAILURE, new CommonError(EmBusinessError.BIND_EXCEPTION_ERROR));
        } else {
            return CommonRes.create(CommonRes.STATUS_FAILURE, new CommonError(EmBusinessError.UNKNOWN_ERROR));
        }

    }

}
