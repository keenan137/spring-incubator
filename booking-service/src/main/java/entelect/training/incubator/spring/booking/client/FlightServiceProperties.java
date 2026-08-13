package entelect.training.incubator.spring.booking.client;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "client.service.flight")
public record FlightServiceProperties(
        String url,
        String username,
        String password
) {
}
