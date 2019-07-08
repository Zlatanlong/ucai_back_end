package upctx.qi_back_end.util;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static org.junit.Assert.*;

public class SendRegMailUtilTest {
    @Autowired
    private SendRegMailUtil sendRegMailUtil;

    @Value("${my.test1}")
    private String test;

    @Test
    public void send(){
//        sendRegMailUtil.send("928339761@qq.com","a");
        System.out.println(test);
    }
}