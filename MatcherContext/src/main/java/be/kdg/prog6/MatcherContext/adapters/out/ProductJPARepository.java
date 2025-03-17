package be.kdg.prog6.MatcherContext.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ProductJPARepository extends JpaRepository<ProductJPAEntity, Long> {
    List<ProductJPAEntity> findByOrderNumberEdi(String orderNumber);
    List<ProductJPAEntity> findByProductCode(String productCode);
//    List<ProductJPAEntity> findByProductCode(String productCode);
}
