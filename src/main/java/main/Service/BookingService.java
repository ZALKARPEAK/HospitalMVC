package main.Service;

import main.Api.BookingApi;
import main.Model.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> getAllBookings();

    Booking getBookingById(Long bookingId);

    void saveBooking(Booking booking);

    List<Booking> getBookingByCustomerId(Long customerId);

    void bookCustomer(Long houseId, Long customerId);
}
