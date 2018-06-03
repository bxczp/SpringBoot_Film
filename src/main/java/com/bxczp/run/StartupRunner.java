package com.bxczp.run;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bxczp.entity.Film;
import com.bxczp.service.FilmService;
import com.bxczp.service.LinkService;
import com.bxczp.service.WebSiteInfoService;
import com.bxczp.service.WebSiteService;

@Component("startupRunner")
//在生成所有bean之后执行CommandLineRunner
public class StartupRunner implements CommandLineRunner, ServletContextListener{
    
    private ServletContext application = null;
     
    @Resource
    private FilmService filmService;
    
    @Resource
    private WebSiteInfoService webSiteInfoService;
    
    @Resource
    private LinkService linkService;
    
    @Resource
    private WebSiteService webSiteService;

    @Override
    public void run(String... args) throws Exception {
        this.loadData();
    }
    
    
    public void loadData() {
        Film film = new Film();
        film.setHot(1);
        application.setAttribute("newestHotFilmList", filmService.list(film, 0, 10));
        application.setAttribute("newestFilmList", filmService.list(null, 0, 10));
        application.setAttribute("newestIndexHotFilmList", filmService.list(film, 0, 32));
        
        application.setAttribute("newestWebSiteList", webSiteService.newestList(0, 10));
        
        application.setAttribute("newestInfoList", webSiteInfoService.list(null, 10, 10));
        
        application.setAttribute("newestLinkList", linkService.listAll());
    }
    
    
    
    

    
    //获取servletContext
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        application = sce.getServletContext();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }

}
