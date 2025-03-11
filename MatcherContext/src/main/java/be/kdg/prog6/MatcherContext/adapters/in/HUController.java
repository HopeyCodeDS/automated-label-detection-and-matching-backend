package be.kdg.prog6.MatcherContext.adapters.in;
import be.kdg.prog6.MatcherContext.domain.HU;
import be.kdg.prog6.MatcherContext.ports.in.FetchHUUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/hu")
public class HUController {
    private final FetchHUUseCase fetchHUUseCase;

    public HUController(FetchHUUseCase fetchHUUseCase) {
        this.fetchHUUseCase = fetchHUUseCase;
    }

    @GetMapping("/search")
    public List<HUResponseDTO> searchHU(@RequestParam String query) {
        List<HU> matchingHUs = fetchHUUseCase.fetchHU(query);

        return matchingHUs.stream()
                .map(hu -> new HUResponseDTO(hu.getHuNumber()))
                .collect(Collectors.toList());
    }
}
