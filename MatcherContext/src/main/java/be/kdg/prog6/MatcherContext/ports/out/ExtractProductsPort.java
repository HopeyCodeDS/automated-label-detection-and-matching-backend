package be.kdg.prog6.MatcherContext.ports.out;

import be.kdg.prog6.MatcherContext.adapters.out.ProductJPAEntity;
import be.kdg.prog6.MatcherContext.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ExtractProductsPort {
    List<Product> extractByOrderNumber(String orderNumber);
    List<Product> extractAll();


}
