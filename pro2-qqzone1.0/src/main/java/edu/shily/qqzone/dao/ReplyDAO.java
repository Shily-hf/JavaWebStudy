package edu.shily.qqzone.dao;

import edu.shily.qqzone.pojo.Reply;
import edu.shily.qqzone.pojo.Topic;

import java.util.List;

/**
 * @author Shily-zhang
 * @Description 主要是和数据库打交道，定义一些操作数据库的方法
 */
public interface ReplyDAO {
    //获取指定日志的回复列表
    List<Reply> getReolyList(Topic topic);

    //添加回复
    void addReply(Reply reply);

    //删除回复
    void delReply(Integer id);
}
