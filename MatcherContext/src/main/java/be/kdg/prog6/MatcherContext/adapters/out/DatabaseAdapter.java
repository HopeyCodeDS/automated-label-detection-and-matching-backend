package be.kdg.prog6.MatcherContext.adapters.out;

import be.kdg.prog6.MatcherContext.domain.HU;
import be.kdg.prog6.MatcherContext.domain.Product;
import be.kdg.prog6.MatcherContext.ports.out.BatchInfoPort;
import be.kdg.prog6.MatcherContext.ports.out.ExtractProductsPort;
import be.kdg.prog6.MatcherContext.ports.out.FetchHUPort;
import be.kdg.prog6.MatcherContext.ports.out.LinkHuToProductPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DatabaseAdapter implements ExtractProductsPort, LinkHuToProductPort, FetchHUPort, BatchInfoPort {


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
    public void linkHuToProduct(String huNumber, String productId, String batch) {
        try {
            Optional<HUJPAEntity> huOptional = hujpaRepository.findByHuNumber(huNumber);
            List<ProductJPAEntity> productList = productJPARepository.findByProductCode(productId);

            System.out.println("🔄 Attempting to link HU: " + huNumber + " to Product: " + productId + " with Batch: " + batch);

            if (huOptional.isPresent() && !productList.isEmpty()) {
                HUJPAEntity hu = huOptional.get();

                Optional<ProductJPAEntity> matchedProduct = productList.stream()
                        .filter(product -> batch.equals(product.getBatch())) // Match batch
                        .findFirst(); // Get the first matching product

                if (matchedProduct.isPresent()) {
                    ProductJPAEntity product = matchedProduct.get();
                    hu.setProduct(product);
                    hujpaRepository.save(hu);

                    System.out.println("✅ Successfully linked HU: " + huNumber + " to Product: " + product.getProductCode() + " (Batch: " + batch + ")");
                } else {
                    System.out.println("❌ No matching product found with batch: " + batch);
                    throw new IllegalArgumentException("Product not found for given batch.");
                }
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
//        List<HUJPAEntity> hujpaEntities= hujpaRepository.findByHuNumberContaining(query);
        // Should be all, and we filter in the frontend
        List<HUJPAEntity> hujpaEntities= hujpaRepository.findAll();

        List<HU> hus= new ArrayList<>();
        for (HUJPAEntity hujpaEntity: hujpaEntities){
            HU hu= new HU();
            hu.initialiseHU(hujpaEntity.getHuNumber());
            hus.add(hu);
        }
        return hus;
    }

    @Override
    public List<Product> extractByProductId(String productCode) {
        List<ProductJPAEntity> productJPAEntities= new ArrayList<>();
        List<Product> products= new ArrayList<>();
        productJPAEntities= productJPARepository.findByProductCode(productCode);
        for (ProductJPAEntity productJPAEntity: productJPAEntities){
            Product product= new Product();
            product.initialiseProduct(productJPAEntity.getClient(),productJPAEntity.getOrderNumberEdi(),productJPAEntity.getRefoEdi(),productJPAEntity.getOrderDate(),productJPAEntity.getCustomerName(),productJPAEntity.getQuantityOrdered(),productJPAEntity.getBatch(),productJPAEntity.getLine(),productJPAEntity.getStockInventory(),productJPAEntity.getDescription1(),productJPAEntity.getPackCode(),productJPAEntity.getProductCode(),productJPAEntity.getDescription2(),productJPAEntity.getProductExtension(),productJPAEntity.getDescriptionPackaging(),productJPAEntity.getPalletSize(),productJPAEntity.getSalesCode());
            products.add(product);
        }
        return products;
    }
}
