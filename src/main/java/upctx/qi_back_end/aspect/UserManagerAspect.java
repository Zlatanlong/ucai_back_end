package upctx.qi_back_end.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import upctx.qi_back_end.domain.SuperManager;
import upctx.qi_back_end.exception.MyException;
import upctx.qi_back_end.exception.enums.ResultEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Aspect
@Component
public class UserManagerAspect {
    private final static Logger logger = LoggerFactory.getLogger(UserManagerAspect.class);

    @Pointcut("execution(public * upctx.qi_back_end.controller.UserManagerController.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Optional optional = (Optional) request.getSession().getAttribute("SuperManager");
        if (optional == null) {
            throw new MyException(ResultEnum.AUTHENTICATION_SUPER_ERROR);
        }
        SuperManager superManager = (SuperManager) optional.get();
        logger.info("superManager={}", superManager.toString());
        //url
        logger.info("url={}", request.getRequestURL());
        //ip
        logger.info("ip={}", request.getRemoteAddr());
    }
}
