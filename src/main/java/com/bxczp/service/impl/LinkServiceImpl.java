package com.bxczp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bxczp.entity.Link;
import com.bxczp.repository.LinkRepository;
import com.bxczp.service.LinkService;

@Service("linkService")
public class LinkServiceImpl implements LinkService {

    @Resource
    private LinkRepository linkRepository;
    
    @Override
    public List<Link> list(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return linkRepository.findAll(pageable).getContent();
    }

    @Override
    public Long getCount() {
        return linkRepository.count();
    }

    @Override
    public void save(Link link) {
        linkRepository.save(link);
    }

    @Override
    public void delete(Integer linkId) {
        linkRepository.deleteById(linkId);
    }

    @Override
    public List<Link> listAll() {
        return linkRepository.findAll();
    }

}
