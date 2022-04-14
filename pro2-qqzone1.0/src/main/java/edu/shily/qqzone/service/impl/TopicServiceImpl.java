package edu.shily.qqzone.service.impl;

import edu.shily.qqzone.dao.TopicDAO;
import edu.shily.qqzone.pojo.Topic;
import edu.shily.qqzone.pojo.UserBasic;
import edu.shily.qqzone.service.TopicService;

import java.util.List;

/**
 * @author Shily-zhang
 * @Description
 */
public class TopicServiceImpl implements TopicService {

    private TopicDAO topicDAO = null;

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        //
        return topicDAO.getTopicList(userBasic);
    }
}
