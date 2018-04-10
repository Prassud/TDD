package model;

public class PriceRate {
    public int getPrice() {
        return price;
    }

    private int price;
    private String unit;

    public PriceRate(int price, String unit) {
        this.price = price;
        this.unit = unit;
    }
}
