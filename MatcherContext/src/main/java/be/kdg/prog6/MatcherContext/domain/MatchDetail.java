package be.kdg.prog6.MatcherContext.domain;

public class MatchDetail {
    private String extractedValue;  // Value from OCR
    private String actualValue;     // Actual value from product
    private int levenshteinDistance;
    private double fieldAccuracy;

    public void InitialiseMatchDetail(String extractedValue, String actualValue, int levenshteinDistance, double fieldAccuracy) {
        this.extractedValue = extractedValue;
        this.actualValue = actualValue;
        this.levenshteinDistance = levenshteinDistance;
        this.fieldAccuracy = fieldAccuracy;
    }

    public String getExtractedValue() {
        return extractedValue;
    }

    public String getActualValue() {
        return actualValue;
    }

    public int getLevenshteinDistance() {
        return levenshteinDistance;
    }

    public double getFieldAccuracy() {
        return fieldAccuracy;
    }

    public void setExtractedValue(String extractedValue) {
        this.extractedValue = extractedValue;
    }

    public void setActualValue(String actualValue) {
        this.actualValue = actualValue;
    }

    public void setLevenshteinDistance(int levenshteinDistance) {
        this.levenshteinDistance = levenshteinDistance;
    }

    public void setFieldAccuracy(double fieldAccuracy) {
        this.fieldAccuracy = fieldAccuracy;
    }

    @Override
    public String toString() {
        return "MatchDetail{" +
                "extractedValue='" + extractedValue + '\'' +
                ", actualValue='" + actualValue + '\'' +
                ", levenshteinDistance=" + levenshteinDistance +
                ", fieldAccuracy=" + fieldAccuracy +
                '}';
    }
}
