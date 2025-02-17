package be.kdg.prog6.MatcherContext.core;


import be.kdg.prog6.MatcherContext.adapters.out.OrderlineRepository;
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

        for (String text : ocrResult.extractedText()) {
            logger.info("Processing extracted text: " + text);

            // Exact Match on ordernummer Edi
            List<Orderline> byOrdernumberEdi = orderlineRepository.findByOrdernummerEdi(text);
            if (!byOrdernumberEdi.isEmpty()) {
                logger.info("Matched by ordernummerEdi: {}", byOrdernumberEdi);
                matches.addAll(byOrdernumberEdi);
            }

            // Fuzzy match on other fields like product code, batch number, etc.
            List<Orderline> byProductcode = orderlineRepository.findByProductCode(text);
            if (!byProductcode.isEmpty()) {
                logger.info("Matched by product code: {}", byProductcode);
                matches.addAll(byProductcode);
            }

            List<Orderline> byBatchnumber = orderlineRepository.findByBatch(text);
            if (!byBatchnumber.isEmpty()) {
                logger.info("Matched by batch number: {}", byBatchnumber);
                matches.addAll(byBatchnumber);
            }
        }

        return matches;
    }

}