package com.bxczp.service;

import java.util.List;

import com.bxczp.entity.Film;

/**
 * 电影service接口
 * @author bxczp
 *
 */
public interface FilmService {
    
    /**
     * 添加或修改电影
     * @param film
     */
    public void save(Film film);
    
    public List<Film> list(Film film, Integer page, Integer pageSize);
    
    public Long count(Film film);
    
    public Film findById(Integer id);
    
    public void delete(Integer id);
}
