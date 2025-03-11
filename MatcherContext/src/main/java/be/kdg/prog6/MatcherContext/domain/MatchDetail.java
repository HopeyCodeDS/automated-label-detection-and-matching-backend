package be.kdg.prog6.MatcherContext.domain;

public class MatchDetail {
    private String extractedValue;
    private int levenshteinDistance;
    private double fieldAccuracy;

    public void InitialiseMatchDetail(String extractedValue, int levenshteinDistance, double fieldAccuracy) {
        this.extractedValue = extractedValue;
        this.levenshteinDistance = levenshteinDistance;
        this.fieldAccuracy = fieldAccuracy;

    }

    public String getExtractedValue() {
        return extractedValue;
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

    public void setLevenshteinDistance(int levenshteinDistance) {
        this.levenshteinDistance = levenshteinDistance;
    }

    public void setFieldAccuracy(double fieldAccuracy) {
        this.fieldAccuracy = fieldAccuracy;
    }
}