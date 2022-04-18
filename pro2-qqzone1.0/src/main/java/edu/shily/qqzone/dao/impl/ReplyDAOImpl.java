package edu.shily.qqzone.dao.impl;

import edu.shily.qqzone.dao.ReplyDAO;
import edu.shily.qqzone.myssm.basedao.BaseDAO;
import edu.shily.qqzone.pojo.Reply;
import edu.shily.qqzone.pojo.Topic;

import java.util.List;

/**
 * @author Shily-zhang
 * @Description
 */
public class ReplyDAOImpl extends BaseDAO<Reply> implements ReplyDAO {
    @Override
    public List<Reply> getReplyList(Topic topic) {
        return executeQuery("select * from t_reply where topic = ?",topic.getId());
    }

    @Override
    public void addReply(Reply reply) {
        executeUpdate("insert into t_reply values(0,?,?,?,?)",reply.getContent(),reply.getReplyDate(),reply.getAuthor().getId(),reply.getTopic().getId());
    }

    @Override
    public void delReply(Integer id) {
        executeUpdate("delete from t_reply where id = ?" ,id);
    }
}
