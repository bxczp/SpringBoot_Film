package com.bxczp.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bxczp.entity.Film;
import com.bxczp.service.FilmService;

@RequestMapping("/film")
@Controller
public class FilmController {

    @Resource
    private FilmService filmService;

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

}
