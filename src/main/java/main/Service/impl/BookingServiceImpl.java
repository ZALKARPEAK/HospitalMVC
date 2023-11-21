package main.Service.impl;


import main.Api.BookingApi;
import main.Model.Booking;
import main.Repo.BookingRepo;
import main.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepo bookingRepo;
    @Override
    public List<Booking> getAllBookings() {
        return bookingRepo.getAllBookings();
    }

    @Override
    public Booking getBookingById(Long bookingId) {
        return bookingRepo.getBookingById(bookingId);
    }

    @Override
    public void saveBooking(Booking booking) {
        bookingRepo.saveBooking(booking);
    }

    @Override
    public List<Booking> getBookingByCustomerId(Long customerId) {
        return bookingRepo.getBookingByCustomerId(customerId);
    }

    @Override
    public void bookCustomer(Long houseId, Long customerId) {
        bookingRepo.bookCustomer(houseId, customerId);
    }
}
