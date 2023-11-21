package main.Repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import main.Api.BookingApi;
import main.Model.Agency;
import main.Model.Booking;
import main.Model.Customer;
import main.Model.House;
import main.Repo.BookingRepo;
import main.Service.BookingService;
import main.Service.CustomerService;
import main.Service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
@Transactional
public class BookingRepoImpl implements BookingRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private BookingService bookingService;
    @Autowired
    private HouseService houseService;
    @Autowired
    private CustomerService customerService;


    @Override
    public List<Booking> getAllBookings() {
        return entityManager.createQuery("select b from Booking b", Booking.class).getResultList();
    }

    @Override
    public Booking getBookingById(Long bookingId) {
        return entityManager.find(Booking.class, bookingId);

    }

    @Override
    public void saveBooking(Booking booking) {
        entityManager.persist(booking);
    }


    @Override
    public List<Booking> getBookingByCustomerId(Long customerId) {
        return entityManager.createQuery(
                        "SELECT b FROM Booking b JOIN b.customer c WHERE c.id = :customerId",
                        Booking.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }

    @Override
    public void bookCustomer(Long houseId, Long customerId) {
        House house1 = houseService.getHouseById(houseId);
        if (!house1.isBooked()) {
            Customer customer = customerService.getCustomerById(customerId);
            Booking booking = new Booking();
            booking.setHouse(house1);
            booking.setCustomer(customer);
            house1.setBooked(true);
            house1.setBooking(booking);
            bookingService.saveBooking(booking);
            houseService.saveHouse1(house1);

        }
    }
}
