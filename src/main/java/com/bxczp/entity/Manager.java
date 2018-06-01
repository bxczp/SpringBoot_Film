package com.bxczp.entity;

/**
 * 管理员实体 （不對應到數據庫）
 * @author bxczp
 *
 */
public class Manager {

    //编号
    private Integer id;
    
    //用户名
    private String userName;
    
    //密码
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
