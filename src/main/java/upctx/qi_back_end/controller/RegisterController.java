package upctx.qi_back_end.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import upctx.qi_back_end.domain.User;
import upctx.qi_back_end.domain.result_domain.Result;
import upctx.qi_back_end.exception.MyException;
import upctx.qi_back_end.exception.enums.ResultEnum;
import upctx.qi_back_end.repository.UserRepository;
import upctx.qi_back_end.service.RegisterService;
import upctx.qi_back_end.util.ResultUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;
    @Autowired
    private UserRepository userRepository;
    @Value("${spring.mail.active-success}")
    private String successPath;
    @Value("${spring.mail.active-failded}")
    private String faildedPath;


    @GetMapping("/hasemail")
    public Result<String> hasEmail(@RequestParam String email) {
        if (registerService.hasEmail(email)) {
            throw new MyException(ResultEnum.REG_HASEMAIL);
        } else {
            return ResultUtil.success("可以注册该邮箱");
        }
    }

    @GetMapping("/hasname")
    public Result<String> hasName(@RequestParam String name) {
        if (registerService.hasName(name)) {
            throw new MyException(ResultEnum.REG_HASNAME);
        } else {
            return ResultUtil.success("可以使用该昵称");
        }
    }

    @PostMapping("/doregister")
    public Result<String> doRegister(@RequestBody Map<String, String> map) throws Exception {
        if (registerService.doRegister(map.get("email"), map.get("password"), map.get("name"))) {
            return ResultUtil.success("注册成功");
        } else {
            throw new MyException(ResultEnum.REG_ERROR);
        }
    }

    @GetMapping("/activate")
    public void activate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        if (registerService.activateEmail(code)) {
            User user = userRepository.findByActiveCode(code).get();
            request.getSession().setAttribute("User", user);
            response.sendRedirect(successPath);
        } else {
            response.sendRedirect(faildedPath);
        }
    }
}
