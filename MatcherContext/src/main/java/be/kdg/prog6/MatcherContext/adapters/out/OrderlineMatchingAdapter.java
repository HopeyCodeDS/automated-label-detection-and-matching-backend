package be.kdg.prog6.MatcherContext.adapters.out;

import be.kdg.prog6.MatcherContext.core.OrderlineMatchingService;
import be.kdg.prog6.MatcherContext.domain.OcrResult;
import be.kdg.prog6.MatcherContext.domain.Orderline;
import be.kdg.prog6.MatcherContext.ports.out.OrderlineMatchingPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderlineMatchingAdapter implements OrderlineMatchingPort {
    private final OrderlineMatchingService orderlineMatchingService;

    public OrderlineMatchingAdapter(OrderlineMatchingService orderlineMatchingService) {
        this.orderlineMatchingService = orderlineMatchingService;
    }

    @Override
    public List<Orderline> findMatchingOrderlines(OcrResult ocrResult) {
        return orderlineMatchingService.findMatchingOrderlines(ocrResult);
    }
}
