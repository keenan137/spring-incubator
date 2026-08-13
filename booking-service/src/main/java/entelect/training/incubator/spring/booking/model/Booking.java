package entelect.training.incubator.spring.booking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String referenceNumber;

    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "fk_booking_customer"))
    private Integer customerId;

    @JoinColumn(name = "flight_id", foreignKey = @ForeignKey(name = "fk_booking_flight"))
    private Integer flightId;
}
