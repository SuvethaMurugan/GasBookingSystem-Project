package com.companyname.gasbookingsystem.booking;


import com.companyname.gasbookingsystem.booking.DTO.BookingDTO;
import com.companyname.gasbookingsystem.booking.exception.BookingNotFoundException;
import com.companyname.gasbookingsystem.booking.exception.CustomerNotExistsWithId;
import com.companyname.gasbookingsystem.booking.exception.NewBookingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @PostMapping
    public Booking createBooking(@RequestBody BookingDTO bookingDTO) throws NewBookingException, BookingNotFoundException, CustomerNotExistsWithId {
        return this.bookingService.createBooking(bookingDTO);
    }

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable("id") Integer id) throws BookingNotFoundException {
        return this.bookingService.getBookingById(id);
    }

    @GetMapping
    public List<Booking> getAllBookings() {

        return (List<Booking>) this.bookingService.getAllBookings();
    }

    @PutMapping("/{id}")
    public Booking updateBooking(@RequestBody Booking booking) throws BookingNotFoundException {
        return this.bookingService.updateBooking(booking);
    }
//    @DeleteMapping("/{id}")
//    public void deleteBooking(@PathVariable Integer id) throws BookingNotFoundException {
//        this.bookingService.deleteBooking(id);
//    }

}


