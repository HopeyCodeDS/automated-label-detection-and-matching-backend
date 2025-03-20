package be.kdg.prog6.MatcherContext.adapters.in;
import be.kdg.prog6.MatcherContext.ports.in.ProductHULinkUseCase;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class HULinkController {
    private final ProductHULinkUseCase productHULinkUseCase;

    public HULinkController(ProductHULinkUseCase productHULinkUseCase) {
        this.productHULinkUseCase = productHULinkUseCase;
    }



    @PostMapping("/match")
    public void linkHU(  @RequestParam("batch") String batch,
                         @RequestParam("product_id") String product_id ,
                         @RequestParam("hu") String hu ) {
        productHULinkUseCase.linkHuToProduct(hu,product_id,batch);
    }
}
