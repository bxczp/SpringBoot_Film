package com.bxczp.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bxczp.entity.WebSite;
import com.bxczp.service.WebSiteInfoService;
import com.bxczp.service.WebSiteService;
import com.bxczp.util.StringUtil;

@RestController
@RequestMapping("/admin/webSite")
public class WebSiteAdminController {
    
    @Resource
    private WebSiteService webSiteService;
    
    @Resource
    private WebSiteInfoService webSiteInfoService;
    
    @RequestMapping("/list")
    public Map<String, Object> list(WebSite webSite, @RequestParam(value="page", required=false)int page,
            @RequestParam(value="rows", required=false)int pageSize) {
        long count = webSiteService.count(webSite);
        List<WebSite> webSiteList = webSiteService.list(webSite, page-1, pageSize);
        Map<String, Object> map = new HashMap<>();
        //map的俩个key名是total 总记录数 和 rows 记录列
        map.put("total", count);
        map.put("rows", webSiteList);
        return map;
    }
    
    @RequestMapping("/save")
    public Map<String, Object> save(WebSite webSite) {
        Map<String, Object> map = new HashMap<>();
        webSiteService.save(webSite);
        map.put("success", true);
        return map;
    }
    
    
    @RequestMapping("/delete")
    public Map<String, Object> delete(String ids) {
        Map<String, Object> map = new HashMap<>();
        boolean flag = true;
        if (StringUtil.isNotEmpty(ids)) {
            String[] id = ids.split(",");
            for (String i : id) {
                if (webSiteInfoService.getByWebSiteId(Integer.parseInt(i)).isEmpty()) {
                    webSiteService.delete(Integer.parseInt(i));
                } else {
                    flag =false;
                }
            }
            if (flag) {
                map.put("success", true);
            } else {
                map.put("success", flag);
                map.put("errorMsg", "电影动态中存在数据，不能删除！");
            }
        }
        return map;
    }

}
