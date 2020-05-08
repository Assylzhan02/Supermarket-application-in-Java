package Supermarket.data;

import java.io.Serializable;

public class DairyProduct extends Product implements Serializable {
    private double fat_content;//1,5%;2.5%;33%

    public DairyProduct() {
    }

    public DairyProduct(Long id, String name, int price,int available, double fat_content) {
        super(id, name, price, available);
        this.fat_content = fat_content;
    }

    public DairyProduct(Long id, String name, int price, int available, int sold, double fat_content) {
        super(id, name, price, available, sold);
        this.fat_content = fat_content;
    }

    public double getFat_content() {
        return fat_content;
    }

    public void setFat_content(double fat_content) {
        this.fat_content = fat_content;
    }

    @Override
    public String getInfo() {
        return getName()+" "+getPrice()+" "+getFat_content();
    }
}
