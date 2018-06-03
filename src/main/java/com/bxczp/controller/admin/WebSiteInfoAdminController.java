package com.bxczp.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bxczp.entity.WebSiteInfo;
import com.bxczp.run.StartupRunner;
import com.bxczp.service.WebSiteInfoService;
import com.bxczp.util.StringUtil;

@RestController
@RequestMapping("/admin/webSiteInfo")
public class WebSiteInfoAdminController {
    
    @Resource
    private StartupRunner startupRunner;
    
    @Resource
    private WebSiteInfoService webSiteInfoService;
    
    //会级联查询把website和film都查找出来
    @RequestMapping("/list")
    public Map<String, Object> list(WebSiteInfo webSiteInfo, @RequestParam(value="page", required=false)int page,
            @RequestParam(value="rows", required=false)int pageSize) {
        Map<String, Object> map = new HashMap<>();
        long count = webSiteInfoService.count(webSiteInfo);
        List<WebSiteInfo> webSiteInfoList = webSiteInfoService.list(webSiteInfo, page-1, pageSize);
        map.put("total", count);
        map.put("rows", webSiteInfoList);
        return map;
    }
    
    
    @RequestMapping("/delete")
    public Map<String, Object> delete(String ids) {
        Map<String, Object> map = new HashMap<>();
        if (StringUtil.isNotEmpty(ids)) {
            String[] id = ids.split(",");
            for (String i : id) {
                webSiteInfoService.delete(Integer.parseInt(i));
            }
            map.put("success", true);
        }
        startupRunner.loadData();
        return map;
    }
    
    @RequestMapping("/save")
    public Map<String, Object> save(WebSiteInfo webSiteInfo) {
        Map<String, Object> map = new HashMap<>();
        webSiteInfo.setPublishDate(new Date());
        webSiteInfoService.save(webSiteInfo);
        map.put("success", true);
        startupRunner.loadData();
        return map;
    }

}
