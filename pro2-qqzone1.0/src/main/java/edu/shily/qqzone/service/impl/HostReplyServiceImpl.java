package edu.shily.qqzone.service.impl;

import edu.shily.qqzone.dao.HostReplyDAO;
import edu.shily.qqzone.pojo.HostReply;
import edu.shily.qqzone.service.HostReplyService;

/**
 * @author Shily-zhang
 * @Description
 */
public class HostReplyServiceImpl implements HostReplyService {

    private HostReplyDAO hostReplyDAO;

    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
        return hostReplyDAO.getHostReplyByReplyId(replyId);
    }
}
