package edu.shily.qqzone.service;

import edu.shily.qqzone.pojo.Reply;
import edu.shily.qqzone.pojo.Topic;

import java.util.List;

/**
 * @author Shily-zhang
 * @Description
 */
public interface ReplyService {
    //根据topic的id获取关联的所有回复
    List<Reply> getReplyListByTopicId(Integer id);

    //添加回复
    void addReply(Reply reply);

    //删除回复
    void delReply(Integer id);

    //删除的指定的日志关联的所有的回复
    void delReplyList(Topic topic);
}
