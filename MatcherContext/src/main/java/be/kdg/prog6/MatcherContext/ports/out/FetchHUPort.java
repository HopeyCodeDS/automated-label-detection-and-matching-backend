package be.kdg.prog6.MatcherContext.ports.out;

import be.kdg.prog6.MatcherContext.domain.HU;
import be.kdg.prog6.MatcherContext.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FetchHUPort {
    public List<HU> fetchHU(String query);



}
