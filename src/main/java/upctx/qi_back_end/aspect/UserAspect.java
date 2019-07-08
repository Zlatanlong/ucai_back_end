package upctx.qi_back_end.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import upctx.qi_back_end.domain.User;
import upctx.qi_back_end.util.UserUtil;

@Aspect
@Component
public class UserAspect {
    private final static Logger logger = LoggerFactory.getLogger(UserManagerAspect.class);
    
    @Pointcut("execution(public * upctx.qi_back_end.controller.userController..*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        Integer id = UserUtil.getUserId();
    }
}
