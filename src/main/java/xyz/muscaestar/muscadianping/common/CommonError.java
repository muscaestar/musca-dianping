package xyz.muscaestar.muscadianping.common;

/**
 * Created by muscaestar on 7/27/20
 *
 * @author muscaestar
 */
public class CommonError {
    // 错误码
    private Integer errCode;

    // 错误信息
    private String errMsg;

    public CommonError(EmBusinessError emBusinessError) {
        this.errCode = emBusinessError.getErrCode();
        this.errMsg = emBusinessError.getErrMsg();
    }

    public CommonError(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
