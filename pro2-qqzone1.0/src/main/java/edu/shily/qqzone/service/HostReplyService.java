package edu.shily.qqzone.service;

import edu.shily.qqzone.pojo.HostReply;

/**
 * @author Shily-zhang
 * @Description
 */
public interface HostReplyService {
    HostReply getHostReplyByReplyId(Integer replyId);

    //添加主人回复
    void addHostReply(HostReply hostReply);

    void delHostReply(Integer id);
}
