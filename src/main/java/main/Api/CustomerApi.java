package main.Api;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import main.Model.Agency;
import main.Model.Booking;
import main.Model.Customer;
import main.Model.House;
import main.Service.AgencyService;
import main.Service.BookingService;
import main.Service.CustomerService;
import main.Service.HouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/CustomerMain")
@RequiredArgsConstructor
public class CustomerApi {

    private final CustomerService customerService;
    private final AgencyService agencyService;
    private final HouseService houseService;
    private final BookingService bookingService;
    @GetMapping
    private String getAllCustomer(Model model) {
        model.addAttribute("getAllCustomer", customerService.getAllCustomer());
        return "/Customer/CustomerMain";
    }

    @GetMapping("/createCustomer")
    public String createCustomer(Model model) {
        model.addAttribute("newCustomer", new Customer());
        return "/Customer/createCustomer";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute(name = "newCustomer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/CustomerMain";
    }
    @DeleteMapping("{id}/delete")
    public String deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/CustomerMain";
    }

    @GetMapping("/{id}/update")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("customer", customerService.getCustomerById(id));
        return "/Customer/updateCustomer";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("customer") Customer customer, @PathVariable("id") Long id) {
        customerService.updateCustomer(id, customer);
        return "redirect:/CustomerMain";
    }


    //BOOKINGS
    @GetMapping("/bookings")
    public String showBookings(Model model) {
        List<Booking> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "Booking/booking";
    }

    @GetMapping("/details/{bookingId}")
    public String showBookingDetails(@PathVariable Long bookingId, Model model) {
        Booking booking = bookingService.getBookingById(bookingId);
        model.addAttribute("booking", booking);
        return "Booking/booking-details";
    }

    @PostMapping("/saveBooking")
    public String saveBooking(@ModelAttribute("newBooking") Booking booking) {
        bookingService.saveBooking(booking);
        return "redirect:/booking";
    }

    @GetMapping("/{getById}/get/booking")
    public String showAndBookCustomer(@PathVariable("getById") Long customerId, Model model) {
        Customer customer = customerService.getCustomerById(customerId);
        List<House> availableHouses = houseService.getAllHouseByAgencyId();

        model.addAttribute("getCustomerById", customer);
        model.addAttribute("houses", availableHouses);
        return "Booking/customer-booking";
    }

    @PostMapping("/{getById}/get/booking")
    public String bookCustomer(@RequestParam Long houseId, @PathVariable Long getById) {
        Customer customer = customerService.getCustomerById(getById);
        bookingService.bookCustomer(customer.getId(), houseId);

        return "redirect:/AgencyMain";
    }
}

