package be.kdg.prog6.MatcherContext.adapters.in;

import be.kdg.prog6.MatcherContext.core.ProductMatchResult;
import be.kdg.prog6.MatcherContext.core.ProductMatchingUseCaseImpl;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/api/ocr")
public class Controller {
    private final RestTemplate restTemplate = new RestTemplate();
    private final ProductMatchingUseCaseImpl productMatchingUseCaseImpl;
    private final String PYTHON_OCR_API = "http://localhost:8000/ocr"; // FastAPI OCR URL

    public Controller(ProductMatchingUseCaseImpl productMatchingUseCaseImpl) {
        this.productMatchingUseCaseImpl = productMatchingUseCaseImpl;
    }

    @PostMapping(value = "/extract-text", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> extractText(
            @RequestParam("file") MultipartFile file,
            @RequestParam("orderNumber") String orderNumber
    ) {
        try {
            if (file.isEmpty()) {
                throw new IllegalArgumentException("Uploaded file is empty");
            }

            System.out.println("Received file: " + file.getOriginalFilename());
            System.out.println("Received order number: " + orderNumber);

            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            // Convert image to byte array
            ByteArrayResource imageResource = new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            };

            // Prepare request body for Python OCR API
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", imageResource);
            body.add("orderNumber", orderNumber);

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            // Call Python OCR API
            ResponseEntity<Map> response = restTemplate.exchange(PYTHON_OCR_API, HttpMethod.POST, requestEntity, Map.class);

            // Extract OCR text from response
            List<String> extractedText = (List<String>) response.getBody().get("text");

            // Find best matching product
            Optional<ProductMatchResult> matchedProduct = productMatchingUseCaseImpl.findBestMatchingProduct(orderNumber, extractedText);

            Map<String, Object> result = new HashMap<>();
            result.put("ocr_text", extractedText);
            result.put("order_number", orderNumber);

            if (matchedProduct.isPresent()) {
                result.put("matched_product", matchedProduct.get().getProduct());
                result.put("match_score", matchedProduct.get().getMatchScore()); // Include match accuracy
            } else {
                result.put("matched_product", "No match found");
                result.put("match_score", 0.0); // No match found
            }

            return ResponseEntity.ok(result);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to process OCR: " + e.getMessage()));
        }
    }

}
