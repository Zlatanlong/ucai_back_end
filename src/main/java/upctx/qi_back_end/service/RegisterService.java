package upctx.qi_back_end.service;

public interface RegisterService {
    // 发送激活邮件
    Boolean activateEmail(String code);

    //处理注册信息
    Boolean doRegister(String email, String password, String name);

    // 是否有邮箱
    Boolean hasEmail(String email);

    // 是否有姓名
    Boolean hasName(String name);
}
