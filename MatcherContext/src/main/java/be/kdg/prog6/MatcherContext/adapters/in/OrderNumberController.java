package be.kdg.prog6.MatcherContext.adapters.in;


import be.kdg.prog6.MatcherContext.ports.in.CheckOrderNumberUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/on")
public class OrderNumberController {

    private final CheckOrderNumberUseCase checkOrderNumberUseCase;

    public OrderNumberController(CheckOrderNumberUseCase checkOrderNumberUseCase) {
        this.checkOrderNumberUseCase = checkOrderNumberUseCase;
    }


    @GetMapping
    public boolean validateOrderNumber(@RequestParam("orderNumber") String orderNumber) {
        return this.checkOrderNumberUseCase.checkOrderNumber(orderNumber);
    }


}
