package be.kdg.prog6.MatcherContext.adapters.in;

import be.kdg.prog6.MatcherContext.domain.ImageData;
import be.kdg.prog6.MatcherContext.domain.OcrResult;
import be.kdg.prog6.MatcherContext.ports.in.OcrUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/ocr")
public class OcrController {
    private final OcrUseCase ocrUseCase;

    public OcrController(OcrUseCase ocrUseCase) {
        this.ocrUseCase = ocrUseCase;
    }

    @PostMapping
    public ResponseEntity<OcrResult> performOcr(@RequestParam("file") MultipartFile file) throws IOException {
        ImageData imageData = new ImageData(file.getBytes());
        OcrResult result = ocrUseCase.performOcr(imageData);
        return ResponseEntity.ok(result);
    }
}