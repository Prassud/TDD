package service;

import app.CustomerType;
import app.InputRequest;
import model.Hotel;
import util.HotelManagementUtil;

import java.time.DayOfWeek;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public class HotelReservationManagementService {
    private List<Hotel> availableHotels;


    public HotelReservationManagementService(List<Hotel> hotels) {
        this.availableHotels = hotels;
    }


    public Hotel process(InputRequest inputReuest) {
        CustomerType customerType = inputReuest.getCustomerType();
        List<DayOfWeek> dayOfWeeks = inputReuest.getDaysofWeek();

        long weekEnds = dayOfWeeks.stream().filter(eachDay ->
                HotelManagementUtil.getInstance().isWeekEnd(eachDay)).count();
        long weekDays = (dayOfWeeks.size() - weekEnds);
        Function<Hotel, Long> function = (eachHotel) -> {
            return weekDays * eachHotel.getWeekEndPriceRateCustomerTypeMap(customerType).getPrice() + weekEnds * eachHotel.getWeekDayPriceRateCustomerTypeMap(customerType).getPrice();
        };
        int minIdx = IntStream.range(0, availableHotels.size()).reduce((i, j) -> function.apply(availableHotels.get(i)) > function.apply(availableHotels.get(j)) ? j : i)
                .getAsInt();

        return availableHotels.get(minIdx);

    }
}
