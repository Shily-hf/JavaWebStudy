package edu.shily.qqzone.dao.impl;

import edu.shily.qqzone.dao.UserBasicDAO;
import edu.shily.qqzone.pojo.UserBasic;
import edu.shily.qqzone.myssm.basedao.BaseDAO;

import java.util.List;

/**
 * @author Shily-zhang
 * @Description 主要是DAO接口中的方法具体实现类
 */
public class UserBasicDAOImpl extends BaseDAO<UserBasic> implements UserBasicDAO {
    @Override
    public UserBasic getUserBasic(String loginId, String pwd) {
        return super.load("select * from t_user_basic where loginId = ? and pwd = ?",loginId,pwd);
    }

    @Override
    public List<UserBasic> getUserBasicList(UserBasic userBasic) {
        String sql = "select fid as id from t_friend where uid = ?";
        return super.executeQuery(sql,userBasic.getId());
    }

    @Override
    public UserBasic getUserBasicById(Integer id) {
        return load("select * from t_user_basic where id = ?",id);
    }
}
