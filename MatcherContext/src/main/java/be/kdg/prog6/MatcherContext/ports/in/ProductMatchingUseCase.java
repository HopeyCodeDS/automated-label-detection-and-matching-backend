package be.kdg.prog6.MatcherContext.ports.in;

import be.kdg.prog6.MatcherContext.adapters.out.ProductJPAEntity;
import be.kdg.prog6.MatcherContext.adapters.out.ProductJPARepository;
import be.kdg.prog6.MatcherContext.core.ProductMatchResult;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductMatchingUseCase {

    public Optional<ProductMatchResult> findBestMatchingProduct(String orderNumber, List<String> extractedText);

}
