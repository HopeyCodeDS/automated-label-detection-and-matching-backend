package be.kdg.prog6.MatcherContext.ports.in;

import be.kdg.prog6.MatcherContext.domain.ProductMatchResultInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductHULinkUseCase {

    void linkHuToProduct(String huNumber, String productId, String batch);

}
