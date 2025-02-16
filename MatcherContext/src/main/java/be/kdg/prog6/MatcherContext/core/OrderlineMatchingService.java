package be.kdg.prog6.MatcherContext.core;


import be.kdg.prog6.MatcherContext.adapters.out.OrderlineRepository;
import be.kdg.prog6.MatcherContext.domain.MatchedOrderline;
import be.kdg.prog6.MatcherContext.domain.OcrResult;
import be.kdg.prog6.MatcherContext.domain.Orderline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderlineMatchingService {

    private static final Logger logger = LoggerFactory.getLogger(OrderlineMatchingService.class);

    @Autowired
    private OrderlineRepository orderlineRepository;

    public List<Orderline> findMatchingOrderlines(OcrResult ocrResult) {
        List<Orderline> matches = new ArrayList<>();

        // Extract potential ordernumbers from the OCR result
        List<String> potentialOrdernumbers = extractPotentialOrdernumbers(ocrResult);

        for (String ordernumber : potentialOrdernumbers) {
            List<Orderline> filteredOrderlines = orderlineRepository.findByOrdernumberEdi(ordernumber);

            for (Orderline orderline : filteredOrderlines) {
                if (matches.contains(orderline)) {
                    continue;
                }

                // Check if any extracted text matches the product code, batch number, or product description
                for (String text : ocrResult.extractedText()){
                    if (text.equals(orderline.getProductCode()) ||
                            text.equals(orderline.getBatchNumber()) ||
                            text.equals(orderline.getProductDescription())){
                        matches.add(orderline);
                        break;
                    }
                }
            }
        }

        return matches;
    }

    private List<String> extractPotentialOrdernumbers(OcrResult ocrResult) {
        List<String> potentialOrdernumbers = new ArrayList<>();
        for (String text : ocrResult.extractedText()) {
            if (text.matches("^[0-9]+$")) { // Assuming ordernumbers are numeric
                potentialOrdernumbers.add(text);
            }
        }
        return potentialOrdernumbers;
    }
}