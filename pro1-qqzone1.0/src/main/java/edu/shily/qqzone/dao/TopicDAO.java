package edu.shily.qqzone.dao;

import edu.shily.qqzone.pojo.Topic;
import edu.shily.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @author Shily-zhang
 * @Description 主要是和数据库打交道，定义一些操作数据库的方法
 */
public interface TopicDAO {

    //获取指定用户的日志列表
    List<Topic> getTopicList(UserBasic userBasic);

    //添加日志
    void addTopic(Topic topic);

    //删除日志
    void delTopic(Topic topic);

    //获取特定日志信息
    Topic getTopic(Integer id);


}
