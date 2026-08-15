package entelect.training.incubator.spring.booking.client;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "client.service.loyalty")
public record LoyaltyServiceProperties (String url) {
}