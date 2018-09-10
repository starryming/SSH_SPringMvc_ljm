package com.learn.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "usercard", schema = "ordersystemssh")
public class UsercardEntity {
    private int usercardId;
    private String hobby;
    private String personalitySignature;
    private UserEntity userEntity;
    private Integer sex;
    /** 双边一对一
     * optional = false 表示一个信息卡不能没有绑定账号
     * @return
     */
    /*@OneToOne(mappedBy = "usercardEntity",optional = false)//表示已经在UserEntity类中对usercardEntity做了映射了。一般有双向关联的都要设置mappedBy
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }*/

    @Id
    @Column(name = "usercard_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getUsercardId() {
        return usercardId;
    }

    public void setUsercardId(int usercardId) {
        this.usercardId = usercardId;
    }

    @Basic
    @Column(name = "hobby", nullable = true, length = 32)
    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Basic
    @Column(name = "personality_signature", nullable = true, length = 255)
    public String getPersonalitySignature() {
        return personalitySignature;
    }

    public void setPersonalitySignature(String personalitySignature) {
        this.personalitySignature = personalitySignature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsercardEntity that = (UsercardEntity) o;
        return usercardId == that.usercardId &&
                Objects.equals(hobby, that.hobby) &&
                Objects.equals(personalitySignature, that.personalitySignature) &&
                Objects.equals(sex, that.sex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usercardId, hobby, personalitySignature, sex);
    }

    @Basic
    @Column(name = "sex", nullable = true)
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
