package com.bxczp.controller.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/webSiteInfo")
public class WebSiteInfoAdminController {
    
    
    @RequestMapping("/list")
    public Map<String, Object> list() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }
    
    

}
