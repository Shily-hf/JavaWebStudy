package edu.shily.qqzone.dao;

import edu.shily.qqzone.pojo.HostReply;

/**
 * @author Shily-zhang
 * @Description 主要是和数据库打交道，定义一些操作数据库的方法
 */
public interface HostReplyDAO {
    //根据replyId查询关联的HostReply实体
    HostReply getHostReplyByReplyId(Integer replyId);
}
