package be.kdg.prog6.MatcherContext.ports.in;
import be.kdg.prog6.MatcherContext.domain.ProductMatchResultInfo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ProductMatchingUseCase {

    public ProductMatchResultInfo findBestMatchingProduct(String orderNumber, String huNumber, List<String> extractedText);

}
