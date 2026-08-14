package entelect.training.incubator.spring.booking.client;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "client.service.customer")
public record CustomerServiceProperties(
        String url,
        String username,
        String password
) {
}
