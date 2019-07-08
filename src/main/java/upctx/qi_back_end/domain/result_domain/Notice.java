package upctx.qi_back_end.domain.result_domain;

import java.util.Date;

/**
 * 把发给用户的通知封装成Notice类
 */
public class Notice {
    /**
     * 通知类型
     * 0为被点赞
     * 1为被回复
     */
    private Integer type;
    private Integer typeId;
    private String content; // 可能还有内容
    private String toReply; // 对哪条评论的通知
    private Integer fromUserId; //
    private String fromUserName;
    private Integer dishId;
    private Date time; // 时间

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getToReply() {
        return toReply;
    }

    public void setToReply(String toReply) {
        this.toReply = toReply;
    }

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
