package be.kdg.prog6.MatcherContext.core;
import be.kdg.prog6.MatcherContext.domain.MatchDetail;
import be.kdg.prog6.MatcherContext.domain.Product;
import be.kdg.prog6.MatcherContext.domain.ProductMatchResultInfo;
import be.kdg.prog6.MatcherContext.ports.in.ProductMatchingUseCase;
import be.kdg.prog6.MatcherContext.ports.out.ExtractProductsPort;
import be.kdg.prog6.MatcherContext.ports.out.LinkHuToProductPort;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductMatchingUseCaseImpl implements ProductMatchingUseCase {
    private static final Logger logger = LoggerFactory.getLogger(ProductMatchingUseCaseImpl.class);

    private final ExtractProductsPort extractProductsPort;
    private final LinkHuToProductPort linkHuToProductPort;
    private final LevenshteinDistance levenshtein = new LevenshteinDistance();

    public ProductMatchingUseCaseImpl(ExtractProductsPort extractProductsPort, LinkHuToProductPort linkHuToProductPort) {
        this.extractProductsPort = extractProductsPort;
        this.linkHuToProductPort = linkHuToProductPort;
    }

    @Override
    public ProductMatchResultInfo findBestMatchingProduct(String orderNumber, String huNumber, List<String> extractedText) {
        logger.info("Starting product match for orderNumber: {} and HU: {}", orderNumber, huNumber);

        List<Product> products = (orderNumber == null || orderNumber.trim().isEmpty())
                ? extractProductsPort.extractAll()
                : extractProductsPort.extractByOrderNumber(orderNumber);

        if (products.isEmpty()) {
            logger.warn("No products found for orderNumber: {}", orderNumber);
            return null;
        }

        List<String> words = extractedText.stream()
                .map(this::normalizeText)
                .flatMap(text -> Arrays.stream(text.split("\\s+")))
                .collect(Collectors.toList());

        List<String> phrases = extractedText.stream()
                .map(this::normalizeText)
                .collect(Collectors.toList());

        logger.info("Extracted Words: {}", words);
        logger.info("Extracted Phrases: {}", phrases);

        // **BONUS LAYER: Exact Match Check**
        for (Product product : products) {
            if (words.contains(normalizeText(product.getProductCode()))) {
                logger.info("Exact match found for Product ID: {}", product.getProductCode());

                // Link HU to full Product entity

                linkHuToProductPort.linkHuToProduct(huNumber, product.getProductCode());


                ProductMatchResultInfo result = new ProductMatchResultInfo();
                result.InitialiseProductMatchResultDomain(
                        huNumber,
                        product.getProductCode(),
                        product.getDescription1(),
                        product.getBatch(),
                        product.getCustomerName(),
                        product.getDescription1(),
                        product.getOrderDate() != null ? product.getOrderDate().toString() : "",
                        words,
                        phrases,
                        1.0, // 100% accuracy
                        true,
                        Collections.emptyMap() // No need for detailed matching info
                );

                return result;
            }
        }

        logger.info("No exact match found. Proceeding with weighted similarity matching...");

        Product bestMatch = null;
        double bestOverallAccuracy = 0;
        Map<String, MatchDetail> bestMatchDetails = new HashMap<>();

        for (Product product : products) {
            double weightedAccuracy = 0;
            Map<String, MatchDetail> matchDetails = new HashMap<>();

            weightedAccuracy = (compareField(product.getProductCode(), words, matchDetails, "Product Code") * 0.4) +
                    (compareField(product.getBatch(), words, matchDetails, "Batch") * 0.3) +
                    (compareField(product.getCustomerName(), phrases, matchDetails, "Customer Name") * 0.2) +
                    (compareField(product.getDescription1(), phrases, matchDetails, "Description") * 0.1) +
                    (compareField(product.getOrderDate() != null ? product.getOrderDate().toString() : "", words, matchDetails, "Order Date") * 0.1);

            if (weightedAccuracy > bestOverallAccuracy) {
                bestOverallAccuracy = weightedAccuracy;
                bestMatch = product;
                bestMatchDetails = matchDetails;
            }
        }

        if (bestMatch != null) {
            ProductMatchResultInfo result = new ProductMatchResultInfo();
            result.InitialiseProductMatchResultDomain(
                    huNumber,
                    bestMatch.getProductCode(),
                    bestMatch.getDescription1(),
                    bestMatch.getBatch(),
                    bestMatch.getCustomerName(),
                    bestMatch.getDescription1(),
                    bestMatch.getOrderDate() != null ? bestMatch.getOrderDate().toString() : "",
                    words,
                    phrases,
                    bestOverallAccuracy,
                    false,
                    bestMatchDetails
            );
            return result;
        } else {
            return null;
        }
    }

    private double compareField(String field, List<String> extractedList, Map<String, MatchDetail> matchDetails, String fieldName) {
        if (field == null || extractedList.isEmpty()) return 0;

        String normalizedField = normalizeText(field);
        if (normalizedField.isEmpty()) return 0;

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
        accuracy = Math.max(0, accuracy);

        MatchDetail detail = new MatchDetail();
        detail.InitialiseMatchDetail(bestMatch, minDistance, accuracy);
        matchDetails.put(fieldName, detail);

        return accuracy;
    }

    private String normalizeText(String input) {
        if (input == null) return "";

        return input.replaceAll("[^a-zA-Z0-9]", " ")  // Remove special characters
                .replaceAll("\\s+", " ")             // Reduce multiple spaces to one
                .toLowerCase()
                .trim();
    }
}
