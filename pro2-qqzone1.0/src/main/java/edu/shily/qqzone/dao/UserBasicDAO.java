package edu.shily.qqzone.dao;

import edu.shily.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @author Shily-zhang
 * @Description 主要是和数据库打交道，定义一些操作数据库的方法
 */
public interface UserBasicDAO {

    //根据帐号和密码获取特定用户信息
    UserBasic getUserBasic(String loginId, String pwd);

    //获取指定用户的所有好友列表
    List<UserBasic> getUserBasicList(UserBasic userBasic);

    //根据id查询UserBasic的信息
    UserBasic getUserBasicById(Integer id);
}
