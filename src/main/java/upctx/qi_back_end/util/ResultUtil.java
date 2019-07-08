package upctx.qi_back_end.util;

import upctx.qi_back_end.domain.result_domain.Result;
import upctx.qi_back_end.exception.enums.ResultEnum;

public class ResultUtil {
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    /**
     * 失败带着返回参数
     *
     * @param code 失败码
     * @param msg  失败信息
     * @return
     */
    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
