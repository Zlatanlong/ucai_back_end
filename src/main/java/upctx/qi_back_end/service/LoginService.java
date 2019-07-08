package upctx.qi_back_end.service;

import upctx.qi_back_end.domain.result_domain.Result;

public interface LoginService {

    // 普通用户登录判断
    Result doLoginUser(String username, String password);

    // 微信用户登录判断;新用户直接添加一条并登陆，老用户更新信息并登陆
    Result doLoginByWx(String openid, String nickname, String userHeadUrl);

    // 菜品管理员登录判断
    Result doLoginDishManager(String account, String password);

    // 超管登录判断
    Result doLoginSuperManager(String account, String password);

    // 判断是否登录（本项目应该是前端判断登录，通过localStorage）
//    Result<User> ifLogined(HttpServletRequest request);

}
