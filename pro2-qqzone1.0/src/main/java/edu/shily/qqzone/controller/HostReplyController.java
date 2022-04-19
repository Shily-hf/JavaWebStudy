package edu.shily.qqzone.controller;

import edu.shily.qqzone.pojo.HostReply;
import edu.shily.qqzone.pojo.UserBasic;
import edu.shily.qqzone.service.HostReplyService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * @author Shily-zhang
 * @Description
 */
public class HostReplyController {

    private HostReplyService hostReplyService;

    public String addHostReply(String content, Integer topicId, HttpSession session){
        UserBasic author = (UserBasic) session.getAttribute("userBasic");
        HostReply hostReply = new HostReply(content, LocalDateTime.now(), author);
        hostReplyService.addHostReply(hostReply);

        return "redirect:topic.do?operate=topicDetail&id=" + topicId;
    }
}
