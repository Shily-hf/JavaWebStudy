package edu.shily.fruit.service.impl;

import edu.shily.fruit.dao.FruitDAO;
import edu.shily.fruit.pojo.Fruit;
import edu.shily.fruit.service.FruitService;
import edu.shily.myssm.basedao.ConnUtil;

import java.util.List;

/**
 * @author Shily-zhang
 * @Description
 */
public class FruitServiceImpl implements FruitService {

    FruitDAO fruitDAO = null;

    @Override
    public List<Fruit> getFruitList(String keyword, Integer pageNo) {
        System.out.println("getFruitList ->" + ConnUtil.getConn());
        return fruitDAO.getFruitList(keyword, pageNo);
    }

    @Override
    public void addFruit(Fruit fruit) {
        fruitDAO.addFruit(fruit);
        Fruit fruit2 = fruitDAO.getFruitByFid(2);
        fruit2.setFcount(99);
        fruitDAO.updateFruit(fruit2);
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        return fruitDAO.getFruitByFid(fid);
    }

    @Override
    public void delFruit(Integer fid) {
        fruitDAO.delFruit(fid);
    }

    @Override
    public Integer getPageCount(String keyword) {
        System.out.println("getFruitList ->" + ConnUtil.getConn());
        int count = fruitDAO.getFruitCount(keyword);
        int pageCount = (count + 5 - 1) / 5;
        return pageCount;
    }

    @Override
    public void updateFruit(Fruit fruit) {
        fruitDAO.updateFruit(fruit);
    }
}
