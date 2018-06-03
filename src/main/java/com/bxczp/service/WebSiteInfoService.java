package com.bxczp.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.bxczp.entity.WebSiteInfo;

public interface WebSiteInfoService {
    
    
    public List<WebSiteInfo> list(WebSiteInfo webSiteInfo, int page, int pageSize);

    public Long count(WebSiteInfo webSiteInfo);
    
    public List<WebSiteInfo> getByFilmId(Integer filmId);
    
    public List<WebSiteInfo> getByWebSiteId(Integer webSiteId);
    
    public void save(WebSiteInfo webSiteInfo);
    
    public void delete(Integer id);
    
    
}
