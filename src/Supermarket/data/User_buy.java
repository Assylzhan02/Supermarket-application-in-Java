package Supermarket.data;

import java.io.Serializable;

public class User_buy implements Serializable {
    private Long id;
    private Long user_id;
    private String product_name;
    private int count;
    private int totalSum;

    public User_buy() {
    }

    public User_buy(Long id, Long user_id, String product_name, int count, int totalSum) {
        this.id = id;
        this.user_id = user_id;
        this.product_name = product_name;
        this.count = count;
        this.totalSum = totalSum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(int totalSum) {
        this.totalSum = totalSum;
    }
}
