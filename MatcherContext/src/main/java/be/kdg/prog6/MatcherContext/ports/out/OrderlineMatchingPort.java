package be.kdg.prog6.MatcherContext.ports.out;

import be.kdg.prog6.MatcherContext.domain.OcrResult;
import be.kdg.prog6.MatcherContext.domain.Orderline;

import java.util.List;

public interface OrderlineMatchingPort {
    List<Orderline> findMatchingOrderlines(OcrResult ocrResult);
}
