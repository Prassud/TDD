package app;

import java.time.DayOfWeek;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class InputRequest {

    public InputRequest(CustomerType customerType, List<DayOfWeek> daysOfWeek) {
        this.customerType = customerType;
        this.daysofWeek = daysOfWeek;
        Collections.sort(this.daysofWeek);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputRequest that = (InputRequest) o;
        return customerType == that.customerType && this.daysofWeek.equals(that.daysofWeek);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerType, daysofWeek);
    }

    private CustomerType customerType;

    private List<DayOfWeek> daysofWeek;


    public CustomerType getCustomerType() {
        return customerType;
    }

    public List<DayOfWeek> getDaysofWeek() {
        return daysofWeek;
    }
}
