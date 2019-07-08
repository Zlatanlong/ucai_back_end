package upctx.qi_back_end.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class SendRegMailUtil {
    @Autowired
    JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${spring.mail.api-path}")
    private String apiPath;

    /**
     * 对指定用户发送激活邮件
     *
     * @param to      用户的email地址
     * @param content 激活码
     * @return
     */
    public Boolean send(String to, String content) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setSubject("菜菜注册激活");
            helper.setText("<center>" +
                            "<h3>感谢您注册菜菜评网站，请点击链接激活</h3></br>" +
                            "<a href=\"" + apiPath + "/register/activate?code=" +
                            content +
                            "\">" + apiPath + "/register/activate?code=" + content + "</a></br>" +
                            "<p>如未能自动完成跳转，请手动复制一链接到浏览器激活</p></br>" +
                            "<p>开发者联系方式：QQ：928339761</p>" +
                            "</center>"
                    , true);
            helper.setTo(to);
            helper.setFrom("long-txgc@foxmail.com");
            mailSender.send(mimeMessage);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
