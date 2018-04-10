package util;

import org.junit.Test;
import service.HotelReservationManagementService;

import java.time.DayOfWeek;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HotelManagemntUtilTest {
    @Test
    public void ShouldVerifyIfTheDateIsGivenInInputFormatThenReturnProperDate() {
        DayOfWeek returnValue = HotelManagementUtil.getInstance().getDayOFWeek("mon");
        assertEquals(DayOfWeek.MONDAY, returnValue);
        returnValue = HotelManagementUtil.getInstance().getDayOFWeek("tue");
        assertEquals(DayOfWeek.TUESDAY, returnValue);
        returnValue = HotelManagementUtil.getInstance().getDayOFWeek("wed");
        assertEquals(DayOfWeek.WEDNESDAY, returnValue);
        returnValue = HotelManagementUtil.getInstance().getDayOFWeek("thur");
        assertEquals(DayOfWeek.THURSDAY, returnValue);
        returnValue = HotelManagementUtil.getInstance().getDayOFWeek("fri");
        assertEquals(DayOfWeek.FRIDAY, returnValue);
        returnValue = HotelManagementUtil.getInstance().getDayOFWeek("sat");
        assertEquals(DayOfWeek.SATURDAY, returnValue);
        returnValue = HotelManagementUtil.getInstance().getDayOFWeek("sun");
        assertEquals(DayOfWeek.SUNDAY, returnValue);
    }


    @Test
    public void shouldVerifyIfReturnsTrueWhenDayOfWeekIsWeekend() {

        assertTrue(HotelManagementUtil.getInstance().isWeekEnd(DayOfWeek.SATURDAY));
        assertTrue(HotelManagementUtil.getInstance().isWeekEnd(DayOfWeek.SUNDAY));

    }

    @Test
    public void shouldVerifyIfUtilISReturningProperDateonGettingInputDateFormat() {
        String returnString = HotelManagementUtil.getInstance().getDayOfWeekFromFormatString("26Mar2009(tue)");
        assertEquals("tues", returnString);
    }

    @Test
    public void shouldVerifyIfReturnsFalseWhenDayOfWeekIsNotWeekend() {

        assertFalse(HotelManagementUtil.getInstance().isWeekEnd(DayOfWeek.MONDAY));
        assertFalse(HotelManagementUtil.getInstance().isWeekEnd(DayOfWeek.TUESDAY));
        assertFalse(HotelManagementUtil.getInstance().isWeekEnd(DayOfWeek.WEDNESDAY));
        assertFalse(HotelManagementUtil.getInstance().isWeekEnd(DayOfWeek.THURSDAY));
        assertFalse(HotelManagementUtil.getInstance().isWeekEnd(DayOfWeek.FRIDAY));


    }

}
