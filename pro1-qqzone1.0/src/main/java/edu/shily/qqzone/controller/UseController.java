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
public class UseController {

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

            session.setAttribute("userBasic",userBasic);
            return "index";

        }else {
            return "login";
        }
    }
}
