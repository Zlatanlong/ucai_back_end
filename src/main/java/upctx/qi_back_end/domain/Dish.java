package upctx.qi_back_end.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @CreatedDate
    private Date createTime;
    @LastModifiedDate
    private Date updateTime;
    @Column(columnDefinition = "tinyint default 0")
    private Integer ifDel = 0;

    private String name;
    private float price;
    private String imgUrl;
    private String description;
    private Integer point;
    @Column(columnDefinition = "float default 0")
    private float totalScore = 0;
    @Column(columnDefinition = "float default 0")
    private float saltyScore = 0;
    @Column(columnDefinition = "float default 0")
    private float sourScore = 0;
    @Column(columnDefinition = "float default 0")
    private float hotScore = 0;
    @Column(columnDefinition = "float default 0")
    private float sweetScore = 0;
    @Column(columnDefinition = "int default 0")
    private Integer evaluationTimes = 0;
    @Column(columnDefinition = "int default 0")
    private Integer likesCount = 0;
    @Column(columnDefinition = "int default 0")
    private Integer collectionCount = 0;
    @Column(columnDefinition = "tinyint default 0")
    private Integer ifHomeShow = 0;
    private Integer restaurantId;
    private String restaurantName;
    private Integer createMangerId;
    private Integer updateMangerId;
    private Integer deleteMangerId;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(float totalScore) {
        this.totalScore = totalScore;
    }

    public float getSaltyScore() {
        return saltyScore;
    }

    public void setSaltyScore(float saltyScore) {
        this.saltyScore = saltyScore;
    }

    public float getSourScore() {
        return sourScore;
    }

    public void setSourScore(float sourScore) {
        this.sourScore = sourScore;
    }

    public float getHotScore() {
        return hotScore;
    }

    public void setHotScore(float hotScore) {
        this.hotScore = hotScore;
    }

    public float getSweetScore() {
        return sweetScore;
    }

    public void setSweetScore(float sweetScore) {
        this.sweetScore = sweetScore;
    }

    public Integer getEvaluationTimes() {
        return evaluationTimes;
    }

    public void setEvaluationTimes(Integer evaluationTimes) {
        this.evaluationTimes = evaluationTimes;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public Integer getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(Integer collectionCount) {
        this.collectionCount = collectionCount;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getCreateMangerId() {
        return createMangerId;
    }

    public void setCreateMangerId(Integer createMangerId) {
        this.createMangerId = createMangerId;
    }

    public Integer getUpdateMangerId() {
        return updateMangerId;
    }

    public void setUpdateMangerId(Integer updateMangerId) {
        this.updateMangerId = updateMangerId;
    }

    public Integer getDeleteMangerId() {
        return deleteMangerId;
    }

    public void setDeleteMangerId(Integer deleteMangerId) {
        this.deleteMangerId = deleteMangerId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Integer getIfHomeShow() {
        return ifHomeShow;
    }

    public void setIfHomeShow(Integer ifHomeShow) {
        this.ifHomeShow = ifHomeShow;
    }
}
