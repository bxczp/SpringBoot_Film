package com.bxczp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bxczp.entity.Link;

/**
 * 友情链接管理
 * @author bxczp
 *
 */
public interface LinkRepository extends JpaRepository<Link, Integer> {

}
