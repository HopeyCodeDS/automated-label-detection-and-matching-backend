package be.kdg.prog6.MatcherContext.core;

import be.kdg.prog6.MatcherContext.adapters.out.ProductJPAEntity;
import be.kdg.prog6.MatcherContext.adapters.out.ProductJPARepository;
import be.kdg.prog6.MatcherContext.domain.Product;
import be.kdg.prog6.MatcherContext.ports.in.ProductMatchingUseCase;
import be.kdg.prog6.MatcherContext.ports.out.ExtractProductsPort;
import org.springframework.stereotype.Service;
import org.apache.commons.text.similarity.LevenshteinDistance;

import java.util.List;
import java.util.Optional;

@Service
public class ProductMatchingUseCaseImpl implements ProductMatchingUseCase {
    private ExtractProductsPort extractProductsPort;
    private LevenshteinDistance levenshtein = new LevenshteinDistance();


    public ProductMatchingUseCaseImpl(ExtractProductsPort extractProductsPort) {
        this.extractProductsPort = extractProductsPort;
    }

    public Optional<ProductMatchResult> findBestMatchingProduct(String orderNumber, List<String> extractedText) {
        List<Product> products = extractProductsPort.extractByOrderNumber(orderNumber);

        if (products.isEmpty()) {
            return Optional.empty();
        }

        Product bestMatch = null;
        double bestScore = Double.MAX_VALUE;  // Lowest distance is best
        double maxPossibleDistance = 0;  // Max possible text distance

        LevenshteinDistance levenshtein = new LevenshteinDistance();

        for (Product product : products) {
            int totalDistance = 0;
            int fieldsMatched = 0;

            if (product.getDescription1() != null) {
                int distance = levenshtein.apply(product.getDescription1(), extractedText.toString());
                totalDistance += distance;
                maxPossibleDistance += Math.max(product.getDescription1().length(), extractedText.toString().length());
                fieldsMatched++;
            }
            if (product.getProductCode() != null) {
                int distance = levenshtein.apply(product.getProductCode(), extractedText.toString());
                totalDistance += distance;
                maxPossibleDistance += Math.max(product.getProductCode().length(), extractedText.toString().length());
                fieldsMatched++;
            }
            if (product.getBatch() != null) {
                int distance = levenshtein.apply(product.getBatch(), extractedText.toString());
                totalDistance += distance;
                maxPossibleDistance += Math.max(product.getBatch().length(), extractedText.toString().length());
                fieldsMatched++;
            }

            // Compute average distance
            double avgDistance = (fieldsMatched > 0) ? (double) totalDistance / fieldsMatched : Double.MAX_VALUE;

            if (avgDistance < bestScore) {
                bestScore = avgDistance;
                bestMatch = product;
            }
        }

        if (bestMatch != null && maxPossibleDistance > 0) {
            double matchAccuracy = 1.0 - (bestScore / maxPossibleDistance);
            matchAccuracy = Math.max(0, matchAccuracy); // Ensure no negative values

            return Optional.of(new ProductMatchResult(bestMatch, matchAccuracy));
        } else {
            return Optional.empty();
        }
    }

}
