package be.kdg.prog6.MatcherContext.adapters.in;
import be.kdg.prog6.MatcherContext.core.ProductMatchingUseCaseImpl;
import be.kdg.prog6.MatcherContext.domain.ProductMatchResultInfo;
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
    private final String PYTHON_OCR_API = "https://ocr-api-2025.azurewebsites.net/ocr"; // FastAPI OCR URL
//    private final String PYTHON_OCR_API = "http://localhost:8000/ocr"; // FastAPI OCR URL

    public Controller(ProductMatchingUseCaseImpl productMatchingUseCaseImpl) {
        this.productMatchingUseCaseImpl = productMatchingUseCaseImpl;
    }

    @PostMapping(value = "/extract-text", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductHuMatchDTO> extractText(
            @RequestParam("file") MultipartFile file,
            @RequestParam("orderNumber") String orderNumber,
            @RequestParam(name = "hu", required = false) String hu
    ) {


        // Check empty file
        try {
            if (file.isEmpty()) {
                throw new IllegalArgumentException("Uploaded file is empty");
            }
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


            System.out.println("OCR API response status: " + response.getStatusCode());
            System.out.println("OCR API response body: " + response.getBody());


            // Extract OCR text from response
            List<String> extractedText = (List<String>) response.getBody().get("text");

            System.out.println("Extracted text: " + extractedText);


            // Find best matching product
            ProductMatchResultInfo matchedProduct =
                    productMatchingUseCaseImpl.findBestMatchingProduct(orderNumber,hu, extractedText);

            ProductHuMatchDTO productHuMatchDTO=new ProductHuMatchDTO();
            if (matchedProduct!=null) {
                productHuMatchDTO= ProductHuMatchDTO.fromDomain(matchedProduct);
            } else {

            }
            System.out.println("✅ Sending DTO Response: " + productHuMatchDTO);


            return ResponseEntity.ok(productHuMatchDTO);

        } catch (Exception e) {
            return null;
        }
    }

}
