package be.kdg.prog6.MatcherContext.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@Getter
@Setter
public class Orderline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String client;
    private String ordernummerEdi;
    private String refoEdi;
    private LocalDate orderdatum;
    private String name1;
    private Integer qtyOrd;
    private String batch;
    private String lineAlfa;
    private String stockInventory;
    private String desc1;
    private String packCode;
    private String productCode;
    private String omschrijving;
    private String productExtention1;
    private String omschrijvingVerpak;
    private String palletSize;
    private String salescode;
    private boolean processed; // Whether the orderline has been processed

    public Orderline() {
    }

    public Orderline(String value, String value1, String value2, LocalDate value3, String value4, int i, String value5, String value6, String value7, String value8, String value9, String value10, String value11, String value12, String value13, String value14, String value15) {
    }
}
