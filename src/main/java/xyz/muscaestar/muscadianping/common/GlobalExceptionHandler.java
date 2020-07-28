package xyz.muscaestar.muscadianping.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
        } else {
            return CommonRes.create(CommonRes.STATUS_FAILURE, new CommonError(EmBusinessError.UNKNOWN_ERROR));
        }

    }

}
