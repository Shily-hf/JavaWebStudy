package edu.shily.book.pojo;

import java.math.BigDecimal;

/**
 * @author Shily-zhang
 * @Description
 */

//我们应该还需要一个Cart类，代表购物车实体
public class CartItem {
    private Integer id;
    private Book book;
    private Integer buyCount;
    private User userBean;
    private Double xj;      //小计，用于存储计算出的费用
    public CartItem() {
    }

    public CartItem(Integer id) {
        this.id = id;
    }

    public CartItem(Book book, Integer buyCount, User userBean) {
        this.book = book;
        this.buyCount = buyCount;
        this.userBean = userBean;
    }

    public CartItem(Integer id, Integer buyCount) {
        this.id = id;
        this.buyCount = buyCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public User getUserBean() {
        return userBean;
    }

    public void setUserBean(User userBean) {
        this.userBean = userBean;
    }

    public Double getXj() {
        BigDecimal bigDecimalPrice = new BigDecimal(getBook().getPrice() + "");
        BigDecimal bigDecimalCount = new BigDecimal("" + buyCount);
        BigDecimal bigDecimalXJ = bigDecimalPrice.multiply(bigDecimalCount);
        xj = bigDecimalXJ.doubleValue();
        return xj;
    }
}
