package edu.shily.fruit.dao.impl;

import edu.shily.fruit.dao.FruitDAO;
import edu.shily.fruit.pojo.Fruit;
import edu.shily.myssm.basedao.BaseDAO;

import java.util.List;

/**
 * @author Shily-zhang
 * @Description
 */
public class FruitImpl extends BaseDAO<Fruit> implements FruitDAO {
    @Override
    public List<Fruit> getFruitList() {
        return super.executeQuery("select * from t_fruit");
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        return super.load("select * from t_fruit where fid = ?",fid);
    }

    @Override
    public void updateFruit(Fruit fruit) {
        String sql = "update t_fruit set fname = ?,price = ?,fcount = ?,remark = ? where fid = ?";
        super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark(),fruit.getFid());
    }

    @Override
    public void delFruit(Integer fid) {
        super.executeUpdate("delete from t_fruit where fid = ?",fid);
    }

    @Override
    public void addFruit(Fruit fruit) {
        String sql = "insert into t_fruit (fid,fname,price,fcount,remark) values (0,?,?,?,?)";
        super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark());
    }
}
