package entelect.training.incubator.spring.booking.model;

import java.time.LocalDateTime;

public class FlightDto {
    private Integer id;

    private String flightNumber;

    private String origin;

    private String destination;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private Integer seatsAvailable;

    private Float seatCost;
}
