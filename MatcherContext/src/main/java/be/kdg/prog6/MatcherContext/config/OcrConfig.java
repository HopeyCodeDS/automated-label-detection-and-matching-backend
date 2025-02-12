package be.kdg.prog6.MatcherContext.config;

import be.kdg.prog6.MatcherContext.adapters.out.FastApiOcrAdapter;
import be.kdg.prog6.MatcherContext.ports.out.OcrPort;
import be.kdg.prog6.MatcherContext.utils.MultipartFormDataWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OcrConfig {
    @Value("${ocr.api.url}")
    private String apiUrl;

    @Bean
    public OcrPort ocrPort(MultipartFormDataWriter formDataWriter) {
        return new FastApiOcrAdapter(apiUrl, formDataWriter);
    }
}