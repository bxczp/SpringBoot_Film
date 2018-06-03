package com.bxczp.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bxczp.entity.Link;
import com.bxczp.run.StartupRunner;
import com.bxczp.service.LinkService;
import com.bxczp.util.StringUtil;

//Ajax交互
@RestController
@RequestMapping("/admin/link")
public class LinkAdminController {
    
    @Resource
    private StartupRunner startupRunner;
    
    @Resource
    private LinkService linkService;
    
    @RequestMapping("/list")
    public Map<String, Object> list(@RequestParam(value="page", required=false)int page,
            @RequestParam(value="rows", required=false)int pageSize) {
        Long count = linkService.getCount();
        //spring封装的pageable中的page是从0开始的，所以传过来的参数page要-1
        List<Link> linkList = linkService.list(page-1, pageSize);
        Map<String, Object> map = new HashMap<>();
        //map的俩个key名是total 总记录数 和 rows 记录列
        map.put("total", count);
        map.put("rows", linkList);
        return map;
    }
    
    @RequestMapping("/save")
    public Map<String, Object> save(Link link) {
        Map<String, Object> map = new HashMap<>();
        linkService.save(link);
        map.put("success", true);
        startupRunner.loadData();
        return map;
    }
    
    @RequestMapping("/delete")
    public Map<String, Object> delete(String ids) {
        Map<String, Object> map = new HashMap<>();
        if (StringUtil.isNotEmpty(ids)) {
            String[] id = ids.split(",");
            for (String s : id) {
                linkService.delete(Integer.parseInt(s));
            }
            map.put("success", true);
        }
        startupRunner.loadData();
        return map;
    }
    

}
