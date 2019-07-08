package upctx.qi_back_end.exception;

import upctx.qi_back_end.exception.enums.ResultEnum;

/**
 * 我们自定义的异常，通过枚举类ResultEnum来构造
 */
public class MyException extends RuntimeException {
    private Integer code;

    public MyException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
