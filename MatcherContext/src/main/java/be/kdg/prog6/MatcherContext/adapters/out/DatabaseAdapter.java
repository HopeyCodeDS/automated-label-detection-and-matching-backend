package be.kdg.prog6.MatcherContext.adapters.out;

import be.kdg.prog6.MatcherContext.domain.HU;
import be.kdg.prog6.MatcherContext.domain.Product;
import be.kdg.prog6.MatcherContext.ports.out.ExtractProductsPort;
import be.kdg.prog6.MatcherContext.ports.out.FetchHUPort;
import be.kdg.prog6.MatcherContext.ports.out.LinkHuToProductPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DatabaseAdapter implements ExtractProductsPort, LinkHuToProductPort, FetchHUPort {


    private ProductJPARepository productJPARepository;
    private HUJPARepository hujpaRepository;


    public DatabaseAdapter(ProductJPARepository productJPARepository, HUJPARepository hujpaRepository) {
        this.productJPARepository = productJPARepository;
        this.hujpaRepository = hujpaRepository;
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
    @Override
    @Transactional
    public void linkHuToProduct(String huNumber, String productId) {
        try {
            Optional<HUJPAEntity> huOptional = hujpaRepository.findByHuNumber(huNumber);
            Optional<ProductJPAEntity> productOptional = productJPARepository.findByProductCode(productId);

            System.out.println("🔄 Attempting to link HU: " + huNumber + " to Product: " + productId);

            if (huOptional.isPresent() && productOptional.isPresent()) {
                HUJPAEntity hu = huOptional.get();
                ProductJPAEntity product = productOptional.get();

                // ✅ Manually set the product and save
                hu.setProduct(product);
                hujpaRepository.save(hu);

                System.out.println("✅ Successfully linked HU: " + huNumber + " to Product: " + product.getProductCode());
            } else {
                System.out.println("❌ HU number or Product not found in DB.");
                throw new IllegalArgumentException("HU number or Product not found in DB.");
            }
        } catch (Exception e) {
            System.err.println("❌ ERROR linking HU to Product: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    public List<Product> extractAll() {
        List<ProductJPAEntity> productJPAEntities = productJPARepository.findAll();
        List<Product> products= new ArrayList<>();

        for (ProductJPAEntity productJPAEntity: productJPAEntities){
            Product product= new Product();
            product.initialiseProduct(productJPAEntity.getClient(),productJPAEntity.getOrderNumberEdi(),productJPAEntity.getRefoEdi(),productJPAEntity.getOrderDate(),productJPAEntity.getCustomerName(),productJPAEntity.getQuantityOrdered(),productJPAEntity.getBatch(),productJPAEntity.getLine(),productJPAEntity.getStockInventory(),productJPAEntity.getDescription1(),productJPAEntity.getPackCode(),productJPAEntity.getProductCode(),productJPAEntity.getDescription2(),productJPAEntity.getProductExtension(),productJPAEntity.getDescriptionPackaging(),productJPAEntity.getPalletSize(),productJPAEntity.getSalesCode());
            products.add(product);

        }
        return products;    }

    @Override
    public List<HU> fetchHU(String query) {
        List<HUJPAEntity> hujpaEntities= hujpaRepository.findByHuNumberContaining(query);
        List<HU> hus= new ArrayList<>();
        for (HUJPAEntity hujpaEntity: hujpaEntities){
            HU hu= new HU();
            hu.initialiseHU(hujpaEntity.getHuNumber());
            hus.add(hu);
        }
        return hus;
    }
}
