package edu.shily.qqzone.service.impl;

import edu.shily.qqzone.dao.TopicDAO;
import edu.shily.qqzone.pojo.Reply;
import edu.shily.qqzone.pojo.Topic;
import edu.shily.qqzone.pojo.UserBasic;
import edu.shily.qqzone.service.ReplyService;
import edu.shily.qqzone.service.TopicService;
import edu.shily.qqzone.service.UserBasicService;

import java.util.List;

/**
 * @author Shily-zhang
 * @Description
 */
public class TopicServiceImpl implements TopicService {

    private TopicDAO topicDAO = null;
    //此处引用的是replyService,而不是replyDAO
    private ReplyService replyService;
    private UserBasicService userBasicService;

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return topicDAO.getTopicList(userBasic);
    }


    @Override
    public Topic getTopicById(Integer id) {
        Topic topic = getTopic(id);
        List<Reply> replyList = replyService.getReplyListByTopicId(topic.getId());
        topic.setReplyList(replyList);

        return topic;
    }

    @Override
    public Topic getTopic(Integer id) {
        Topic topic = topicDAO.getTopic(id);
        UserBasic author = topic.getAuthor();
        author = userBasicService.getUserBasicById(author.getId());
        topic.setAuthor(author);

        return topic;
    }

    @Override
    public void delTopic(Integer id) {
        Topic topic = topicDAO.getTopic(id);
        if (topic!=null){
            replyService.delReplyList(topic);

            topicDAO.delTopic(topic);
        }
    }
}
