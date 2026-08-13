package entelect.training.incubator.spring.booking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingsSearchRequest {
    private SearchType searchType;
    private Integer customerId;
    private String referenceNumber;
}
