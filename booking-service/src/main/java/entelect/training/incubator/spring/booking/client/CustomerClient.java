package entelect.training.incubator.spring.booking.client;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import entelect.training.incubator.spring.booking.model.CustomerDto;

import java.util.concurrent.CompletableFuture;

@Service
public class CustomerClient {
    private final RestClient restClient;

    public CustomerClient(RestClient.Builder builder, CustomerServiceProperties props)
    {
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
    public CompletableFuture<CustomerDto> getCustomer(Integer id) {
        CustomerDto customer =  restClient.get()
                .uri("/customers/{id}", id)
                .retrieve()
                .body(CustomerDto.class);

        return CompletableFuture.completedFuture(customer);
    }
}
