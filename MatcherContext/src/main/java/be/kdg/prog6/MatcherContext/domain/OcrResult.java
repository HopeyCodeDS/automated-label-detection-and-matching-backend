package be.kdg.prog6.MatcherContext.domain;

import java.util.List;

public record OcrResult(String[] extractedText, List<Orderline> matches) {
    // Constructor for creating an OcrResult without matches
    public OcrResult(String[] extractedText) {
        this(extractedText, List.of());
    }

    // Method to set matches
    public OcrResult setMatches(List<Orderline> matches) {
        return new OcrResult(this.extractedText(), matches);
    }
}