package entelect.training.incubator.spring.booking.service;

import entelect.training.incubator.spring.booking.client.CustomerClient;
import entelect.training.incubator.spring.booking.client.FlightClient;
import entelect.training.incubator.spring.booking.model.*;
import entelect.training.incubator.spring.booking.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@Service
public class BookingsService {

    private final BookingRepository bookingRepository;
    private final CustomerClient customerClient;
    private final FlightClient flightClient;

    public BookingsService(BookingRepository bookingRepository, CustomerClient customerClient, FlightClient flightClient) {
        this.bookingRepository = bookingRepository;
        this.customerClient = customerClient;
        this.flightClient = flightClient;
    }

    public CompletableFuture<Booking> createBooking(Booking booking) {
        CompletableFuture<CustomerDto> customerFuture = customerClient.getCustomer(booking.getCustomerId());
        CompletableFuture<FlightDto> flightFuture = flightClient.getFlightId(booking.getFlightId());

        return CompletableFuture.allOf(customerFuture, flightFuture)
                .thenApply(_ -> {
                    CustomerDto customer = customerFuture.join();
                    FlightDto flight = flightFuture.join();

                    // validation
                    if (customer == null) {
                        throw new RuntimeException("Customer not found");
                    }
                    if (flight == null) {
                        throw new RuntimeException("Flight not found");
                    }
                    return bookingRepository.save(booking);
                });
    }

    public List<Booking> getBookings() {
        Iterable<Booking> bookingIterable = bookingRepository.findAll();

        List<Booking> result = new ArrayList<>();
        bookingIterable.forEach(result::add);

        return result;
    }

    public Booking getBooking(Integer id) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        return bookingOptional.orElse(null);
    }

    public Booking searchBookings(BookingsSearchRequest searchRequest) {
        Map<SearchType, Supplier<Optional<Booking>>> searchStrategies = new HashMap<>();

        searchStrategies.put(SearchType.CUSTOMER_ID_SEARCH, () -> bookingRepository.findByCustomerId(searchRequest.getCustomerId()));
        searchStrategies.put(SearchType.REFERENCE_NUMBER_SEARCH, () -> bookingRepository.findByReferenceNumber(searchRequest.getReferenceNumber()));

        Optional<Booking> bookingOptional = searchStrategies.get(searchRequest.getSearchType()).get();

        return bookingOptional.orElse(null);
    }
}
