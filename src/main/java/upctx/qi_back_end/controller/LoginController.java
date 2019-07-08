package upctx.qi_back_end.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import upctx.qi_back_end.domain.result_domain.Result;
import upctx.qi_back_end.service.LoginService;
import upctx.qi_back_end.util.ResultUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping("/user")
    public Result loginUser(@RequestParam String email,
                            @RequestParam String password) {
        return loginService.doLoginUser(email, password);
    }

    @GetMapping("/wxuser")
    public Result loginWxUser(@RequestParam String openid,
                            @RequestParam String nickname,
                              @RequestParam String avatarUrl) {
        return loginService.doLoginByWx(openid, nickname, avatarUrl);
    }

    @GetMapping("/out/user")
    public Result outUser() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        request.getSession().removeAttribute("User");
        return ResultUtil.success();
    }

    @GetMapping("/dishmanager")
    public Result loginDishManager(@RequestParam String account,
                                   @RequestParam String password) {
        return loginService.doLoginDishManager(account, password);
    }

    @GetMapping("/supermanager")
    public Result loginSuperManager(@RequestParam String account,
                                    @RequestParam String password) {
        return loginService.doLoginSuperManager(account, password);
    }

}
