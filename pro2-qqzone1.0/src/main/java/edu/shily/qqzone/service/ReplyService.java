package edu.shily.qqzone.service;

import edu.shily.qqzone.pojo.Reply;

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
}
