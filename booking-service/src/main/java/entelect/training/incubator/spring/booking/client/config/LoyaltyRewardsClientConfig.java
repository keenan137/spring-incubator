package entelect.training.incubator.spring.booking.client.config;

import entelect.training.incubator.spring.booking.client.FlightServiceProperties;
import entelect.training.incubator.spring.booking.client.LoyaltyRewardsClient;
import entelect.training.incubator.spring.booking.client.LoyaltyServiceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class LoyaltyRewardsClientConfig {
    LoyaltyServiceProperties props;

    public LoyaltyRewardsClientConfig(LoyaltyServiceProperties props) {
        this.props = props;
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("entelect.training.incubator.spring.booking.client.soap");
        return marshaller;
    }
    @Bean
    public LoyaltyRewardsClient loyaltyRewardsClient(Jaxb2Marshaller marshaller) {
        LoyaltyRewardsClient client = new LoyaltyRewardsClient();
        client.setDefaultUri(props.url());
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
