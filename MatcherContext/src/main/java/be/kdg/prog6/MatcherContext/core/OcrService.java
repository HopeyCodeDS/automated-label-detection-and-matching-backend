package be.kdg.prog6.MatcherContext.core;

import be.kdg.prog6.MatcherContext.domain.ImageData;
import be.kdg.prog6.MatcherContext.domain.OcrResult;
import be.kdg.prog6.MatcherContext.ports.in.OcrUseCase;
import be.kdg.prog6.MatcherContext.ports.out.OcrPort;
import org.springframework.stereotype.Service;

@Service
public class OcrService implements OcrUseCase {
    private final OcrPort ocrPort;

    public OcrService(OcrPort ocrPort) {
        this.ocrPort = ocrPort;
    }


    @Override
    public OcrResult performOcr(ImageData imageData) {
        return ocrPort.extractText(imageData);
    }
}