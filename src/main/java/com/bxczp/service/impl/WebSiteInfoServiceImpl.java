package com.bxczp.service.impl;

import java.util.List;

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

import com.bxczp.entity.WebSiteInfo;
import com.bxczp.repository.WebSiteInfoRepository;
import com.bxczp.service.WebSiteInfoService;
import com.bxczp.util.StringUtil;

@Service("webSiteInfoService")
public class WebSiteInfoServiceImpl implements WebSiteInfoService {

    @Resource
    private WebSiteInfoRepository webSiteInfoRepository;

    @Override
    public List<WebSiteInfo> list(WebSiteInfo webSiteInfo, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "publishDate");
        return webSiteInfoRepository.findAll(new Specification<WebSiteInfo>() {

            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<WebSiteInfo> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                Predicate predicate = builder.conjunction();
                if (webSiteInfo != null) {
                    if (StringUtil.isNotEmpty(webSiteInfo.getInfo())) {
                        predicate.getExpressions()
                                .add(builder.like(root.get("info"), "%" + webSiteInfo.getInfo().trim() + "%"));
                    }
                }
                return predicate;
            }
        }, pageable).getContent();
    }

    @Override
    public Long count(WebSiteInfo webSiteInfo) {
        return webSiteInfoRepository.count(new Specification<WebSiteInfo>() {

            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<WebSiteInfo> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                Predicate predicate = builder.conjunction();
                if (webSiteInfo != null) {
                    if (StringUtil.isNotEmpty(webSiteInfo.getInfo())) {
                        predicate.getExpressions()
                                .add(builder.like(root.get("info"), "%" + webSiteInfo.getInfo().trim() + "%"));
                    }
                }
                return predicate;
            }
        });
    }

    @Override
    public List<WebSiteInfo> getByFilmId(Integer filmId) {
        return webSiteInfoRepository.getByFilmId(filmId);
    }

    @Override
    public List<WebSiteInfo> getByWebSiteId(Integer webSiteId) {
        return webSiteInfoRepository.getByWebSiteId(webSiteId);
    }

}
