package xyz.muscaestar.muscadianping.common;

/**
 * Created by muscaestar on 7/27/20
 *
 * @author muscaestar
 */
public class BusinessException extends Exception {
    private CommonError commonError;

    public BusinessException(EmBusinessError emBusinessError) {
        super();
        this.commonError = new CommonError(emBusinessError);
    }

    public BusinessException(EmBusinessError emBusinessError, String errMsg) {
        super();
        this.commonError = new CommonError(emBusinessError);
        commonError.setErrMsg(errMsg);
    }

    public CommonError getCommonError() {
        return commonError;
    }
}
