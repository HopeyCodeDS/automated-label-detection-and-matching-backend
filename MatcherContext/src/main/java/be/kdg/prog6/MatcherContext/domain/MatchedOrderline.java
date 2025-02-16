package be.kdg.prog6.MatcherContext.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MatchedOrderline {
    private String id;
    private String productCode;
    private String batchNumber;
    private String productDescription;
}
