package upctx.qi_back_end.exception;

import upctx.qi_back_end.exception.enums.ResultEnum;

/**
 * 我们自定义的异常，通过枚举类ResultEnum来构造
 */
/*继承RunTimeException，其继承了Exception，可进行事务回滚*/
public class MyException extends RuntimeException {
    private Integer code;

    /*给构造函数，使用结果枚举类进行初始化,此类的作用是,使异常的返回信息 也是统一格式
    * 自定义异常格式 , 是
    * */
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
