package xyz.muscaestar.muscadianping.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Encoder;
import xyz.muscaestar.muscadianping.common.AdminPermission;
import xyz.muscaestar.muscadianping.common.BusinessException;
import xyz.muscaestar.muscadianping.common.EmBusinessError;
import xyz.muscaestar.muscadianping.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by muscaestar on 7/29/20
 *
 * @author muscaestar
 */
@Controller("/admin/admin")
@RequestMapping("/admin/admin")
public class AdminController {

    public static final String CURRENT_ADMIN_SESSION = "CURRENT_ADMIN_SESSION";

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.encryptPassword}")
    private String adminEncPassword;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    UserService userService;

    @RequestMapping("/index")
    @AdminPermission
    public ModelAndView getAdminIndex() {
        ModelAndView modelAndView = new ModelAndView("/admin/admin/index");
        modelAndView.addObject("userCount", userService.countAllUser());
        modelAndView.addObject("CONTROLLER_NAME", "admin");
        modelAndView.addObject("ACTION_NAME", "index");
        return modelAndView;
    }

    @RequestMapping("/loginpage")
    public ModelAndView getLoginPage() {
        ModelAndView modelAndView = new ModelAndView("/admin/admin/login");
        return modelAndView;
    }

    @RequestMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password) throws BusinessException, NoSuchAlgorithmException {
        if (StringUtils.isEmpty(email) || password.isEmpty()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "邮箱密码不能为空");
        }
        if (email.equals(this.adminEmail) && encodeByMD5(password).equals(this.adminEncPassword)) {
            httpServletRequest.getSession().setAttribute(CURRENT_ADMIN_SESSION, email);
            return "redirect:/admin/admin/index";
        } else {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "用户名密码错误");
        }
    }

    private String encodeByMD5(String str) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(md5.digest(str.getBytes(StandardCharsets.UTF_8)));
    }
}
