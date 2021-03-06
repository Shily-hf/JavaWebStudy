package edu.shily.qqzone.dao.impl;

import edu.shily.qqzone.dao.HostReplyDAO;
import edu.shily.qqzone.myssm.basedao.BaseDAO;
import edu.shily.qqzone.pojo.HostReply;

/**
 * @author Shily-zhang
 * @Description
 */
public class HostReplyDAOImpl extends BaseDAO<HostReply> implements HostReplyDAO {
    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
        return load("select * from t_host_reply where reply = ?",replyId);
    }

    @Override
    public void addHostReply(HostReply hostReply) {
        executeUpdate("insert into t_host_reply values(0,?,?,?)",hostReply.getContent(),hostReply.getHostReplyDate(),hostReply.getAuthor().getId());

    }

    @Override
    public void delHostReply(Integer id) {
        super.executeUpdate("delete from t_host_reply where id = ?",id);
    }
}
