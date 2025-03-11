package be.kdg.prog6.MatcherContext.domain;

import java.util.List;
import java.util.Map;

public class ProductMatchResultInfo {
    private String huNumber;
    private String productId;
    private String productName;
    private String batch;
    private String customerName;
    private String description;
    private String orderDate;
    private List<String> extractedWords;
    private List<String> extractedPhrases;
    private double accuracy;
    private boolean exactMatch;
    private Map<String, MatchDetail> matchDetails; // Key = Product Field, Value = Match Detail

    public String getHuNumber() {
        return huNumber;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getBatch() {
        return batch;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getDescription() {
        return description;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public List<String> getExtractedWords() {
        return extractedWords;
    }

    public List<String> getExtractedPhrases() {
        return extractedPhrases;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public boolean isExactMatch() {
        return exactMatch;
    }

    public Map<String, MatchDetail> getMatchDetails() {
        return matchDetails;
    }



    public void InitialiseProductMatchResultDomain(
            String huNumber, String productId, String productName, String batch,
            String customerName, String description, String orderDate,
            List<String> extractedWords, List<String> extractedPhrases,
            double accuracy, boolean exactMatch, Map<String, MatchDetail> matchDetails) {
        this.huNumber = huNumber;
        this.productId = productId;
        this.productName = productName;
        this.batch = batch;
        this.customerName = customerName;
        this.description = description;
        this.orderDate = orderDate;
        this.extractedWords = extractedWords;
        this.extractedPhrases = extractedPhrases;
        this.accuracy = accuracy;
        this.exactMatch = exactMatch;
        this.matchDetails = matchDetails;
    }
}
