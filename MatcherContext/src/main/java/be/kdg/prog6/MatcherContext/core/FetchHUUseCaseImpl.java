package be.kdg.prog6.MatcherContext.core;

import be.kdg.prog6.MatcherContext.domain.HU;
import be.kdg.prog6.MatcherContext.ports.in.FetchHUUseCase;
import be.kdg.prog6.MatcherContext.ports.in.ProductMatchingUseCase;
import be.kdg.prog6.MatcherContext.ports.out.ExtractProductsPort;
import be.kdg.prog6.MatcherContext.ports.out.FetchHUPort;
import be.kdg.prog6.MatcherContext.ports.out.LinkHuToProductPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FetchHUUseCaseImpl implements FetchHUUseCase {
    private static final Logger logger = LoggerFactory.getLogger(FetchHUUseCaseImpl.class);

    private final FetchHUPort fetchHUPort;

    public FetchHUUseCaseImpl(FetchHUPort fetchHUPort) {
        this.fetchHUPort = fetchHUPort;
    }

    @Override
    public List<HU> fetchHU(String query) {
        List<HU> fetchedHUs= fetchHUPort.fetchHU(query);

        return fetchedHUs;
    }
}
