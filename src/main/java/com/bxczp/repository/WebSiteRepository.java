package com.bxczp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bxczp.entity.WebSite;

public interface WebSiteRepository extends JpaRepository<WebSite , Integer>, JpaSpecificationExecutor<WebSite> {
    
}
