package be.kdg.prog6.MatcherContext.core;

import be.kdg.prog6.MatcherContext.domain.Product;
import be.kdg.prog6.MatcherContext.ports.in.CheckOrderNumberUseCase;
import be.kdg.prog6.MatcherContext.ports.out.ExtractProductsPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckOrderNumberUseCaseImpl implements CheckOrderNumberUseCase {

    private final ExtractProductsPort extractProductsPort;

    public CheckOrderNumberUseCaseImpl(ExtractProductsPort extractProductsPort) {
        this.extractProductsPort = extractProductsPort;
    }

    @Override
    public boolean checkOrderNumber(String orderNumber) {
        List< Product> p = this.extractProductsPort.extractByOrderNumber(orderNumber);
        return !p.isEmpty();
    }
}
