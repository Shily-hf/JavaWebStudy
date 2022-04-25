package edu.shily.book.pojo;

import java.util.Map;
import java.util.Set;

/**
 * @author Shily-zhang
 * @Description
 */
public class Cart {
    private Map<Integer,CartItem> cartItemMap;      //购物车中购物车项的集合，这个Map集合中的key是Book的id
    private Double totalMoney;                      //购物车中的总金额
    private Integer totalCount;                     //购物车中购物项的数量
    private Integer totalbookCount;                 //购物车单项的书本的总数量，而不是购物车项的总数量
    public Cart() {
    }

    public Map<Integer, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Integer, CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }

    public Double getTotalMoney() {
        totalMoney = 0.0;
        if (cartItemMap!=null && cartItemMap.size() > 0){
            Set<Map.Entry<Integer, CartItem>> entries = cartItemMap.entrySet();
            for (Map.Entry<Integer,CartItem> cartItemEntry : entries){
                CartItem cartItem = cartItemEntry.getValue();
                totalMoney = totalMoney + cartItem.getXj();
            }
        }
        return totalMoney;
    }

    public Integer getTotalCount() {
        totalCount = 0;
        if (cartItemMap!=null && cartItemMap.size() > 0){
            totalCount = cartItemMap.size();
        }
        return totalCount;
    }

    public Integer getTotalbookCount() {
        totalCount = 0;
        if (cartItemMap!=null && cartItemMap.size() > 0){
            for (CartItem cartItem : cartItemMap.values()){
                totalbookCount += cartItem.getBuyCount();
            }
        }
        return totalbookCount;
    }
}
