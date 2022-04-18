package edu.shily.qqzone.controller;

import edu.shily.qqzone.pojo.Reply;
import edu.shily.qqzone.pojo.Topic;
import edu.shily.qqzone.pojo.UserBasic;
import edu.shily.qqzone.service.ReplyService;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Shily-zhang
 * @Description
 */
public class ReplyController {

    private ReplyService replyService;

    public String addReply(String content,Integer topicId,HttpSession session){
        UserBasic author = (UserBasic) session.getAttribute("userBasic");
//        Reply reply = new Reply(content, LocalDateTime.now(), author, new Topic(topicId));
//        replyService.addReply(reply);

        return "redirect:topic.do?topic=topicDetail&id=" + topicId;
    }
}
