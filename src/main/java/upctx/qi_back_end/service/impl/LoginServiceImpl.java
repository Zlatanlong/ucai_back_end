package upctx.qi_back_end.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import upctx.qi_back_end.domain.DishManager;
import upctx.qi_back_end.domain.result_domain.Result;
import upctx.qi_back_end.domain.SuperManager;
import upctx.qi_back_end.domain.User;
import upctx.qi_back_end.exception.MyException;
import upctx.qi_back_end.exception.enums.ResultEnum;
import upctx.qi_back_end.repository.DishManagerRepository;
import upctx.qi_back_end.repository.SuperManagerRepository;
import upctx.qi_back_end.repository.UserRepository;
import upctx.qi_back_end.service.LoginService;
import upctx.qi_back_end.util.ResultUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DishManagerRepository dishManagerRepository;
    @Autowired
    private SuperManagerRepository superManagerRepository;

    @Override
    public Result<User> doLoginUser(String email, String password) {
//        Optional<User> userOptional = userRepository.findByName(username);
        Optional<User> userOptional;
        if (!(email.equals("")||email == null)) {
            userOptional= userRepository.findByEmail(email);
        } else {
            userOptional= userRepository.findByEmail("meiyouyouxiang");
        }
        if (userOptional.isPresent()) {
            // 判断用户有没有
            if (userOptional.get().getActiveStatus() != 0) {
//                判断激活与否
                if (userOptional.get().getPassword().equals(password)) {
//                    判断密码
                    if (userOptional.get().getIfDel() != 1) {
//                        判断是否封号
                        // 在session里添加登录信息
                        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                        HttpServletRequest request = attributes.getRequest();
                        request.getSession().setAttribute("User", userOptional.get().getId());
                        return ResultUtil.success(userOptional);
                    } else {
                        throw new MyException(ResultEnum.LOGIN_DEL_ERROR);
                    }
                } else {
                    throw new MyException(ResultEnum.LOGIN_PASSWORD_ERROR);
                }
            } else {
                throw new MyException(ResultEnum.LOGIN_ACTIVATE_ERROR);
            }
        } else {
            throw new MyException(ResultEnum.LOGIN_ID_ERROR);
        }
    }

    @Override
    public Result doLoginByWx(String openid, String nickname, String userHeadUrl) {
        Optional<User> userOptional;
        if (!(openid.equals("")||openid == null)) {
            userOptional= userRepository.findByOpenid(openid);
        } else {
            userOptional= userRepository.findByOpenid("meiyouyouxiang");
        }
        if (userOptional.isPresent()) {
            // 判断用户有没有,没有则直接注册,有则更新nickname
            User user = userOptional.get();
            user.setName("微信用户:"+nickname);
            user.setUserHeadUrl(userHeadUrl);

            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            request.getSession().setAttribute("User", user.getId());

            return ResultUtil.success(userRepository.save(user));
        } else {
            User user = new User();
            user.setOpenid(openid);
            user.setName("微信用户:"+nickname);
            user.setUserHeadUrl(userHeadUrl);
            user.setUserKind(1);
            user.setActiveStatus(0);
            String code = UUID.randomUUID().toString() + UUID.randomUUID().toString();
            user.setActiveCode(code);
            user = userRepository.save(user); //存入并得到user的id
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            request.getSession().setAttribute("User", user.getId());

            return ResultUtil.success(user);
//            System.out.println(userRepository.save(user));
//            return ResultUtil.success("true");
        }
    }

    @Override
    public Result<DishManager> doLoginDishManager(String account, String password) {
        Optional<DishManager> dishManager = dishManagerRepository.findByAccount(account);
        if (dishManager.isPresent()) {
            if (dishManager.get().getPassword().equals(password)) {
                if (dishManager.get().getIfDel() != 1) {
                    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                    HttpServletRequest request = attributes.getRequest();
                    request.getSession().setAttribute("DishManager", dishManager);
                    return ResultUtil.success(dishManager);
                } else {
                    throw new MyException(ResultEnum.LOGIN_DEL_ERROR);
                }
            } else {
                throw new MyException(ResultEnum.LOGIN_PASSWORD_ERROR);
            }
        } else {
            throw new MyException(ResultEnum.LOGIN_ID_ERROR);
        }
    }

    @Override
    public Result doLoginSuperManager(String account, String password) {
        Optional<SuperManager> superManager = superManagerRepository.findByAccount(account);
        if (superManager.isPresent()) {
            if (superManager.get().getPassword().equals(password)) {
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                HttpServletRequest request = attributes.getRequest();
                request.getSession().setAttribute("SuperManager", superManager);
                return ResultUtil.success(superManager);
            } else {
                throw new MyException(ResultEnum.LOGIN_PASSWORD_ERROR);
            }
        } else {
            throw new MyException(ResultEnum.LOGIN_ID_ERROR);
        }
    }
}
