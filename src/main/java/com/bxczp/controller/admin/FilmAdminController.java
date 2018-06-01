package com.bxczp.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bxczp.entity.Film;
import com.bxczp.service.FilmService;
import com.bxczp.util.DateUtil;

//是RestController,会把Map自动封装成Json串 
@RestController
@RequestMapping("/admin/film")
public class FilmAdminController {
    
    @Value("${imageFilePath}")
    private String imageFilePath;
    
    @Resource
    private FilmService filmService;
    
    /**
     * 添加或修改电影信息
     * @param film
     * @param imageFile
     * @return
     * @throws Exception 
     */
    @RequestMapping("/save")
    public Map<String, Object> save(Film film, @RequestParam(name="imageFile")MultipartFile imageFile) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if(!imageFile.isEmpty()) {
            // 获取文件名
            String fileName = imageFile.getOriginalFilename();
            film.setImageName(fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String newFileName=DateUtil.getCurrentDateStr()+suffixName;
            FileUtils.copyInputStreamToFile(imageFile.getInputStream(), new File(imageFilePath+newFileName));
            film.setPublishDate(new Date());
            filmService.save(film);
            map.put("success", true);
        }
        return map;
    }
    
    
    @RequestMapping("/ckeditorUpload")
    public String ckeditorUpload(@RequestParam(name="upload")MultipartFile file, String CKEditorFuncNum) throws Exception {
        
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String newFileName=DateUtil.getCurrentDateStr()+suffixName;
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(imageFilePath+newFileName));
         
        StringBuffer sb=new StringBuffer();
        sb.append("<script type=\"text/javascript\">");
        sb.append("window.parent.CKEDITOR.tools.callFunction("+ CKEditorFuncNum + ",'" +"/static/filmImage/"+ newFileName + "','')");
        sb.append("</script>");
         
        return sb.toString();
    }
    
    
    @RequestMapping("/list")
    public Map<String, Object> list(Film film, @RequestParam(value="page", required=false)int page,
            @RequestParam(value="rows", required=false)int pageSize) {
        Map<String, Object> map = new HashMap<>();
        Long count = filmService.count(film);
        List<Film> filmList = filmService.list(film, page-1, pageSize);
        map.put("total", count);
        map.put("rows", filmList);
        return map;
    }
    

}
