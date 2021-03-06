package com.example.mapper;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface UserMapper {
    /**
     * 查询用户
     * @return
     */
    List<User> selectUserAll();


    User selectUserById(int id);

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
    void deleteUserByIds(@Param("ids") int[] ids);

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

    User login(@Param("username") String username);


    //根据编号查询
    User selectUserByNo(String userNo);



}
