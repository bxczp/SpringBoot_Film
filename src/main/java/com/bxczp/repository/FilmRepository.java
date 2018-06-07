package com.bxczp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.bxczp.entity.Film;

/**
 * 电影Repository接口
 * @author bxczp
 *
 */
public interface FilmRepository extends JpaRepository<Film, Integer>, JpaSpecificationExecutor<Film>{
    
    @Query(value="select * from t_film where id > ?1 order by id desc limit 1 ", nativeQuery=true)
    public Film getNextFilm(Integer id);
    
    @Query(value="select * from t_film where id < ?1 order by id desc limit 1 ", nativeQuery=true)
    public Film getPreFilm(Integer id);
    
    @Query(value="select * from t_film  order by rand() desc limit ?1 ", nativeQuery=true)
    public List<Film> randomList(Integer n);
    
    
    

}
