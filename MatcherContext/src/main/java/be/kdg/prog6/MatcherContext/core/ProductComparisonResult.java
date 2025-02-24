package be.kdg.prog6.MatcherContext.core;

import be.kdg.prog6.MatcherContext.domain.Product;

import java.util.Map;

class ProductComparisonResult {
    private final Product product;
    private final Map<String, Integer> fieldDistances;
    private final double avgDistance;

    public ProductComparisonResult(Product product, Map<String, Integer> fieldDistances, double avgDistance) {
        this.product = product;
        this.fieldDistances = fieldDistances;
        this.avgDistance = avgDistance;
    }

    public Product getProduct() {
        return product;
    }

    public Map<String, Integer> getFieldDistances() {
        return fieldDistances;
    }

    public double getAvgDistance() {
        return avgDistance;
    }
}
