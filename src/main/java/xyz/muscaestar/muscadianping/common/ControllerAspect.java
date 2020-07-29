package xyz.muscaestar.muscadianping.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import xyz.muscaestar.muscadianping.controller.admin.AdminController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by muscaestar on 7/29/20
 *
 * @author muscaestar
 */
@Aspect
@Configuration
public class ControllerAspect {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    HttpServletResponse httpServletResponse;

    @Around("execution(* xyz.muscaestar.muscadianping.controller.admin.*.*(..)) " +
            "&& @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object validateBeforeAdminController(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        AdminPermission adminPermission = method.getAnnotation(AdminPermission.class);
        if (adminPermission == null) {
            return joinPoint.proceed();
        }

        // 需要权限，检查session判断是否登陆
        String email = (String) httpServletRequest.getSession().getAttribute(AdminController.CURRENT_ADMIN_SESSION);
        if (email == null) { // 未登陆
            if (adminPermission.produceType().equals("text/html")) {
                httpServletResponse.sendRedirect("/admin/admin/loginpage");
                return null;
            } else { // 前端需要非页面形式的返回
                CommonError commonError = new CommonError(EmBusinessError.ADMIN_SHOULD_LOGIN);
                return CommonRes.create(CommonRes.STATUS_FAILURE, commonError);
            }
        } else {
            return joinPoint.proceed();
        }
    }
}
