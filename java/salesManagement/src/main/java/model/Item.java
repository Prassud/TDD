package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Item {
    private double price;

    private String name;

    public double getTaxCost() {
        return taxCost;
    }

    public double getTotalCost() {
        BigDecimal bd = new BigDecimal(this.getTaxCost() + this.getPrice());
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private double taxCost = 0.0;

    private boolean imported;
    private int qty;
    private List<Type> types;
    private Category category;

    public Item(double price, String name, int qty, boolean isImported) {

        this.price = price;
        this.name = name;
        this.qty = qty;
        this.imported = isImported;
        this.types = new ArrayList<>();

    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getQty() {
        return qty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Double.compare(item.price, price) == 0 &&
                qty == item.qty &&
                Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(price, name, qty);
    }

    public List<Type> getTypes() {
        return types;
    }

    public void addType(Type itemType) {
        this.types.add(itemType);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isImported() {
        return imported;
    }

    public void computeSalesTax() {
        if (this.taxCost == 0.0) {
            this.getTypes().stream().forEach(eachType -> {
                this.taxCost += getTaxAmount(eachType);
            });
        }
    }

    private double getTaxAmount(Type eachType) {
        return this.price * (eachType.getTaxPercentage() / 100.0);
    }

    public enum Type {

        normal(10), imported(5), excluded(0);

        public int getTaxPercentage() {
            return taxPercentage;
        }

        private int taxPercentage;

        Type(int discountPerset) {

            this.taxPercentage = discountPerset;
        }
    }
}
