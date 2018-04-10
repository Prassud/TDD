package model;

import app.CustomerType;

import java.util.Map;
import java.util.Objects;

public class Hotel {


    private String name;

    private Rating rating;

    private Map<CustomerType, PriceRate> weekDayPriceRateCustomerTypeMap;

    private Map<CustomerType, PriceRate> weekEndPriceRateCustomerTypeMap;

    public Hotel(String name, Rating rating, Map<CustomerType, PriceRate> weekDayPriceRateCustomerTypeMap, Map<CustomerType, PriceRate> weekEndPriceRateCustomerTypeMap) {

        this.name = name;
        this.rating = rating;
        this.weekDayPriceRateCustomerTypeMap = weekDayPriceRateCustomerTypeMap;
        this.weekEndPriceRateCustomerTypeMap = weekEndPriceRateCustomerTypeMap;

    }

    public String getName() {
        return name;
    }

    public Rating getRating() {
        return rating;
    }

    public PriceRate getWeekDayPriceRateCustomerTypeMap(CustomerType customerType) {
        return weekDayPriceRateCustomerTypeMap.get(customerType);
    }

    public PriceRate getWeekEndPriceRateCustomerTypeMap(CustomerType customerType) {
        return weekEndPriceRateCustomerTypeMap.get(customerType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(name, hotel.name) &&
                Objects.equals(rating, hotel.rating) &&
                Objects.equals(weekDayPriceRateCustomerTypeMap, hotel.weekDayPriceRateCustomerTypeMap) &&
                Objects.equals(weekEndPriceRateCustomerTypeMap, hotel.weekEndPriceRateCustomerTypeMap);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, rating, weekDayPriceRateCustomerTypeMap, weekEndPriceRateCustomerTypeMap);
    }
}
