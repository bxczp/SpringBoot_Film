package com.bxczp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * 电影实体
 * @author bxczp
 *
 */
@Entity
@Table(name="t_film")
public class Film {
    
    //主键
    private Integer id;
    
    //电影名称
    private String name;
    
    //电影题目
    private String title;
    
    //电影内容
    private String content;
    
    //图片名称
    private String imageName;
    
    //是否热门（1为热门 0为非热门）
    private Integer hot;
    
    //发布日期
    private Date publishDate;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(length=500)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(length=500)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Lob
    @Column(columnDefinition="TEXT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(length=300)
    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
    
    

}
