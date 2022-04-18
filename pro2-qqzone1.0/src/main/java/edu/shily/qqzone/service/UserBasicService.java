package edu.shily.qqzone.service;

import edu.shily.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @author Shily-zhang
 * @Description 专注于业务逻辑
 */
public interface UserBasicService {
    //登陆验证的方法
    UserBasic login(String loginId,String pwd);

    //获取好友信息列表
    List<UserBasic> getFriendList(UserBasic userBasic);

    //根据id获取指定用户信息
    UserBasic getUserBasicById(Integer id);

}
