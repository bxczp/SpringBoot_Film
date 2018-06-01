package com.bxczp.service;

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
    
    
}
