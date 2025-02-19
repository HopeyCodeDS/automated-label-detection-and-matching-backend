package be.kdg.prog6.MatcherContext.ports.out;

import be.kdg.prog6.MatcherContext.domain.ImageData;
import be.kdg.prog6.MatcherContext.domain.OcrResult;

public interface OcrPort {
    OcrResult extractText(ImageData imageData);
}