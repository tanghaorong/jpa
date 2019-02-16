package com.thr.entity;

import javax.persistence.*;


@Entity
@Table(name = "t_user")
public class User extends PageEntity{

    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 100)
    private String userName;

    @Column(length = 100)
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
