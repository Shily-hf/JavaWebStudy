package edu.shily.qqzone.controller;

import edu.shily.qqzone.pojo.Topic;
import edu.shily.qqzone.pojo.UserBasic;
import edu.shily.qqzone.service.TopicService;
import edu.shily.qqzone.service.UserBasicService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Shily-zhang
 * @Description 控制逻辑，核心作用是控制后端与前端的交互
 */
public class UserController {

    private UserBasicService userBasicService;
    private TopicService topicService;

    public String login(String loginId, String pwd, HttpSession session){
        //1.登陆验证
        UserBasic userBasic = userBasicService.login(loginId,pwd);
        if (userBasic != null){
            //获取相关的好友信息
            List<UserBasic> friendList = userBasicService.getFriendList(userBasic);
            //获取相关的日志列表信息（但是，日志只有id,没有其他信息）
            List<Topic> topicList = topicService.getTopicList(userBasic);

            userBasic.setFriendList(friendList);
            userBasic.setTopicList(topicList);

            //userBasic保存的是登陆者的信息
            session.setAttribute("userBasic",userBasic);
            //friend保存的是当前进入的是谁的空间
            session.setAttribute("friend",userBasic);
            return "index";

        }else {
            return "login";
        }
    }

    public String friend(Integer id,HttpSession session){
        //根据id获取指定的用户信息
        UserBasic currentFriend = userBasicService.getUserBasicById(id);

        List<Topic> topicList = topicService.getTopicList(currentFriend);

        currentFriend.setTopicList(topicList);

        session.setAttribute("friend",currentFriend);

        return "index";
    }
}
