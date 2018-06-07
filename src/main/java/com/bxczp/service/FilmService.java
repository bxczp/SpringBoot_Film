package com.bxczp.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.bxczp.entity.Film;

/**
 * 电影service接口
 * @author bxczp
 *
 */
public interface FilmService {
    
    public Film getNextFilm(Integer id);
    
    public Film getPreFilm(Integer id);
    
    
    /**
     * 添加或修改电影
     * @param film
     */
    public void save(Film film);
    
    public List<Film> list(Film film, Integer page, Integer pageSize);
    
    public Long count(Film film);
    
    public Film findById(Integer id);
    
    public void delete(Integer id);
    
    public List<Film> randomList(Integer n);
}
