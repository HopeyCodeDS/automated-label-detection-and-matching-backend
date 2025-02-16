package be.kdg.prog6.MatcherContext.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Orderline {
    @Id
    private String id;
    private String ordernumberEdi;
    private String productCode;
    private String batchNumber;
    private String productDescription;
    private boolean processed; // Whether the orderline has been processed
}
