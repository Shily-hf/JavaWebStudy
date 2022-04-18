package edu.shily.qqzone.controller;

import edu.shily.qqzone.pojo.Topic;
import edu.shily.qqzone.service.TopicService;

import javax.servlet.http.HttpSession;

/**
 * @author Shily-zhang
 * @Description
 */
public class TopicController {

    private TopicService topicService;

    public String topicDetail(Integer id, HttpSession session){
        Topic topic = topicService.getTopicById(id);

        session.setAttribute("topic",topic);
        return "frames/detail";
    }
}
