package com.bxczp.service.impl;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.bxczp.entity.Film;
import com.bxczp.repository.FilmRepository;
import com.bxczp.service.FilmService;
import com.bxczp.util.StringUtil;

@Service("filmService")
public class FilmServiceImpl implements FilmService {

    
    @Resource
    private FilmRepository filmRepository;
    
    @Override
    public void save(Film film) {
        filmRepository.save(film);
    }

    @Override
    public List<Film> list(Film film, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC,"publishDate");
        return filmRepository.findAll(new Specification<Film>() {
            
            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Film> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                Predicate predicate = builder.conjunction();
                if (film != null) {
                    if (StringUtil.isNotEmpty(film.getName())) {
                        predicate.getExpressions().add(builder.like(root.get("name"), "%"+film.getName().trim()+"%"));
                    }
                    
                }
                return predicate;
            }
        }, pageable).getContent();
    }

    @Override
    public Long count(Film film) {
        return filmRepository.count(new Specification<Film>() {

            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Film> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                Predicate predicate = builder.conjunction();
                if (film != null) {
                    if (StringUtil.isNotEmpty(film.getName())) {
                        predicate.getExpressions().add(builder.like(root.get("name"), "%"+film.getName().trim()+"%"));
                    }
                }
                return predicate;
            }
            
        });
    }

    @Override
    public Film findById(Integer id) {
        return filmRepository.findById(id).get();
    }

    @Override
    public void delete(Integer id) {
        filmRepository.deleteById(id);
    }
    
    
    
}
