package com.bxczp.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.bxczp.entity.WebSite;
import com.bxczp.repository.WebSiteRepository;
import com.bxczp.service.WebSiteService;
import com.bxczp.util.StringUtil;

@Service("webSiteService")
public class WebSiteServiceImpl implements WebSiteService {

    @Resource
    private WebSiteRepository webSiteRepository;

    @Override
    public List<WebSite> list(WebSite webSite, Integer page, Integer pageSize) {
        // 根据id字段升序
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.ASC, "id");
        Page<WebSite> webSiteList = webSiteRepository.findAll(new Specification<WebSite>() {
            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<WebSite> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                Predicate predicate = builder.conjunction();
                if (webSite != null) {
                    if (StringUtil.isNotEmpty(webSite.getName())) {
                        predicate.getExpressions().add(builder.like(root.get("name"), "%"+webSite.getName()+"%"));
                    }
                    if (StringUtil.isNotEmpty(webSite.getUrl())) {
                        predicate.getExpressions().add(builder.like(root.get("url"), "%"+webSite.getUrl()+"%"));
                    }
                }
                return predicate;
            }
        }, pageable);
        return webSiteList.getContent();
    }

    @Override
    public Long count(WebSite webSite) {
        return webSiteRepository.count(new Specification<WebSite>() {
            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<WebSite> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                Predicate predicate = builder.conjunction();
                if (webSite != null) {
                    if (StringUtil.isNotEmpty(webSite.getName())) {
                        predicate.getExpressions().add(builder.like(root.get("name"), "%"+webSite.getName()+"%"));
                    }
                    if (StringUtil.isNotEmpty(webSite.getUrl())) {
                        predicate.getExpressions().add(builder.like(root.get("url"), "%"+webSite.getUrl()+"%"));
                    }
                }
                return predicate;
            }
        });
    }

    @Override
    public void save(WebSite webSite) {
        webSiteRepository.save(webSite);
    }

    @Override
    public void delete(Integer id) {
        webSiteRepository.deleteById(id);
    }

}
