package com.bxczp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bxczp.entity.WebSiteInfo;
import com.bxczp.repository.WebSiteInfoRepository;
import com.bxczp.service.WebSiteInfoService;

@Service("webSiteInfoService")
public class WebSiteInfoServiceImpl implements WebSiteInfoService {

    
    private WebSiteInfoRepository webSiteInfoRepository;
    
    @Override
    public List<WebSiteInfo> list() {
        
        return null;
    }

}
