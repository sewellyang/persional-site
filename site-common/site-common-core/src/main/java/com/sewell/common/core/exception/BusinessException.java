package com.sewell.common.core.exception;

import com.sewell.common.core.exception.constant.IErrCode;

public class BusinessException extends RuntimeException{

    private final int code;

    public BusinessException(String message) {
        super(message);
        this.code = 1000;
    }

    /**
     * 设置错误编码和错误消息来创建异常
     * <p/>
     * 并不是所有异常都需要错误编码，只有需要程序判断并自动处理的异常才使用错误编码<br/>
     * 如用户登录页面需要输入手机号和验证码，则服务器判断登录失败后，有可能是手机号错误，也有可能是验证码错误<br/>
     * 这时服务器必须告知客户端程序是对应的业务错误码,这样客户端程序可根据编码来高亮提示用户<br/>
     * 而如果是用户已经被冻结而不能登录，客户端程序不需要额外的判断，则无需验证码，直接通过msg提示错误内容即可
     *
     * @param code    错误编码
     * @param message 错误消息
     */
    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 以枚举形式参数传入
     * @param errCode
     */
    public BusinessException(IErrCode errCode) {
        super(errCode.getMessage());
        this.code = errCode.getCode();
    }


    public static BusinessException of(IErrCode errCode){
        return new BusinessException(errCode);
    }
}
