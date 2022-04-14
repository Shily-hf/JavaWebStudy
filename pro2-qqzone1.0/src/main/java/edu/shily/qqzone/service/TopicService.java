package edu.shily.qqzone.service;

import edu.shily.qqzone.pojo.Topic;
import edu.shily.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @author Shily-zhang
 * @Description
 */
public interface TopicService {
    //查询特定用户的日志列表
    List<Topic> getTopicList(UserBasic userBasic);

    //
}
