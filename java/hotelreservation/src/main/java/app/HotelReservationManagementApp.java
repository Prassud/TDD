package app;


import service.HotelReservationManagementService;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static util.HotelManagementUtil.getInstance;


public class HotelReservationManagementApp {

    private HotelReservationManagementService service;

    public HotelReservationManagementApp(HotelReservationManagementService service) {
        this.service = service;
    }

    public void run(String inputString) {
        String[] inputData = inputString.split(":");
        CustomerType customerType = CustomerType.valueOf(inputData[0].toUpperCase(Locale.getDefault()));
        List<DayOfWeek> dayOfWeeks = new ArrayList<DayOfWeek>(10);
        Arrays.stream(inputData[1].split(",")).forEach(eachInputData -> {
            eachInputData = eachInputData.trim();
            dayOfWeeks.add(getInstance().getDayOFWeek(getInstance().getDayOfWeekFromFormatString(eachInputData)));

        });
        service.process(new InputRequest(customerType, dayOfWeeks));
    }
}
