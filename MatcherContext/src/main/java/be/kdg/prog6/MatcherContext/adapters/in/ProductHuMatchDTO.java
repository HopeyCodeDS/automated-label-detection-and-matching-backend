package be.kdg.prog6.MatcherContext.adapters.in;

import be.kdg.prog6.MatcherContext.domain.MatchDetail;
import be.kdg.prog6.MatcherContext.domain.ProductMatchResultInfo;

import java.util.List;
import java.util.Map;

public class ProductHuMatchDTO {
    private String huNumber;
    private String productId;
    private String productName;
    private String batch;
    private String customerName;
    private String description;

    public void setHuNumber(String huNumber) {
        this.huNumber = huNumber;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setExtractedWords(List<String> extractedWords) {
        this.extractedWords = extractedWords;
    }

    public void setExtractedPhrases(List<String> extractedPhrases) {
        this.extractedPhrases = extractedPhrases;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public void setExactMatch(boolean exactMatch) {
        this.exactMatch = exactMatch;
    }

    public void setMatchDetails(Map<String, MatchDetail> matchDetails) {
        this.matchDetails = matchDetails;
    }

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

    private String orderDate;
    private List<String> extractedWords;
    private List<String> extractedPhrases;
    private double accuracy;
    private boolean exactMatch;
    private Map<String, MatchDetail> matchDetails; // Key = Product Field, Value = Match Detail
    public static ProductHuMatchDTO fromDomain(ProductMatchResultInfo domain) {
        return new ProductHuMatchDTO(
                domain.getHuNumber(),
                domain.getProductId(),
                domain.getProductName(),
                domain.getBatch(),
                domain.getCustomerName(),
                domain.getDescription(),
                domain.getOrderDate(),
                domain.getExtractedWords(),
                domain.getExtractedPhrases(),
                domain.getAccuracy(),
                domain.isExactMatch(),
                domain.getMatchDetails()
        );
    }

    public ProductHuMatchDTO() {
    }

    public ProductHuMatchDTO(
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
