package be.kdg.prog6.MatcherContext.core;

import be.kdg.prog6.MatcherContext.domain.ImageData;
import be.kdg.prog6.MatcherContext.domain.OcrResult;
import be.kdg.prog6.MatcherContext.domain.Orderline;
import be.kdg.prog6.MatcherContext.ports.in.OcrUseCase;
import be.kdg.prog6.MatcherContext.ports.out.OcrPort;
import be.kdg.prog6.MatcherContext.ports.out.OrderlineMatchingPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OcrService implements OcrUseCase {
    private final OcrPort ocrPort;
    private final OrderlineMatchingPort orderlineMatchingPort;

    public OcrService(OcrPort ocrPort, OrderlineMatchingPort orderlineMatchingPort) {
        this.ocrPort = ocrPort;
        this.orderlineMatchingPort = orderlineMatchingPort;
    }


    @Override
    public OcrResult performOcr(ImageData imageData) {
        OcrResult ocrResult = ocrPort.extractText(imageData);
        List<Orderline> matches = orderlineMatchingPort.findMatchingOrderlines(ocrResult);
        ocrResult.setMatches(matches);
        return ocrResult;
    }
}