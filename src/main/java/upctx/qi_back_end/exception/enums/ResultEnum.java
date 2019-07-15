package upctx.qi_back_end.exception.enums;

public enum ResultEnum {
    /*使用枚举类型，把所有的异常结果列出，进行异常统一处理*/
    /**
     * 0-100: 普通异常
     * 100-199: 登录异常
     * 200-299: 用户信息异常
     * 300-399: 菜异常
     * 400-499: 餐厅异常
     * 500-599: 回复异常
     * 600-699: 注册异常
     * 700-799: 评分异常
     * 800-899: ？？异常
     * 900-999: ？？异常
     * 1000-1099: 分页异常
     */
    /*定义了枚举实例，它的属性是类的私有变量，然后可以通过使用    该类的名称.枚举实例名.get(属性)
    * */
    UNKONW_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    AUTHENTICATION_NOT_USER(1, "用户没有登录"),
    AUTHENTICATION_SUPER_ERROR(2, "没有超管登录"),
    AUTHENTICATION_DISH_ERROR(3, "没有菜管登录"),
    LOGIN_PASSWORD_ERROR(101, "密码错误"),
    LOGIN_ACTIVATE_ERROR(102, "没有激活"),
    LOGIN_DEL_ERROR(103, "账号被删除"),
    LOGIN_ID_ERROR(104, "账号错误"),
    USER_NULL(201, "没用用户"),
    USER_DISHLIKE_NULL(202, "没有喜欢的菜"),
    USER_RESTAURANTLIKE_NULL(203, "没有喜欢的餐厅"),
    USER_REPLYLIKE_NULL(204, "没有喜欢的评论"),
    USER_DISHCOLLECTION_NULL(205, "没有收藏的菜"),
    USER_UPLOADHEADIMG_NULL(206, "上传头像失败"),
    USER_NOTSEENNOTICE_NULL(207, "没有未读信息"),
    USER_SEENNOTICE_NULL(208, "没有已读信息"),
    USER_NOTICEKIND_ERROR(209, "通知类型错误"),
    DISH_NULL(301, "没有菜"),
    DISH_FILE_UPLOAD_ERROR(302, "菜图片上传失败"),
    RESTAURANT_NULL(401, "没有餐厅"),
    RESTAURANT_DISH_NULL(402, "餐厅没有菜"),
    RESTAURANT_REPLY_NULL(403, "餐厅没有回复"),
    REPLY_NULL(501, "没有回复"),
    REPLY_FILE_UPLOAD_ERROR(502, "没有回复"),
    REG_HASEMAIL(601, "已经存在该邮箱的用户"),
    REG_HASNAME(602, "已经存在该用户名的用户"),
    REG_ERROR(603, "注册失败"),
    SCORE_NULL(700, "没有评分信息"),
    SCORE_HAS_ERROR(701, "已经有评分"),
    SCORE_ERROR(702, "评分保存失败"),
    SCORE_ERROR_DISH(703, "菜品更新评分失败"),
    PAGE_START_ERROR(1001, "起始页不能小于1"),
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

/*属性已经枚举列出,只有get方法,去获取属性,不再设置,是通过  枚举实例名.get属性()*/
    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
