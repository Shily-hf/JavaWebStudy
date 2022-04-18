package edu.shily.qqzone.service.impl;

import edu.shily.qqzone.dao.HostReplyDAO;
import edu.shily.qqzone.dao.ReplyDAO;
import edu.shily.qqzone.pojo.HostReply;
import edu.shily.qqzone.pojo.Reply;
import edu.shily.qqzone.pojo.Topic;
import edu.shily.qqzone.pojo.UserBasic;
import edu.shily.qqzone.service.HostReplyService;
import edu.shily.qqzone.service.ReplyService;
import edu.shily.qqzone.service.UserBasicService;

import java.util.List;

/**
 * @author Shily-zhang
 * @Description
 */
public class ReplyServiceImpl implements ReplyService {

    private ReplyDAO replyDAO;
    //此处引入的是其他POJO对应的Service接口，而不是DAO接口
    //其他POJO对应的业务逻辑是分装在service层的，我需要调用别人的业务逻辑方法，而不去深入考虑人家内部的细节
    private HostReplyService hostReplyService;

    private UserBasicService userBasicService;

    @Override
    public List<Reply> getReplyListByTopicId(Integer topicId) {
        List<Reply> replyList = replyDAO.getReplyList(new Topic(topicId));
        for (int i = 0; i < replyList.size(); i++) {
            Reply reply = replyList.get(i);
            //1.将关联的作者设置进去
            UserBasic author = userBasicService.getUserBasicById(reply.getAuthor().getId());
            reply.setAuthor(author);

            //2.将关联的HostReply设置进去
            HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());
            reply.setHostReply(hostReply);
        }
        return replyList;
    }

    @Override
    public void addReply(Reply reply) {
        replyDAO.addReply(reply);
    }
}
