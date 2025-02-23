package be.kdg.prog6.MatcherContext.adapters.out;

import be.kdg.prog6.MatcherContext.domain.Product;
import be.kdg.prog6.MatcherContext.ports.out.ExtractProductsPort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class DatabaseAdapter implements ExtractProductsPort {


    private ProductJPARepository productJPARepository;

    public DatabaseAdapter(ProductJPARepository productJPARepository) {
        this.productJPARepository = productJPARepository;
    }

    @Override
    public List<Product> extractByOrderNumber(String orderNumber) {
        List<ProductJPAEntity> productJPAEntities = productJPARepository.findByOrderNumberEdi(orderNumber);
        List<Product> products= new ArrayList<>();

        for (ProductJPAEntity productJPAEntity: productJPAEntities){
            Product product= new Product();
            product.initialiseProduct(productJPAEntity.getClient(),productJPAEntity.getOrderNumberEdi(),productJPAEntity.getRefoEdi(),productJPAEntity.getOrderDate(),productJPAEntity.getCustomerName(),productJPAEntity.getQuantityOrdered(),productJPAEntity.getBatch(),productJPAEntity.getLine(),productJPAEntity.getStockInventory(),productJPAEntity.getDescription1(),productJPAEntity.getPackCode(),productJPAEntity.getProductCode(),productJPAEntity.getDescription2(),productJPAEntity.getProductExtension(),productJPAEntity.getDescriptionPackaging(),productJPAEntity.getPalletSize(),productJPAEntity.getSalesCode());
            products.add(product);

        }
        return products;

    }
}
