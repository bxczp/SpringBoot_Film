package com.bxczp.service;

import java.util.List;

import com.bxczp.entity.WebSite;

public interface WebSiteService {

    
    public List<WebSite> list(WebSite webSite, Integer page, Integer pageSize);
    
    public Long count(WebSite webSite);
    
    public void save(WebSite webSite);
    
    public void delete(Integer id);
    
}
