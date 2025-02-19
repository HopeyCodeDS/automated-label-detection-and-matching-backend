package be.kdg.prog6.MatcherContext.adapters.out;

import be.kdg.prog6.MatcherContext.domain.ImageData;
import be.kdg.prog6.MatcherContext.domain.OcrResult;
import be.kdg.prog6.MatcherContext.ports.out.OcrPort;
import be.kdg.prog6.MatcherContext.utils.MultipartFormDataWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Component
public class FastApiOcrAdapter implements OcrPort {
    private final ObjectMapper objectMapper;
    private final MultipartFormDataWriter formDataWriter;
    private final String apiUrl;

    public FastApiOcrAdapter(@Value("${ocr.api.url}") String apiUrl, MultipartFormDataWriter formDataWriter) {
        this.apiUrl = apiUrl;
        this.objectMapper = new ObjectMapper();
        this.formDataWriter = formDataWriter;
    }

    @Override
    public OcrResult extractText(ImageData imageData) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");

            try (OutputStream os = connection.getOutputStream()) {
                formDataWriter.writeMultipartFormData(os, imageData.data());
            }

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (InputStream is = connection.getInputStream()) {
                    FastApiResponse response = objectMapper.readValue(is, FastApiResponse.class);
                    return new OcrResult(response.text(), List.of());
                }
            }
            throw new IOException("FastAPI call failed with response code: " + connection.getResponseCode());
        } catch (IOException e) {
            throw new RuntimeException("Failed to perform OCR using FastAPI", e);
        }
    }
}