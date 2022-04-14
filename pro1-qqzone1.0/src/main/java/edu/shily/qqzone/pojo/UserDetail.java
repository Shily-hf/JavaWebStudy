package edu.shily.qqzone.pojo;

import java.time.LocalDateTime;

/**
 * @author Shily-zhang
 * @Description 描述User的个人详细信息
 */
public class UserDetail {
    private Integer id;
    private String realName;
    private String tel;
    private String email;
    private LocalDateTime bitrh;
    private String star;

    public UserDetail() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getBitrh() {
        return bitrh;
    }

    public void setBitrh(LocalDateTime bitrh) {
        this.bitrh = bitrh;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }
}


//父类：java.util.Date 年月日时分秒
//子类：java.sql.Date  年月日
//子类：java.sql.Time 时分秒