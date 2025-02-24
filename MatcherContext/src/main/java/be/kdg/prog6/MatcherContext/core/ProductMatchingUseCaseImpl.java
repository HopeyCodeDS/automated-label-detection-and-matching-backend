package be.kdg.prog6.MatcherContext.core;

import be.kdg.prog6.MatcherContext.domain.Product;
import be.kdg.prog6.MatcherContext.ports.in.ProductMatchingUseCase;
import be.kdg.prog6.MatcherContext.ports.out.ExtractProductsPort;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductMatchingUseCaseImpl implements ProductMatchingUseCase {
    private static final Logger logger = LoggerFactory.getLogger(ProductMatchingUseCaseImpl.class);

    private final ExtractProductsPort extractProductsPort;
    private final LevenshteinDistance levenshtein = new LevenshteinDistance();

    public ProductMatchingUseCaseImpl(ExtractProductsPort extractProductsPort) {
        this.extractProductsPort = extractProductsPort;
    }

    @Override
    public Optional<ProductMatchResult> findBestMatchingProduct(String orderNumber, List<String> extractedText) {
        logger.info("Starting product match for orderNumber: {}", orderNumber);
        List<Product> products = extractProductsPort.extractByOrderNumber(orderNumber);

        if (products.isEmpty()) {
            logger.warn("No products found for orderNumber: {}", orderNumber);
            return Optional.empty();
        }

        // Normalize extracted text into words and phrases
        List<String> words = extractedText.stream()
                .map(this::normalizeText)
                .flatMap(text -> Arrays.stream(text.split("\\s+"))) // Split into individual words
                .collect(Collectors.toList());

        List<String> phrases = extractedText.stream()
                .map(this::normalizeText)
                .collect(Collectors.toList()); // Keep as full phrases

        logger.info("Extracted Words (for product codes, batch & order date): {}", words);
        logger.info("Extracted Phrases (for descriptions, company name & order date): {}", phrases);

        Product bestMatch = null;
        double bestOverallAccuracy = 0;

        for (Product product : products) {
            logger.info("Comparing product: {}", product.getProductCode());

            double weightedAccuracy = 0;

            // Weight factors
            double productCodeWeight = 0.4;
            double batchWeight = 0.3;  // Adjusted Batch weight to 0.3
            double companyWeight = 0.2;
            double descriptionWeight = 0.1;
            double orderDateWeight = 0.1;  // Added Order Date weight

            // Compare Product Code
            double productCodeAccuracy = compareField(product.getProductCode(), words);
            weightedAccuracy += productCodeAccuracy * productCodeWeight;

            // Compare Batch
            double batchAccuracy = compareField(product.getBatch(), words);
            weightedAccuracy += batchAccuracy * batchWeight;

            // Compare Company Name
            double companyAccuracy = compareField(product.getCustomerName(), phrases);
            weightedAccuracy += companyAccuracy * companyWeight;

            // Compare Description
            double descriptionAccuracy = compareField(product.getDescription1(), phrases);
            weightedAccuracy += descriptionAccuracy * descriptionWeight;

            // Compare Order Date
            double orderDateAccuracy = compareField(product.getOrderDate().toString(), words);
            weightedAccuracy += orderDateAccuracy * orderDateWeight;

            logger.info("\nFinal Weighted Accuracy for product {}: {}%\n", product.getProductCode(), String.format("%.2f", weightedAccuracy * 100));

            // Determine the best match
            if (weightedAccuracy > bestOverallAccuracy) {
                bestOverallAccuracy = weightedAccuracy;
                bestMatch = product;
            }
        }

        if (bestMatch != null) {
            logger.info("\nBest overall match: {} with accuracy: {}%\n", bestMatch.getProductCode(), String.format("%.2f", bestOverallAccuracy * 100));
            return Optional.of(new ProductMatchResult(bestMatch, bestOverallAccuracy));
        } else {
            logger.warn("\nNo suitable match found.");
            return Optional.empty();
        }
    }

    /**
     * Compares a field (Product Code, Batch, Company Name, Description, Order Date) against the extracted words or phrases.
     * Returns the accuracy percentage.
     */
    private double compareField(String field, List<String> extractedList) {
        if (field == null || extractedList.isEmpty()) return 0;

        String normalizedField = normalizeText(field);
        if (normalizedField.isEmpty()) return 0;

        // Filter extracted words/phrases that are at least 50% of the field length
        List<String> validMatches = extractedList.stream()
                .filter(p -> p.length() >= normalizedField.length() / 2)
                .collect(Collectors.toList());

        if (validMatches.isEmpty()) return 0;

        String bestMatch = null;
        int minDistance = Integer.MAX_VALUE;

        for (String candidate : validMatches) {
            int distance = levenshtein.apply(normalizedField, candidate);
            if (distance < minDistance) {
                minDistance = distance;
                bestMatch = candidate;
            }
        }

        double accuracy = 1.0 - (double) minDistance / normalizedField.length();
        accuracy = Math.max(0, accuracy); // Ensure no negative accuracy

        logger.info("\nBest match for field {}: {}\n  → Matched: {}\n  → Distance: {}\n  → Accuracy: {}%",
                field, normalizedField, bestMatch, minDistance, String.format("%.2f", accuracy * 100));

        return accuracy;
    }

    /**
     * Normalize text: Removes special characters, replaces them with spaces, and converts to lowercase.
     */
    private String normalizeText(String input) {
        if (input == null) return "";

        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("[^a-zA-Z0-9]", " ")  // Replace special characters with spaces
                .replaceAll("\\s+", " ")         // Ensure multiple spaces are reduced to one
                .toLowerCase()
                .trim();
    }
}
