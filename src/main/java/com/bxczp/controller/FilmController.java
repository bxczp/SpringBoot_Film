package com.bxczp.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bxczp.entity.Film;
import com.bxczp.service.FilmService;
import com.bxczp.service.WebSiteInfoService;
import com.bxczp.util.PageUtil;
import com.bxczp.util.StringUtil;

@RequestMapping("/film")
@Controller
public class FilmController {

    @Resource
    private FilmService filmService;
    
    @Resource
    private WebSiteInfoService webSiteInfoService;

    /**
     * 搜索电影 简单模糊查询
     * 
     * @param s_film
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping("/search")
    public ModelAndView search(@Valid Film s_film, BindingResult bindingResult) throws Exception {
        ModelAndView mav = new ModelAndView();
        // bindingResult 是 @Valid 验证的 错误信息绑定
        // @Valid 验证 的规范 是 实体类上 定义 @NotEmpty
        if (bindingResult.hasErrors()) {
            mav.addObject("error", bindingResult.getFieldError().getDefaultMessage());
            mav.addObject("title", "首页");
            mav.addObject("mainPage", "film/indexFilm");
            mav.addObject("mainPageKey", "#f");
            mav.setViewName("index");
        } else {
            List<Film> filmList = filmService.list(s_film, 0, 32);
            mav.addObject("filmList", filmList);
            mav.addObject("title", s_film.getName());
            mav.addObject("mainPage", "film/result");
            mav.addObject("mainPageKey", "#f");
            mav.addObject("s_film", s_film);
            mav.addObject("total", filmList.size());
            mav.setViewName("index");
        }
        return mav;
    }

    @RequestMapping("/list/{id}")
    public ModelAndView list(@PathVariable(value = "id", required = false) String id) {
        ModelAndView modelAndView = new ModelAndView();
        List<Film> filmList = null;
        if (StringUtil.isNotEmpty(id)) {
            filmList = filmService.list(null, Integer.parseInt(id) - 1, 20);
        } else {
            id = "1";
            filmList = filmService.list(null, Integer.parseInt(id) - 1, 20);
        }
        long total = filmService.count(null);
        modelAndView.addObject("mainPage", "film/list");
        modelAndView.addObject("filmList", filmList);
        modelAndView.addObject("mainPageKey", "#f");
        modelAndView.addObject("title", "电影列表");
        modelAndView.addObject("total", total);
        modelAndView.addObject("pageCode", PageUtil.genPagination("/film/list", total, Integer.parseInt(id), 20));
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/{id}")
    public ModelAndView view(@PathVariable(value = "id") String id) {
        ModelAndView modelAndView = new ModelAndView();
        Film film = filmService.findById(Integer.parseInt(id));
        modelAndView.addObject("mainPage", "film/view");
        modelAndView.addObject("film", film);
        modelAndView.addObject("mainPageKey", "#f");
        modelAndView.addObject("pageCode", this.getUpAndDownPageCode(filmService.getPreFilm(Integer.parseInt(id)), filmService.getNextFilm(Integer.parseInt(id))));
        modelAndView.addObject("title", "电影详情");
        modelAndView.addObject("randomFilmList", filmService.randomList(8));
        modelAndView.addObject("webSiteInfoList", webSiteInfoService.getByFilmId(Integer.parseInt(id)));
        modelAndView.setViewName("index");
        return modelAndView;
    }

    /**
     * 获取下一个电影你和上一个电影
     * 
     * @param lastFilm
     * @param nextFilm
     * @return
     */
    private String getUpAndDownPageCode(Film lastFilm, Film nextFilm) {
        StringBuffer pageCode = new StringBuffer();
        if (lastFilm == null || lastFilm.getId() == null) {
            pageCode.append("<p>上一篇：没有了</p>");
        } else {
            pageCode.append("<p>上一篇：<a href='/film/" + lastFilm.getId() + "'>" + lastFilm.getTitle() + "</a></p>");
        }
        if (nextFilm == null || nextFilm.getId() == null) {
            pageCode.append("<p>下一篇：没有了</p>");
        } else {
            pageCode.append("<p>下一篇：<a href='/film/" + nextFilm.getId() + "'>" + nextFilm.getTitle() + "</a></p>");
        }
        return pageCode.toString();
    }

}
