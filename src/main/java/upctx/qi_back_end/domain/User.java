package upctx.qi_back_end.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @CreatedDate
    private Date createTime;
    @LastModifiedDate
    private Date updateTime;
    @Column(columnDefinition = "tinyint default 0")
    private Integer ifDel = 0;

    @Column(unique = true)
    private String email;
    @Column(nullable = true)
    private String password;
    @Column(unique = false, nullable = false)
    private String name;

    private Integer collegeId;
    private String enrollmentYear;
    private String home;
    private String phonenum;
    private String qq;
    private String userHeadUrl;

    private String activeCode;
    @Column(columnDefinition = "tinyint default 0")
    private Integer activeStatus = 0;
//    微信登录的信息

    @Column(unique = true)
    private String openid;
    @Column(columnDefinition = "tinyint default 0")
    private Integer userKind = 0;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public String getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(String enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    public Integer getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Integer activeStatus) {

        this.activeStatus = activeStatus;
    }

    public String getUserHeadUrl() {
        return userHeadUrl;
    }

    public void setUserHeadUrl(String userHeadUrl) {
        this.userHeadUrl = userHeadUrl;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Integer getUserKind() {
        return userKind;
    }

    public void setUserKind(Integer userKind) {
        this.userKind = userKind;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", ifDel=" + ifDel +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", collegeId=" + collegeId +
                ", enrollmentYear='" + enrollmentYear + '\'' +
                ", home='" + home + '\'' +
                ", phonenum='" + phonenum + '\'' +
                ", qq='" + qq + '\'' +
                ", userHeadUrl='" + userHeadUrl + '\'' +
                ", activeCode='" + activeCode + '\'' +
                ", activeStatus=" + activeStatus +
                '}';
    }
}
