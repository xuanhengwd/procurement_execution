package com.example.controller;


import com.example.pojo.Menu;
import com.example.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("menuController")
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private MenuService menuService;


    @RequestMapping("selectMenuAll")
    @ResponseBody
    List<Menu> selectMenuAll(){
        List<Menu> menus = menuService.selectMenuAll();
        return menus;
    }


    /**
     * 添加菜单
     * @param menu
     * @return
     */
    @RequestMapping(value = "addMenu",method = RequestMethod.POST)
    @ResponseBody
    String addMenu(Menu menu){
       menuService.addMenu(menu);
       return "true";
    }



}
