package entelect.training.incubator.spring.booking.controller;

import entelect.training.incubator.spring.booking.model.Booking;
import entelect.training.incubator.spring.booking.model.BookingsSearchRequest;
import entelect.training.incubator.spring.booking.service.BookingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("bookings")
public class BookingsController {
    
    private final Logger LOGGER = LoggerFactory.getLogger(BookingsController.class);

    private final BookingsService bookingsService;

    public BookingsController(BookingsService bookingsService) {
        this.bookingsService = bookingsService;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getBooking(@PathVariable Integer id) {
        LOGGER.info("Finding booking with id={}", id);
        Booking booking = this.bookingsService.getBooking(id);

        if (booking != null) {
            LOGGER.trace("Found booking");
            return new ResponseEntity<>(booking, HttpStatus.OK);
        }

        LOGGER.info("Booking not found");
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<?> getAllBookings(){
        LOGGER.info("Finding all bookings.");

        List<Booking> bookings = this.bookingsService.getBookings();

        if(bookings.isEmpty()){
            LOGGER.error("No bookings found.");
            return ResponseEntity.notFound().build();
        }

        LOGGER.trace("Bookings found.");
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody Booking booking) {
        LOGGER.info("Processing booking creation request for booking {}", booking);
        CompletableFuture<Booking> createdBookingFuture = this.bookingsService.createBooking(booking);
        LOGGER.trace("Booking created");
        return new ResponseEntity<>(createdBookingFuture.join(), HttpStatus.CREATED);
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchBookings(@RequestBody BookingsSearchRequest searchRequest) {
        LOGGER.info("Processing booking search request for request {}", searchRequest);

        Booking booking = bookingsService.searchBookings(searchRequest);

        if (booking != null) {
            return ResponseEntity.ok(booking);
        }

        LOGGER.trace("Booking not found");
        return ResponseEntity.notFound().build();
    }
}
