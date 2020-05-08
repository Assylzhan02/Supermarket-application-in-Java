package Supermarket.data;

import java.io.Serializable;

public class Drink extends Product implements Serializable {
    private double liters;
    private String kind_of_drink;

    public Drink() {}

    public Drink(Long id, String name, int price,int available, double liters, String kind_of_drink) {
        super(id, name, price, available);
        this.liters = liters;
        this.kind_of_drink = kind_of_drink;
    }

    public Drink(Long id, String name, int price, int available, int sold, double liters, String kind_of_drink) {
        super(id, name, price, available, sold);
        this.liters = liters;
        this.kind_of_drink = kind_of_drink;
    }

    public double getLiters() {
        return liters;
    }

    public void setLiters(double liters) {
        this.liters = liters;
    }

    public String getKind_of_drink() {
        return kind_of_drink;
    }

    public void setKind_of_drink(String kind_of_drink) {
        this.kind_of_drink = kind_of_drink;
    }

    @Override
    public String getInfo() {
        return getName()+" "+getPrice()+" "+getLiters()+" "+getKind_of_drink();
    }
}
