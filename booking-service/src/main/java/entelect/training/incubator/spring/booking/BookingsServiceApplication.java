package entelect.training.incubator.spring.booking;

import entelect.training.incubator.spring.booking.client.CustomerServiceProperties;
import entelect.training.incubator.spring.booking.client.FlightServiceProperties;
import entelect.training.incubator.spring.booking.client.LoyaltyServiceProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({CustomerServiceProperties.class, FlightServiceProperties.class, LoyaltyServiceProperties.class})
public class BookingsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingsServiceApplication.class, args);
	}

}
