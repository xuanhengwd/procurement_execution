package com.example.controller;

import com.example.pojo.Dept;
import com.example.pojo.Role;
import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller("userController")
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/selectUserAll")
    @ResponseBody
    List<User> selectUserAll(){
        List<User> userList = userService.selectUserAll();
        return userList;
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ResponseBody
    String addUser(User user){
        System.out.println(user);
        userService.addUser(user);
        return "true";
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteUserById",method = RequestMethod.POST)
    @ResponseBody
    String deleteUserById(int id){
        userService.deleteUserById(id);
        return "true";
    }
    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/deleteUserByIds",method = RequestMethod.POST)
    @ResponseBody
    String deleteUserByIds(int[] ids){
        userService.deleteUserByIds(ids);
        return "true";
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateUserById",method = RequestMethod.POST)
    @ResponseBody
    String updateUserById(User user){
        userService.updateUserById(user);
        return "true";
    }

    /**
     * 条件查询
     * @param user
     * @return
     */
    @RequestMapping(value = "/selectUserByCondition",method = RequestMethod.POST)
    @ResponseBody
    List<User> selectUserByCondition(User user){
        List<User> userList = userService.selectUserByCondition(user);
        return userList;
    }

    /**
     * 查询所有部门的数量
     * @return
     */
    @RequestMapping(value = "/userCount")
    @ResponseBody
    int userCount(){
        return userService.userCount();
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    User login(String username, String password, HttpSession session){

        User user = userService.login(username, password);
        System.out.println(user);
        //System.out.println(user);
        return user;
    }


}
