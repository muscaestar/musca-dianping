package xyz.muscaestar.muscadianping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.muscaestar.muscadianping.model.UserModel;
import xyz.muscaestar.muscadianping.service.UserService;

/**
 * Created by muscaestar on 7/27/20
 *
 * @author muscaestar
 */
@Controller("/user")
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }

    @RequestMapping("/get")
    @ResponseBody
    public UserModel getUserById(@RequestParam("id") Integer id) {
        return userService.getUserById(id);
    }
}
