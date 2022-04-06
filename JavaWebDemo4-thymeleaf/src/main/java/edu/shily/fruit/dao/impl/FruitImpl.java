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
}
