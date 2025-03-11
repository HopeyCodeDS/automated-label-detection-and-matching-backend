package be.kdg.prog6.MatcherContext.ports.in;

import be.kdg.prog6.MatcherContext.domain.HU;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FetchHUUseCase {

    public List<HU> fetchHU(String query);

}
