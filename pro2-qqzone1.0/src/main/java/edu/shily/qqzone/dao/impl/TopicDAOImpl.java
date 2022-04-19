package edu.shily.qqzone.dao.impl;

import edu.shily.qqzone.dao.TopicDAO;
import edu.shily.qqzone.myssm.basedao.BaseDAO;
import edu.shily.qqzone.pojo.Topic;
import edu.shily.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @author Shily-zhang
 * @Description 主要是DAO接口中的方法具体实现类
 */
public class TopicDAOImpl extends BaseDAO<Topic> implements TopicDAO {
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return super.executeQuery("select * from t_topic where author = ?",userBasic.getId());
    }

    @Override
    public void addTopic(Topic topic) {

    }

    @Override
    public void delTopic(Topic topic) {
        executeUpdate("delete from t_topic where id = ?",topic.getId());
    }

    @Override
    public Topic getTopic(Integer id) {
        return load("select * from t_topic where id = ?",id);
    }
}
