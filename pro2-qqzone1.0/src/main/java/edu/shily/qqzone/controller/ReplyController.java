package edu.shily.qqzone.controller;

import edu.shily.qqzone.pojo.Reply;
import edu.shily.qqzone.pojo.Topic;
import edu.shily.qqzone.pojo.UserBasic;
import edu.shily.qqzone.service.ReplyService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * @author Shily-zhang
 * @Description
 */
public class ReplyController {

    private ReplyService replyService;

    public String addReply(String content,Integer topicId,HttpSession session){
        UserBasic author = (UserBasic) session.getAttribute("userBasic");
        Reply reply = new Reply(content, LocalDateTime.now(), author, new Topic(topicId));
        replyService.addReply(reply);

        return "redirect:topic.do?operate=topicDetail&id=" + topicId;
    }

    public String delReply(Integer replyId,Integer topicId){
        replyService.delReply(replyId);

        return "redirect:topic.do?operate=topicDetail&id=" + topicId;
    }
}
