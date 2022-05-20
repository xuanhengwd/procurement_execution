package com.example.service.impl;

import com.example.mapper.UserMapper;
import com.example.pojo.User;
import com.example.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> selectUserAll(int curPage,int pageCount) {
        PageHelper.startPage(curPage, pageCount);
        return userMapper.selectUserAll();
    }

    @Override
    public User selectById(int id) {
        User user = userMapper.selectUserById(id);
        return user;
    }

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public void deleteUserById(int id) {
        userMapper.deleteUserById(id);
    }

    @Override
    public void deleteUserByIds(int[] ids) {
        userMapper.deleteUserByIds(ids);
    }

    @Override
    public void updateUserById(User user) {
       userMapper.updateUserById(user);
    }

    @Override
    public List<User> selectUserByCondition(User user,int curPage,int pageCount) {
        String userNo = user.getUserNo();
        String userName = user.getUserName();
        String name = user.getName();
        if (userNo != null && userNo.length() > 0) {
            user.setUserNo("%"+userNo+"%");
        }
        if (userName != null && userName.length() > 0) {
            user.setUserName("%"+userName+"%");
        }
        if (name != null && name.length() > 0) {
            user.setName("%"+name+"%");
        }
        PageHelper.startPage(curPage,pageCount);
        List<User> userList = userMapper.selectUserByCondition(user);
        return userList;
    }

    @Override
    public int userCount() {
        return userMapper.userCount();
    }

    @Override
    public User login(String username) {
        User user = userMapper.login(username);
        return user;
    }

    @Override
    public User selectUserByNo(String userNo) {

        User user = userMapper.selectUserByNo(userNo);
        return user;
    }
}
