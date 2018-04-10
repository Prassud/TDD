package app;

import model.Hotel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import service.HotelReservationManagementService;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class HotelReservationManagemEntAppTest {

    private Hotel hotel;
    private HotelReservationManagementApp app;

    private HotelReservationManagementService service;

    private List<DayOfWeek> daysOfWeek;


    @Before
    public void setup() {
        ArrayList<Hotel> hotels = new ArrayList<>();
        hotels.add(hotel);
        service = spy(new HotelReservationManagementService(hotels));
        app = new HotelReservationManagementApp(service);
        daysOfWeek = new ArrayList<>();
        daysOfWeek.add(DayOfWeek.TUESDAY);
        daysOfWeek.add(DayOfWeek.MONDAY);
        daysOfWeek.add(DayOfWeek.WEDNESDAY);
    }


//    @Test
//    public void shouldVerifyIfApplicationProcessOnHotelManagementService() {
//        app.run(inputString);
//        verify(service, times(1)).process();
//    }


    @Test
    public void shouldVerifyIfApplicationProcessOnHotelManagementServiceWithGivenInputRequest() {

        String inputString = "Regular: 16Mar2009(mon), 17Mar2009(tues), 18Mar2009(wed)";
        InputRequest expectedResult = new InputRequest(CustomerType.REGULAR, daysOfWeek);
        app.run(inputString);
        ArgumentCaptor<InputRequest> argumentCaptor = ArgumentCaptor.forClass(InputRequest.class);
        verify(service, times(1)).process(argumentCaptor.capture());
        InputRequest inputRequest = argumentCaptor.getValue();
        assertEquals(expectedResult, inputRequest);

    }

    @Test
    public void shouldVerifyIfApplicationProcessOnHotelManagementServiceWithGivenInputRequestForRewardsCustomer() {

        String inputString = "Rewards: 16Mar2009(mon), 17Mar2009(tues), 18Mar2009(wed)";

        InputRequest expectedResult = new InputRequest(CustomerType.REWARDS, daysOfWeek);
        app.run(inputString);
        ArgumentCaptor<InputRequest> argumentCaptor = ArgumentCaptor.forClass(InputRequest.class);
        verify(service, times(1)).process(argumentCaptor.capture());
        InputRequest inputRequest = argumentCaptor.getValue();
        assertEquals(expectedResult, inputRequest);

    }

}
