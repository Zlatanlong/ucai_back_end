package upctx.qi_back_end.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RestController
public class TestController {
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    JavaMailSender mailSender;

    @GetMapping("/test")
    public String test() {
        System.out.println(fromEmail);
        return "1";
    }

    @GetMapping("/send")
    public String send() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setSubject("菜菜注册激活");
            helper.setText("" +
                            "<h3>感谢您注册菜菜评网站，请点击链接激活</h3>" +
                            "<a href=\"http://localhost:8080/register/activate?code=" +
                            "123" +
                            "\">点击激活</a>" +
                            "<h3>如未自动完成激活，请手动复制一下链接到浏览器激活</h3>"
                    , true);
            helper.setTo("928339761@qq.com");
            helper.setFrom("long-txgc@foxmail.com");
            mailSender.send(mimeMessage);
            return "1";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "2";
        }
    }
}
