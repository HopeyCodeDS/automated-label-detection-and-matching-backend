package be.kdg.prog6.MatcherContext.ports.in;


import be.kdg.prog6.MatcherContext.domain.ImageData;
import be.kdg.prog6.MatcherContext.domain.OcrResult;

public interface OcrUseCase {
    OcrResult performOcr(ImageData imageData);
}
