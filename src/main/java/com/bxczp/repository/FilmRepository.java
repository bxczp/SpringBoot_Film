package com.bxczp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bxczp.entity.Film;

/**
 * 电影Repository接口
 * @author bxczp
 *
 */
public interface FilmRepository extends JpaRepository<Film, Integer> {

}
