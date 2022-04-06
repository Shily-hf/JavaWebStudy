package edu.shily.fruit.dao;

import edu.shily.fruit.pojo.Fruit;

import java.util.List;

/**
 * @author Shily-zhang
 * @Description
 */
public interface FruitDAO {
    //获取所有的库存列表信息
    List<Fruit> getFruitList();
}
