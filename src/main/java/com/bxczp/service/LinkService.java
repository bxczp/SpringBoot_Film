package com.bxczp.service;

import java.util.List;

import com.bxczp.entity.Link;

public interface LinkService {
    
    public List<Link> list(Integer page, Integer pageSize);
    
    public List<Link> listAll();
    
    public Long getCount();
    
    public void save(Link link);
    
    public void delete(Integer linkId);
}
