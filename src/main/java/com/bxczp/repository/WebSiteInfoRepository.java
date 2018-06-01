package com.bxczp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.bxczp.entity.WebSiteInfo;

public interface WebSiteInfoRepository extends JpaRepository<WebSiteInfo, Integer>, JpaSpecificationExecutor<WebSiteInfo> {

    //由于Film WebSte 表和 webSiteInfo 存在外键关联关系，所以在删除 film 或 website 前，要先判断
    //这条数据能否被删除。
    
    // nativeQuery=true 指开启本地SQL语句查询， 默认是false 为HQL语句查询
    @Query(value="select * from t_websiteinfo where film_id = ?1", nativeQuery=true)
    public List<WebSiteInfo> getByFilmId(Integer filmId);
    
    @Query(value="select * from t_websiteinfo where web_site_id = ?1", nativeQuery=true)
    public List<WebSiteInfo> getByWebSiteId(Integer webSiteId);
}
