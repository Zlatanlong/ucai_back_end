package upctx.qi_back_end.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import upctx.qi_back_end.domain.User;
import upctx.qi_back_end.exception.MyException;
import upctx.qi_back_end.exception.enums.ResultEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class UserUtil {
    /**
     * 从cookie中获取用户,同时判断登录状态
     * @param key
     * @return
     */
    public static Integer getUserId(String key) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Integer id = (Integer) request.getSession().getAttribute(key);
        if (id == null) {
            throw new MyException(ResultEnum.AUTHENTICATION_NOT_USER);
        } else {
            return id;
        }
    }

    public static Integer getUserId() {
        return getUserId("User");
    }
}
