package xyz.muscaestar.muscadianping.common;

/**
 * Created by muscaestar on 7/27/20
 *
 * @author muscaestar
 */
public class CommonRes {

    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_FAILURE = "failure";

    // 状态码，success/failure
    private String status;

    // 返回对象，json类数据/错误对象
    private Object data;

    // 通用创建方法
    public static CommonRes create(Object data) {
        return CommonRes.create(STATUS_SUCCESS, data);
    }
    // 通用创建方法
    public static CommonRes create(String status, Object data) {
        return new CommonRes(status, data);
    }

    public CommonRes(String status, Object data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

