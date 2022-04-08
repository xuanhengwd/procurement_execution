package com.example.controller;


import com.example.pojo.Menu;
import com.example.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("menuController")
@RequestMapping("/menu")
@CrossOrigin
public class MenuController {
    @Autowired
    private MenuService menuService;


    @RequestMapping("/selectMenuAll")
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
    @RequestMapping(value = "/addMenu",method = RequestMethod.POST)
    @ResponseBody
    String addMenu(Menu menu){
       menuService.addMenu(menu);
       return "true";
    }

    /**
     * 根据id删除菜单
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteMenuById",method = RequestMethod.POST)
    @ResponseBody
    String deleteMenuById(int id){
//        System.out.println("C:"+id);
        menuService.deleteMenuById(id);
        return "true";
    }

    /**
     * 根据id 来修改菜单的信息
     * @param menu
     * @return
     */
    @RequestMapping(value = "/updateMenuById",method = RequestMethod.POST)
    @ResponseBody
    String updateMenuById(Menu menu){
        menuService.updateMenuById(menu);
        return "true";
    }


    /**
     * 条件查询
     * @param menu
     * @return
     */
    @RequestMapping(value = "/selectMenuByCondition",method = RequestMethod.POST)
    @ResponseBody
    List<Menu> selectMenuByCondition(Menu menu){
//        System.out.println(menu);
        List<Menu> menus = menuService.selectMenuByCondition(menu);
        return menus;
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/deleteMenuByIds",method = RequestMethod.POST)
    @ResponseBody
    String deleteMenuByIds(int[] ids){
        menuService.deleteMenuByIds(ids);
        return "true";
    }

    /**
     * 菜单数量
     * @return
     */
    @RequestMapping(value = "/menuCount")
    @ResponseBody
    int menuCount(){
        return menuService.menuCount();
    }

}
