package Supermarket.data;

import java.io.Serializable;

public abstract class Product implements Serializable {
    private Long id;
    private String name;
    private int price;
    private int available;
    private int sold;

    public abstract String getInfo();

    public Product() {
    }

    public Product(Long id, String name, int price, int available) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.available = available;
    }

    public Product(Long id, String name, int price, int available, int sold) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.available = available;
        this.sold = sold;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }
}
