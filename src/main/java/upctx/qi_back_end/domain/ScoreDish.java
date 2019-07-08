package upctx.qi_back_end.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class ScoreDish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @CreatedDate
    private Date createTime;
    @LastModifiedDate
    private Date updateTime;
    @Column(columnDefinition = "tinyint default 0")
    private Integer ifDel = 0;

    @Column(nullable = false)
    private Integer userId;
    @Column(nullable = false)
    private Integer dishId;
    private Integer totalScore;
    private Integer saltyScore;
    private Integer sourScore;
    private Integer hotScore;
    private Integer sweetScore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIfDel() {
        return ifDel;
    }

    public void setIfDel(Integer ifDel) {
        this.ifDel = ifDel;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getSaltyScore() {
        return saltyScore;
    }

    public void setSaltyScore(Integer saltyScore) {
        this.saltyScore = saltyScore;
    }

    public Integer getSourScore() {
        return sourScore;
    }

    public void setSourScore(Integer sourScore) {
        this.sourScore = sourScore;
    }

    public Integer getHotScore() {
        return hotScore;
    }

    public void setHotScore(Integer hotScore) {
        this.hotScore = hotScore;
    }

    public Integer getSweetScore() {
        return sweetScore;
    }

    public void setSweetScore(Integer sweetScore) {
        this.sweetScore = sweetScore;
    }
}
