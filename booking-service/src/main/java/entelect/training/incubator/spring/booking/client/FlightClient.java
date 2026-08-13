package entelect.training.incubator.spring.booking.client;

import entelect.training.incubator.spring.booking.model.FlightDto;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.concurrent.CompletableFuture;

@Service
public class FlightClient {
    private final RestClient restClient;

    public FlightClient(RestClient.Builder builder, FlightServiceProperties props) {
        this.restClient = builder
                .baseUrl(props.url())
                .requestInterceptor((request, body, execution) -> {
                    request.getHeaders()
                            .setBasicAuth(props.username(), props.password());

                    return execution.execute(request, body);
                })
                .build();
    }

    @Async
    public CompletableFuture<FlightDto> getFlightId(Integer id) {
        FlightDto flight =  restClient.get()
                .uri("/flights/{id}", id)
                .retrieve()
                .body(FlightDto.class);

        return CompletableFuture.completedFuture(flight);
    }
}
