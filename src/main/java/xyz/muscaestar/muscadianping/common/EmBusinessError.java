package xyz.muscaestar.muscadianping.common;

/**
 * Created by muscaestar on 7/27/20
 *
 * @author muscaestar
 */
public enum EmBusinessError {
    // 10000 - 通用错误信息
    OBJECT_NOT_FOUND(10001, "找不到请求对象"),
    UNKNOWN_ERROR(10002, "未知错误"),
    HANDLER_NOT_FOUND(10003, "找不到执行路径"),
    BIND_EXCEPTION_ERROR(10004, "请求参数错误"),
    PARAMETER_VALIDATION_ERROR(10005, "请求参数校验失败"),


    // 20000 - 用户相关错误
    REGISTER_DUP_FAIL(20001, "用户已存在"),
    LOGIN_FAIL(20002, "手机号或密码不正确");


    private Integer errCode;
    private String errMsg;

    EmBusinessError(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
