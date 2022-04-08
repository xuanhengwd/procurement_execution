package com.example.service;

import com.example.pojo.Dept;
import com.example.pojo.Role;
import com.example.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    /**
     * 查询用户
     * @return
     */
    List<User> selectUserAll();

    /**
     * 添加用户
     * @param user
     */
    void addUser(User user);




    /**
     * 根据id删除用户
     * @param id
     */
    void deleteUserById(int id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteUserByIds(int[] ids);

    /**
     * 根据id修改
     * @param user
     */
    void updateUserById(User user);


    /**
     * 条件查询
     * @param user
     * @return
     */
    List<User>selectUserByCondition(User user);

    int userCount();

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    User login(String username,String password);
}
