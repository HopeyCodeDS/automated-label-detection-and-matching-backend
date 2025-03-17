package be.kdg.prog6.MatcherContext.core;

import be.kdg.prog6.MatcherContext.domain.MatchDetail;
import be.kdg.prog6.MatcherContext.domain.Product;
import be.kdg.prog6.MatcherContext.domain.ProductMatchResultInfo;
import be.kdg.prog6.MatcherContext.ports.in.ProductHULinkUseCase;
import be.kdg.prog6.MatcherContext.ports.in.ProductMatchingUseCase;
import be.kdg.prog6.MatcherContext.ports.out.BatchInfoPort;
import be.kdg.prog6.MatcherContext.ports.out.ExtractProductsPort;
import be.kdg.prog6.MatcherContext.ports.out.LinkHuToProductPort;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductHULinkUseCaseImpl implements ProductHULinkUseCase {
    private static final Logger logger = LoggerFactory.getLogger(ProductHULinkUseCaseImpl.class);

    private final ExtractProductsPort extractProductsPort;
    private final BatchInfoPort batchInfoPort;
    private final LinkHuToProductPort linkHuToProductPort;

    public ProductHULinkUseCaseImpl(ExtractProductsPort extractProductsPort, BatchInfoPort batchInfoPort, LinkHuToProductPort linkHuToProductPort) {
        this.extractProductsPort = extractProductsPort;
        this.batchInfoPort = batchInfoPort;
        this.linkHuToProductPort = linkHuToProductPort;
    }

    @Override
    public void linkHuToProduct(String huNumber, String productId, String batch) {
        linkHuToProductPort.linkHuToProduct(huNumber,productId,batch);
    }
}
