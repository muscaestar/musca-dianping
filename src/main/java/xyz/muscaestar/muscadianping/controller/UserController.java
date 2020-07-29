package xyz.muscaestar.muscadianping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import xyz.muscaestar.muscadianping.common.*;
import xyz.muscaestar.muscadianping.model.UserModel;
import xyz.muscaestar.muscadianping.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

/**
 * Created by muscaestar on 7/27/20
 *
 * @author muscaestar
 */
@Controller("/user")
@RequestMapping("/user")
public class UserController {
    public static final String CURRENT_USER_SESSION = "CurrentUserSession";

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    UserService userService;

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }


    @RequestMapping("/thymeleaf")
    public ModelAndView testThymeleaf() {
        ModelAndView modelAndView = new ModelAndView("/test_temp");
        modelAndView.addObject("name", "Name Value");
        return modelAndView;
    }

    @RequestMapping("/get")
    @ResponseBody
    public CommonRes getUserById(@RequestParam("id") Integer id) throws BusinessException {
        UserModel userModel = userService.getUserById(id);
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.OBJECT_NOT_FOUND);
        }

        return CommonRes.create(userModel);
    }

    @RequestMapping("/register")
    @ResponseBody
    public CommonRes register(@Valid @RequestBody RegisterReq registerReq, BindingResult bindingResult) throws NoSuchAlgorithmException, BusinessException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrString(bindingResult));
        }
        UserModel userModel = new UserModel();
        userModel.setPassword(registerReq.getPassword());
        userModel.setGender(registerReq.getGender());
        userModel.setNickName(registerReq.getNickName());
        userModel.setTelephone(registerReq.getTelephone());

        UserModel savedUserModel = userService.register(userModel);
        return CommonRes.create(savedUserModel);
    }

    @RequestMapping("/login")
    @ResponseBody
    public CommonRes login(@Valid @RequestBody LoginReq loginReq, BindingResult bindingResult) throws BusinessException, NoSuchAlgorithmException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrString(bindingResult));
        }
        UserModel userModel = userService.login(loginReq.getTelephone(), loginReq.getPassword());
        httpServletRequest.getSession().setAttribute(CURRENT_USER_SESSION, userModel);
        return CommonRes.create(userModel);
    }

    @RequestMapping("/logout")
    @ResponseBody
    public CommonRes logout() {
        httpServletRequest.getSession().invalidate();
        return CommonRes.create(null);
    }

    @RequestMapping("/getcurrentuser")
    @ResponseBody
    public CommonRes getCurrentUser() {
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute(CURRENT_USER_SESSION);
        return CommonRes.create(userModel);
    }
}
