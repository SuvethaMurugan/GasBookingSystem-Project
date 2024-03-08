package com.companyname.gasbookingsystem.booking;


import com.companyname.gasbookingsystem.booking.DTO.BookingDTO;
import com.companyname.gasbookingsystem.booking.exception.BookingNotFoundException;
import com.companyname.gasbookingsystem.booking.exception.CustomerNotExistsWithId;
import com.companyname.gasbookingsystem.booking.exception.CylinderNotExistsWithId;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public Booking createBooking(@RequestBody BookingDTO bookingDTO) throws  BookingNotFoundException, CustomerNotExistsWithId, CylinderNotExistsWithId {
        return this.bookingService.createBooking(bookingDTO);
    }

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable("id") Integer id) throws BookingNotFoundException {
        return this.bookingService.getBookingById(id);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return this.bookingService.getAllBookings();
    }

    @PutMapping("/id")
    public Booking updateBooking(@RequestBody Booking booking) throws BookingNotFoundException {
        return this.bookingService.updateBooking(booking);
    }


}



