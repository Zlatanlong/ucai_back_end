package upctx.qi_back_end.domain.result_domain;
/*HTTP请求的最外层对象，规范化返回的信息，结果对象，在其他地方进行调用处理.对返回网页的信息进行格式化,统一格式*/
public class Result<T> {
    /**
     * 错误码.
     */
    private Integer code;

    /**
     * 提示信息.
     */
    private String msg;

    /**
     * 具体的内容.
     */
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
