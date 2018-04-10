package util;

import java.time.DayOfWeek;

public class HotelManagementUtil {

    private static HotelManagementUtil instance;

    private HotelManagementUtil() {

    }


    public DayOfWeek getDayOFWeek(String dayOfWeekStr) {
        dayOfWeekStr = dayOfWeekStr.toUpperCase();
        DayOfWeek dayOfWeek = null;
        switch (dayOfWeekStr) {
            case "MON":
                dayOfWeek = DayOfWeek.MONDAY;
                break;
            case "TUES":
                dayOfWeek = DayOfWeek.TUESDAY;
                break;
            case "WED":
                dayOfWeek = DayOfWeek.WEDNESDAY;
                break;
            case "THUR":
                dayOfWeek = DayOfWeek.THURSDAY;
                break;
            case "FRI":
                dayOfWeek = DayOfWeek.FRIDAY;
                break;
            case "SAT":
                dayOfWeek = DayOfWeek.SATURDAY;
                break;
            default:
                dayOfWeek = DayOfWeek.SUNDAY;

        }
        return dayOfWeek;

    }

    public boolean isWeekEnd(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    public String getDayOfWeekFromFormatString(String dateFormatString) {
        return dateFormatString.substring(dateFormatString.indexOf('(') + 1, dateFormatString.length() - 1);
    }

    public static HotelManagementUtil getInstance() {
        return instance == null ? (instance = new HotelManagementUtil()) : instance;
    }
}
