package xyz.muscaestar.muscadianping.common;

import javax.validation.constraints.NotBlank;

/**
 * Created by muscaestar on 7/29/20
 *
 * @author muscaestar
 */
public class SellerCreateReq {
    @NotBlank(message = "商家名不能为空")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
