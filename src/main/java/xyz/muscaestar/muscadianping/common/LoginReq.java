package xyz.muscaestar.muscadianping.common;

import javax.validation.constraints.NotBlank;

/**
 * Created by muscaestar on 7/28/20
 *
 * @author muscaestar
 */
public class LoginReq {
    @NotBlank(message = "手机号不能为空")
    private String telephone;
    @NotBlank(message = "密码不能为空")
    private String password;

    public String getTelephone() {
        return telephone;
    }

    public String getPassword() {
        return password;
    }
}
