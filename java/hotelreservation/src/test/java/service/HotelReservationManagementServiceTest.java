package service;

import app.CustomerType;
import app.InputRequest;
import model.Hotel;
import model.PriceRate;
import model.Rating;
import org.junit.Before;
import org.junit.Test;
import util.HotelManagementUtil;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class HotelReservationManagementServiceTest {

    private HotelReservationManagementService service;

    private HotelManagementUtil managementUtil;

    private Hotel lakeWood;

    private Hotel bridgeWood;

    private Hotel ridgeWood;


    @Before
    public void setup() {

        List<Hotel> hotels = new ArrayList<Hotel>(10);
        hotels.add(lakeWood = createHotel("LakeWood", 3, 110, 80, 90, 80, "$"));
        hotels.add(bridgeWood = createHotel("Bridgewood", 3, 110, 80, 90, 80, "$"));
        hotels.add(ridgeWood = createHotel("Ridgewood", 3, 110, 80, 90, 80, "$"));
        this.managementUtil = spy(HotelManagementUtil.getInstance());
        service = new HotelReservationManagementService(hotels);
    }


    @Test
    public void shouldVerifyIfServiceProcessTheInputRequestAndReturnMinimumCostHotel() {


        List<DayOfWeek> dayOfWeeks = new ArrayList<>(3);
        dayOfWeeks.add(DayOfWeek.MONDAY);
        dayOfWeeks.add(DayOfWeek.TUESDAY);
        dayOfWeeks.add(DayOfWeek.WEDNESDAY);
        InputRequest request = spy(new InputRequest(CustomerType.REGULAR, dayOfWeeks));
        Hotel reserveedHotel = service.process(request);
        assertEquals(reserveedHotel, lakeWood);
        verify(request, times(1)).getCustomerType();
        verify(request, times(1)).getDaysofWeek();

        verify(lakeWood, times(2)).getWeekDayPriceRateCustomerTypeMap(CustomerType.REGULAR);
        verify(lakeWood, times(0)).getWeekDayPriceRateCustomerTypeMap(CustomerType.REWARDS);

        verify(bridgeWood, times(1)).getWeekDayPriceRateCustomerTypeMap(CustomerType.REGULAR);
        verify(bridgeWood, times(0)).getWeekDayPriceRateCustomerTypeMap(CustomerType.REWARDS);

        verify(ridgeWood, times(1)).getWeekDayPriceRateCustomerTypeMap(CustomerType.REGULAR);
        verify(ridgeWood, times(0)).getWeekDayPriceRateCustomerTypeMap(CustomerType.REWARDS);

        verify(lakeWood, times(2)).getWeekEndPriceRateCustomerTypeMap(CustomerType.REGULAR);
        verify(lakeWood, times(0)).getWeekEndPriceRateCustomerTypeMap(CustomerType.REWARDS);


        verify(bridgeWood, times(1)).getWeekEndPriceRateCustomerTypeMap(CustomerType.REGULAR);
        verify(bridgeWood, times(0)).getWeekEndPriceRateCustomerTypeMap(CustomerType.REWARDS);

        verify(ridgeWood, times(1)).getWeekEndPriceRateCustomerTypeMap(CustomerType.REGULAR);
        verify(ridgeWood, times(0)).getWeekEndPriceRateCustomerTypeMap(CustomerType.REWARDS);

    }

    private Hotel createHotel(String name, int ratingValue, int regWeekDayPrice, int rewWeekDayPrice, int regWeekEndPrice, int rewWeekEndPrice, String unit) {
        Rating rating = new Rating(ratingValue);
        PriceRate regWeekDayPriceRate = new PriceRate(regWeekDayPrice, unit);
        PriceRate rewWeekDayPriceRate = new PriceRate(rewWeekDayPrice, unit);
        PriceRate regWeekEndPriceRate = new PriceRate(regWeekEndPrice, unit);
        PriceRate rewWeekEndPriceRate = new PriceRate(rewWeekEndPrice, unit);
        Map<CustomerType, PriceRate> weekDayPriceRateCustomerTypeMap = new HashMap<>(10);
        weekDayPriceRateCustomerTypeMap.put(CustomerType.REGULAR, regWeekDayPriceRate);
        weekDayPriceRateCustomerTypeMap.put(CustomerType.REWARDS, rewWeekDayPriceRate);
        Map<CustomerType, PriceRate> weekEndPriceRateCustomerTypeMap = new HashMap<>(10);
        weekEndPriceRateCustomerTypeMap.put(CustomerType.REGULAR, regWeekEndPriceRate);
        weekEndPriceRateCustomerTypeMap.put(CustomerType.REWARDS, rewWeekEndPriceRate);
        return spy(new Hotel(name, rating, weekDayPriceRateCustomerTypeMap, weekDayPriceRateCustomerTypeMap));
    }


}
