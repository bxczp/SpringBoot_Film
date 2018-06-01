package com.bxczp.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bxczp.entity.Film;
import com.bxczp.repository.FilmRepository;
import com.bxczp.service.FilmService;

@Service("filmService")
public class FilmServiceImpl implements FilmService {

    
    @Resource
    private FilmRepository filmRepository;
    
    @Override
    public void save(Film film) {
        filmRepository.save(film);
    }

}
